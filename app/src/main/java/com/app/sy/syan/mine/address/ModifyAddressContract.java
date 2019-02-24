package com.app.sy.syan.mine.address;

import com.app.sy.syan.base.BaseView;

public interface ModifyAddressContract {

    interface View extends BaseView {
        void modifySuccess();
    }

    interface Presenter {
        void modify(String address);
    }
}
