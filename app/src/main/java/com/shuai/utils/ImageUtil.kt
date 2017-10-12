package com.shuai.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream
import java.io.IOException

/**
 * Created by jiangyingjun on 2017/10/12.
 */

class ImageUtil{

    private fun getBitmapByte(bitmap:Bitmap): ByteArray {
        val out = ByteArrayOutputStream()
        //参数1转换类型，参数2压缩质量，参数3字节流资源
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
        try {
            out.flush()
            out.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        var  byte= Base64.encode(out.toByteArray(), Base64.DEFAULT)

        return byte

//        return out.toByteArray()
    }


    private fun getBitmapFromByte(byte: ByteArray):Bitmap{

        var byte= Base64.decode(byte, Base64.DEFAULT)

        return BitmapFactory.decodeByteArray(byte,0,byte.size)
    }

}
