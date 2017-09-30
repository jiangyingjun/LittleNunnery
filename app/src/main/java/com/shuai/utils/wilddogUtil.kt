//package com.shuai.utils
//
//import android.util.Log
//import com.shuai.tinker.util.SampleApplicationContext
//import com.wilddog.wilddogauth.WilddogAuth
//import com.wilddog.wilddogcore.WilddogApp
//import com.wilddog.wilddogcore.WilddogOptions
//import com.wilddog.wilddogauth.model.WilddogUser
//
//
//
///**
// * 野狗插件工具类
// */
//class  wilddogUtil{
//
//
////    private constructor(){
////        val options = WilddogOptions.Builder().setSyncUrl("https://"+Config.YEGOU_APP_ID+".wilddogio.com").build()
////        WilddogApp.initializeApp(SampleApplicationContext.context, options)
////        wilddogAuth= WilddogAuth.getInstance()
////    }
//
//
//
//    private var instance:wilddogUtil?=null
//    get() {
//        if(field==null){
//            return  wilddogUtil()
//        }
//        return  field
//    }
//    companion object {
//       private var ins:wilddogUtil?=wilddogUtil().instance
//       public final fun getInstance():wilddogUtil?{
//           return ins
//       }
//
//    }
//    var wilddogAuth:WilddogAuth?=null
//    init {
//        val options = WilddogOptions.Builder().setSyncUrl("https://"+Config.YEGOU_APP_ID+".wilddogio.com").build()
//        WilddogApp.initializeApp(SampleApplicationContext.context, options)
//        wilddogAuth= WilddogAuth.getInstance()
//    }
//
//
//    /**
//     *
//     * 匿名登录
//     *
//     * */
//
//    fun signInAnonymously(){
//
//        wilddogAuth!!.signInAnonymously().addOnCompleteListener(){ var1->
//
//            if (var1.isSuccessful) {
//                Log.e("success", "Login success!")
//                Log.e("Anonymous", var1.result.wilddogUser.isAnonymous.toString())
//            } else {
//                Log.e("failure", "reason:" + var1.exception)
//            }
//
//
//        }
//
//
//    }
//
//
//    fun getUserInfo(){
//
//
//        val user = wilddogAuth!!.getCurrentUser()
//        val uid = user.getUid()
//        val isAnonymous = user.isAnonymous()
//
//
//        Log.e("userInfo",uid+"-------"+isAnonymous)
//
//
//    }
//
//
//
//    /**
//     * 野狗推出登录
//     * */
//    fun wilddogExitLogin(){
//        wilddogAuth!!.signOut();
//    }
//
//
//}