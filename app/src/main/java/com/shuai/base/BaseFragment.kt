package com.shuai.base

import android.support.v4.app.Fragment

/**
 * Created by jiangyingjun on 2017/8/4.
 */
 open class BaseFragment :Fragment(){


    override fun onDestroy() {
        super.onDestroy()

        /*
        *@time 2017-08-04
        *检测内存泄漏*/
//       var refWatcher= BaseApplication.getRefWatcher(SampleApplicationContext.application)
//        refWatcher.watch(this)

    }


}