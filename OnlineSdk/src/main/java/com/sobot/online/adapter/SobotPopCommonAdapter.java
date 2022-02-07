package com.sobot.online.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sobot.online.model.OnlineCustomPopModel;
import com.sobot.online.weight.recyclerview.adapter.HelperRecyclerViewHolder;
import com.sobot.online.weight.recyclerview.adapter.HelperStateRecyclerViewAdapter;
import com.sobot.onlinecommon.utils.SobotResourceUtils;

//带搜索选择弹窗  通用 adapter
public class SobotPopCommonAdapter extends HelperStateRecyclerViewAdapter<OnlineCustomPopModel> {
    private Context mContext;
    private int selectPosition = -1;//选中的索引，默认-1 没有选中的

    public SobotPopCommonAdapter(Context context) {
        super(context, SobotResourceUtils.getResLayoutId(context, "adapter_common_string_item"));
        mContext = context;
    }

    @Override
    protected void HelperBindData(HelperRecyclerViewHolder viewHolder, int position, OnlineCustomPopModel item) {
        viewHolder.setText(SobotResourceUtils.getResId(mContext, "tv_common_pop_item_text"), item.getsValue());
        ImageView iv_common_pop_item_select = viewHolder.getView(SobotResourceUtils.getResId(mContext, "iv_common_pop_item_select"));
        TextView textView= viewHolder.getView(SobotResourceUtils.getResId(mContext, "tv_common_pop_item_text"));
        if (selectPosition == position) {
            textView.setTextColor(ContextCompat.getColor(mContext,SobotResourceUtils.getResColorId(mContext,"sobot_online_common_gray1")));
            iv_common_pop_item_select.setVisibility(View.VISIBLE);
        } else {
            textView.setTextColor(ContextCompat.getColor(mContext,SobotResourceUtils.getResColorId(mContext,"sobot_online_common_gray2")));
            iv_common_pop_item_select.setVisibility(View.GONE);
        }
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

    public int getSelectPosition() {
        return selectPosition;
    }

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
    }
}
