package com.quanlyquancafeapp.model;

public class Admin {
    private int color;
    private int image;
    private String type;

    public Admin(int color, int image, String type) {
        this.color = color;
        this.image = image;
        this.type = type;
    }

    public int getColor() {
        return color;
    }
    public void setColor(int color) {
        this.color = color;
    }
    public int getImage() {
        return image;
    }
    public void setImage(int image) {
        this.image = image;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
