package com.quanlyquancafeapp.model;

public class Product {
    private Long id;
    private String name;
    private int image;
    private String unit;
    private float price;
    private String sale;
    private int availableQuantity;
    private String species;
    private int count;

    public Product(Long id,String name, int image, int price,String species, String sale) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.species = species;
        this.sale = sale;
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
    public int getImage() {
        return image;
    }
    public void setImage(int image) {
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
}
