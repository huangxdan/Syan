package com.app.sy.syan.mine;

import com.app.sy.syan.base.BaseView;
import com.app.sy.syan.data.StaffInfo;

public class MineContract {

    interface View extends BaseView {
        void bindDate(StaffInfo staffInfo);
        void showNoNet();
    }

    interface Presenter {
        void getData();
    }
}
