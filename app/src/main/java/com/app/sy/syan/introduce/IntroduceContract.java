package com.app.sy.syan.introduce;

import com.app.sy.syan.base.BaseView;
import com.app.sy.syan.data.IntroduceInfo;

public class IntroduceContract {
    interface View extends BaseView {
        void bindData(IntroduceInfo goodsInfo);
    }

    interface Presenter {
        void getData();
    }
}
