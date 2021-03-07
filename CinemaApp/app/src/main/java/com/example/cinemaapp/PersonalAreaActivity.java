package com.example.cinemaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PersonalAreaActivity extends AppCompatActivity {
    GlobalVariables gv = GlobalVariables.getInstance();
    // объявляем переменные
    TextView lastnameText, firstnameText, phoneText, emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_area);
        // присваеваем значение id каждого элемента интерфейса переменным
        lastnameText = findViewById(R.id.lastnameText);
        firstnameText = findViewById(R.id.firstnameText);
        phoneText = findViewById(R.id.phoneText);
        emailText = findViewById(R.id.emailText);

        if(gv.get_code_user_login() != 0) {
            lastnameText.setText(gv.get_last_name().toString());
            firstnameText.setText(gv.get_first_name().toString());
            phoneText.setText(gv.get_phone().toString());
            emailText.setText(gv.get_email().toString());
        }
    }
}