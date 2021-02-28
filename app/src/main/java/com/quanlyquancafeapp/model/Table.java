package com.quanlyquancafeapp.model;

import java.io.Serializable;

public class Table implements Serializable {
    private Long id;
    private String tableId;
    private String name;
    public Table(Long id, String tableId,String name) {
        this.id = id;
        this.tableId = tableId;
        this.name = name;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTableId() {
        return tableId;
    }
    public void setTableId(String tableId) {
        this.tableId = tableId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
