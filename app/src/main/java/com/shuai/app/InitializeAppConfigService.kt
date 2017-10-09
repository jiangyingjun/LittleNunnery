package com.shuai.app

import android.app.IntentService
import android.content.Intent
import android.util.Log
import com.megvii.facepp.sdk.Facepp
import com.megvii.licensemanager.sdk.LicenseManager
import com.shuai.R
import com.shuai.face.ConUtil
import com.shuai.tinker.util.SampleApplicationContext
import com.shuai.utils.Config
import com.shuai.utils.CrashHandler

/**
 * Created by jiangyingjun on 2017/10/9.
 */
class InitializeAppConfigService :IntentService{




    constructor() : super("InitializeAppConfigService")



    companion object {

        val INIIT_APP_CREATE="com.shuai.app.init.open"


        fun start(){

            var intent=Intent(SampleApplicationContext.context,InitializeAppConfigService::class.java)
            intent.action=INIIT_APP_CREATE

            SampleApplicationContext.context.startService(intent)

        }
    }


    override fun onHandleIntent(intent: Intent?) {
            if (intent!=null){


                if(intent.action.equals(INIIT_APP_CREATE)){
                    init()
                }

            }

    }

    private fun init(){
        CrashHandler.getInstance().init(application)
        initFacePPData()


     }



    /**
     *
     * 获取face++的联网授权
     *
     * */
    private fun initFacePPData() {

        val licenseManager = LicenseManager(SampleApplicationContext.context)
        licenseManager.setExpirationMillis(Facepp.getApiExpirationMillis(SampleApplicationContext.context, ConUtil.getFileContent(SampleApplicationContext.context, R.raw
                .megviifacepp_0_4_7_model)))

        val uuid = ConUtil.getUUIDString(SampleApplicationContext.context)
        val apiName = Facepp.getApiName()

        licenseManager.setAuthTimeBufferMillis(0)

        licenseManager.takeLicenseFromNetwork(uuid, Config.FACE_PP_API_KEY, Config.FACE_PP_API_SECRET, apiName,
                LicenseManager.DURATION_30DAYS, "Landmark", "1", true, object : LicenseManager.TakeLicenseCallback {

            override fun onSuccess() {

            }

            override  fun onFailed(i: Int, bytes: ByteArray) {
                Log.e("jyj-->", String(bytes))

            }
        })


    }

}