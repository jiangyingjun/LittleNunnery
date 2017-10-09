package com.shuai.model.gen;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.internal.DaoConfig;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by admin on 2016/8/15.
 */
public final class MigrationHelper {
    public MigrationHelper() {
    }

    public static void migrate(Database db, Class... daoClasses) {
        generateTempTables(db, daoClasses);
        dropAllTables(db, true, daoClasses);
        createAllTables(db, false, daoClasses);
        restoreData(db, daoClasses);
    }

    private static void generateTempTables(Database db, Class... daoClasses) {
        for(int i = 0; i < daoClasses.length; ++i) {
            DaoConfig daoConfig = new DaoConfig(db, daoClasses[i]);
            String tableName = daoConfig.tablename;
            String tempTableName = daoConfig.tablename.concat("_TEMP");
            StringBuilder insertTableStringBuilder = new StringBuilder();
            insertTableStringBuilder.append("CREATE TEMPORARY TABLE ").append(tempTableName);
            insertTableStringBuilder.append(" AS SELECT * FROM ").append(tableName).append(";");
            db.execSQL(insertTableStringBuilder.toString());
        }

    }

    private static void dropAllTables(Database db, boolean ifExists, @NonNull Class... daoClasses) {
        reflectMethod(db, "dropTable", ifExists, daoClasses);
    }

    private static void createAllTables(Database db, boolean ifNotExists, @NonNull Class... daoClasses) {
        reflectMethod(db, "createTable", ifNotExists, daoClasses);
    }

    private static void reflectMethod(Database db, String methodName, boolean isExists, @NonNull Class... daoClasses) {
        if(daoClasses.length >= 1) {
            try {
                Class[] e = daoClasses;
                int len$ = daoClasses.length;

                for(int i$ = 0; i$ < len$; ++i$) {
                    Class cls = e[i$];
                    Method method = cls.getDeclaredMethod(methodName, new Class[]{Database.class, Boolean.TYPE});
                    method.invoke((Object)null, new Object[]{db, Boolean.valueOf(isExists)});
                }
            } catch (NoSuchMethodException var9) {
                var9.printStackTrace();
            } catch (InvocationTargetException var10) {
                var10.printStackTrace();
            } catch (IllegalAccessException var11) {
                var11.printStackTrace();
            }

        }
    }

    private static void restoreData(Database db, Class... daoClasses) {
        for(int i = 0; i < daoClasses.length; ++i) {
            DaoConfig daoConfig = new DaoConfig(db, daoClasses[i]);
            String tableName = daoConfig.tablename;
            String tempTableName = daoConfig.tablename.concat("_TEMP");
            List columns = getColumns(db, tempTableName);
            ArrayList properties = new ArrayList(columns.size());

            for(int dropTableStringBuilder = 0; dropTableStringBuilder < daoConfig.properties.length; ++dropTableStringBuilder) {
                String insertTableStringBuilder = daoConfig.properties[dropTableStringBuilder].columnName;
                if(columns.contains(insertTableStringBuilder)) {
                    properties.add(insertTableStringBuilder);
                }
            }

            if(properties.size() > 0) {
                String var10 = TextUtils.join(",", properties);
                StringBuilder var11 = new StringBuilder();
                var11.append("INSERT INTO ").append(tableName).append(" (");
                var11.append(var10);
                var11.append(") SELECT ");
                var11.append(var10);
                var11.append(" FROM ").append(tempTableName).append(";");
                db.execSQL(var11.toString());
            }

            StringBuilder var12 = new StringBuilder();
            var12.append("DROP TABLE ").append(tempTableName);
            db.execSQL(var12.toString());
        }

    }

    private static List<String> getColumns(Database db, String tableName) {
        Object columns = null;
        Cursor cursor = null;

        try {
            cursor = db.rawQuery("SELECT * FROM " + tableName + " limit 0", (String[])null);
            if(null != cursor && cursor.getColumnCount() > 0) {
                columns = Arrays.asList(cursor.getColumnNames());
            }
        } catch (Exception var8) {
            var8.printStackTrace();
        } finally {
            if(cursor != null) {
                cursor.close();
            }

            if(null == columns) {
                columns = new ArrayList();
            }

        }

        return (List)columns;
    }
}