package com.google.emarket;

public class Item {
    private String uid,name,price;

    public Item(String uid, String name, String price) {
        this.uid = uid;
        this.name = name;
        this.price = price;
    }

    public String getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
