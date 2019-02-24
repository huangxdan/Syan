package com.app.sy.syan.love;

import com.app.sy.syan.base.BaseView;
import com.app.sy.syan.data.LoveInfo;

public interface LoveContract {
    interface View extends BaseView {
        void bindData(LoveInfo loveInfo);
    }

    interface Presenter {
        void getData();
    }
}
