package com.shuai.utils

import java.io.File

/**
 * Created by jiangyingjun on 2017/6/8.
 */
object FileUtil{



        /*创建文件夹*/
        fun CreateDirFile(filePath :String): Boolean{
            val result:Boolean
            var file= File(filePath)

                if(!file.exists()){
                    result= file.mkdirs()
                }else{
                    result = false

                }
            return result

        }






}