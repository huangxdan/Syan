package com.app.sy.syan.welfare.character;

import com.app.sy.syan.base.BaseView;
import com.app.sy.syan.data.CharacterInfo;

public interface CharacterContract {

    interface View extends BaseView {
        void bindData(CharacterInfo characterInfo);
    }

    interface Presenter {
        void getData(String staffNumber);
    }
}
