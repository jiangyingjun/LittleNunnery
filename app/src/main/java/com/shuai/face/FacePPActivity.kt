package com.shuai.face

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.megvii.facepp.sdk.Facepp
import com.megvii.licensemanager.sdk.LicenseManager
import com.shuai.R
import com.shuai.base.BaseActivity
import com.shuai.utils.Config
import kotlinx.android.synthetic.main.activity_facepp.*

/**
 * Created by jiangyingjun on 2017/9/20.
 */
class FacePPActivity :BaseActivity(), View.OnClickListener {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facepp)



        facepp_btn.setOnClickListener(this)

        initView()





    }
    /**
     *
     * 获取face++的联网授权
     *
    * */
    private fun initData() {

        val licenseManager = LicenseManager(this)
        licenseManager.setExpirationMillis(Facepp.getApiExpirationMillis(this, ConUtil.getFileContent(this, R.raw
                .megviifacepp_0_4_7_model)))

        val uuid = ConUtil.getUUIDString(this@FacePPActivity)
        val apiName = Facepp.getApiName()

        licenseManager.setAuthTimeBufferMillis(0)

        licenseManager.takeLicenseFromNetwork(uuid, Config.FACE_PP_API_KEY, Config.FACE_PP_API_SECRET, apiName,
                LicenseManager.DURATION_30DAYS, "Landmark", "1", true, object : LicenseManager.TakeLicenseCallback {

            override fun onSuccess() {
                authState(true)
            }

          override  fun onFailed(i: Int, bytes: ByteArray) {
                Log.e("jyj-->", String(bytes))
                authState(false)
            }
        })


    }

    private fun initView() {



    }


    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.facepp_btn->{

            Toast.makeText(this@FacePPActivity,"fasfas",Toast.LENGTH_SHORT).show()

                initData()

            }
        }



    }


    private fun authState(isSuccess: Boolean) {
        if (isSuccess) {

            val intent = Intent()
            intent.setClass(this, FaceppActionActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP//If set, and the activity being launched is already running in the current task, then instead of launching a new instance of that activity,all of the other activities on top of it will be closed and this Intent will be delivered to the (now on top) old activity as a new Intent.
            startActivity(intent)

        } else {
//            WarrantyBar.setVisibility(View.GONE)
//            againWarrantyBtn.setVisibility(View.VISIBLE)
//            WarrantyText.setText(resources.getString(R.string.auth_fail))
        }
    }




}