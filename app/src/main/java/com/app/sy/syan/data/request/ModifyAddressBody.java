package com.app.sy.syan.data.request;

public class ModifyAddressBody {
    private String staffNumber;
    private String address;

    public ModifyAddressBody(String staffNumber, String address) {
        this.staffNumber = staffNumber;
        this.address = address;
    }
}
