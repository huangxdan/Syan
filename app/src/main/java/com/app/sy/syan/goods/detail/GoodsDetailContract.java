package com.app.sy.syan.goods.detail;

import com.app.sy.syan.base.BaseView;
import com.app.sy.syan.data.CartGoodsCount;
import com.app.sy.syan.data.GoodsInfo;

public interface GoodsDetailContract {
    interface View extends BaseView {
        void bindData(GoodsInfo goodsInfo);

        void bindCartCount(CartGoodsCount cartGoodsCount);
    }

    interface Presenter {
        void getData(String productId);

        void getCartCount(String productId);

        void updateCartNum(String productId);
    }
}
