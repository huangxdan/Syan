package com.app.sy.syan.view;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.sy.syan.R;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import rx.functions.Action1;


public class NavigationBar {
    public interface NavigationBarInteface {

        void onNavigationLeftItemClick();

        void onNavigationRightItemClick();

        void onNavigationCenterItemClick();
    }


    public interface NavigationItemStyle {
        final int ITEM_ICON_HIDDEN = 0;
        final int ITEM_ICON_ONLY = 1;
        final int ITEM_TITLE_ONLY = 2;
        final int ITEM_ICON_AND_TITLE = 3;
    }


    private Activity activity;
    private View view;

    private LinearLayout llNaviRoot;
    private View leftItem;
    private ImageView leftItemIcon;
    private TextView leftItemTitle;


    private View rightItem;
    private ImageView rightItemIcon;
    private TextView rightItemTitle;

    private View centerItem;
    private ImageView centerItemIcon;
    private TextView centerItemTitle;

    private NavigationBarInteface mNavigationBarInteface;

    /**
     * @param activity
     * @param navigationBarInteface
     * @deprecated suggest to user (view, hfNavigationBarInteface)
     */
    public NavigationBar(Activity activity, NavigationBarInteface navigationBarInteface) {
        this.activity = activity;
        this.mNavigationBarInteface = navigationBarInteface;
        initView();
        bindListener();
    }

    public NavigationBar(View view, NavigationBarInteface navigationBarInteface) {
        this.view = view;
        this.mNavigationBarInteface = navigationBarInteface;
        initView();
        bindListener();
    }

    private void initView() {
        if (activity != null) {
            llNaviRoot = (LinearLayout) activity.findViewById(R.id.ll_navi_root);
            leftItem = activity.findViewById(R.id.pub_navi_left_item);
            leftItemIcon = (ImageView) activity.findViewById(R.id.pub_navi_left_item_icon);
            leftItemTitle = (TextView) activity.findViewById(R.id.pub_navi_left_item_title);
            rightItem = activity.findViewById(R.id.pub_navi_right_item);
            rightItemIcon = (ImageView) activity.findViewById(R.id.pub_navi_right_item_icon);
            rightItemTitle = (TextView) activity.findViewById(R.id.pub_navi_right_item_title);
            centerItem = activity.findViewById(R.id.pub_navi_center_item);
            centerItemIcon = (ImageView) activity.findViewById(R.id.pub_navi_center_item_icon);
            centerItemTitle = (TextView) activity.findViewById(R.id.pub_navi_center_item_title);
        } else if (view != null) {
            llNaviRoot = (LinearLayout) view.findViewById(R.id.ll_navi_root);
            leftItem = view.findViewById(R.id.pub_navi_left_item);
            leftItemIcon = (ImageView) view.findViewById(R.id.pub_navi_left_item_icon);
            leftItemTitle = (TextView) view.findViewById(R.id.pub_navi_left_item_title);
            rightItem = view.findViewById(R.id.pub_navi_right_item);
            rightItemIcon = (ImageView) view.findViewById(R.id.pub_navi_right_item_icon);
            rightItemTitle = (TextView) view.findViewById(R.id.pub_navi_right_item_title);
            centerItem = view.findViewById(R.id.pub_navi_center_item);
            centerItemIcon = (ImageView) view.findViewById(R.id.pub_navi_center_item_icon);
            centerItemTitle = (TextView) view.findViewById(R.id.pub_navi_center_item_title);
        }

    }

    private void bindListener() {
        RxView.clicks(leftItem).
                throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        if (mNavigationBarInteface != null) {
                            mNavigationBarInteface.onNavigationLeftItemClick();
                        }
                    }
                });
        RxView.clicks(centerItem).
                throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        if (mNavigationBarInteface != null) {
                            mNavigationBarInteface.onNavigationCenterItemClick();
                        }
                    }
                });
        RxView.clicks(rightItem).
                throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        if (mNavigationBarInteface != null) {
                            mNavigationBarInteface.onNavigationRightItemClick();
                        }
                    }
                });
    }

    public void setLeftItemHidden() {
        leftItem.setVisibility(View.INVISIBLE);
    }

    public void setLeftItemShow() {
        leftItem.setVisibility(View.VISIBLE);
    }

    public void setCenterItemHidden() {
        centerItem.setVisibility(View.INVISIBLE);
    }

    public void setCenterItemShow() {
        centerItem.setVisibility(View.VISIBLE);
    }

    public void setRightItemHidden() {
        rightItem.setVisibility(View.INVISIBLE);
    }

    public void setRightItemShow() {
        rightItem.setVisibility(View.VISIBLE);
    }

    public void setLeftItemIconHidden() {
        leftItemIcon.setVisibility(View.GONE);
    }

    public void setLeftItemTitleHidden() {
        leftItemTitle.setVisibility(View.GONE);
    }

    public void setLeftItemIconShow() {
        leftItemIcon.setVisibility(View.VISIBLE);
    }

    public void setLeftItemTitleShow() {
        leftItemTitle.setVisibility(View.VISIBLE);
    }

    public void setCenterItemTitleHidden() {
        centerItemTitle.setVisibility(View.GONE);
    }

    public void setCenterItemTitleShow() {
        centerItemTitle.setVisibility(View.VISIBLE);
    }

    public void setCenterItemIconHidden() {
        centerItemIcon.setVisibility(View.GONE);
    }

    public void setCenterItemIconShow() {
        centerItemIcon.setVisibility(View.VISIBLE);
    }

    public void setRightItemIconShow() {
        rightItemIcon.setVisibility(View.VISIBLE);
    }

    public void setRightItemIconHidden() {
        rightItemIcon.setVisibility(View.GONE);
    }

    public void setRightItemTitleHidden() {
        rightItemTitle.setVisibility(View.GONE);
    }

    public void setRightItemTitleShow() {
        rightItemTitle.setVisibility(View.VISIBLE);
    }

    public void setLeftTextSize(int size){
        leftItemTitle.setTextSize(size);
    }

    public void setCenterTextSize(int size){
        centerItemTitle.setTextSize(size);
    }

    public void setRightTextSize(int size){
        rightItemTitle.setTextSize(size);
    }

    public void setLeftIcon(String url) {

    }

    public void setNaviRootBgColor(int color) {
        llNaviRoot.setBackgroundResource(color);
    }

    public void setLeftIcon(int resId) {
        leftItemIcon.setImageResource(resId);
    }

    public void setLeftTitle(String title) {
        leftItemTitle.setText(title);
    }

    public void setLeftTitle(int resId) {
        leftItemTitle.setText(resId);
    }

    public void setRightIcon(String url) {

    }

    public void setRightIcon(int resId) {
        rightItemIcon.setImageResource(resId);
    }

    public void setRightTitle(String title) {
        rightItemTitle.setText(title);
    }

    public void setRightTitle(int resId) {
        rightItemTitle.setText(resId);
    }

    public void setCenterIcon(String url) {
    }

    public void setCenterIcon(int resId) {
        centerItemIcon.setImageResource(resId);
    }

    public void setCenterTitle(String title) {
        centerItemTitle.setText(title);
    }

    public void setCenterTitle(int resId) {
        centerItemTitle.setText(resId);
    }
}