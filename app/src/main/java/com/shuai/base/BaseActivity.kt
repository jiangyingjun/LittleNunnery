package com.shuai.base

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.jude.beam.bijection.Presenter
import com.jude.beam.expansion.BeamBaseActivity

/**
 * open  重写了父类的protected类型的方法 并且在自类型中没有显示引用
 * internal  能见到类声明的 本模块内 的任何客户端都可见其 internal 成员
 *
 */
open class BaseActivity<T: Presenter<*>> : BeamBaseActivity<T>(){

    companion object{
        val TAG="jyj-->"
    }


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }


    override fun onStart() {
        super.onStart()
    }


    override fun onResume() {
        super.onResume()
    }


    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }




}