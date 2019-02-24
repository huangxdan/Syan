package com.app.sy.syan.mine.order.confirm;

import com.app.sy.syan.base.BaseView;

public interface ConfirmContract {

    interface View extends BaseView {
        void bindData();
    }

    interface Presenter {
        void getData(String address);
    }
}
