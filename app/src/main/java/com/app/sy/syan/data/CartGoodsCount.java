package com.app.sy.syan.data;

/**
 * date 2019/2/28
 * version
 * describe 商品详情页 购物车数量
 *
 * @author hxd
 */
public class CartGoodsCount {
    private int cartTotal;

    public int getCartTotal() {
        return cartTotal;
    }

    public void setCartTotal(int cartTotal) {
        this.cartTotal = cartTotal;
    }

    public CartGoodsCount(int cartTotal) {
        this.cartTotal = cartTotal;
    }
}
