package com.alin.commonlib.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;


/**
 * @author vondear
 * @date 2018/06/11 14:20:10
 * 在系统的Toast基础上封装
 */

@SuppressLint("InflateParams")
public class Ts {

    /**
     * Toast 替代方法 ：立即显示无需等待
     */
    private static Toast mToast;

    /**
     * 封装了Toast的方法 :需要等待
     *
     * @param context Context
     * @param str     要显示的字符串
     * @param isLong  Toast.LENGTH_LONG / Toast.LENGTH_SHORT
     */
    public static void showToast(Context context, String str, boolean isLong) {
        if (isLong) {
            Toast.makeText(context, str, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
        }
    }

    public static Toast getToast() {
        if (null == mToast) {
            mToast = new Toast(AppLauncherUtils.getContext());
        }
        return mToast;
    }


    public static void showToastNoShow(String msg) {
        showToastNoShow(msg, Toast.LENGTH_SHORT);
    }

    public static void showToastNoShow(String msg, int duration) {
        if (mToast == null) {
            mToast = Toast.makeText(AppLauncherUtils.getContext(), msg, duration);
        } else {
            if (mToast.getView() != null) {
                mToast.cancel();
                mToast = null;
            }
            mToast = Toast.makeText(AppLauncherUtils.getContext(), msg, duration);
            mToast.setText(msg);
        }
    }

    public static void showLocalToast(int resId) {
        showToastNoShow("【" + AppLauncherUtils.getContext().getString(resId) + "】");
        mToast.show();
    }

    public static void showLocalToast(String msg) {
        showToastNoShow("【" + msg + "】");
        mToast.show();
    }

    public static void showLocalCenterToast(int resId) {
        showLocalCenterToast(AppLauncherUtils.getContext().getString(resId));
    }

    public static void showLocalCenterToast(String msg) {
        showCenterToast("【" + msg + "】");
    }

    public static void showCenterToast(int resId) {
        showCenterToast(AppLauncherUtils.getContext().getString(resId));
    }

    public static void showCenterToast(String msg) {
        showToastNoShow(msg);
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.show();
    }

    public static void showToastWithViewAndGravity(View view, int gravity) {
        showToastNoShow("");
        mToast.setGravity(gravity, 0, 60);
        mToast.setView(view);
        mToast.show();
    }
}
