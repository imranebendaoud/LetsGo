package com.example.letsgo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.letsgo.dao.AllAnnoncesDao;
import com.example.letsgo.dao.AnnoncesDao;
import com.example.letsgo.model.Annonce;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ListAnnonces extends AppCompatActivity {
    private Annonce annonce;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Annonce> annonces = getAnnoncesData();

        final ListView listView = (ListView) findViewById(R.id.listView1);
        listView.setAdapter(new CustomListAdapter(this, annonces));
        Intent i = getIntent();
        annonce = (Annonce)i.getSerializableExtra("annonce");
        // When the user clicks on the ListItem
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("test","test");
                Object o = listView.getItemAtPosition(position);
                Annonce annonce = (Annonce) o;
//                Toast.makeText(getApplicationContext(), "Selected :" + " " + annonce, Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(), DetailsAnnonce.class);
                i.putExtra("Annonce",annonce);
                startActivity(i);
            };
        }) ;
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