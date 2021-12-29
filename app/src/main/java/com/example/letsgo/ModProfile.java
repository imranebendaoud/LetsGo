package com.example.letsgo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letsgo.model.User;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModProfile extends AppCompatActivity {

    User myUser;
    EditText username_et_id;
    EditText firstname_et_id;
    EditText lastname_et_id;
    EditText email_et_id;
    EditText password_et_id;
    EditText ville_et_id;
    EditText age_et_id;
    EditText sexe_et_id;
    EditText phone_et_id;
    EditText bio_et_id;
    Button apply_modif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_profile);

        username_et_id = (EditText) findViewById(R.id.username_et_id);
        firstname_et_id = (EditText) findViewById(R.id.firstname_et_id);
        lastname_et_id = (EditText) findViewById(R.id.lastname_et_id);
        email_et_id = (EditText) findViewById(R.id.email_et_id);
        //password_et_id = (EditText) findViewById(R.id.password_et_id);
        ville_et_id = (EditText) findViewById(R.id.ville_et_id);
        age_et_id = (EditText) findViewById(R.id.age_et_id);
        sexe_et_id = (EditText) findViewById(R.id.sexe_et_id);
        phone_et_id = (EditText) findViewById(R.id.phone_et_id);
        bio_et_id = (EditText) findViewById(R.id.bio_et_id);
        apply_modif = (Button) findViewById(R.id.apply_modif);

        myUser = (User)getIntent().getSerializableExtra("myUser");

        username_et_id.setText(myUser.getUsername());
        firstname_et_id.setText(myUser.getFirstname());
        lastname_et_id.setText(myUser.getLastname());;
        email_et_id.setText(myUser.getEmail());
        //password_et_id = (EditText) findViewById(R.id.password_et_id);
        ville_et_id.setText(myUser.getVille());
        age_et_id.setText(String.valueOf(myUser.getAge()));
        sexe_et_id.setText(myUser.getSexe());
        phone_et_id.setText(myUser.getPhone());
        bio_et_id.setText(myUser.getBio());

        apply_modif.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {
                myUser.setUsername(username_et_id.getText().toString());
                myUser.setFirstname(firstname_et_id.getText().toString());
                myUser.setLastname(lastname_et_id.getText().toString());
                myUser.setEmail(email_et_id.getText().toString());
                //password_et_id = (EditText) findViewById(R.id.password_et_id);
                myUser.setVille(ville_et_id.getText().toString());
                myUser.setAge(Integer.parseInt(age_et_id.getText().toString()));
                myUser.setSexe(sexe_et_id.getText().toString());
                myUser.setPhone(phone_et_id.getText().toString());
                myUser.setBio(bio_et_id.getText().toString());
//                switchActivities();
            }

        });

    }


//    private void switchActivities() {
//        Intent switchActivityIntent = new Intent(this, Profile.class);
//        switchActivityIntent.putExtra("myUser",  myUser);
//        startActivity(switchActivityIntent);
//    }
}
