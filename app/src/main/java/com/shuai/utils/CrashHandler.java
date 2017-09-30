package com.shuai.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;

import com.shuai.BuildConfig;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 未处理异常捕获报告
 *
 * @author songdy
 */
public class CrashHandler implements UncaughtExceptionHandler {


    // 系统默认的UncaughtException处理类
    private UncaughtExceptionHandler mDefaultHandler;
    // CrashHandler实例
    private static CrashHandler INSTANCE = new CrashHandler();
    // 程序的Context对象
    private Context mContext;
    // 用来存储设备信息和异常信息
    private Map<String, String> infos = new HashMap<>();

    // 用于格式化日期,作为日志文件名的一部分
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

    /**
     * 保证只有一个CrashHandler实例
     */
    private CrashHandler() {
    }

    /**
     * 获取CrashHandler实例 ,单例模式
     */
    public static CrashHandler getInstance() {
        return INSTANCE;
    }

    /**
     * 初始化
     *
     * @param context
     */
    public void init(Context context) {
        mContext = context.getApplicationContext();
        // 获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        // 设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
//        Log.e(mContext, "Capture the Exception!!!!");
        /*程序调试时候不发送异常信息到服务端*/

        if (handleException(ex) && mDefaultHandler != null) {
            // 1.获取当前程序的版本号. 版本的id
            String versioninfo = getVersionInfo();
            // 2.获取手机的硬件信息.
            String mobileInfo = getMobileInfo();
            // 3.把错误的堆栈信息 获取出来
            String errorinfo = getErrorInfo(ex);


            // 4.把所有的信息 还有信息对应的时间 提交到服务器  Const.DOWNLOAD_PLATFORM_NAME[Arrays.asList(Const.DOWNLOAD_PLATFORM_ID).indexOf(CommReqFieldValuePackag.FROM)]


            String info = versioninfo + "\n"
//                    + getPixels() + "\n"
                    + mobileInfo + "\n"
//                    + ApplicationValue.getInstance().requestJsonStr + "\n\n"
//                    + ApplicationValue.getInstance().receiveJsonStr + "\n\n"
                    + errorinfo;

            Log.e(mContext.toString(), "Info=>>>>> " + errorinfo);

            if (BuildConfig.isSaveCrashLog){
                if (FileUtils.isSdcardExist()) {
                    String sdPath = Environment.getExternalStorageDirectory() + "/";
                    String mSavePath = sdPath + Config.Companion.getDIRCETOYR();
                    FileUtils.createDirFile(mSavePath);
                    File txtFile = FileUtils.createNewFile(mSavePath + "/" + "crash_log"+DateUtil.getFormatTime(System.currentTimeMillis())+".txt");
                    try {
                        FileOutputStream fos = new FileOutputStream(txtFile);
                        fos.write(FileUtils.strToByteArray(info));
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            // 退出程序
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        } else {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Log.e(mContext.toString(), "error : " + e.getMessage());
            }
            // 退出程序
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }


//		}else{
//			// 如果用户没有处理则让系统默认的异常处理器来处理
//			 mDefaultHandler.uncaughtException(thread, ex);
//		}
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false.
     */
    private boolean handleException(final Throwable ex) {
        if (ex == null) {
            return false;
        }
        // 使用Toast来显示异常信息
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Looper.loop();

            }
        }.start();
        // 收集设备参数信息
//		collectDeviceInfo(mContext);
        // 保存日志文件
//		saveCrashInfo2File(ex);
        return true;
    }

    /**
     * 收集设备参数信息
     *
     * @param ctx
     */
    public void collectDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(),
                    PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null"
                        : pi.versionName;
                String versionCode = pi.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }
        } catch (NameNotFoundException e) {
            Log.i(mContext.toString(), "an error occured when collect package info==>>>"
                    + e.getMessage());
        }

        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
                Log.i(mContext.toString(), field.getName() + " : " + field.get(null));
            } catch (Exception e) {
                Log.i(mContext.toString(), "an error occured when collect crash info==>>>"
                        + e.getMessage());
            }
        }
    }

    /**
     * 获取错误的信息
     *
     * @param arg1
     * @return
     */
    private String getErrorInfo(Throwable arg1) {
        Writer writer = new StringWriter();
        PrintWriter pw = new PrintWriter(writer);
        arg1.printStackTrace(pw);
        pw.close();
        return writer.toString();
    }

    /**
     * 获取手机的版本信息
     *
     * @return
     */
    public String getVersionInfo() {
        String ver = "未知版本号";
        try {
            PackageManager pm = mContext.getPackageManager();
            PackageInfo info = pm.getPackageInfo(mContext.getPackageName(), 0);
            ver = "App Android 版本 ：   " + info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ver;
    }

    /**
     * 得到屏幕分辨率
     *
     * @return
     */
//    private String getPixels() {
//        DisplayMetrics displayMetrics = PhoneInfoUtil.getInstance().getDisplayMetrics(mContext);
//        return "手机分辨率： " + displayMetrics.widthPixels + "x" + displayMetrics.heightPixels;
//    }

    /**
     * 获取手机的硬件信息
     *
     * @return
     */
    private String getMobileInfo() {
        StringBuilder sb = new StringBuilder();
        // 通过反射获取系统的硬件信息
        try {
            Field[] fields = Build.class.getDeclaredFields();
            for (Field field : fields) {
                // 暴力反射 ,获取私有的信息
                field.setAccessible(true);
                String name = field.getName();
//				if("MANUFACTURER".equals(name) || "MODEL".equals(name) || "SERIAL".equals(name)){
                String value = field.get(null).toString();
                sb.append(name + "=" + value);
                sb.append("\n");
//				}

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 保存错误信息到文件中
     *
     * @param ex
     * @return 返回文件名称, 便于将文件传送到服务器
     */
    private String saveCrashInfo2File(Throwable ex) {

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
        }

        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);
        try {
            long timestamp = System.currentTimeMillis();
            String time = formatter.format(new Date());
            String fileName = "crash-" + time + "-" + timestamp + ".log";
            if (Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {
                String path = "/sdcard/crash/";
                File dir = new File(path);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(path + fileName);
                fos.write(sb.toString().getBytes());
                fos.close();
            }
            return fileName;
        } catch (Exception e) {
            Log.i(
                    mContext.toString(),
                    "an error occured while writing file...===>>"
                            + e.getMessage());
        }
        return null;
    }

}
