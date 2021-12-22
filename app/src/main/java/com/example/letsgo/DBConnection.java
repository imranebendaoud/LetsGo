package com.example.letsgo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
public class DBConnection {

    public DBConnection(){

    }

    public static Connection getConnection() {
        Connection conn;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6458663","sql6458663","crV5tnb9Pn");

          //  conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mobile","root1","root1");
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void executeQuery(String query) {
        Connection conn = DBConnection.getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

}
