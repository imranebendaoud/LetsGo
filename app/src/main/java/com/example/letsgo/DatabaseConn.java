package com.example.letsgo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConn {

    Connection conn;

     public DatabaseConn(){
        try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://mysql-63048-0.cloudclusters.net:17837/letsgo","admin","edo8wnIO");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    public Connection getConnection() {
        return conn;
    }

    public void setConnection(Connection connection) {
        this.conn = connection;
    }

}
