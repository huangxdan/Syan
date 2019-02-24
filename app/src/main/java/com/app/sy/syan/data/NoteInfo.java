package com.app.sy.syan.data;

public class NoteInfo {
    //                   --订单id(留言id)
    private String orderId;
    //               		--订单信息(留言信息)
    private String orderInfo;
    //             			--订单状态(留言状态)
    private String orderState;
    //     		--订单人编号(员工编号)
    private String orderPersonNumber;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getOrderPersonNumber() {
        return orderPersonNumber;
    }

    public void setOrderPersonNumber(String orderPersonNumber) {
        this.orderPersonNumber = orderPersonNumber;
    }
}
