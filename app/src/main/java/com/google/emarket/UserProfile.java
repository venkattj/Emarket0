package com.google.emarket;

public class UserProfile {
    private String name,shop,location;

    public void setName(String name) {
        this.name = name;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {

        return name;
    }

    public String getShop() {
        return shop;
    }

    public String getLocation() {
        return location;
    }

    public UserProfile(String nmae, String shop, String location) {
        this.name = nmae;
        this.shop = shop;
        this.location = location;

    }
}
