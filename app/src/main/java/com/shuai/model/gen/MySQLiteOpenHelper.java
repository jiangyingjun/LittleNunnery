package com.shuai.model.gen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.database.Database;


/**
 *   数据库的升级帮助类
 *
 * Modification History:
 * Date          Author     Version    Description
 * -----------------------------------------------------------
 * 2016-8-18    mafushun      5.4.8      1.0
 * ------------------------
 * Why & What is modified:
 *    新建的表，需要在MigrationHelper.migrate 添加xxxDao.class
 */
public class MySQLiteOpenHelper extends DaoMaster.OpenHelper {
    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);

    }

    //数据库升级：在已有表中增加新字段的时候，需要升级,添加表，需要升级。
    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        //数据库不升级
        if (newVersion <= oldVersion) {
            return;
        }
        //数据库升级，如果包含新建的表，则需要先创建一下才可以进行升级，
        DaoMaster.createAllTables(db, true);
        //新建的表需要，在Migrate的添加xxxDao.class
//        MigrationHelper.migrate(db,
////                MyUzaiContentCacheDao.class,
////                UpdateInfoDao.class
//
//                );
    }
}
