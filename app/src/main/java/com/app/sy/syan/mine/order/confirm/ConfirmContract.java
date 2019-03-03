package com.app.sy.syan.mine.order.confirm;

import com.app.sy.syan.base.BaseView;
import com.app.sy.syan.data.GoodsInfo;
import com.app.sy.syan.data.StaffInfo;

import java.util.List;

public interface ConfirmContract {

    interface View extends BaseView {
        void bindData(StaffInfo staffInfo);

        void confirmSuccess();
    }

    interface Presenter {
        void getData();

        void addMyOrder(List<GoodsInfo> list, String totalMoney);
    }
}
