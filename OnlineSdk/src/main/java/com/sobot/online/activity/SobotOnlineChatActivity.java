package com.sobot.online.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sobot.common.ui.SobotBaseConstant;
import com.sobot.common.utils.SobotImageUtils;
import com.sobot.online.OnlineConstant;
import com.sobot.online.adapter.MessageType;
import com.sobot.online.adapter.SobotOnlineMsgAdapter;
import com.sobot.online.api.OnlineBaseCode;
import com.sobot.online.api.SobotOnlineUrlApi;
import com.sobot.online.base.SobotOnlineBaseActivity;
import com.sobot.online.dialog.OnlineTipDialog;
import com.sobot.online.dialog.SobotDialogUtils;
import com.sobot.online.model.ChatMessageModel;
import com.sobot.online.model.ChatMessageObjectModel;
import com.sobot.online.model.ChatMessageRichTextModel;
import com.sobot.online.model.ChatMessageVideoModel;
import com.sobot.online.model.CidsModel;
import com.sobot.online.model.HistoryUserInfoModel;
import com.sobot.online.model.OfflineMsgModel;
import com.sobot.online.model.OnlineCommonModel;
import com.sobot.online.model.OnlineMsgModelResult;
import com.sobot.online.util.SobotOnlineImageUtils;
import com.sobot.online.util.voice.AudioPlayCallBack;
import com.sobot.online.util.voice.AudioPlayPresenter;
import com.sobot.online.weight.OfflineStateView;
import com.sobot.online.weight.SobotContainsEmojiEditText;
import com.sobot.online.weight.emoji.DisplayEmojiRules;
import com.sobot.online.weight.emoji.EmojiconNew;
import com.sobot.online.weight.emoji.SobotInputHelper;
import com.sobot.online.weight.kpswitch.CustomeChattingPanel;
import com.sobot.online.weight.kpswitch.util.KPSwitchConflictUtil;
import com.sobot.online.weight.kpswitch.util.KeyboardUtil;
import com.sobot.online.weight.kpswitch.view.CustomeViewFactory;
import com.sobot.online.weight.kpswitch.view.SobotChattingPanelEmoticonView;
import com.sobot.online.weight.kpswitch.view.SobotChattingPanelUploadView;
import com.sobot.online.weight.kpswitch.widget.KPSwitchPanelLinearLayout;
import com.sobot.online.weight.recyclerview.SobotRecyclerView;
import com.sobot.online.weight.toast.SobotToastUtil;
import com.sobot.onlinecommon.control.CustomerServiceInfoModel;
import com.sobot.onlinecommon.frame.http.callback.SobotFileResultCallBack;
import com.sobot.onlinecommon.frame.http.callback.SobotResultCallBack;
import com.sobot.onlinecommon.gson.SobotGsonUtil;
import com.sobot.onlinecommon.socket.MsgCacheManager;
import com.sobot.onlinecommon.socket.SobotSocketConstant;
import com.sobot.onlinecommon.socket.module.ChatMessageMsgModel;
import com.sobot.onlinecommon.socket.module.PushMessageModel;
import com.sobot.onlinecommon.ui.statusbar.SobotStatusBarCompat;
import com.sobot.onlinecommon.utils.SobotOnlineLogUtils;
import com.sobot.onlinecommon.utils.SobotMD5Utils;
import com.sobot.onlinecommon.utils.SobotMediaFileUtils;
import com.sobot.onlinecommon.utils.SobotResourceUtils;
import com.sobot.onlinecommon.utils.SobotSPUtils;
import com.sobot.onlinecommon.utils.SobotTimeUtils;
import com.sobot.onlinecommon.utils.SobotUtils;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sobot.online.OnlineConstant.MSG_TYPE_SENSITIVE;
import static com.sobot.online.OnlineConstant.SOBOT_CUSTOM_USER;

/**
 * @Description: 聊天主页面
 * @Author: znw
 * @CreateDate: 2020/08/28 10:05
 * @Version: 1.0
 */
public class SobotOnlineChatActivity extends SobotOnlineBaseActivity implements SobotRecyclerView.LoadingListener, View.OnClickListener
        , SobotChattingPanelEmoticonView.SobotEmoticonClickListener
        , SobotChattingPanelUploadView.SobotPlusClickListener, OfflineStateView.OfflineStateViewListener, SobotOnlineMsgAdapter.OnlineMsgCallBack {
    //头部返回按钮
    private TextView sobot_online_username_back_tv;
    private LinearLayout sobot_ll_bottom;//输入框的布局
    private OfflineStateView rl_bottom_offline_state;//离线消息的view
    private ImageView iv_online_user_pullblack;//右上角拉黑控件
    private ImageView iv_online_user_zhuanjie;//右上角转接控件
    private ImageView iv_online_user_biaoji;//右上标记控件
    //底部功能控件
    private TextView tv_sobot_chat_yaoping;//邀评
    private TextView tv_sobot_chat_quick_reply;//快捷回复
    private TextView tv_sobot_chat_intelligence_reply;//智能回复
    private TextView tv_sobot_chat_service_summary;//服务总结
    //消息列表控件
    private SobotRecyclerView srv_online_msg_list;
    private SobotOnlineMsgAdapter mMsgListAdapter;

    private KPSwitchPanelLinearLayout mPanelRoot; // 聊天下面的面板
    private ImageButton btn_emoticon_view; // 表情面板
    private Button sobot_btn_upload_view; // 显示底部菜单按钮
    private Button sobot_btn_send; // 发送消息按钮
    private SobotContainsEmojiEditText et_sendmessage;// 当前用户输入的信息
    //键盘监听
    private ViewTreeObserver.OnGlobalLayoutListener mKPSwitchListener;
    //键盘相关
    public int currentPanelId = 0;//切换聊天面板时 当前点击的按钮id 为了能切换到对应的view上

    private AudioPlayPresenter mAudioPlayPresenter = null;
    private AudioPlayCallBack mAudioPlayCallBack = null;

    List<ChatMessageModel> data = new ArrayList<>();

    private HistoryUserInfoModel userInfo;
    private String flag_from_page = null;
    private boolean hasSummary = false;//表示当前用户是否做过服务总结
    private String fromTab;//表示来自哪个页面 online   history
    private List<String> cids = new ArrayList<>();//cid的列表
    private int currentCidPosition = 0;//当前查询聊天记录所用的cid位置
    private boolean getCidsFinish = false;//表示查询cid的接口是否结束
    private boolean isInGethistory = false;//表示是否正在查询历史记录

    CustomerServiceInfoModel admin;//登录客服
    private int userSource = -1;//用户来源 0桌面1微信2app3微博4移动网站9企业微信，10微信小程序


    @Override
    protected int getContentViewResId() {
        return getResLayoutId("sobot_activity_chat_main");
    }

    @Override
    protected void initView() {
        SobotStatusBarCompat.setStatusBarColor(getSobotActivity(), getResColor("sobot_chat_status_bar_color"));
        Bundle bundel = getIntent().getBundleExtra("bundle");
        if (bundel != null) {
            userInfo = (HistoryUserInfoModel) bundel.getSerializable("userInfo");
            flag_from_page = bundel.getString("flag");
            hasSummary = bundel.getBoolean("hasSummary", false);
            fromTab = bundel.getString("fromTab");

            SobotOnlineLogUtils.i("flag_from_page=====" + flag_from_page);
        } else {
            return;
        }
        admin = (CustomerServiceInfoModel) SobotSPUtils.getInstance().getObject(SOBOT_CUSTOM_USER);
        sobot_online_username_back_tv = findViewById(getResId("sobot_online_username_back_tv"));
        //头部左侧返回按钮点击返回
        if (sobot_online_username_back_tv != null) {
            sobot_online_username_back_tv.setText(userInfo != null ? userInfo.getUname() : "");
            sobot_online_username_back_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hidePanelAndKeyboard(mPanelRoot);
                    finish();
                }
            });
        }
        mPanelRoot = (KPSwitchPanelLinearLayout) findViewById(getResId("sobot_panel_root"));
        sobot_ll_bottom = (LinearLayout) findViewById(getResId("sobot_ll_bottom"));
        rl_bottom_offline_state = (OfflineStateView) findViewById(getResId("rl_bottom_offline_state"));
        sobot_btn_upload_view = (Button) findViewById(getResId("sobot_btn_upload_view"));
        et_sendmessage = (SobotContainsEmojiEditText) findViewById(getResId("sobot_et_sendmessage"));
        btn_emoticon_view = (ImageButton) findViewById(getResId("sobot_btn_emoticon_view"));
        sobot_btn_send = (Button) findViewById(getResId("sobot_btn_send"));
        sobot_btn_send.setText(SobotResourceUtils.getResString(getSobotActivity(), "sobot_online_send"));
        iv_online_user_pullblack = (ImageView) findViewById(getResId("iv_online_user_pullblack"));
        iv_online_user_biaoji = (ImageView) findViewById(getResId("iv_online_user_biaoji"));
        iv_online_user_zhuanjie = (ImageView) findViewById(getResId("iv_online_user_zhuanjie"));

        int transferFunction = admin != null ? admin.getTransferFunction() : 1;
        if (transferFunction == 1) {
            iv_online_user_zhuanjie.setVisibility(View.VISIBLE);
        } else {
            iv_online_user_zhuanjie.setVisibility(View.GONE);
        }

        tv_sobot_chat_yaoping = findViewById(getResId("tv_sobot_chat_yaoping"));
        tv_sobot_chat_yaoping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hidePanelAndKeyboard(mPanelRoot);
                if (userInfo != null) {
                    //是否黑名单 1 是 ，0 否
                    int isBlack = userInfo.getIsblack();
                    if (isBlack == 1) {
                        SobotToastUtil.showCustomToast(getSobotActivity(), getResString("online_user_already_lahei"));
                        return;
                    }
                }
                if (isFromHistory()) {
                    SobotToastUtil.showCustomToast(getSobotActivity(), getResString("online_cannt_evaluate"));
                } else {
                    //邀评
                    invateEvaluate();
                }
            }
        });

        tv_sobot_chat_quick_reply = findViewById(getResId("tv_sobot_chat_quick_reply"));
        tv_sobot_chat_quick_reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hidePanelAndKeyboard(mPanelRoot);
                if (userInfo != null) {
                    //是否黑名单 1 是 ，0 否
                    int isBlack = userInfo.getIsblack();
                    if (isBlack == 1) {
                        SobotToastUtil.showCustomToast(getSobotActivity(), getResString("online_user_already_lahei"));
                        return;
                    }
                }
                //进入快捷回复界面
                Intent intent = new Intent(getSobotActivity(), SobotQuickReplyActivity.class);
                startActivityForResult(intent, OnlineConstant.SOBOT_REQUEST_CODE_QUICK_REPLY);

            }
        });
        //未总结的会话才能跳转到服务总结页面
        tv_sobot_chat_service_summary = findViewById(getResId("tv_sobot_chat_service_summary"));
        boolean canSummary = SobotSPUtils.getInstance().getBoolean(OnlineConstant
                .OPEN_SUMMARY_FLAG, false);
        if (canSummary) {
            tv_sobot_chat_service_summary.setVisibility(View.VISIBLE);
            if (hasSummary) {
                tv_sobot_chat_service_summary.setText(getResString("sobot_online_has_zongjie"));
                tv_sobot_chat_service_summary.setOnClickListener(null);
            } else {
                tv_sobot_chat_service_summary.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (userInfo != null) {
                            //是否黑名单 1 是 ，0 否
                            int isBlack = userInfo.getIsblack();
                            if (isBlack == 1) {
                                SobotToastUtil.showCustomToast(getSobotActivity(), getResString("online_user_already_lahei"));
                                return;
                            }
                        }
                        hidePanelAndKeyboard(mPanelRoot);
                        //进入服务总结界面
                        Intent intent = new Intent(getSobotActivity(), SobotServiceSummaryActivity.class);
                        intent.putExtra("userInfo", userInfo);
                        intent.putExtra("cid", userInfo.getLastCid());
                        startActivityForResult(intent, OnlineConstant.SOBOT_REQUEST_CODE_SERVICE_SUMMARY);
                    }
                });
            }
        } else {
            tv_sobot_chat_service_summary.setVisibility(View.GONE);
        }

        tv_sobot_chat_intelligence_reply = findViewById(getResId("tv_sobot_chat_intelligence_reply"));
        tv_sobot_chat_intelligence_reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hidePanelAndKeyboard(mPanelRoot);
                if (userInfo != null) {
                    //是否黑名单 1 是 ，0 否
                    int isBlack = userInfo.getIsblack();
                    if (isBlack == 1) {
                        SobotToastUtil.showCustomToast(getSobotActivity(), getResString("online_user_already_lahei"));
                        return;
                    }
                }
                //进入智能回复界面
                Intent intent = new Intent(getSobotActivity(), SobotIntelligenceReplyActivity.class);
                startActivityForResult(intent, OnlineConstant.SOBOT_REQUEST_CODE_INTELLIGENCE_REPLY);

            }
        });

        et_sendmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                resetBtnUploadAndSend();
            }
        });
        et_sendmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doEmoticonBtn2Blur();
                btn_emoticon_view.setBackgroundResource(SobotResourceUtils.getDrawableId(getSobotContext(), "sobot_emoticon_button_selector"));
            }
        });
        et_sendmessage.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View arg0, boolean isFocused) {
                if (isFocused) {
                    int length = et_sendmessage.getText().toString().trim().length();
                    if (length != 0) {
                        sobot_btn_send.setVisibility(View.VISIBLE);
                        sobot_btn_upload_view.setVisibility(View.GONE);
                    }
                    //根据是否有焦点切换实际的背景
                    // edittext_layout.setBackgroundResource(getResDrawableId("sobot_chatting_bottom_bg_focus"));
                } else {
                    //  edittext_layout.setBackgroundResource(getResDrawableId("sobot_chatting_bottom_bg_blur"));
                }
            }
        });

        et_sendmessage.addTextChangedListener(new

                                                      TextWatcher() {
                                                          @Override
                                                          public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                                                              resetBtnUploadAndSend();
                                                          }

                                                          @Override
                                                          public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                                                          }

                                                          @Override
                                                          public void afterTextChanged(Editable arg0) {
                                                          }
                                                      });

        srv_online_msg_list =

                findViewById(getResId("srv_online_msg_list"));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getSobotActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        srv_online_msg_list.setLayoutManager(layoutManager);
        srv_online_msg_list.setLoadingListener(this);
        srv_online_msg_list.setPullRefreshEnabled(true);
        srv_online_msg_list.setLoadingMoreEnabled(false);//禁止加载更多
        //滑动消息列表控件手指松开时隐藏键盘
        srv_online_msg_list.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    hidePanelAndKeyboard(mPanelRoot);
                }
                return false;
            }
        });

        showEmotionBtn();

        //监听聊天的面板
        mKPSwitchListener = KeyboardUtil.attach(

                getSobotActivity(), mPanelRoot,
                new KeyboardUtil.OnKeyboardShowingListener() {
                    @Override
                    public void onKeyboardShowing(boolean isShowing) {
                        resetEmoticonBtn();
                        if (isShowing) {
                            srv_online_msg_list.scrollToPosition(mMsgListAdapter.getItemCount());
                        }
                    }
                });
        KPSwitchConflictUtil.attach(mPanelRoot, sobot_btn_upload_view, et_sendmessage);

        btn_emoticon_view.setOnClickListener(this);
        sobot_btn_send.setOnClickListener(this);
        sobot_btn_upload_view.setOnClickListener(this);

        if (isFromBlack()) {
            sobot_ll_bottom.setVisibility(View.GONE);
            rl_bottom_offline_state.setVisibility(View.VISIBLE);
            OfflineMsgModel data = new OfflineMsgModel();
            data.setStatus(SobotSocketConstant.TYPE_OFFLINEMSG_DISPLAY_BLACK);
            rl_bottom_offline_state.setViewType(userInfo.getId(), data, this);
        } else if (isFromHistory()) {
            sobot_ll_bottom.setVisibility(View.GONE);
            queryUserState();
        } else if (isFromSessionAlert()) {
            if (((CustomerServiceInfoModel) SobotSPUtils.getInstance().getObject(SOBOT_CUSTOM_USER)).getAid().equals(userInfo.getStaffId())) {
                sobot_ll_bottom.setVisibility(View.VISIBLE);
            } else {
                sobot_ll_bottom.setVisibility(View.GONE);
                queryUserState();
            }
        } else if (isFromOnline()) {
            sobot_ll_bottom.setVisibility(View.VISIBLE);
        }

        iv_online_user_zhuanjie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hidePanelAndKeyboard(mPanelRoot);
                if (userInfo != null) {
                    //是否黑名单 1 是 ，0 否
                    int isBlack = userInfo.getIsblack();
                    if (isBlack == 1) {
                        SobotToastUtil.showCustomToast(getSobotActivity(), getResString("online_user_already_lahei"));
                        return;
                    }
                }
                if (isFromHistory()) {
                    SobotToastUtil.showCustomToast(getSobotActivity(), SobotResourceUtils.getResString(getSobotActivity(), "online_user_no_transfer"));
                } else {
                    Intent transferIntent = new Intent(getSobotActivity(), SobotOnlineTransferActivity.class);
                    if (cids != null && cids.size() > 0) {
                        transferIntent.putExtra("cid", cids.get(cids.size() - 1));
                    }
                    transferIntent.putExtra("uid", userInfo.getId());
                    startActivity(transferIntent);
                }

            }
        });
    }

    @Override
    protected void initData() {
        //初始化查询cid
        queryCids();
        String source = getIntent().getStringExtra("userSource");
        if (!TextUtils.isEmpty(source)) {
            userSource = Integer.parseInt(source);
        }
        SobotOnlineLogUtils.i("用户来源：" + userSource);
        mMsgListAdapter = new SobotOnlineMsgAdapter(this, userInfo, userSource, this);
        srv_online_msg_list.setAdapter(mMsgListAdapter);
        mMsgListAdapter.setListAll(data);
        registBroadCast();

        if (userInfo != null) {
            if (userInfo.getIsblack() == 1) {
                iv_online_user_pullblack.setBackgroundResource(SobotResourceUtils.getDrawableId(getSobotContext(), "sobot_online_lahei_sel"));
            } else {
                iv_online_user_pullblack.setBackgroundResource(SobotResourceUtils.getDrawableId(getSobotContext(), "sobot_online_lahei_def"));
            }
            if (userInfo.getIsmark() == 1) {
                iv_online_user_biaoji.setBackgroundResource(SobotResourceUtils.getDrawableId(getSobotContext(), "sobot_online_biaoji_sel"));
            } else {
                iv_online_user_biaoji.setBackgroundResource(SobotResourceUtils.getDrawableId(getSobotContext(), "sobot_online_biaoji_def"));
            }
        }
        iv_online_user_pullblack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hidePanelAndKeyboard(mPanelRoot);
                if (userInfo != null) {
                    int blackFunction = admin != null ? admin.getBlackFunction() : 0;
                    if (blackFunction == 1) {
                        //有权限拉黑功能
                        if (userInfo.getIsblack() == 1) {
                            //解除用户黑名单
                            //removeBlackUsers();
                        } else {
                            //打开拉黑pop
                            Intent pullBlackIntent = new Intent(getSobotActivity(), SobotOnlinePullBlackActivity.class);
                            pullBlackIntent.putExtra("userInfo", userInfo);
                            startActivityForResult(pullBlackIntent, OnlineConstant.SOBOT_REQUEST_CODE_LAHEI);
                        }
                    }
                }

            }
        });

        iv_online_user_biaoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hidePanelAndKeyboard(mPanelRoot);
                if (userInfo != null) {
                    //是否黑名单 1 是 ，0 否
                    int isBlack = userInfo.getIsblack();
                    if (isBlack == 1) {
                        SobotToastUtil.showCustomToast(getSobotActivity(), getResString("online_user_already_lahei"));
                        return;
                    }
                }
                if (userInfo != null) {
                    if (userInfo.getIsmark() == 1) {
                        //解除标记
                        markUsers(false);
                    } else {
                        //标记用户
                        markUsers(true);
                    }
                }

            }
        });
    }


    /**
     * handler 消息实体message 更新ui界面
     *
     * @param messageAdapter
     * @param msg
     */
    public void updateUiMessage(SobotOnlineMsgAdapter messageAdapter, Message msg) {
        ChatMessageModel myMessage = (ChatMessageModel) msg.obj;
        messageAdapter.addItemToLast(myMessage);
//        messageAdapter.notifyDataSetChanged();
    }

    public void updateUiMessageStatus(SobotOnlineMsgAdapter messageAdapter, int position) {
        messageAdapter.notifyItemChanged(position);
    }

    public void updateUiMsg(SobotOnlineMsgAdapter messageAdapter, ChatMessageModel message) {
        messageAdapter.addItemToLast(message);
        messageAdapter.notifyDataSetChanged();
    }


    // 图片通知
    public void sendImageMessageToHandler(String imageUrl, Handler handler,
                                          String id, String lastCid) {
//        ChatMessageModel msgModel = new ChatMessageModel();
//        msgModel.setId(id);
//        msgModel.setCid(lastCid);
//        msgModel.setAction(5);
//        msgModel.setSenderType(2);
//        msgModel.setSender(MyApplication.getUser().getAid());
//        msgModel.setT(System.currentTimeMillis() + "");
//        msgModel.setMsgType(1);
//        msgModel.setMsg(imageUrl);
//        msgModel.setIsSendOk(1);
//        msgModel.setIsHistory(1);
//        if (MyApplication.getUser() != null && !TextUtils.isEmpty(MyApplication.getUser().getFace())) {
//            msgModel.setSenderFace(MyApplication.getUser().getFace());
//        }
//
//        Message message = new Message();
//        message.what = SobotSocketConstant.NEW_INFOMATION;//
//        message.obj = msgModel;
//        handler.sendMessage(message);
    }


    public Handler handler = new Handler() {
        @SuppressWarnings("unchecked")
        public void handleMessage(final android.os.Message msg) {
            switch (msg.what) {
                case SobotSocketConstant.NEW_INFOMATION:
                    updateUiMessage(mMsgListAdapter, msg);
                    srv_online_msg_list.scrollToPosition(mMsgListAdapter.getItemCount());
                    break;
            }
        }
    };

    private void registBroadCast() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(SobotSocketConstant.BROADCAST_SOBOT_MSG);
        filter.addAction(SobotSocketConstant.BROADCAST_SOBOT_UPDATE_USERINFO);
        filter.addAction(SobotSocketConstant.BROADCAST_SOBOT_RESEND_IMG_MSG);/* 重新发送图片广播 */
        filter.addAction(SobotSocketConstant.BROADCAST_SOBOT_RESEND_TEXT_MSG);/* 重新发送文本广播 */
        filter.addAction(SobotSocketConstant.BROADCAST_SOBOT_QUICK_REPLY_SEARCH_CONTENT);/* 重新发送文本广播 */
        filter.addAction(SobotSocketConstant.BROADCAST_CUSTOM_COMITSUMMARY);//提交服务总结
        filter.addAction(SobotSocketConstant.BROADCAST_SOBOT_REVOKE_MESSAGE);//撤回的消息重新编辑广播
        filter.addAction(SobotSocketConstant.BROADCAST_SOBOT_ADD_BLACK);//撤回的消息重新编辑广播
        registerReceiver(receiver, filter);
    }


    //被抢接的客服弹出提示
    private void scrambleFor() {
        OnlineTipDialog tipDialog = new OnlineTipDialog(getSobotContext(), getResString("online_chat_cover_transfer"), getResString("online_know"), new OnlineTipDialog.OnItemClick() {
            @Override
            public void OnClick(int type) {
                finish();
            }
        });
        tipDialog.setCanceledOnTouchOutside(false);
        tipDialog.show();
    }


    //从历史列表进来聊天页
    private boolean isFromHistory() {
        if (flag_from_page.equals("history")) {
            return true;
        }
        return false;
    }

    //从在线用户列表进来聊天页
    private boolean isFromOnline() {
        if (flag_from_page.equals("online")) {
            return true;
        }
        return false;
    }

    //从黑名单列表进来聊天页
    private boolean isFromBlack() {
        if (flag_from_page.equals("black")) {
            // showRightView(0, 0, true);
            return true;
        }
        return false;
    }

    //从会话警报列表页进来聊天页
    private boolean isFromSessionAlert() {
        if (flag_from_page.equals("sessionAlert")) {
            return true;
        }
        return false;
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            SobotOnlineLogUtils.i("SobotOnlineChatActivity-->广播是   ：" + intent.getAction());

            if (SobotSocketConstant.BROADCAST_SOBOT_MSG.equals(intent.getAction())) {
                String msgContentJson = intent.getStringExtra("msgContent");
                SobotOnlineLogUtils.i(msgContentJson);
                PushMessageModel pushMsg = (PushMessageModel) SobotGsonUtil.gsonToBean(msgContentJson, PushMessageModel.class);
                if (pushMsg == null) {
                    return;
                }
                //新消息
                if (pushMsg.getType() == SobotSocketConstant.NEW_INFOMATION) {
                    if (userInfo.getId().equals(pushMsg.getUid())) {
                        if (pushMsg.getContent() != null) {
                            showMessage(pushMsg, 5);
                            MsgCacheManager.getInstance().delUnReadMsgCount(userInfo.getId());
                        }
                    }
                }

                if (pushMsg.getType() == SobotSocketConstant.VISIT_TRAIL) {
                    if (userInfo.getId().equals(pushMsg.getUid())) {
                        if (pushMsg.getContent() != null) {
                            //  showMessage(pushMsg, 5);
                        }
                    }
                }

                if (pushMsg.getType() == SobotSocketConstant.OFFLINE_USER) {
                    if (userInfo.getId().equals(pushMsg.getUid())) {
                        flag_from_page = "history";
                        ChatMessageModel msgModel = new ChatMessageModel();
                        msgModel.setId(System.currentTimeMillis() + "");
                        msgModel.setAction(10);
                        msgModel.setMsg(getResString("online_user_xiaxian"));
                        msgModel.setMessage(pushMsg.getMessage());
                        Message message = new Message();
                        message.what = SobotSocketConstant.NEW_INFOMATION;
                        message.obj = msgModel;
                        handler.sendMessage(message);
                    }
                }

                //用户评价过邀评，服务器发送过来的类型
                if (pushMsg.getType() == SobotSocketConstant.MESSAGE_USER_EVALUATE) {
                    ChatMessageModel msgModel = new ChatMessageModel();
                    msgModel.setId(System.currentTimeMillis() + "");
                    msgModel.setAction(31);
                    msgModel.setTs(SobotTimeUtils.getNowString());
                    msgModel.setMessage(new ChatMessageMsgModel("0", SobotResourceUtils.getResString(getSobotContext(), "online_user_already_evaluate")));
                    Message message = new Message();
                    message.what = SobotSocketConstant.NEW_INFOMATION;
                    message.obj = msgModel;
                    handler.sendMessage(message);
                }

                if (pushMsg.getType() == SobotSocketConstant.NEW_USER) {
                    if (userInfo.getId().equals(pushMsg.getUid())) {
                        flag_from_page = "online";
                        //在当前界面中用户如果重新上线了，那么需要更新cid
                        userInfo.setLastCid(pushMsg.getCid());
                        //重新上线以后，底部输入框显示，离线view影藏
                        sobot_ll_bottom.setVisibility(View.VISIBLE);
                        rl_bottom_offline_state.setVisibility(View.GONE);
                    }
                }

                if (pushMsg.getType() == SobotSocketConstant.INPUTIING) {
                    if (userInfo.getId().equals(pushMsg.getUid())) {
                        ChatMessageModel msgModel = new ChatMessageModel();
                        msgModel.setSenderType(0);
                        msgModel.setAction(5);
                        msgModel.setInputIng(true);
                        msgModel.setMessage(pushMsg.getMessage());
                        msgModel.setMsg(pushMsg.getContent());
                        msgModel.setSenderName(pushMsg.getUname());
                        if (!TextUtils.isEmpty(pushMsg.getFace())) {
                            msgModel.setSenderFace(pushMsg.getFace());
                        }
                        Message message = new Message();
                        message.what = SobotSocketConstant.NEW_INFOMATION;
                        message.obj = msgModel;
//                        handler.sendMessage(message);
                    }
                }

                if (pushMsg.getType() == SobotSocketConstant.VISIT_TRAIL) {
//                    if (userInfo.getId().equals(pushMsg.getUid())) {
//                        VisitTrailModel visitTrailModel = (VisitTrailModel) GsonUtil.jsonToBean(msgContentJson,
//                                VisitTrailModel.class);
//                        SobotLogUtils.i("访问轨迹-----" + visitTrailModel.toString());
//
//                        //添加聊天页面中的访问轨迹
//                        ChatMessageModel msgModel = new ChatMessageModel();
//                        msgModel.setId(System.currentTimeMillis() + "");
//                        msgModel.setAction(SobotSocketConstant.MSG_TYPE_ACTION_VISIT_TRAIL);
//                        msgModel.setVisitTrailModel(visitTrailModel);
//                        msgModel.setMessage(pushMsg.getMessage());
//
//                        Message message = handler.obtainMessage();
//                        message.what = SobotSocketConstant.NEW_INFOMATION;
//                        message.obj = msgModel;
//                        handler.sendMessage(message);
//
//                        //更新访问轨迹窗口中的数据
//                        if (spadapter != null) {
//                            List<VisitTrailModel> allData = spadapter.getAllData();
//                            allData.add(0, visitTrailModel);
//                            spadapter.clear();
//                            spadapter.addAll(allData);
//                        }
//                    }
                }

                if (pushMsg.getType() == SobotSocketConstant.MESSAGE_NOT_DELIVERED) {
                }

                //被别的客服抢接，服务器发送过来的类型
                if (pushMsg.getType() == SobotSocketConstant.ACTIVE_RECEPT) {
                    if (userInfo.getId().equals(pushMsg.getUid())) {
                        sobot_ll_bottom.setVisibility(View.GONE);
                        scrambleFor();//弹出被抢接的提示
                    }
                }

            } else if (SobotSocketConstant.BROADCAST_SOBOT_RESEND_IMG_MSG.equals(intent.getAction())) {
                String msgContentJson = intent.getStringExtra("msgContent");
                try {
                    JSONObject obj = new JSONObject(msgContentJson);
                    String filePath = obj.getString("context");
                    String id = obj.getString("id");
//                    sendFileMsg(isFromHistory(), filePath, OnlineConstant.SOBOT_MSG_TYPE_IMG, id);
                } catch (Exception e) {
                }
                SobotOnlineLogUtils.i("重新发送图片msgContentJson--->" + msgContentJson);
            } else if (SobotSocketConstant.BROADCAST_SOBOT_RESEND_TEXT_MSG.equals(intent.getAction())) {
                String msgContentJson = intent.getStringExtra("msgContent");
                boolean sensitive = intent.getBooleanExtra("sensitive", false);
                String posi = "";
                try {
                    JSONObject obj = new JSONObject(msgContentJson);
                    String msg = obj.getString("context");
                    String id = obj.getString("id");
                    posi = obj.getString("posi");
                    if (sensitive) {
                        posi = "0";
                    }
                    sendMsg(msg, OnlineConstant.SOBOT_MSG_TYPE_TEXT, "", isFromHistory(), null);
                } catch (Exception e) {
                }
                SobotOnlineLogUtils.i("重新发送文本msgContentJson--->" + msgContentJson + "----posi----" + posi);
            } else if (SobotSocketConstant.BROADCAST_SOBOT_UPDATE_USERINFO.equals(intent.getAction())) {
                setTitle(intent.getStringExtra("uname"));
            } else if (SobotSocketConstant.BROADCAST_SOBOT_QUICK_REPLY_SEARCH_CONTENT.equals(intent.getAction())) {
                hidePanelAndKeyboard(mPanelRoot);
                String content = intent.getStringExtra("searchContent");
                String from = intent.getStringExtra("from");
                if ("QuickReplySecondListActivity".equals(from)) {
                    sendClose1BroadCast();
                } else {
                    sendCloseBroadCast();
                }
                if (!TextUtils.isEmpty(content)) {
                    et_sendmessage.setText(content);
                    et_sendmessage.setSelection(content.length());
                    SobotInputHelper.toggleInputMode(getSobotActivity());
                }
            } else if (SobotSocketConstant.BROADCAST_CUSTOM_COMITSUMMARY.equals(intent.getAction())) {
                hasSummary = true;
                if (hasSummary) {
                    tv_sobot_chat_service_summary.setOnClickListener(null);
                    tv_sobot_chat_service_summary.setText(getResString("sobot_online_has_zongjie"));
                }
                hidePanelAndKeyboard(mPanelRoot);
//				setPanelView(mPanelRoot,btn_add.getId());
            } else if (SobotSocketConstant.BROADCAST_SOBOT_REVOKE_MESSAGE.equals(intent.getAction())) {
                String reovkeStr = intent.getStringExtra("message_revoke");
                if (!TextUtils.isEmpty(reovkeStr)) {
                    et_sendmessage.setText(reovkeStr);
                }
            } else if (SobotSocketConstant.BROADCAST_SOBOT_ADD_BLACK.equals(intent.getAction())) {
                sobot_ll_bottom.setVisibility(View.GONE);
                rl_bottom_offline_state.setVisibility(View.VISIBLE);
                OfflineMsgModel data = new OfflineMsgModel();
                data.setStatus(SobotSocketConstant.TYPE_OFFLINEMSG_DISPLAY_BLACK);
                rl_bottom_offline_state.setViewType(userInfo.getId(), data, SobotOnlineChatActivity.this);
            }
        }
    };

    private void showMessage(PushMessageModel pushMsg, int action) {
        ChatMessageModel msgModel = new ChatMessageModel();
        msgModel.setId(System.currentTimeMillis() + "");
        msgModel.setAction(action);
        msgModel.setSenderType(0);
        msgModel.setMsgId(pushMsg.getMsgId());
        msgModel.setSenderName(pushMsg.getUname());
        msgModel.setSenderFace(pushMsg.getFace());
        msgModel.setIsHistory(1);

        if (!TextUtils.isEmpty(pushMsg.getFace())) {
            msgModel.setSenderFace(pushMsg.getFace());
        }
        msgModel.setMessage(pushMsg.getMessage());
        msgModel.setListend(true);
        Message message = handler.obtainMessage();
        message.what = SobotSocketConstant.NEW_INFOMATION;
        message.obj = msgModel;
        handler.sendMessage(message);
    }

    /**
     * 初始化查询cid的列表
     */
    private void queryCids() {
        getCidsFinish = false;
        if (userInfo == null) {
            return;
        }
        zhiChiApi.queryCids(getSobotActivity(), userInfo.getId(), new SobotResultCallBack<CidsModel>() {
            @Override
            public void onSuccess(CidsModel cidsModel) {
                getCidsFinish = true;
                if (cidsModel != null) {
                    cids = cidsModel.getCids();
                    if (cids != null) {
                        boolean hasRepet = false;
                        for (int i = 0; i < cids.size(); i++) {
                            if (cids.get(i).equals(userInfo.getLastCid())) {
                                hasRepet = true;
                                break;
                            }
                        }
                        if (!hasRepet) {
                            cids.add(userInfo.getLastCid());
                        }
                        Collections.reverse(cids);
                        SobotOnlineLogUtils.i("cids:" + cids.toString());
                        getHistoryMsg(true);
                    }
                }
            }

            @Override
            public void onFailure(Exception e, String des) {
                getCidsFinish = true;
                e.printStackTrace();
            }
        });
    }

    /**
     * 根据当前cid的位置获取cid
     *
     * @return
     */
    public String getCurrentCid() {
        String currentCid = userInfo.getLastCid();
        if (currentCidPosition > 0) {
            if (currentCidPosition > cids.size() - 1) {
                currentCid = "-1";
            } else {
                currentCid = cids.get(currentCidPosition);
            }
        }
        return currentCid;
    }

    private void getHistoryMsg(final boolean isFirst) {
        if ((!getCidsFinish && !isFirst) || isInGethistory) {
            //1.查询cid接口没有结束时 又不是第一次查询历史记录  那么 直接什么也不做就返回
            //2.如果查询历史记录的接口正在跑   那么什么也不做
            srv_online_msg_list.refreshComplete();
        } else {
            final Map<String, String> map = new HashMap<>();
            String currentCid = getCurrentCid();
            if ("-1".equals(currentCid)) {
                srv_online_msg_list.refreshComplete();
                return;
            }
            map.put("cid", currentCid);
            map.put("uid", userInfo.getId());
            SobotOnlineLogUtils.i("userInfo.getId() ----  >" + userInfo.getId());
            isInGethistory = true;
            zhiChiApi.queryHistoryRecords(getSobotActivity(), map, new SobotResultCallBack<List<ChatMessageModel>>() {
                @Override
                public void onSuccess(List<ChatMessageModel> messageModels) {
                    isInGethistory = false;
                    srv_online_msg_list.refreshComplete();
                    List<ChatMessageModel> tempdata = new ArrayList<>();
                    if (messageModels != null && messageModels.size() > 0) {
                        for (int i = 0; i < messageModels.size(); i++) {
                            ChatMessageModel model = messageModels.get(i);
                            if (model != null) {
                                if (1 == model.getUserNoSeeFlag()) {
                                    model.setIsSendOk(0);
                                    tempdata.add(model);
                                    ChatMessageModel msgModel = new ChatMessageModel();
                                    msgModel.setAction(MSG_TYPE_SENSITIVE);
                                    msgModel.setId(System.currentTimeMillis() + "");
                                    msgModel.setCid(userInfo.getLastCid());
                                    msgModel.setSenderType(2);
                                    msgModel.setMsgType(MessageType.MESSAGE_TYPE_SENSITIVE);
                                    msgModel.setIsHistory(1);
                                    msgModel.setMessage(model.getMessage());
                                    msgModel.setSender(((CustomerServiceInfoModel) SobotSPUtils.getInstance().getObject(SOBOT_CUSTOM_USER)).getAid());
                                    msgModel.setSenderName(((CustomerServiceInfoModel) SobotSPUtils.getInstance().getObject(SOBOT_CUSTOM_USER)).getNickName());
                                    msgModel.setRevokeFlag(1);
                                    msgModel.setSensitive(true);
                                    if (!model.isRevokeFlag()) {
                                        tempdata.add(msgModel);
                                    }
                                } else {
                                    tempdata.add(model);
                                }
                            }
                        }
                    }
                    data.addAll(tempdata);
                    mMsgListAdapter.addItemsToHead(tempdata);
                    currentCidPosition++;
                    if (isFirst) {
                        srv_online_msg_list.scrollToPosition(data.size());
                    }
                }

                @Override
                public void onFailure(Exception e, String des) {
                    isInGethistory = false;
                    srv_online_msg_list.refreshComplete();
                }
            });
        }

    }


    @Override
    public void onRefresh() {
        getHistoryMsg(false);
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        KeyboardUtil.detach(getSobotActivity(), mKPSwitchListener);
        try {
            unregisterReceiver(receiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 重置表情按钮的焦点键盘
     */
    public void resetEmoticonBtn() {
        String panelViewTag = getPanelViewTag(mPanelRoot);
        String instanceTag = CustomeViewFactory.getInstanceTag(getSobotContext(), btn_emoticon_view.getId());
        if (mPanelRoot.getVisibility() == View.VISIBLE && instanceTag.equals(panelViewTag)) {
            doEmoticonBtn2Focus();
        } else {
            doEmoticonBtn2Blur();
        }
    }

    /**
     * 使表情按钮获取焦点
     */
    public void doEmoticonBtn2Focus() {
        btn_emoticon_view.setSelected(true);
    }

    /**
     * 使表情按钮失去焦点
     */
    public void doEmoticonBtn2Blur() {
        btn_emoticon_view.setSelected(false);
    }

    /**
     * 获取当前显示的聊天面板的tag
     *
     * @param panelLayout
     */
    private String getPanelViewTag(final View panelLayout) {
        String str = "";
        if (panelLayout instanceof KPSwitchPanelLinearLayout) {
            KPSwitchPanelLinearLayout tmpView = (KPSwitchPanelLinearLayout) panelLayout;
            View childView = tmpView.getChildAt(0);
            if (childView != null && childView instanceof CustomeChattingPanel) {
                CustomeChattingPanel customeChattingPanel = (CustomeChattingPanel) childView;
                str = customeChattingPanel.getPanelViewTag();
            }
        }
        return str;
    }

    @Override
    public void onClick(View view) {
        if (view == sobot_btn_send) {// 发送消息按钮
            //获取发送内容
            final String message_result = et_sendmessage.getText().toString().trim();
            if (!TextUtils.isEmpty(message_result)) {
                //转人工接口没跑完的时候  屏蔽发送，防止统计出现混乱
                resetEmoticonBtn();
                try {
                    sendMsg(message_result, OnlineConstant.SOBOT_MSG_TYPE_TEXT, "", isFromHistory(), null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        if (view == sobot_btn_upload_view) {// 点击加号按钮,显示功能菜单
            pressSpeakSwitchPanelAndKeyboard(sobot_btn_upload_view);
            doEmoticonBtn2Blur();
            gotoLastItem();
        }

        if (view == btn_emoticon_view) {//显示表情面板
            // 切换表情面板
            pressSpeakSwitchPanelAndKeyboard(btn_emoticon_view);
            //切换表情按钮的状态
            switchEmoticonBtn();
            gotoLastItem();
        }

    }

    private void gotoLastItem() {
        if (srv_online_msg_list != null) {
            srv_online_msg_list.scrollToPosition(srv_online_msg_list.getItemCount());
        }
    }

    /*
     * 切换键盘和面板的方法 一般都用这个就行
     * 参数是按下的那个按钮
     */
    public void pressSpeakSwitchPanelAndKeyboard(final View switchPanelKeyboardBtn) {
        //切换更多方法的面板
        switchPanelAndKeyboard(mPanelRoot, switchPanelKeyboardBtn, et_sendmessage);

    }

    /**
     * 切换表情按钮焦点
     */
    public void switchEmoticonBtn() {
        boolean flag = btn_emoticon_view.isSelected();
        if (flag) {
            doEmoticonBtn2Blur();
        } else {
            doEmoticonBtn2Focus();
        }
        //切换表情和键盘
        if (btn_emoticon_view.isSelected()) {
            btn_emoticon_view.setBackgroundResource(SobotResourceUtils.getDrawableId(getSobotContext(), "sobot_keyboard_normal"));
        } else {
            btn_emoticon_view.setBackgroundResource(SobotResourceUtils.getDrawableId(getSobotContext(), "sobot_emoticon_button_selector"));
        }
    }


    //切换键盘和面板的方法
    public void switchPanelAndKeyboard(final View panelLayout, final View switchPanelKeyboardBtn, final View focusView) {
        if (currentPanelId == 0 || currentPanelId == switchPanelKeyboardBtn.getId()) {
            //没选中的时候或者  点击是自身的时候正常切换面板和键盘
            boolean switchToPanel = panelLayout.getVisibility() != View.VISIBLE;
            if (!switchToPanel) {
                KPSwitchConflictUtil.showKeyboard(panelLayout, focusView);
            } else {
                KPSwitchConflictUtil.showPanel(panelLayout);
                setPanelView(panelLayout, switchPanelKeyboardBtn.getId());
            }
        } else {
            //之前选过  但是现在点击的不是自己的时候  显示自己的面板
            KPSwitchConflictUtil.showPanel(panelLayout);
            setPanelView(panelLayout, switchPanelKeyboardBtn.getId());
        }
        currentPanelId = switchPanelKeyboardBtn.getId();
    }

    /**
     * 设置聊天面板的view
     *
     * @param panelLayout
     * @param btnId
     */
    private void setPanelView(final View panelLayout, int btnId) {
        if (panelLayout instanceof KPSwitchPanelLinearLayout) {
            KPSwitchPanelLinearLayout tmpView = (KPSwitchPanelLinearLayout) panelLayout;
            View childView = tmpView.getChildAt(0);
            if (childView != null && childView instanceof CustomeChattingPanel) {
                CustomeChattingPanel customeChattingPanel = (CustomeChattingPanel) childView;
                Bundle bundle = new Bundle();
                customeChattingPanel.setupView(btnId, bundle, this);
            }
        }
    }


    /**
     * 隐藏键盘和面板
     *
     * @param layout
     */
    public void hidePanelAndKeyboard(KPSwitchPanelLinearLayout layout) {
        KPSwitchConflictUtil.hidePanelAndKeyboard(layout);
        doEmoticonBtn2Blur();
        currentPanelId = 0;
    }


    /**
     * 根据输入框里的内容切换显示  发送按钮还是加号（更多方法）
     */
    private void resetBtnUploadAndSend() {
        if (et_sendmessage.getText().toString().length() > 0) {
            sobot_btn_upload_view.setVisibility(View.GONE);
            sobot_btn_send.setVisibility(View.VISIBLE);
        } else {
            sobot_btn_send.setVisibility(View.GONE);
            sobot_btn_upload_view.setVisibility(View.VISIBLE);
            sobot_btn_upload_view.setEnabled(true);
            sobot_btn_upload_view.setClickable(true);
            if (Build.VERSION.SDK_INT >= 11) {
                sobot_btn_upload_view.setAlpha(1f);
            }
        }
    }

    /**
     * 显示表情按钮   如果没有表情资源则不会显示此按钮
     */
    private void showEmotionBtn() {
        Map<String, String> mapAll = DisplayEmojiRules.getMapAll(getSobotContext());
        if (mapAll.size() > 0) {
            btn_emoticon_view.setVisibility(View.VISIBLE);
        } else {
            btn_emoticon_view.setVisibility(View.GONE);
        }
    }


    /**
     * 输入表情的方法
     *
     * @param item
     */
    @Override
    public void inputEmoticon(EmojiconNew item) {
        SobotInputHelper.input2OSC(et_sendmessage, item);
    }

    /**
     * 输入框删除的方法
     */
    @Override
    public void backspace() {
        SobotInputHelper.backspace(et_sendmessage);
    }

    /**
     * 提供给聊天面板执行的方法
     * 图库
     */
    @Override
    public void btnPicture() {
        hidePanelAndKeyboard(mPanelRoot);
        selectPicFromLocal();
        gotoLastItem();
    }

    /**
     * 提供给聊天面板执行的方法
     * 视频
     */
    @Override
    public void btnVedio() {
        hidePanelAndKeyboard(mPanelRoot);
        selectVedioFromLocal();
        gotoLastItem();
    }

    /**
     * 提供给聊天面板执行的方法
     * 照相
     */
    @Override
    public void btnCameraPicture() {
        hidePanelAndKeyboard(mPanelRoot);
        selectPicFromCamera(); // 拍照 上传
        gotoLastItem();
    }

    //发送消息 文本 图片 视频
    private void sendMsg(final String msgContent, int msgType, final String filePath, boolean isOffline, List<ChatMessageRichTextModel.ChatMessageRichListModel> richListModels) {
        et_sendmessage.setText("");
        Map<String, String> map = new HashMap<>();
        map.put("content", msgContent);
        map.put("uid", userInfo.getId());
        map.put("cid", userInfo.getLastCid());
        if (msgType == OnlineConstant.SOBOT_MSG_TYPE_RICH) {
            map.put("objMsgType", "0");
            map.put("msgType", "5");
        } else {
            map.put("msgType", msgType + "");
        }

        final ChatMessageModel msgModel = new ChatMessageModel();
        msgModel.setAdminSendContent(msgContent);
        msgModel.setAdminSendFilePath(filePath);
        msgModel.setSensitive(false);
        msgModel.setT(System.currentTimeMillis() + "");
        msgModel.setCid(userInfo.getLastCid());
        msgModel.setAction(5);
        msgModel.setSenderType(2);
        msgModel.setSender(admin.getAid());
        msgModel.setSenderName(admin.getNickName());
        msgModel.setIsHistory(1);
        final ChatMessageMsgModel chatMessageMsgModel = new ChatMessageMsgModel();
        chatMessageMsgModel.setMsgType(msgType + "");
        if (OnlineConstant.SOBOT_MSG_TYPE_TEXT == msgType) {
            chatMessageMsgModel.setContent(msgContent);
        } else if (OnlineConstant.SOBOT_MSG_TYPE_IMG == msgType) {
            //图片类型
            chatMessageMsgModel.setContent(filePath);
        } else if (OnlineConstant.SOBOT_MSG_TYPE_VIDEO == msgType) {
            //视频类型
            File videoFile = new File(filePath);
            String fName = SobotMD5Utils.getMD5Str(videoFile.getAbsolutePath());
            final ChatMessageVideoModel chatMessageVideoModel = new ChatMessageVideoModel();
            chatMessageVideoModel.setFileName(fName);
            chatMessageVideoModel.setUrl(filePath);
            chatMessageVideoModel.setSnapshot(SobotOnlineImageUtils.getThumbPath(filePath));
            chatMessageMsgModel.setContent(chatMessageVideoModel);
        } else if (OnlineConstant.SOBOT_MSG_TYPE_RICH == msgType && richListModels != null) {
            //富文本类型
            ChatMessageRichTextModel richTextModel = new ChatMessageRichTextModel();
            richTextModel.setRichList(richListModels);
            ChatMessageObjectModel messageObjectModel = new ChatMessageObjectModel(richTextModel, 0);
            chatMessageMsgModel.setContent(messageObjectModel);
            chatMessageMsgModel.setMsgType("5");
        }
        msgModel.setMessage(chatMessageMsgModel);
        //发送中
        msgModel.setIsSendOk(2);
        if (admin != null && !TextUtils.isEmpty(admin.getFace())) {
            msgModel.setSenderFace(admin.getFace());
        }
        Message message = handler.obtainMessage();
        message.what = SobotSocketConstant.NEW_INFOMATION;
        message.obj = msgModel;
        handler.sendMessage(message);
        zhiChiApi.send(getSobotActivity(), isOffline, map, filePath, new SobotFileResultCallBack<OnlineMsgModelResult>() {
            @Override
            public void onSuccess(OnlineMsgModelResult msgModelResult) {
                if (msgModelResult == null && msgModelResult.getData() != null) {
                    msgModel.setIsSendOk(0);
                    mMsgListAdapter.notifyDataSetChanged();
                    return;
                }
                if (!TextUtils.isEmpty(msgModelResult.getData().getMsgId())) {
                    msgModel.setMsgId(msgModelResult.getData().getMsgId());
                }else {
                    msgModel.setMsgId(System.currentTimeMillis()+"");
                }
                if (isFromHistory()) {
                    //离线消息
                    if (!TextUtils.isEmpty(msgModelResult.getData().getStatus())) {
                        //1-机器人,2-人工，3-排队，4-访问页面，5-微信端大于48小时，6-可以发送离线消息
                        switch (msgModelResult.getData().getStatus()) {
                            case "1":
                            case "3":
                            case "4":
                            case "5":
                                //离线消息发送失败
                                msgModel.setIsSendOk(0);
                                queryUserState();
                                break;
                            case "2":
                                //登录人和发送人是否时同一人
                                if (TextUtils.isEmpty(msgModelResult.getData().getAid()) && admin.getAid().equals(msgModelResult.getData().getAid())) {
                                    msgModel.setIsSendOk(1);
                                } else {
                                    msgModel.setIsSendOk(0);
                                    queryUserState();
                                }
                                break;
                            case "6":
                                msgModel.setIsSendOk(1);
                                break;
                        }
                    }
                } else {
                    //在线消息
                    msgModel.setIsSendOk(1);
                }
                if (OnlineConstant.result_success_code_sensitive_words.equals(msgModelResult.getRetCode())) {
                    //包含敏感词，发送失败
                    msgModel.setIsSendOk(0);
                }
                if (admin != null && !TextUtils.isEmpty(admin.getFace())) {
                    msgModel.setSenderFace(admin.getFace());
                }
                if (msgModel.getMessage().getContent() instanceof ChatMessageVideoModel) {
                    //视频类型
                    ChatMessageVideoModel chatMessageVideoModel = new ChatMessageVideoModel();
                    chatMessageVideoModel.setUrl(filePath);
                    chatMessageVideoModel.setFileName(!TextUtils.isEmpty(msgModelResult.getData().getFileName()) ? msgModelResult.getData().getFileName() : "");
                    chatMessageVideoModel.setServiceUrl(!TextUtils.isEmpty(msgModelResult.getData().getFileUrl()) ? msgModelResult.getData().getFileUrl() : "");
                    msgModel.getMessage().setContent(chatMessageVideoModel);
                }
                if (OnlineConstant.result_success_code_sensitive_words.equals(msgModelResult.getRetCode())) {
                    ChatMessageModel msgModel1 = new ChatMessageModel();
                    msgModel1.setAction(SobotSocketConstant.MSG_TYPE_SENSITIVE);
                    msgModel1.setMsgId(msgModelResult.getData().getMsgId());
                    msgModel1.setId(msgModelResult.getData().getMsgId());
                    msgModel1.setCid(userInfo.getLastCid());
                    msgModel1.setSenderType(2);
                    msgModel1.setMsgType(MessageType.MESSAGE_TYPE_SENSITIVE);
                    //1历史记录消息 0当前会话消息
                    msgModel1.setIsHistory(1);
                    msgModel1.setSender(admin.getAid());
                    msgModel1.setSenderName(admin.getNickName());
                    msgModel1.setRevokeFlag(1);
                    msgModel1.setSensitive(true);
                    msgModel1.setMessage(new ChatMessageMsgModel(OnlineConstant.SOBOT_MSG_TYPE_TEXT + "", msgContent));

                    Message message1 = handler.obtainMessage();
                    message1.what = SobotSocketConstant.NEW_INFOMATION;
                    message1.obj = msgModel1;
                    handler.sendMessage(message1);
                }
                if (msgModel.getIsSendOk() == 1) {
                    //通知当前会话列表更新最后一次聊天数据
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("lastMsg", msgModel.getMessage());
                    intent.setAction(SobotSocketConstant.BROADCAST_SOBOT_UPDATE_LAST_MSG);
                    intent.putExtra("uid", userInfo.getId());
                    intent.putExtra("ts", SobotTimeUtils.getNowString());
                    intent.putExtras(bundle);
                    SobotUtils.getApp().sendBroadcast(intent);
                }
                mMsgListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Exception e, String des) {
                msgModel.setIsSendOk(0);
                mMsgListAdapter.notifyDataSetChanged();
            }

            @Override
            public void inProgress(int currentProgress) {
                SobotOnlineLogUtils.i("-----进度: " + currentProgress + "%");
            }
        });
    }

    //发送消息 文本 图片 视频
    private void reSendMsg(final ChatMessageModel msgModel) {
        Map<String, String> map = new HashMap<>();
        map.put("content", msgModel.getAdminSendContent());
        map.put("uid", userInfo.getId());
        map.put("cid", userInfo.getLastCid());
        map.put("msgType", msgModel.getMsgType() + "");
        zhiChiApi.send(getSobotActivity(), isFromHistory(), map, msgModel.getAdminSendFilePath(), new SobotFileResultCallBack<OnlineMsgModelResult>() {
            @Override
            public void onSuccess(OnlineMsgModelResult msgModelResult) {
                if (msgModelResult == null && msgModelResult.getData() != null) {
                    msgModel.setIsSendOk(0);
                    mMsgListAdapter.notifyDataSetChanged();
                    return;
                }
                msgModel.setMsgId(msgModelResult.getData().getMsgId());
                if (isFromHistory()) {
                    //离线消息
                    if (msgModelResult != null && !TextUtils.isEmpty(msgModelResult.getData().getStatus())) {
                        //离线消息发送失败
                        msgModel.setIsSendOk(0);
                        queryUserState();
                    } else {
                        msgModel.setIsSendOk(1);
                    }
                } else {
                    //在线消息
                    msgModel.setIsSendOk(1);
                }
                if (OnlineConstant.result_success_code_sensitive_words.equals(msgModelResult.getRetCode())) {
                    //包含敏感词，发送失败
                    msgModel.setIsSendOk(0);
                }
                if (OnlineConstant.result_success_code_sensitive_words.equals(msgModelResult.getRetCode())) {
                    //包含敏感词，发送失败
                    msgModel.setIsSendOk(0);
                }
                msgModel.setT(System.currentTimeMillis() + "");
                if (OnlineConstant.result_success_code_sensitive_words.equals(msgModelResult.getRetCode())) {
                    ChatMessageModel msgModel1 = new ChatMessageModel();
                    msgModel1.setAction(SobotSocketConstant.MSG_TYPE_SENSITIVE);
                    msgModel1.setMsgId(msgModelResult.getData().getMsgId());
                    msgModel1.setId(msgModelResult.getData().getMsgId());
                    msgModel1.setCid(userInfo.getLastCid());
                    msgModel1.setSenderType(2);
                    msgModel1.setMsgType(MessageType.MESSAGE_TYPE_SENSITIVE);
                    //1历史记录消息 0当前会话消息
                    msgModel1.setIsHistory(1);
                    msgModel1.setSender(admin.getAid());
                    msgModel1.setSenderName(admin.getNickName());
                    msgModel1.setRevokeFlag(1);
                    msgModel1.setSensitive(true);
                    msgModel1.setMessage(new ChatMessageMsgModel(OnlineConstant.SOBOT_MSG_TYPE_TEXT + "", msgModel.getAdminSendContent()));

                    Message message1 = handler.obtainMessage();
                    message1.what = SobotSocketConstant.NEW_INFOMATION;
                    message1.obj = msgModel1;
                    handler.sendMessage(message1);
                }
                if (msgModel.getIsSendOk() == 1) {
                    //通知当前会话列表更新最后一次聊天数据
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("lastMsg", msgModel.getMessage());
                    intent.setAction(SobotSocketConstant.BROADCAST_SOBOT_UPDATE_LAST_MSG);
                    intent.putExtra("uid", userInfo.getId());
                    intent.putExtra("ts", SobotTimeUtils.getNowString());
                    intent.putExtras(bundle);
                    SobotUtils.getApp().sendBroadcast(intent);
                }
                mMsgListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Exception e, String des) {
                msgModel.setIsSendOk(0);
                mMsgListAdapter.notifyDataSetChanged();
            }

            @Override
            public void inProgress(int currentProgress) {
                SobotOnlineLogUtils.i("-----进度: " + currentProgress + "%");
            }
        });
    }


    /**
     * 查询用户当前状态
     * 用来作为是否能发送离线消息的判断依据
     */
    private void queryUserState() {
        zhiChiApi.getStatusNow(getSobotActivity(), userInfo.getId(), new SobotResultCallBack<OfflineMsgModel>() {
            @Override
            public void onSuccess(OfflineMsgModel offlineMsgModel) {
                updataOfflineMsgUi(offlineMsgModel);
            }

            @Override
            public void onFailure(Exception e, String des) {

            }
        });
    }

    private void updataOfflineMsgUi(OfflineMsgModel data) {
        if (data != null && !TextUtils.isEmpty(data.getStatus()) && (SobotSocketConstant.TYPE_OFFLINEMSG_CHAT_TO_ROBOT.equals(data.getStatus())
                || (SobotSocketConstant.TYPE_OFFLINEMSG_CHAT_TO_CUSTOMER.equals(data.getStatus()))
                || SobotSocketConstant.TYPE_OFFLINEMSG_LINEUP.equals(data.getStatus())
                || SobotSocketConstant.TYPE_OFFLINEMSG_VISITOR.equals(data.getStatus())
                || SobotSocketConstant.TYPE_OFFLINEMSG_WECHAT_USER_OUTTIME.equals(data.getStatus())
                || SobotSocketConstant.TYPE_OFFLINEMSG_DISPLAY_INPUTBOX.equals(data.getStatus()))) {
            hidePanelAndKeyboard(mPanelRoot);
            sobot_ll_bottom.setVisibility(View.GONE);
            rl_bottom_offline_state.setVisibility(View.VISIBLE);
            rl_bottom_offline_state.setViewType(userInfo.getId(), data, this);
        } else {
            sobot_ll_bottom.setVisibility(View.VISIBLE);
        }
        if (data != null && SobotSocketConstant.TYPE_OFFLINEMSG_CHAT_TO_CUSTOMER.equals(data.getStatus()) && data.getAid().equals(admin.getAid())) {
            //如果当前客户正在和客服自己聊天，改为在线，可以继续聊天
            flag_from_page = "online";
            sobot_ll_bottom.setVisibility(View.VISIBLE);
            rl_bottom_offline_state.setVisibility(View.GONE);
            userInfo.setLastCid(data.getCid());
        }
    }


    private void sendCloseBroadCast() {
        Intent intent = new Intent();
        intent.setAction("com.sobot.close.beforeactivity");
        sendBroadcast(intent);
    }

    private void sendClose1BroadCast() {
        Intent intent = new Intent();
        intent.setAction("com.sobot.close.beforeactivity1");
        sendBroadcast(intent);
    }

    @Override
    public void onOfflineStateCallBack(int eventType) {
        switch (eventType) {
            case OfflineStateView.showinputbox:
                sobot_ll_bottom.setVisibility(View.VISIBLE);
                rl_bottom_offline_state.setVisibility(View.GONE);
                break;
            case OfflineStateView.showNetError:
                break;

        }
    }

    /**
     * 解除用户黑名单
     */
    private void removeBlackUsers() {
        if (userInfo == null || TextUtils.isEmpty(userInfo.getId())) {
            return;
        }
        SobotDialogUtils.startProgressDialog(getSobotContext());
        zhiChiApi.addOrDeleteBlackList(getSobotActivity(), userInfo.getId(), "", 0, SobotOnlineUrlApi.api_deleteBlackList, new SobotResultCallBack<OnlineBaseCode>() {
            @Override
            public void onSuccess(OnlineBaseCode OnlineBaseCode) {
                SobotDialogUtils.stopProgressDialog(getSobotContext());
                if (OnlineBaseCode != null) {
                    SobotToastUtil.showCustomToast(getSobotContext(), SobotResourceUtils.getResString(getSobotContext(), "online_remove_user_black_success"));
                    iv_online_user_pullblack.setBackgroundResource(SobotResourceUtils.getDrawableId(getSobotContext(), "sobot_online_lahei_def"));
                    userInfo.setIsblack(0);
                }
            }

            @Override
            public void onFailure(Exception e, String des) {
                SobotToastUtil.showCustomToast(getSobotContext(), des);
                SobotDialogUtils.stopProgressDialog(getSobotContext());
            }
        });
    }

    /**
     * 标记用户的功能
     *
     * @param isAddOrDeleteMark true 标记用户 false 取消标记
     */
    private void markUsers(final boolean isAddOrDeleteMark) {
        final String url;
        if (isAddOrDeleteMark) {
            url = SobotOnlineUrlApi.api_addMarkList;
        } else {
            url = SobotOnlineUrlApi.api_deleteMarkList;
        }

        if (userInfo == null || TextUtils.isEmpty(userInfo.getId())) {
            return;
        }
        SobotDialogUtils.startProgressDialog(getSobotContext());
        zhiChiApi.addOrDeleteMarkList(getSobotActivity(), userInfo.getId(), url,
                new SobotResultCallBack<OnlineBaseCode>() {
                    @Override
                    public void onSuccess(OnlineBaseCode OnlineBaseCode) {
                        SobotDialogUtils.stopProgressDialog(getSobotContext());
                        if (OnlineBaseCode != null) {
                            if (isAddOrDeleteMark) {
                                userInfo.setIsmark(1);
                                SobotToastUtil.showCustomToast(getSobotContext(), SobotResourceUtils.getResString(getSobotContext(), "online_add_user_mark_success"));
                                iv_online_user_biaoji.setBackgroundResource(SobotResourceUtils.getDrawableId(getSobotContext(), "sobot_online_biaoji_sel"));
                            } else {
                                userInfo.setIsmark(0);
                                SobotToastUtil.showCustomToast(getSobotContext(), SobotResourceUtils.getResString(getSobotContext(), "online_remove_user_mark_success"));
                                iv_online_user_biaoji.setBackgroundResource(SobotResourceUtils.getDrawableId(getSobotContext(), "sobot_online_biaoji_def"));
                            }
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent();
                                    intent.putExtra("uid", userInfo.getId());
                                    intent.putExtra("markStatus", userInfo.getIsmark());
                                    intent.setAction(SobotSocketConstant.BROADCAST_SOBOT_MARK);
                                    SobotUtils.getApp().sendBroadcast(intent);
                                }
                            }, 1000);
                        }
                    }

                    @Override
                    public void onFailure(Exception e, String des) {
                        SobotToastUtil.showCustomToast(getSobotContext(), des);
                        SobotDialogUtils.stopProgressDialog(getSobotContext());
                    }
                });
    }

    public void invateEvaluate() {
        if (userInfo == null)
            return;
        //邀请评价
        SobotOnlineLogUtils.i("主动邀请评价 start");
        zhiChiApi.invateEvaluateInt(getSobotActivity(), userInfo.getLastCid(), new SobotResultCallBack<OnlineCommonModel>() {
            @Override
            public void onSuccess(OnlineCommonModel evaluateResult) {
                if (evaluateResult != null) {
                    //成功
                    if ("0".equals(evaluateResult.getStatus())) {
                        ChatMessageModel msgModel = new ChatMessageModel();
                        msgModel.setId(System.currentTimeMillis() + "");
                        msgModel.setAction(31);
                        msgModel.setTs(SobotTimeUtils.getNowString());
                        msgModel.setMessage(new ChatMessageMsgModel("0", SobotResourceUtils.getResString(getSobotContext(), "online_send_evaluate")));
                        Message message = new Message();
                        message.what = SobotSocketConstant.NEW_INFOMATION;
                        message.obj = msgModel;
                        handler.sendMessage(message);
                    } else if ("1".equals(evaluateResult.getStatus())) {
                        ChatMessageModel msgModel = new ChatMessageModel();
                        msgModel.setId(System.currentTimeMillis() + "");
                        msgModel.setAction(31);
                        msgModel.setMessage(new ChatMessageMsgModel("0", SobotResourceUtils.getResString(getSobotContext(), "online_user_already_evaluate")));
                        Message message = new Message();
                        message.what = SobotSocketConstant.NEW_INFOMATION;
                        message.obj = msgModel;
                        handler.sendMessage(message);
                    } else if ("2".equals(evaluateResult.getStatus())) {
                        ChatMessageModel msgModel = new ChatMessageModel();
                        msgModel.setId(System.currentTimeMillis() + "");
                        msgModel.setAction(31);
                        msgModel.setMessage(new ChatMessageMsgModel("0", SobotResourceUtils.getResString(getSobotContext(), "online_no_support_evaluate")));
                        Message message = new Message();
                        message.what = SobotSocketConstant.NEW_INFOMATION;
                        message.obj = msgModel;
                        handler.sendMessage(message);
                    } else if ("3".equals(evaluateResult.getStatus())) {
                        ChatMessageModel msgModel = new ChatMessageModel();
                        msgModel.setId(System.currentTimeMillis() + "");
                        msgModel.setAction(31);
                        msgModel.setMessage(new ChatMessageMsgModel("0", SobotResourceUtils.getResString(getSobotContext(), "online_evaluate_need_zixun")));
                        Message message = new Message();
                        message.what = SobotSocketConstant.NEW_INFOMATION;
                        message.obj = msgModel;
                        handler.sendMessage(message);
                    } else if ("4".equals(evaluateResult.getStatus())) {
                        ChatMessageModel msgModel = new ChatMessageModel();
                        msgModel.setId(System.currentTimeMillis() + "");
                        msgModel.setAction(31);
                        msgModel.setMessage(new ChatMessageMsgModel("0", SobotResourceUtils.getResString(getSobotContext(), "online_fuwu_exception")));
                        Message message = new Message();
                        message.what = SobotSocketConstant.NEW_INFOMATION;
                        message.obj = msgModel;
                        handler.sendMessage(message);
                    } else if ("5".equals(evaluateResult.getStatus())) {
                        zhiChiApi.invateEvaluateIntOff(getSobotActivity(), userInfo.getLastCid(), new SobotResultCallBack<OnlineCommonModel>() {
                            @Override
                            public void onSuccess(OnlineCommonModel commonModelResult) {
                                if (commonModelResult != null && !TextUtils.isEmpty(commonModelResult.getStatus())) {
                                    //成功
                                    if (commonModelResult != null && "1".equals(commonModelResult.getStatus())) {
                                        ChatMessageModel msgModel = new ChatMessageModel();
                                        msgModel.setId(System.currentTimeMillis() + "");
                                        msgModel.setAction(31);
                                        msgModel.setMessage(new ChatMessageMsgModel("0", SobotResourceUtils.getResString(getSobotContext(), "online_user_already_evaluate")))
                                        ;
                                        Message message = new Message();
                                        message.what = SobotSocketConstant.NEW_INFOMATION;
                                        message.obj = msgModel;
                                        handler.sendMessage(message);
                                    } else {
                                        ChatMessageModel msgModel = new ChatMessageModel();
                                        msgModel.setId(System.currentTimeMillis() + "");
                                        msgModel.setAction(31);
                                        msgModel.setMessage(new ChatMessageMsgModel("0", SobotResourceUtils.getResString(getSobotContext(), "online_cannt_evaluate")));
                                        Message message = new Message();
                                        message.what = SobotSocketConstant.NEW_INFOMATION;
                                        message.obj = msgModel;
                                        handler.sendMessage(message);

                                    }
                                }
                            }

                            @Override
                            public void onFailure(Exception e, String des) {

                            }
                        });
                    }
                    //Intent intent = new Intent();
                    //intent.setAction(SobotSocketConstant.BROADCAST_CUSTOM_COMITSUMMARY);
                    //intent.putExtra("cid", userInfo.getLastCid());
                    //sendBroadcast(intent);
                }
            }

            @Override
            public void onFailure(Exception e, String des) {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);

            SobotOnlineLogUtils.i("onActivityResult返回的结果：" + requestCode + "--" + resultCode + "--" + data);
            if (resultCode == RESULT_OK) {
                if (requestCode == OnlineConstant.SOBOT_REQUEST_CODE_LAHEI) {
                    int isBlack = data.getIntExtra("isBlack", 0);
                    if (isBlack == 1) {
                        iv_online_user_pullblack.setBackgroundResource(SobotResourceUtils.getDrawableId(getSobotContext(), "sobot_online_lahei_sel"));
                        if (userInfo != null) {
                            userInfo.setIsblack(isBlack);
                        }
                    }
                }

                if (requestCode == SobotBaseConstant.REQUEST_CODE_PICTURE) { //发送本地图片
                    if (data != null && data.getData() != null) {
                        Uri selectedImage = data.getData();
                        String path = SobotImageUtils.getPath(getSobotActivity(), selectedImage);
                        if (SobotMediaFileUtils.isVideoFileType(path)) {
                            File videoFile = new File(path);
                            if (videoFile.exists()) {
                                sendMsg("", OnlineConstant.SOBOT_MSG_TYPE_VIDEO, path, isFromHistory(), null);
                            }
                        } else {
                            sendMsg("", OnlineConstant.SOBOT_MSG_TYPE_IMG, path, isFromHistory(), null);
                        }
                    } else {
                        SobotToastUtil.showCustomToast(getSobotContext(), getResString("online_did_not_get_picture_path"));
                    }

                }
                if (requestCode == SobotBaseConstant.REQUEST_CODE_VIDEO) { // 发送本地视频
                    if (data != null && data.getData() != null) {
                        Uri selectedImage = data.getData();
                        String path = SobotImageUtils.getPath(getSobotActivity(), selectedImage);
                        if (SobotMediaFileUtils.isVideoFileType(path)) {
                            File videoFile = new File(path);
                            if (videoFile.exists()) {
                                sendMsg("", OnlineConstant.SOBOT_MSG_TYPE_VIDEO, path, isFromHistory(), null);
                            }
                        } else {
                            sendMsg("", OnlineConstant.SOBOT_MSG_TYPE_IMG, path, isFromHistory(), null);
                        }
                    } else {
                        SobotToastUtil.showCustomToast(getSobotContext(), getResString("online_did_not_get_picture_path"));
                    }

                }
                hidePanelAndKeyboard(mPanelRoot);
            }

            if (requestCode == OnlineConstant.SOBOT_REQUEST_CODE_CAMERA) {
                int actionType = SobotCameraActivity.getActionType(data);
                if (actionType == SobotCameraActivity.ACTION_TYPE_VIDEO) {
                    File videoFile = new File(SobotCameraActivity.getSelectedVideo(data));
                    if (videoFile.exists()) {
                        sendMsg("", OnlineConstant.SOBOT_MSG_TYPE_VIDEO, videoFile.getAbsolutePath(), isFromHistory(), null);
                    } else {
                        SobotToastUtil.showCustomToast(getSobotContext(), SobotResourceUtils.getResString(getSobotContext(), "online_pic_select_again"));
                    }
                } else {
                    File tmpPic = new File(SobotCameraActivity.getSelectedImage(data));
                    if (tmpPic.exists()) {
                        sendMsg("", OnlineConstant.SOBOT_MSG_TYPE_IMG, tmpPic.getAbsolutePath(), isFromHistory(), null);
                    } else {
                        SobotToastUtil.showCustomToast(getSobotContext(), SobotResourceUtils.getResString(getSobotContext(), "online_pic_select_again"));
                    }
                }
            }
            //发送快捷回复消息
            if (requestCode == OnlineConstant.SOBOT_REQUEST_CODE_QUICK_REPLY && data != null) {
                String sendContent = data.getStringExtra("sendContent");
                boolean isAutoSend = data.getBooleanExtra("isAutoSend", false);
                if (isAutoSend) {
                    if (TextUtils.isEmpty(sendContent)) {
                        SobotToastUtil.showCustomToast(getSobotContext(), SobotResourceUtils.getResString(getSobotContext(), "online_no_empty"));
                        return;
                    }
                    sendMsg(sendContent, OnlineConstant.SOBOT_MSG_TYPE_TEXT, "", isFromHistory(), null);
                } else {
                    et_sendmessage.setText(TextUtils.isEmpty(sendContent) ? "" : sendContent);
                }
            }
            //发送智能回复消息
            if (requestCode == OnlineConstant.SOBOT_REQUEST_CODE_INTELLIGENCE_REPLY && data != null) {
                List<ChatMessageRichTextModel.ChatMessageRichListModel> richListModels = (List<ChatMessageRichTextModel.ChatMessageRichListModel>) data.getSerializableExtra("richList");
                String sendContent = data.getStringExtra("sendContent");
                int sendType = data.getIntExtra("sendType", -1);
                boolean isAutoSend = data.getBooleanExtra("isAutoSend", false);
                hidePanelAndKeyboard(mPanelRoot);
                if (isAutoSend) {
                    if (TextUtils.isEmpty(sendContent)) {
                        SobotToastUtil.showCustomToast(getSobotContext(), SobotResourceUtils.getResString(getSobotContext(), "online_no_empty"));
                        return;
                    }
                    sendMsg(sendContent, sendType, "", isFromHistory(), richListModels);
                } else {
                    et_sendmessage.setText(TextUtils.isEmpty(sendContent) ? "" : sendContent);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clickAudioItem(final ChatMessageModel message, ImageView ivAudioPlay) {
        if (mAudioPlayPresenter == null) {
            mAudioPlayPresenter = new AudioPlayPresenter(getSobotContext());
        }
        if (mAudioPlayCallBack == null) {
            mAudioPlayCallBack = new AudioPlayCallBack() {
                @Override
                public void onPlayStart(ChatMessageModel messageModel, ImageView ivAudioPlay) {
                    startAnim(message, ivAudioPlay);
                }

                @Override
                public void onPlayEnd(ChatMessageModel messageModel, ImageView ivAudioPlay) {
                    stopAnim(message, ivAudioPlay);
                }
            };
        }
        mAudioPlayPresenter.clickAudio(message, mAudioPlayCallBack, ivAudioPlay);
    }

    @Override
    public void reSendMsg(ChatMessageModel message, ImageView sendStatusIV) {
        if (message != null) {
            if (OnlineConstant.SOBOT_MSG_TYPE_TEXT == message.getMsgType()) {
                reSendMsg(message);
            }
        }
    }

    // 开始播放
    public void startAnim(ChatMessageModel message, ImageView voicePlay) {
        message.setVoideIsPlaying(true);

        Drawable playDrawable = voicePlay.getDrawable();
        if (playDrawable instanceof AnimationDrawable) {
            ((AnimationDrawable) playDrawable).start();
        } else {
            voicePlay.setImageResource(
                    SobotResourceUtils.getIdByName(getSobotContext(), "drawable", "sobot_voice_from_icon"));
            Drawable rePlayDrawable = voicePlay.getDrawable();
            if (rePlayDrawable != null
                    && rePlayDrawable instanceof AnimationDrawable) {
                ((AnimationDrawable) rePlayDrawable).start();
            }
        }
    }

    // 关闭播放
    public void stopAnim(ChatMessageModel message, ImageView voicePlay) {
        message.setVoideIsPlaying(false);

        Drawable playDrawable = voicePlay.getDrawable();
        if (playDrawable != null
                && playDrawable instanceof AnimationDrawable) {
            ((AnimationDrawable) playDrawable).stop();
            ((AnimationDrawable) playDrawable).selectDrawable(2);
        }
    }
}
