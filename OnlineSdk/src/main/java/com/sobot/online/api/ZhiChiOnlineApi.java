package com.sobot.online.api;

import com.sobot.online.model.ChatMessageModel;
import com.sobot.online.model.ChatReplyGroupInfoModel;
import com.sobot.online.model.ChatReplyInfoModel;
import com.sobot.online.model.CidsModel;
import com.sobot.online.model.ConversationConfigModelResult;
import com.sobot.online.model.CreateWorkOrderUserResult;
import com.sobot.online.model.CusFieldConfigList;
import com.sobot.online.model.CusFieldDataInfoModel;
import com.sobot.online.model.EditUserInfoResult;
import com.sobot.online.model.HistoryChatModel;
import com.sobot.online.model.HistoryUserInfoModel;
import com.sobot.online.model.OfflineMsgModel;
import com.sobot.online.model.OnLineGroupModel;
import com.sobot.online.model.OnLineServiceModel;
import com.sobot.online.model.OnlineCommonModel;
import com.sobot.online.model.OnlineEnterPriseModel;
import com.sobot.online.model.OnlineMsgModelResult;
import com.sobot.online.model.OnlineServiceStatus;
import com.sobot.online.model.OnlineTokenModel;
import com.sobot.online.model.QueueUserModel;
import com.sobot.online.model.RebotSmartAnswerModel;
import com.sobot.online.model.SummaryInfoModelResult;
import com.sobot.online.model.SummaryModel;
import com.sobot.online.model.SynChronousModel;
import com.sobot.online.model.UnitInfoModel;
import com.sobot.online.model.UnitTypeAndFieldModel;
import com.sobot.online.model.UserConversationModel;
import com.sobot.online.model.UserInfoModel;
import com.sobot.online.model.WorkOrderUser;
import com.sobot.onlinecommon.control.CustomerServiceInfoModel;
import com.sobot.onlinecommon.frame.http.callback.SobotFileResultCallBack;
import com.sobot.onlinecommon.frame.http.callback.SobotResultCallBack;

import java.util.List;
import java.util.Map;

/**
 * 对外输出接口类
 */
public interface ZhiChiOnlineApi {

    /**
     * 获取token接口
     */
    void getToken(Object cancelTag, Map param, SobotResultCallBack<OnlineTokenModel> resultCallBack);

    /**
     * 在线登录接口
     */
    void login(Object cancelTag, String account, String loginStatus, String token, SobotResultCallBack<CustomerServiceInfoModel> resultCallBack);

    /**
     * 获取用户配置信息
     */
    void conversationConfig(Object cancelTag, SobotResultCallBack<ConversationConfigModelResult.ConversationConfigModel> resultCallBack);

    /**
     * 初始化接口
     */
    void synChronous(Object cancelTag, SobotResultCallBack<SynChronousModel> resultCallBack);

    /**
     * 查询排队接口
     *
     * @param pageNum
     * @param pageSize
     */
    void queryWaitUser(Object cancelTag, int pageNum, int pageSize,
                       SobotResultCallBack<QueueUserModel> resultCallBack);


    /**
     * 查询用户历史会话列表接口
     *
     * @param pageNum
     * @param pageSize
     * @param type     0 全部 1 星标 2 黑名单
     */
    void queryConversationList(Object cancelTag, int pageNum, int pageSize, int type, Map params, SobotResultCallBack<List<HistoryUserInfoModel>> resultCallBack);

    /**
     * 查询历史会话列表接口
     */
    void getHistoryChatList(Object cancelTag, int pageNum, int pageSize, Map patams, SobotResultCallBack<List<HistoryChatModel>> resultCallBack);


    /**
     * 查询cid列表
     *
     * @param cancelTag
     * @param uid       用户id
     */
    void queryCids(Object cancelTag, String uid, SobotResultCallBack<CidsModel> resultCallBack);

    /**
     * 获取聊天记录接口
     * 根据CID查询
     */
    void queryHistoryRecords(Object cancelTag, Map<String, String> map, SobotResultCallBack<List<ChatMessageModel>> resultCallBack);

    /**
     * 邀请接口
     *
     * @param userId
     */
    void invite(Object cancelTag, String userId, SobotResultCallBack<OnlineCommonModel> resultCallBack);

    /**
     * 发送聊天信息接口
     */
    void send(Object cancelTag, boolean isOffline, Map<String, String> map, String path, SobotFileResultCallBack<OnlineMsgModelResult>
            resultCallBack);

    /**
     * 离线消息   获取用户目前状态
     *
     * @param uid
     */
    void getStatusNow(Object cancelTag, String uid, final SobotResultCallBack<OfflineMsgModel> resultCallBack);

    /**
     * 根据url的不同，执行不同的操作
     * 添加或解除黑名单接口
     *
     * @param userId 用户id
     * @param reason 拉黑原因
     * @param url    执行的请求连接
     * @param type   1 24小时拉黑，其它情况永久拉黑
     */
    void addOrDeleteBlackList(Object cancelTag, String userId, String reason, int type, String url, SobotResultCallBack<OnlineBaseCode> resultCallBack);


    /**
     * 添加星标或解除星标接口
     *
     * @param userId
     */
    void addOrDeleteMarkList(Object cancelTag, String userId, String url, SobotResultCallBack<OnlineBaseCode> resultCallBack);


    /**
     * 客服主动邀请评价
     *
     * @param cid
     */
    void invateEvaluateInt(Object cancelTag, String cid, SobotResultCallBack<OnlineCommonModel> resultCallBack);

    /**
     * 下线主动邀请评价
     *
     * @param cid
     */
    void invateEvaluateIntOff(Object cancelTag, String cid, SobotResultCallBack<OnlineCommonModel> resultCallBack);


    /**
     * 获取用户会话信息
     *
     * @param uid
     * @param cid
     */
    void queryConMsg(Object cancelTag, String uid, String cid, SobotResultCallBack<UserConversationModel> resultCallBack);

    /**
     * 获取用户信息
     *
     * @param userId 用户id
     */
    void getUserInfo(Object cancelTag, String userId, SobotResultCallBack<UserInfoModel> resultCallBack);

    /**
     * 获取公司列表
     */
    void getAppEnterpriseList(Object cancelTag, int pageNum, int pageSize, String keyword, SobotResultCallBack<List<OnlineEnterPriseModel>> resultCallBack);

    /**
     * 查询用户详细信息
     *
     * @param userId 用户id
     */
    void getAppUserInfoByUserId(Object cancelTag, String userId, SobotResultCallBack<WorkOrderUser> resultCallBack);

    /**
     * 取得客户VIP等级列表
     */
    void getAppUserLevelDataInfo(Object cancelTag, SobotResultCallBack<List<CusFieldDataInfoModel>> resultCallBack);

    /**
     * 新增客户时，查询客户自定义字段
     *
     * @param operateType 1 用户字段  2 公司字段 3 工单字段
     * @param openFlag    0 未开启  1开启
     */
    void getAppCusFieldConfigInfoList(Object cancelTag, String operateType, String openFlag, SobotResultCallBack<CusFieldConfigList> resultCallBack);


    /**
     * 创建用户的接口   和   客户中心  添加用户的接口，是同一个
     *
     * @param user 新增客户信息
     */
    void addAppUserInfo(Object cancelTag, WorkOrderUser user, SobotResultCallBack<CreateWorkOrderUserResult> resultCallBack);

    /**
     * 编辑客户信息
     *
     * @param user 客户
     */
    void updateAppUserInfo(Object cancelTag, WorkOrderUser user, SobotResultCallBack<EditUserInfoResult> resultCallBack);

    /**
     * 查询客服自定义状态  (获取当前客服的状态值)
     */
    void getServiceStatus(Object cancelTag, SobotResultCallBack<List<OnlineServiceStatus>> resultCallBack);

    /**
     * 客服切换在线接口
     */
    void busyOrOnline(Object cancelTag, boolean isOnline, String statusCode, SobotResultCallBack<OnlineBaseCode> resultCallBack);

    /**
     * 客服切换登录状态申请
     */
    void chopStatus(Object cancelTag, String nowStatus, String cutStatus, String cutReason, String cutFlag, String tid, String cutTime,SobotResultCallBack<OnlineBaseCode> resultCallBack);

    /**
     * 客服下线接口
     */
    void out(Object cancelTag, String puid, SobotResultCallBack<OnlineBaseCode> resultCallBack);

    /**
     * 管理员移除用户接口
     */
    void leave(Object cancelTag, String cid, String userId, SobotResultCallBack<OnlineBaseCode> resultCallBack);

    /**
     * 查询其它客服
     */
    void getOtherAdmins(Object cancelTag, String keyWord, SobotResultCallBack<List<OnLineServiceModel>> resultCallBack);

    /**
     * 转接接口
     */
    void transfer(Object cancelTag, String cid, String joinId, String uid, SobotResultCallBack<OnlineBaseCode> resultCallBack);

    /**
     * 查询客服组
     */
    void queryTransferGroup(Object cancelTag, String keyWord, SobotResultCallBack<List<OnLineGroupModel>> resultCallBack);

    /**
     * 转接客服组
     */
    void transferToGroup(Object cancelTag, String cid, String groupId, String groupName, String uid, SobotResultCallBack<OnlineBaseCode> resultCallBack);

    /**
     * 快捷回复分组查询
     *
     * @param adminFlag adminFlag 0:客服查询  1:公用查询
     */
    void newReplyGrouplist(Object cancelTag, String adminFlag, SobotResultCallBack<List<ChatReplyGroupInfoModel>> resultCallBack);

    /**
     * 根据分组id查询快捷回复内容列表
     */
    void searchReplyByGroupId(Object cancelTag, String groupId, SobotResultCallBack<List<ChatReplyInfoModel>> resultCallBack);

    /**
     * 根据关键字模糊查询快捷回复内容列表
     */
    void vagueSearchReply(Object cancelTag, String adminFlag, String keyword, SobotResultCallBack<List<ChatReplyInfoModel>> resultCallBack);

    /**
     * 智能回复机器人知识库查询
     */
    void getSmartReplys(Object cancelTag, String requestText, String rebotFlags, SobotResultCallBack<List<RebotSmartAnswerModel>> resultCallBack);

    /**
     * 智能回复内部知识库查询
     */
    void innerInternalChat(Object cancelTag, String content, SobotResultCallBack<List<RebotSmartAnswerModel>> resultCallBack);

    /**
     * 智能回复选择机器人
     */
    void modifyAdminConfig(Object cancelTag, String chooseRobotIds, SobotResultCallBack<OnlineBaseCode> resultCallBack);

    /**
     * 提交咨询总结
     */
    void summarySubmit(Object cancelTag, SummaryModel summaryModel, SobotResultCallBack<OnlineBaseCode> resultCallBack);

    /**
     * 查询咨询业务
     */
    void queryUnit(Object cancelTag, String keyword, SobotResultCallBack<List<UnitInfoModel>> resultCallBack);

    /**
     * 根据单元id查询业务类型
     */
    void getUnifoInfoById(Object cancelTag, String unitId, String typeId, SobotResultCallBack<UnitTypeAndFieldModel> resultCallBack);

    /**
     * 查询咨询总结
     */
    void queryConversation(Object cancelTag, String cid, SobotResultCallBack<SummaryInfoModelResult.SummaryInfoModel> resultCallBack);

    /**
     * 所属业务和业务类型模糊查询
     */
    void selectOperationType(Object cancelTag, String cid, String keyword, SobotResultCallBack<List<UnitInfoModel>> resultCallBack);


    /**
     * 消息撤回
     */
    void revokeMsg(Object cancelTag, String msgId, String uid, String cid, SobotResultCallBack<OnlineCommonModel> resultCallBack);

}
