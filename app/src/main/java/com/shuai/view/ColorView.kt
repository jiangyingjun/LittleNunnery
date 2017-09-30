package com.shuai.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.ImageView
import com.shuai.R

/**
 * Created by jiangyingjun on 2017/6/28.
 */
class ColorView : ImageView {


    private  var mBitmap:Bitmap?=null

    private  var mColor:Int ?=null

    private  var mPaint :Paint ?=null

    private  var colorMatrix:ColorMatrix?=null
    /*
    * red
    * green
    *
    * blue
    *
    * alpha
    *
    * */
    private val colorArray = floatArrayOf( 0.33f,0.59f,0.11f,0f, 0f,
                                            0.33f,0.59f,0.11f,0f, 0f,
                                            0.33f,0.59f,0.11f,0f, 0f,
                                            0f, 0f, 0f, 1f, 0f)

    constructor(context:Context):this(context, null!!)


    constructor(context:Context,attributeSet: AttributeSet):super(context,attributeSet){
        mBitmap=BitmapFactory.decodeResource(resources, R.drawable.a2)
        mPaint= Paint()
        colorMatrix= ColorMatrix()

    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas!!.drawBitmap(mBitmap,0f,0f,mPaint)
        colorMatrix!!.set(colorArray)
        mPaint!!.setColorFilter(ColorMatrixColorFilter(colorMatrix))

        canvas!!.drawBitmap(mBitmap,0f,0f,mPaint)
    }


    fun setColorImage(bitmap: Bitmap){
        this.mBitmap=bitmap
    }

    fun setBitmapColor(color :Int){
        this.mColor=color
    }
}