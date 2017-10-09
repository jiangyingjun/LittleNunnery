package com.shuai.activity.home

import android.os.Bundle
import android.os.Environment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.Toast
import com.shuai.R
import com.shuai.adapter.main.MainFragmentAdapter
import com.shuai.fragment.main.mainFragment
import com.shuai.fragment.main.myFragment
import com.shuai.utils.Config
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : FragmentActivity(), View.OnClickListener, ViewPager.OnPageChangeListener {



    private var mainAdapter : MainFragmentAdapter?=null

    var fm : FragmentManager?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recursionMakeFolder()

        initView()

    }
    var lists=ArrayList<Int>()
    private fun initView() {

        main_tab_1!!.setOnClickListener (this)
        main_tab_2!!.setOnClickListener (this)
        main_tab_3!!.setOnClickListener (this)
        main_tab_4!!.setOnClickListener (this)
        main_tab_5!!.setOnClickListener (this)
        main_layout_page.overScrollMode= View.OVER_SCROLL_NEVER


        var fragmentList=ArrayList<Fragment>()
            fragmentList.add(mainFragment())
            fragmentList.add(myFragment())
        fm=supportFragmentManager
        mainAdapter= MainFragmentAdapter(this@MainActivity,fragmentList,fm )

       main_layout_page.adapter=mainAdapter
       main_layout_page.setCurrentItem(0)
        tabBackgroundDefault(main_layout_page!!.currentItem)
       main_layout_page!!.addOnPageChangeListener(this)
    }

    override fun onClick(v: View) {

        when(v.id){
            R.id.main_tab_1->{
                main_layout_page.setCurrentItem(0)
                Toast.makeText(this@MainActivity,"tab_1", Toast.LENGTH_SHORT).show()
            }
            R.id.main_tab_2->{
                main_layout_page.setCurrentItem(1)
                Toast.makeText(this@MainActivity,"tab_2", Toast.LENGTH_SHORT).show()
            }
            R.id.main_tab_3->{
                Toast.makeText(this@MainActivity,"tab_3", Toast.LENGTH_SHORT).show()
            }

            R.id.main_tab_4->{
                Toast.makeText(this@MainActivity,"tab_4", Toast.LENGTH_SHORT).show()
            }
            R.id.main_tab_5->{

                Toast.makeText(this@MainActivity,"tab_5", Toast.LENGTH_SHORT).show()
            }
        }
    }




    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
       main_layout_page.setCurrentItem(position)
        for (x in 0..main_tab_layout.childCount-1){
            main_tab_layout!!.getChildAt(x).isSelected=false
        }
        tabBackgroundDefault(position)
    }

    private fun  tabBackgroundDefault(position: Int){

        when(position){
            0->{
                main_tab_1.isSelected=true
            }
            1->{
                main_tab_2.isSelected=true
            }
            2->{
                main_tab_3.isSelected=true
            }
            3->{
                main_tab_4.isSelected=true
            }
            4->{
                main_tab_5.isSelected=true
            }

        }



    }




    /*测试 递归生成文件夹*/
    private fun recursionMakeFolder(){

        var path= Environment.getExternalStorageDirectory().absolutePath

        var dir= File(path+ File.separator+ Config.DIRCETOYR)
        if(!dir.exists()){
          var result= dir.mkdirs()
//            Log.e("jyj-->",result.toString())
        }

        var recursioinPath="https://mbuy.uzai.com/hybrid/booking/bankdiscount.html"
        if(recursioinPath.contains("https://")){
            recursioinPath=recursioinPath.replace("https://","")
        }
        var resultPath= recursioinPath.split("/")
        var filePath=path+ File.separator+ Config.DIRCETOYR
        for (x in 0..resultPath.size-1){
            if(x!=resultPath.size-1){
                filePath=  filePath+ File.separator +resultPath[x]
            }

//            Log.e("jyj-->",resultPath[x])
        }
//        Log.e("jyj-->",filePath)
        var makeFile= File(filePath)
        if (!makeFile.exists()){
         var result=  makeFile.mkdirs()
//            Log.e("jyj-->",result.toString())
        }
    }




}


