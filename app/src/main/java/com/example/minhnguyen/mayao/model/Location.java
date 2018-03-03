package com.example.minhnguyen.mayao.model;

/**
 * Created by Minh Nguyen on 3/2/2018.
 */

public class Location {

    int id;
    String img;
    String name1;
    String name2;

    public Location(int id, String img, String name1, String name2) {
        this.id = id;
        this.img = img;
        this.name1 = name1;
        this.name2 = name2;
    }

    public Location() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }
}
