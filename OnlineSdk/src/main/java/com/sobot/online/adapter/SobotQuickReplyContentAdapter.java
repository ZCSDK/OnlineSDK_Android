package com.sobot.online.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.sobot.online.model.ChatReplyInfoModel;
import com.sobot.online.weight.recyclerview.adapter.HelperRecyclerViewHolder;
import com.sobot.online.weight.recyclerview.adapter.HelperStateRecyclerViewAdapter;
import com.sobot.onlinecommon.utils.SobotResourceUtils;

//快捷回复 - 回复内容 adapter
public class SobotQuickReplyContentAdapter extends HelperStateRecyclerViewAdapter<ChatReplyInfoModel> {
    private Context mContext;
    private OnSendReplyContentListener mSendReplyContentListener;

    public SobotQuickReplyContentAdapter(Context context, OnSendReplyContentListener sendReplyContentListener) {
        super(context, SobotResourceUtils.getResLayoutId(context, "adapter_quick_reply_content_layout"));
        mContext = context;
        mSendReplyContentListener = sendReplyContentListener;
    }

    @Override
    protected void HelperBindData(HelperRecyclerViewHolder viewHolder, int position, final ChatReplyInfoModel item) {
        viewHolder.setText(SobotResourceUtils.getResId(mContext, "tv_online_content"), item.getValue());
        viewHolder.setOnClickListener(SobotResourceUtils.getResId(mContext, "tv_online_reply_edit_send"), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSendReplyContentListener != null) {
                    mSendReplyContentListener.onSendReplyContent(false, item.getValue());
                }
            }
        });
        viewHolder.setOnClickListener(SobotResourceUtils.getResId(mContext, "tv_online_reply_send"), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSendReplyContentListener != null) {
                    mSendReplyContentListener.onSendReplyContent(true, item.getValue());
                }
            }
        });
    }


    @Override
    public View getEmptyView(ViewGroup parent) {
        return mLInflater.inflate(SobotResourceUtils.getResLayoutId(mContext, "sobot_layout_empty"), parent, false);
    }

    @Override
    public View getErrorView(ViewGroup parent) {
        return mLInflater.inflate(SobotResourceUtils.getResLayoutId(mContext, "sobot_layout_empty"), parent, false);
    }

    @Override
    public View getLoadingView(ViewGroup parent) {
        return mLInflater.inflate(SobotResourceUtils.getResLayoutId(mContext, "sobot_layout_empty"), parent, false);
    }

    //快捷回复内容直接发送和编辑后发送按钮监听
    public interface OnSendReplyContentListener {
        void onSendReplyContent(boolean isAutoSend, String sendContent);
    }

}
