package com.shuai

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.view.animation.OvershootInterpolator
import android.widget.ImageView
import com.shuai.utils.ViewHelper
import com.shuai.view.recyclerview.LoadMoreAdapter

/**
 * Created by jiangyingjun on 2017/7/26.
 */
class RecyclerViewDataAdapter : LoadMoreAdapter {



     var  mData=ArrayList<String>()
     var inflater:LayoutInflater?=null
     var mLastPosition=-1
    constructor(context: Context,view:RecyclerView, list: ArrayList<String>) : super(context,view) {
        this.mData=list
        inflater= LayoutInflater.from(context)
    }

//    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
//
//        holder!!.iv!!.setImageResource(R.drawable.a2)
//    }

    override fun getItemCount(): Int {
       return mData.size
    }


    override fun onCreateNormalViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(inflater!!.inflate(R.layout.item,null,false))
    }

    override fun onBindNormalViewHolder(holders: RecyclerView.ViewHolder, position: Int) {
            var holder=holders as ViewHolder
            holder.iv!!.setImageResource(R.drawable.a2)

        var  adapterPosition=holder.adapterPosition
        if (adapterPosition<mLastPosition){
            val scaleX = ObjectAnimator.ofFloat(holder.iv, "scaleX", 0.4f, 1f)
            val scaleY = ObjectAnimator.ofFloat(holder.iv, "scaleY", 0.4f, 1f)
            var  anim=AnimatorSet()
            anim.playTogether(scaleX,scaleY)
            anim.duration=200
            anim.interpolator=OvershootInterpolator()
            anim.start()
            mLastPosition = adapterPosition
        }else{
            ViewHelper.clearAnimation(holder.itemView)
        }


    }

//    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
//        return ViewHolder(inflater!!.inflate(R.layout.item,null,false))
//    }


    class  ViewHolder : RecyclerView.ViewHolder {

        var iv:ImageView?=null

        constructor(view: View):super(view){

            iv= view.findViewById(R.id.imgs) as ImageView?
        }
    }




}