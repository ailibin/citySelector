package com.alin.cityselectorlib.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alin.cityselectorlib.R;
import com.alin.cityselectorlib.router.RouterPath;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.interfaces.OnConfirmListener;

/**
 * @ProjectName: Cityselector
 * @Package: com.alin.commonlib.pop
 * @ClassName: PopTestActivity
 * @Description: java类作用描述
 * @Author: ailibin
 * @CreateDate: 2020/12/5 11:25
 * @Version: 1.0
 */
@Route(path = RouterPath.TEST_POP_ACTIVITY)
public class PopTestActivity extends AppCompatActivity {

    BasePopupView popupView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_pop);
        TextView tvTestPop = findViewById(R.id.tvTestPop);
        TextView tvTestRouter = findViewById(R.id.tvTestRouter);

        tvTestPop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupView = new XPopup.Builder(PopTestActivity.this)
//                        .dismissOnBackPressed(false)
//                        .navigationBarColor(Color.BLUE)
//                        .hasBlurBg(true)
//                         .dismissOnTouchOutside(false)
//                         .autoDismiss(false)
//                        .popupAnimation(PopupAnimation.NoAnimation)
//                        .isLightStatusBar(true)
                        .hasNavigationBar(false)
//                        .setPopupCallback(new DemoXPopupListener())
                        .asConfirm("哈哈", "床前明月光，疑是地上霜；举头望明月，低头思故乡。",
                                "取消", "确定",
                                new OnConfirmListener() {
                                    @Override
                                    public void onConfirm() {
//                                        Ts.showCenterToast("click");
                                    }
                                }, null, false);
                popupView.show();
            }
        });

        tvTestRouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(RouterPath.TEST_POP_ACTIVITY2).navigation();
            }
        });

    }
}
