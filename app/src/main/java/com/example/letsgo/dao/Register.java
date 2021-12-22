package com.example.letsgo.dao;

import android.os.AsyncTask;
import android.util.Log;

import com.example.letsgo.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Register extends AsyncTask<String, Void, User> {
    Connection connection=null;

    @Override
    protected User doInBackground(String... strings) {
        Log.d("TAG", "ana f add user");
        User user = null;

        try {
            String firstname=strings[0];
            String lastname=strings[1];
            String username=strings[2];
            String email=strings[3];
            String password=strings[4];
            String image=strings[5];
            String phone=strings[6];
            String ville=strings[7];
            String bio=strings[8];
            String sexe=strings[9];
            String age=strings[10];


            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://mysql-63048-0.cloudclusters.net:17837/letsgo","admin","edo8wnIO");
            Log.d("con", connection.toString());


            String sql="INSERT INTO user(firstname,lastname,username,email,password,image,phone,ville,bio,sexe,age)" +
                    " VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement=connection.prepareStatement(sql);
            statement.setString(1,firstname);
            statement.setString(2,lastname);
            statement.setString(3,username);
            statement.setString(4,email);
            statement.setString(5,password);
            statement.setString(6,image);
            statement.setString(7,phone);
            statement.setString(8,ville);
            statement.setString(9,bio);
            statement.setString(10,sexe);
            statement.setInt(11,Integer.parseInt(age));
            //Log.d("username",username);
            //statement.setString(9,null);
            statement.execute();
           // ResultSet rs = statement.executeQuery();
            user = new User(firstname,lastname,username,email,password,image,phone,ville,bio,sexe,Integer.parseInt(age));

            connection.close();
            Log.d("TAG", "doInBackground: "+user.getBio());

        }
        catch(Exception e)
        {
            Log.d("errorM", e.toString());
            e.printStackTrace();
        }

        return user;

    }


}

