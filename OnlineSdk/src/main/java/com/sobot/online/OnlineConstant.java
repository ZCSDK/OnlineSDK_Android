package com.sobot.online;


public interface OnlineConstant {

    String ONLINE_KEEP_LOGIN_STATUS = "online_keep_login_status";//是否记住本次登录的状态（在线、忙碌）
    String ONLINE_LOGIN_STATUS = "online_login_status";//本次登录的状态（1在线、0忙碌）
    String ONLINE_APPID = "online_appid";//token标志，接口请求时，header必须带此参数
    String ONLINE_APPKEY = "online_appkey";//token标志，接口请求时，header必须带此参数
    String SOBOT_CUSTOM_ACCOUNT = "sobot_custom_account";//客服账号

    String KEY_TEMP_ID = "temp-id";//token标志，接口请求时，header必须带此参数
    String KEY_TOKEN = "token";//token标志，接口请求时，header必须带此参数
    /**
     * 所有的接口，如果cod=0，就不要解析data了
     * 如果code=1，就解析data里面的数据
     */
    String result_success_code = "000000";
    String result_success_code_second = "1";
    String result_success_code_sensitive_words = "200032";//敏感词

    int result_fail_code = 0;

    //全部
    int TYPE_ALL = 0;
    //星标
    int TYPE_MARKE = 1;
    //黑名单
    int TYPE_BLACK = 2;


    int SobotOnlineReceptionFragment_resultCode = 1;

    //登录客服信息
    String SOBOT_CUSTOM_USER = "sobot_custom_user";


    /**
     * 用户和机器人客服建立会话显示
     */
    int MSG_TYPE_USER_ROBOT_ONLINE = 4;
    /**
     * 用户请求由机器人转人工客服
     */
    int MSG_TYPE_SOBOT_TRANSFER_ADMIN = 6;
    /**
     * 用户排队
     */
    int MSG_TYPE_USER_QUEUE = 7;
    /**
     * 用户和客服建立会话
     */
    int MSG_TYPE_USER_ADMIN_CONNECTED = 8;
    /**
     * 客服间会话转接
     */
    int MSG_TYPE_ADMIN_TRANSFER_ADMIN = 9;
    /**
     * 用户下线
     */
    int MSG_TYPE_USER_OFFLINE = 10;
    /**
     * 加入黑名单
     */
    int MSG_TYPE_ADD_BLACKLIST = 11;

    /**
     * 移除黑名单
     */
    int MSG_TYPE_DELETE_BLACKLIST = 12;
    /**
     * 添加标星
     */
    int MSG_TYPE_ADD_MARKLIST = 16;
    /**
     * 移除标星
     */
    int MSG_TYPE_DELETE_MARKLIST = 17;
    /**
     * 客户打电话申请application calls
     */
    int USRE_APPLICATION_CALLS = 18;
    /**
     * 客服给用户回拨电话
     */
    int ADMIN_CALL_BACK = 19;
    /**
     * 客服邀请用户建立会话
     */
    int MSG_TYPE_ADMIN_CONNECTED_USER = 21;
    /**
     * 实时推送的轨迹消息
     */
    int MSG_TYPE_ACTION_VISIT_TRAIL = 23;

    /**
     * 客服上线
     */
    int MSG_TYPE_CUSTOMER_LOGIN = 1;

    /**
     * 客服下线
     */
    int MSG_TYPE_CUSTOMER_LOGOUT = 2;

    /**
     * 管理员忙碌
     */
    int MSG_TYPE_ADMIN_BUSY = 15;

    /**
     * 无历史记录
     */
    int MSG_TYPE_HISTORY_EMPTY = 22;

    /**
     * 自动应答提示语
     */
    int MSG_TYPE_HISTORY_AUTO_REPLY = 25;

    /**
     * 客服敏感词提醒
     */
    int MSG_TYPE_SENSITIVE = 30;

    /**
     * 邀请评价成功提示语
     */
    int MSG_TYPE_EVALUATE = 31;


    int SOBOT_REQUEST_CODE_LAHEI = 0x00001;//拉黑返回
    int SOBOT_REQUEST_CODE_VIP = 0x00002;//选择vip等级返回
    int SOBOT_REQUEST_CODE_IS_VIP = 0x00003;//选择是否是vip客户
    int SOBOT_REQUEST_CODE_CAMERA = 0x00004;//打开拍照界面
    int SOBOT_REQUEST_CODE_SHAIXUAN_USER = 0x00005;//打开用户筛选
    int SOBOT_REQUEST_CODE_SHAIXUAN_CHAT = 0x00006;//打开会话筛选
    int SOBOT_REQUEST_CODE_QUICK_REPLY = 0x00007;//打开快捷回复
    int SOBOT_REQUEST_CODE_INTELLIGENCE_REPLY = 0x00008;//打开智能回复
    int SOBOT_REQUEST_CODE_SERVICE_SUMMARY = 0x00009;//打开服务总结
    int SOBOT_REQUEST_CODE_SUMMARY_STATUS = 0x00010;//打开服务总结状态
    int SOBOT_REQUEST_CODE_SUMMARY_BUSINESS = 0x00011;//打开服务咨询业务
    int SOBOT_REQUEST_CODE_SUMMARY_UNIT_TYPE = 0x00012;//打开服务咨询业务类型
    int SOBOT_REQUEST_CODE_USER_ENTERPRISE = 0x00013;//客户信息打开公司选择界面
    int SOBOT_REQUEST_CODE_SUMMARY_SEARCH = 0x00014;//服务总结业务类型模糊查询
    int SOBOT_REQUEST_CODE_TRANSFER_KEFU_REVIEW = 0x00015;//转接客服审核
    int SOBOT_REQUEST_CODE_TRANSFER_GROUP_REVIEW = 0x00016;//转接客服组审核


    int SOBOT_MSG_TYPE_TEXT = 0;//文本消息类型
    int SOBOT_MSG_TYPE_IMG = 1;//图片消息类型
    int SOBOT_MSG_TYPE_AUDIO = 2;//音频消息类型
    int SOBOT_MSG_TYPE_VIDEO = 3;//视频消息类型
    int SOBOT_MSG_TYPE_FILE = 4;//文件消息类型
    int SOBOT_MSG_TYPE_RICH = 5;//富文本消息类型
    int SOBOT_MSG_TYPE_DUOLUN = 6;//多伦消息类型
    int SOBOT_MSG_TYPE_LOCATION = 7;//位置类型
    int SOBOT_MSG_TYPE_CONSULTING = 8;//小卡片类型
    int SOBOT_MSG_TYPE_ORDERCARD = 9;//订单卡片类型
    int SOBOT_MSG_TYPE_REMIND = 10;//提醒消息类型
    int SOBOT_MSG_TYPE_ERROR = 11;//未识别消息类型

    String STATUS_ONLINE = "1";//在线
    String STATUS_BUSY = "2";//忙碌
    String STATUS_LITTLE_HUGH = "3";//小休
    String STATUS_TRAIN = "4";//培训
    String STATUS_MEETING = "5";//会议
    String STATUS_HAVE_MEALS = "6";//用餐
    String STATUS_ACTIVI = "7";//活动
    String STATUS_OFFLINE = "99";//离线 （自己添加的）

    String SOBOT_INTENT_DATA_SELECTED_FILE = "sobot_intent_data_selected_file";

    /**
     * 管理员状态,离线
     */
    int ADMIN_STATUS_OUT = 0;
    /**
     * 管理员状态,人工在线
     */
    int ADMIN_STATUS_ONLINE = 1;
    /**
     * 管理员状态,人工忙碌
     */
    int ADMIN_STATUS_BUSY = 2;
    /**
     * 管理员状态，人工小休
     */
    int ADMIN_STATUS_REST = 3;
    /**
     * 管理员状态，人工培训
     */
    int ADMIN_STATUS_TRAIN = 4;
    /**
     * 管理员状态,人工会议
     */
    int ADMIN_STATUS_MEETING = 5;
    /**
     * 管理员状态,人工用餐
     */
    int ADMIN_STATUS_FEAST = 6;
    /**
     * 管理员状态，人工活动
     */
    int ADMIN_STATUS_SPORTING = 7;



    String SCAN_PATH_FLAG = "scan_path_flag";//浏览轨迹开关
    String ISINVITE_FLAG = "isinvite_flag";//是否打开邀请浏览用户功能
    String TOPFLAG = "topFlag";//星标置顶 0不置顶 1置顶
    String SORTFLAG = "sortFlag";//会话排序 0 按接入顺序 1 按新消息时间

    //服务总结的配置
    String OPEN_SUMMARY_FLAG = "open_summary_flag";//咨询总结开关
    String SUMMARY_OPERATION_FLAG = "summary_operation_flag";//咨询总结业务单元开关
    String SUMMARY_OPERATION_INPUT_FLAG = "summary_operation_input_flag";//咨询总结业务单元必填开关
    String SUMMARY_TYPE_FLAG = "summary_type_flag";//咨询总结问题类型开关
    String SUMMARY_STATUS_FLAG = "summary_status_flag";//咨询总结处理状态开关
    String SUMMARY_STATUS_INPUT_FLAG = "summary_status_input_flag";//咨询总结处理状态必填开关
    String QDESCRIBE_SHOW_FLAG = "qdescribe_show_flag";//咨询总结备注是否显示

    String TRANSFER_AUDIT_FLAG = "transfer_audit_flag";//转接申请审核开关
    String KEFU_LOGIN_STATUS_FLAG = "kefu_login_status_flag";//客服登录状态切换申请审核开关

}