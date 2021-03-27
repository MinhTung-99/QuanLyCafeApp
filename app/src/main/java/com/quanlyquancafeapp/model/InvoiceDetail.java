package com.quanlyquancafeapp.model;

import java.util.ArrayList;

public class InvoiceDetail extends Invoice{
    private Long idInvoice;
    private Long idProduct;
    private Integer count;

    private Customer customer;
    private Product product;

    public InvoiceDetail() {
        customer = new Customer();
        product = new Product();
    }

    public InvoiceDetail(Long idInvoice, Long idProduct) {
        this.idInvoice = idInvoice;
        this.idProduct = idProduct;

        customer = new Customer();
        product = new Product();
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
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
}
