package com.app.sy.syan.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.app.sy.syan.R;


public class LoadingDialog extends Dialog {
    private Context mContext;

    public LoadingDialog(Context context) {
        super(context, R.style.dialogStyle);
        this.mContext = context;
        init();
    }


    private void init() {
        View view = View.inflate(mContext, R.layout.dialog_loading, null);

        setContentView(view);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        setCanceledOnTouchOutside(true);
    }

    public void showDialog() {
        if (!isShowing()) {
            show();
        }
    }

    public void closeDialog() {
        if (isShowing()) {
            cancel();
        }
    }
}
