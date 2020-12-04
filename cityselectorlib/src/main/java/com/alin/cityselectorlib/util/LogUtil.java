package com.alin.cityselectorlib.util;

import android.util.Log;

import com.alin.cityselectorlib.BuildConfig;


/**
 * Created by justin on 17/9/11.
 * Log工具类，对系统的Log进行了封装，并加入了system.out和文件的输出方式。
 */
public class LogUtil {

    public static final int DEBUG = 0;
    public static final int INFO = 1;
    public static final int ERROR = 2;

    public static void initLog() {
        stcLevel = PackageUtils.isProductionEnviron() ? stcLevel = 10 : DEBUG;
    }


    /**
     * 日志级别
     */
    public static int stcLevel = DEBUG;

    /**
     * 文件输出开关
     */
    public static boolean file_toggle = false;

    /**
     * Console输出开关
     */
    public static boolean sysprint_toggle = false;

    /**
     * 日志中是否打印线程信息
     */
    public static boolean thread_toggle = false;

    /**
     * 预留入口，可以在程序内部建立调用
     *
     * @param level
     */
    public static void setLevel(int level) {
        stcLevel = level;
    }

    /**
     * 预留入口，可以在程序内部建立调用
     *
     * @param fileToggle
     * @param sysprintToggle
     */
    public static void setLogToggle(boolean fileToggle, boolean sysprintToggle) {
        file_toggle = fileToggle;
        sysprint_toggle = sysprintToggle;
    }

    public static void i(String tag, String content) {
        if (stcLevel <= INFO) {
            if (BuildConfig.DEBUG) {
                if (content.length() < 3500) {
                    Log.i(tag, content);
                    return;
                }
                int max_str_length = 2001 - tag.length();
                //大于4000时
                while (content.length() > max_str_length) {
                    Log.i(tag, content.substring(0, max_str_length));
                    content = content.substring(max_str_length);
                }
            }
            //剩余部分
            Log.i(tag, content);
        }
    }

    public static void i(Object c, String tag, String content) {
        if (stcLevel <= INFO) {
            Log.i(tag, "[" + c.getClass().getSimpleName() + "]" + content);
        }
    }

    public static void i(String tag, String format, Object... args) {
        if (stcLevel <= INFO) {
            String content = String.format(format, args);
            Log.i(tag, thread_toggle ? ("[" + getCurrentThreadName() + "]" + content) : content);
        }
    }

    public static void d(String tag, String content) {
        if (stcLevel <= DEBUG) {
            Log.d(tag, thread_toggle ? ("[" + getCurrentThreadName() + "]" + content) : content);
        }
    }

    public static void e(String tag, String content) {
        if (stcLevel <= ERROR) {
            Log.e(tag, thread_toggle ? ("[" + getCurrentThreadName() + "]" + content) : content);
        }
    }

    public static void e(String tag, String content, Throwable e) {
        if (stcLevel <= ERROR) {
            Log.e(tag, thread_toggle ? ("[" + getCurrentThreadName() + "]" + content) : content, e);
        }
    }

    public static void print(Object content) {
        if (sysprint_toggle) {
            System.out.print(thread_toggle ? ("[" + getCurrentThreadName() + "]" + content) : content);
        }
    }

    public static void println(Object content) {
        if (sysprint_toggle) {
            System.out.println(thread_toggle ? ("[" + getCurrentThreadName() + "]" + content) : content);
        }
    }

    private static String getCurrentThreadName() {
        Thread thread = Thread.currentThread();
        return thread == null ? "" : thread.getName();
    }
}
