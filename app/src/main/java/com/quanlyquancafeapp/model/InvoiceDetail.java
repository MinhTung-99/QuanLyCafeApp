package com.quanlyquancafeapp.model;

public class InvoiceDetail extends Invoice{
    private Long idInvoice;
    private Long idProduct;
    private Integer count;

    public InvoiceDetail() {
    }

    public InvoiceDetail(Long idInvoice, Long idProduct) {
        this.idInvoice = idInvoice;
        this.idProduct = idProduct;
    }

    public Long getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(Long idInvoice) {
        this.idInvoice = idInvoice;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
