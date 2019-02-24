package com.app.sy.syan.data;

/**
 *
 * version
 * describe 今日收益
 */
public class WelfareInfo {
    //                 --账户id
    private String accountId;
    //                			--员工id
    private String staffId;
    private String staffName;
    private String staffNumber;
    //        			--今日服务费用
    private String todayServiceFee;
    //        			--货品服务费用
    private String goodsServiceFee;
    //      			--发票服务费用
    private String invoiceServiceFee;
    //           			--本周总服务费用
    private String weekTotalFee;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffNumber() {
        return staffNumber;
    }

    public void setStaffNumber(String staffNumber) {
        this.staffNumber = staffNumber;
    }

    public String getTodayServiceFee() {
        return todayServiceFee;
    }

    public void setTodayServiceFee(String todayServiceFee) {
        this.todayServiceFee = todayServiceFee;
    }

    public String getGoodsServiceFee() {
        return goodsServiceFee;
    }

    public void setGoodsServiceFee(String goodsServiceFee) {
        this.goodsServiceFee = goodsServiceFee;
    }

    public String getInvoiceServiceFee() {
        return invoiceServiceFee;
    }

    public void setInvoiceServiceFee(String invoiceServiceFee) {
        this.invoiceServiceFee = invoiceServiceFee;
    }

    public String getWeekTotalFee() {
        return weekTotalFee;
    }

    public void setWeekTotalFee(String weekTotalFee) {
        this.weekTotalFee = weekTotalFee;
    }
}
