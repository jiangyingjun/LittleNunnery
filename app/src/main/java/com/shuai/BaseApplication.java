package com.shuai;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.shuai.tinker.util.SampleApplicationContext;
import com.shuai.tinker.util.TinkerManager;
import com.shuai.utils.CrashHandler;
import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.loader.app.DefaultApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;

@SuppressWarnings("unused")
@DefaultLifeCycle(application = "com.shuai.SampleApplication",
        flags = ShareConstants.TINKER_ENABLE_ALL,loadVerifyFlag = false)
public class BaseApplication  extends DefaultApplicationLike {

    public BaseApplication(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }


    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
//        MultiDex.install(base);
        SampleApplicationContext.application = this;
        SampleApplicationContext.context = base;

        TinkerManager.setTinkerApplicationLike(this);
        TinkerManager.initFastCrashProtect();
        //should set before tinker is installed
        TinkerManager.setUpgradeRetryEnable(true);

        //optional set logIml, or you can use default debug log
        // TinkerInstaller.setLogIml(new MyLogImp());

        //installTinker after load multiDex
        //or you can put com.tencent.tinker.** to main dex
        TinkerManager.installTinker(this);

        Tinker tinker = Tinker.with(getApplication());



    }


    @Override
    public void onCreate() {
        super.onCreate();

//        if (LeakCanary.isInAnalyzerProcess(getApplication())) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        refWatcher = LeakCanary.install(getApplication());
//        GreenDaoManager.getInstance();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplication());
    }

//    public static RefWatcher getRefWatcher(BaseApplication application) {
//        return application.refWatcher;
//    }
//
//    private RefWatcher refWatcher;







}
