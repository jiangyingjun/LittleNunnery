package com.shuai

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.shuai.activity.home.MainActivitys
import com.shuai.base.BaseActivity
import com.shuai.model.bean.ReceiveDTO
import com.shuai.network.NetWorks
import com.shuai.network.NetWorksSubscriber
import com.shuai.utils.glide.GlideImageLoader
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : BaseActivity(), View.OnClickListener {

/**
*    初始化数据后面追加 ？ 表示数据可以为空 sample   Int?
*   对象后面追加 !! 表示 该对象一定不为空
*
*
* */

    var imgLoader:GlideImageLoader?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        initData()
        initView()
//        network()



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

//                network()
                    goToMain()
            }




        }

    }



    private fun displayView(){







    }



    private fun network(){


        var aa=object : NetWorksSubscriber<ReceiveDTO>() {
            override fun onCompleted() {
                super.onCompleted()

                Log.e("jyj-->","oncompleted")

            }

            override fun onNext(s: ReceiveDTO) {
                super.onNext(s)
                displayView()
                Log.e("jyj-->",s.content)
                Toast.makeText(this@SplashActivity,s.content, Toast.LENGTH_LONG).show()

            }

            override fun onError(e: Throwable) {
                super.onError(e)
//                Log.e("jyj-->",e.toString())
//                Toast.makeText(this@SplashActivity,e.toString(), Toast.LENGTH_LONG).show()
                goToMain()
            }
        }

       NetWorks.getStartAppPacture(aa)



    }



    private fun goToMain(){

       var intent= Intent(this@SplashActivity,MainActivitys::class.java)
        startActivity(intent)


    }


}
