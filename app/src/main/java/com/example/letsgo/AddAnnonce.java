package com.example.letsgo;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.letsgo.model.Annonce;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.Objects;

public class AddAnnonce extends AppCompatActivity {
    public static final int PICK_IMAGE = 100;
    // Connection connection;
    AlertDialog.Builder builder;
    EditText titre,description,dateevenement,duree,frais,contact,maxParticipant;
    String titreString,descriptionString,dateevenementString,dureeString,contactString;
    Integer maxParticipantString;
    Double fraisString;
    Button saveButton,imageButton;
    ImageView image;
    Uri imageUri;
    public void pickImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            image.setImageURI(imageUri);
            Log.d("imaage", imageUri.toString());
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.addannonce);
        builder = new AlertDialog.Builder(this);
        saveButton = (Button) findViewById(R.id.buttonSave);
        imageButton = (Button) findViewById(R.id.buttonImage);
        image =(ImageView) findViewById(R.id.imageView2);
        titre =(EditText) findViewById(R.id.editTextTextPersonName2);
        description =(EditText) findViewById(R.id.discription_et);
        duree =(EditText) findViewById(R.id.editTextTextPersonName3);
        frais =(EditText) findViewById(R.id.editTextTextPersonName4);
        contact =(EditText) findViewById(R.id.editTextPhone);
        maxParticipant =(EditText) findViewById(R.id.editTextNumber);
        dateevenement =(EditText) findViewById(R.id.editTextDate);
        imageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pickImage();
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                titreString = titre.getText().toString();
                descriptionString = description.getText().toString();
                dureeString = duree.getText().toString();
                contactString = contact.getText().toString();
                Date date = new Date();
                if (imageUri == null){
                    imageUri=Uri.parse("content://com.android.calendar/");
                }
                if(!TextUtils.isEmpty(titreString)&&!TextUtils.isEmpty(descriptionString)&&!TextUtils.isEmpty(dureeString)&&!TextUtils.isEmpty(contactString)&&!TextUtils.isEmpty(maxParticipant.getText().toString())&&!TextUtils.isEmpty(frais.getText().toString())){
                    builder.setMessage("Do you want to add this annonce ?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    maxParticipantString = Integer.parseInt(maxParticipant.getText().toString());
                                    fraisString = Double.parseDouble(frais.getText().toString());
                                    Annonce a = new Annonce(9,titreString,descriptionString,date,dureeString,maxParticipantString,null,fraisString,imageUri.toString(),contactString);
                                    FirebaseDatabase database = FirebaseDatabase.getInstance("https://mobilefirebase-81e77-default-rtdb.firebaseio.com");
                                    DatabaseReference myRef = database.getReference("Database").child("Annonce");
                                    myRef.push().setValue(a);
                                    Intent i = new Intent(getApplicationContext(), ShowAllAnnonces.class);
                                    startActivity(i);
                                    Toast.makeText(getApplicationContext(),"Annonce added successfuly !",Toast.LENGTH_SHORT).show();
                                    finish();
                                    Toast.makeText(getApplicationContext(),"you choose yes action for alertbox",
                                            Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //  Action for 'NO' Button
                                    dialog.cancel();
                                    Toast.makeText(getApplicationContext(),"you choose no action for alertbox",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.setTitle("Alert Dialog");
                    alert.show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Invalid annonce details !",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


}