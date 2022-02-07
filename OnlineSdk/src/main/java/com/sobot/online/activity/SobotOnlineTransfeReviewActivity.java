package com.sobot.online.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.sobot.online.base.SobotOnlineDialogBaseActivity;
import com.sobot.onlinecommon.utils.SobotResourceUtils;

//转接审核界面
public class SobotOnlineTransfeReviewActivity extends SobotOnlineDialogBaseActivity implements View.OnClickListener {

    private EditText et_online_pop_reason;//拉黑原因
    private TextView tv_online_pop_submit;//确定
    private TextView tv_online_pop_cancle;//取消
    private TextView tv_online_pop_header_title;//头部标题
    private TextView tv_online_pop_header_cancle;//头部取消按钮

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //窗口对齐屏幕宽度
        Window win = this.getWindow();
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        win.setAttributes(lp);
    }


    @Override
    protected int getContentViewResId() {
        return SobotResourceUtils.getResLayoutId(this, "sobot_online_pop_apply");
    }


    @Override
    public void initView() {
        tv_online_pop_header_title = findViewById(SobotResourceUtils.getResId(getSobotContext(), "tv_online_pop_header_title"));
        tv_online_pop_header_title.setText(SobotResourceUtils.getResString(getSobotContext(), "online_transfer_reason_title"));
        tv_online_pop_cancle = findViewById(SobotResourceUtils.getResId(getSobotContext(), "tv_online_pop_cancle"));
        tv_online_pop_cancle.setOnClickListener(this);
        tv_online_pop_header_cancle = findViewById(SobotResourceUtils.getResId(getSobotContext(), "tv_online_pop_header_cancle"));
        tv_online_pop_header_cancle.setOnClickListener(this);
        tv_online_pop_submit = findViewById(SobotResourceUtils.getResId(getSobotContext(), "tv_online_pop_submit"));
        tv_online_pop_submit.setOnClickListener(this);
        et_online_pop_reason = findViewById(SobotResourceUtils.getResId(getSobotContext(), "et_online_pop_reason"));
        et_online_pop_reason.setHint(SobotResourceUtils.getResString(getSobotContext(), "online_transfer_reason_hint"));
        displayInNotchByMargin(et_online_pop_reason);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void initData() {
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
        if (v.getId() == SobotResourceUtils.getResId(getSobotContext(), "tv_online_pop_pull_black_submit")) {
            Intent okIntent = new Intent();
            okIntent.putExtra("transfer_reason", et_online_pop_reason.getText().toString());
            setResult(RESULT_OK, okIntent);
            finish();
        }
    }
}