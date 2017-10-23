package com.shuai

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import com.shuai.activity.home.MainActivitys
import com.shuai.model.bean.LocalEntry
import com.shuai.model.bean.ReceiveDTO
import com.shuai.network.NetWorks
import com.shuai.network.NetWorksSubscriber
import com.shuai.utils.glide.GlideImageLoader
import kotlinx.android.synthetic.main.activity_splash.*
import org.json.JSONObject


class SplashActivity : Activity(), View.OnClickListener {

/**
*    初始化数据后面追加 ？ 表示数据可以为空 sample   Int?
*   对象后面追加 !! 表示 该对象一定不为空
*
*
* */

    var imgLoader:GlideImageLoader?=null


    private var mHandler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)

            when (msg!!.what) {
                1 -> {
                    goToMain()
                }
            }

        }


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        initData()
        initView()
        network()




        mHandler.sendEmptyMessageDelayed(1, 1200)

}

    private fun initData() {
        imgLoader = GlideImageLoader(this)
    }


    private fun initView() {

        sp_start_img.setOnClickListener(this)

    }



    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.sp_start_img->{

                network()

            }




        }

    }



    private fun displayView(){







    }



    private fun network(){


        var aa = object : NetWorksSubscriber<String>() {
            override fun onCompleted() {
                super.onCompleted()

                Log.e("jyj-->","oncompleted")

            }

            override fun onNext(s: String) {
                super.onNext(s)
                displayView()
                Log.e("jyj-->", s)
                Toast.makeText(this@SplashActivity, s, Toast.LENGTH_LONG).show()
                var netResult = Gson().fromJson(s, ReceiveDTO::class.java)

                var jsonResult = JSONObject().getJSONObject(netResult.jsonResult)

                imgLoader!!.display(sp_start_img, jsonResult.getString("img"))
            }

            override fun onError(e: Throwable) {
                super.onError(e)
                Log.e("jyj-->",e.toString())
                Toast.makeText(this@SplashActivity,e.toString(), Toast.LENGTH_LONG).show()
//                goToMain()
            }
        }

       NetWorks.getStartAppPacture(aa)



    }



    private fun goToMain(){

       var intent= Intent(this@SplashActivity,MainActivitys::class.java)


        var list = ArrayList<String>()
        val add = list.add("jyj")
        var localEntry = LocalEntry()

        localEntry.aa = "33333"
        intent.putExtra("data2", localEntry)
        intent.putStringArrayListExtra("data", list)
        startActivity(intent)

        finish()

    }


}
