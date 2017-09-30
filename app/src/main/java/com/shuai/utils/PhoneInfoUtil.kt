package com.shuai.utils

import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.text.TextUtils
import android.util.DisplayMetrics

/**
 * Created by jiangyingjun on 2017/3/22.
 */

class PhoneInfoUtil {


    companion object {

        var mDisplayWidth: Int = 0
        var mDisplayHeight: Int = 0

        val instance: PhoneInfoUtil
            get() {

                return PhoneInfoUtil()
            }
    }



    /*获取手机的时间制 12 或者 24  获取失败默认返回 24*/
    fun getTimeSystem(context: Context): Int {
        val cv = context.contentResolver
        val strTimeFormat = android.provider.Settings.System.getString(cv,
                android.provider.Settings.System.TIME_12_24)
        if (!TextUtils.isEmpty(strTimeFormat)) {
            return Integer.parseInt(strTimeFormat)
        } else {
            return 24
        }

    }


    /**
     * 获得状态栏的高度

     * @param context
     * *
     * @return
     */
    fun getStatusHeight(context: Context): Int {

        var statusHeight = -1
        try {
            val clazz = Class.forName("com.android.internal.R\$dimen")
            val `object` = clazz.newInstance()
            val height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(`object`).toString())
            statusHeight = context.resources.getDimensionPixelSize(height)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return statusHeight
    }

    fun getDisplayMetrics(context: Context): DisplayMetrics {
        val metric = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay
                .getMetrics(metric)
        return metric
    }

    /**
     * 获取屏幕宽度

     * @param context
     * *
     * @return
     */
    fun getDisplayWidth(context: Context): Int {
        if (mDisplayWidth == 0) {
            val metric = getDisplayMetrics(context)
            mDisplayWidth = metric.widthPixels
        }
        return mDisplayWidth
    }

    /**
     * 获取屏幕高度

     * @param context
     * *
     * @return
     */
    fun getDisplayHeight(context: Context): Int {
        if (mDisplayHeight == 0) {
            val metric = getDisplayMetrics(context)
            mDisplayHeight = metric.heightPixels
        }
        return mDisplayHeight
    }


}
