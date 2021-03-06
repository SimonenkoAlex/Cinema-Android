package com.example.cinemaapp;

public class ConnectionDatabase {
    private Connection myConnection;
    public boolean  init(String login, String pass)
    {
        String url = "jdbc:postgresql://localhost:5432/cinema";
        try
        {
            Class.forName("org.postgresql.Driver");
            myConnection = DriverManager.getConnection(url, login, pass);
        } catch(Exception e){
            return false;
        }
        return true;
    }
}
