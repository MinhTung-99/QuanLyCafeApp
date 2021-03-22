package com.quanlyquancafeapp.model;

public class Invoice {
    private Long id;
    private Long idAccount;
    private Long idTable;
    private float totalMoney;
    private float inToMoney;
    private String dateBuy;
    private String time;
    private String typePay;
    private Integer isPay;

    public Invoice() {
    }
    public Invoice(Long idAccount, Long idTable, float totalMoney, float inToMoney, String dateBuy,String time,String typePay ,Integer isPay) {
        this.idAccount = idAccount;
        this.idTable = idTable;
        this.totalMoney = totalMoney;
        this.inToMoney = inToMoney;
        this.dateBuy = dateBuy;
        this.time = time;
        this.typePay = typePay;
        this.isPay = isPay;
    }
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
    public Long getIdTable() {
        return idTable;
    }
    public void setIdTable(Long idTable) {
        this.idTable = idTable;
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
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getTypePay() {
        return typePay;
    }
    public void setTypePay(String typePay) {
        this.typePay = typePay;
    }
    public Integer getIsPay() {
        return isPay;
    }
    public void setIsPay(Integer isPay) {
        this.isPay = isPay;
    }
}
