package com.shuai

import android.content.Context

import com.shuai.utils.glide.GlideImageLoader

/**
 * Created by jiangyingjun on 2017/9/25.
 */

class Test {


    private var loader: GlideImageLoader? = null
    internal fun funs(context: Context) {
        loader = GlideImageLoader(context)
    }
}
