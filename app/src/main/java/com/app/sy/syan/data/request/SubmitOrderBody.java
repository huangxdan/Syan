package com.app.sy.syan.data.request;

import java.util.List;

/**
 * date 2019/3/3
 * version
 * describe
 *
 * @author hxd
 */
public class SubmitOrderBody {
    private String staffNumber;
    private String moneyTotal;
    private List<OrderInfo> orderinfo;

    public SubmitOrderBody(String staffNumber, String moneyTotal, List<OrderInfo> orderinfo) {
        this.staffNumber = staffNumber;
        this.moneyTotal = moneyTotal;
        this.orderinfo = orderinfo;
    }

    public static class OrderInfo {
        private String productId;
        private int goodCount;
        private String money;

        public OrderInfo(String productId, int goodCount, String money) {
            this.productId = productId;
            this.goodCount = goodCount;
            this.money = money;
        }
    }
}
