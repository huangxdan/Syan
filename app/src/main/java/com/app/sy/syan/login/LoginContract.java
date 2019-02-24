package com.app.sy.syan.login;

import com.app.sy.syan.base.BasePresenter;
import com.app.sy.syan.base.BaseView;
import com.app.sy.syan.data.StaffInfo;

/**
 * date 2018/5/8
 * version
 * describe
 *
 * @author hxd
 */
public interface LoginContract {

    interface View extends BaseView {
        void showToast(String msg);
        void loginSuccess(StaffInfo staffInfo);
    }

    interface Presenter {
        void getData(String loginName, String loginPwd);
    }

}
