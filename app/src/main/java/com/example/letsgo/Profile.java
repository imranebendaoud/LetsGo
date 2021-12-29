package com.example.letsgo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letsgo.model.User;

public class Profile extends AppCompatActivity {
    User myUser = new User();

    TextView username_tv_id;
    TextView firstname_tv_id;
    TextView lastname_tv_id;
    TextView email_tv_id;
    TextView password_tv_id;
    TextView ville_tv_id;
    TextView age_tv_id;
    TextView sexe_tv_id;
    TextView phone_tv_id;
    TextView bio_tv_id;
    Button modify_profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        username_tv_id = (TextView) findViewById(R.id.username_et_id);
        firstname_tv_id = (TextView) findViewById(R.id.firstname_et_id);
        lastname_tv_id = (TextView) findViewById(R.id.lastname_et_id);
        email_tv_id = (TextView) findViewById(R.id.email_et_id);
        //password_tv_id = (TextView) findViewById(R.id.password_tv_id);
        ville_tv_id = (TextView) findViewById(R.id.ville_et_id);
        age_tv_id = (TextView) findViewById(R.id.age_et_id);
        sexe_tv_id = (TextView) findViewById(R.id.sexe_et_id);
        phone_tv_id = (TextView) findViewById(R.id.phone_et_id);
        bio_tv_id = (TextView) findViewById(R.id.bio_et_id);
        modify_profile = (Button) findViewById(R.id.modify_profile);

        myUser = (User)getIntent().getSerializableExtra("myUser");

        username_tv_id.setText(myUser.getUsername());
        firstname_tv_id.setText(myUser.getFirstname());
        lastname_tv_id.setText(myUser.getLastname());;
        email_tv_id.setText(myUser.getEmail());
        //password_tv_id = (TextView) findViewById(R.id.password_tv_id);
        ville_tv_id.setText(myUser.getVille());
        age_tv_id.setText(String.valueOf(myUser.getAge()));
        sexe_tv_id.setText(myUser.getSexe());
        phone_tv_id.setText(myUser.getPhone());
        bio_tv_id.setText(myUser.getBio());
            modify_profile.setOnClickListener(new View.OnClickListener() {

                @Override

                public void onClick(View view) {
                    Log.d("hello","i'm here now");
                    System.out.println("Hey this is switch activity");
                    switchActivities();
                }

            });
}

        private void switchActivities() {
            Intent i = new Intent(getApplicationContext(), ModProfile.class);
            i.putExtra("myUser",myUser);
            startActivity(i);
        }

}
