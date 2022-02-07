package com.sobot.online.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sobot.online.model.ChatMessageRichTextModel;
import com.sobot.online.model.RebotSmartAnswerModel;
import com.sobot.online.util.HtmlTools;
import com.sobot.online.weight.recyclerview.adapter.HelperRecyclerViewHolder;
import com.sobot.online.weight.recyclerview.group.GroupedStateRecyclerViewAdapter;
import com.sobot.onlinecommon.utils.SobotHtmlUtils;
import com.sobot.onlinecommon.utils.SobotResourceUtils;

import java.util.ArrayList;
import java.util.List;

//智能回复 - 机器人知识库 adapter
public class SobotRebotKnoweledgeAdapter extends GroupedStateRecyclerViewAdapter<RebotSmartAnswerModel> {
    private Context mContext;
    private OnSendReplyContentListener mSendReplyContentListener;
    private LayoutInflater mLInflater;

    public SobotRebotKnoweledgeAdapter(Context context, List<RebotSmartAnswerModel> list, OnSendReplyContentListener sendReplyContentListener) {
        super(context, list);
        this.mContext = context;
        mLInflater = LayoutInflater.from(mContext);
        this.mSendReplyContentListener = sendReplyContentListener;
    }

    @Override
    public View getEmptyView(ViewGroup parent) {
        return LayoutInflater.from(mContext).inflate(SobotResourceUtils.getResLayoutId(mContext, "sobot_layout_empty"), parent, false);
    }

    @Override
    public View getErrorView(ViewGroup parent) {
        return LayoutInflater.from(mContext).inflate(SobotResourceUtils.getResLayoutId(mContext, "sobot_layout_empty"), parent, false);
    }

    @Override
    public View getLoadingView(ViewGroup parent) {
        return LayoutInflater.from(mContext).inflate(SobotResourceUtils.getResLayoutId(mContext, "sobot_layout_empty"), parent, false);
    }

    //返回组的数量
    @Override
    public int getGroupCount() {
        return getGroups().size();
    }

    //返回当前组的子项数量
    @Override
    public int getChildrenCount(int groupPosition) {
        return getGroup(groupPosition).getInfos().size();
    }

    //当前这个组是否有头部
    @Override
    public boolean hasHeader(int groupPosition) {
        return true;
    }

    //当前这个组是否有尾部
    @Override
    public boolean hasFooter(int groupPosition) {
        return false;
    }

    //返回头部的布局id。(如果hasHeader返回false，这个方法不会执行)
    @Override
    public int getHeaderLayout(int viewType) {
        return SobotResourceUtils.getResLayoutId(mContext, "sobot_textview_layout");
    }

    //返回尾部的布局id。(如果hasFooter返回false，这个方法不会执行)
    @Override
    public int getFooterLayout(int viewType) {
        return 0;
    }

    //返回子项的布局id。
    @Override
    public int getChildLayout(int viewType) {
        return SobotResourceUtils.getResLayoutId(mContext, "adapter_knowledge_layout");
    }

    //绑定头部布局数据。(如果hasHeader返回false，这个方法不会执行)
    @Override
    public void onBindHeaderViewHolder(HelperRecyclerViewHolder holder, int groupPosition, RebotSmartAnswerModel item) {
        holder.setText(SobotResourceUtils.getResId(mContext, "online_tv"), item.getRobotName() + "（" + item.getInfos().size() + "）");
    }

    //绑定尾部布局数据。(如果hasFooter返回false，这个方法不会执行)
    @Override
    public void onBindFooterViewHolder(HelperRecyclerViewHolder holder, int groupPosition, RebotSmartAnswerModel item) {

    }

    //绑定子项布局数据。
    @Override
    public void onBindChildViewHolder(HelperRecyclerViewHolder holder, int groupPosition, final int childPosition, final RebotSmartAnswerModel item) {
        final String noHtmlAnswerStr = TextUtils.isEmpty(item.getInfos().get(childPosition).getAnswerTxt()) ? SobotHtmlUtils.removeHtmlTag(item.getInfos().get(childPosition).getAnswer()) : item.getInfos().get(childPosition).getAnswerTxt();
        holder.setText(SobotResourceUtils.getResId(mContext, "tv_online_question"), item.getInfos().get(childPosition).getQuestion());
        TextView contentTv = holder.getView(SobotResourceUtils.getResId(mContext, "tv_online_content"));
        HtmlTools.getInstance(mContext).setRichText(contentTv, noHtmlAnswerStr, SobotResourceUtils.getResColorId(mContext, "sobot_link"));
        holder.setOnClickListener(SobotResourceUtils.getResId(mContext, "tv_online_reply_edit_send"), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSendReplyContentListener != null) {
                    mSendReplyContentListener.onSendReplyContent(false, noHtmlAnswerStr, (ArrayList) item.getInfos().get(childPosition).getRichList());
                }
            }
        });
        holder.setOnClickListener(SobotResourceUtils.getResId(mContext, "tv_online_reply_send"), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSendReplyContentListener != null) {
                    mSendReplyContentListener.onSendReplyContent(true, item.getInfos().get(childPosition).getAnswer(), (ArrayList) item.getInfos().get(childPosition).getRichList());
                }
            }
        });
    }


    //快捷回复内容直接发送和编辑后发送按钮监听
    public interface OnSendReplyContentListener {
        void onSendReplyContent(boolean isAutoSend, String sendContent, ArrayList<ChatMessageRichTextModel.ChatMessageRichListModel> richListModelList);
    }


}