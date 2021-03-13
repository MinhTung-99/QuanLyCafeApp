package com.quanlyquancafeapp.model;

public class User {
    private Long id;
    private String nameStore;
    private String address;
    private String phoneNumber;
    private String gender;
    private String userName;
    private String password;
    private String typeUser;

    public User() {
    }
    public User(Long id, String nameStore, String address, String phoneNumber, String gender, String userName, String password, String typeUser) {
        this.id = id;
        this.nameStore = nameStore;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.userName = userName;
        this.password = password;
        this.typeUser = typeUser;
    }
    public User(String nameStore, String address, String phoneNumber, String userName, String password, String typeUser) {
        this.nameStore = nameStore;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.password = password;
        this.typeUser = typeUser;
    }
    public User(String phoneNumber, String gender, String userName, String password, String typeUser) {
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.userName = userName;
        this.password = password;
        this.typeUser = typeUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameStore() {
        return nameStore;
    }

    public void setNameStore(String nameStore) {
        this.nameStore = nameStore;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }
}
