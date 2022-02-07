package com.sobot.online.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sobot.online.model.QueueUserModel;
import com.sobot.online.weight.recyclerview.adapter.HelperRecyclerViewHolder;
import com.sobot.online.weight.recyclerview.adapter.HelperStateRecyclerViewAdapter;
import com.sobot.onlinecommon.utils.SobotResourceUtils;
import com.sobot.pictureframe.SobotBitmapUtil;

//排队用户列表 adapter
public class SobotPaiduiAdapter extends HelperStateRecyclerViewAdapter<QueueUserModel.QueueUser> {
    private Context mContext;
    private OnInviteListener mOnInviteListener;

    public SobotPaiduiAdapter(Context context, OnInviteListener onInviteListener) {
        super(context, SobotResourceUtils.getResLayoutId(context, "adapter_item_paidui"));
        mContext = context;
        mOnInviteListener = onInviteListener;
    }

    @Override
    protected void HelperBindData(HelperRecyclerViewHolder viewHolder, int position, final QueueUserModel.QueueUser item) {
        TextView nikeTV = viewHolder.getView(SobotResourceUtils.getResId(mContext, "tv_paidui_user_nike"));
        nikeTV.setText(item.getUname());
        TextView onlyNikeTV = viewHolder.getView(SobotResourceUtils.getResId(mContext, "tv_paidui_user_only_nike"));
        onlyNikeTV.setText(item.getUname());
        TextView groupTV = viewHolder.getView(SobotResourceUtils.getResId(mContext, "tv_paidui_user_group"));
        if (TextUtils.isEmpty(item.getGroupName())) {
            groupTV.setVisibility(View.GONE);
            nikeTV.setVisibility(View.GONE);
            onlyNikeTV.setVisibility(View.VISIBLE);
        } else {
            groupTV.setVisibility(View.VISIBLE);
            nikeTV.setVisibility(View.VISIBLE);
            onlyNikeTV.setVisibility(View.GONE);
            groupTV.setText(item.getGroupName());
        }
        if (!TextUtils.isEmpty(item.getRemainTime())) {
            viewHolder.setText(SobotResourceUtils.getResId(mContext, "tv_paidui_remain_time"), item.getRemainTime());
        }
        //头像
        ImageView head_avator = viewHolder.getView(SobotResourceUtils.getResId(mContext, "srcv_paidui_user_avatar"));
        if (head_avator != null) {
            setUserHead(item, mContext, head_avator);
        }
        viewHolder.setOnClickListener(SobotResourceUtils.getResId(mContext, "tv_paidui_invite"), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnInviteListener != null) {
                    mOnInviteListener.onInvite(item);
                }
            }
        });
    }


    @Override
    public View getEmptyView(ViewGroup parent) {
        View view = mLInflater.inflate(SobotResourceUtils.getResLayoutId(mContext, "sobot_layout_empty"), parent, false);
        TextView tv_empty_view = view.findViewById(SobotResourceUtils.getResId(mContext, "tv_empty_view"));
        tv_empty_view.setText(SobotResourceUtils.getResString(mContext,"sobot_online_no_paidui"));
        return view;
    }

    @Override
    public View getErrorView(ViewGroup parent) {
        return mLInflater.inflate(SobotResourceUtils.getResLayoutId(mContext, "sobot_layout_empty"), parent, false);
    }

    @Override
    public View getLoadingView(ViewGroup parent) {
        return mLInflater.inflate(SobotResourceUtils.getResLayoutId(mContext, "sobot_layout_empty"), parent, false);
    }


    private void setUserHead(QueueUserModel.QueueUser model, Context context, ImageView head_avator) {
        int source = model.getSource();
        SobotBitmapUtil.display(mContext, getUserAvatorWithSource(context, source), head_avator);
    }

    /**
     * 聊天列表获取默认头像id
     *
     * @param source
     * @return
     */
    public int getUserAvatorWithSource(Context context, int source) {
        int avatorDrawable;
        switch (source) {
            case 0:
                avatorDrawable = getDrawable(context, "avatar_computer_online");
                break;
            case 1:
            case 9:
            case 10:
                avatorDrawable = getDrawable(context, "avatar_wechat_online");
                break;
            case 2:
                avatorDrawable = getDrawable(context, "avatar_app_online");
                break;
            case 3:
                avatorDrawable = getDrawable(context, "avatar_weibo_online");
                break;
            case 4:
                avatorDrawable = getDrawable(context, "avatar_phone_online");
                break;
            default:
                avatorDrawable = getDrawable(context, "avatar_computer_online");
                break;
        }
        return avatorDrawable;
    }

    public int getDrawable(Context context, String drawableIdStr) {
        return SobotResourceUtils.getDrawableId(context, drawableIdStr);
    }

    //排队用户邀请监听
    public interface OnInviteListener {
        void onInvite(QueueUserModel.QueueUser queueUser);
    }
}
