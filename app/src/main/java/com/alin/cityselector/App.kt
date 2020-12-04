package com.alin.cityselector

import androidx.multidex.MultiDexApplication
import com.alibaba.android.arouter.launcher.ARouter

/**
 *
 * @ProjectName:    Cityselector
 * @Package:        com.alin.cityselector
 * @ClassName:      App
 * @Description:    application
 * @Author:         ailibin
 * @CreateDate:     2020/11/27 9:55
 * @Version:        1.0
 */
class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            //开启InstantRun之后，一定要在ARouter.init之前调用openDebug
            ARouter.openDebug()
            ARouter.openLog()
        }
        ARouter.init(this)

    }


}