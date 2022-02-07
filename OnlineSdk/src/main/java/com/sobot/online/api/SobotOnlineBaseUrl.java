package com.sobot.online.api;

import android.text.TextUtils;

/**
 * 接口请求地址 基础域名 管理类
 */
public class SobotOnlineBaseUrl {
    public static final String defaultHostname = "api.sobot.com";

    private static final String baseHost = "https://" + defaultHostname + "/";


    private static String api_host;

    public static void setApi_Host(String apiHost) {
        if (!TextUtils.isEmpty(apiHost)) {
            if (!apiHost.endsWith("/")) {
                api_host = apiHost + "/";
            } else {
                api_host = apiHost;
            }
        }
    }

    public static String getApi_Host() {
        if (!TextUtils.isEmpty(api_host)) {
            return api_host;
        }
        return baseHost;
    }

    public static String getBaseIp() {
        return getApi_Host()+"chat-admin-sdk/new/sdk/v1/";
    }


}
