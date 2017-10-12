package com.shuai.face

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Toast
import com.alibaba.fastjson.JSON
import com.megvii.facepp.sdk.Facepp
import com.megvii.licensemanager.sdk.LicenseManager
import com.shuai.R
import com.shuai.base.BaseActivity
import com.shuai.model.bean.face.FaceDetect
import com.shuai.network.NetWorks
import com.shuai.network.NetWorksSubscriber
import com.shuai.utils.Config
import kotlinx.android.synthetic.main.activity_facepp.*
import kotlinx.android.synthetic.main.activity_mains.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import java.io.File

/**
 * Created by jiangyingjun on 2017/9/20.
 */
class FacePPActivity :BaseActivity(), View.OnClickListener {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facepp)



        facepp_btn.setOnClickListener(this)

        initView()
        initData()

    }
    private fun initData() {




    }

    private fun initView() {



    }


    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.facepp_btn->{

//            detectFace()

                var s="\n" +
                        "\n" +
                        "{\n" +
                        "\t\"image_id\": \"XNw0u/NER2UkTMx26MfEJw==\",\n" +
                        "\t\"request_id\": \"1507780667,cf7ae2e0-6e03-4e6c-a748-b802e757894e\",\n" +
                        "\t\"time_used\": 292,\n" +
                        "\t\"faces\": [{\n" +
                        "\t\t\"landmark\": {\n" +
                        "\t\t\t\"mouth_upper_lip_left_contour2\": {\n" +
                        "\t\t\t\t\"y\": 244,\n" +
                        "\t\t\t\t\"x\": 244\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"mouth_upper_lip_top\": {\n" +
                        "\t\t\t\t\"y\": 243,\n" +
                        "\t\t\t\t\"x\": 259\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"mouth_upper_lip_left_contour1\": {\n" +
                        "\t\t\t\t\"y\": 242,\n" +
                        "\t\t\t\t\"x\": 253\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"left_eye_upper_left_quarter\": {\n" +
                        "\t\t\t\t\"y\": 170,\n" +
                        "\t\t\t\t\"x\": 212\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"left_eyebrow_lower_middle\": {\n" +
                        "\t\t\t\t\"y\": 157,\n" +
                        "\t\t\t\t\"x\": 214\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"mouth_upper_lip_left_contour3\": {\n" +
                        "\t\t\t\t\"y\": 246,\n" +
                        "\t\t\t\t\"x\": 248\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"left_eyebrow_lower_left_quarter\": {\n" +
                        "\t\t\t\t\"y\": 157,\n" +
                        "\t\t\t\t\"x\": 203\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"right_eyebrow_lower_left_quarter\": {\n" +
                        "\t\t\t\t\"y\": 158,\n" +
                        "\t\t\t\t\"x\": 283\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"right_eye_pupil\": {\n" +
                        "\t\t\t\t\"y\": 169,\n" +
                        "\t\t\t\t\"x\": 291\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"mouth_lower_lip_right_contour1\": {\n" +
                        "\t\t\t\t\"y\": 249,\n" +
                        "\t\t\t\t\"x\": 273\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"mouth_lower_lip_left_contour2\": {\n" +
                        "\t\t\t\t\"y\": 252,\n" +
                        "\t\t\t\t\"x\": 243\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"mouth_lower_lip_right_contour3\": {\n" +
                        "\t\t\t\t\"y\": 256,\n" +
                        "\t\t\t\t\"x\": 270\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"mouth_lower_lip_right_contour2\": {\n" +
                        "\t\t\t\t\"y\": 251,\n" +
                        "\t\t\t\t\"x\": 279\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"contour_chin\": {\n" +
                        "\t\t\t\t\"y\": 291,\n" +
                        "\t\t\t\t\"x\": 265\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"contour_left9\": {\n" +
                        "\t\t\t\t\"y\": 287,\n" +
                        "\t\t\t\t\"x\": 248\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"left_eye_lower_right_quarter\": {\n" +
                        "\t\t\t\t\"y\": 178,\n" +
                        "\t\t\t\t\"x\": 228\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"mouth_lower_lip_top\": {\n" +
                        "\t\t\t\t\"y\": 251,\n" +
                        "\t\t\t\t\"x\": 260\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"right_eyebrow_upper_middle\": {\n" +
                        "\t\t\t\t\"y\": 147,\n" +
                        "\t\t\t\t\"x\": 295\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"right_eyebrow_left_corner\": {\n" +
                        "\t\t\t\t\"y\": 159,\n" +
                        "\t\t\t\t\"x\": 269\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"right_eye_lower_right_quarter\": {\n" +
                        "\t\t\t\t\"y\": 173,\n" +
                        "\t\t\t\t\"x\": 301\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"right_eye_bottom\": {\n" +
                        "\t\t\t\t\"y\": 175,\n" +
                        "\t\t\t\t\"x\": 292\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"contour_left7\": {\n" +
                        "\t\t\t\t\"y\": 265,\n" +
                        "\t\t\t\t\"x\": 224\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"contour_left6\": {\n" +
                        "\t\t\t\t\"y\": 252,\n" +
                        "\t\t\t\t\"x\": 215\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"contour_left5\": {\n" +
                        "\t\t\t\t\"y\": 238,\n" +
                        "\t\t\t\t\"x\": 207\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"contour_left4\": {\n" +
                        "\t\t\t\t\"y\": 223,\n" +
                        "\t\t\t\t\"x\": 202\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"contour_left3\": {\n" +
                        "\t\t\t\t\"y\": 208,\n" +
                        "\t\t\t\t\"x\": 197\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"contour_left2\": {\n" +
                        "\t\t\t\t\"y\": 193,\n" +
                        "\t\t\t\t\"x\": 194\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"contour_left1\": {\n" +
                        "\t\t\t\t\"y\": 177,\n" +
                        "\t\t\t\t\"x\": 192\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"left_eye_lower_left_quarter\": {\n" +
                        "\t\t\t\t\"y\": 177,\n" +
                        "\t\t\t\t\"x\": 212\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"contour_right1\": {\n" +
                        "\t\t\t\t\"y\": 169,\n" +
                        "\t\t\t\t\"x\": 342\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"contour_right3\": {\n" +
                        "\t\t\t\t\"y\": 203,\n" +
                        "\t\t\t\t\"x\": 340\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"contour_right2\": {\n" +
                        "\t\t\t\t\"y\": 186,\n" +
                        "\t\t\t\t\"x\": 341\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"mouth_left_corner\": {\n" +
                        "\t\t\t\t\"y\": 247,\n" +
                        "\t\t\t\t\"x\": 236\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"contour_right4\": {\n" +
                        "\t\t\t\t\"y\": 220,\n" +
                        "\t\t\t\t\"x\": 337\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"contour_right7\": {\n" +
                        "\t\t\t\t\"y\": 265,\n" +
                        "\t\t\t\t\"x\": 312\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"left_eyebrow_left_corner\": {\n" +
                        "\t\t\t\t\"y\": 158,\n" +
                        "\t\t\t\t\"x\": 193\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"nose_right\": {\n" +
                        "\t\t\t\t\"y\": 216,\n" +
                        "\t\t\t\t\"x\": 279\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"nose_tip\": {\n" +
                        "\t\t\t\t\"y\": 214,\n" +
                        "\t\t\t\t\"x\": 257\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"contour_right5\": {\n" +
                        "\t\t\t\t\"y\": 237,\n" +
                        "\t\t\t\t\"x\": 331\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"nose_contour_lower_middle\": {\n" +
                        "\t\t\t\t\"y\": 226,\n" +
                        "\t\t\t\t\"x\": 259\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"right_eye_top\": {\n" +
                        "\t\t\t\t\"y\": 165,\n" +
                        "\t\t\t\t\"x\": 291\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"mouth_lower_lip_left_contour3\": {\n" +
                        "\t\t\t\t\"y\": 256,\n" +
                        "\t\t\t\t\"x\": 250\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"right_eye_right_corner\": {\n" +
                        "\t\t\t\t\"y\": 170,\n" +
                        "\t\t\t\t\"x\": 308\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"mouth_upper_lip_right_contour1\": {\n" +
                        "\t\t\t\t\"y\": 241,\n" +
                        "\t\t\t\t\"x\": 265\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"mouth_upper_lip_right_contour2\": {\n" +
                        "\t\t\t\t\"y\": 243,\n" +
                        "\t\t\t\t\"x\": 275\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"right_eyebrow_lower_right_quarter\": {\n" +
                        "\t\t\t\t\"y\": 155,\n" +
                        "\t\t\t\t\"x\": 309\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"left_eye_left_corner\": {\n" +
                        "\t\t\t\t\"y\": 174,\n" +
                        "\t\t\t\t\"x\": 206\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"mouth_right_corner\": {\n" +
                        "\t\t\t\t\"y\": 245,\n" +
                        "\t\t\t\t\"x\": 285\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"right_eye_lower_left_quarter\": {\n" +
                        "\t\t\t\t\"y\": 175,\n" +
                        "\t\t\t\t\"x\": 284\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"left_eyebrow_right_corner\": {\n" +
                        "\t\t\t\t\"y\": 159,\n" +
                        "\t\t\t\t\"x\": 236\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"left_eyebrow_lower_right_quarter\": {\n" +
                        "\t\t\t\t\"y\": 159,\n" +
                        "\t\t\t\t\"x\": 225\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"right_eye_center\": {\n" +
                        "\t\t\t\t\"y\": 171,\n" +
                        "\t\t\t\t\"x\": 292\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"left_eye_pupil\": {\n" +
                        "\t\t\t\t\"y\": 173,\n" +
                        "\t\t\t\t\"x\": 222\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"nose_left\": {\n" +
                        "\t\t\t\t\"y\": 220,\n" +
                        "\t\t\t\t\"x\": 240\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"mouth_lower_lip_left_contour1\": {\n" +
                        "\t\t\t\t\"y\": 250,\n" +
                        "\t\t\t\t\"x\": 248\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"left_eye_upper_right_quarter\": {\n" +
                        "\t\t\t\t\"y\": 170,\n" +
                        "\t\t\t\t\"x\": 228\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"right_eyebrow_lower_middle\": {\n" +
                        "\t\t\t\t\"y\": 155,\n" +
                        "\t\t\t\t\"x\": 296\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"left_eye_center\": {\n" +
                        "\t\t\t\t\"y\": 174,\n" +
                        "\t\t\t\t\"x\": 220\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"contour_left8\": {\n" +
                        "\t\t\t\t\"y\": 277,\n" +
                        "\t\t\t\t\"x\": 235\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"contour_right9\": {\n" +
                        "\t\t\t\t\"y\": 287,\n" +
                        "\t\t\t\t\"x\": 284\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"right_eye_left_corner\": {\n" +
                        "\t\t\t\t\"y\": 175,\n" +
                        "\t\t\t\t\"x\": 276\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"left_eyebrow_upper_left_quarter\": {\n" +
                        "\t\t\t\t\"y\": 150,\n" +
                        "\t\t\t\t\"x\": 202\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"left_eye_bottom\": {\n" +
                        "\t\t\t\t\"y\": 179,\n" +
                        "\t\t\t\t\"x\": 220\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"left_eye_right_corner\": {\n" +
                        "\t\t\t\t\"y\": 176,\n" +
                        "\t\t\t\t\"x\": 235\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"right_eyebrow_upper_left_quarter\": {\n" +
                        "\t\t\t\t\"y\": 150,\n" +
                        "\t\t\t\t\"x\": 281\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"contour_right8\": {\n" +
                        "\t\t\t\t\"y\": 278,\n" +
                        "\t\t\t\t\"x\": 300\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"right_eyebrow_right_corner\": {\n" +
                        "\t\t\t\t\"y\": 155,\n" +
                        "\t\t\t\t\"x\": 322\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"right_eye_upper_left_quarter\": {\n" +
                        "\t\t\t\t\"y\": 168,\n" +
                        "\t\t\t\t\"x\": 282\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"left_eyebrow_upper_middle\": {\n" +
                        "\t\t\t\t\"y\": 150,\n" +
                        "\t\t\t\t\"x\": 214\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"right_eyebrow_upper_right_quarter\": {\n" +
                        "\t\t\t\t\"y\": 147,\n" +
                        "\t\t\t\t\"x\": 310\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"nose_contour_left1\": {\n" +
                        "\t\t\t\t\"y\": 177,\n" +
                        "\t\t\t\t\"x\": 245\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"nose_contour_left2\": {\n" +
                        "\t\t\t\t\"y\": 207,\n" +
                        "\t\t\t\t\"x\": 243\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"nose_contour_left3\": {\n" +
                        "\t\t\t\t\"y\": 224,\n" +
                        "\t\t\t\t\"x\": 249\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"nose_contour_right1\": {\n" +
                        "\t\t\t\t\"y\": 175,\n" +
                        "\t\t\t\t\"x\": 266\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"nose_contour_right2\": {\n" +
                        "\t\t\t\t\"y\": 204,\n" +
                        "\t\t\t\t\"x\": 273\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"mouth_lower_lip_bottom\": {\n" +
                        "\t\t\t\t\"y\": 258,\n" +
                        "\t\t\t\t\"x\": 260\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"contour_right6\": {\n" +
                        "\t\t\t\t\"y\": 252,\n" +
                        "\t\t\t\t\"x\": 323\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"nose_contour_right3\": {\n" +
                        "\t\t\t\t\"y\": 222,\n" +
                        "\t\t\t\t\"x\": 269\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"left_eye_top\": {\n" +
                        "\t\t\t\t\"y\": 168,\n" +
                        "\t\t\t\t\"x\": 220\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"mouth_upper_lip_right_contour3\": {\n" +
                        "\t\t\t\t\"y\": 246,\n" +
                        "\t\t\t\t\"x\": 272\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"left_eyebrow_upper_right_quarter\": {\n" +
                        "\t\t\t\t\"y\": 152,\n" +
                        "\t\t\t\t\"x\": 226\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"right_eye_upper_right_quarter\": {\n" +
                        "\t\t\t\t\"y\": 166,\n" +
                        "\t\t\t\t\"x\": 300\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"mouth_upper_lip_bottom\": {\n" +
                        "\t\t\t\t\"y\": 247,\n" +
                        "\t\t\t\t\"x\": 259\n" +
                        "\t\t\t}\n" +
                        "\t\t},\n" +
                        "\t\t\"attributes\": {\n" +
                        "\t\t\t\"emotion\": {\n" +
                        "\t\t\t\t\"sadness\": 6.385,\n" +
                        "\t\t\t\t\"neutral\": 53.139,\n" +
                        "\t\t\t\t\"disgust\": 17.603,\n" +
                        "\t\t\t\t\"anger\": 0.88,\n" +
                        "\t\t\t\t\"surprise\": 5.712,\n" +
                        "\t\t\t\t\"fear\": 3.021,\n" +
                        "\t\t\t\t\"happiness\": 13.259\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"beauty\": {\n" +
                        "\t\t\t\t\"female_score\": 77.129,\n" +
                        "\t\t\t\t\"male_score\": 75.229\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"gender\": {\n" +
                        "\t\t\t\t\"value\": \"Female\"\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"age\": {\n" +
                        "\t\t\t\t\"value\": 19\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"mouthstatus\": {\n" +
                        "\t\t\t\t\"close\": 19.419,\n" +
                        "\t\t\t\t\"surgical_mask_or_respirator\": 0.0,\n" +
                        "\t\t\t\t\"open\": 80.581,\n" +
                        "\t\t\t\t\"other_occlusion\": 0.0\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"glass\": {\n" +
                        "\t\t\t\t\"value\": \"None\"\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"skinstatus\": {\n" +
                        "\t\t\t\t\"dark_circle\": 7.398,\n" +
                        "\t\t\t\t\"stain\": 6.494,\n" +
                        "\t\t\t\t\"acne\": 4.529,\n" +
                        "\t\t\t\t\"health\": 75.768\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"headpose\": {\n" +
                        "\t\t\t\t\"yaw_angle\": 11.923901,\n" +
                        "\t\t\t\t\"pitch_angle\": 3.597795,\n" +
                        "\t\t\t\t\"roll_angle\": -5.58217\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"blur\": {\n" +
                        "\t\t\t\t\"blurness\": {\n" +
                        "\t\t\t\t\t\"threshold\": 50.0,\n" +
                        "\t\t\t\t\t\"value\": 0.405\n" +
                        "\t\t\t\t},\n" +
                        "\t\t\t\t\"motionblur\": {\n" +
                        "\t\t\t\t\t\"threshold\": 50.0,\n" +
                        "\t\t\t\t\t\"value\": 0.405\n" +
                        "\t\t\t\t},\n" +
                        "\t\t\t\t\"gaussianblur\": {\n" +
                        "\t\t\t\t\t\"threshold\": 50.0,\n" +
                        "\t\t\t\t\t\"value\": 0.405\n" +
                        "\t\t\t\t}\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"smile\": {\n" +
                        "\t\t\t\t\"threshold\": 30.1,\n" +
                        "\t\t\t\t\"value\": 44.261\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"eyestatus\": {\n" +
                        "\t\t\t\t\"left_eye_status\": {\n" +
                        "\t\t\t\t\t\"normal_glass_eye_open\": 0.004,\n" +
                        "\t\t\t\t\t\"no_glass_eye_close\": 0.0,\n" +
                        "\t\t\t\t\t\"occlusion\": 0.0,\n" +
                        "\t\t\t\t\t\"no_glass_eye_open\": 99.996,\n" +
                        "\t\t\t\t\t\"normal_glass_eye_close\": 0.0,\n" +
                        "\t\t\t\t\t\"dark_glasses\": 0.0\n" +
                        "\t\t\t\t},\n" +
                        "\t\t\t\t\"right_eye_status\": {\n" +
                        "\t\t\t\t\t\"normal_glass_eye_open\": 0.019,\n" +
                        "\t\t\t\t\t\"no_glass_eye_close\": 0.0,\n" +
                        "\t\t\t\t\t\"occlusion\": 0.0,\n" +
                        "\t\t\t\t\t\"no_glass_eye_open\": 99.981,\n" +
                        "\t\t\t\t\t\"normal_glass_eye_close\": 0.0,\n" +
                        "\t\t\t\t\t\"dark_glasses\": 0.0\n" +
                        "\t\t\t\t}\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"facequality\": {\n" +
                        "\t\t\t\t\"threshold\": 70.1,\n" +
                        "\t\t\t\t\"value\": 73.568\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"ethnicity\": {\n" +
                        "\t\t\t\t\"value\": \"Asian\"\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"eyegaze\": {\n" +
                        "\t\t\t\t\"right_eye_gaze\": {\n" +
                        "\t\t\t\t\t\"position_x_coordinate\": 0.516,\n" +
                        "\t\t\t\t\t\"vector_z_component\": 0.969,\n" +
                        "\t\t\t\t\t\"vector_x_component\": -0.034,\n" +
                        "\t\t\t\t\t\"vector_y_component\": 0.245,\n" +
                        "\t\t\t\t\t\"position_y_coordinate\": 0.439\n" +
                        "\t\t\t\t},\n" +
                        "\t\t\t\t\"left_eye_gaze\": {\n" +
                        "\t\t\t\t\t\"position_x_coordinate\": 0.507,\n" +
                        "\t\t\t\t\t\"vector_z_component\": 0.953,\n" +
                        "\t\t\t\t\t\"vector_x_component\": -0.161,\n" +
                        "\t\t\t\t\t\"vector_y_component\": 0.258,\n" +
                        "\t\t\t\t\t\"position_y_coordinate\": 0.435\n" +
                        "\t\t\t\t}\n" +
                        "\t\t\t}\n" +
                        "\t\t},\n" +
                        "\t\t\"face_rectangle\": {\n" +
                        "\t\t\t\"width\": 154,\n" +
                        "\t\t\t\"top\": 144,\n" +
                        "\t\t\t\"left\": 191,\n" +
                        "\t\t\t\"height\": 154\n" +
                        "\t\t},\n" +
                        "\t\t\"face_token\": \"91dfcc9e9e2ef59134998a6662f6789a\"\n" +
                        "\t}]\n" +
                        "}\n" +
                        "     \n" +
                        "{\n" +
                        "    \"image_id\":\"XNw0u/NER2UkTMx26MfEJw==\",\n" +
                        "    \"request_id\":\"1507780667,cf7ae2e0-6e03-4e6c-a748-b802e757894e\",\n" +
                        "    \"time_used\":292,\n" +
                        "    \"faces\":[\n" +
                        "        {\n" +
                        "            \"landmark\":Object{...},\n" +
                        "            \"attributes\":Object{...},\n" +
                        "            \"face_rectangle\":Object{...},\n" +
                        "            \"face_token\":\"91dfcc9e9e2ef59134998a6662f6789a\"\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}\n" +
                        ""
                try {
                    var faceDetect=JSON.parseObject(s,FaceDetect::class.java)

                    Log.e("jyj-->",faceDetect.toString())
                }catch (e:Exception){


                }

            }
        }



    }



    /**
    *
    * @time 2017-10-12
    * @description 人脸检测  face detect
    * */

    private fun  detectFace(){


        var aa=object : NetWorksSubscriber<String>() {
            override fun onCompleted() {
                super.onCompleted()

            }

            override fun onNext(s: String) {
                super.onNext(s)

                var faceDetect=JSON.parseObject(s,FaceDetect::class.java)

                Toast.makeText(this@FacePPActivity,s, Toast.LENGTH_LONG).show()
            }

            override fun onError(e: Throwable) {
                super.onError(e)
//                Log.e("jyj-->",e.toString())
                Toast.makeText(this@FacePPActivity,e.toString(), Toast.LENGTH_LONG).show()
            }
        }


        var imgFile= File(Environment.getExternalStorageDirectory().absolutePath+"/jyj/luhan.jpg")
//             创建RequestBody，传入参数："multipart/form-data"，File
        val requestImgFile = RequestBody.create(MediaType.parse("image/jpg"), imgFile)
        // 创建MultipartBody.Part，用于封装文件数据
        val requestImgPart = MultipartBody.Part.createFormData("image_file",imgFile.name, requestImgFile)
        var key= RequestBody.create(null,Config.FACE_PP_API_KEY)
        var secret= RequestBody.create(null,Config.FACE_PP_API_SECRET)
        var return_landmark = RequestBody.create(null, "1")
        var attributes="gender,age,smiling,headpose,facequality,blur,eyestatus,emotion,ethnicity,beauty,mouthstatus,eyegaze,skinstatus"
        var return_attributes= RequestBody.create(null,attributes)
        NetWorks.facePPdetectApi(Config.facePP_Detect,key,secret,return_landmark,return_attributes,requestImgPart,aa)




    }

}