package com.example.minhnguyen.mayao.model;

/**
 * Created by Minh Nguyen on 2/28/2018.
 */

public class Server {
    int id;
    String label;
    String hostname;
    String location;
    String type;
    int size;
    int ram;
    int cpu;
    int disk;
    int idcustom;
    int idprogram;

    public Server() {
    }

    public Server(int id, String label, String hostname, String location, String type, int size, int ram, int cpu, int disk, int idcustom, int idprogram) {
        this.id = id;
        this.label = label;
        this.hostname = hostname;
        this.location = location;
        this.type = type;
        this.size = size;
        this.ram = ram;
        this.cpu = cpu;
        this.disk = disk;
        this.idcustom = idcustom;
        this.idprogram = idprogram;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getCpu() {
        return cpu;
    }

    public void setCpu(int cpu) {
        this.cpu = cpu;
    }

    public int getDisk() {
        return disk;
    }

    public void setDisk(int disk) {
        this.disk = disk;
    }

    public int getIdcustom() {
        return idcustom;
    }

    public void setIdcustom(int idcustom) {
        this.idcustom = idcustom;
    }

    public int getIdprogram() {
        return idprogram;
    }

    public void setIdprogram(int idprogram) {
        this.idprogram = idprogram;
    }
}
