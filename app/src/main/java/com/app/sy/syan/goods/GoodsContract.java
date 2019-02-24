package com.app.sy.syan.goods;

import com.app.sy.syan.base.BaseView;
import com.app.sy.syan.data.GoodsInfo;

import java.util.List;

public interface GoodsContract {

    interface View extends BaseView {
        void bindData(List<GoodsInfo> goodsInfos);
        void showNoNet();
    }

    interface Presenter {
        void getDate();
    }
}
