package com.study.zhoujun.actionbaralpha;

import android.app.Activity;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

/**
 * 使用时在需要顶部的布局中设置android:fitsSystemWindows="true"
 * Created by zhoujun on 2016/5/11.
 */
public class SystemBarUtils {
    /**
     * 设置状态栏透明
     * @param mActivity
     */
    public static void init(Activity mActivity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            mActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * 设置toolBar的PaddingHeight
     * @param mActivity
     * @param toolbar 需要设置的Toolbar
     */
    public static void setActionBarPaddingHeight(Activity mActivity, Toolbar toolbar) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            toolbar.getLayoutParams().height = getAppBarHeight(mActivity);
            toolbar.setPadding(toolbar.getPaddingLeft(), getAppBarHeight(mActivity), toolbar.getPaddingRight(), toolbar.getPaddingBottom());
        }
    }
    /**
     * 设置toolBar的PaddingHeight
     * @param mActivity
     * @param view 需要设置的View
     */
    public static void setActionBarPaddingHeight(Activity mActivity, View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            view.getLayoutParams().height = getAppBarHeight(mActivity);
            view.setPadding(view.getPaddingLeft(), getAppBarHeight(mActivity), view.getPaddingRight(), view.getPaddingBottom());
        }
    }

    private static int getAppBarHeight(Activity mActivity) {
        return dip2px(mActivity, 56) + getStatusBarHeight(mActivity);
    }

    private static int getStatusBarHeight(Activity mActivity) {
        int result = 0;
        int resourceId = mActivity.getResources().getIdentifier("status_bar_height", "dimen", "android");

        if (resourceId > 0) {
            result = mActivity.getResources().getDimensionPixelSize(resourceId);
        }

        return result;
    }

    private static int dip2px(Activity mActivity, float dipValue) {
        final float scale = mActivity.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
