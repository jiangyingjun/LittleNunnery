package com.shuai

import android.util.Log
import com.jude.beam.bijection.Presenter
import com.jude.beam.expansion.BeamBaseActivity
import com.shuai.model.dao.User
import com.shuai.model.gen.GreenDaoManager
import com.shuai.model.gen.UserDao

/**
 * Created by jiangyingjun on 2017/10/9.
 */

class Test{


    fun fun1() {


        val userDao = GreenDaoManager.getInstance().daoSession.userDao


        val user = User()
        user.id=10
        user.age = 11
        user.name = "33433"
       var result= userDao.insert(user)

        userDao.deleteAll()
       Log.e("jyj-->",result.toString())




        /***
         *
         * greendao 1用来干嘛的  2优点是啥
         *
         *
         *
         *
         * */







    }

}
