package com.app.sy.syan.note;

import com.app.sy.syan.base.BaseView;

public class NoteContract {

    interface View extends BaseView {
        void noteSuccess();
    }

    interface Presenter {
        void getDate(String orderInfo);
    }
}
