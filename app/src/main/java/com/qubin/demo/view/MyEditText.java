package com.qubin.demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

import com.qubin.demo.R;

/**
 * author：created by qub
 * date：2020/6/9 14
 * descri：
 */
public class MyEditText extends AppCompatEditText {

    private Paint mPaint = null;
    private int lineHeight;
    private int height;
    private int width;
    private int paddingLeft;
    private int paddingRight;
    private float textSize;
    private float lines;

    public MyEditText(Context context) {
        super(context);
        init();
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5);
        mPaint.setColor(getResources().getColor(R.color.colorAccent));
        lineHeight = 2;

        //获取控件高度
        height = this.getHeight();
        width = this.getWidth();
        paddingLeft = this.getPaddingLeft();
        paddingRight = this.getPaddingRight();

        textSize = this.getTextSize();
        //总共行数
        lines = height / textSize;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mode = MeasureSpec.getMode(widthMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < 5; i++) {

            canvas.drawLine(0,
                    10 * i,
                    50,
                    10 * i,
                    mPaint);

        }
    }
}
