package com.sobot.online.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sobot.online.model.HistoryChatModel;
import com.sobot.online.weight.recyclerview.adapter.HelperRecyclerViewHolder;
import com.sobot.online.weight.recyclerview.adapter.HelperStateRecyclerViewAdapter;
import com.sobot.online.weight.recyclerview.swipemenu.SwipeMenuLayout;
import com.sobot.onlinecommon.utils.SobotResourceUtils;
import com.sobot.onlinecommon.utils.SobotTimeUtils;
import com.sobot.pictureframe.SobotBitmapUtil;

//历史会话列表 adapter
public class SobotHistoryChatAdapter extends HelperStateRecyclerViewAdapter<HistoryChatModel> {
    private Context mContext;

    public SobotHistoryChatAdapter(Context context) {
        super(context, SobotResourceUtils.getResLayoutId(context, "adapter_reception_layout"));
        mContext = context;
    }

    @Override
    protected void HelperBindData(HelperRecyclerViewHolder viewHolder, int position, HistoryChatModel item) {
        final SwipeMenuLayout superSwipeMenuLayout = (SwipeMenuLayout) viewHolder.itemView;
        superSwipeMenuLayout.setSwipeEnable(false);   //设置是否可以侧滑
        viewHolder.setText(SobotResourceUtils.getResId(mContext, "tv_reception_user_nike"), item.getUname());
        viewHolder.setText(SobotResourceUtils.getResId(mContext, "tv_reception_last_msg_content"), TextUtils.isEmpty(item.getLastMsg()) ? SobotResourceUtils.getResString(mContext, "online_click_look_msg") : item.getLastMsg());
        viewHolder.setText(SobotResourceUtils.getResId(mContext, "tv_reception_last_msg_time"), SobotTimeUtils.getTimeFormatText(item.getConvEndDateTime(), SobotResourceUtils.getResString(mContext, "onnline_time_tianqian")));
        ImageView iv_reception_user_mark = viewHolder.getView(SobotResourceUtils.getResId(mContext, "iv_reception_user_mark"));
        if (item.getMarkStatus() == 1) {
            iv_reception_user_mark.setVisibility(View.VISIBLE);
            viewHolder.setText(SobotResourceUtils.getResId(mContext, "tv_reception_menu_star_target"), SobotResourceUtils.getResString(mContext, "online_mark_cancle"));
        } else {
            viewHolder.setText(SobotResourceUtils.getResId(mContext, "tv_reception_menu_star_target"), SobotResourceUtils.getResString(mContext, "online_mark"));
            iv_reception_user_mark.setVisibility(View.GONE);
        }
        //头像
        ImageView head_avator = viewHolder.getView(SobotResourceUtils.getResId(mContext, "srcv_reception_user_avatar"));
        if (head_avator != null) {
            SobotBitmapUtil.display(mContext, getUserAvatorWithSource(mContext, item.getSource(), true), head_avator);
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

    /**
     * 聊天列表获取默认头像id
     *
     * @param source
     * @param isOnline
     * @return
     */
    public int getUserAvatorWithSource(Context context, int source, boolean isOnline) {
        int avatorDrawable;
        switch (source) {
            case 0:
                avatorDrawable = isOnline ? getDrawable(context, "avatar_computer_online") : getDrawable(context, "avatar_computer_offline");
                break;
            case 1:
            case 9:
            case 10:
                avatorDrawable = isOnline ? getDrawable(context, "avatar_wechat_online") : getDrawable(context, "avatar_wechat_offline");
                break;
            case 2:
                avatorDrawable = isOnline ? getDrawable(context, "avatar_app_online") : getDrawable(context, "avatar_app_offline");
                break;
            case 3:
                avatorDrawable = isOnline ? getDrawable(context, "avatar_weibo_online") : getDrawable(context, "avatar_weibo_offline");
                break;
            case 4:
                avatorDrawable = isOnline ? getDrawable(context, "avatar_phone_online") : getDrawable(context, "avatar_phone_offline");
                break;
            default:
                avatorDrawable = isOnline ? getDrawable(context, "avatar_computer_online") : getDrawable(context, "avatar_computer_offline");
                break;
        }
        return avatorDrawable;
    }

    public int getDrawable(Context context, String drawableIdStr) {
        return SobotResourceUtils.getDrawableId(context, drawableIdStr);
    }
}
