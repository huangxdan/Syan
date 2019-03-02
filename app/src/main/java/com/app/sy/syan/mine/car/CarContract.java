package com.app.sy.syan.mine.car;

import com.app.sy.syan.base.BaseView;
import com.app.sy.syan.data.GoodsInfo;

import java.util.List;

public interface CarContract {

    interface View extends BaseView {
        void bindData(List<GoodsInfo> list);

        void showNoNet();
    }

    interface Presenter {
        void getData();

        void updateCartNum(String productId, String goodsNum);
    }
}
