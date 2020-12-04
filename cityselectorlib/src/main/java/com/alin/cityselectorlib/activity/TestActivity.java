package com.alin.cityselectorlib.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alin.cityselector.arouter.constant.ConstantMap;
import com.alin.cityselectorlib.R;
import com.alin.cityselectorlib.entity.Key;
import com.alin.cityselectorlib.router.RouterPath;
import com.alin.cityselectorlib.util.LogUtil;

import kotlin.jvm.JvmField;

/**
 * @ProjectName: Cityselector
 * @Package: com.alin.cityselectorlib.activity
 * @ClassName: TestActivity
 * @Description: 测试页面  测试Autowired自动注入
 * @Author: ailibin
 * @CreateDate: 2020/12/4 15:11
 * @Version: 1.0
 */
@Route(path = RouterPath.TEST_ACTIVITY)
public class TestActivity extends AppCompatActivity {

    @JvmField
    @Autowired(name = Key.PARAM_STRING)
    String content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
        setContentView(R.layout.activity_test);
        LogUtil.e(ConstantMap.TAG,content);
    }
}
