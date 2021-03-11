package com.example.cinemaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.*;

public class MainActivity extends AppCompatActivity {
    private static ConnectionDatabase connect = new ConnectionDatabase();
    GlobalVariables gv = GlobalVariables.getInstance();
    // объявляем переменные
    Button btnLogin;
    EditText loginText, passText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // присваеваем значение id каждого элемента интерфейса переменным
        btnLogin = (Button) findViewById(R.id.btnLogin);
        loginText = (EditText) findViewById(R.id.loginText);
        passText = (EditText) findViewById(R.id.passText);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authorization(loginText.getText().toString(), passText.getText().toString());
            }
        });
    }

    // создание функции для авторизации на PostgreSQL
    public void authorization(String login, String password){
        try {
            String storeProcedureCall = "SELECT * FROM to_login_android('" + login + "','" + password + "')";
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
                gv.set_code_user_login(_code);
                gv.set_last_name(_last_name);
                gv.set_first_name(_first_name);
                gv.set_phone(_phone);
                gv.set_email(_email);
                Intent personalArea = new Intent(this, PersonalAreaActivity.class);
                startActivity(personalArea);
            } else {
                Toast.makeText(getApplicationContext(), _report.toString(), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception error) {
            Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}