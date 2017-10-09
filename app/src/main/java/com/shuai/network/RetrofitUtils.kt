package com.shuai.network

import com.google.gson.GsonBuilder
import com.shuai.utils.Config
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

/**
 * Created by lianglaing on 2016-08-05.
 */
object RetrofitUtils {


    /**
     * 获取Retrofit对象

     * @return
     */
     //设置服务器路径
            //添加转化库，默认是Gson
            //添加回调库，采用RxJava
            //设置使用okhttp网络请求
    val retrofit: Retrofit
        get() {

            val gson = GsonBuilder().setLenient().create()
          return  Retrofit.Builder()
                    .baseUrl(Config.HOST_URL_STR)
//                    .addConverterFactory(GsonConverterFactory.create(gson))
                  .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(OkHttp3Utils.okHttpClient)
                    .build()
        }




}