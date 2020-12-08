package com.alin.commonlib.util;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

/**
 * @author：dxl
 * @email：sddxl2368@gmail.com
 * @date： 2019/12/4
 * @description <Application管理工具类>
 */
public class AppLauncherUtils {
    private static Context context;
    private static AppLauncherUtils install;
    public static Handler mMainThreadHandler;
    public static Handler mThreadHandler;
    private static final String TAG = AppLauncherUtils.class.getSimpleName();

    private AppLauncherUtils(Context context) {
        this.context = context;
    }

    public static Context getContext(){
        return context;
    }
    public static Handler getmMainThreadHandler(){return mMainThreadHandler;}

    public static void init(Context context){
        if (null ==install){
            synchronized (AppLauncherUtils.class){
                if (null == install){
                    install = new AppLauncherUtils(context);
                    mMainThreadHandler = new Handler(Looper.getMainLooper());
                    HandlerThread thread = new HandlerThread(TAG, android.os.Process.THREAD_PRIORITY_BACKGROUND);
                    thread.start();
                    mThreadHandler = new Handler(thread.getLooper());
                }
            }
        }
    }
}
