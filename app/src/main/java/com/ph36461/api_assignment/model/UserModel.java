package com.ph36461.api_assignment.model;

public class UserModel {
    String name, email, phone, password;


    String profileImg;
    int money;

    boolean banStatus;

    public UserModel(String name, String email, String phone, String password, String profileImg, int money, boolean banStatus) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.profileImg = profileImg;
        this.money = money;
        this.banStatus = banStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public boolean isBanStatus() {
        return banStatus;
    }

    public void setBanStatus(boolean banStatus) {
        this.banStatus = banStatus;
    }
}
