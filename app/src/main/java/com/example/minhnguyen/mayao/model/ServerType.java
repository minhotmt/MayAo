package com.example.minhnguyen.mayao.model;

/**
 * Created by Minh Nguyen on 3/2/2018.
 */

public class ServerType {
    int id;
    String imgType;
    String name;
    String size;

    public ServerType() {
    }

    public ServerType(int id, String imgType, String name, String size) {
        this.id = id;
        this.imgType = imgType;
        this.name = name;
        this.size = size;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgType() {
        return imgType;
    }

    public void setImgType(String imgType) {
        this.imgType = imgType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
