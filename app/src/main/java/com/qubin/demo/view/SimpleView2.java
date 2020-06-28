package com.qubin.demo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.qubin.demo.R;

/**
 * author：created by qub
 * date：2020/4/24 16
 * descri：
 */
public class SimpleView2 extends View {
    //画笔
    private Paint mPaint;

    private int progress;//进度
    private int bgcolor;//背景颜色
    private int procolor;//进度颜色
    private int mWidth;
    private int mHeight;

    public SimpleView2 (Context context) {
        this(context,null);
    }

    public SimpleView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
        //初始化画笔
        mPaint=new Paint();

    }

    private void initAttrs(AttributeSet attrs){
        if(attrs==null){
            return;
        }
        mWidth = 400;
        mHeight = 30;
        //获得所有的属性
        TypedArray array=getContext().obtainStyledAttributes(attrs,R.styleable.SimpleView2);
        progress=array.getInteger(R.styleable.SimpleView2_progress,0);
        bgcolor=array.getColor(R.styleable.SimpleView2_bgcolor,Color.GRAY);
        procolor=array.getColor(R.styleable.SimpleView2_procolor,Color.CYAN);
        array.recycle();//回收TypedArray
    }

    @Override
    protected void onMeasure(int widthMeasureSpec,int heightMeasureSpec){
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int temp = measureSize(widthMode, width);
        mWidth = temp == 0 ? mHeight : temp;
        temp = measureSize(heightMode,height);
        mHeight = temp == 0 ? mHeight : temp;
        setMeasuredDimension(mWidth,mHeight);


    }

    private int measureSize(int mode,int defsize){
        int size = 0;
        switch (mode){
            case MeasureSpec.UNSPECIFIED:
                case MeasureSpec.AT_MOST:
                    break;

                    case MeasureSpec.EXACTLY:
                        size = defsize;
                        break;
        }

        return size;
    }

    @Override
    protected void onDraw(Canvas canvas){
        mPaint.setColor(bgcolor);//设置进度条背景颜色
        RectF rectF = new RectF();//背景的矩形
        rectF.set(3,3,mWidth - 3,mHeight - 3);
        canvas.drawRoundRect(rectF,3,3,mPaint);
        mPaint.setColor(procolor);
        RectF proRect=new RectF();//进度的矩形
        proRect.set(3,3,mWidth * ((float) progress / 100),mHeight - 3);
        canvas.drawRoundRect(proRect,3,3,mPaint);
    }

    public void setProgress(int progress){
        if(progress < 0 || progress > 100){
            return;
        }
        this.progress=progress;
        //重绘组件
        postInvalidate();
    }




}
