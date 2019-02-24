package com.app.sy.syan.data.request;

public class NoteBody {
    private String staffNumber;
    private String orderInfo;

    public String getStaffNumber() {
        return staffNumber;
    }

    public void setStaffNumber(String staffNumber) {
        this.staffNumber = staffNumber;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    public NoteBody(String staffNumber, String orderInfo) {
        this.staffNumber = staffNumber;
        this.orderInfo = orderInfo;
    }
}
