package com.shuai.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import java.util.jar.Attributes

/**
 * Created by jiangyingjun on 2017/6/23.
 */
class MyRecyclerView : RecyclerView{


    constructor(context: Context):super(context)

    constructor(context: Context,attributeSet: AttributeSet):super(context,attributeSet)


    constructor(context: Context,attributeSet: AttributeSet,defStyleAttr:Int):super(context,attributeSet,defStyleAttr)


    override fun fling(velocityX: Int, velocityY: Int): Boolean {


        return super.fling(velocityX, velocityY)
    }


}