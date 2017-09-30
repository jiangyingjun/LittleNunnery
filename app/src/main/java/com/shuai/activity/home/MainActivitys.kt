package com.shuai.activity.home

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Toast
import com.shuai.R
import com.shuai.adapter.main.MainActivitysRecyclerViewAdapter
import com.shuai.base.BaseActivity
import kotlinx.android.synthetic.main.activity_mains.*

/**
 * Created by jiangyingjun on 2017/9/25.
 */
class MainActivitys :BaseActivity(){


    var mHandler=object:Handler(){
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)

//            Log.e("message-->",msg!!.what.toString())
//            mains_btn.text="handlemessage"

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mains)


        initView()


//        mHandler.post(Runnable {
//            kotlin.run {
//                mains_btn.text="postrunable"
//            }
//        })

        mHandler.sendEmptyMessage(0)


        mains_btn.setOnClickListener {

            Thread(Runnable { kotlin.run {

                mHandler.post(Runnable {
                    kotlin.run {
                        mains_btn.text="postrunable"
                    }
                })



            } }).start()
        }
    }

    private fun initView() {

        var layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)
//        var gridLayoutMangager=GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false)
        mains_recyclerview.layoutManager=layoutManager
        var  testData= ArrayList<String>()

        for (x in 1..10){

            testData.add(x.toString())

        }

        var adapter =MainActivitysRecyclerViewAdapter(this,testData)

        mains_recyclerview.adapter=adapter

        mains_recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {


            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
//                Log.e("jyj-->",adapter.itemCount.toString()+"   "+layoutManager.findLastVisibleItemPosition()+"    "+newState+"    "+adapter.itemCount)
                if(newState==0&&layoutManager.findLastVisibleItemPosition()+1==adapter.itemCount&&adapter.itemCount<30){
                    loadMoreData()
                    for (x in testData.size+1..testData.size+10){
                        testData.add(x.toString())
                        if (testData.size==30){
                            testData.add("last")
                        }
                    }
                    adapter.mData=testData
                    adapter.notifyDataSetChanged()



                }
                if(layoutManager.findLastVisibleItemPosition()+1>=30){

                    Toast.makeText(this@MainActivitys,"no more",Toast.LENGTH_SHORT).show()
                }

            }


            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }

        })


    }



    private fun loadMoreData(){






    }


}