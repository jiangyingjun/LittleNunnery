package com.shuai.face

import android.os.Bundle
import android.view.View
import android.widget.Button
import com.jude.beam.bijection.RequiresPresenter
import com.shuai.R
import com.shuai.base.BaseActivity
import kotlinx.android.synthetic.main.activity_facepp.*

/**
 * Created by jiangyingjun on 2017/9/20.
 */
@RequiresPresenter(FacePPPresenter::class)
class FacePPActivity :BaseActivity<FacePPPresenter>(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facepp)

        initView()
        initData()

    }



     var v_btn :Button?=null

    private fun initData() {


    }

     fun initView() {
        v_btn =facepp_btn
         facepp_btn.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.facepp_btn->{

          presenter.detectFace()


            }
        }



    }



}