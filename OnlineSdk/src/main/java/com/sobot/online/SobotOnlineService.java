package com.sobot.online;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.sobot.common.utils.SobotCommonApi;
import com.sobot.common.utils.SobotGlobalContext;
import com.sobot.online.activity.SobotAuthorActivity;
import com.sobot.online.activity.SobotCustomerServiceChatActivity;
import com.sobot.online.api.OnlineBaseCode;
import com.sobot.online.api.SobotOnlineBaseUrl;
import com.sobot.online.api.ZhiChiOnlineApi;
import com.sobot.online.api.ZhiChiOnlineApiFactory;
import com.sobot.online.control.OnlineChatManager;
import com.sobot.online.model.OnlineTokenModel;
import com.sobot.onlinecommon.control.CustomerServiceInfoModel;
import com.sobot.onlinecommon.control.OnlineMsgManager;
import com.sobot.onlinecommon.frame.http.callback.SobotResultCallBack;
import com.sobot.onlinecommon.socket.MsgCacheManager;
import com.sobot.onlinecommon.socket.SobotSocketConstant;
import com.sobot.onlinecommon.socket.channel.Const;
import com.sobot.onlinecommon.socket.channel.SobotTCPServer;
import com.sobot.onlinecommon.utils.SobotOnlineLogUtils;
import com.sobot.onlinecommon.utils.SobotMD5Utils;
import com.sobot.onlinecommon.utils.SobotOnlineUtils;
import com.sobot.onlinecommon.utils.SobotSPUtils;
import com.sobot.onlinecommon.utils.SobotUtils;
import com.sobot.onlinecommon.utils.StServiceUtils;

import java.util.HashMap;
import java.util.Map;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static com.sobot.online.OnlineConstant.SOBOT_CUSTOM_USER;

/**
 * 接口输出类
 */
public class SobotOnlineService {

    private static String Tag = SobotOnlineService.class.getSimpleName();

    /**
     * 初始化SDK，设置域名和认证token
     *
     * @param host 可以为空，默认阿里云服务；如果需要，请设置自己的域名
     */
    public static void initWithHost(Application application, String host) {
        SobotUtils.init(application);
        SobotCommonApi.init(application);
        if (!TextUtils.isEmpty(host)) {
            SobotOnlineBaseUrl.setApi_Host(host);
        }
    }

    /**
     * 启动客服认证页面
     *
     * @param context
     * @param appid       公司appid
     * @param appkey      商户appkey
     * @param account     客服账户(邮箱)
     * @param loginStatus 登录状态 1:在线,2:忙碌,-1等待客服设置,0:离线,直接返回
     */
    public static void startAuthWithAcount(Context context, String appid, String appkey, String account, int loginStatus) {
        if (context == null) {
            SobotOnlineLogUtils.e("startAuthWithAcount 参数 context不能为空");
            return;
        }
        if (TextUtils.isEmpty(appid) || TextUtils.isEmpty(appkey)) {
            Log.e(Tag, "请在Application中初始化SDK!");
            return;
        }
        if (loginStatus == 0) {
            Toast.makeText(context, "不能以离线状态登录", Toast.LENGTH_SHORT).show();
            Log.e(Tag, "不能以离线状态登录");
            return;
        }
        if (TextUtils.isEmpty(account)) {
            SobotOnlineLogUtils.e("客服账户不能为空");
            return;
        }
        String custom_kick_status = SobotSPUtils.getInstance().getString(SobotSocketConstant.custom_kick_status);
        if (!TextUtils.isEmpty(custom_kick_status) && custom_kick_status.equals(SobotSocketConstant.push_custom_outline_kick)) {
            OnlineMsgManager.getInstance(context).setCustomerServiceInfoModel(null);
            SobotSPUtils.getInstance().remove(SobotSocketConstant.custom_kick_status);
        }
        CustomerServiceInfoModel admin = OnlineMsgManager.getInstance(context).getCustomerServiceInfoModel();
        if (admin != null) {
            Log.i(Tag, "客服已登录，直接进入聊天页");
            Intent intent = new Intent(context, SobotCustomerServiceChatActivity.class);
            OnlineChatManager.connChannel(context, admin.getWslinkBak(), admin.getWslinkDefault(),
                    admin.getAid(), admin.getCompanyId(), admin.getPuid());
            context.startActivity(intent);
        } else {
            Intent intent = new Intent(context, SobotAuthorActivity.class);
            intent.putExtra("appid", appid);
            intent.putExtra("appkey", appkey);
            intent.putExtra("account", account);
            intent.putExtra("loginStatus", loginStatus);
            intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    /**
     * 通过设置token方式启动客服认证页面
     *
     * @param context
     * @param account     客服账户(邮箱)
     * @param loginToken  登录客服token
     * @param loginStatus 1:在线,2:忙碌,-1等待客服设置,0:离线,直接返回
     */
    public static void startAuthWithToken(Context context, String account, String loginToken, int loginStatus) {
        if (context == null) {
            SobotOnlineLogUtils.e("startAuthWithAcount 参数 context不能为空");
            return;
        }
        if (loginStatus == 0) {
            Toast.makeText(context, "不能以离线状态登录", Toast.LENGTH_SHORT).show();
            Log.e(Tag, "不能以离线状态登录");
            return;
        }
        String appid = SobotSPUtils.getInstance().getString(OnlineConstant.ONLINE_APPID);
        String appkey = SobotSPUtils.getInstance().getString(OnlineConstant.ONLINE_APPKEY);
        if (TextUtils.isEmpty(loginToken)) {
            if (TextUtils.isEmpty(appid) || TextUtils.isEmpty(appkey)) {
                Log.e(Tag, "请在Application中初始化SDK!");
                return;
            }
            if (TextUtils.isEmpty(account)) {
                SobotOnlineLogUtils.e("客服账户不能为空");
                return;
            }
        }
        Intent intent = new Intent(context, SobotAuthorActivity.class);
        intent.putExtra("appid", appid);
        intent.putExtra("appkey", appkey);
        intent.putExtra("account", account);
        intent.putExtra("loginStatus", loginStatus);
        intent.putExtra("loginToken", loginToken);
        context.startActivity(intent);
    }


    /**
     * 仅登录客服，不执行页面逻辑
     *
     * @param account     客服账户(邮箱)
     * @param loginStatus 登录状态 0:忙碌，1:在线，-1使用默认值
     */
    public static void doLoginWithAccount(final Context context, final String account, final int loginStatus) {
        if (context == null) {
            SobotOnlineLogUtils.e("doLoginWithAccount方法 参数 context不能为空");
            return;
        }
        String appid = SobotSPUtils.getInstance().getString(OnlineConstant.ONLINE_APPID);
        String appkey = SobotSPUtils.getInstance().getString(OnlineConstant.ONLINE_APPKEY);

        if (TextUtils.isEmpty(appid) || TextUtils.isEmpty(appkey)) {
            Log.e(Tag, "请在Application中调用【SobotOnlineService.initWithAppid()】来初始化SDK!");
            return;
        }
        if (TextUtils.isEmpty(account)) {
            SobotOnlineLogUtils.e("客服账户不能为空");
            return;
        }
        final ZhiChiOnlineApi zhiChiApi = ZhiChiOnlineApiFactory.createZhiChiApi(context);
        final Map<String, String> tokebMap = new HashMap<>();
        String nowMills = System.currentTimeMillis() + "";
        tokebMap.put("appid", appid);
        tokebMap.put("create_time", nowMills);
        tokebMap.put("sign", SobotMD5Utils.getMD5Str(appid + nowMills + appkey));
        zhiChiApi.getToken(account, tokebMap, new SobotResultCallBack<OnlineTokenModel>() {
            @Override
            public void onSuccess(OnlineTokenModel onlineTokenModel) {
                if (onlineTokenModel.getItem() != null && !TextUtils.isEmpty(onlineTokenModel.getRet_code()) && OnlineConstant.result_success_code.equals(onlineTokenModel.getRet_code())) {
                    zhiChiApi.login(context, account, loginStatus + "", onlineTokenModel.getItem().getToken(), new SobotResultCallBack<CustomerServiceInfoModel>() {
                        @Override
                        public void onSuccess(CustomerServiceInfoModel user) {
                            if (user != null && !TextUtils.isEmpty(user.getWslinkDefault()) && !TextUtils.isEmpty(user.getAid()) && !TextUtils.isEmpty(user.getCompanyId()) && !TextUtils.isEmpty(user.getPuid())) {
                                Intent intent = new Intent(context, SobotTCPServer.class);
                                if (user.getWslinkBak() != null && user.getWslinkBak().size() > 0) {
                                    intent.putExtra("wslinkBak", user.getWslinkBak().get(0));
                                }
                                intent.putExtra("wslinkDefault", user.getWslinkDefault());
                                intent.putExtra("companyId", user.getCompanyId());
                                intent.putExtra("aid", user.getAid());
                                intent.putExtra("puid", user.getPuid());
                                intent.putExtra("userType", Const.user_type_customer);
                                StServiceUtils.safeStartService(context, intent);
                            }
                        }

                        @Override
                        public void onFailure(Exception e, String des) {
                            e.printStackTrace();
                            Log.e(Tag, des);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Exception e, String des) {
                e.printStackTrace();
            }
        });

    }

    /**
     * 获取未读消息数
     */
    public static int getUnReadNumber(Context context) {
        int UnReadNumber = MsgCacheManager.getInstance().getTotalUnReadMsgCount();
        return UnReadNumber;
    }

    /**
     * 退出登录
     */
    public static void outAdmin(final Context context) {
        final ZhiChiOnlineApi zhiChiApi = ZhiChiOnlineApiFactory.createZhiChiApi(context);
        CustomerServiceInfoModel admin = (CustomerServiceInfoModel) SobotSPUtils.getInstance().getObject(SOBOT_CUSTOM_USER);
        if (admin != null) {
            zhiChiApi.out(context, admin.getPuid(), new SobotResultCallBack<OnlineBaseCode>() {
                @Override
                public void onSuccess(OnlineBaseCode onlineBaseCode) {
                    //清除缓存用户信息
                    OnlineMsgManager.getInstance(context).setCustomerServiceInfoModel(null);
                    SobotSPUtils.getInstance().remove(SOBOT_CUSTOM_USER);
                    disconnChannel(context);
                    context.stopService(new Intent(context, SobotTCPServer.class));
                    SobotGlobalContext.getInstance().finishAllActivity();
                }

                @Override
                public void onFailure(Exception e, String des) {
                    SobotOnlineLogUtils.e(des);
                }
            });
        }
    }

    /**
     * 设置是否开启消息提醒   默认不提醒
     *
     * @param context
     * @param flag      true 开启 ； false 关闭
     * @param smallIcon 小图标的id 设置通知栏中的小图片，尺寸一般建议在24×24
     */
    public static void setNotificationFlag(Context context, boolean flag, int smallIcon) {
        if (context == null) {
            return;
        }
        SobotSPUtils.getInstance().put(SobotSocketConstant.SOBOT_NOTIFICATION_FLAG, flag);
        SobotSPUtils.getInstance().put(SobotSocketConstant.SOBOT_NOTIFICATION_SMALL_ICON, smallIcon);
    }

    /*************通道相关*********************/

    /**
     * 关闭通道
     *
     * @param context
     */
    public static void disconnChannel(Context context) {
        context.sendBroadcast(new Intent(Const.SOBOT_CUSTOME_DISCONNCHANNEL));
    }


    /**
     * 日志显示设置
     *
     * @param isShowLog true 显示日志信息 默认false不显示
     */
    public static void setShowDebug(Boolean isShowLog) {
        SobotOnlineLogUtils.setShowDebug(isShowLog);
        SobotCommonApi.setShowLogDebug(isShowLog);
    }

}