package com.app.sy.syan.mine.order;

import com.app.sy.syan.base.BaseView;
import com.app.sy.syan.data.MyOrderList;

import java.util.List;

public interface OrderContract {

    interface View extends BaseView {
        void bindData(List<MyOrderList> lists);

        void showNoNet();
    }

    interface Presenter {
        void getData();
    }
}
