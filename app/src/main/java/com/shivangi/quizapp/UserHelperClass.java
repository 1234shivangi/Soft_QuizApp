package com.shivangi.quizapp;

public class UserHelperClass {
    String Name , Number , Email , Password , Confirmed_Password ;

    public UserHelperClass() {
    }

    public UserHelperClass(String name, String number, String email, String password, String confirmed_Password) {
        Name = name;
        Number = number;
        Email = email;
        Password = password;
        Confirmed_Password = confirmed_Password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getConfirmed_Password() {
        return Confirmed_Password;
    }

    public void setConfirmed_Password(String confirmed_Password) {
        Confirmed_Password = confirmed_Password;
    }
}

