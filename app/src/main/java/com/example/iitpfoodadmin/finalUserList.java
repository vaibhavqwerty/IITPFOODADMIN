package com.example.iitpfoodadmin;

import com.example.iitpfoodadmin.foodList;

import java.util.ArrayList;

public class finalUserList {

    ArrayList<userDelivery> userDeliveryArrayList;

    finalUserList() {
        userDeliveryArrayList = new ArrayList<>();
    }

    finalUserList(ArrayList<userDelivery> userDeliveryArrayList) {
        this.userDeliveryArrayList = userDeliveryArrayList;
    }

    ArrayList<userDelivery> getUserDeliveryArrayList() {
        return userDeliveryArrayList;
    }

}
