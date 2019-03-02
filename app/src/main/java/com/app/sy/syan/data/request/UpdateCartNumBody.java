package com.app.sy.syan.data.request;

/**
 * date 2019/2/28
 * version
 * describe
 *
 * @author hxd
 */
public class UpdateCartNumBody {
    private String staffNumber;
    private String productId;
    private String goodsNum;

    public UpdateCartNumBody(String staffNumber, String productId, String goodsNum) {
        this.staffNumber = staffNumber;
        this.productId = productId;
        this.goodsNum = goodsNum;
    }
}
