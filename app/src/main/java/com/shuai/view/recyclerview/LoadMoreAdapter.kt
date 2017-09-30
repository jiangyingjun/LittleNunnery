package com.shuai.view.recyclerview

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.shuai.R

/**
 * Created by jiangyingjun on 2017/7/26.
 */
public abstract  class  LoadMoreAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder> {


    companion object{

        //正常条目
        val TYPE_NORMAL_ITEM = 0;
        //加载条目
        val TYPE_LOADING_ITEM = 1;
    }

    var mContext:Context?=null
    private var mStaggeredGridLayoutManager: StaggeredGridLayoutManager? = null

    var mFooterViewHolder:FootViewHolder?=null
    constructor(context: Context ,recyclerView: RecyclerView){
        this.mContext=context
        setSpanCount(recyclerView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
       var type= getItemViewType(position)
        if (type==TYPE_NORMAL_ITEM){
            onBindNormalViewHolder(holder!!,position)
        }else{
            (holder as FootViewHolder).tv!!.setOnClickListener {

                Toast.makeText(mContext,"loadmore",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
       if (viewType== TYPE_NORMAL_ITEM){
           return onCreateNormalViewHolder(parent,viewType)
       }else {
           mFooterViewHolder=FootViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.footer_view,parent,false))
           return mFooterViewHolder as FootViewHolder
       }
    }

    override fun getItemViewType(position: Int): Int {
        if (itemCount == position + 1) {
            return TYPE_LOADING_ITEM
        } else {
            return TYPE_NORMAL_ITEM
        }
    }

    abstract fun onCreateNormalViewHolder (parent: ViewGroup?,viewType: Int):RecyclerView.ViewHolder

    abstract fun onBindNormalViewHolder (holder:RecyclerView.ViewHolder,position: Int)


    fun setFooterText(text:CharSequence){

        mFooterViewHolder!!.tv!!.text=text

    }





    class FootViewHolder  :RecyclerView.ViewHolder{
       var  tv:TextView?=null
        constructor(view:View):super(view){
            tv=view.findViewById(R.id.textView) as TextView
        }
    }


    /**
     * 设置每个条目占用的列数

     * @param recyclerView recycleView
     */
    private fun setSpanCount(recyclerView: RecyclerView) {

        var layoutManager = recyclerView.layoutManager
        if (layoutManager is GridLayoutManager) {
            val gridLayoutManager = layoutManager
            gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    var type = getItemViewType(position)
                    if (type == TYPE_NORMAL_ITEM) {
                        return 1
                    } else {
                        return gridLayoutManager.spanCount
                    }
                }
            }
        }

        if (layoutManager is StaggeredGridLayoutManager) {
            mStaggeredGridLayoutManager = layoutManager
        }
    }


}