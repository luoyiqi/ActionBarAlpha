package com.study.zhoujun.actionbaralpha;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by zhoujun on 2016/5/10.
 */
public class myScrollView extends ScrollView {

    private IScrollViewListener mIScrollViewListener;

    public myScrollView(Context context) {
        super(context);
    }

    public myScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public myScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * This is called in response to an internal scroll in this view (i.e., the
     * view scrolled its own contents). This is typically as a result of
     * {@link #scrollBy(int, int)} or {@link #scrollTo(int, int)} having been
     * called.
     * l,t代表left,top，也就是触摸点相对左上角的偏移量。而oldl,oldt就是滑动前的偏移量。
     *
     * @param l    Current horizontal scroll origin.
     * @param t    Current vertical scroll origin.
     * @param oldl Previous horizontal scroll origin.
     * @param oldt Previous vertical scroll origin.
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mIScrollViewListener != null) {
            mIScrollViewListener.onScroll(l, t, oldl, oldt);
        }
    }

    public void setScrollListener(IScrollViewListener iScrollViewListener) {
        this.mIScrollViewListener = iScrollViewListener;
    }

}
