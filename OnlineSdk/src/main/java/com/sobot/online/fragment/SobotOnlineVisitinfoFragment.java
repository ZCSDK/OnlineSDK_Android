package com.sobot.online.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sobot.online.activity.OnlineWebViewActivity;
import com.sobot.online.base.SobotOnlineBaseFragment;
import com.sobot.online.model.HistoryUserInfoModel;
import com.sobot.online.model.UserConversationModel;
import com.sobot.online.util.HtmlTools;
import com.sobot.onlinecommon.frame.http.callback.SobotResultCallBack;
import com.sobot.onlinecommon.socket.SobotSocketConstant;
import com.sobot.onlinecommon.utils.SobotResourceUtils;

/**
 * @Description: 访问信息页面
 * @Author: znw
 * @CreateDate: 2020/09/7 19:58
 * @Version: 1.0
 */
public class SobotOnlineVisitinfoFragment extends SobotOnlineBaseFragment {
    private View mRootView;
    private TextView tv_visitor_search_engines;
    private TextView tv_visitor_loading_page;
    private TextView tv_visitor_launch_page;
    private TextView tv_visitor_access_channel;
    private TextView tv_visitor_user_skill_name;
    private TextView tv_visitor_user_line_up_time;
    private TextView tv_visitor_user_ip_address;
    private TextView tv_visitor_user_terminal;
    private TextView tv_visitor_user_system;
    private TextView tv_visitor_count;
    private TextView tv_visitor_search_keywords;
    private HistoryUserInfoModel userInfoModel;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(getResLayoutId("sobot_fragment_visit_info"), container, false);
        initView();
        initData();
        return mRootView;
    }

    private void initData() {
        userInfoModel = (HistoryUserInfoModel) getSobotActivity().getIntent().getSerializableExtra("userinfo");
        zhiChiApi.queryConMsg(getSobotActivity(), userInfoModel.getId(), userInfoModel.getLastCid(), new SobotResultCallBack<UserConversationModel>() {
            @Override
            public void onSuccess(final UserConversationModel resultModel) {
                if (!isAdded()) {
                    return;
                }
                if (resultModel != null) {
                    if (!TextUtils.isEmpty(resultModel.getVisitLandPage())) {
                        tv_visitor_loading_page.setText(resultModel.getVisitLandPage());
                        tv_visitor_loading_page.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(), OnlineWebViewActivity.class);
                                intent.putExtra("url", resultModel.getVisitLandPage());
                                startActivity(intent);
                            }
                        });
                    } else {
                        tv_visitor_loading_page.setText("--");
                    }

                    if (!TextUtils.isEmpty(resultModel.getConLaunchPage())) {
                        tv_visitor_launch_page.setText(resultModel.getVisitLandPage());
                        tv_visitor_launch_page.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(), OnlineWebViewActivity.class);
                                intent.putExtra("url", resultModel.getConLaunchPage());
                                startActivity(intent);
                            }
                        });
                    } else {
                        tv_visitor_launch_page.setText("--");
                    }

                    if (resultModel.getSource() == 0) {
                        tv_visitor_access_channel.setText(SobotResourceUtils.getResString(getSobotActivity(), "online_desktop_website"));
                    } else if (resultModel.getSource() == 1) {
                        tv_visitor_access_channel.setText(SobotResourceUtils.getResString(getSobotActivity(), "online_wechat"));
                    } else if (resultModel.getSource() == 2) {
                        tv_visitor_access_channel.setText("APP");
                    } else if (resultModel.getSource() == 3) {
                        tv_visitor_access_channel.setText(SobotResourceUtils.getResString(getSobotActivity(), "online_micro_blog"));
                    } else if (resultModel.getSource() == 4) {
                        tv_visitor_access_channel.setText(SobotResourceUtils.getResString(getSobotActivity(), "online_mobile_website"));
                    } else {
                        tv_visitor_access_channel.setText(SobotResourceUtils.getResString(getSobotActivity(), "online_other"));
                    }

                    tv_visitor_user_skill_name.setText(formatStr(resultModel.getGroupName()));
                    tv_visitor_user_line_up_time.setText(formatStr(resultModel.getRemainTime()));
                    tv_visitor_user_ip_address.setText(formatStr(resultModel.getIp()));
                    tv_visitor_user_terminal.setText(formatStr(resultModel.getTerminal()));
                    tv_visitor_user_system.setText(formatStr(resultModel.getOs()));

                    if (resultModel.getVisitMsg() != null) {
                        setVisitSourceType(getSobotActivity(), resultModel.getVisitSourceType(),
                                resultModel.getSearchSource(), tv_visitor_search_engines);
                    } else {
                        tv_visitor_search_engines.setText("--");
                    }
                }
            }

            @Override
            public void onFailure(Exception e, String des) {

            }
        });
    }


    protected void initView() {
        tv_visitor_search_engines = mRootView.findViewById(SobotResourceUtils.getResId(getSobotActivity(), "tv_visitor_search_engines"));
        tv_visitor_loading_page = mRootView.findViewById(SobotResourceUtils.getResId(getSobotActivity(), "tv_visitor_loading_page"));
        tv_visitor_launch_page = mRootView.findViewById(SobotResourceUtils.getResId(getSobotActivity(), "tv_visitor_launch_page"));
        tv_visitor_access_channel = mRootView.findViewById(SobotResourceUtils.getResId(getSobotActivity(), "tv_visitor_access_channel"));
        tv_visitor_user_skill_name = mRootView.findViewById(SobotResourceUtils.getResId(getSobotActivity(), "tv_visitor_user_skill_name"));
        tv_visitor_user_line_up_time = mRootView.findViewById(SobotResourceUtils.getResId(getSobotActivity(), "tv_visitor_user_line_up_time"));
        tv_visitor_user_ip_address = mRootView.findViewById(SobotResourceUtils.getResId(getSobotActivity(), "tv_visitor_user_ip_address"));
        tv_visitor_user_terminal = mRootView.findViewById(SobotResourceUtils.getResId(getSobotActivity(), "tv_visitor_user_terminal"));
        tv_visitor_user_system = mRootView.findViewById(SobotResourceUtils.getResId(getSobotActivity(), "tv_visitor_user_system"));
        tv_visitor_count = mRootView.findViewById(SobotResourceUtils.getResId(getSobotActivity(), "tv_visitor_count"));
        tv_visitor_search_keywords = mRootView.findViewById(SobotResourceUtils.getResId(getSobotActivity(), "tv_visitor_search_keywords"));

    }

    /**
     * visitSourceType 如果存在 1 搜索引擎 2 站外网站 3直接访问
     * 如果不存在  searchSource =0 浏览网页  其他 由一下页面发起咨询
     *
     * @param tvVisitFromContent
     */
    public static String setVisitSourceType(Context context, String visitSourceType, String searchSource,
                                            TextView tvVisitFromContent) {
        String tmpStr = "";
        String type = "";
        if (!TextUtils.isEmpty(visitSourceType)) {
            switch (visitSourceType) {
                case SobotSocketConstant.TYPE_VISIT_SSOURCE_SEARCH_ENGINES:
                    //搜索引擎
                    tmpStr = SobotResourceUtils.getResString(context, "online_search_engines");
                    type = SobotSocketConstant.TYPE_VISIT_SSOURCE_SEARCH_ENGINES;
                    break;
                case SobotSocketConstant.TYPE_VISIT_SSOURCE_OUTSIDE_ACCESS:
                    //站外网站
                    tmpStr = SobotResourceUtils.getResString(context, "online_from_other_web");
                    type = SobotSocketConstant.TYPE_VISIT_SSOURCE_OUTSIDE_ACCESS;
                    break;
                case SobotSocketConstant.TYPE_VISIT_SSOURCE_DIRECT_ACCESS:
                    //直接访问
                    tmpStr = SobotResourceUtils.getResString(context, "online_direct_access");
                    type = SobotSocketConstant.TYPE_VISIT_SSOURCE_DIRECT_ACCESS;
                    break;
            }
        } else {
            if (!TextUtils.isEmpty(searchSource)) {
                switch (searchSource) {
                    case SobotSocketConstant.TYPE_VISIT_SSOURCE_BROWSE_PAGE:
                        //浏览网页
                        tmpStr = SobotResourceUtils.getResString(context, "online_browse_web");
                        type = SobotSocketConstant.TYPE_VISIT_SSOURCE_BROWSE_PAGE;
                        break;
                    default:
                        tmpStr = SobotResourceUtils.getResString(context, "online_from_following_page");
                        type = SobotSocketConstant.TYPE_VISIT_SSOURCE_OTHER;
                        break;
                }
            }
        }
        HtmlTools.getInstance(context).setRichText(tvVisitFromContent, tmpStr, SobotResourceUtils.getResColorId(context, "sobot_online_color"));
        return type;
    }

    private String formatStr(String str) {
        return TextUtils.isEmpty(str) ? "--" : str;
    }
}
