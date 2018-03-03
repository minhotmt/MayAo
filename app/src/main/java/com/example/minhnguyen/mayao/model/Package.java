package com.example.minhnguyen.mayao.model;

/**
 * Created by Minh Nguyen on 3/3/2018.
 */

public class Package {
    int id;
    int size;
    int moneymo;
    int cpu;
    int memory;
    int bandwidth;


    public Package() {
    }

    public Package(int id, int size, int moneymo, int cpu, int memory, int bandwidth) {
        this.id = id;
        this.size = size;
        this.moneymo = moneymo;
        this.cpu = cpu;
        this.memory = memory;
        this.bandwidth = bandwidth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMoneymo() {
        return moneymo;
    }

    public void setMoneymo(int moneymo) {
        this.moneymo = moneymo;
    }

    public int getCpu() {
        return cpu;
    }

    public void setCpu(int cpu) {
        this.cpu = cpu;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public int getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(int bandwidth) {
        this.bandwidth = bandwidth;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}

