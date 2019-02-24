package com.app.sy.syan.welfare.extract;

import com.app.sy.syan.base.BaseView;
import com.app.sy.syan.data.WelfareInfo;

/**
 * date 2018/5/8
 * version
 * describe
 *
 * @author hxd
 */
public class ExtractContract {

    interface View extends BaseView {
        void bindDate(WelfareInfo welfareInfo);
    }

    interface Presenter {
        void getData(String staffNumber);
    }
}
