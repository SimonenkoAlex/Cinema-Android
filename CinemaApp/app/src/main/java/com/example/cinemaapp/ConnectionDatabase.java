package com.example.cinemaapp;

import java.sql.*;

public class ConnectionDatabase {
    private Connection connect = null;

    static final String DRIVER = "org.postgresql.Driver";
    static final String URL = "jdbc:postgresql://localhost:5432/cinema";
    static final String USER = "postgres";
    static final String PASS = "IVTalexsim";

    public Connection connectPostgreSQL()
    {
        try
        {
            Class.forName(DRIVER);
            connect = DriverManager.getConnection(URL, USER, PASS);
        } catch(Exception error){
            System.err.println(error.getMessage());
        }
        return connect;
    }

    public void destroy() throws Exception{
        connect.close();
    }
}
