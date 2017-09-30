package com.shuai.view.recyclerview

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup



/**
 * Created by jiangyingjun on 2017/7/26.
 */
class MyLInearLayoutManager : LinearLayoutManager {


    var mIsVerticallCanScroll=false

    private val mMeasuredDimension = IntArray(2)

    constructor(context: Context):super(context){}

    constructor(context: Context,orientation: Int,reverseLayout:Boolean):super(context,orientation,reverseLayout){}


    fun  setVericallScrollEnable(verticallCanScroll:Boolean){

        this.mIsVerticallCanScroll=verticallCanScroll

    }

    override fun canScrollVertically(): Boolean {
        return mIsVerticallCanScroll&&super.canScrollVertically()
    }


    override fun onMeasure(recycler: RecyclerView.Recycler?, state: RecyclerView.State?, widthSpec: Int, heightSpec: Int) {
        var widthMode = View.MeasureSpec.getMode(widthSpec)
        var heightMode = View.MeasureSpec.getMode(heightSpec)
        var heightSize = View.MeasureSpec.getSize(heightSpec)
        var width = 0
        var height = 0
        for ( i  in 0..  getItemCount()) {
            try {
                measureScrapChild(recycler!!, i, widthSpec, View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED), mMeasuredDimension)
            } catch (e:IndexOutOfBoundsException ) {
                e.printStackTrace()
            }
            if (getOrientation() == HORIZONTAL) {
                width = width + mMeasuredDimension[0];
                if (i == 0) {
                    height = mMeasuredDimension[1];
                }
            } else {
                //【每个item的高度都要加上分割线高度】
                height = height + mMeasuredDimension[1]
                if (i == 0) {
                    width = mMeasuredDimension[0]
                }
            }
        }
        when (widthMode) {
             View.MeasureSpec.EXACTLY->{}

             View.MeasureSpec.AT_MOST->{}

             View.MeasureSpec.UNSPECIFIED->{}
        }
        when (heightMode) {
             View.MeasureSpec.EXACTLY->{

                 height = heightSize
             }
             View.MeasureSpec.AT_MOST->{}

             View.MeasureSpec.UNSPECIFIED->{}
        }
        setMeasuredDimension(widthSpec,heightSpec)
//        super.onMeasure(recycler, state, widthSpec, heightSpec)
    }


    private fun measureScrapChild(recycler: RecyclerView.Recycler, position: Int, widthSpec: Int, heightSpec: Int, measuredDimension: IntArray) {
        val view = recycler.getViewForPosition(position)
        if (view != null) {
            val p = view.layoutParams as RecyclerView.LayoutParams
            val childHeightSpec = ViewGroup.getChildMeasureSpec(heightSpec, paddingTop + paddingBottom, p.height)
            view.measure(widthSpec, childHeightSpec)
            measuredDimension[0] = view.measuredWidth + p.leftMargin + p.rightMargin
            measuredDimension[1] = view.measuredHeight + p.bottomMargin + p.topMargin
            recycler.recycleView(view)
        }
    }

}