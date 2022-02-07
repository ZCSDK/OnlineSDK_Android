package com.sobot.online.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.sobot.onlinecommon.utils.SobotResourceUtils;

/**
 * 重新发送消息弹窗
 */
public class OnlineReSendDialog extends Dialog {

	private Context content;
	public Button button;
	public Button button2;
	private TextView sobot_message;
	public OnItemClick mOnItemClick = null;
	public OnlineReSendDialog(Context context) {
		super(context, SobotResourceUtils.getIdByName(context, "style", "sobot_noAnimDialogStyle"));
		this.content = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(SobotResourceUtils.getIdByName(content, "layout", "online_resend_message_dialog"));
		sobot_message= (TextView) findViewById(SobotResourceUtils.getIdByName(content, "id", "sobot_message"));
		sobot_message.setText(SobotResourceUtils.getResString(content,"sobot_resend_msg"));
		button = (Button) findViewById(SobotResourceUtils.getIdByName(content, "id", "sobot_negativeButton"));
		button.setText(SobotResourceUtils.getResString(content,"sobot_online_send"));
		button2 = (Button) findViewById(SobotResourceUtils.getIdByName(content, "id", "sobot_positiveButton"));
		button2.setText(SobotResourceUtils.getResString(content,"online_cancle"));
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mOnItemClick.OnClick(0);
			}
		});
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mOnItemClick.OnClick(1);
			}
		});
	}

	public void setOnClickListener(OnItemClick onItemClick) {
		mOnItemClick = onItemClick;
	}

	public interface OnItemClick{
		void OnClick(int type);
	}
}