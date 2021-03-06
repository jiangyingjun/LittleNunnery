package com.shuai.view

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ScrollView

/**
 * Created by jiangyingjun on 2016/12/12.
 */

 class MyScrollView : ScrollView {

    private val TAG = javaClass.name
    private var mOnScrollListener: OnScrollListener? = null
    // 是否在触摸状态
    private var inTouch = false
    // 上次滑动的最后位置
    private var lastT = 0

    constructor(context: Context): this(context,null){}

    constructor(context: Context,attributeSet: AttributeSet?):this(context,attributeSet,0){}

    constructor(context: Context,attributeSet: AttributeSet?, defStyleAttr:Int):super(context,attributeSet,defStyleAttr){

    }

    /***
     * 处理手离开屏幕 scrollView 还在滚动的情况
     */
    private val handler = object : Handler() {
        override fun handleMessage(msg: android.os.Message) {
            if (lastT == scrollY) {
                // 如果上次的位置和当前的位置相同，可认为是在空闲状态
                //                Log.e(TAG, "SCROLL_STATE_IDLE");
                mOnScrollListener!!.onScrollStateChanged(this@MyScrollView,
                        SCROLL_STATE_IDLE)
                if (scrollY + height >= computeVerticalScrollRange()) {
                    mOnScrollListener!!.onBottomArrived()
                } else {
                    Log.d(TAG, "没有到最下方")
                }
            }
        }
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                inTouch = true
                if (scrollY + height >= computeVerticalScrollRange()) {
                    mOnScrollListener!!.onBottomArrived()
                }
            }
            MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
                inTouch = false

                lastT = scrollY
                handler.removeMessages(CHECK_STATE)// 确保只在最后一次做这个check
                handler.sendEmptyMessageDelayed(CHECK_STATE, 5)// 5毫秒检查一下
            }
        }
        return super.onTouchEvent(ev)
    }

    /**published scrolllistener interface */
    fun setOnScrollListener(OnScrollListener: OnScrollListener) {
        this.mOnScrollListener = OnScrollListener
    }

    /**
     * scroll listener callback
     */

    interface OnScrollListener {
        fun onScroll(scrollY: Int)


        /**
         * 滑动到底部回调
         */
        fun onBottomArrived()

        /**
         * 滑动状态回调

         * @param view
         * *            当前的scrollView
         * *
         * @param scrollState
         * *            当前的状态
         */
        fun onScrollStateChanged(view: MyScrollView,
                                 scrollState: Int)

    }


    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)

        if (mOnScrollListener == null) {
            return
        }

        if (inTouch) {
            if (t != oldt) {
                // 有手指触摸，并且位置有滚动
                Log.i(TAG, "SCROLL_STATE_TOUCH_SCROLL")
                mOnScrollListener!!.onScrollStateChanged(this,
                        SCROLL_STATE_TOUCH_SCROLL)
            }
        } else {
            if (t != oldt) {
                // 没有手指触摸，并且位置有滚动，就可以简单的认为是在fling
                Log.w(TAG, "SCROLL_STATE_FLING")
                mOnScrollListener!!.onScrollStateChanged(this,
                        SCROLL_STATE_FLING)
                // 记住上次滑动的最后位置
                lastT = t
                handler.removeMessages(CHECK_STATE)// 确保只在最后一次做这个check
                handler.sendEmptyMessageDelayed(CHECK_STATE, 5)// 5毫秒检查一下
            }
        }

        if (t >= 0) {
            mOnScrollListener!!.onScroll(t)
        }

    }


    override fun onViewRemoved(child: View) {
        super.onViewRemoved(child)

    }

    companion object {


        // 检查ScrollView的最终状态
        private val CHECK_STATE = 0
        /**
         * The view is not scrolling. Note navigating the list using the
         * trackball counts as being in the idle state since these transitions
         * are not animated.
         */
        var SCROLL_STATE_IDLE = 0

        /**
         * The user is scrolling using touch, and their finger is still on the
         * screen
         */
        var SCROLL_STATE_TOUCH_SCROLL = 1

        /**
         * The user had previously been scrolling using touch and had performed
         * a fling. The animation is now coasting to a stop
         */
        var SCROLL_STATE_FLING = 2
    }
}
