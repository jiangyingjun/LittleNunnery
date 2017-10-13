package com.shuai

import com.jude.beam.bijection.Presenter
import com.jude.beam.expansion.BeamBaseActivity
import com.shuai.model.dao.User
import com.shuai.model.gen.GreenDaoManager
import com.shuai.model.gen.UserDao

/**
 * Created by jiangyingjun on 2017/10/9.
 */

class Test<T : Presenter<*>> : BeamBaseActivity<T>() {


    fun fun1() {


        val userDao = GreenDaoManager.getInstance().daoSession.userDao


        val user = User()
        user.age = 11
        user.name = "aaaa"
        userDao.insert(user)

    }

}
