package com.example.letsgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.letsgo.dao.AllAnnoncesDao;
import com.example.letsgo.model.Annonce;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ShowAllAnnonces extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_annonces);
        List<Annonce> annonces = getAnnoncesData();
        Log.d("photo", annonces.toString());
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



    private  List<Annonce> getAnnoncesData() {
        Log.d("get annonce data","im there");

        List<Annonce> listAnnonces = new ArrayList<Annonce>();
        try {
            listAnnonces =new AllAnnoncesDao().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return listAnnonces;
    }


}