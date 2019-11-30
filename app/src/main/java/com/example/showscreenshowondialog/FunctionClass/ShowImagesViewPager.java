package com.example.showscreenshowondialog.FunctionClass;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

/**
 * Create by $冯日天 on 2019/11/29
 */
public class ShowImagesViewPager extends ViewPager {

    public ShowImagesViewPager(Context context) {
        this(context,null);
    }

    public ShowImagesViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException e) {
            //uncomment if you really want to see these errors
            //e.printStackTrace();
            return false;
        }
    }

}
