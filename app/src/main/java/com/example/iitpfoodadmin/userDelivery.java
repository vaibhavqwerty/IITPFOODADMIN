package com.example.iitpfoodadmin;

public class userDelivery {
    private String uName;
    private String uAddress;
    private String uMobile;

    public userDelivery()
    {
        this.uName="abc";
        this.uAddress="Null";
        this.uMobile="Null";

    }

    public userDelivery(String name,String Address,String mobile)
    {
        this.uMobile=mobile;
        this.uAddress=Address;
        this.uName=name;
    }
    public String getuName(){return uName;}

    public String getuAddress() {
        return uAddress;
    }

    public String getuMobile() {
        return uMobile;
    }

    public void setuAddress(String uAddress) {
        this.uAddress = uAddress;
    }

    public void setuMobile(String uMobile) {
        this.uMobile = uMobile;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }
}
