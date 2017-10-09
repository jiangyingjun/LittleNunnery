package com.shuai.utils.glide;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.shuai.R;


public class GlideImageLoader {

    public static  GlideImageLoader instance;

    private Context mContext;

    public GlideImageLoader(Context context) {
        this.mContext = context;
    }


//    public GlideImageLoader(){}
//
//    public static GlideImageLoader getInstance(){
//
//        if (instance==null){
//
//
//            instance = new GlideImageLoader();
//        }
//
//
//        return instance;
//    }





    /**
     * 加载图片方法
     *
     * @param lodingImagerView
     * @param resourcesId      drawable中的图片id
     */
    public void display(ImageView lodingImagerView, int resourcesId) {//外部接口函数
        Glide.with(mContext)
                .load(resourcesId)
                .animate(android.R.anim.fade_in)  // 自己设置渐现动画可以解决加载图片变形问题
                .into(lodingImagerView);
    }

    /**
     * 加载图片方法
     *
     * @param lodingImagerView
     * @param picUrl
     */
    public void display(ImageView lodingImagerView, String picUrl) {//外部接口函数
        Glide.with(mContext)
                .load(picUrl)
                .animate(android.R.anim.fade_in)  // 自己设置渐现动画可以解决加载图片变形问题
//                .placeholder(R.color.loading_img_default_color)
                .placeholder(R.drawable.default_loading_fail)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(lodingImagerView);
    }

    /**
     * 加载图片方法
     *
     * @param activity
     * @param lodingImagerView
     * @param picUrl
     */
    public void display(Activity activity, ImageView lodingImagerView, String picUrl) {//外部接口函数
        Glide.with(activity)
                .load(picUrl)
                .animate(android.R.anim.fade_in)  // 自己设置渐现动画可以解决加载图片变形问题
//              .placeholder(R.color.loading_img_default_color)
                .placeholder(R.drawable.default_loading_fail)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(lodingImagerView);
    }

    /**
     * 加载图片方法
     *
     * @param activity
     * @param loadingImageView
     * @param picUrl
     * @param listener
     */
     public void display(Activity activity, ImageView loadingImageView,String picUrl,RequestListener listener){
        Glide.with(activity).load(picUrl)
                .animate(android.R.anim.fade_in)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .listener(listener)
                .into(loadingImageView);
    }
    /*为首页加载完广告图后跳转临时加的需求方法
    * 配合360首发活动*/
    public void displays(Activity activity, ImageView loadingImageView,String picUrl,RequestListener listener){
        Glide.with(activity).load(picUrl)
                .animate(android.R.anim.fade_in)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .listener(listener)
                .into(loadingImageView);
    }

    /**
     * 加载图片方法，加载图片为 defaultImg
     *
     * @param lodingImagerView
     * @param picUrl
     * @param defaultImg
     */
    public void display(ImageView lodingImagerView, String picUrl, int defaultImg) {//外部接口函数
        if (defaultImg != 0) {
            Glide.with(mContext)
                    .load(picUrl)
                    .animate(android.R.anim.fade_in)  // 自己设置渐现动画可以解决加载图片变形问题
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .placeholder(defaultImg)
                    .into(lodingImagerView);
        } else {
            Glide.with(mContext)
                    .load(picUrl)
                    .animate(android.R.anim.fade_in)  // 自己设置渐现动画可以解决加载图片变形问题
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                  .placeholder(R.color.loading_img_default_color)
                    .placeholder(R.drawable.default_loading_fail)
                    .into(lodingImagerView);
        }
    }

    /**
     * 加载图片方法，加载图片为 defaultImg
     *
     * @param lodingImagerView
     * @param picUrl
     * @param defaultImg
     */
    public void displayNoType(ImageView lodingImagerView, String picUrl, int defaultImg) {//外部接口函数
        if (defaultImg != 0) {
            Glide.with(mContext)
                    .load(picUrl)
                    .animate(android.R.anim.fade_in)  // 自己设置渐现动画可以解决加载图片变形问题
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(defaultImg)
                    .into(lodingImagerView);
        } else {
            Glide.with(mContext)
                    .load(picUrl)
                    .animate(android.R.anim.fade_in)  // 自己设置渐现动画可以解决加载图片变形问题
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                  .placeholder(R.color.loading_img_default_color)
                    .placeholder(R.drawable.default_loading_fail)
                    .into(lodingImagerView);
        }
    }

    /**
     * 加载图片方法，加载图片为 defaultImg
     *
     * @param activity
     * @param lodingImagerView
     * @param picUrl
     * @param defaultImg
     */
    public void display(Activity activity, ImageView lodingImagerView, String picUrl, int defaultImg) {//外部接口函数
        if (defaultImg != 0) {
            Glide.with(activity)
                    .load(picUrl)
                    .animate(android.R.anim.fade_in)  // 自己设置渐现动画可以解决加载图片变形问题
                    .placeholder(defaultImg)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(lodingImagerView);
        } else {
            Glide.with(activity)
                    .load(picUrl)
                    .animate(android.R.anim.fade_in)  // 自己设置渐现动画可以解决加载图片变形问题
//                  .placeholder(R.color.loading_img_default_color)
                    .placeholder(R.drawable.default_loading_fail)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(lodingImagerView);
        }
    }




    /**
     * 加载图片方法，加载图片为 defaultImg
     *
     * @param lodingImagerView
     * @param picUrl
     * @param defaultImg
     */
    public void displayRound(ImageView lodingImagerView, String picUrl, int defaultImg, int roundSize) {//外部接口函数
        if (defaultImg != 0) {
            Glide.with(mContext)
                    .load(picUrl).asBitmap()
                    .animate(android.R.anim.fade_in)  // 自己设置渐现动画可以解决加载图片变形问题
                    .placeholder(defaultImg)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .transform(new GlideRoundTransform(mContext, roundSize))
                    .into(lodingImagerView);
        } else {
            Glide.with(mContext)
                    .load(picUrl)
                    .animate(android.R.anim.fade_in)  // 自己设置渐现动画可以解决加载图片变形问题
//                  .placeholder(R.color.loading_img_default_color)
                    .placeholder(R.drawable.default_loading_fail)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .transform(new GlideRoundTransform(mContext, roundSize))
                    .into(lodingImagerView);
        }
    }

    public void clearImageCache() {
        new Thread(runnable).start();
        Glide.get(mContext).clearMemory(); // 必须在主线程中执行
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                Glide.get(mContext).clearDiskCache(); // 必须在子线程中执行
            } catch (Exception ex) {
//                LogUtil.e(mContext, ex.toString());
            }
        }
    };

}
