package com.sobot.online.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sobot.online.model.OnlineEnterPriseModel;
import com.sobot.online.weight.recyclerview.adapter.HelperRecyclerViewHolder;
import com.sobot.online.weight.recyclerview.adapter.HelperStateRecyclerViewAdapter;
import com.sobot.onlinecommon.utils.SobotResourceUtils;

//客户信息 公司搜索列表 adapter
public class OnlineEnterPriseAdapter extends HelperStateRecyclerViewAdapter<OnlineEnterPriseModel> {
    private Context mContext;
    private String mSelectEnterpriseId;

    public OnlineEnterPriseAdapter(Context context, String selectEnterpriseId) {
        super(context, SobotResourceUtils.getResLayoutId(context, "adapter_common_string_item"));
        mContext = context;
        mSelectEnterpriseId = selectEnterpriseId;
    }

    @Override
    protected void HelperBindData(HelperRecyclerViewHolder viewHolder, int position, OnlineEnterPriseModel item) {
        viewHolder.setText(SobotResourceUtils.getResId(mContext, "tv_common_pop_item_text"), item.getEnterpriseName());
        ImageView iv_common_pop_item_select = viewHolder.getView(SobotResourceUtils.getResId(mContext, "iv_common_pop_item_select"));
        if (!TextUtils.isEmpty(mSelectEnterpriseId) && mSelectEnterpriseId.equals(item.getEnterpriseId())) {
            iv_common_pop_item_select.setVisibility(View.VISIBLE);
        } else {
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


    public int getDrawable(Context context, String drawableIdStr) {
        return SobotResourceUtils.getDrawableId(context, drawableIdStr);
    }
}
