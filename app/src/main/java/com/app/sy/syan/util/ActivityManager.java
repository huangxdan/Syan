package com.app.sy.syan.util;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class ActivityManager {

    /**
     * 保存在栈里的所有Activity
     */
    List<Activity> mActivities= new ArrayList<>();
    /**
     * 当前显示的Activity
     */
    private Activity mCurrentActivity = null;
    /**
     * 栈顶Activity
     */
    private Activity mLastActivity = null;

    private static ActivityManager sInstance;

    /**
     * 获取ActivityManager实例
     *
     * @return ActivityManager实例
     */
    public static ActivityManager instance() {
        if (sInstance == null) {
            sInstance = new ActivityManager();
        }
        return sInstance;
    }

    private ActivityManager() {

    }

    /**
     * 当Activity执行onCreate时调用 - 保存启动的Activity
     *
     * @param activity 执行onCreate的Activity
     */
    public void onCreate(Activity activity) {
        mActivities.add(activity);
    }

    /**
     * 当Activity执行onDestroy时调用 - 移除销毁的Activity
     *
     * @param activity 执行onDestroy时的Activity
     */
    public void onDestroy(Activity activity) {
        if (mLastActivity == activity) {
            mLastActivity = null;
        }

        mActivities.remove(activity);
    }

    /**
     * 关闭所有activity
     */
    public void finishActivities() {
        Log.e("TTT", "mActivities = " + mActivities.size());
        for (Activity activity : mActivities) {
            activity.finish();
        }
        mActivities.clear();
    }

    /**
     * 关闭到某个页面
     *
     * @param cls
     */
    public void closeToActivity(Class<?> cls) {
        while (!this.mActivities.isEmpty()) {
            Activity activity = this.lastActivity();
            if (activity.getClass() == cls) break;
            activity.finish();
            this.removeActivity(activity);
        }
    }

    private Activity lastActivity() {
        int i = this.mActivities.size() - 1;
        return this.mActivities.get(i);
    }

    public void removeActivity(Activity activity) {
        this.mActivities.remove(activity);
    }

    /**
     * 当Activity执行onResume时调用 - 保存当前显示的activity，更新栈顶Activity
     *
     * @param activity 执行onResume的Activity
     */
    public void onResume(Activity activity) {
        mCurrentActivity = activity;
    }

    /**
     * 当Activity执行onPause时调用 - 清除当前显示的Activity
     *
     * @param activity 执行onPause的Activity
     */
    public void onPause(Activity activity) {
        mCurrentActivity = null;
        mLastActivity = activity;
    }

    /**
     * 获取当前显示的Activity
     *
     * @return 当前显示的Activity，可能为空
     */
    public Activity getCurrentActivity() {
        return mCurrentActivity;
    }

    /**
     * 是否为当前的Activity
     *
     * @param activity activity
     * @return 是：true
     */
    public boolean isCurrentActivity(Activity activity) {
        return mCurrentActivity == activity;
    }

    /**
     * 获取栈顶的Activity
     *
     * @return 栈顶的Activity
     */
    public Activity getLastActivity() {
        return mLastActivity;
    }

    /**
     * 获取所有的Activities
     *
     * @return Activities
     */
    public List<Activity> getActivities() {
        return mActivities;
    }
}
