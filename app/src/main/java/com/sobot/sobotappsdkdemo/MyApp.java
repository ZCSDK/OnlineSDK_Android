package com.sobot.sobotappsdkdemo;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;

import com.sobot.online.SobotOnlineService;
import com.sobot.utils.SobotLogUtils;
import com.umeng.commonsdk.UMConfigure;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        String yuming = SharedPreferencesUtil.getStringData(this, "yuming", "");
        Log.i("============",yuming+"------------------0000000000000000000000");
        if (!TextUtils.isEmpty(yuming)) {
            //初始化方法由基础组件包提供，要使用IM SDK，需要在宿主应用application.onCreate函数中调用基础组件包提供的初始化函数
            SobotOnlineService.initWithHost(this, yuming);
        } else {
            //初始化方法由基础组件包提供，要使用IM SDK，需要在宿主应用application.onCreate函数中调用基础组件包提供的初始化函数
            SobotOnlineService.initWithHost(this, "https://api.sobot.com");
        }
        SobotOnlineService.setNotificationFlag(this, true, R.drawable.sobot_logo_small_icon);
        SobotOnlineService.setShowDebug(true);

        //友盟init
        UMConfigure.init(this, "5fa8bb1a1c520d3073a333fb", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, null);
        /**
         * 设置组件化的Log开关
         * 参数: boolean 默认为false，如需查看LOG设置为true
         */
        UMConfigure.setLogEnabled(true);

    }
}
