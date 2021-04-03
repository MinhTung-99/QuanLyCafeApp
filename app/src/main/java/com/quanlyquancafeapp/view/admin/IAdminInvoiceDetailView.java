package com.quanlyquancafeapp.view.admin;

import com.quanlyquancafeapp.model.Invoice;

public interface IAdminInvoiceDetailView {
    void setTotalMoneyTxt(Invoice invoice);
    void setAdapterProductInvoice();
    void setNameTableTxt();
    void setDateTxt();
    void setIdInvoiceTxt();
}
