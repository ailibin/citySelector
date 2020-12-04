package com.alin.cityselectorlib.base

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity

/**
 *
 * @Description:
 * @Author:         ailibin
 * @CreateDate:     2020/5/4 9:47
 */

abstract class BaseKtActivity: AppCompatActivity(){

    //避免toast弹出多个,界面半天之后都没有消失
    private var mToast: Toast? = null
    fun Activity.toast(message: CharSequence) {
        if (mToast == null) {
            mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT)
        }
        mToast?.duration = Toast.LENGTH_SHORT
        mToast?.setText(message.toString())
        mToast?.show()
    }

    fun Activity.toast(@StringRes messageRes: Int) {
        if (mToast == null) {
            mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT)
        }
        mToast?.duration = Toast.LENGTH_SHORT
        mToast?.setText(messageRes)
        mToast?.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        ContentViewUtils.inject(this)
        setContentLayout()
    }

    open fun setContentLayout() {
        setContentView(getLayoutId())
        initData()
        initView()
    }

    abstract fun getLayoutId(): Int

    abstract fun initView()

    open fun initData() {

    }




}