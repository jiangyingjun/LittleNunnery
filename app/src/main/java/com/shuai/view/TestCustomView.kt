package com.shuai.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.TextView
import com.shuai.R

/**
 * Created by jiangyingjun on 2017/10/20.
 */
class TestCustomView: TextView{


    constructor(context: Context) : this(context, null!!)

    constructor(context: Context,attributeSet: AttributeSet):this(context,attributeSet,0)

    constructor(context: Context,attributeSet: AttributeSet,defStyleRes:Int) :super(context,attributeSet,defStyleRes){


       var typeArray= context.obtainStyledAttributes(attributeSet,R.styleable.testCustomView)

       var colorsssss=typeArray.getString(R.styleable.testCustomView_colorsss)

        Log.e("jyj-->",colorsssss)
       System.out.println(colorsssss+" jyj ")

    }


    override fun setText(text: CharSequence?, type: BufferType?) {
        super.setText(text, type)
    }


}