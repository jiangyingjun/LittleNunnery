package com.shuai


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.shuai.base.BaseActivity
import com.shuai.network.NetWorks
import com.shuai.network.NetWorksSubscriber
import kotlinx.android.synthetic.main.activity_test_ye_gou.*

class TestYeGouActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_ye_gou)




        /**
         * data change listener
         * **/
        //    WilddogUtil.getInstance().ref.addValueEventListener(object : ValueEventListener {
//        override fun onDataChange(dataSnapshot: DataSnapshot) {
//            if(dataSnapshot.getValue()!=null){
//                Log.e("datachange-->", dataSnapshot.childrenCount.toString() + "--" + dataSnapshot.value.toString())
//            }
//        }
//
//        override fun onCancelled(syncError: SyncError) {
//            if(syncError!=null){
//                Log.e("onCancelled-->", syncError.message)
//            }
//
//        }
//    })

        /**
         * connected stauts linstener
         *
         * */
        //    WilddogUtil.getInstance().connectedRef.addValueEventListener(object :ValueEventListener{
//        override fun onDataChange(dataSnapshot: DataSnapshot) {
//
//            val connected = dataSnapshot.getValue(Boolean::class.java) as Boolean
//            if (connected) {
////                Toast.makeText(this@SplashActivity,"connent",Toast.LENGTH_SHORT).show()
//                Log.e("connect----->","connect")
//            } else {
////                Toast.makeText(this@SplashActivity,"not connented",Toast.LENGTH_SHORT).show()
//                Log.e("connect----->","not connect")
//            }
//        }
//
//        override fun onCancelled(syncError: SyncError) {
//            if(syncError!=null){
//                Log.e("connected-->", syncError.message)
//            }
//
//        }
//
//    })

    }









    private fun initView() {

//        splash_img!!.setOnClickListener(this)
        splash_btn_login!!.setOnClickListener(this)
        splash_btn_exit_login!!.setOnClickListener(this)
        splash_btn_value!!.setOnClickListener(this)
        splash_btn_video!!.setOnClickListener(this)
        splash_btn_userinfo!!.setOnClickListener(this)

        splash_btn_change!!.setOnClickListener(this)
        splash_btn_delete!!.setOnClickListener(this)

        splash_btn_meitiliu!!.setOnClickListener(this)


        splash_interface!!.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when (v.id) {

            R.id.splash_interface->{


//                Thread(Runnable {
//
//                    val client = OkHttpClient()
//
//                    val request = Request.Builder()
//                            .url("http://localhost:8080/test/test")
//                            .get()
//                            .addHeader("cache-control", "no-cache")
//                            .addHeader("postman-token", "0e90bc6c-0583-c4f1-b2de-662f39adc5fe")
//                            .build()
//
//                    val response = client.newCall(request).execute()
//
//                    Log.e("jyj-->", response.message())
//
//                }).start()



            }

//            R.id.splash_img -> {
////                var result:Boolean?=null;
//////                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
//////                    var contentUri=FileProvider.getUriForFile(this@SplashActivity,Config.FileProvider_Name,File(Environment.getExternalStorageDirectory().absolutePath + File.separator + Config.DIRCETOYR))
//////
//////
//////                }else{
//////                    result=FileUtil.CreateDirFile(Environment.getExternalStorageDirectory().absolutePath + File.separator + Config.DIRCETOYR)
//////                }
////                result=FileUtil.CreateDirFile(Environment.getExternalStorageDirectory().absolutePath + File.separator + Config.DIRCETOYR)
////                Log.e(BaseActivity.TAG,result.toString())
////                TinkerInstaller.onReceiveUpgradePatch(this@SplashActivity, Environment.getExternalStorageDirectory().absolutePath + File.separator + Config.DIRCETOYR + "/patch.apk" )
////                val intent = Intent(this@SplashActivity, TestActivity::class.java)
////                startActivity(intent)
////                finish()
//            }

//            R.id.splash_btn_login->{
//
//                WilddogUtil.getInstance().signInAnonymously()
//
//
//            }
//
//
//            R.id.splash_btn_exit_login->{
//
//
//                WilddogUtil.getInstance().exitWilddog()
//
//
//            }

            R.id.splash_btn_value->{

//                var  data=makeData()
//                if (data.size>0){
//                    WilddogUtil.getInstance().ref.setValue(data){ syncError, syncReference ->
//                        if (syncError!=null){
//                            Log.e("data-->",syncError.errCode.toString()+"      "+syncError.message)
//                        }else{
//
//                            Log.e("data-->","success")
//
//                        }}
//                }

            }
            R.id.splash_btn_video->{

//                WilddogUtil.getInstance().initVideoSDK()
//
//                WilddogUtil.getInstance().video.setListener(object : WilddogVideo.Listener {
//                    override fun onCalled(conversation: Conversation, s: String) {
//                        Log.e("oncalled", conversation.status.toString()+"     "+s)
//
//                    }
//
//                    override fun onTokenError(wilddogVideoError: WilddogVideoError) {
//                        Log.e("onTokenError",wilddogVideoError.message )
//                    }
//                })

//                var intent=Intent(this@SplashActivity,ConverationActivity::class.java)
//                startActivity(intent)

            }

//            R.id.splash_btn_userinfo->{
//
//
//                WilddogUtil.getInstance().getLoginUserInfo()
//
//            }

            R.id.splash_btn_change->{


                var  data=makeData()
                if (data.size>0){
//                    WilddogUtil.getInstance().ref.updateChildren(data as Map<String, Any>?)
                }


            }


            R.id.splash_btn_delete->{


//                WilddogUtil.getInstance().ref.removeValue()

            }


            R.id.splash_btn_meitiliu->{

//                val builder = LocalStreamOptions.Builder()
//
//                var options=builder.captureAudio(true).captureAudio(true).dimension(LocalStreamOptions.Dimension.DIMENSION_720P).build()

//                val localStream = WilddogUtil.getInstance().video.createLocalStream(options)

            }


        }
    }


    private fun makeData():HashMap<String,String>{
        var users=HashMap<String,String>()

        var key=splash_et_key.text

        var data=splash_et_value.text



        if (key.equals("")||key==null||key.length<=0){


            Toast.makeText(this@TestYeGouActivity,"输入key值",Toast.LENGTH_SHORT).show()

            return users
        }

        if (data.length<=0||data==null||data.equals("")){

            Toast.makeText(this@TestYeGouActivity,"输入value值",Toast.LENGTH_SHORT).show()
            return users
        }
        if (key!=null&&!key.equals("key")&&data!=null&&!data.equals("value")&&!key.equals("")&&!data.equals("")){
            users.put(splash_et_key.text.toString(),splash_et_value.text.toString())
        }

        return users
    }



    private  val list=ArrayList<Int>()
    private fun  testFun(list:List<Int>):Int?{

        for (i in 1..list.size-1){
            Log.e("jyj-->",i.toString())

        }

        return  list.size

    }

    override fun onDestroy() {
        super.onDestroy()


    }





}
