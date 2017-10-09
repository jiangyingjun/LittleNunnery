package com.shuai;

import com.shuai.model.dao.User;
import com.shuai.model.gen.GreenDaoManager;
import com.shuai.model.gen.UserDao;

/**
 * Created by jiangyingjun on 2017/10/9.
 */

public class Test {


    public  void fun1(){


       UserDao userDao= GreenDaoManager.getInstance().getDaoSession().getUserDao();


        User user = new User();
        user.setAge(11);
        user.setName("aaaa");
        userDao.insert(user);

    }

}
