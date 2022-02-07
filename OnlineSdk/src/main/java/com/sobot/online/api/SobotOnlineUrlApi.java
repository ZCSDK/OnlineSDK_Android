package com.sobot.online.api;

public interface SobotOnlineUrlApi {

    String VERSION = "1.0.2";

    //------------ 1.0 接口 ------------

    // 获取访问token编码
    String api_getToken = SobotOnlineBaseUrl.getApi_Host() + "api/get_token";
    // 进线用户列表 同步加载接口
    String api_login = SobotOnlineBaseUrl.getBaseIp() + "login";
    // 获取用户配置信息接口
    String api_conversationConfig = SobotOnlineBaseUrl.getBaseIp() + "conversationConfig";
    // 进线用户列表 同步加载接口
    String api_synChronous = SobotOnlineBaseUrl.getBaseIp() + "synChronous";
    // 查询排队用户接口
    String api_queryWaitUser = SobotOnlineBaseUrl.getBaseIp() + "queryWaitUser";
    //查询星标接口
    String api_queryMarkList = SobotOnlineBaseUrl.getBaseIp() + "queryUserMark";
    //查看黑名单列表
    String api_queryBlackList = SobotOnlineBaseUrl.getBaseIp() + "queryUserBlack";
    //查看历史用户接口
    String api_getHistroryUser = SobotOnlineBaseUrl.getBaseIp() + "getHistoryUser";
    //查看历史会话接口
    String api_getHistoryChat = SobotOnlineBaseUrl.getBaseIp() + "getHistoryChat";
    //查询cid列表
    String api_queryCids = SobotOnlineBaseUrl.getBaseIp() + "queryCids";
    //据cid 查询聊天记录
    String api_queryHistoryRecords = SobotOnlineBaseUrl.getBaseIp() + "queryHistoryRecords";
    //邀请
    String api_invite = SobotOnlineBaseUrl.getBaseIp() + "invite";
    //消息撤回
    String api_revokeMsg = SobotOnlineBaseUrl.getBaseIp() + "revokeMsg";

    //发送聊天的信息
    String api_send = SobotOnlineBaseUrl.getBaseIp() + "send";
    //发送离线信息
    String api_sendOfflineMsg = SobotOnlineBaseUrl.getBaseIp() + "sendOfflineMsg";

    // 发送文件接口
    String api_fileUpload = SobotOnlineBaseUrl.getBaseIp() + "fileUpload";

    // 发送离线文件接口
    String api_sendOfflineFile = SobotOnlineBaseUrl.getBaseIp() + "sendOfflineFile";


    //离线消息  获取用户目前状态
    String api_getStatusNow = SobotOnlineBaseUrl.getBaseIp() + "getStatusNow";

    //添加黑名单 String userId,String reason,String type(1 24小时拉黑)
    String api_addBlackList = SobotOnlineBaseUrl.getBaseIp() + "addUserBlack";

    //删除黑名单 String userId
    String api_deleteBlackList = SobotOnlineBaseUrl.getBaseIp() + "delUserBlack";

    //添加星标接口
    String api_addMarkList = SobotOnlineBaseUrl.getBaseIp() + "addUserMark";

    //删除星标
    String api_deleteMarkList = SobotOnlineBaseUrl.getBaseIp() + "delUserMark";

    //查询星标
    String api_queryUserMark = SobotOnlineBaseUrl.getBaseIp() + "queryUserMark";

    //主动邀请评价
    String api_reComment = SobotOnlineBaseUrl.getBaseIp() + "reComment";
    // 主动邀请评价offlin
    String api_isComment = SobotOnlineBaseUrl.getBaseIp() + "isComment";
    /*查询会话信息接口*/
    String api_queryConMsg = SobotOnlineBaseUrl.getBaseIp() + "queryConMsg";
    // 获取用户信息
    String api_getUserInfo = SobotOnlineBaseUrl.getBaseIp() + "getUserInfo";
    /*获取用户详情*/
    String api_getAppUserInfoByUserId = SobotOnlineBaseUrl.getApi_Host() + "basic/getAppUserInfoByUserId/4";
    /*新增客户时，查询客户自定义字段*/
    String api_getAppCusFieldConfigInfoList = SobotOnlineBaseUrl.getApi_Host() + "basic-set/getAppCusFieldConfigInfoList/4";
    //获取公司列表
    String api_getAppEnterpriseList = SobotOnlineBaseUrl.getApi_Host() + "basic-customer/getAppEnterpriseList/4";
    //获取客户VIP等级列表
    String api_getAppUserLevelDataInfo = SobotOnlineBaseUrl.getApi_Host() + "basic-set/getAppUserLevelDataInfo/4";

    //创建工单选择对应用户时，创建用户的接口
    String api_addAppUserInfo = SobotOnlineBaseUrl.getApi_Host() + "basic-customer/addAppUserInfo/4";
    //编辑更新客户信息
    String api_updateAppUserInfo = SobotOnlineBaseUrl.getApi_Host() + "basic-customer/updateAppUserInfo/4";

    //查询客服自定义状态接口  (获取当前客服的状态值)
    String api_getServiceStatus = SobotOnlineBaseUrl.getBaseIp() + "getServiceStatus";

    // 客服切换到忙碌的状态
    String api_busy = SobotOnlineBaseUrl.getBaseIp() + "busy";
    // 客服转换到在线的状态
    String api_online = SobotOnlineBaseUrl.getBaseIp() + "online";
    // 客服下线的接口
    String api_out = SobotOnlineBaseUrl.getBaseIp() + "out";
    // 管理员强制用户下线的接口
    String api_leave = SobotOnlineBaseUrl.getBaseIp() + "leave";
    // 客服切换登录状态（空闲和离线除外）
    String api_chopStatus = SobotOnlineBaseUrl.getBaseIp() + "chopStatus";


    //查询客服
    String api_getOtherAdmins = SobotOnlineBaseUrl.getBaseIp() + "getOtherAdmins";
    //查询客服组
    String api_queryTransferGroup = SobotOnlineBaseUrl.getBaseIp() + "queryTransferGroup";
    //转接客服
    String api_transfer = SobotOnlineBaseUrl.getBaseIp() + "transfer";
    //转接客服组
    String api_transferToGroup = SobotOnlineBaseUrl.getBaseIp() + "transferToGroup";

    //快捷回复查询
    String api_newReplyGrouplist = SobotOnlineBaseUrl.getBaseIp() + "newReplyGrouplist";
    //根据分组id查询快捷回复内容列表
    String api_searchReplyByGroupId = SobotOnlineBaseUrl.getBaseIp() + "searchReplyByGroupId";
    //根据关键字模糊查询快捷回复内容列表
    String api_vagueSearchReply = SobotOnlineBaseUrl.getBaseIp() + "vagueSearchReply";

    //智能回复机器人知识库查询
    String api_getSmartReplys = SobotOnlineBaseUrl.getBaseIp() + "getSmartReplys";
    //智能回复内部知识库查询
    String api_innerInternalChat = SobotOnlineBaseUrl.getBaseIp() + "innerInternalChat";
    //智能回复选择机器人
    String api_modifyAdminConfig = SobotOnlineBaseUrl.getBaseIp() + "modifyAdminConfig";

    //提交咨询总结
    String api_summarySubmit = SobotOnlineBaseUrl.getBaseIp() + "summarySubmit";
    //查询服务总结业务
    String api_queryUnit = SobotOnlineBaseUrl.getBaseIp() + "queryUnit";
    //根据单元id查询服务总结业务类型列表
    String api_getUnifoInfoById = SobotOnlineBaseUrl.getBaseIp() + "getUnifoInfoById";
    //查询咨询总结
    String api_queryConversation = SobotOnlineBaseUrl.getBaseIp() + "queryConversation";
    //业务或者业务类型模糊查询
    String api_selectOperationType = SobotOnlineBaseUrl.getBaseIp() + "selectOperationType";

}