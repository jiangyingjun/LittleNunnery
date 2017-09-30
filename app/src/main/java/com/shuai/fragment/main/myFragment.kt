package com.shuai.fragment.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shuai.R
import com.shuai.base.BaseFragment

/**
 * Created by jiangyingjun on 2017/6/7.
 */
class  myFragment :BaseFragment(){

    private var rootView:View?=null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        rootView = inflater!!.inflate(R.layout.fragment_my, null)
        return rootView


    }

}