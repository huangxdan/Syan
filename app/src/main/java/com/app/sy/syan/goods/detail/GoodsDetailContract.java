package com.app.sy.syan.goods.detail;

import com.app.sy.syan.base.BaseView;
import com.app.sy.syan.data.GoodsInfo;

public interface GoodsDetailContract {
    interface View extends BaseView{
        void bindData(GoodsInfo goodsInfo);
    }
    interface Presenter{
        void getData(String productId);
    }
}
