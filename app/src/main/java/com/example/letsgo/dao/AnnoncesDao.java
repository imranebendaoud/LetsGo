package com.example.letsgo.dao;

import android.os.AsyncTask;

import com.example.letsgo.model.Annonce;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnnoncesDao extends AsyncTask<Integer, Void, Annonce> {


    @Override
    protected Annonce doInBackground(Integer... params) {
        Annonce annonce = null;

        try {
            int id = params[0];
            System.out.println("idddddddd :"+id);
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://mysql-63048-0.cloudclusters.net:17837/letsgo","admin","edo8wnIO");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM annonce where id_annonce=?");
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                annonce = new Annonce();
                annonce.setTitre(rs.getString("titre"));
                annonce.setDescription(rs.getString("description"));
                annonce.setDuree(rs.getString("duree"));
                annonce.setFrais(rs.getFloat("frais"));
                annonce.setDuree(rs.getString("duree"));
                annonce.setMax_participants(rs.getInt("max_participants"));
                annonce.setContact(rs.getString("contact"));
                annonce.setAdresse(rs.getString("adresse"));
            }

            System.out.println(annonce.toString());

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


        return annonce ;
    }
}
