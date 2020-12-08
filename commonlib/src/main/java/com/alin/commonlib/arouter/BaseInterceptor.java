package com.alin.commonlib.arouter;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alin.cityselector.arouter.constant.ConstantMap;
import com.alin.cityselector.arouter.constant.RouterMap;

/**
 * @ProjectName: Cityselector
 * @Package: com.alin.cityselector.arouter
 * @ClassName: BaseInterceptor
 * @Description: 在路由基础组件中定义拦截器，在拦截器中，我们通过Postcard的getExtra方法来获得目标界面@Route中声明的extras属性，
 * 该属性是一个int类型，我们可以根据拦截的需求，在路由基础组件中定义不同的常量
 * @Author: ailibin
 * @CreateDate: 2020/12/4 14:51
 * @Version: 1.0
 */
public class BaseInterceptor implements IInterceptor {

    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        //getExtra() 对应目标 Activity 中的 @Route 声明。
        if (postcard.getExtra() == ConstantMap.LOGIN_EXTRA) {
            //判断是否登录。
            boolean isLogin = postcard.getExtras().getBoolean(ConstantMap.IS_LOGIN);
            if (!isLogin) {
                //如果没有登录，那么跳转到登录界面。
                ARouter.getInstance().build(RouterMap.LOGIN_PAGE).navigation();
            } else {
                //否则继续放行。
                postcard.withString(ConstantMap.IS_LOGIN_EXTRA, "登录了!");
                callback.onContinue(postcard);
            }
        } else {
            //对于其他不需要登录的界面直接放行。
            callback.onContinue(postcard);
        }
    }

    @Override
    public void init(Context context) {

    }
}
