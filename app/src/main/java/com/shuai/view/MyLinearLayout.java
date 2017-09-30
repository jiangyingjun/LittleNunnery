package com.shuai.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by jiangyingjun on 2017/6/22.
 */

public class MyLinearLayout extends LinearLayout {

    public MyLinearLayout(Context context) {
        this(context,null);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    int downx,downy,temp_length;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
//        return true;
    }
    private View fill_view;
    public void  setView(View view){
        this.fill_view = view;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:

                int x= (int) event.getX();
                int y= (int) event.getY();
                if (Math.abs(x-downx)>Math.abs(y-downy)&&x-downx<0){

                    LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) fill_view.getLayoutParams();
                    params.width = temp_length=Math.abs(x-downx);
                    Log.e("action-->", "ACTION_DOWN"+"      "+Math.abs(x-downx));
                    fill_view.setLayoutParams(params);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_DOWN:
                downx = (int) event.getX();
                downy = (int) event.getY();
                Log.e("action-->", "ACTION_MOVE");
                return true;
            case MotionEvent.ACTION_UP:
                ValueAnimator va= ValueAnimator.ofInt(temp_length,0);
                va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {

                        LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) fill_view.getLayoutParams();
                        params.width = (int) animation.getAnimatedValue();
                        fill_view.setLayoutParams(params);

                    }
                });
                va.setDuration(400);
                va.start();
                Log.e("action-->", "ACTION_UP");
                break;
        }
        return super.onTouchEvent(event);
    }




}
