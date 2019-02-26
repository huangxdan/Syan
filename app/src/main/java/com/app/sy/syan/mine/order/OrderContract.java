package com.app.sy.syan.mine.order;

import com.app.sy.syan.base.BaseView;

public interface OrderContract {

    interface View extends BaseView {
        void bindData();

        void showNoNet();
    }

    interface Presenter {
        void getData(String address);
    }
}