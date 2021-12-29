package com.example.letsgo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.letsgo.model.Annonce;
import com.squareup.picasso.Picasso;

public class DetailsAnnonce extends AppCompatActivity {
    TextView title,adresse,description,duree,contact,max_participants,frais;
    ImageView imageAnnonce;
    Annonce annonce;
    Button button;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_annonce);
        title=(TextView)findViewById(R.id.title);
        imageAnnonce=(ImageView)findViewById(R.id.imageAnnonce);
        adresse=findViewById(R.id.adresse);
        description = findViewById(R.id.description);
        duree=findViewById(R.id.dureeVoyage);
        contact=findViewById(R.id.contact);
        max_participants=findViewById(R.id.availablePlaces);
        frais=findViewById(R.id.frais);
        button=findViewById(R.id.buttonLet);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddToParticipants.class);
                startActivity(i);
            }
        });
        Log.d("test","details annonce");
        Intent i = getIntent();
        final Annonce[] annonce = {(Annonce) i.getSerializableExtra("Annonce")};

        Log.d("titre annonce",annonce[0].getTitre());
        title.setText(annonce[0].getTitre());
        description.setText(annonce[0].getDescription());
        duree.setText(annonce[0].getDuree());
        contact.setText(annonce[0].getContact());
        max_participants.setText(Integer.toString(annonce[0].getMax_participants()));
        frais.setText(Double.toString(annonce[0].getFrais()));
        adresse.setText(annonce[0].getAdresse());
        Picasso.with(getApplicationContext()).load(annonce[0].getImages_url()).into(imageAnnonce);


    }


}