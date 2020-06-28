package com.qubin.demo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.qubin.demo.R;

/**
 * author：created by qub
 * date：2020/6/8 15
 * descri：
 */
public class MyImageView extends View {
    Paint mPaint;
    private Drawable drawable;

    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        initAttrs(attrs);

    }

    private void initAttrs(AttributeSet attrs){
        if (attrs != null){
            TypedArray typedArray = null;
            try {
                typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.MyImageView);
                drawable = typedArray.getDrawable(R.styleable.MyImageView_src);
            }finally {
                if (typedArray != null){
                    typedArray.recycle();
                }
            }
        }
    }

    private void measureDrawable(){
        if (drawable == null){
            throw new RuntimeException("drawable 不能为空");
        }

        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();

    }
}
