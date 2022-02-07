package com.sobot.online.api;

import android.content.Context;
import android.text.TextUtils;

import com.sobot.common.utils.SobotGlobalContext;
import com.sobot.online.OnlineConstant;
import com.sobot.online.SobotOnlineService;
import com.sobot.online.model.ChatMessageListModelResult;
import com.sobot.online.model.ChatMessageModel;
import com.sobot.online.model.ChatReplyGroupInfoModel;
import com.sobot.online.model.ChatReplyGroupInfoModelResult;
import com.sobot.online.model.ChatReplyInfoModel;
import com.sobot.online.model.ChatReplyInfoModelResult;
import com.sobot.online.model.CidsModel;
import com.sobot.online.model.CidsModelResult;
import com.sobot.online.model.ConversationConfigModelResult;
import com.sobot.online.model.CreateWorkOrderUserResult;
import com.sobot.online.model.CusFieldConfigList;
import com.sobot.online.model.CusFieldConfigListResult;
import com.sobot.online.model.CusFieldDataInfoListResult;
import com.sobot.online.model.CusFieldDataInfoModel;
import com.sobot.online.model.EditUserInfoResult;
import com.sobot.online.model.HistoryChatModel;
import com.sobot.online.model.HistoryChatModelResult;
import com.sobot.online.model.HistoryUserInfoListResult;
import com.sobot.online.model.HistoryUserInfoModel;
import com.sobot.online.model.OfflineMsgModel;
import com.sobot.online.model.OfflineMsgResult;
import com.sobot.online.model.OnLineGroupModel;
import com.sobot.online.model.OnLineGroupModelResult;
import com.sobot.online.model.OnLineServiceModel;
import com.sobot.online.model.OnLineServiceModelResult;
import com.sobot.online.model.OnlineCommonModel;
import com.sobot.online.model.OnlineCommonModelResult;
import com.sobot.online.model.OnlineCustomerServiceInfoResult;
import com.sobot.online.model.OnlineEnterPriseModel;
import com.sobot.online.model.OnlineEnterPriseModelResult;
import com.sobot.online.model.OnlineMsgModelResult;
import com.sobot.online.model.OnlineServiceStatus;
import com.sobot.online.model.OnlineServiceStatusResult;
import com.sobot.online.model.OnlineSynChronousResult;
import com.sobot.online.model.OnlineTokenModel;
import com.sobot.online.model.QueueUserModel;
import com.sobot.online.model.QueueUserModelResult;
import com.sobot.online.model.RebotSmartAnswerModel;
import com.sobot.online.model.RebotSmartAnswerModelResult;
import com.sobot.online.model.SummaryInfoModelResult;
import com.sobot.online.model.SummaryModel;
import com.sobot.online.model.SynChronousModel;
import com.sobot.online.model.UnitInfoModel;
import com.sobot.online.model.UnitInfoModelResult;
import com.sobot.online.model.UnitTypeAndFieldModel;
import com.sobot.online.model.UnitTypeInfoModelResult;
import com.sobot.online.model.UserConversationModel;
import com.sobot.online.model.UserConversationModelResult;
import com.sobot.online.model.UserInfoModel;
import com.sobot.online.model.UserInfoModelResult;
import com.sobot.online.model.WorkOrderUser;
import com.sobot.online.model.WorkOrderUserResult;
import com.sobot.onlinecommon.control.CustomerServiceInfoModel;
import com.sobot.onlinecommon.control.OnlineMsgManager;
import com.sobot.onlinecommon.frame.http.callback.SobotFileResultCallBack;
import com.sobot.onlinecommon.frame.http.callback.SobotResultCallBack;
import com.sobot.onlinecommon.gson.SobotGsonUtil;
import com.sobot.onlinecommon.socket.MsgCacheManager;
import com.sobot.onlinecommon.utils.SobotAppUtils;
import com.sobot.onlinecommon.utils.SobotOnlineLogUtils;
import com.sobot.onlinecommon.utils.SobotResourceUtils;
import com.sobot.onlinecommon.utils.SobotSPUtils;
import com.sobot.onlinecommon.utils.SobotUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sobot.online.OnlineConstant.SOBOT_CUSTOM_USER;


/**
 * 对外输出接口类实现
 */
public class ZhiChiOnlineApiImpl implements ZhiChiOnlineApi {

    private static final String tag = ZhiChiOnlineApiImpl.class.getSimpleName() + "";
    private Context mContext;

    private ZhiChiOnlineApiImpl() {
    }

    public ZhiChiOnlineApiImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void getToken(Object cancelTag, Map param, final SobotResultCallBack<OnlineTokenModel> resultCallBack) {
        OnlineHttpUtils.getInstance().doGetWithNoHeader(cancelTag, SobotOnlineUrlApi.api_getToken, param,
                new OnlineHttpUtils.StringCallBack() {

                    @Override
                    public void onError(Exception e, String msg, int responseCode) {
                        SobotOnlineLogUtils.i(SobotOnlineUrlApi.api_getToken + "  请求异常: " + e.getMessage());
                        resultCallBack.onFailure(e, SobotResourceUtils.getResString(mContext, "sobot_online_net_error"));
                    }

                    @Override
                    public void onResponse(String response) {
                        OnlineTokenModel result = SobotGsonUtil.gsonToBean(response, OnlineTokenModel.class);
                        if (result != null) {
                            resultCallBack.onSuccess(result);
                        } else {
                            resultCallBack.onFailure(new Exception(), TextUtils.isEmpty(result.getRet_msg()) ? SobotResourceUtils.getResString(mContext, "sobot_online_net_error") : result.getRet_msg());
                        }
                    }

                    @Override
                    public void inProgress(int progress) {
                    }
                });
    }

    @Override
    public void login(Object cancelTag, String account, String loginStatus, String token, final SobotResultCallBack<CustomerServiceInfoModel> resultCallBack) {
        //登录时使用的通道类型  10 代表TCP连接
        int channel_way = 10;
        Map<String, String> loginMap = new HashMap<>();
        loginMap.put("email", account);
        loginMap.put("token", token);
        loginMap.put("loginStatus", loginStatus);
        loginMap.put("from", 2 + "");
        loginMap.put("way", channel_way + "");
        loginMap.put("system", SobotAppUtils.getSystemVersion());
        loginMap.put("ack", "1");
        loginMap.put("version", SobotAppUtils.getAppVersionName());
        OnlineHttpUtils.getInstance().doPost(cancelTag, SobotOnlineUrlApi.api_login, loginMap,
                new OnlineHttpUtils.StringCallBack() {

                    @Override
                    public void onError(Exception e, String msg, int responseCode) {
                        SobotOnlineLogUtils.i(SobotOnlineUrlApi.api_login + "  请求异常: " + e.getMessage());
                        resultCallBack.onFailure(e, SobotResourceUtils.getResString(mContext, "sobot_online_net_error"));
                    }

                    @Override
                    public void onResponse(String response) {
                        OnlineCustomerServiceInfoResult result = SobotGsonUtil.gsonToBean(response, OnlineCustomerServiceInfoResult.class);
                        if (result != null && !TextUtils.isEmpty(result.getRetCode()) && OnlineConstant.result_success_code.equals(result.getRetCode())) {
                            MsgCacheManager.getInstance().clearAllPushMessageModels();
                            resultCallBack.onSuccess(result.getData());
                        } else {
                            resultCallBack.onFailure(new Exception(), (result == null || TextUtils.isEmpty(result.getRetMsg())) ? SobotResourceUtils.getResString(mContext, "sobot_online_net_error") : result.getRetMsg());
                        }
                    }

                    @Override
                    public void inProgress(int progress) {
                    }
                });
    }

    @Override
    public void conversationConfig(Object cancelTag, final SobotResultCallBack<ConversationConfigModelResult.ConversationConfigModel> resultCallBack) {
        OnlineHttpUtils.getInstance().doPost(cancelTag, SobotOnlineUrlApi.api_conversationConfig, null,
                new OnlineHttpUtils.StringCallBack() {

                    @Override
                    public void onError(Exception e, String msg, int responseCode) {
                        SobotOnlineLogUtils.i(SobotOnlineUrlApi.api_conversationConfig + "  请求异常: " + e.getMessage());
                        resultCallBack.onFailure(e, SobotResourceUtils.getResString(mContext, "sobot_online_net_error"));
                    }

                    @Override
                    public void onResponse(String response) {
                        ConversationConfigModelResult result = SobotGsonUtil.gsonToBean(response, ConversationConfigModelResult.class);
                        if (result != null && !TextUtils.isEmpty(result.getRetCode()) && OnlineConstant.result_success_code.equals(result.getRetCode())) {
                            resultCallBack.onSuccess(result.getData());
                        } else {
                            checkTokenCloseSobot(result != null ? result.getRetCode() : "");
                            resultCallBack.onFailure(new Exception(), (result == null || TextUtils.isEmpty(result.getRetMsg())) ? SobotResourceUtils.getResString(mContext, "sobot_online_net_error") : result.getRetMsg());
                        }
                    }

                    @Override
                    public void inProgress(int progress) {
                    }
                });
    }

    @Override
    public void synChronous(Object cancelTag, final SobotResultCallBack<SynChronousModel> resultCallBack) {
        OnlineHttpUtils.getInstance().doPost(cancelTag, SobotOnlineUrlApi.api_synChronous, null,
                new OnlineHttpUtils.StringCallBack() {

                    @Override
                    public void onError(Exception e, String msg, int responseCode) {
                        SobotOnlineLogUtils.i(SobotOnlineUrlApi.api_synChronous + "  请求异常: " + e.getMessage());
                        resultCallBack.onFailure(e, SobotResourceUtils.getResString(mContext, "sobot_online_net_error"));
                    }

                    @Override
                    public void onResponse(String response) {
                        OnlineSynChronousResult result = SobotGsonUtil.gsonToBean(response, OnlineSynChronousResult.class);
                        if (result != null && !TextUtils.isEmpty(result.getRetCode()) && OnlineConstant.result_success_code.equals(result.getRetCode())) {
                            resultCallBack.onSuccess(result.getData());
                        } else {
                            checkTokenCloseSobot(result != null ? result.getRetCode() : "");
                            resultCallBack.onFailure(new Exception(), (result == null || TextUtils.isEmpty(result.getRetMsg())) ? SobotResourceUtils.getResString(mContext, "sobot_online_net_error") : result.getRetMsg());
                        }
                    }

                    @Override
                    public void inProgress(int progress) {
                    }
                });
    }

    @Override
    public void queryWaitUser(Object cancelTag, int pageNum, int pageSize, final SobotResultCallBack<QueueUserModel> resultCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("pageNum", pageNum + "");
        map.put("pageSize", pageSize + "");
        OnlineHttpUtils.getInstance().doPost(cancelTag, SobotOnlineUrlApi.api_queryWaitUser, map,
                new OnlineHttpUtils.StringCallBack() {

                    @Override
                    public void onError(Exception e, String msg, int responseCode) {
                        SobotOnlineLogUtils.i(SobotOnlineUrlApi.api_queryWaitUser + "  请求异常: " + e.getMessage());
                        resultCallBack.onFailure(e, SobotResourceUtils.getResString(mContext, "sobot_online_net_error"));
                    }

                    @Override
                    public void onResponse(String response) {
                        QueueUserModelResult result = SobotGsonUtil.gsonToBean(response, QueueUserModelResult.class);
                        if (result != null && !TextUtils.isEmpty(result.getRetCode()) && OnlineConstant.result_success_code.equals(result.getRetCode())) {
                            resultCallBack.onSuccess(result.getData());
                        } else {
                            checkTokenCloseSobot(result != null ? result.getRetCode() : "");
                            resultCallBack.onFailure(new Exception(), (result == null || TextUtils.isEmpty(result.getRetMsg())) ? SobotResourceUtils.getResString(mContext, "sobot_online_net_error") : result.getRetMsg());
                        }
                    }

                    @Override
                    public void inProgress(int progress) {
                    }
                });
    }

    @Override
    public void queryConversationList(Object cancelTag, int pageNum, int pageSize, int type, Map params, final SobotResultCallBack<List<HistoryUserInfoModel>> resultCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("pageNo", pageNum + "");
        map.put("pageNum", pageNum + "");
        map.put("pageSize", pageSize + "");
        if (params != null) {
            map.putAll(params);
        }
        String urlStr = SobotOnlineUrlApi.api_getHistroryUser;
        switch (type) {
            case 1:
                urlStr = SobotOnlineUrlApi.api_queryMarkList;
                break;
            case 2:
                urlStr = SobotOnlineUrlApi.api_queryBlackList;
                break;
        }
        final String finalUrlStr = urlStr;
        OnlineHttpUtils.getInstance().doPost(cancelTag, urlStr, map,
                new OnlineHttpUtils.StringCallBack() {

                    @Override
                    public void onError(Exception e, String msg, int responseCode) {
                        SobotOnlineLogUtils.i(finalUrlStr + "  请求异常: " + e.getMessage());
                        resultCallBack.onFailure(e, SobotResourceUtils.getResString(mContext, "sobot_online_net_error"));
                    }

                    @Override
                    public void onResponse(String response) {
                        HistoryUserInfoListResult result = SobotGsonUtil.gsonToBean(response, HistoryUserInfoListResult.class);
                        if (result != null && !TextUtils.isEmpty(result.getRetCode()) && OnlineConstant.result_success_code.equals(result.getRetCode())) {
                            resultCallBack.onSuccess(result.getData());
                        } else {
                            checkTokenCloseSobot(result != null ? result.getRetCode() : "");
                            resultCallBack.onFailure(new Exception(), (result == null || TextUtils.isEmpty(result.getRetMsg())) ? SobotResourceUtils.getResString(mContext, "sobot_online_net_error") : result.getRetMsg());
                        }
                    }

                    @Override
                    public void inProgress(int progress) {
                    }
                });
    }

    @Override
    public void getHistoryChatList(Object cancelTag, int pageNum, int pageSize, Map patams, final SobotResultCallBack<List<HistoryChatModel>> resultCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("pageNum", pageNum + "");
        map.put("pageSize", pageSize + "");
        if (patams != null) {
            map.putAll(patams);
        }
        OnlineHttpUtils.getInstance().doPost(cancelTag, SobotOnlineUrlApi.api_getHistoryChat, map,
                new OnlineHttpUtils.StringCallBack() {

                    @Override
                    public void onError(Exception e, String msg, int responseCode) {
                        SobotOnlineLogUtils.i(SobotOnlineUrlApi.api_getHistoryChat + "  请求异常: " + e.getMessage());
                        resultCallBack.onFailure(e, SobotResourceUtils.getResString(mContext, "sobot_online_net_error"));
                    }

                    @Override
                    public void onResponse(String response) {
                        HistoryChatModelResult result = SobotGsonUtil.gsonToBean(response, HistoryChatModelResult.class);
                        if (result != null && !TextUtils.isEmpty(result.getRetCode()) && OnlineConstant.result_success_code.equals(result.getRetCode())) {
                            resultCallBack.onSuccess(result.getData());
                        } else {
                            checkTokenCloseSobot(result != null ? result.getRetCode() : "");
                            resultCallBack.onFailure(new Exception(), (result == null || TextUtils.isEmpty(result.getRetMsg())) ? SobotResourceUtils.getResString(mContext, "sobot_online_net_error") : result.getRetMsg());
                        }
                    }

                    @Override
                    public void inProgress(int progress) {
                    }
                });
    }

    @Override
    public void queryCids(Object cancelTag, String uid, final SobotResultCallBack<CidsModel> resultCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        OnlineHttpUtils.getInstance().doPost(cancelTag, SobotOnlineUrlApi.api_queryCids, map,
                new OnlineHttpUtils.StringCallBack() {

                    @Override
                    public void onError(Exception e, String msg, int responseCode) {
                        SobotOnlineLogUtils.i(SobotOnlineUrlApi.api_queryCids + "  请求异常: " + e.getMessage());
                        resultCallBack.onFailure(e, SobotResourceUtils.getResString(mContext, "sobot_online_net_error"));
                    }

                    @Override
                    public void onResponse(String response) {
                        CidsModelResult result = SobotGsonUtil.gsonToBean(response, CidsModelResult.class);
                        if (result != null && !TextUtils.isEmpty(result.getRetCode()) && OnlineConstant.result_success_code.equals(result.getRetCode())) {
                            resultCallBack.onSuccess(result.getData());
                        } else {
                            checkTokenCloseSobot(result != null ? result.getRetCode() : "");
                            resultCallBack.onFailure(new Exception(), (result == null || TextUtils.isEmpty(result.getRetMsg())) ? SobotResourceUtils.getResString(mContext, "sobot_online_net_error") : result.getRetMsg());
                        }
                    }

                    @Override
                    public void inProgress(int progress) {
                    }
                });
    }

    @Override
    public void queryHistoryRecords(Object cancelTag, Map<String, String> map, final SobotResultCallBack<List<ChatMessageModel>> resultCallBack) {
        OnlineHttpUtils.getInstance().doPost(cancelTag, SobotOnlineUrlApi.api_queryHistoryRecords, map,
                new OnlineHttpUtils.StringCallBack() {

                    @Override
                    public void onError(Exception e, String msg, int responseCode) {
                        SobotOnlineLogUtils.i(SobotOnlineUrlApi.api_queryHistoryRecords + "  请求异常: " + e.getMessage());
                        resultCallBack.onFailure(e, SobotResourceUtils.getResString(mContext, "sobot_online_net_error"));
                    }

                    @Override
                    public void onResponse(String response) {
                        ChatMessageListModelResult result = SobotGsonUtil.gsonToBean(response, ChatMessageListModelResult.class);
                        if (result != null && !TextUtils.isEmpty(result.getRetCode()) && OnlineConstant.result_success_code.equals(result.getRetCode())) {
                            resultCallBack.onSuccess(result.getData());
                        } else {
                            checkTokenCloseSobot(result != null ? result.getRetCode() : "");
                            resultCallBack.onFailure(new Exception(), (result == null || TextUtils.isEmpty(result.getRetMsg())) ? SobotResourceUtils.getResString(mContext, "sobot_online_net_error") : result.getRetMsg());
                        }
                    }

                    @Override
                    public void inProgress(int progress) {
                    }
                });
    }

    @Override
    public void invite(Object cancelTag, String userId, final SobotResultCallBack<OnlineCommonModel> resultCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", userId);
        OnlineHttpUtils.getInstance().doPost(cancelTag, SobotOnlineUrlApi.api_invite, map,
                new OnlineHttpUtils.StringCallBack() {

                    @Override
                    public void onError(Exception e, String msg, int responseCode) {
                        SobotOnlineLogUtils.i(SobotOnlineUrlApi.api_invite + "  请求异常: " + e.getMessage());
                        resultCallBack.onFailure(e, SobotResourceUtils.getResString(mContext, "sobot_online_net_error"));
                    }

                    @Override
                    public void onResponse(String response) {
                        OnlineCommonModelResult result = SobotGsonUtil.gsonToBean(response, OnlineCommonModelResult.class);
                        if (result != null && !TextUtils.isEmpty(result.getRetCode()) && OnlineConstant.result_success_code.equals(result.getRetCode())) {
                            resultCallBack.onSuccess(result.getData());
                        } else {
                            checkTokenCloseSobot(result != null ? result.getRetCode() : "");
                            resultCallBack.onFailure(new Exception(), (result == null || TextUtils.isEmpty(result.getRetMsg())) ? SobotResourceUtils.getResString(mContext, "sobot_online_net_error") : result.getRetMsg());
                        }
                    }

                    @Override
                    public void inProgress(int progress) {
                    }
                });
    }

    @Override
    public void send(Object cancelTag, boolean isOffline, Map<String, String> map, String path, final SobotFileResultCallBack<OnlineMsgModelResult> resultCallBack) {
        final String urlStr = isOffline ? SobotOnlineUrlApi.api_sendOfflineMsg : SobotOnlineUrlApi.api_send;
        OnlineHttpUtils.getInstance().doPostWithFile(cancelTag, urlStr, map, path,
                new OnlineHttpUtils.StringCallBack() {

                    @Override
                    public void onError(Exception e, String msg, int responseCode) {
                        SobotOnlineLogUtils.i(urlStr + "  请求异常: " + e.getMessage());
                        resultCallBack.onFailure(e, SobotResourceUtils.getResString(mContext, "sobot_online_net_error"));
                    }

                    @Override
                    public void onResponse(String response) {
                        OnlineMsgModelResult result = SobotGsonUtil.gsonToBean(response, OnlineMsgModelResult.class);
                        if (result != null && !TextUtils.isEmpty(result.getRetCode()) && (OnlineConstant.result_success_code.equals(result.getRetCode()) || OnlineConstant.result_success_code_sensitive_words.equals(result.getRetCode()))) {
                            resultCallBack.onSuccess(result);
                        } else {
                            checkTokenCloseSobot(result != null ? result.getRetCode() : "");
                            resultCallBack.onFailure(new Exception(), (result == null || TextUtils.isEmpty(result.getRetMsg())) ? SobotResourceUtils.getResString(mContext, "sobot_online_net_error") : result.getRetMsg());
                        }
                    }

                    @Override
                    public void inProgress(int progress) {
                        resultCallBack.inProgress(progress);
                    }
                });
    }

    @Override
    public void getStatusNow(Object cancelTag, String uid, final SobotResultCallBack<OfflineMsgModel> resultCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        OnlineHttpUtils.getInstance().doPost(cancelTag, SobotOnlineUrlApi.api_getStatusNow, map,
                new OnlineHttpUtils.StringCallBack() {

                    @Override
                    public void onError(Exception e, String msg, int responseCode) {
                        SobotOnlineLogUtils.i(SobotOnlineUrlApi.api_getStatusNow + "  请求异常: " + e.getMessage());
                        resultCallBack.onFailure(e, SobotResourceUtils.getResString(mContext, "sobot_online_net_error"));
                    }

                    @Override
                    public void onResponse(String response) {
                        OfflineMsgResult result = SobotGsonUtil.gsonToBean(response, OfflineMsgResult.class);
                        if (result != null && !TextUtils.isEmpty(result.getRetCode()) && OnlineConstant.result_success_code.equals(result.getRetCode())) {
                            resultCallBack.onSuccess(result.getData());
                        } else {
                            checkTokenCloseSobot(result != null ? result.getRetCode() : "");
                            resultCallBack.onFailure(new Exception(), (result == null || TextUtils.isEmpty(result.getRetMsg())) ? SobotResourceUtils.getResString(mContext, "sobot_online_net_error") : result.getRetMsg());
                        }
                    }

                    @Override
                    public void inProgress(int progress) {
                    }
                });
    }

    @Override
    public void addOrDeleteBlackList(Object cancelTag, String userId, final String reason, int type, final String url, final SobotResultCallBack<OnlineBaseCode> resultCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", userId);
        map.put("reason", reason);
        map.put("type", type + "");//1：24小时拉黑
        OnlineHttpUtils.getInstance().doPost(cancelTag, url, map,
                new OnlineHttpUtils.StringCallBack() {

                    @Override
                    public void onError(Exception e, String msg, int responseCode) {
                        SobotOnlineLogUtils.i(url + "  请求异常: " + e.getMessage());
                        resultCallBack.onFailure(e, SobotResourceUtils.getResString(mContext, "sobot_online_net_error"));
                    }

                    @Override
                    public void onResponse(String response) {
                        OnlineBaseCode result = SobotGsonUtil.gsonToBean(response, OnlineBaseCode.class);
                        if (result != null && !TextUtils.isEmpty(result.getRetCode()) && OnlineConstant.result_success_code.equals(result.getRetCode())) {
                            resultCallBack.onSuccess(result);
                        } else {
                            checkTokenCloseSobot(result != null ? result.getRetCode() : "");
                            resultCallBack.onFailure(new Exception(), (result == null || TextUtils.isEmpty(result.getRetMsg())) ? SobotResourceUtils.getResString(mContext, "sobot_online_net_error") : result.getRetMsg());
                        }
                    }

                    @Override
                    public void inProgress(int progress) {
                    }
                });
    }

    @Override
    public void addOrDeleteMarkList(Object cancelTag, String userId, final String url, final SobotResultCallBack<OnlineBaseCode> resultCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", userId);
        OnlineHttpUtils.getInstance().doPost(cancelTag, url, map,
                new OnlineHttpUtils.StringCallBack() {

                    @Override
                    public void onError(Exception e, String msg, int responseCode) {
                        SobotOnlineLogUtils.i(url + "  请求异常: " + e.getMessage());
                        resultCallBack.onFailure(e, SobotResourceUtils.getResString(mContext, "sobot_online_net_error"));
                    }

                    @Override
                    public void onResponse(String response) {
                        OnlineBaseCode result = SobotGsonUtil.gsonToBean(response, OnlineBaseCode.class);
                        if (result != null && !TextUtils.isEmpty(result.getRetCode()) && OnlineConstant.result_success_code.equals(result.getRetCode())) {
                            resultCallBack.onSuccess(result);
                        } else {
                            checkTokenCloseSobot(result != null ? result.getRetCode() : "");
                            resultCallBack.onFailure(new Exception(), (result == null || TextUtils.isEmpty(result.getRetMsg())) ? SobotResourceUtils.getResString(mContext, "sobot_online_net_error") : result.getRetMsg());
                        }
                    }

                    @Override
                    public void inProgress(int progress) {
                    }
                });
    }

    @Override
    public void invateEvaluateInt(Object cancelTag, String cid, final SobotResultCallBack<OnlineCommonModel> resultCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("cid", cid);
        OnlineHttpUtils.getInstance().doPost(cancelTag, SobotOnlineUrlApi.api_reComment, map,
                new OnlineHttpUtils.StringCallBack() {

                    @Override
                    public void onError(Exception e, String msg, int responseCode) {
                        SobotOnlineLogUtils.i(SobotOnlineUrlApi.api_reComment + "  请求异常: " + e.getMessage());
                        resultCallBack.onFailure(e, SobotResourceUtils.getResString(mContext, "sobot_online_net_error"));
                    }

                    @Override
                    public void onResponse(String response) {
                        OnlineCommonModelResult result = SobotGsonUtil.gsonToBean(response, OnlineCommonModelResult.class);
                        if (result != null && !TextUtils.isEmpty(result.getRetCode()) && OnlineConstant.result_success_code.equals(result.getRetCode())) {
                            resultCallBack.onSuccess(result.getData());
                        } else {
                            checkTokenCloseSobot(result != null ? result.getRetCode() : "");
                            resultCallBack.onFailure(new Exception(), (result == null || TextUtils.isEmpty(result.getRetMsg())) ? SobotResourceUtils.getResString(mContext, "sobot_online_net_error") : result.getRetMsg());
                        }
                    }

                    @Override
                    public void inProgress(int progress) {
                    }
                });
    }

    @Override
    public void invateEvaluateIntOff(Object cancelTag, String cid, final SobotResultCallBack<OnlineCommonModel> resultCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("cid", cid);
        OnlineHttpUtils.getInstance().doPost(cancelTag, SobotOnlineUrlApi.api_isComment, map,
                new OnlineHttpUtils.StringCallBack() {

                    @Override
                    public void onError(Exception e, String msg, int responseCode) {
                        SobotOnlineLogUtils.i(SobotOnlineUrlApi.api_isComment + "  请求异常: " + e.getMessage());
                        resultCallBack.onFailure(e, SobotResourceUtils.getResString(mContext, "sobot_online_net_error"));
                    }

                    @Override
                    public void onResponse(String response) {
                        OnlineCommonModelResult result = SobotGsonUtil.gsonToBean(response, OnlineCommonModelResult.class);
                        if (result != null && !TextUtils.isEmpty(result.getRetCode()) && OnlineConstant.result_success_code.equals(result.getRetCode())) {
                            resultCallBack.onSuccess(result.getData());
                        } else {
                            checkTokenCloseSobot(result != null ? result.getRetCode() : "");
                            resultCallBack.onFailure(new Exception(), (result == null || TextUtils.isEmpty(result.getRetMsg())) ? SobotResourceUtils.getResString(mContext, "sobot_online_net_error") : result.getRetMsg());
                        }
                    }

                    @Override
                    public void inProgress(int progress) {
                    }
                });
    }

    @Override
    public void queryConMsg(Object cancelTag, String uid, String cid, final SobotResultCallBack<UserConversationModel> resultCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("cid", cid);
        OnlineHttpUtils.getInstance().doPost(cancelTag, SobotOnlineUrlApi.api_queryConMsg, map,
                new OnlineHttpUtils.StringCallBack() {

                    @Override
                    public void onError(Exception e, String msg, int responseCode) {
                        SobotOnlineLogUtils.i(SobotOnlineUrlApi.api_queryConMsg + "  请求异常: " + e.getMessage());
                        resultCallBack.onFailure(e, SobotResourceUtils.getResString(mContext, "sobot_online_net_error"));
                    }

                    @Override
                    public void onResponse(String response) {
                        UserConversationModelResult result = SobotGsonUtil.gsonToBean(response, UserConversationModelResult.class);
                        if (result != null && !TextUtils.isEmpty(result.getRetCode()) && OnlineConstant.result_success_code.equals(result.getRetCode())) {
                            resultCallBack.onSuccess(result.getData());
                        } else {
                            checkTokenCloseSobot(result != null ? result.getRetCode() : "");
                            resultCallBack.onFailure(new Exception(), (result == null || TextUtils.isEmpty(result.getRetMsg())) ? SobotResourceUtils.getResString(mContext, "sobot_online_net_error") : result.getRetMsg());
                        }
                    }

                    @Override
                    public void inProgress(int progress) {
                    }
                });
    }

    @Override
    public void getUserInfo(Object cancelTag, String userId, final SobotResultCallBack<UserInfoModel> resultCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", userId);
        OnlineHttpUtils.getInstance().doPost(cancelTag, SobotOnlineUrlApi.api_getUserInfo, map,
                new OnlineHttpUtils.StringCallBack() {

                    @Override
                    public void onError(Exception e, String msg, int responseCode) {
                        SobotOnlineLogUtils.i(SobotOnlineUrlApi.api_getUserInfo + "  请求异常: " + e.getMessage());
                        resultCallBack.onFailure(e, SobotResourceUtils.getResString(mContext, "sobot_online_net_error"));
                    }

                    @Override
                    public void onResponse(String response) {
                        UserInfoModelResult result = SobotGsonUtil.gsonToBean(response, UserInfoModelResult.class);
                        if (result != null && !TextUtils.isEmpty(result.getRetCode()) && OnlineConstant.result_success_code.equals(result.getRetCode())) {
                            resultCallBack.onSuccess(result.getData());
                        } else {
                            checkTokenCloseSobot(result != null ? result.getRetCode() : "");
                            resultCallBack.onFailure(new Exception(), (result == null || TextUtils.isEmpty(result.getRetMsg())) ? SobotResourceUtils.getResString(mContext, "sobot_online_net_error") : result.getRetMsg());
                        }
                    }

                    @Override
                    public void inProgress(int progress) {
                    }
                });
    }

    @Override
    public void getAppEnterpriseList(Object cancelTag, int pageNum, int pageSize, String keyword, final SobotResultCallBack<List<OnlineEnterPriseModel>> resultCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("pageNo", pageNum + "");
        map.put("pageSize", pageSize + "");
        map.put("enterpriseName", keyword);
        OnlineHttpUtils.getInstance().doGet(cancelTag, SobotOnlineUrlApi.api_getAppEnterpriseList, map,
                new OnlineHttpUtils.StringCallBack() {

                    @Override
                    public void onError(Exception e, String msg, int responseCode) {
                        SobotOnlineLogUtils.i(SobotOnlineUrlApi.api_getAppEnterpriseList + "  请求异常: " + e.getMessage());
                        resultCallBack.onFailure(e, SobotResourceUtils.getResString(mContext, "sobot_online_net_error"));
                    }

                    @Override
                    public void onResponse(String response) {
                        OnlineEnterPriseModelResult result = SobotGsonUtil.gsonToBean(response, OnlineEnterPriseModelResult.class);
                        if (result != null && !TextUtils.isEmpty(result.getRetCode()) && OnlineConstant.result_success_code.equals(result.getRetCode())) {
                            resultCallBack.onSuccess(result.getItems());
                        } else {
                            checkTokenCloseSobot(result != null ? result.getRetCode() : "");
                            resultCallBack.onFailure(new Exception(), SobotResourceUtils.getResString(mContext, "sobot_online_net_error"));
                        }
                    }

                    @Override
                    public void inProgress(int progress) {
                    }
                });
    }

    @Override
    public void getAppUserInfoByUserId(Object cancelTag, String userId, final SobotResultCallBack<WorkOrderUser> resultCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("id", userId);
        OnlineHttpUtils.getInstance().doGet(cancelTag, SobotOnlineUrlApi.api_getAppUserInfoByUserId, map,
                new OnlineHttpUtils.StringCallBack() {

                    @Override
                    public void onError(Exception e, String msg, int responseCode) {
                        SobotOnlineLogUtils.i(SobotOnlineUrlApi.api_getAppUserInfoByUserId + "  请求异常: " + e.getMessage());
                        resultCallBack.onFailure(e, SobotResourceUtils.getResString(mContext, "sobot_online_net_error"));
                    }

                    @Override
                    public void onResponse(String response) {
                        WorkOrderUserResult result = SobotGsonUtil.gsonToBean(response, WorkOrderUserResult.class);
                        if (result != null && !TextUtils.isEmpty(result.getCode()) && OnlineConstant.result_success_code_second.equals(result.getCode())) {
                            resultCallBack.onSuccess(result.getData());
                        } else {
                            checkTokenCloseSobot(result.getCode());
                            resultCallBack.onFailure(new Exception(), TextUtils.isEmpty(result.getMsg()) ? SobotResourceUtils.getResString(mContext, "sobot_online_net_error") : result.getMsg());
                        }
                    }

                    @Override
                    public void inProgress(int progress) {
                    }
                });
    }

    @Override
    public void getAppUserLevelDataInfo(Object cancelTag, final SobotResultCallBack<List<CusFieldDataInfoModel>> resultCallBack) {
        OnlineHttpUtils.getInstance().doGet(cancelTag, SobotOnlineUrlApi.api_getAppUserLevelDataInfo, null,
                new OnlineHttpUtils.StringCallBack() {

                    @Override
                    public void onError(Exception e, String msg, int responseCode) {
                        SobotOnlineLogUtils.i(SobotOnlineUrlApi.api_getAppUserLevelDataInfo + "  请求异常: " + e.getMessage());
                        resultCallBack.onFailure(e, SobotResourceUtils.getResString(mContext, "sobot_online_net_error"));
                    }

                    @Override
                    public void onResponse(String response) {
                        CusFieldDataInfoListResult result = SobotGsonUtil.gsonToBean(response, CusFieldDataInfoListResult.class);
                        if (result != null && !TextUtils.isEmpty(result.getRetCode()) && OnlineConstant.result_success_code.equals(result.getRetCode())) {
                            resultCallBack.onSuccess(result.getItems());
                        } else {
                            checkTokenCloseSobot(result != null ? result.getRetCode() : "");
                            resultCallBack.onFailure(new Exception(), SobotResourceUtils.getResString(mContext, "sobot_online_net_error"));
                        }
                    }

                    @Override
                    public void inProgress(int progress) {
                    }
                });
    }

    @Override
    public void getAppCusFieldConfigInfoList(Object cancelTag, String operateType, String openFlag, final SobotResultCallBack<CusFieldConfigList> resultCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("operateType", operateType);
        map.put("openFlag", openFlag);
        OnlineHttpUtils.getInstance().doGet(cancelTag, SobotOnlineUrlApi.api_getAppCusFieldConfigInfoList, map,
                new OnlineHttpUtils.StringCallBack() {

                    @Override
                    public void onError(Exception e, String msg, int responseCode) {
                        SobotOnlineLogUtils.i(SobotOnlineUrlApi.api_getAppCusFieldConfigInfoList + "  请求异常: " + e.getMessage());
                        resultCallBack.onFailure(e, SobotResourceUtils.getResString(mContext, "sobot_online_net_error"));
                    }

                    @Override
                    public void onResponse(String response) {
                        CusFieldConfigListResult result = SobotGsonUtil.gsonToBean(response, CusFieldConfigListResult.class);
                        if (result != null && !TextUtils.isEmpty(result.getCode()) && OnlineConstant.result_success_code_second.equals(result.getCode())) {
                            resultCallBack.onSuccess(result.getData());
                        } else {
                            checkTokenCloseSobot(result.getCode());
                            resultCallBack.onFailure(new Exception(), TextUtils.isEmpty(result.getMsg()) ? SobotResourceUtils.getResString(mContext, "sobot_online_net_error") : result.getMsg());
                        }
                    }

                    @Override
                    public void inProgress(int progress) {
                    }
                });
    }

    @Override
    public void addAppUserInfo(Object cancelTag, WorkOrderUser user, final SobotResultCallBack<CreateWorkOrderUserResult> resultCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("img", user.getImg());
        map.put("nick", user.getNick());
        map.put("partnerId", user.getPartnerId());
        map.put("uname", user.getUname());
        map.put("visitorIds", user.getVisitorIds());
        map.put("source", user.getSource() + "");
        map.put("isVip", (user.isVip() ? 1 : 0) + "");
        map.put("vipLevel", user.getVipLevel());
        map.put("email", user.getEmail());
        map.put("tel", user.getTel());
        map.put("enterpriseName", user.getEnterpriseName());
        map.put("enterpriseId", user.getEnterpriseId());
        map.put("proviceId", user.getProviceId());
        map.put("proviceName", user.getProviceName());
        map.put("cityId", user.getCityId());
        map.put("cityName", user.getCityName());
        map.put("areaId", user.getAreaId());
        map.put("areaName", user.getAreaName());
        map.put("qq", user.getQq());
        map.put("remark", user.getRemark());
        map.put("customFields", user.getCustomFields());
        OnlineHttpUtils.getInstance().doPost(cancelTag, SobotOnlineUrlApi.api_addAppUserInfo, map,
                new OnlineHttpUtils.StringCallBack() {

                    @Override
                    public void onError(Exception e, String msg, int responseCode) {
                        SobotOnlineLogUtils.i(SobotOnlineUrlApi.api_addAppUserInfo + "  请求异常: " + e.getMessage());
                        resultCallBack.onFailure(e, SobotResourceUtils.getResString(mContext, "sobot_online_net_error"));
                    }

                    @Override
                    public void onResponse(String response) {
                        CreateWorkOrderUserResult result = SobotGsonUtil.gsonToBean(response, CreateWorkOrderUserResult.class);
                        if (result != null && !TextUtils.isEmpty(result.getRetCode()) && OnlineConstant.result_success_code.equals(result.getRetCode())) {
                            resultCallBack.onSuccess(result);
                        } else {
                            checkTokenCloseSobot(result != null ? result.getRetCode() : "");
                            resultCallBack.onFailure(new Exception(), (result == null || TextUtils.isEmpty(result.getRetMsg())) ? SobotResourceUtils.getResString(mContext, "sobot_online_net_error") : result.getRetMsg());
                        }
                    }

                    @Override
                    public void inProgress(int progress) {
                    }
                });
    }

    @Override
    public void updateAppUserInfo(Object cancelTag, WorkOrderUser user, final SobotResultCallBack<EditUserInfoResult> resultCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("id", user.getId());
        map.put("partnerId", user.getPartnerId());
        map.put("img", user.getImg());
        map.put("nick", user.getNick());
        map.put("uname", user.getUname());
        map.put("source", user.getSource() + "");
        map.put("isVip", (user.isVip() ? 1 : 0) + "");
        map.put("vipLevel", user.getVipLevel());
        map.put("email", user.getEmail());
        map.put("tel", user.getTel());
        map.put("enterpriseName", user.getEnterpriseName());
        map.put("enterpriseId", user.getEnterpriseId());
        map.put("proviceId", user.getProviceId());
        map.put("proviceName", user.getProviceName());
        map.put("cityId", user.getCityId());
        map.put("cityName", user.getCityName());
        map.put("areaId", user.getAreaId());
        map.put("areaName", user.getAreaName());
        map.put("qq", user.getQq());
        map.put("remark", user.getRemark());
        map.put("customFields", user.getCustomFields());
        OnlineHttpUtils.getInstance().doPost(cancelTag, SobotOnlineUrlApi.api_updateAppUserInfo, map,
                new OnlineHttpUtils.StringCallBack() {

                    @Override
                    public void onError(Exception e, String msg, int responseCode) {
                        SobotOnlineLogUtils.i(SobotOnlineUrlApi.api_updateAppUserInfo + "  请求异常: " + e.getMessage());
                        resultCallBack.onFailure(e, SobotResourceUtils.getResString(mContext, "sobot_online_net_error"));
                    }

                    @Override
                    public void onResponse(String response) {
                        EditUserInfoResult result = SobotGsonUtil.gsonToBean(response, EditUserInfoResult.class);
                        if (result != null && !TextUtils.isEmpty(result.getRetCode()) && OnlineConstant.result_success_code.equals(result.getRetCode())) {
                            resultCallBack.onSuccess(result);
                        } else {
                            checkTokenCloseSobot(result != null ? result.getRetCode() : "");
                            resultCallBack.onFailure(new Exception(), (result == null || TextUtils.isEmpty(result.getRetMsg())) ? SobotResourceUtils.getResString(mContext, "sobot_online_net_error") : result.getRetMsg());
                        }
                    }

                    @Override
                    public void inProgress(int progress) {
                    }
                });
    }

    @Override
    public void getServiceStatus(Object cancelTag, final SobotResultCallBack<List<OnlineServiceStatus>> resultCallBack) {
        OnlineHttpUtils.getInstance().doPost(cancelTag, SobotOnlineUrlApi.api_getServiceStatus, null,
                new OnlineHttpUtils.StringCallBack() {

                    @Override
                    public void onError(Exception e, String msg, int responseCode) {
                        SobotOnlineLogUtils.i(SobotOnlineUrlApi.api_getServiceStatus + "  请求异常: " + e.getMessage());
                        resultCallBack.onFailure(e, SobotResourceUtils.getResString(mContext, "sobot_online_net_error"));
                    }

                    @Override
                    public void onResponse(String response) {
                        OnlineServiceStatusResult result = SobotGsonUtil.gsonToBean(response, OnlineServiceStatusResult.class);
                        if (result != null && !TextUtils.isEmpty(result.getRetCode()) && OnlineConstant.result_success_code.equals(result.getRetCode())) {
                            resultCallBack.onSuccess(result.getData());
                        } else {
                            checkTokenCloseSobot(result != null ? result.getRetCode() : "");
                            resultCallBack.onFailure(new Exception(), (result == null || TextUtils.isEmpty(result.getRetMsg())) ? SobotResourceUtils.getResString(mContext, "sobot_online_net_error") : result.getRetMsg());
                        }
                    }

                    @Override
                    public void inProgress(int progress) {
                    }
                });
    }

    @Override
    public void busyOrOnline(Object cancelTag, boolean isOnline, String statusCode, final SobotResultCallBack<OnlineBaseCode> resultCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("statusCode", statusCode);
        String urlStr = "";
        if (isOnline) {
            urlStr = SobotOnlineUrlApi.api_online;
        } else {
            urlStr = SobotOnlineUrlApi.api_busy;
        }
        final String finalUrlStr = urlStr;
        OnlineHttpUtils.getInstance().doPost(cancelTag, urlStr, map,
                new OnlineHttpUtils.StringCallBack() {

                    @Override
                    public void onError(Exception e, String msg, int responseCode) {
                        SobotOnlineLogUtils.i(finalUrlStr + "  请求异常: " + e.getMessage());
                        resultCallBack.onFailure(e, SobotResourceUtils.getResString(mContext, "sobot_online_net_error"));
                    }

                    @Override
                    public void onResponse(String response) {
                        OnlineBaseCode result = SobotGsonUtil.gsonToBean(response, OnlineBaseCode.class);
                        if (result != null && !TextUtils.isEmpty(result.getRetCode()) && OnlineConstant.result_success_code.equals(result.getRetCode())) {
                            resultCallBack.onSuccess(result);
                        } else {
                            checkTokenCloseSobot(result != null ? result.getRetCode() : "");
                            resultCallBack.onFailure(new Exception(), (result == null || TextUtils.isEmpty(result.getRetMsg())) ? SobotResourceUtils.getResString(mContext, "sobot_online_net_error") : result.getRetMsg());
                        }
                    }

                    @Override
                    public void inProgress(int progress) {
                    }
                });
    }

    @Override
    public void chopStatus(Object cancelTag, String nowStatus, String cutStatus, String cutReason, String cutFlag, String tid, String cutTime, final SobotResultCallBack<OnlineBaseCode> resultCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("nowStatus", nowStatus);
        map.put("cutStatus", cutStatus);
        map.put("cutReason", cutReason);
        map.put("cutFlag", cutFlag);
        map.put("tid", tid);
        map.put("cutTime", cutTime);
        OnlineHttpUtils.getInstance().doPost(cancelTag, SobotOnlineUrlApi.api_chopStatus, map,
                new OnlineHttpUtils.StringCallBack() {

                    @Override
                    public void onError(Exception e, String msg, int responseCode) {
                        SobotOnlineLogUtils.i(SobotOnlineUrlApi.api_chopStatus + "  请求异常: " + e.getMessage());
                        resultCallBack.onFailure(e, SobotResourceUtils.getResString(mContext, "sobot_online_net_error"));
                    }

                    @Override
                    public void onResponse(String response) {
                        OnlineBaseCode result = SobotGsonUtil.gsonToBean(response, OnlineBaseCode.class);
                        if (result != null && !TextUtils.isEmpty(result.getRetCode()) && OnlineConstant.result_success_code.equals(result.getRetCode())) {
                            resultCallBack.onSuccess(result);
                        } else {
                            checkTokenCloseSobot(result != null ? result.getRetCode() : "");
                            resultCallBack.onFailure(new Exception(), (result == null || TextUtils.isEmpty(result.getRetMsg())) ? SobotResourceUtils.getResString(mContext, "sobot_online_net_error") : result.getRetMsg());
                        }
                    }

                    @Override
                    public void inProgress(int progress) {
                    }
                });
    }

    @Override
    public void out(Object cancelTag, String puid, final SobotResultCallBack<OnlineBaseCode> resultCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", puid);
        OnlineHttpUtils.getInstance().doPost(cancelTag, SobotOnlineUrlApi.api_out, map,
                new OnlineHttpUtils.StringCallBack() {

                    @Override
                    public void onError(Exception e, String msg, int responseCode) {
                        SobotOnlineLogUtils.i(SobotOnlineUrlApi.api_out + "  请求异常: " + e.getMessage());
                        resultCallBack.onFailure(e, SobotResourceUtils.getResString(mContext, "sobot_online_net_error"));
                    }

                    @Override
                    public void onResponse(String response) {
                        OnlineBaseCode result = SobotGsonUtil.gsonToBean(response, OnlineBaseCode.class);
                        if (result != null && !TextUtils.isEmpty(result.getRetCode()) && OnlineConstant.result_success_code.equals(result.getRetCode())) {
                            resultCallBack.onSuccess(result);
                        } else {
                            checkTokenCloseSobot(result != null ? result.getRetCode() : "");
                            resultCallBack.onFailure(new Exception(), (result == null || TextUtils.isEmpty(result.getRetMsg())) ? SobotResourceUtils.getResString(mContext, "sobot_online_net_error") : result.getRetMsg());
                        }
                    }

                    @Override
                    public void inProgress(int progress) {
                    }
                });
    }

    @Override
    public void leave(Object cancelTag, String cid, String userId, final SobotResultCallBack<OnlineBaseCode> resultCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", userId);
        map.put("cid", cid);
        OnlineHttpUtils.getInstance().doPost(cancelTag, SobotOnlineUrlApi.api_leave, map,
                new OnlineHttpUtils.StringCallBack() {

                    @Override
                    public void onError(Exception e, String msg, int responseCode) {
                        SobotOnlineLogUtils.i(SobotOnlineUrlApi.api_leave + "  请求异常: " + e.getMessage());
                        resultCallBack.onFailure(e, SobotResourceUtils.getResString(mContext, "sobot_online_net_error"));
                    }

                    @Override
                    public void onResponse(String response) {
                        OnlineBaseCode result = SobotGsonUtil.gsonToBean(response, OnlineBaseCode.class);
                        if (result != null && !TextUtils.isEmpty(result.getRetCode()) && OnlineConstant.result_success_code.equals(result.getRetCode())) {
                            resultCallBack.onSuccess(result);
                        } else {
                            checkTokenCloseSobot(result != null ? result.getRetCode() : "");
                            resultCallBack.onFailure(new Exception(), (result == null || TextUtils.isEmpty(result.getRetMsg())) ? SobotResourceUtils.getResString(mContext, "sobot_online_net_error") : result.getRetMsg());
                        }
                    }

                    @Override
                    public void inProgress(int progress) {
                    }
                });
    }

    @Override
    public void getOtherAdmins(Object cancelTag, String keyWord, final SobotResultCallBack<List<OnLineServiceModel>> resultCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("keyWord", keyWord);
        OnlineHttpUtils.getInstance().doPost(cancelTag, SobotOnlineUrlApi.api_getOtherAdmins, map,
                new OnlineHttpUtils.StringCallBack() {

                    @Override
                    public void onError(Exception e, String msg, int responseCode) {
                        SobotOnlineLogUtils.i(SobotOnlineUrlApi.api_getOtherAdmins + "  请求异常: " + e.getMessage());
                        resultCallBack.onFailure(e, SobotResourceUtils.getResString(mContext, "sobot_online_net_error"));
                    }

                    @Override
                    public void onResponse(String response) {
                        OnLineServiceModelResult result = SobotGsonUtil.gsonToBean(response, OnLineServiceModelResult.class);
                        if (result != null && !TextUtils.isEmpty(result.getRetCode()) && OnlineConstant.result_success_code.equals(result.getRetCode())) {
                            resultCallBack.onSuccess(result.getData());
                        } else {
                            checkTokenCloseSobot(result != null ? result.getRetCode() : "");
                            resultCallBack.onFailure(new Exception(), (result == null || TextUtils.isEmpty(result.getRetMsg())) ? SobotResourceUtils.getResString(mContext, "sobot_online_net_error") : result.getRetMsg());
                        }
                    }

                    @Override
                    public void inProgress(int progress) {
                    }
                });
    }

    @Override
    public void transfer(Object cancelTag, String cid, String joinId, String uid, final SobotResultCallBack<OnlineBaseCode> resultCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("cid", cid);
        map.put("joinId", joinId);
        map.put("uid", uid);
        OnlineHttpUtils.getInstance().doPost(cancelTag, SobotOnlineUrlApi.api_transfer, map,
                new OnlineHttpUtils.StringCallBack() {

                    @Override
                    public void onError(Exception e, String msg, int responseCode) {
                        SobotOnlineLogUtils.i(SobotOnlineUrlApi.api_transfer + "  请求异常: " + e.getMessage());
                        resultCallBack.onFailure(e, SobotResourceUtils.getResString(mContext, "sobot_online_net_error"));
                    }

                    @Override
                    public void onResponse(String response) {
                        OnlineBaseCode result = SobotGsonUtil.gsonToBean(response, OnlineBaseCode.class);
                        if (result != null && !TextUtils.isEmpty(result.getRetCode()) && OnlineConstant.result_success_code.equals(result.getRetCode())) {
                            resultCallBack.onSuccess(result);
                        } else {
                            checkTokenCloseSobot(result != null ? result.getRetCode() : "");
                            resultCallBack.onFailure(new Exception(), (result == null || TextUtils.isEmpty(result.getRetMsg())) ? SobotResourceUtils.getResString(mContext, "sobot_online_net_error") : result.getRetMsg());
                        }
                    }

                    @Override
                    public void inProgress(int progress) {
                    }
                });
    }

    @Override
    public void queryTransferGroup(Object cancelTag, String keyWord, final SobotResultCallBack<List<OnLineGroupModel>> resultCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("keyWord", keyWord);
        OnlineHttpUtils.getInstance().doPost(cancelTag, SobotOnlineUrlApi.api_queryTransferGroup, map,
                new OnlineHttpUtils.StringCallBack() {

                    @Override
                    public void onError(Exception e, String msg, int responseCode) {
                        SobotOnlineLogUtils.i(SobotOnlineUrlApi.api_queryTransferGroup + "  请求异常: " + e.getMessage());
                        resultCallBack.onFailure(e, SobotResourceUtils.getResString(mContext, "sobot_online_net_error"));
                    }

                    @Override
                    public void onResponse(String response) {
                        OnLineGroupModelResult result = SobotGsonUtil.gsonToBean(response, OnLineGroupModelResult.class);
                        if (result != null && !TextUtils.isEmpty(result.getRetCode()) && OnlineConstant.result_success_code.equals(result.getRetCode())) {
                            resultCallBack.onSuccess(result.getData());
                        } else {
                            checkTokenCloseSobot(result != null ? result.getRetCode() : "");
                            resultCallBack.onFailure(new Exception(), (result == null || TextUtils.isEmpty(result.getRetMsg())) ? SobotResourceUtils.getResString(mContext, "sobot_online_net_error") : result.getRetMsg());
                        }
                    }

                    @Override
                    public void inProgress(int progress) {
                    }
                });
    }

    @Override
    public void transferToGroup(Object cancelTag, String cid, String groupId, String groupName, String uid, final SobotResultCallBack<OnlineBaseCode> resultCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("cid", cid);
        map.put("groupId", groupId);
        map.put("groupName", groupName);
        map.put("uid", uid);
        OnlineHttpUtils.getInstance().doPost(cancelTag, SobotOnlineUrlApi.api_transferToGroup, map,
                new OnlineHttpUtils.StringCallBack() {

                    @Override
                    public void onError(Exception e, String msg, int responseCode) {
                        SobotOnlineLogUtils.i(SobotOnlineUrlApi.api_transferToGroup + "  请求异常: " + e.getMessage());
                        resultCallBack.onFailure(e, SobotResourceUtils.getResString(mContext, "sobot_online_net_error"));
                    }

                    @Override
                    public void onResponse(String response) {
                        OnlineBaseCode result = SobotGsonUtil.gsonToBean(response, OnlineBaseCode.class);
                        if (result != null && !TextUtils.isEmpty(result.getRetCode()) && OnlineConstant.result_success_code.equals(result.getRetCode())) {
                            resultCallBack.onSuccess(result);
                        } else {
                            checkTokenCloseSobot(result != null ? result.getRetCode() : "");
                            resultCallBack.onFailure(new Exception(), (result == null || TextUtils.isEmpty(result.getRetMsg())) ? SobotResourceUtils.getResString(mContext, "sobot_online_net_error") : result.getRetMsg());
                        }
                    }

                    @Override
                    public void inProgress(int progress) {
                    }
                });
    }

    @Override
    public void newReplyGrouplist(Object cancelTag, String adminFlag, final SobotResultCallBack<List<ChatReplyGroupInfoModel>> resultCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("adminFlag", adminFlag);
        map.put("filterReply", "0");
        OnlineHttpUtils.getInstance().doPost(cancelTag, SobotOnlineUrlApi.api_newReplyGrouplist, map,
                new OnlineHttpUtils.StringCallBack() {

                    @Override
                    public void onError(Exception e, String msg, int responseCode) {
                        SobotOnlineLogUtils.i(SobotOnlineUrlApi.api_newReplyGrouplist + "  请求异常: " + e.getMessage());
                        resultCallBack.onFailure(e, SobotResourceUtils.getResString(mContext, "sobot_online_net_error"));
                    }

                    @Override
                    public void onResponse(String response) {
                        ChatReplyGroupInfoModelResult result = SobotGsonUtil.gsonToBean(response, ChatReplyGroupInfoModelResult.class);
                        if (result != null && !TextUtils.isEmpty(result.getRetCode()) && OnlineConstant.result_success_code.equals(result.getRetCode())) {
                            resultCallBack.onSuccess(result.getData());
                        } else {
                            checkTokenCloseSobot(result != null ? result.getRetCode() : "");
                            resultCallBack.onFailure(new Exception(), (result == null || TextUtils.isEmpty(result.getRetMsg())) ? SobotResourceUtils.getResString(mContext, "sobot_online_net_error") : result.getRetMsg());
                        }
                    }

                    @Override
                    public void inProgress(int progress) {
                    }
                });
    }

    @Override
    public void searchReplyByGroupId(Object cancelTag, String groupId, final SobotResultCallBack<List<ChatReplyInfoModel>> resultCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("groupId", groupId);
        OnlineHttpUtils.getInstance().doPost(cancelTag, SobotOnlineUrlApi.api_searchReplyByGroupId, map,
                new OnlineHttpUtils.StringCallBack() {

                    @Override
                    public void onError(Exception e, String msg, int responseCode) {
                        SobotOnlineLogUtils.i(SobotOnlineUrlApi.api_searchReplyByGroupId + "  请求异常: " + e.getMessage());
                        resultCallBack.onFailure(e, SobotResourceUtils.getResString(mContext, "sobot_online_net_error"));
                    }

                    @Override
                    public void onResponse(String response) {
                        ChatReplyInfoModelResult result = SobotGsonUtil.gsonToBean(response, ChatReplyInfoModelResult.class);
                        if (result != null && !TextUtils.isEmpty(result.getRetCode()) && OnlineConstant.result_success_code.equals(result.getRetCode())) {
                            resultCallBack.onSuccess(result.getData());
                        } else {
                            checkTokenCloseSobot(result != null ? result.getRetCode() : "");
                            resultCallBack.onFailure(new Exception(), (result == null || TextUtils.isEmpty(result.getRetMsg())) ? SobotResourceUtils.getResString(mContext, "sobot_online_net_error") : result.getRetMsg());
                        }
                    }

                    @Override
                    public void inProgress(int progress) {
                    }
                });
    }

    @Override
    public void vagueSearchReply(Object cancelTag, String adminFlag, String keyword, final SobotResultCallBack<List<ChatReplyInfoModel>> resultCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("adminFlag", adminFlag);
        map.put("keyword", keyword);
        OnlineHttpUtils.getInstance().doPost(cancelTag, SobotOnlineUrlApi.api_vagueSearchReply, map,
                new OnlineHttpUtils.StringCallBack() {

                    @Override
                    public void onError(Exception e, String msg, int responseCode) {
                        SobotOnlineLogUtils.i(SobotOnlineUrlApi.api_vagueSearchReply + "  请求异常: " + e.getMessage());
                        resultCallBack.onFailure(e, SobotResourceUtils.getResString(mContext, "sobot_online_net_error"));
                    }

                    @Override
                    public void onResponse(String response) {
                        ChatReplyInfoModelResult result = SobotGsonUtil.gsonToBean(response, ChatReplyInfoModelResult.class);
                        if (result != null && !TextUtils.isEmpty(result.getRetCode()) && OnlineConstant.result_success_code.equals(result.getRetCode())) {
                            resultCallBack.onSuccess(result.getData());
                        } else {
                            checkTokenCloseSobot(result != null ? result.getRetCode() : "");
                            resultCallBack.onFailure(new Exception(), (result == null || TextUtils.isEmpty(result.getRetMsg())) ? SobotResourceUtils.getResString(mContext, "sobot_online_net_error") : result.getRetMsg());
                        }
                    }

                    @Override
                    public void inProgress(int progress) {
                    }
                });
    }

    @Override
    public void getSmartReplys(Object cancelTag, String requestText, String rebotFlags, final SobotResultCallBack<List<RebotSmartAnswerModel>> resultCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("requestText", requestText);
        map.put("robotFlag", rebotFlags);
        OnlineHttpUtils.getInstance().doPost(cancelTag, SobotOnlineUrlApi.api_getSmartReplys, map,
                new OnlineHttpUtils.StringCallBack() {

                    @Override
                    public void onError(Exception e, String msg, int responseCode) {
                        SobotOnlineLogUtils.i(SobotOnlineUrlApi.api_getSmartReplys + "  请求异常: " + e.getMessage());
                        resultCallBack.onFailure(e, SobotResourceUtils.getResString(mContext, "sobot_online_net_error"));
                    }

                    @Override
                    public void onResponse(String response) {
                        RebotSmartAnswerModelResult result = SobotGsonUtil.gsonToBean(response, RebotSmartAnswerModelResult.class);
                        if (result != null && !TextUtils.isEmpty(result.getRetCode()) && OnlineConstant.result_success_code.equals(result.getRetCode())) {
                            resultCallBack.onSuccess(result.getData());
                        } else {
                            checkTokenCloseSobot(result != null ? result.getRetCode() : "");
                            resultCallBack.onFailure(new Exception(), (result == null || TextUtils.isEmpty(result.getRetMsg())) ? SobotResourceUtils.getResString(mContext, "sobot_online_net_error") : result.getRetMsg());
                        }
                    }

                    @Override
                    public void inProgress(int progress) {
                    }
                });
    }

    @Override
    public void innerInternalChat(Object cancelTag, String content, final SobotResultCallBack<List<RebotSmartAnswerModel>> resultCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("content", content);
        OnlineHttpUtils.getInstance().doPost(cancelTag, SobotOnlineUrlApi.api_innerInternalChat, map,
                new OnlineHttpUtils.StringCallBack() {

                    @Override
                    public void onError(Exception e, String msg, int responseCode) {
                        SobotOnlineLogUtils.i(SobotOnlineUrlApi.api_innerInternalChat + "  请求异常: " + e.getMessage());
                        resultCallBack.onFailure(e, SobotResourceUtils.getResString(mContext, "sobot_online_net_error"));
                    }

                    @Override
                    public void onResponse(String response) {
                        RebotSmartAnswerModelResult result = SobotGsonUtil.gsonToBean(response, RebotSmartAnswerModelResult.class);
                        if (result != null && !TextUtils.isEmpty(result.getRetCode()) && OnlineConstant.result_success_code.equals(result.getRetCode())) {
                            resultCallBack.onSuccess(result.getData());
                        } else {
                            checkTokenCloseSobot(result != null ? result.getRetCode() : "");
                            resultCallBack.onFailure(new Exception(), (result == null || TextUtils.isEmpty(result.getRetMsg())) ? SobotResourceUtils.getResString(mContext, "sobot_online_net_error") : result.getRetMsg());
                        }
                    }

                    @Override
                    public void inProgress(int progress) {
                    }
                });
    }

    @Override
    public void modifyAdminConfig(Object cancelTag, String chooseRobotIds, final SobotResultCallBack<OnlineBaseCode> resultCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("chooseRobotIds", chooseRobotIds);
        OnlineHttpUtils.getInstance().doPost(cancelTag, SobotOnlineUrlApi.api_modifyAdminConfig, map,
                new OnlineHttpUtils.StringCallBack() {

                    @Override
                    public void onError(Exception e, String msg, int responseCode) {
                        SobotOnlineLogUtils.i(SobotOnlineUrlApi.api_modifyAdminConfig + "  请求异常: " + e.getMessage());
                        resultCallBack.onFailure(e, SobotResourceUtils.getResString(mContext, "sobot_online_net_error"));
                    }

                    @Override
                    public void onResponse(String response) {
                        OnlineBaseCode result = SobotGsonUtil.gsonToBean(response, OnlineBaseCode.class);
                        if (result != null && !TextUtils.isEmpty(result.getRetCode()) && OnlineConstant.result_success_code.equals(result.getRetCode())) {
                            resultCallBack.onSuccess(result);
                        } else {
                            checkTokenCloseSobot(result != null ? result.getRetCode() : "");
                            resultCallBack.onFailure(new Exception(), (result == null || TextUtils.isEmpty(result.getRetMsg())) ? SobotResourceUtils.getResString(mContext, "sobot_online_net_error") : result.getRetMsg());
                        }
                    }

                    @Override
                    public void inProgress(int progress) {
                    }
                });
    }

    @Override
    public void summarySubmit(Object cancelTag, SummaryModel summaryModel, final SobotResultCallBack<OnlineBaseCode> resultCallBack) {
        Map<String, String> map = new HashMap<>();
        if (!TextUtils.isEmpty(summaryModel.getOperationId())) {
            map.put("operationId", summaryModel.getOperationId());
        }
        if (!TextUtils.isEmpty(summaryModel.getOperationName())) {
            map.put("operationName", summaryModel.getOperationName());
        }

        if (!TextUtils.isEmpty(summaryModel.getReqTypeId())) {
            map.put("reqTypeId", summaryModel.getReqTypeId());
        }
        if (!TextUtils.isEmpty(summaryModel.getQuestionDescribe())) {
            map.put("questionDescribe", summaryModel.getQuestionDescribe());
        }

        if (-1 != summaryModel.getQuestionStatus()) {
            map.put("questionStatus", summaryModel.getQuestionStatus() + "");
        }

        if (summaryModel.isInvalidSession()) {
            map.put("invalidSession", "1");
        }

        map.put("uid", summaryModel.getUid());
        map.put("cid", summaryModel.getCid());
        OnlineHttpUtils.getInstance().doPost(cancelTag, SobotOnlineUrlApi.api_summarySubmit, map,
                new OnlineHttpUtils.StringCallBack() {

                    @Override
                    public void onError(Exception e, String msg, int responseCode) {
                        SobotOnlineLogUtils.i(SobotOnlineUrlApi.api_summarySubmit + "  请求异常: " + e.getMessage());
                        resultCallBack.onFailure(e, SobotResourceUtils.getResString(mContext, "sobot_online_net_error"));
                    }

                    @Override
                    public void onResponse(String response) {
                        OnlineBaseCode result = SobotGsonUtil.gsonToBean(response, OnlineBaseCode.class);
                        if (result != null && !TextUtils.isEmpty(result.getRetCode()) && OnlineConstant.result_success_code.equals(result.getRetCode())) {
                            resultCallBack.onSuccess(result);
                        } else {
                            checkTokenCloseSobot(result != null ? result.getRetCode() : "");
                            resultCallBack.onFailure(new Exception(), (result == null || TextUtils.isEmpty(result.getRetMsg())) ? SobotResourceUtils.getResString(mContext, "sobot_online_net_error") : result.getRetMsg());
                        }
                    }

                    @Override
                    public void inProgress(int progress) {
                    }
                });
    }

    @Override
    public void queryUnit(Object cancelTag, String keyword, final SobotResultCallBack<List<UnitInfoModel>> resultCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("keyword", keyword);
        OnlineHttpUtils.getInstance().doPost(cancelTag, SobotOnlineUrlApi.api_queryUnit, map,
                new OnlineHttpUtils.StringCallBack() {

                    @Override
                    public void onError(Exception e, String msg, int responseCode) {
                        SobotOnlineLogUtils.i(SobotOnlineUrlApi.api_queryUnit + "  请求异常: " + e.getMessage());
                        resultCallBack.onFailure(e, SobotResourceUtils.getResString(mContext, "sobot_online_net_error"));
                    }

                    @Override
                    public void onResponse(String response) {
                        UnitInfoModelResult result = SobotGsonUtil.gsonToBean(response, UnitInfoModelResult.class);
                        if (result != null && !TextUtils.isEmpty(result.getRetCode()) && OnlineConstant.result_success_code.equals(result.getRetCode())) {
                            resultCallBack.onSuccess(result.getData());
                        } else {
                            checkTokenCloseSobot(result != null ? result.getRetCode() : "");
                            resultCallBack.onFailure(new Exception(), (result == null || TextUtils.isEmpty(result.getRetMsg())) ? SobotResourceUtils.getResString(mContext, "sobot_online_net_error") : result.getRetMsg());
                        }
                    }

                    @Override
                    public void inProgress(int progress) {
                    }
                });
    }

    @Override
    public void getUnifoInfoById(Object cancelTag, String unitId, String typeId, final SobotResultCallBack<UnitTypeAndFieldModel> resultCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("unitId", unitId);
        map.put("typeId", typeId);
        OnlineHttpUtils.getInstance().doPost(cancelTag, SobotOnlineUrlApi.api_getUnifoInfoById, map,
                new OnlineHttpUtils.StringCallBack() {

                    @Override
                    public void onError(Exception e, String msg, int responseCode) {
                        SobotOnlineLogUtils.i(SobotOnlineUrlApi.api_getUnifoInfoById + "  请求异常: " + e.getMessage());
                        resultCallBack.onFailure(e, SobotResourceUtils.getResString(mContext, "sobot_online_net_error"));
                    }

                    @Override
                    public void onResponse(String response) {
                        UnitTypeInfoModelResult result = SobotGsonUtil.gsonToBean(response, UnitTypeInfoModelResult.class);
                        if (result != null && !TextUtils.isEmpty(result.getRetCode()) && OnlineConstant.result_success_code.equals(result.getRetCode())) {
                            resultCallBack.onSuccess(result.getData());
                        } else {
                            checkTokenCloseSobot(result != null ? result.getRetCode() : "");
                            resultCallBack.onFailure(new Exception(), (result == null || TextUtils.isEmpty(result.getRetMsg())) ? SobotResourceUtils.getResString(mContext, "sobot_online_net_error") : result.getRetMsg());
                        }
                    }

                    @Override
                    public void inProgress(int progress) {
                    }
                });
    }

    @Override
    public void queryConversation(Object cancelTag, String cid, final SobotResultCallBack<SummaryInfoModelResult.SummaryInfoModel> resultCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("cid", cid);
        OnlineHttpUtils.getInstance().doPost(cancelTag, SobotOnlineUrlApi.api_queryConversation, map,
                new OnlineHttpUtils.StringCallBack() {

                    @Override
                    public void onError(Exception e, String msg, int responseCode) {
                        SobotOnlineLogUtils.i(SobotOnlineUrlApi.api_queryConversation + "  请求异常: " + e.getMessage());
                        resultCallBack.onFailure(e, SobotResourceUtils.getResString(mContext, "sobot_online_net_error"));
                    }

                    @Override
                    public void onResponse(String response) {
                        SummaryInfoModelResult result = SobotGsonUtil.gsonToBean(response, SummaryInfoModelResult.class);
                        if (result != null && !TextUtils.isEmpty(result.getRetCode()) && OnlineConstant.result_success_code.equals(result.getRetCode())) {
                            resultCallBack.onSuccess(result.getData());
                        } else {
                            checkTokenCloseSobot(result != null ? result.getRetCode() : "");
                            resultCallBack.onFailure(new Exception(), (result == null || TextUtils.isEmpty(result.getRetMsg())) ? SobotResourceUtils.getResString(mContext, "sobot_online_net_error") : result.getRetMsg());
                        }
                    }

                    @Override
                    public void inProgress(int progress) {
                    }
                });
    }

    @Override
    public void selectOperationType(Object cancelTag, String cid, String keyword, final SobotResultCallBack<List<UnitInfoModel>> resultCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("keyword", keyword);
        map.put("cid", cid);
        OnlineHttpUtils.getInstance().doPost(cancelTag, SobotOnlineUrlApi.api_selectOperationType, map,
                new OnlineHttpUtils.StringCallBack() {

                    @Override
                    public void onError(Exception e, String msg, int responseCode) {
                        SobotOnlineLogUtils.i(SobotOnlineUrlApi.api_selectOperationType + "  请求异常: " + e.getMessage());
                        resultCallBack.onFailure(e, SobotResourceUtils.getResString(mContext, "sobot_online_net_error"));
                    }

                    @Override
                    public void onResponse(String response) {
                        UnitInfoModelResult result = SobotGsonUtil.gsonToBean(response, UnitInfoModelResult.class);
                        if (result != null && !TextUtils.isEmpty(result.getRetCode()) && OnlineConstant.result_success_code.equals(result.getRetCode())) {
                            resultCallBack.onSuccess(result.getData());
                        } else {
                            checkTokenCloseSobot(result != null ? result.getRetCode() : "");
                            resultCallBack.onFailure(new Exception(), (result == null || TextUtils.isEmpty(result.getRetMsg())) ? SobotResourceUtils.getResString(mContext, "sobot_online_net_error") : result.getRetMsg());
                        }
                    }

                    @Override
                    public void inProgress(int progress) {
                    }
                });
    }

    @Override
    public void revokeMsg(Object cancelTag, String msgId, String uid, String cid, final SobotResultCallBack<OnlineCommonModel> resultCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("msgId", msgId);
        map.put("uid", uid);
        map.put("cid", cid);
        OnlineHttpUtils.getInstance().doPost(cancelTag, SobotOnlineUrlApi.api_revokeMsg, map,
                new OnlineHttpUtils.StringCallBack() {

                    @Override
                    public void onError(Exception e, String msg, int responseCode) {
                        SobotOnlineLogUtils.i(SobotOnlineUrlApi.api_revokeMsg + "  请求异常: " + e.getMessage());
                        resultCallBack.onFailure(e, SobotResourceUtils.getResString(mContext, "sobot_online_net_error"));
                    }

                    @Override
                    public void onResponse(String response) {
                        OnlineCommonModelResult result = SobotGsonUtil.gsonToBean(response, OnlineCommonModelResult.class);
                        if (result != null && !TextUtils.isEmpty(result.getRetCode()) && OnlineConstant.result_success_code.equals(result.getRetCode())) {
                            resultCallBack.onSuccess(result.getData());
                        } else {
                            if (result != null && !TextUtils.isEmpty(result.getRetMsg())) {
                                resultCallBack.onFailure(new Exception(), result.getRetMsg());
                            } else {
                                checkTokenCloseSobot(result != null ? result.getRetCode() : "");
                                resultCallBack.onFailure(new Exception(), (result == null || TextUtils.isEmpty(result.getRetMsg())) ? SobotResourceUtils.getResString(mContext, "sobot_online_net_error") : result.getRetMsg());
                            }
                        }
                    }

                    @Override
                    public void inProgress(int progress) {
                    }
                });
    }


    //检查用户超时关闭所有界面，跳转到登录页面
    private void checkTokenCloseSobot(String responseCode) {
        if (!TextUtils.isEmpty(responseCode) && ("900002".equals(responseCode) || "999998".equals(responseCode))) {
            //清除缓存用户信息
            OnlineMsgManager.getInstance(SobotUtils.getApp()).setCustomerServiceInfoModel(null);
            SobotSPUtils.getInstance().remove(SOBOT_CUSTOM_USER);
            //离线成功返回登录界面
            SobotGlobalContext.getInstance().finishAllActivity();
            String appid = SobotSPUtils.getInstance().getString(OnlineConstant.ONLINE_APPID);
            String appkey = SobotSPUtils.getInstance().getString(OnlineConstant.ONLINE_APPKEY);
            String account = SobotSPUtils.getInstance().getString(OnlineConstant.SOBOT_CUSTOM_ACCOUNT);
            SobotOnlineService.startAuthWithAcount(SobotUtils.getApp(), appid, appkey, account, -1);
        }
    }
}