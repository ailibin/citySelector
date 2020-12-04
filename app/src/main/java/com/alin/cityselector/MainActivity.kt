package com.alin.cityselector

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.alibaba.android.arouter.launcher.ARouter
import com.alin.cityselectorlib.entity.Key
import com.alin.cityselectorlib.router.RouterPath
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        btnJumpCityList.setOnClickListener {
            ARouter.getInstance().build(RouterPath.TEST_SELECT_CITY_ACTIVITY)
                .withString(Key.PARAM_CITY_NAME, "广州").navigation()
        }

    }

}