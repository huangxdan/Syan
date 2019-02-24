package com.app.sy.syan.view;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

import com.app.sy.syan.util.L;


public class MyScrollView extends ScrollView {
    Context mContext;
    private View mView;
    private float touchY;
    private int scrollY = 0;
    private boolean handleStop = false;
    private int eachStep = 0;
    /**
     * 最大滑动距离
     */
    private static final int MAX_SCROLL_HEIGHT = 200;
    /**
     * 阻尼系数,越小阻力就越大
     */
    private static final float SCROLL_RATIO = 0.3f;

    public MyScrollView(Context context) {
        super(context);
        this.mContext = context;
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
    }

    @Override
    protected void onFinishInflate() {
        if (getChildCount() > 0) {
            this.mView = getChildAt(0);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (arg0.getAction() == MotionEvent.ACTION_DOWN) {
            touchY = arg0.getY();
        }
        return super.onInterceptTouchEvent(arg0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mView == null) {
            return super.onTouchEvent(ev);
        } else {
            commonOnTouchEvent(ev);
        }
        return super.onTouchEvent(ev);
    }

    private void commonOnTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_UP:
                if (mView.getScrollY() != 0) {
                    handleStop = true;
                    animation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                float nowY = ev.getY();
                L.d("MyScrollView", "nowY===" + nowY);
                int deltaY = (int) (touchY - nowY);
                L.d("MyScrollView", "deltaY===" + deltaY);
                touchY = nowY;
                L.d("MyScrollView", "touchY===" + touchY);
                //deltaY大于0上拉
                if (isNeedMove() && deltaY > 0) {
                    int offset = mView.getScrollY();
                    L.d("MyScrollView", "offset===" + offset);
                    if (offset < MAX_SCROLL_HEIGHT && offset > -MAX_SCROLL_HEIGHT) {
                        mView.scrollBy(0, (int) (deltaY * SCROLL_RATIO));
                        handleStop = false;
                    }
                }

                break;
            default:
                break;
        }
    }

    private boolean isNeedMove() {
        int viewHeight = mView.getMeasuredHeight();
        int scrollHeight = getHeight();
        int offset = viewHeight - scrollHeight;
        int scrollY = getScrollY();
        if (scrollY == 0 || scrollY == offset) {
            return true;
        }
        return false;
    }

    private void animation() {
        scrollY = mView.getScrollY();
        eachStep = scrollY / 10;
        resetPositionHandler.sendEmptyMessage(0);
    }

    Handler resetPositionHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            if (scrollY != 0 && handleStop) {
                scrollY -= eachStep;
                boolean genuine = (eachStep < 0 && scrollY > 0) || (eachStep > 0 && scrollY < 0);
                if (genuine) {
                    scrollY = 0;
                }
                mView.scrollTo(0, scrollY);
                this.sendEmptyMessageDelayed(0, 5);
            }
        }
    };

}




