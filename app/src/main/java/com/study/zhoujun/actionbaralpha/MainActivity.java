package com.study.zhoujun.actionbaralpha;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private myScrollView mScrollView;
    private ImageView imgview;
    private Drawable bgDrawable;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SystemBarUtils.init(this);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //设置toolbar高度和内边距
        SystemBarUtils.setActionBarPaddingHeight(this,toolbar);
        setSupportActionBar(toolbar);

        bgDrawable = getResources().getDrawable(android.R.color.holo_green_dark);

        imgview = (ImageView) findViewById(R.id.imgview);
        mScrollView = (myScrollView) findViewById(R.id.scrollView);
        mScrollView.setScrollListener(new IScrollViewListener() {
            @Override
            public void onScroll(int l, int t, int oldl, int oldt) {
                //滑动时动态改变ACtionbar透明度
                int actionBarHeight = imgview.getHeight();
                // Log.e("123","actionBarHeight:"+actionBarHeight);
                // Log.e("123","actionBarHeight:"+actionBarHeight);
                //获取比例
                if (mScrollView.getScrollY() <= actionBarHeight) {
                    float scall = (float) (mScrollView.getScrollY() * 1.0 / actionBarHeight);

                    //设置文字渐变
                    SpannableString string = new SpannableString(toolbar.getTitle());

//                    int colorValue = (int) (255 * (1 - scall));
                    int alphaValue = (int) (255 * scall);
//                    if (mScrollView.getScrollY() <= actionBarHeight / 2) {
//                        string.setSpan(new ForegroundColorSpan(Color.argb(colorValue, colorValue, colorValue, colorValue)), 0,
//                                toolbar.getTitle().length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//                    } else {
//                        string.setSpan(new ForegroundColorSpan(Color.argb(alphaValue, colorValue, colorValue, colorValue)), 0,
//                                toolbar.getTitle().length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//                    }
                    string.setSpan(new ForegroundColorSpan(Color.argb(255, 255, 255, 255)), 0,
                            toolbar.getTitle().length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                    toolbar.setTitle(string);
                    //设置actionBar背景渐变
                    bgDrawable.setAlpha(alphaValue);
                    toolbar.setBackgroundDrawable(bgDrawable);
                }
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mClient = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initStatuebar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    private int getAppBarHeight() {
        return dip2px(56) + getStatusBarHeight();
    }

    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");

        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }

        return result;
    }

    private int dip2px(float dipValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
