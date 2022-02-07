package com.sobot.online.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sobot.online.api.ZhiChiOnlineApi;
import com.sobot.online.api.ZhiChiOnlineApiFactory;

public class SobotOnlineBaseFragment extends SobotBaseFragment {
    public ZhiChiOnlineApi zhiChiApi;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (zhiChiApi == null) {
            synchronized (SobotOnlineBaseActivity.class) {
                if (zhiChiApi == null) {
                    zhiChiApi = ZhiChiOnlineApiFactory.createZhiChiApi(getSobotActivity());
                }
            }
        }
    }
}
