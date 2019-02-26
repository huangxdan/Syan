package com.app.sy.syan.mine.order.pay;

import com.app.sy.syan.base.BaseView;

public interface PayContract {

    interface View extends BaseView {
        void bindData();
    }

    interface Presenter {
        void getData(String address);
    }
}
