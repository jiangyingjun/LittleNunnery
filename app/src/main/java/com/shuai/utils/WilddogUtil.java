//package com.shuai.utils;
//
//import android.util.Log;
//import android.widget.Toast;
//
//import com.shuai.tinker.util.SampleApplicationContext;
//import com.wilddog.client.SyncError;
//import com.wilddog.client.SyncReference;
//import com.wilddog.client.WilddogSync;
//import com.wilddog.location.WilddogLocation;
//import com.wilddog.video.WilddogVideo;
//import com.wilddog.wilddogauth.WilddogAuth;
//import com.wilddog.wilddogauth.core.Task;
//import com.wilddog.wilddogauth.core.listener.OnCompleteListener;
//import com.wilddog.wilddogauth.core.result.AuthResult;
//import com.wilddog.wilddogauth.model.WilddogUser;
//import com.wilddog.wilddogcore.WilddogApp;
//import com.wilddog.wilddogcore.WilddogOptions;
//
///**
// * Created by jiangyingjun on 2017/9/12.
// */
//
//public class WilddogUtil {
//
//    private static WilddogUtil instance;
//
//
//
//    public static WilddogUtil getInstance(){
//
//        if (instance==null){
//            instance = new WilddogUtil();
//        }
//        return instance;
//    }
//    private WilddogAuth wilddogAuth;
//
//    public SyncReference ref;
//
//    public SyncReference connectedRef;
//
//    public  SyncReference offlineRef;
//
//    /**
//     * 定位操作类
//     * */
//    public WilddogLocation location;
//    /*野狗sdk初始化*/
//    private WilddogUtil(){
//        WilddogOptions options =new WilddogOptions.Builder().setSyncUrl("https://"+Config.Companion.getYEGOU_APP_ID()+".wilddogio.com").build();
//        WilddogApp.initializeApp(SampleApplicationContext.context, options);
//
//
//        /*获取登录相关的实例
//            */
//        wilddogAuth= WilddogAuth.getInstance();
//
//        ref= WilddogSync.getInstance().getReference("web/saving-data/wildblog/users");
//        connectedRef = WilddogSync.getInstance().getReference(".info/connected");
//
//        offlineRef = WilddogSync.getInstance().getReference("disconnectmessage");
//
//        offLineLinstener();
//
//        location = new WilddogLocation(WilddogSync.getInstance().getReference());
//
//    }
//    /**
//     *
//     * 匿名登录
//     * */
//    public void signInAnonymously(){
//        wilddogAuth.signInAnonymously().addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(Task<AuthResult> var1) {
//                if(var1.isSuccessful()){
////                    Log.e("success","Login success!");
//                    Log.e("Anonymous",String.valueOf(var1.getResult().getWilddogUser().isAnonymous()));
////                    initVideoSDK();
//                    Toast.makeText(SampleApplicationContext.context,"登录成功",Toast.LENGTH_SHORT).show();
//                }else {
////                    Log.e("failure","reason:"+var1.getException());
//                    Toast.makeText(SampleApplicationContext.context,"登录失败---》"+var1.getException(),Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
//    /**
//     * 获取匿名登录用户信息
//     *
//     * */
//    public String getLoginUserInfo(){
//
//
//        WilddogUser user = wilddogAuth.getCurrentUser();
//
//        if (user!=null){
//            Toast.makeText(SampleApplicationContext.context,"Uid-->"+user.getUid(),Toast.LENGTH_SHORT).show();
//            return user.getUid();
//        }else {
//
//            Toast.makeText(SampleApplicationContext.context,"暂无用户信息"+user.getUid(),Toast.LENGTH_SHORT).show();
//            return "";
//        }
//
////        String uid = user.getUid();
////        boolean isAnonymous = user.isAnonymous();
//
//    }
//
//    /**
//    *
//    *
//    * 推出登录
//    * */
//    public void exitWilddog(){
//
//        wilddogAuth.signOut();
//
//    }
//
//
//    /**
//     *
//     *
//     *  离线监听
//     *
//     * */
//    private void offLineLinstener(){
//
//
//        offlineRef.onDisconnect().removeValue(new SyncReference.CompletionListener() {
//            @Override
//            public void onComplete(SyncError error, SyncReference syncReference) {
//                if (error != null) {
//                    System.out.println("could not establish onDisconnect event:" + error.getMessage());
//                }
//            }
//        });
//
//    }
//
//    public WilddogVideo video;
//    public void initVideoSDK(){
//
//        String token = WilddogAuth.getInstance().getCurrentUser().getToken(false).getResult().getToken();
//        //初始化 WilddogVideo SDK
//        WilddogVideo.initialize(SampleApplicationContext.context, Config.Companion.getYEGOU_VIDEO_APP_ID(),token);
//        //获取 WilddogVideo对象
//        video = WilddogVideo.getInstance();
//
//    }
//
//
//
//
//}
