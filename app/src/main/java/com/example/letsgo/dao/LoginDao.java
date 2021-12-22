package com.example.letsgo.dao;

import android.os.AsyncTask;

import com.example.letsgo.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao extends AsyncTask<String, Void, User> {
    @Override
    protected User doInBackground(String... strings) {
        User user = null;
        try {

            String username = "monsef";
            String password = "chaib";


            //Connection connection=new DBConnexion().getConnection();
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://mysql-63048-0.cloudclusters.net:17837/letsgo","admin","edo8wnIO");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user where username=? and password=?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUsername(username);
                user.setPassword(password);
                user.setAge(rs.getInt("age"));
            }

            System.out.println(user.getAge());
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return user;

    }


}
