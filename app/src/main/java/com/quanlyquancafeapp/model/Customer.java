package com.quanlyquancafeapp.model;

import java.io.Serializable;

public class Customer implements Serializable {
    private Long id;
    private String name;
    private int count;
    private int done;

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
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public int getDone() {
        return done;
    }
    public void setDone(int done) {
        this.done = done;
    }
}
