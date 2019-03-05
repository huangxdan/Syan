package com.app.sy.syan.data;

import java.util.List;

/**
 * date 2019/3/2
 * version
 * describe
 *
 * @author hxd
 */
public class MyOrderList {
    private String orderId;
    private String orderInfo;
    private String orderState;
    private List<MyOrderInfo> productList;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    public List<MyOrderInfo> getProductList() {
        return productList;
    }

    public void setProductList(List<MyOrderInfo> productList) {
        this.productList = productList;
    }
}
