package com.sobot.online.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sobot.online.api.OnlineBaseCode;
import com.sobot.online.api.SobotOnlineUrlApi;
import com.sobot.online.base.SobotOnlineDialogBaseActivity;
import com.sobot.online.dialog.SobotDialogUtils;
import com.sobot.online.model.HistoryUserInfoModel;
import com.sobot.online.weight.toast.SobotToastUtil;
import com.sobot.onlinecommon.frame.http.callback.SobotResultCallBack;
import com.sobot.onlinecommon.socket.SobotSocketConstant;
import com.sobot.onlinecommon.utils.SobotKeyboardUtils;
import com.sobot.onlinecommon.utils.SobotResourceUtils;
import com.sobot.onlinecommon.utils.SobotSizeUtils;
import com.sobot.onlinecommon.utils.SobotUtils;

//拉黑界面
public class SobotOnlinePullBlackActivity extends SobotOnlineDialogBaseActivity implements View.OnClickListener {

    private TextView tv_online_pop_long_pull_black;//永久拉黑
    private TextView tv_online_pop_24_pull_black;//24小时拉黑
    private EditText et_online_pop_pull_black_reason;//拉黑原因
    private TextView tv_online_pop_pull_black_submit;//确定
    private TextView tv_online_pop_pull_black_cancle;//取消
    private TextView tv_online_pop_header_title;//头部标题
    private TextView tv_online_pop_header_cancle;//头部取消按钮
    private TextView tv_online_pop_24_pull_black_des;//24小时拉黑描述

    private HistoryUserInfoModel userInfo;//要拉黑的用户id
    private int laheiType;// 0:永久拉黑， 1:24小时拉黑 默认永久拉黑

    @Override
    protected int getContentViewResId() {
        return SobotResourceUtils.getResLayoutId(this, "sobot_online_pop_pull_black");
    }


    @Override
    public void initView() {
        tv_online_pop_header_title = findViewById(SobotResourceUtils.getResId(getSobotContext(), "tv_online_pop_header_title"));
        tv_online_pop_header_title.setText(SobotResourceUtils.getResString(getSobotContext(), "online_lahei_user"));
        tv_online_pop_pull_black_cancle = findViewById(SobotResourceUtils.getResId(getSobotContext(), "tv_online_pop_pull_black_cancle"));
        tv_online_pop_pull_black_cancle.setOnClickListener(this);
        tv_online_pop_header_cancle = findViewById(SobotResourceUtils.getResId(getSobotContext(), "tv_online_pop_header_cancle"));
        tv_online_pop_header_cancle.setOnClickListener(this);
        tv_online_pop_long_pull_black = findViewById(SobotResourceUtils.getResId(getSobotContext(), "tv_online_pop_long_pull_black"));
        tv_online_pop_long_pull_black.setOnClickListener(this);
        tv_online_pop_24_pull_black = findViewById(SobotResourceUtils.getResId(getSobotContext(), "tv_online_pop_24_pull_black"));
        tv_online_pop_24_pull_black.setOnClickListener(this);
        tv_online_pop_24_pull_black_des = findViewById(SobotResourceUtils.getResId(getSobotContext(), "tv_online_pop_24_pull_black_des"));
        tv_online_pop_pull_black_submit = findViewById(SobotResourceUtils.getResId(getSobotContext(), "tv_online_pop_pull_black_submit"));
        tv_online_pop_pull_black_submit.setOnClickListener(this);
        et_online_pop_pull_black_reason = findViewById(SobotResourceUtils.getResId(getSobotContext(), "et_online_pop_pull_black_reason"));

        displayInNotchByMargin(et_online_pop_pull_black_reason);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void initData() {
        userInfo = (HistoryUserInfoModel) getIntent().getSerializableExtra("userInfo");
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == SobotResourceUtils.getResId(getSobotContext(), "tv_online_pop_pull_black_cancle")) {
            hideSoftInput();
            finish();
        }
        if (v.getId() == SobotResourceUtils.getResId(getSobotContext(), "tv_online_pop_header_cancle")) {
            hideSoftInput();
            finish();
        }
        if (v.getId() == SobotResourceUtils.getResId(getSobotContext(), "tv_online_pop_long_pull_black")) {
            setDrawalbes(SobotResourceUtils.getDrawableId(getSobotContext(), "sobot_pull_black_button_selected"), tv_online_pop_long_pull_black);
            tv_online_pop_long_pull_black.setTextColor(getResColor("sobot_online_common_gray1"));
            setDrawalbes(SobotResourceUtils.getDrawableId(getSobotContext(), "sobot_pull_black_button_no_selected"), tv_online_pop_24_pull_black);
            tv_online_pop_24_pull_black.setTextColor(getResColor("sobot_online_common_gray3"));
            tv_online_pop_24_pull_black_des.setVisibility(View.GONE);
            et_online_pop_pull_black_reason.setVisibility(View.VISIBLE);
            laheiType = 0;
        }
        if (v.getId() == SobotResourceUtils.getResId(getSobotContext(), "tv_online_pop_24_pull_black")) {
            setDrawalbes(SobotResourceUtils.getDrawableId(getSobotContext(), "sobot_pull_black_button_selected"), tv_online_pop_24_pull_black);
            tv_online_pop_long_pull_black.setTextColor(getResColor("sobot_online_common_gray3"));
            setDrawalbes(SobotResourceUtils.getDrawableId(getSobotContext(), "sobot_pull_black_button_no_selected"), tv_online_pop_long_pull_black);
            tv_online_pop_24_pull_black.setTextColor(getResColor("sobot_online_common_gray1"));
            tv_online_pop_24_pull_black_des.setVisibility(View.VISIBLE);
            et_online_pop_pull_black_reason.setVisibility(View.GONE);
            laheiType = 1;
            SobotKeyboardUtils.hideSoftInput(tv_online_pop_long_pull_black);
        }
        if (v.getId() == SobotResourceUtils.getResId(getSobotContext(), "tv_online_pop_pull_black_submit") && zhiChiApi != null) {
            blackUsers(et_online_pop_pull_black_reason.getText().toString(), laheiType);
        }
    }

    //动态设置drawableLeft
    private void setDrawalbes(int resId, TextView view) {
        Drawable drawable = getResources().getDrawable(resId);
        drawable.setBounds(0, 0, SobotSizeUtils.dp2px(15), SobotSizeUtils.dp2px(15));
        view.setCompoundDrawables(drawable, null, null, null);
    }

    /**
     * 黑名单的功能
     *
     * @param reason
     * @param type   0:永久拉黑， 1:24小时拉黑
     */
    private void blackUsers(String reason, int type) {
        if (TextUtils.isEmpty(reason) && type == 0) {
            SobotToastUtil.showCustomToast(getSobotContext(), SobotResourceUtils.getResString(getSobotContext(), "online_lahei_reason_no_empty"));
            return;
        }
        if (userInfo == null || TextUtils.isEmpty(userInfo.getId())) {
            return;
        }
        SobotDialogUtils.startProgressDialog(getSobotContext());
        zhiChiApi.addOrDeleteBlackList(getSobotActivity(), userInfo.getId(), reason, type, SobotOnlineUrlApi.api_addBlackList, new SobotResultCallBack<OnlineBaseCode>() {
            @Override
            public void onSuccess(OnlineBaseCode OnlineBaseCode) {
                SobotDialogUtils.stopProgressDialog(getSobotContext());
                if (OnlineBaseCode != null) {
                    SobotToastUtil.showCustomToastWithListenr(getSobotContext(), SobotResourceUtils.getResString(getSobotContext(), "online_add_user_black_success"), SobotResourceUtils.getDrawableId(getSobotContext(), "sobot_success_icon"), new SobotToastUtil.OnAfterShowListener() {
                        @Override
                        public void doAfter() {
                            Intent intent = new Intent();
                            intent.setAction(SobotSocketConstant.BROADCAST_SOBOT_ADD_BLACK);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("userInfo", userInfo);
                            intent.putExtras(bundle);
                            SobotUtils.getApp().sendBroadcast(intent);
                            Intent okIntent = new Intent();
                            okIntent.putExtra("isBlack", 1);
                            setResult(RESULT_OK, okIntent);
                            finish();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Exception e, String des) {
                SobotToastUtil.showCustomToast(getSobotContext(), des);
                SobotDialogUtils.stopProgressDialog(getSobotContext());
            }
        });
    }

}