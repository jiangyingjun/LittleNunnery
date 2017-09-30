package com.shuai.utils

import android.support.v4.view.ViewCompat
import android.view.View

/**
 * Created by jiangyingjun on 2017/8/2.
 */
object ViewHelper{


    fun clearAnimation(v:View){

        ViewCompat.setAlpha(v,1f)
        ViewCompat.setScaleY(v, 1f)
        ViewCompat.setScaleX(v, 1f)
        ViewCompat.setTranslationY(v, 0f)
        ViewCompat.setTranslationX(v, 0f)
        ViewCompat.setRotation(v, 0f)
        ViewCompat.setRotationY(v, 0f)
        ViewCompat.setRotationX(v, 0f)
        ViewCompat.setPivotY(v, (v.getMeasuredHeight() / 2).toFloat())
        ViewCompat.setPivotX(v, (v.getMeasuredWidth() / 2).toFloat())
        ViewCompat.animate(v).setInterpolator(null).startDelay = 0



    }




}