package com.shuai.adapter.main

import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.shuai.R
import com.shuai.face.FacePPActivity
import com.shuai.face.FaceppActionActivity

/**
 * Created by jiangyingjun on 2017/9/26.
 */
class MainActivitysRecyclerViewAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    var mData=ArrayList<String>()
     var mContext:Context?=null


    val  NORMAL_VIEW=1
    val  FOOTER_VIEW=2


    constructor(context: Context, list: ArrayList<String>) : this() {

        this.mContext=context
        this.mData=list
        LayoutInflater.from(context)
    }



    override fun getItemCount(): Int {
        return  mData.size
    }


    override fun getItemViewType(position: Int): Int {

        if (position==30){

            return FOOTER_VIEW
        }else{
            return NORMAL_VIEW
        }
//        return super.getItemViewType(position)
    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int):RecyclerView.ViewHolder {

        if (viewType==FOOTER_VIEW){
            return FooterViewHolder(LayoutInflater.from(mContext).inflate(R.layout.footer_view, parent,false))

        }else {
            return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_mainactivitys_recyclerview, parent,false))
        }


    }


    override fun onBindViewHolder(holders: RecyclerView.ViewHolder?, position: Int) {
        if(position==30){
            var holder=holders as FooterViewHolder

            holder.item_text.text="fasfsaffafasfaf"
        }else{
            var holder=holders as ViewHolder

            holder.item_card.setOnClickListener {

                var intent=Intent(mContext,FaceppActionActivity::class.java)
                mContext!!.startActivity(intent)

            }

            holder.item_text.setOnClickListener{

                Toast.makeText(mContext,"dsfaf",Toast.LENGTH_SHORT).show()

            }

            holder.item_text.text=mData[position]
        }

    }


    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        val item_card=itemView!!.findViewById(R.id.item_mains_card) as CardView

        val item_text=itemView!!.findViewById(R.id.item_mains_text) as TextView

    }


    class FooterViewHolder (itemView: View?):RecyclerView.ViewHolder(itemView){


        val item_text=itemView!!.findViewById(R.id.textView) as TextView

    }

}