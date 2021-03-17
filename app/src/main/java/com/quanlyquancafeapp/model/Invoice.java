package com.quanlyquancafeapp.model;

public class Invoice {
    private Long id;
    private Long idAccount;
    private Long idProduct;
    private Long idTable;
    private Integer count;
    private float totalMoney;
    private float inToMoney;
    private String dateBuy;
    private Integer isPay;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getIdAccount() {
        return idAccount;
    }
    public void setIdAccount(Long idAccount) {
        this.idAccount = idAccount;
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
    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
    public float getTotalMoney() {
        return totalMoney;
    }
    public void setTotalMoney(float totalMoney) {
        this.totalMoney = totalMoney;
    }
    public float getInToMoney() {
        return inToMoney;
    }
    public void setInToMoney(float inToMoney) {
        this.inToMoney = inToMoney;
    }
    public String getDateBuy() {
        return dateBuy;
    }
    public void setDateBuy(String dateBuy) {
        this.dateBuy = dateBuy;
    }
    public Integer getIsPay() {
        return isPay;
    }
    public void setIsPay(Integer isPay) {
        this.isPay = isPay;
    }
}
