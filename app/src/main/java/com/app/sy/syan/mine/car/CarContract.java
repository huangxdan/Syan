package com.app.sy.syan.mine.car;

import com.app.sy.syan.base.BaseView;

public interface CarContract {

    interface View extends BaseView {
        void bindData();
        void showNoNet();
    }

    interface Presenter {
        void getData(String address);
    }
}
