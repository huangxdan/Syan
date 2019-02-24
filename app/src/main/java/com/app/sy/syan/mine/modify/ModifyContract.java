package com.app.sy.syan.mine.modify;

import com.app.sy.syan.base.BaseView;

/**
 * date 2018/5/8
 * version
 * describe
 *
 * @author hxd
 */
public interface ModifyContract {

    interface View extends BaseView {
        void modufySuccess();
    }

    interface Presenter {
        void modify(String oldPwd, String newPwd);
    }
}
