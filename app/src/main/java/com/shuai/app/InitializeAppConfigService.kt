package com.shuai.app

import android.app.IntentService
import android.content.Intent
import com.shuai.tinker.util.SampleApplicationContext
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
     }


}