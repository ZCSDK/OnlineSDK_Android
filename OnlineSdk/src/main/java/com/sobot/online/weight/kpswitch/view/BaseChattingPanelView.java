package com.sobot.online.weight.kpswitch.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.sobot.onlinecommon.utils.SobotResourceUtils;


/**
 * 所有聊天面板view的基类
 */
public abstract class BaseChattingPanelView {
    private View rootView = null;
    protected Context context = null;

    public BaseChattingPanelView(Context context) {
        this.context = context;
        rootView = initView();
        rootView.setTag(getRootViewTag());
    }

    /**
     * 初始化view
     */
    public abstract View initView();

    /**
     * 初始化数据
     */
    public abstract void initData();

    public abstract void setListener(SobotBasePanelListener listener);

    /**
     * 获取view
     *
     * @return
     */
    public View getRootView() {
        return rootView;
    }

    protected int getResId(String name) {
        return SobotResourceUtils.getIdByName(context, "id", name);
    }

    protected int getResLayoutId(String name) {
        return SobotResourceUtils.getIdByName(context, "layout", name);
    }

    protected int getResDrawableId(String name) {
        return SobotResourceUtils.getIdByName(context, "drawable", name);
    }

    protected int getResIntegerId(String name) {
        return SobotResourceUtils.getIdByName(context, "integer", name);
    }

    protected int getResInteger(String name) {
        return context.getResources().getInteger(getResIntegerId(name));
    }

    protected int getResStringId(String name) {
        return SobotResourceUtils.getIdByName(context, "string", name);
    }

    protected String getResString(String name) {
        return SobotResourceUtils.getResString(context, name);
//        return context.getResources().getString(getResStringId(name));
    }

    /**
     * 获取根view的tag
     *
     * @return
     */
    public abstract String getRootViewTag();

    /**
     * view的显示回调
     */
    public void onViewStart(Bundle bundle) {
    }

    public interface SobotBasePanelListener {
    }
}