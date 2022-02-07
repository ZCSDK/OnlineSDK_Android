package com.sobot.online.base;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sobot.common.ui.SobotBaseConstant;
import com.sobot.common.ui.SobotMarkConfig;
import com.sobot.common.ui.base.SobotBaseActivity;
import com.sobot.common.ui.notchlib.SobotINotchScreen;
import com.sobot.common.ui.notchlib.SobotNotchScreenManager;
import com.sobot.common.ui.permission.SobotPermissionListenerImpl;
import com.sobot.common.utils.SobotCommonApi;
import com.sobot.online.OnlineConstant;
import com.sobot.online.activity.SobotCameraActivity;
import com.sobot.online.api.ZhiChiOnlineApi;
import com.sobot.online.api.ZhiChiOnlineApiFactory;
import com.sobot.online.model.ConversationConfigModelResult;
import com.sobot.online.weight.toast.SobotToastUtil;
import com.sobot.onlinecommon.frame.http.callback.SobotResultCallBack;
import com.sobot.onlinecommon.utils.SobotSPUtils;
import com.sobot.utils.SobotSDCardUtils;
import com.sobot.utils.SobotSystemUtils;

/**
 * @Description: 在线客服基类 BaseActivity
 * @Author: znw
 * @CreateDate: 2020/09/1 11:05
 * @Version: 1.0
 */
public class SobotOnlineBaseActivity extends SobotBaseActivity {

    public ZhiChiOnlineApi zhiChiApi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (zhiChiApi == null) {
            synchronized (SobotOnlineBaseActivity.class) {
                if (zhiChiApi == null) {
                    zhiChiApi = ZhiChiOnlineApiFactory.createZhiChiApi(SobotOnlineBaseActivity.this);
                }
            }
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentViewResId() {
        return 0;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    //返回整个头部
    public RelativeLayout getHearderView() {
        return findViewById(getResId("rl_sobot_online_base_header_root"));
    }

    //返回头部左侧返回按钮控件
    public TextView getHearderLeftView() {
        return findViewById(getResId("tv_sobot_online_base_header_left"));
    }

    //返回头部左侧返回按钮控件
    public TextView getHearderRightView() {
        return findViewById(getResId("tv_sobot_online_base_header_right"));
    }

    //返回头部中间标题
    public TextView getHearderTitleView() {
        return findViewById(getResId("tv_sobot_online_base_header_title"));
    }

    //设置头部中间标题
    public void setHearderTitle(String title) {
        TextView titleTV = getHearderTitleView();
        if (titleTV != null) {
            titleTV.setText(title);
        }
    }

    public SobotBaseActivity getSobotActivity() {
        return this;
    }

    public Context getSobotContext() {
        return this;
    }

    public void displayInNotchByMargin(final View view) {
        if (SobotCommonApi.getSwitchMarkStatus(SobotMarkConfig.LANDSCAPE_SCREEN) && SobotCommonApi.getSwitchMarkStatus(SobotMarkConfig.DISPLAY_INNOTCH) && view != null && getSobotActivity() != null) {
            // 获取刘海屏信息
            SobotNotchScreenManager.getInstance().getNotchInfo(this, new SobotINotchScreen.NotchScreenCallback() {
                @Override
                public void onResult(SobotINotchScreen.NotchScreenInfo notchScreenInfo) {
                    if (notchScreenInfo.hasNotch) {
                        for (Rect rect : notchScreenInfo.notchRects) {
                            if (view.getParent() instanceof LinearLayout) {
                                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
                                layoutParams.leftMargin = rect.right + layoutParams.leftMargin;
                                view.setLayoutParams(layoutParams);
                            } else if (view.getParent() instanceof RelativeLayout) {
                                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                                layoutParams.leftMargin = rect.right + layoutParams.leftMargin;
                                view.setLayoutParams(layoutParams);
                            } else {
                                view.setPadding(rect.right + view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
                            }
                        }
                    }
                }
            });

        }
    }

    /**
     * 填充用户配置信息
     */
    public void fillUserConfig() {
        zhiChiApi.conversationConfig(this, new SobotResultCallBack<ConversationConfigModelResult.ConversationConfigModel>() {
            @Override
            public void onSuccess(ConversationConfigModelResult.ConversationConfigModel conversationConfigModel) {

                SobotSPUtils.getInstance().put(OnlineConstant
                        .SCAN_PATH_FLAG, conversationConfigModel.getScanPathFlag() == 1);
                SobotSPUtils.getInstance().put(OnlineConstant
                        .ISINVITE_FLAG, conversationConfigModel.getIsInvite() == 0);

                //服务总结
                SobotSPUtils.getInstance().put(OnlineConstant
                        .OPEN_SUMMARY_FLAG, conversationConfigModel.getOpenSummaryFlag() == 1);
                SobotSPUtils.getInstance().put(OnlineConstant
                        .SUMMARY_OPERATION_FLAG, conversationConfigModel.getSummaryOperationFlag() == 1);
                SobotSPUtils.getInstance().put(OnlineConstant
                        .SUMMARY_OPERATION_INPUT_FLAG, conversationConfigModel.getSummaryOperationInputFlag() == 1);
                SobotSPUtils.getInstance().put(OnlineConstant
                        .SUMMARY_TYPE_FLAG, conversationConfigModel.getSummaryTypeFlag() == 1);
                SobotSPUtils.getInstance().put(OnlineConstant
                        .SUMMARY_STATUS_FLAG, conversationConfigModel.getSummaryStatusFlag() == 1);
                SobotSPUtils.getInstance().put(OnlineConstant
                        .SUMMARY_STATUS_INPUT_FLAG, conversationConfigModel.getSummaryStatusInputFlag() == 1);
                SobotSPUtils.getInstance().put(OnlineConstant
                        .QDESCRIBE_SHOW_FLAG, conversationConfigModel.getQDescribeShowFlag() == 1);
            }

            @Override
            public void onFailure(Exception e, String des) {

            }
        });
    }

    /**
     * 通过照相上传图片
     */
    public void selectPicFromCamera() {

        if (!SobotSDCardUtils.isExitsSdcard()) {
            SobotToastUtil.showCustomToast(getApplicationContext(), getResString("sobot_app_sdcard_does_not_exist"),
                    Toast.LENGTH_SHORT);
            return;
        }
        sobotPermissionListener = new SobotPermissionListenerImpl() {
            @Override
            public void onPermissionSuccessListener() {
                //如果有拍照所需的权限，跳转到拍照界面
                startActivityForResult(SobotCameraActivity.newIntent(getSobotContext()), OnlineConstant.SOBOT_REQUEST_CODE_CAMERA);
            }
        };

        if (!checkStorageAudioAndCameraPermission()) {
            return;
        }
        // 打开拍摄页面
        startActivityForResult(SobotCameraActivity.newIntent(getSobotContext()), OnlineConstant.SOBOT_REQUEST_CODE_CAMERA);
    }


    /**
     * 检查拍摄权限
     *
     * @return true, 已经获取权限;false,没有权限,尝试获取
     */
    protected boolean checkStorageAudioAndCameraPermission() {
        if (Build.VERSION.SDK_INT >= 23 && SobotSystemUtils.getTargetSdkVersion(getSobotActivity().getApplicationContext()) >= 23) {
            if (ContextCompat.checkSelfPermission(getSobotActivity(), Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                this.requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO}
                        , SobotBaseConstant.SOBOT_CODE_PERMISSIONS_REQUEST);
                return false;
            }
            if (ContextCompat.checkSelfPermission(getSobotActivity(), Manifest.permission.RECORD_AUDIO)
                    != PackageManager.PERMISSION_GRANTED) {
                this.requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO}
                        , SobotBaseConstant.SOBOT_CODE_PERMISSIONS_REQUEST);
                return false;
            }
        }
        return true;
    }
}
