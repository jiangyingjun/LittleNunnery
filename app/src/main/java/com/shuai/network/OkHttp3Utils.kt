package com.shuai.network

import java.io.IOException
import java.util.concurrent.TimeUnit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor


object OkHttp3Utils {


    /**
     * 获取OkHttpClient对象

     * @return
     */
    ////添加拦截器(添加默认请求头Accept与Content-Type)
    //添加拦截器(打印所有请求头和响应头日志)
    //设置请求读写的超时时间
    //请求超时时间
    //写入超时时间
    //读取超时时间
    val okHttpClient: OkHttpClient
        get() {

                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
               return  OkHttpClient.Builder()
                        .addInterceptor(RewriteInterceptor())
                        .addInterceptor(interceptor)
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .build()
        }

    /**
     * 这个操作也是对OkHttp中使用拦截器（添加每次请求需要携带的请求头）
     */
    internal class RewriteInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
                    .newBuilder()
                   //.addHeader("Accept", "application/json,textml,application/xml,application/xhtml+xml,textml;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5")
                    .addHeader("Accept-Encoding", "gzip, deflate")
                    .addHeader("Accept","*/*")
                    .addHeader("Content-Type", "application/json;charset=UTF-8")
//                    .addHeader("cache-control", "no-cache")
//                    .addHeader("postman-token", "0e90bc6c-0583-c4f1-b2de-662f39adc5fe")
                    .build()
            return chain.proceed(request)
        }
    }
}
