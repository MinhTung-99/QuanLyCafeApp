package com.quanlyquancafeapp.model;

public class Order {
    private Long idProduct;
    private Long idTable;
    private Long idAccount;
    private int count;
    private float totalMoney;
    private float intoMoney;

    public Order() {
    }

    public Order(Long idProduct, Long idTable, Long idAccount, int count, float totalMoney, float intoMoney) {
        this.idProduct = idProduct;
        this.idTable = idTable;
        this.idAccount = idAccount;
        this.count = count;
        this.totalMoney = totalMoney;
        this.intoMoney = intoMoney;
    }
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
    public Long getIdAccount() {
        return idAccount;
    }
    public void setIdAccount(Long idAccount) {
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
