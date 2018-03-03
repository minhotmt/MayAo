package com.example.minhnguyen.mayao.model;

/**
 * Created by Minh Nguyen on 2/28/2018.
 */

public class Customer {
    int id;
    String name;
    String pass;
    String email;
    String phone;
    int idserver;

    public Customer() {
    }

    public Customer(int id, String name, String pass, String email, String phone, int idserver) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.email = email;
        this.phone = phone;
        this.idserver = idserver;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getIdserver() {
        return idserver;
    }

    public void setIdserver(int idserver) {
        this.idserver = idserver;
    }
}
