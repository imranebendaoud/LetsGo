package com.example.letsgo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.letsgo.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterForm extends AppCompatActivity {

    TextView loginTXT;
    Button a_btnReg;
    EditText a_txtfirstname, a_txtlastname, a_txtusername, a_txtphone, a_txtbio, a_txtville, a_txtsexe, a_txtage, a_txtpassword,a_txtimage,a_txtemail;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_form);
        a_btnReg=(Button)findViewById(R.id.btnReg);
        a_txtfirstname=(EditText)findViewById(R.id.txtFullName);
        a_txtlastname=(EditText)findViewById(R.id.txtUser);
        a_txtusername=(EditText)findViewById(R.id.user);
        a_txtphone=(EditText)findViewById(R.id.ph);
        a_txtimage=(EditText)findViewById(R.id.img);
        a_txtbio=(EditText)findViewById(R.id.bio);
        a_txtville=(EditText)findViewById(R.id.vil);
        a_txtemail=(EditText)findViewById(R.id.mail);
        a_txtsexe=(EditText)findViewById(R.id.sx);
        a_txtage=(EditText)findViewById(R.id.age);
        a_txtpassword=(EditText)findViewById(R.id.pass);
//        a_btnLogin.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setContentView(R.layout.);
//            }
//        });
        loginTXT=(TextView)findViewById(R.id.loginTXT);
        loginTXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
        a_btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstname= a_txtfirstname.getText().toString();
                String lastname= a_txtlastname.getText().toString();
                String username= a_txtusername.getText().toString();
                String email= a_txtphone.getText().toString();
                String password= a_txtpassword.getText().toString();
                String image= a_txtimage.getText().toString();
                String phone= a_txtphone.getText().toString();
                String ville= a_txtville.getText().toString();
                String bio= a_txtbio.getText().toString();
                String sexe= a_txtsexe.getText().toString();
                String age= a_txtage.getText().toString();
                if(!TextUtils.isEmpty(firstname)||!TextUtils.isEmpty(lastname)||!TextUtils.isEmpty(username)||!TextUtils.isEmpty(email)||!TextUtils.isEmpty(password)||!TextUtils.isEmpty(sexe)||!TextUtils.isEmpty(age)||!TextUtils.isEmpty(bio)||!TextUtils.isEmpty(phone)||!TextUtils.isEmpty(image)||!TextUtils.isEmpty(ville)){
                    User u = new User(firstname,lastname,username,email,password,image,phone,ville,bio,sexe,Integer.parseInt(age));
                    Log.d("looooooog", "onClick: "+firstname+lastname+username);
                    FirebaseDatabase database = FirebaseDatabase.getInstance("https://mobilefirebase-81e77-default-rtdb.firebaseio.com");
                    DatabaseReference myRef = database.getReference("Database").child("User");
                    myRef.push().setValue(u);

                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(),"register successful",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Invalid register details !",Toast.LENGTH_SHORT).show();
                }
                 }
        });
    }
}