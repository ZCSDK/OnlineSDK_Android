package com.sobot.online.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sobot.common.utils.SobotGlobalContext;
import com.sobot.online.OnlineConstant;
import com.sobot.online.R;
import com.sobot.online.activity.SobotCustomerServiceChatActivity;
import com.sobot.online.activity.SobotOnlineChatActivity;
import com.sobot.online.activity.SobotServiceSummaryActivity;
import com.sobot.online.adapter.SobotPaiduiAdapter;
import com.sobot.online.adapter.SobotReceptionAdapter;
import com.sobot.online.api.MemoryCacheManager;
import com.sobot.online.api.OnlineBaseCode;
import com.sobot.online.api.SobotOnlineUrlApi;
import com.sobot.online.base.SobotOnlineBaseFragment;
import com.sobot.online.dialog.SobotOnlineCommonDialog;
import com.sobot.online.model.HistoryUserInfoModel;
import com.sobot.online.model.OnlineCommonModel;
import com.sobot.online.model.QueueUserModel;
import com.sobot.online.model.SynChronousModel;
import com.sobot.online.util.OrderUtils;
import com.sobot.online.weight.recyclerview.SobotRecyclerView;
import com.sobot.online.weight.recyclerview.adapter.BaseRecyclerViewAdapter;
import com.sobot.online.weight.recyclerview.swipemenu.SobotSwipeMenuRecyclerView;
import com.sobot.online.weight.toast.SobotToastUtil;
import com.sobot.onlinecommon.control.CustomerServiceInfoModel;
import com.sobot.onlinecommon.control.OnlineMsgManager;
import com.sobot.onlinecommon.frame.http.callback.SobotResultCallBack;
import com.sobot.onlinecommon.gson.SobotGsonUtil;
import com.sobot.onlinecommon.socket.MsgCacheManager;
import com.sobot.onlinecommon.socket.SobotSocketConstant;
import com.sobot.onlinecommon.socket.module.ChatMessageMsgModel;
import com.sobot.onlinecommon.socket.module.PushMessageModel;
import com.sobot.onlinecommon.utils.SobotOnlineLogUtils;
import com.sobot.onlinecommon.utils.SobotResourceUtils;
import com.sobot.onlinecommon.utils.SobotSPUtils;
import com.sobot.onlinecommon.utils.SobotTimeUtils;

import java.util.ArrayList;
import java.util.List;

import static com.sobot.online.OnlineConstant.SOBOT_CUSTOM_USER;
import static com.sobot.online.OnlineConstant.SobotOnlineReceptionFragment_resultCode;

/**
 * @Description: 当前会话页面
 * @Author: znw
 * @CreateDate: 2020/08/20 09:45
 * @Version: 1.0
 */
public class SobotOnlineReceptionFragment extends SobotOnlineBaseFragment implements View.OnClickListener, SobotRecyclerView.LoadingListener {
    private View mRootView;
    private TextView tv_online_reception_on;//会话中
    private TextView tv_online_reception_screen_on;//排队中

    SobotSwipeMenuRecyclerView ssmrv_online_reception;//用户列表
    SobotSwipeMenuRecyclerView ssmrv_online_paidui;//排队列表
    private SobotReceptionAdapter receptionAdapter;
    private SobotPaiduiAdapter paiduiAdapter;
    CustomerServiceInfoModel admin;//登录用户
    HistoryUserInfoModel userInfo;
    private String cid;
    //以下为配置
    private int mTopflag;//星标置顶 0不置顶 1置顶
    private int mSortflag;//会话排序 0 按接入顺序 1 按新消息时间
    //排序比较器
    private OrderUtils.OrderComparator mOrderComparator;
    boolean flag_has = false;

    List<PushMessageModel> dataList = new ArrayList<>();//会话中用户
    List<QueueUserModel.QueueUser> queueUserList = new ArrayList<>();//排队中用户

    private int currentPage = 1;//当前页
    private int pageSize = 20;//排队用户列表每页显示数量

    private SobotOnlineCommonDialog closeDialog;//客服掉线弹窗

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(getResLayoutId("sobot_fragment_online_reception"), container, false);
        initView();
        initData();
        return mRootView;
    }

    private void initData() {
        registBroadCast();
        logicEmptyView();
        loadUserConfig();
        paiduiAdapter = new SobotPaiduiAdapter(getSobotActivity(), new SobotPaiduiAdapter.OnInviteListener() {
            @Override
            public void onInvite(QueueUserModel.QueueUser queueUser) {
                if (queueUser != null) {
                    invite(queueUser);
                }
            }
        });
        receptionAdapter = new SobotReceptionAdapter(getSobotActivity(), new SobotReceptionAdapter.OnReceptionSwipeListener() {
            @Override
            public void onClose(final PushMessageModel item, final int position) {
                if (closeDialog == null) {
                    final PushMessageModel tempModel = item;
                    boolean show_summary = SobotSPUtils.getInstance().getBoolean(OnlineConstant
                            .OPEN_SUMMARY_FLAG, false);
                    if (show_summary) {
                        //若服务总结已总结，则关闭，若服务总结未总结，则提示服务总结
                        if (!MemoryCacheManager.getInstance().hasSummaryCid(item.getCid())) {
                            SobotToastUtil.showCustomToast(getSobotActivity(), getResString("online_please_submit_service_summary"));
                            return;
                        }
                    }
                    closeDialog = new SobotOnlineCommonDialog(getSobotActivity(), SobotResourceUtils.getResString(getSobotActivity(), "online_exit_chat"), SobotResourceUtils.getResString(getSobotActivity(), "online_ok"), SobotResourceUtils.getResString(getSobotActivity(), "online_cancle"), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (v.getId() == SobotResourceUtils.getResId(getSobotActivity(), "sobot_btn_cancle")) {
                            } else if (v.getId() == SobotResourceUtils.getResId(getSobotActivity(), "sobot_btn_ok")) {
                                zhiChiApi.leave(getSobotActivity(), tempModel.getCid(), tempModel.getUserId(), new SobotResultCallBack<OnlineBaseCode>() {
                                    @Override
                                    public void onSuccess(OnlineBaseCode onlineBaseCode) {
                                        receptionAdapter.removeToIndex(position);
                                        receptionAdapter.myNotifyDataSetChanged();
                                        tv_online_reception_on.setText(SobotResourceUtils.getResString(getSobotActivity(), "sobot_reception_on") + " (" + getOnlineChatCount() + ")");
                                    }

                                    @Override
                                    public void onFailure(Exception e, String des) {
                                        SobotToastUtil.showCustomToast(getSobotActivity(), des);
                                    }
                                });
                            }
                            closeDialog.dismiss();
                            closeDialog = null;
                        }
                    });
                }
                closeDialog.show();
            }

            @Override
            public void onMark(final PushMessageModel item, final int position) {
                String url;
                if (item.getMarkStatus() == 1) {
                    //删除星标
                    url = SobotOnlineUrlApi.api_deleteMarkList;
                } else {
                    //添加星标
                    url = SobotOnlineUrlApi.api_addMarkList;
                }
                zhiChiApi.addOrDeleteMarkList(getSobotActivity(), item.getUid(), url, new SobotResultCallBack<OnlineBaseCode>() {
                    @Override
                    public void onSuccess(OnlineBaseCode onlineBaseCode) {
                        if (item.getMarkStatus() == 1) {
                            item.setMarkStatus(0);
                        } else {
                            item.setMarkStatus(1);
                        }
                        receptionAdapter.notifyItemChanged(position + 1);
                    }

                    @Override
                    public void onFailure(Exception e, String des) {

                    }
                });
            }

            @Override
            public void onSummary(PushMessageModel item, int position) {
                HistoryUserInfoModel userInfo = formatUserInfo(item);
                //进入服务总结界面
                Intent intent = new Intent(getSobotActivity(), SobotServiceSummaryActivity.class);
                intent.putExtra("userInfo", userInfo);
                intent.putExtra("cid", userInfo.getLastCid());
                startActivityForResult(intent, OnlineConstant.SOBOT_REQUEST_CODE_SERVICE_SUMMARY);
            }
        });

        receptionAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener<PushMessageModel>() {
            @Override
            public void onItemClick(View view, PushMessageModel item, int position) {
                if (item == null)
                    return;
                MsgCacheManager.getInstance().delUnReadMsgCount(item.getUid());
                //清空当前用户的未读消息数字
                item.setUnReadCount(0);
                receptionAdapter.notifyItemChanged(position + 1);
                HistoryUserInfoModel userInfo = formatUserInfo(item);
                Intent intent = new Intent(getActivity(),
                        SobotOnlineChatActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("userInfo", userInfo);
                bundle.putBoolean("hasSummary", MemoryCacheManager.getInstance().hasSummaryCid(item.getCid()));
                if (userInfo.isOnline()) {
                    bundle.putString("flag", "online");
                } else {
                    bundle.putString("flag", "history");
                }
                //表示是由哪个页面点击打开的
                bundle.putString("fromTab", "online");
                intent.putExtra("bundle", bundle);
                intent.putExtra("userSource", userInfo.getSource());
                SobotSPUtils.getInstance().put("uid",
                        userInfo.getId());
                SobotSPUtils.getInstance().put("lastCid",
                        userInfo.getLastCid());
                SobotSPUtils.getInstance().put("push_cid",
                        item.getCid());
                cid = item.getCid();
                startActivityForResult(intent, SobotOnlineReceptionFragment_resultCode);
            }
        });
        receptionAdapter.setOnItemLongClickListener(new BaseRecyclerViewAdapter.OnItemLongClickListener<PushMessageModel>() {

            @Override
            public void onItemLongClick(View view, PushMessageModel item, int position) {
                if (item == null)
                    return;
                MsgCacheManager.getInstance().delUnReadMsgCount(item.getUid());
                //清空当前用户的未读消息数字
                item.setUnReadCount(0);
                receptionAdapter.notifyItemChanged(position + 1);
                HistoryUserInfoModel userInfo = formatUserInfo(item);
                Intent intent = new Intent(getActivity(),
                        SobotOnlineChatActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("userInfo", userInfo);
                bundle.putBoolean("hasSummary", MemoryCacheManager.getInstance().hasSummaryCid(item.getCid()));
                if (userInfo.isOnline()) {
                    bundle.putString("flag", "online");
                } else {
                    bundle.putString("flag", "history");
                }
                //表示是由哪个页面点击打开的
                bundle.putString("fromTab", "online");
                intent.putExtra("bundle", bundle);
                intent.putExtra("userSource", userInfo.getSource());
                SobotSPUtils.getInstance().put("uid",
                        userInfo.getId());
                SobotSPUtils.getInstance().put("lastCid",
                        userInfo.getLastCid());
                SobotSPUtils.getInstance().put("push_cid",
                        item.getCid());
                cid = item.getCid();
                startActivityForResult(intent, SobotOnlineReceptionFragment_resultCode);
            }
        });
        ssmrv_online_reception.setAdapter(receptionAdapter);
        ssmrv_online_paidui.setAdapter(paiduiAdapter);
        synChronous();
    }

    //同步更新会话中列表
    private void synChronous() {
        dataList.clear();
        flag_has = false;
        receptionAdapter.setListAll(dataList);
        zhiChiApi.synChronous(getSobotActivity(), new SobotResultCallBack<SynChronousModel>() {
            @Override
            public void onSuccess(SynChronousModel synChronousModel) {
                if (synChronousModel != null && synChronousModel.getUserList() != null) {
                    List<PushMessageModel> userList = synChronousModel.getUserList();
                    dataList.addAll(userList);
                    receptionAdapter.setListAll(dataList);
                    receptionAdapter.myNotifyDataSetChanged();
                    tv_online_reception_on.setText(SobotResourceUtils.getResString(getSobotActivity(), "sobot_reception_on") + " (" + +getOnlineChatCount() + ")");
                    tv_online_reception_screen_on.setText(SobotResourceUtils.getResString(getSobotActivity(), "sobot_screen_on") + " (" + synChronousModel.getWaitSize() + ")");
                    OnlineMsgManager.getInstance(getSobotActivity()).setTempMsgList(null);
                }
            }

            @Override
            public void onFailure(Exception e, String des) {

            }
        });
    }

    //获取排队中用户列表
    private void queryWaitUser() {
        zhiChiApi.queryWaitUser(getSobotActivity(), currentPage, pageSize, new SobotResultCallBack<QueueUserModel>() {
            @Override
            public void onSuccess(QueueUserModel queueUserModel) {
                ssmrv_online_paidui.refreshComplete();
                ssmrv_online_paidui.loadMoreComplete();
                if (queueUserModel != null && queueUserModel.getList() != null) {
                    List<QueueUserModel.QueueUser> tempList = queueUserModel.getList();
                    queueUserList.addAll(tempList);
                    paiduiAdapter.setListAll(queueUserList);
                    tv_online_reception_screen_on.setText(SobotResourceUtils.getResString(getSobotActivity(), "sobot_screen_on") + " (" + queueUserModel.getWaitSize() + ")");
                    if (queueUserList.size() < pageSize) {
                        ssmrv_online_paidui.setLoadingMoreEnabled(false);
                    } else {
                        ssmrv_online_paidui.setLoadingMoreEnabled(true);
                    }
                } else {
                    ssmrv_online_paidui.setLoadingMoreEnabled(false);
                }
            }

            @Override
            public void onFailure(Exception e, String des) {
                ssmrv_online_paidui.refreshComplete();
                ssmrv_online_paidui.loadMoreComplete();
            }
        });
    }


    //邀请排队用户
    private void invite(final QueueUserModel.QueueUser queueUser) {
        zhiChiApi.invite(getSobotActivity(), queueUser.getUid(), new SobotResultCallBack<OnlineCommonModel>() {
            @Override
            public void onSuccess(OnlineCommonModel data) {
                SobotOnlineLogUtils.i("邀请成功----status：" + data.getStatus());
                SobotToastUtil.showCustomToast(getSobotActivity(), SobotResourceUtils.getResString(getSobotActivity(), "online_invite_success"));
                paiduiAdapter.remove(queueUser);
                synChronous();
            }

            @Override
            public void onFailure(Exception e, String des) {
                SobotToastUtil.showCustomToast(getSobotActivity(), des);
            }
        });
    }


    protected void initView() {
        admin = (CustomerServiceInfoModel) SobotSPUtils.getInstance().getObject(SOBOT_CUSTOM_USER);
        tv_online_reception_on = mRootView.findViewById(R.id.tv_online_reception_on);
        tv_online_reception_screen_on = mRootView.findViewById(R.id.tv_online_reception_screen_on);
        ssmrv_online_reception = mRootView.findViewById(R.id.ssmrv_online_reception);
        ssmrv_online_paidui = mRootView.findViewById(R.id.ssmrv_online_paidui);

        tv_online_reception_on.setText(SobotResourceUtils.getResString(getSobotActivity(), "sobot_reception_on"));
        tv_online_reception_screen_on.setText(SobotResourceUtils.getResString(getSobotActivity(), "sobot_screen_on"));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getSobotActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ssmrv_online_reception.setLayoutManager(layoutManager);
        ssmrv_online_reception.setPullRefreshEnabled(false);//禁止下拉
        ssmrv_online_reception.setLoadingMoreEnabled(false);//禁止加载更多

        LinearLayoutManager paiduiLayoutManager = new LinearLayoutManager(getSobotActivity());
        paiduiLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ssmrv_online_paidui.setLayoutManager(paiduiLayoutManager);
        ssmrv_online_paidui.setPullRefreshEnabled(true);
        ssmrv_online_paidui.setLoadingMoreEnabled(true);
        ssmrv_online_paidui.setLoadingListener(this);

        tv_online_reception_on.setOnClickListener(this);
        tv_online_reception_screen_on.setOnClickListener(this);
    }

    private HistoryUserInfoModel formatUserInfo(PushMessageModel pushMessageModel) {
        HistoryUserInfoModel userInfo = new HistoryUserInfoModel();
        userInfo.setId(pushMessageModel.getUid());
        userInfo.setLastCid(pushMessageModel.getCid());
        userInfo.setIsmark(pushMessageModel.getMarkStatus());
        userInfo.setSource(pushMessageModel.getUsource() + "");
        userInfo.setUname(pushMessageModel.getUname());
        userInfo.setChatType(pushMessageModel.getChatType());
        userInfo.setOnline(pushMessageModel.isOnline());
        return userInfo;
    }


    private void registBroadCast() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(SobotSocketConstant.BROADCAST_SOBOT_MSG);
        filter.addAction(SobotSocketConstant.BROADCAST_SOBOT_TRANSFER);
        filter.addAction(SobotSocketConstant.BROADCAST_SOBOT_ADD_BLACK);
        filter.addAction(SobotSocketConstant.BROADCAST_SOBOT_MARK);
        filter.addAction(SobotSocketConstant.BROADCAST_SOBOT_REMOVE_MARK);
        filter.addAction(SobotSocketConstant.BROADCAST_SOBOT_LIST_SYNCHRONOUS_USERS);
        filter.addAction(SobotSocketConstant.BROADCAST_SOBOT_UPDATE_USERINFO);
        filter.addAction(SobotSocketConstant.BROADCAST_SOBOT_UPDATE_CUSTOMER);
        filter.addAction(SobotSocketConstant.BROADCAST_SOBOT_RELOGIN_RESET_UNREAD);
        filter.addAction(SobotSocketConstant.SESSION_SEQUENCE_CONFIG_CHANGED);
        filter.addAction(SobotSocketConstant.BROADCAST_CUSTOM_COMITSUMMARY);//提交服务总结
        filter.addAction(SobotSocketConstant.BROADCAST_SOBOT_UPDATE_LAST_MSG);//用户在聊天详情也发送消息成功，更新会回列表
        if (getActivity() != null) {
            getActivity().registerReceiver(receiver, filter);
        }
    }

    public BroadcastReceiver receiver = new BroadcastReceiver() {

        @SuppressWarnings("unchecked")
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent == null || intent.getAction() == null) {
                return;
            }
            if (intent.getAction().equals(SobotSocketConstant.BROADCAST_SOBOT_RELOGIN_RESET_UNREAD)) {
                return;
            }
            if (intent.getAction().equals(SobotSocketConstant.BROADCAST_SOBOT_MSG)) {
                String msgContentJson = intent.getStringExtra("msgContent");
                PushMessageModel result = SobotGsonUtil.gsonToBean(msgContentJson, PushMessageModel.class);
                if (result == null) {
                    return;
                }
                if (result.getType() == SobotSocketConstant.UPDATE_USER_INFO) {
                    if (receptionAdapter != null) {
                        ((SobotCustomerServiceChatActivity) getActivity()).fillUserConfig();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (receptionAdapter != null) {
                                    receptionAdapter.setCanSummary(SobotSPUtils.getInstance().getBoolean(OnlineConstant
                                            .OPEN_SUMMARY_FLAG, false));
                                    receptionAdapter.notifyDataSetChanged();
                                }
                            }
                        }, 2 * 1000);

                    }
                    return;
                }
                result.setUserId(result.getUid());
                if (result.getType() == SobotSocketConstant.ACTIVE_RECEPT) {
                    if (admin.getAid().equals(result.getTid())) {
                        for (int i = receptionAdapter.getItemCount() - 1; i >= 0; i--) {
                            PushMessageModel item = (PushMessageModel) receptionAdapter.getData(i);
                            if (item != null && !TextUtils.isEmpty(result.getUid()) && result.getUid().equals(item.getUid())) {
                                item.setTimeOrder(result.getTimeOrder());
                                item.setAcceptTimeOrder(result.getAcceptTimeOrder());
                                item.setMessage(result.getMessage());
                                item.setTs(SobotTimeUtils.getNowString());
                                flag_has = true;
                                OnlineMsgManager.getInstance(getSobotActivity()).setTempMsgList((ArrayList<PushMessageModel>) receptionAdapter.getList());
                                item.setIsOnline(false);
                                item.setFace(null);
                                receptionAdapter.myNotifyDataSetChanged();
                                break;
                            }
                        }
                        tv_online_reception_on.setText(SobotResourceUtils.getResString(getSobotActivity(), "sobot_reception_on") + " (" + getOnlineChatCount() + ")");
                    }
                }

                processNewData(result);
            }

            if (intent.getAction().equals(SobotSocketConstant.BROADCAST_SOBOT_ADD_BLACK)) {
                HistoryUserInfoModel userInfo = (HistoryUserInfoModel) intent.getSerializableExtra("userInfo");
                List<PushMessageModel> allData = receptionAdapter.getList();
                for (int i = 0; i < allData.size(); i++) {
                    if (userInfo != null && !TextUtils.isEmpty(userInfo.getId()) && allData.get(i).getUid().equals(userInfo.getId())) {
                        receptionAdapter.removeToIndex(i);
                        break;
                    }
                }

                logicEmptyView();
            }


            if (intent.getAction().equals(SobotSocketConstant.BROADCAST_SOBOT_LIST_SYNCHRONOUS_USERS) || intent.getAction().equals(SobotSocketConstant.BROADCAST_SOBOT_TRANSFER)) {
                synChronous();
            }

            if (intent.getAction().equals(SobotSocketConstant.BROADCAST_SOBOT_MARK)) {
                String uid = intent.getStringExtra("uid");
                int markStatus = intent.getIntExtra("markStatus", 0);
                if (TextUtils.isEmpty(uid) || receptionAdapter == null && receptionAdapter.getList() == null) {
                    return;
                }
                for (int i = 0; i < receptionAdapter.getList().size(); i++) {
                    if (uid.equals(receptionAdapter.getList().get(i).getUid())) {
                        PushMessageModel pushMessageModel = receptionAdapter.getList().get(i);
                        pushMessageModel.setMarkStatus(markStatus);
                    }
                }
                receptionAdapter.notifyDataSetChanged();
            }

            if (intent.getAction().equals(SobotSocketConstant.BROADCAST_SOBOT_UPDATE_USERINFO)) {
                String uid = intent.getStringExtra("uid");
                if (TextUtils.isEmpty(uid) || receptionAdapter == null && receptionAdapter.getList() == null) {
                    return;
                }
                List<PushMessageModel> allData = receptionAdapter.getList();
                for (int i = 0; i < allData.size(); i++) {
                    if (uid.equals(allData.get(i).getUid())) {
                        allData.get(i).setUname(intent.getStringExtra("uname"));
                        break;
                    }
                }
            }

            if (intent.getAction().equals(SobotSocketConstant.BROADCAST_CUSTOM_COMITSUMMARY)) {
                String tempCid = intent.getStringExtra("cid");
                if (!TextUtils.isEmpty(tempCid)) {
                    MemoryCacheManager.getInstance().putSummaryCid(tempCid);
                    receptionAdapter.notifyDataSetChanged();
                }
            }

            if (intent.getAction().equals(SobotSocketConstant.BROADCAST_SOBOT_UPDATE_LAST_MSG)) {
                String uid = intent.getStringExtra("uid");
                String ts = intent.getStringExtra("ts");
                ChatMessageMsgModel lastMsg = (ChatMessageMsgModel) intent.getSerializableExtra("lastMsg");
                for (int i = 0; i < receptionAdapter.getList().size(); i++) {
                    if (uid.equals(receptionAdapter.getList().get(i).getUid())) {
                        PushMessageModel pushMessageModel = receptionAdapter.getList().get(i);
                        pushMessageModel.setTs(ts);
                        pushMessageModel.setMessage(lastMsg);
                    }
                }
                receptionAdapter.notifyDataSetChanged();
            }

            if (intent.getAction().equals(SobotSocketConstant.SESSION_SEQUENCE_CONFIG_CHANGED)) {
                //刷新数据
                loadUserConfig();
            }

            orderAndRefreshList();
        }
    };

    private void orderAndRefreshList() {
        tv_online_reception_on.setText(SobotResourceUtils.getResString(getSobotActivity(), "sobot_reception_on") + " (" + getOnlineChatCount() + ")");
    }

    /**
     * 加载用户配置
     * 登录接口直接给的配置在initdata中就直接加载
     */
    private void loadUserConfig() {
        if (getActivity() != null) {
            mTopflag = SobotSPUtils.getInstance().getInt(SobotSocketConstant.TOPFLAG, 0);
            mSortflag = SobotSPUtils.getInstance().getInt(SobotSocketConstant.SORTFLAG, 0);
            mOrderComparator = new OrderUtils.OrderComparator(mSortflag, mTopflag);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == tv_online_reception_on) {
            tv_online_reception_on.setTextColor(ContextCompat.getColor(getSobotActivity(), SobotResourceUtils.getResColorId(getContext(), "sobot_online_color")));
            tv_online_reception_screen_on.setTextColor(ContextCompat.getColor(getSobotActivity(), SobotResourceUtils.getResColorId(getContext(), "sobot_online_common_gray2")));
            ssmrv_online_paidui.setVisibility(View.GONE);
            ssmrv_online_reception.setVisibility(View.VISIBLE);
//            synChronous();
        }
        if (v == tv_online_reception_screen_on) {
            tv_online_reception_screen_on.setTextColor(ContextCompat.getColor(getSobotActivity(), SobotResourceUtils.getResColorId(getContext(), "sobot_online_color")));
            tv_online_reception_on.setTextColor(ContextCompat.getColor(getSobotActivity(), SobotResourceUtils.getResColorId(getContext(), "sobot_online_common_gray2")));
            ssmrv_online_paidui.setVisibility(View.VISIBLE);
            ssmrv_online_reception.setVisibility(View.GONE);
            currentPage = 1;
            queueUserList.clear();
            queryWaitUser();
        }
    }

    private void setModelUnread(PushMessageModel push) {
        if (getActivity() == null) {
            return;
        }
        if (SobotGlobalContext.getInstance().currentActivity() != null) {
            // 对话界面，并且在前台。未读不显示
            if (!SobotGlobalContext.getInstance().currentActivity().getClass().getName().contains("SobotOnlineChatActivity")
                    || (SobotGlobalContext.getInstance().currentActivity().getClass().getName().contains("SobotOnlineChatActivity") && !push.getCid().equals(cid))) {
                int count = push.getUnReadCount() + 1;
                push.setUnReadCount(count);
            }
        }
    }


    /**
     * 根据数据判断是否显示空态页面
     */
    private void logicEmptyView() {
        if (receptionAdapter == null) {
            // 当没有数据的时候显示没有数据的提示按钮
//            second_chat.setText("暂无在线访客");
//            img_nochat.setBackgroundResource(R.drawable.no_online_user);
//            rl_online.setVisibility(View.VISIBLE);
        } else {
            if (receptionAdapter.getItemCount() > 0) {
//                rl_online.setVisibility(View.GONE);
            } else {
                // 当没有数据的时候显示没有数据的提示按钮
//                second_chat.setText("暂无在线访客");
//                img_nochat.setBackgroundResource(R.drawable.no_online_user);
//                rl_online.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getActivity() != null) {
            getActivity().unregisterReceiver(receiver);
        }
    }

    private void processNewData(PushMessageModel result) {
        if (result == null) {
            return;
        }
        int type = result.getType();
        //如果收到排队消息，刷新排队中列表
        if (type == SobotSocketConstant.QUEUE) {
            currentPage = 1;
            queueUserList.clear();
            queryWaitUser();
        }
        // 只处理三种类型用户上线，下线，新消息,排队
        if (type == SobotSocketConstant.NEW_USER || type == SobotSocketConstant.OFFLINE_USER || type == SobotSocketConstant.NEW_INFOMATION) {
			/*if (!TextUtils.isEmpty(result.getTs())) {
				result.setTimeOrder(result.getTs());
				result.setTs(DateUtil.formatDateTime(result.getTs()));
			} else {
				result.setTimeOrder(DateUtil.toDate(System.currentTimeMillis(), DateUtil.DATE_FORMAT));
				result.setTs(DateUtil.toDate(System.currentTimeMillis(), DateUtil.DATE_FORMAT0));
			}*/
            refershTime(result);
            refershOrderTime(result);
            if (type == SobotSocketConstant.NEW_USER || type == SobotSocketConstant.OFFLINE_USER) {
                setAcceptTime(result, true);
            }
            if (receptionAdapter.getList().size() > 0) {
                List<PushMessageModel> allData = receptionAdapter.getList();
                for (int i = 0; i < allData.size(); i++) {
                    if (result.getUid().equals(allData.get(i).getUid())) {
                        PushMessageModel item = (PushMessageModel) receptionAdapter.getData(i);
                        item.setType(type);
                        item.setTimeOrder(result.getTimeOrder());
                        item.setAcceptTimeOrder(result.getAcceptTimeOrder());
                        item.setMessage(result.getMessage());
                        item.setTs(result.getTs());

                        flag_has = true;
                        if (type == SobotSocketConstant.NEW_INFOMATION) {
                            setModelUnread(item);
                            item.setFace(result.getFace());
                            OnlineMsgManager.getInstance(getSobotActivity()).setTempMsgList((ArrayList<PushMessageModel>) receptionAdapter.getList());
                        }
                        if (type == SobotSocketConstant.OFFLINE_USER) {
                            OnlineMsgManager.getInstance(getSobotActivity()).setTempMsgList((ArrayList<PushMessageModel>) receptionAdapter.getList());
                            item.setIsOnline(false);
                            item.setFace(null);
                        }

                        if (type == SobotSocketConstant.NEW_USER) {
                            item.setFace(result.getFace());
                            item.setCid(result.getCid());
                            item.setIsOnline(true);
                            result.setTs(SobotTimeUtils.getNowString());
                            item.setMessage(new ChatMessageMsgModel("0", "<font color= '#DD3B3B'>[" + getResString("online_new_user_online") + "]</font>"));
                        }
                        receptionAdapter.notifyItemChanged(i + 1);
                        break;
                    } else {
                        flag_has = false;
                    }
                }

                if (!flag_has) {
                    if (type == SobotSocketConstant.NEW_USER) {
                        result.setIsOnline(true);
                        result.setTs(SobotTimeUtils.getNowString());
                        result.setMessage(new ChatMessageMsgModel("0", "<font color= '#DD3B3B'>[" + getResString("online_new_user_online") + "]</font>"));
                    }
                    receptionAdapter.addItemToHead(result);
                }
            } else {
                if (type == SobotSocketConstant.NEW_USER) {
                    result.setIsOnline(true);
                    result.setTs(SobotTimeUtils.getNowString());
                    result.setMessage(new ChatMessageMsgModel("0", "<font color= '#DD3B3B'>[" + getResString("online_new_user_online") + "]</font>"));
                } else if (type == SobotSocketConstant.NEW_INFOMATION) {
                    setModelUnread(result);
                }
                receptionAdapter.addItemToHead(result);
            }
            tv_online_reception_on.setText(SobotResourceUtils.getResString(getSobotActivity(), "sobot_reception_on") + " (" + getOnlineChatCount() + ")");
            logicEmptyView();
            receptionAdapter.myNotifyDataSetChanged();
        }
    }

    //获取在线会话总数
    private int getOnlineChatCount() {
        int count = 0;
        if (receptionAdapter != null) {
            List<PushMessageModel> pushMessageModels = receptionAdapter.getList();
            for (int i = 0; i < pushMessageModels.size(); i++) {
                if (pushMessageModels.get(i).isOnline()) {
                    count = count + 1;
                }
            }
        }
        return count;
    }

    /**
     * 格式化显示时间
     *
     * @param info 推送过来的model
     */
    private void refershTime(PushMessageModel info) {
        if (TextUtils.isEmpty(info.getTs())) {
            info.setTs(SobotTimeUtils.getNowString());
        } else {
            info.setTs(info.getTs());
        }
    }

    /**
     * 设置接入时间
     *
     * @param info
     */
    private void setAcceptTime(PushMessageModel info, boolean isNow) {
        if (TextUtils.isEmpty(info.getTs())) {
            info.setAcceptTimeOrder(SobotTimeUtils.getNowString());
        } else {
            if (!isNow) {
                info.setAcceptTimeOrder(info.getTs());
            } else {
                info.setAcceptTimeOrder(SobotTimeUtils.getNowString());
            }
        }
    }

    /**
     * 新消息时间
     *
     * @param info
     */
    private void refershOrderTime(PushMessageModel info) {
//		if(TextUtils.isEmpty(info.getTs())) {
        info.setTimeOrder(SobotTimeUtils.getNowString());
//		}else{
//			info.setTimeOrder(info.getTs());
//		}
    }

    @Override
    public void onRefresh() {
        currentPage = 1;
        queueUserList.clear();
        queryWaitUser();
    }

    @Override
    public void onLoadMore() {
        currentPage++;
        queryWaitUser();
    }
}
