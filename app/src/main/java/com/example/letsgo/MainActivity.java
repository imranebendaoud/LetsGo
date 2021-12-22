package com.example.letsgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.letsgo.dao.AnnoncesDao;
import com.example.letsgo.dao.LoginDao;
import com.example.letsgo.model.Annonce;
import com.example.letsgo.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mysql.jdbc.Util;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    TextView createAccout;
    Button loginButton;
    EditText usernametext,passwordtext;
    Boolean loginGood = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
       /* Date date = new Date();
        Annonce a = new Annonce(9,"titre 2","description 2",date,"duree 2",
                10,null,1000,"https://cutt.ly/vUtK69I","contact 2");
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://mobilefirebase-81e77-default-rtdb.firebaseio.com");
        DatabaseReference myRef = database.getReference("Database").child("Annonce");
        myRef.push().setValue(a);
        User u = new User("admin","admin","admin","admin@admin.com","admin","imageurl","064545234","marrakech",
                "bio bio bio","homme",22);
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://mobilefirebase-81e77-default-rtdb.firebaseio.com");
        DatabaseReference myRef = database.getReference("Database").child("User");
        myRef.push().setValue(u);
*/
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

