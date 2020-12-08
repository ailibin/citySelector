package com.alin.cityselector

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.aiitec.jpmj.entitylibary.model.CharCitys
import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.alibaba.android.arouter.launcher.ARouter
import com.alin.cityselectorlib.entity.Key
import com.alin.cityselectorlib.router.RouterPath
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE = 0x110

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        btnJumpCityList.setOnClickListener {
            ARouter.getInstance().build(RouterPath.TEST_SELECT_CITY_ACTIVITY)
                .withString(Key.PARAM_CITY_NAME, "广州").navigation(this, REQUEST_CODE)
        }

        btnJumpTest.setOnClickListener {
            ARouter.getInstance().build(RouterPath.TEST_ACTIVITY).withString(Key.PARAM_STRING, "佛山")
                .navigation()
        }

        btnPopTest.setOnClickListener {
            ARouter.getInstance().build( RouterPath.TEST_POP_ACTIVITY).navigation()
        }

        btnSeekBar.setOnClickListener {
            ARouter.getInstance().build( RouterPath.TEST_SEEK_BAR_ACTIVITY).navigation()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            return
        }
        val city = data?.getParcelableExtra<CharCitys>(Key.PARAM_CITY)
        if (requestCode == REQUEST_CODE) {
            Toast.makeText(
                this,
                "收到了结果:${city?.name}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}