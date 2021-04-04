package com.quanlyquancafeapp.model;

import java.io.Serializable;

public class Table implements Serializable {
    private Long id;
    private String name;
    private int countPeople;
    private int countCurrentPeople;

    public Table() {
    }

    public Table(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getCountPeople() {
        return countPeople;
    }
    public void setCountPeople(int countPeople) {
        this.countPeople = countPeople;
    }
    public int getCountCurrentPeople() {
        return countCurrentPeople;
    }
    public void setCountCurrentPeople(int countCurrentPeople) {
        this.countCurrentPeople = countCurrentPeople;
    }
}
