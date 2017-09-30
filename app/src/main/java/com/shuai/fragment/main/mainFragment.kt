package com.shuai.fragment.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.shuai.R
import com.shuai.adapter.main.MainFragmentAdapter
import com.shuai.adapter.main.mainFragment.RecyclerViewAdapter
import com.shuai.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_main.*
/**
 * Created by jiangyingjun on 2017/6/5.
 */
class  mainFragment: BaseFragment() {

    private  var rootView:View?=null
    private  var fragment_main_recycler:RecyclerView?=null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        rootView=inflater!!.inflate(R.layout.fragment_main,null)

        initView(rootView)
        return rootView

    }

    private fun initView(view:View?){
        fragment_main_recycler=view!!.findViewById(R.id.fragment_main_recycler) as RecyclerView

        var list=ArrayList<String>()

        list.add("自由行")
        list.add("商务邮轮")
        list.add("亲子游")
        list.add("单人行")
        list.add("邮轮")
        list.add("定制")
        list.add("奇迹")
        list.add("国内跟团")
        list.add("自由行")
        list.add("商务邮轮")
        list.add("亲子游")
        list.add("单人行")
        list.add("邮轮")
        list.add("定制")
        list.add("奇迹")
        list.add("国内跟团")
        list.add("自由行")
        list.add("商务邮轮")
        list.add("亲子游")
        list.add("单人行")
        list.add("邮轮")
        list.add("定制")
        list.add("奇迹")
        list.add("国内跟团")
        list.add("自由行")
        list.add("商务邮轮")
        list.add("亲子游")
        list.add("单人行")
        list.add("邮轮")
        list.add("定制")
        list.add("奇迹")
        list.add("国内跟团")
        list.add("自由行")
        list.add("商务邮轮")
        list.add("亲子游")
        list.add("单人行")
        list.add("邮轮")
        list.add("定制")
        list.add("奇迹")
        list.add("国内跟团")
        list.add("自由行")
        list.add("商务邮轮")
        list.add("亲子游")
        list.add("单人行")
        list.add("邮轮")
        list.add("定制")
        list.add("奇迹")
        list.add("国内跟团")
        var adapter =RecyclerViewAdapter(fragment_main_recycler!!,activity,list)

        fragment_main_recycler!!.setOnClickListener { Toast.makeText(this@mainFragment.context,"click",Toast.LENGTH_SHORT).show() }
        var gridlayoutManager=GridLayoutManager(activity,2,GridLayoutManager.VERTICAL,false)
        var linearlayoutManager=LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        fragment_main_recycler!!.layoutManager=gridlayoutManager
        fragment_main_recycler!!.adapter=adapter







    }



}