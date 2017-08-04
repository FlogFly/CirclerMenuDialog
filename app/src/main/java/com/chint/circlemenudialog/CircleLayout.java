package com.chint.circlemenudialog;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Project:RecycleWheelView
 * Author:dyping
 * Date:2017/8/3 14:59
 */

public class CircleLayout extends ViewGroup {

    private float mAngleOffset;
    private float mAngleRange;

    private int mInnerRadius;

    public CircleLayout(Context context) {
        super(context);
    }

    public CircleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CircleLayout, 0, 0);

        try {
            mAngleOffset = a.getFloat(R.styleable.CircleLayout_angleOffset, -90f);
            mAngleRange = a.getFloat(R.styleable.CircleLayout_angleRange, 360f);
            mInnerRadius = a.getDimensionPixelSize(R.styleable.CircleLayout_innerRadius, 80);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            a.recycle();
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        final int count = getChildCount();

        int maxHeight = 0;
        int maxWidth = 0;


        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                measureChild(child, widthMeasureSpec, heightMeasureSpec);
                maxWidth = Math.max(maxWidth, child.getMeasuredWidth());
                maxHeight = Math.max(maxHeight, child.getMeasuredHeight());
            }
        }

        maxHeight = Math.max(maxHeight, getSuggestedMinimumHeight());
        maxWidth = Math.max(maxWidth, getSuggestedMinimumWidth());

        int width = resolveSize(maxWidth, widthMeasureSpec);
        int height = resolveSize(maxHeight, heightMeasureSpec);

        if(MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.AT_MOST && MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST){
            setMeasuredDimension(1000, 1000);
        }else if(MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.AT_MOST){
            setMeasuredDimension(1000, height);
        }else if(MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST){
            setMeasuredDimension(width, 1000);
        }else{
            setMeasuredDimension(width, height);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int childs = getChildCount();

        final int width = getWidth();
        final int height = getHeight();

        final float minDimen = width > height ? height : width;
        final float radius = (minDimen - mInnerRadius) / 2f;

        float startAngle = mAngleOffset;

        for (int i = 0; i < childs; i++) {
            final View child = getChildAt(i);

            final LayoutParams lp = child.getLayoutParams();

            final float angle = mAngleRange / childs;

            final float centerAngle = startAngle + angle / 2f;
            int x;
            int y;

            if (childs > 1) {
                x = (int) (radius * Math.cos(Math.toRadians(centerAngle))) + width / 2;
                y = (int) (radius * Math.sin(Math.toRadians(centerAngle))) + height / 2;
            } else {
                x = width / 2;
                y = height / 2;
            }

            final int halfChildWidth = child.getMeasuredWidth() / 2;
            final int halfChildHeight = child.getMeasuredHeight() / 2;

            final int left = lp.width != LayoutParams.MATCH_PARENT ? x - halfChildWidth : 0;
            final int top = lp.height != LayoutParams.MATCH_PARENT ? y - halfChildHeight : 0;
            final int right = lp.width != LayoutParams.MATCH_PARENT ? x + halfChildWidth : width;
            final int bottom = lp.height != LayoutParams.MATCH_PARENT ? y + halfChildHeight : height;

            child.layout(left, top, right, bottom);
            startAngle += angle;
        }
        invalidate();
    }
}
