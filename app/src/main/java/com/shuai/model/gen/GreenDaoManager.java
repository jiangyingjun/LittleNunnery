package com.shuai.model.gen;

import com.shuai.tinker.util.SampleApplicationContext;
import com.shuai.utils.Config;

/**
 * Created by jiangyingjun on 2017/10/9.
 */

public class GreenDaoManager {

    private static GreenDaoManager mInstance;

    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    public GreenDaoManager() {

        MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(SampleApplicationContext.context, Config.Companion.getDB_NAME(),null);
        mDaoMaster = new DaoMaster(mySQLiteOpenHelper.getWritableDb());
        mDaoSession = mDaoMaster.newSession();



    }


    public static GreenDaoManager getInstance() {
        if (mInstance == null) {
            synchronized (GreenDaoManager.class) {
                if (mInstance == null) {
                    mInstance = new GreenDaoManager();
                }
            }
        }
        return mInstance;
    }


    public DaoMaster getDaoMaster() {
        return mDaoMaster;
    }

    public DaoSession getDaoSession() {
        return mDaoMaster.newSession();
    }


}
