package com.shuai.utils.glide;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.Headers;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.stream.BaseGlideUrlLoader;
import com.bumptech.glide.load.model.stream.StreamModelLoader;
import com.bumptech.glide.module.GlideModule;
import com.bumptech.glide.request.target.ViewTarget;
import com.shuai.R;
import com.shuai.utils.Config;


import java.io.InputStream;

/**
 * Author:    郝伟伟
 * Version：  5.4.6
 * Date:     2016.06.23
 * Description:设置Glide的header或者设置Glide相关的的一些列东西，是全局性的
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * Why & What is modified:
 */
public class GlideSetup implements GlideModule{
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
    /* no costumization */
        builder.setDecodeFormat(DecodeFormat.PREFER_RGB_565);
        ViewTarget.setTagId(R.id.glide_tag_id);

        int cacheSize100MegaBytes = 104857600;
        builder.setDiskCache(
                new InternalCacheDiskCacheFactory(context, cacheSize100MegaBytes)
        );

        MemorySizeCalculator calculator = new MemorySizeCalculator(context);
        int defaultMemoryCacheSize = calculator.getMemoryCacheSize();
        int defaultBitmapPoolSize = calculator.getBitmapPoolSize();

        int customMemoryCacheSize = (int) (0.8 * defaultMemoryCacheSize);
        int customBitmapPoolSize = (int) (0.8 * defaultBitmapPoolSize);

        builder.setMemoryCache( new LruResourceCache( customMemoryCacheSize ));
        builder.setBitmapPool( new LruBitmapPool( customBitmapPoolSize ));
    }
    @Override
    public void registerComponents(Context context, Glide glide) {
        glide.register(String.class, InputStream.class, new HeaderedLoader.Factory());
    }

    private static class HeaderedLoader extends BaseGlideUrlLoader<String> {
        public static final Headers HEADERS = new LazyHeaders.Builder()
                .addHeader("User-Agent", Config.Companion.getFORMAT_USER_AGENT())//设置Glide的User-Agent为固定的，用于绕过服务器User-Agent限制，是为了公司内网网络限制而做得设置
                .build();

        public HeaderedLoader(Context context) {
            super(context);
        }

        @Override protected String getUrl(String model, int width, int height) {
            return model;
        }

        @Override protected Headers getHeaders(String model, int width, int height) {
            return HEADERS;
        }

        public static class Factory implements ModelLoaderFactory<String, InputStream> {
            @Override public StreamModelLoader<String> build(Context context, GenericLoaderFactory factories) {
                return new HeaderedLoader(context);
            }
            @Override public void teardown() { /* nothing to free */ }
        }
    }
}
