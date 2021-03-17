package com.quanlyquancafeapp.model;

import java.io.Serializable;
import java.util.Date;

public class InvoiceOld extends Order implements Serializable {
    private Long id;
    private String date;

    public InvoiceOld(Long id, String date, Long idProduct, Long idTable, Long idAccount, int count, float totalMoney, float intoMoney) {
        super(idProduct, idTable, idAccount, count, totalMoney, intoMoney);
        this.id = id;
        this.date = date;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
}
