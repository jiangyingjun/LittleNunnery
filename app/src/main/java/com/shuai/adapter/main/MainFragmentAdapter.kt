package com.shuai.adapter.main

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup

/**
 * Created by jiangyingjun on 2017/6/5.
 */
class MainFragmentAdapter(var mContext: Context, var mList:List<Fragment>, fm: FragmentManager?) : FragmentPagerAdapter(fm) {



    override fun getItem(position: Int): Fragment {
        return  mList.get(position)
    }




    override fun getCount(): Int {
      return mList.size
    }


}