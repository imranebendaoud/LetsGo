package com.example.letsgo;

import androidx.annotation.NonNull;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView createAccout;
    Button loginButton;
    EditText usernametext,passwordtext;
    Boolean loginGood = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        createAccout = (TextView)findViewById(R.id.textView14);
        createAccout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), RegisterForm.class);
                    startActivity(i);
                }
        });
        usernametext = (EditText) findViewById(R.id.username);
        passwordtext = (EditText) findViewById(R.id.password);
        loginButton=(Button)findViewById(R.id.button);
        List<User> listUsers = new ArrayList<User>();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance("https://mobilefirebase-81e77-default-rtdb.firebaseio.com").
                        getReference("Database").child("User").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            User u = snapshot.getValue(User.class);
                            Log.d("Annonce firebase",u.getFirstname());
                            listUsers.add(u);
                        }
                            for (User user : listUsers){
                                if(user.getUsername().equals(usernametext.getText().toString()) && user.getPassword().equals(passwordtext.getText().toString())){
                                    loginGood = true;
                                    Log.d("inside for", loginGood.toString());
                                    break;
                                }
                                else{
                                    loginGood = false;
                                    Log.d("inside for else", loginGood.toString());
                                }
                                Log.d("login good", loginGood.toString());
                                Log.d("users", user.getUsername()+" "+user.getPassword());
                                Log.d("users text", usernametext.getText().toString()+" "+passwordtext.getText().toString());

                            }

                        if((!TextUtils.isEmpty(usernametext.getText().toString()) || !TextUtils.isEmpty(passwordtext.getText().toString())) && loginGood==true){
                            Intent i = new Intent(getApplicationContext(), ShowAllAnnonces.class);
                            startActivity(i);
                            Toast.makeText(getApplicationContext(),"login successful",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Invalid login details !",Toast.LENGTH_SHORT).show();
                        }


                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

    }
}

