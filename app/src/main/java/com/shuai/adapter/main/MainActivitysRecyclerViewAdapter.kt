package com.shuai.adapter.main

import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.shuai.R
import com.shuai.face.FacePPActivity
import com.shuai.face.FaceppActionActivity
import com.shuai.utils.glide.GlideImageLoader

/**
 * Created by jiangyingjun on 2017/9/26.
 */
class MainActivitysRecyclerViewAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    var mData=ArrayList<String>()
     var mContext:Context?=null


    val  NORMAL_VIEW=1
    val  FOOTER_VIEW=2

    var imageLoader:GlideImageLoader?=null
    constructor(context: Context, list: ArrayList<String>) : this() {

        this.mContext=context
        this.mData=list
        LayoutInflater.from(context)

        imageLoader=  GlideImageLoader(context)
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
            imageLoader!!.display(holder.item_img, "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1507530011&di=bfb2fd33b8eca2f3713b363d9a7b4de8&src=http://image.uczzd.cn/16729767241092375647.jpeg?id=0&from=export")

        }

    }


    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        val item_card=itemView!!.findViewById(R.id.item_mains_card) as CardView

        val item_text=itemView!!.findViewById(R.id.item_mains_text) as TextView

        val item_img=itemView!!.findViewById(R.id.item_mians_img) as ImageView
    }


    class FooterViewHolder (itemView: View?):RecyclerView.ViewHolder(itemView){


        val item_text=itemView!!.findViewById(R.id.textView) as TextView

    }

}