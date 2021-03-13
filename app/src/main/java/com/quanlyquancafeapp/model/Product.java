package com.quanlyquancafeapp.model;

import android.graphics.Bitmap;
import android.net.Uri;

public class Product {
    private Long id;
    private String name;
    private byte[] image;
    private Bitmap bitmap;
    private String unit;
    private float price;
    private String sale;
    private int availableQuantity;
    private String species;
    private int count;
    private int barcode;

    public Product() {
    }
    public Product(Long id, String name, byte[] image, int price, String species, String sale) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.species = species;
        this.sale = sale;
    }

    public Product(String name, byte[] image, String unit, float price, String sale, int availableQuantity, String species, int barcode) {
        this.name = name;
        this.image = image;
        this.unit = unit;
        this.price = price;
        this.sale = sale;
        this.availableQuantity = availableQuantity;
        this.species = species;
        this.barcode = barcode;
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
    public byte[] getImage() {
        return image;
    }
    public void setImage(byte[] image) {
        this.image = image;
    }
    public String getUnit() {
        return unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public String getSale() {
        return sale;
    }
    public void setSale(String sale) {
        this.sale = sale;
    }
    public int getAvailableQuantity() {
        return availableQuantity;
    }
    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }
    public String getSpecies() {
        return species;
    }
    public void setSpecies(String species) {
        this.species = species;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public int getBarcode() {
        return barcode;
    }
    public void setBarcode(int barcode) {
        this.barcode = barcode;
    }
    public Bitmap getBitmap() {
        return bitmap;
    }
    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
