package com.sobot.online.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.sobot.online.OnlineConstant;
import com.sobot.online.model.OnlineServiceStatus;
import com.sobot.online.weight.recyclerview.adapter.HelperRecyclerViewAdapter;
import com.sobot.online.weight.recyclerview.adapter.HelperRecyclerViewHolder;
import com.sobot.onlinecommon.utils.SobotResourceUtils;

//客服在线状态弹窗 adapter
public class SobotPopUserStatusAdapter extends HelperRecyclerViewAdapter<OnlineServiceStatus> {
    private Context mContext;

    public SobotPopUserStatusAdapter(Context context) {
        super(context, SobotResourceUtils.getResLayoutId(context, "adapter_string_item"));
        mContext = context;
    }

    @Override
    protected void HelperBindData(HelperRecyclerViewHolder viewHolder, int position, OnlineServiceStatus item) {
        viewHolder.setText(SobotResourceUtils.getResId(mContext, "tv_common_pop_item_text"), item.getDictName());
        ImageView ivStatus = viewHolder.getView(SobotResourceUtils.getResId(mContext, "iv_common_pop_item_img"));

        if (OnlineConstant.STATUS_ONLINE.equals(item.getDictValue())) {
            ivStatus.setBackgroundResource(SobotResourceUtils.getDrawableId(mContext, "sobot_status_green"));
        } else if (OnlineConstant.STATUS_BUSY.equals(item.getDictValue())) {
            ivStatus.setBackgroundResource(SobotResourceUtils.getDrawableId(mContext, "sobot_status_red"));
        } else if (OnlineConstant.STATUS_OFFLINE.equals(item.getDictValue())) {
            ivStatus.setBackgroundResource(SobotResourceUtils.getDrawableId(mContext, "sobot_status_gray"));
        } else {
            ivStatus.setBackgroundResource(SobotResourceUtils.getDrawableId(mContext, "sobot_status_custom"));
        }
    }

}
