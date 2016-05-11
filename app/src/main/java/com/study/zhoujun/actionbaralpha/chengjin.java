package com.study.zhoujun.actionbaralpha;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jaeger.library.StatusBarUtil;

/**
 * Created by zhoujun on 2016/5/10.
 */
public class chengjin extends AppCompatActivity{
    private LinearLayout mLinearLayout;
    private myScrollView mScrollView;
    private Drawable bgDrawable;
    private ImageView imgview;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        SystemBarUtils.init(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);
        StatusBarUtil
        mLinearLayout = (LinearLayout) findViewById(R.id.mLinearLayout);
        SystemBarUtils.setActionBarPaddingHeight(this,mLinearLayout);

        bgDrawable = getResources().getDrawable(android.R.color.holo_green_dark);

        imgview = (ImageView) findViewById(R.id.imgview);
        mScrollView = (myScrollView) findViewById(R.id.scrollView);
        mScrollView.setScrollListener(new IScrollViewListener() {
            @Override
            public void onScroll(int l, int t, int oldl, int oldt) {
                //滑动时动态改变ACtionbar透明度
                int actionBarHeight = imgview.getHeight();
                //获取比例
                if (mScrollView.getScrollY() <= actionBarHeight) {
                    float scall = (float) (mScrollView.getScrollY() * 1.0 / actionBarHeight);

                    int alphaValue = (int) (255 * scall);

                    //设置actionBar背景渐变
                    bgDrawable.setAlpha(alphaValue);
                    mLinearLayout.setBackgroundDrawable(bgDrawable);
                }
            }
        });
    }
}
