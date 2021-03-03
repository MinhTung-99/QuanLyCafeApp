package com.quanlyquancafeapp.model;

public class Order {
    private Long idProduct;
    private Long idTable;
    private String idAccount;
    private int count;
    private float totalMoney;
    private float intoMoney;

    public Long getIdProduct() {
        return idProduct;
    }
    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }
    public Long getIdTable() {
        return idTable;
    }
    public void setIdTable(Long idTable) {
        this.idTable = idTable;
    }
    public String getIdAccount() {
        return idAccount;
    }
    public void setIdAccount(String idAccount) {
        this.idAccount = idAccount;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public float getTotalMoney() {
        return totalMoney;
    }
    public void setTotalMoney(float totalMoney) {
        this.totalMoney = totalMoney;
    }
    public float getIntoMoney() {
        return intoMoney;
    }
    public void setIntoMoney(float intoMoney) {
        this.intoMoney = intoMoney;
    }
}
