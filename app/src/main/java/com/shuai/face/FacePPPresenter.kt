package com.shuai.face

import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import com.alibaba.fastjson.JSON

import com.jude.beam.bijection.Presenter
import com.shuai.model.bean.face.FaceDetect
import com.shuai.network.NetWorks
import com.shuai.network.NetWorksSubscriber
import com.shuai.utils.Config
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

/**
 * Created by jiangyingjun on 2017/10/13.
 */

class FacePPPresenter : Presenter<FacePPActivity>() {


    override fun onCreateView(view: FacePPActivity) {
        super.onCreateView(view)
        initData()
    }


    private fun initData() {
        view.v_btn!!.text="presenter"
    }



    /**
     *
     * @time 2017-10-12
     * @description 人脸检测  face detect
     * */

    fun  detectFace(){


        var aa=object : NetWorksSubscriber<String>() {
            override fun onCompleted() {
                super.onCompleted()

            }

            override fun onNext(s: String) {
                super.onNext(s)

                var faceDetect= JSON.parseObject(s, FaceDetect::class.java)
                Toast.makeText(view,s, Toast.LENGTH_LONG).show()
            }

            override fun onError(e: Throwable) {
                super.onError(e)
//                Log.e("jyj-->",e.toString())
                Toast.makeText(view,e.toString(), Toast.LENGTH_LONG).show()
            }
        }


        var imgFile= File(Environment.getExternalStorageDirectory().absolutePath+"/jyj/luhan.jpg")
//             创建RequestBody，传入参数："multipart/form-data"，File
        val requestImgFile = RequestBody.create(MediaType.parse("image/jpg"), imgFile)
        // 创建MultipartBody.Part，用于封装文件数据
        val requestImgPart = MultipartBody.Part.createFormData("image_file",imgFile.name, requestImgFile)
        var key= RequestBody.create(null, Config.FACE_PP_API_KEY)
        var secret= RequestBody.create(null, Config.FACE_PP_API_SECRET)
        var return_landmark = RequestBody.create(null, "1")
        var attributes="gender,age,smiling,headpose,facequality,blur,eyestatus,emotion,ethnicity,beauty,mouthstatus,eyegaze,skinstatus"
        var return_attributes= RequestBody.create(null,attributes)
        NetWorks.facePPdetectApi(Config.facePP_Detect,key,secret,return_landmark,return_attributes,requestImgPart,aa)




    }



}
