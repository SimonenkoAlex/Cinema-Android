package com.example.cinemaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.CallableStatement;
import java.sql.Types;

public class MainActivity extends AppCompatActivity {
    private static ConnectionDatabase connect = new ConnectionDatabase();

    Button btnLogin;
    EditText loginText, passText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // присваеваем значение id каждого элемента интерфейса переменным
        btnLogin = findViewById(R.id.btnLogin);
        loginText = findViewById(R.id.loginText);
        passText = findViewById(R.id.passText);
    }

    // создание функции для авторизации на PostgreSQL
    public void authorization(String login, String password){
        try {
            String storeProcedureCall = "CALL to_login_android(?,?,?,?,?,?,?,?)";
            CallableStatement stmt = connect.connectPostgreSQL().prepareCall(storeProcedureCall);
            // первые два параметра являются входными
            stmt.setString(1, login);
            stmt.setString(2, password);
            // последние 6 выходных данных показывают какого типа параметры
            stmt.registerOutParameter(3, Types.INTEGER);
            stmt.registerOutParameter(4, Types.VARCHAR);
            stmt.registerOutParameter(5, Types.VARCHAR);
            stmt.registerOutParameter(6, Types.VARCHAR);
            stmt.registerOutParameter(7, Types.VARCHAR);
            stmt.registerOutParameter(8, Types.VARCHAR);

            stmt.executeUpdate();
            // объявляем переменные, получаемые от функции в PostgreSQL
            Integer _code = stmt.getInt(3);
            String _last_name = stmt.getString(4);
            String _first_name = stmt.getString(5);
            String _phone = stmt.getString(6);
            String _email = stmt.getString(7);
            String _report = stmt.getString(8);
            // условие проверки правильности введения имени пользователя и пароля
            if(_report.equals("OK")){

            } else {
                Toast.makeText(getApplicationContext(), _report.toString(), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception error) {
            Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}