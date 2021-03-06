package com.example.cinemaapp;

public class GlobalVariables {
    private static GlobalVariables instance;
    private static int _code_user_login = 0;
    private static String _last_name;
    private static String _first_name;
    private static String _phone;
    private static String _email;

    public int get_code_user_login() {
        return _code_user_login;
    }

    public void set_code_user_login(int _code_user_login) {
        GlobalVariables._code_user_login = _code_user_login;
    }

    public String get_last_name() {
        return _last_name;
    }

    public void set_last_name(String _last_name) {
        GlobalVariables._last_name = _last_name;
    }

    public String get_first_name() {
        return _first_name;
    }

    public void set_first_name(String _first_name) {
        GlobalVariables._first_name = _first_name;
    }

    public String get_phone() {
        return _phone;
    }

    public void set_phone(String _phone) {
        GlobalVariables._phone = _phone;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        GlobalVariables._email = _email;
    }

    public static synchronized GlobalVariables getInstance() {
        if (instance == null) {
            instance = new GlobalVariables();
        }
        return instance;
    }
}
