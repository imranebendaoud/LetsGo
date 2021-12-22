package com.example.letsgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.letsgo.model.Annonce;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowAllAnnonces extends AppCompatActivity {
    Button addannonce;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_annonces);
        Button addannonce = (Button) findViewById(R.id.button2);
        addannonce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddAnnonce.class);
                startActivity(i);
            }
        });
        List<Annonce> listAnnonces = new ArrayList<Annonce>();
        FirebaseDatabase.getInstance("https://mobilefirebase-81e77-default-rtdb.firebaseio.com").
                getReference("Database").child("Annonce").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Annonce a = snapshot.getValue(Annonce.class);
                    Log.d("Annonce firebase",a.getContact());
                    listAnnonces.add(a);
                }
                Log.d("listfunctionInside", listAnnonces.toString());
                final ListView listView = (ListView) findViewById(R.id.annonceListView);
                listView.setAdapter(new CustomListAdapter(getApplicationContext(), listAnnonces));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                        Log.d("test","test ana hnaa");
                        Object o = listView.getItemAtPosition(position);
                        Annonce annonce = (Annonce) o;
                        Intent i = new Intent(getApplicationContext(), DetailsAnnonce.class);
//                Toast.makeText(ShowAllAnnonces.this, "Selected :" + " " + annonce, Toast.LENGTH_LONG).show();
                        i.putExtra("Annonce",annonce);
                        startActivity(i);
                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }






}