package com.example.iitpfoodadmin;

public class foodList {
    private String text;
    private String name;
    private String quantity;
    private String totalPrice;


public foodList()
{
    this.text="hello";
    this.name="abcd";
    this.quantity="0";
    this.totalPrice="0";
}

    public foodList(String text, String name,String quantity,String totalPrice) {
        this.text = text;
        this.name = name;
        this.quantity=quantity;
        this.totalPrice=totalPrice;

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }


}

