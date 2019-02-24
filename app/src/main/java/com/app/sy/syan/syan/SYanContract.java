package com.app.sy.syan.syan;

import com.app.sy.syan.base.BaseView;
import com.app.sy.syan.data.SYanInfo;

public class SYanContract {
    interface View extends BaseView {
        void bindData(SYanInfo goodsInfo);
    }

    interface Presenter {
        void getData();
    }
}
