package com.shuai.adapter.main.mainFragment

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.shuai.R
import com.shuai.view.recyclerview.LoadMoreAdapter

/**
 * Created by jiangyingjun on 2017/6/12.
 */
 class RecyclerViewAdapter: LoadMoreAdapter  {

    var mData=ArrayList<String>()
    constructor(view:RecyclerView,context: Context,mData: ArrayList<String>):super(context,view){
        this.mData=mData
    }


    override fun onCreateNormalViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return ViewHoler(LayoutInflater.from(mContext).inflate(R.layout.mainfragment_recyclerview_item,null))
    }

    override fun onBindNormalViewHolder(holders: RecyclerView.ViewHolder, position: Int) {
       var  holder =holders as ViewHoler
        if (flag==position) holder!!.item_layolut.isSelected=true else holder!!.item_layolut.isSelected=false

        holder!!.item_text!!.text=mData.get(position)

        if(holder!!.item_layolut!!.isSelected){
            holder!!.item_text.setTextColor(R.color.green)
            holder!!.item_line.visibility=View.VISIBLE

        }else{
            holder!!.item_text.setTextColor(R.color.colorPrimaryDark)
            holder!!.item_line.visibility=View.INVISIBLE

        }
        holder!!.item_layolut.setOnClickListener {
            flag=position
            notifyDataSetChanged()
        }
    }


    private var  flag =0


    override fun getItemCount(): Int {
      return  mData.size+1
    }



   class ViewHoler(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        val item_text=itemView!!.findViewById(R.id.item_text)as TextView

        val item_layolut=itemView!!.findViewById(R.id.item_layout)as LinearLayout


       val  item_line=itemView!!.findViewById(R.id.item_line)
    }
}