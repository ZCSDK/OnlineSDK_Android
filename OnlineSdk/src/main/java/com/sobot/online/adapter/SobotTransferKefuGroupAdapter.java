package com.sobot.online.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.sobot.online.model.OnLineGroupModel;
import com.sobot.online.weight.recyclerview.adapter.HelperRecyclerViewHolder;
import com.sobot.online.weight.recyclerview.adapter.HelperStateRecyclerViewAdapter;
import com.sobot.onlinecommon.utils.SobotResourceUtils;

//转接客服组列表 adapter
public class SobotTransferKefuGroupAdapter extends HelperStateRecyclerViewAdapter<OnLineGroupModel> {
    private Context mContext;
    private OnTransferKefuGroupListener mTransferKefuGroupListener;

    public SobotTransferKefuGroupAdapter(Context context, OnTransferKefuGroupListener transferKefuGroupListener) {
        super(context, SobotResourceUtils.getResLayoutId(context, "adapter_item_transfer_group"));
        mContext = context;
        mTransferKefuGroupListener = transferKefuGroupListener;
    }

    @Override
    protected void HelperBindData(HelperRecyclerViewHolder viewHolder, final int position, final OnLineGroupModel item) {
        viewHolder.setText(SobotResourceUtils.getResId(mContext, "tv_transfer_name"), item.getGroupName());
        viewHolder.setText(SobotResourceUtils.getResId(mContext, "tv_transfer_kefu_info"), SobotResourceUtils.getResString(mContext, "online_zaixian_count") + ":" + item.getOnlineNum() + "  " + SobotResourceUtils.getResString(mContext, "online_unsaturated_count") + ":" + item.getFreeNum());
        viewHolder.setOnClickListener(SobotResourceUtils.getResId(mContext, "tv_transfer_invite"), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTransferKefuGroupListener != null) {
                    mTransferKefuGroupListener.onTransferKefuGroup(item, position);
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

    //转接客服组点击监听
    public interface OnTransferKefuGroupListener {
        void onTransferKefuGroup(OnLineGroupModel groupModel, int position);
    }
}
