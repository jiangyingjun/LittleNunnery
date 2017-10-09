package com.shuai.activity.home

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.util.Xml
import android.view.View
import android.widget.Toast
import com.bumptech.glide.load.Encoder
import com.shuai.R
import com.shuai.adapter.main.MainActivitysRecyclerViewAdapter
import com.shuai.base.BaseActivity
import com.shuai.model.bean.ReceiveDTO
import com.shuai.model.gen.GreenDaoManager
import com.shuai.network.NetWorks
import com.shuai.network.NetWorksSubscriber
import com.shuai.utils.Config
import kotlinx.android.synthetic.main.activity_mains.*
import org.json.JSONObject

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


        mHandler.sendEmptyMessage(0)


        mains_btn.setOnClickListener {

//            Thread(Runnable { kotlin.run {
//
//                mHandler.post(Runnable {
//                    kotlin.run {
//                        mains_btn.text="postrunable"
//
//                       var userdao= GreenDaoManager.getInstance().daoSession.userDao
//
//                        var numcount=userdao.count()
//
//                        Toast.makeText(this@MainActivitys,numcount.toString(),Toast.LENGTH_SHORT).show()
//                    }
//                })
//
//            } }).start()
            var aa=object : NetWorksSubscriber<String>() {
                override fun onCompleted() {
                    super.onCompleted()

                    Log.e("jyj-->","oncompleted")

                }

                override fun onNext(s: String) {
                    super.onNext(s)
                    Log.e("jyj-->",s)
                    Toast.makeText(this@MainActivitys,s, Toast.LENGTH_LONG).show()
                }

                override fun onError(e: Throwable) {
                    super.onError(e)
//                Log.e("jyj-->",e.toString())
                    Toast.makeText(this@MainActivitys,e.toString(), Toast.LENGTH_LONG).show()
                }
            }
            var params_json=HashMap<String,String>()

            params_json.put("api_key",Config.FACE_PP_API_KEY)
            params_json.put("api_secret",Config.FACE_PP_API_SECRET)
            params_json.put("image_url","https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1507530011&di=bfb2fd33b8eca2f3713b363d9a7b4de8&src=http://image.uczzd.cn/16729767241092375647.jpeg?id=0&from=export")



            NetWorks.facePPdetectApi(Config.facePP_Detect,params_json,aa)


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