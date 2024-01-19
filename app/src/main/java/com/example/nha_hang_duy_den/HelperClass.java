package com.example.nha_hang_duy_den;

public class HelperClass {
    String fullname,username,password,phone;



    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public HelperClass(String fullname, String username, String password, String phone) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.phone = phone;
    }
}
