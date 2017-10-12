package com.shuai.activity.home

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Base64
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.load.DecodeFormat
import com.shuai.R
import com.shuai.adapter.main.MainActivitysRecyclerViewAdapter
import com.shuai.base.BaseActivity
import com.shuai.network.NetWorks
import com.shuai.network.NetWorksSubscriber
import com.shuai.utils.Config
import kotlinx.android.synthetic.main.activity_mains.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException


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





            var  imgFile=File(String(getBitmapByte( BitmapFactory.decodeResource(resources,R.drawable.luhan))))
//             创建RequestBody，传入参数："multipart/form-data"，File
            val requestImgFile = RequestBody.create(MediaType.parse("multipart/form-data"), imgFile)
            // 创建MultipartBody.Part，用于封装文件数据
            val requestImgPart = MultipartBody.Part.createFormData("img_file", imgFile.getName(), requestImgFile)

            NetWorks.facePPdetectApi(Config.facePP_Detect,params_json,requestImgPart,aa)


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


    private fun getBitmapByte(bitmap: Bitmap): ByteArray {
        val out = ByteArrayOutputStream()
        //参数1转换类型，参数2压缩质量，参数3字节流资源
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
        try {
            out.flush()
            out.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        var  byte=Base64.encode(out.toByteArray(),Base64.NO_WRAP)

        return byte

//        return out.toByteArray()
    }


}