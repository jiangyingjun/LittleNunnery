package com.shuai


import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.widget.LinearLayoutManager
import com.shuai.base.BaseActivity
import com.shuai.view.MyScrollView
import com.shuai.view.recyclerview.DividerGridItemDecoration
import com.shuai.view.recyclerview.MyLInearLayoutManager

import kotlinx.android.synthetic.main.activity_test.*
import java.util.*
import kotlin.collections.ArrayList

class TestActivity : BaseActivity(), MyScrollView.OnScrollListener {


    val mHanlder=object :Handler(){
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)

            when(msg!!.what){
                1->{
//                    if (a==13){
//
//                        return
//                    }
                mCanLoadMore=true
                adapter!!.mData=listData
                adapter!!.notifyDataSetChanged()
                if(a==12){
                    adapter!!.setFooterText("load over")
                }

                }
            }

        }
    };


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        initView()
    }


    var listData=ArrayList<String>()

    var adapter :RecyclerViewDataAdapter?=null
    private fun initView() {


//        img!!.setOnClickListener {
//            Thread(Runnable {
//                for (i in 0..Integer.MAX_VALUE - 1)
//                    Toast.makeText(SampleApplicationContext.context, "haha", Toast.LENGTH_SHORT).show()
//            }).start()
//        }

        scrollview.setOnScrollListener(this)

        var  layoutmananger=MyLInearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        layoutmananger.setVericallScrollEnable(false)
        recyclview.layoutManager = layoutmananger

        var divider= DividerGridItemDecoration(this)
         divider.setDivider(0,0,0,20)
        recyclview.addItemDecoration(divider)



        for (x in 0..4){

            listData.add(x.toString())
        }

        adapter=RecyclerViewDataAdapter(this,recyclview,listData)

        recyclview.adapter=adapter

    }



    override fun onScroll(scrollY: Int) {
    }
    var a=10

    var  mCanLoadMore=true
    override fun onBottomArrived() {
//        if (!mCanLoadMore){
//            return
//        }

        mCanLoadMore=false
        a+=1
        listData.add(a.toString())
        adapter!!.mData=listData
        adapter!!.notifyDataSetChanged()
        mHanlder.sendEmptyMessage(1)

    }

    override fun onScrollStateChanged(view: MyScrollView, scrollState: Int) {
    }


}
