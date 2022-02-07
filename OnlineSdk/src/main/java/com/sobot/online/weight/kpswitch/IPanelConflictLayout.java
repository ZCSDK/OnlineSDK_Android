package com.sobot.online.weight.kpswitch;

import android.content.Context;
import android.view.View;

public interface IPanelConflictLayout {
    boolean isKeyboardShowing();

    /**
     * @return The real status of Visible or not
     */
    boolean isVisible();

    /**
     * Keyboard->Panel
     *
     * @see com.sobot.online.weight.kpswitch.util.KPSwitchConflictUtil#showPanel(View)
     */
    void handleShow();

    /**
     * Panel->Keyboard
     *
     * @see com.sobot.online.weight.kpswitch.util.KPSwitchConflictUtil#showKeyboard(View, View)
     */
    void handleHide();

    /**
     * @param isIgnoreRecommendHeight Ignore guaranteeing the panel height equal to the keyboard
     *                                height.
     * @attr ref com.sobot.online.weight.kpswitch.R.styleable#KPSwitchPanelLayout_ignore_recommend_height
     * @see com.sobot.online.weight.kpswitch.handler.KPSwitchPanelLayoutHandler#resetToRecommendPanelHeight(int)
     * @see com.sobot.online.weight.kpswitch.util.KeyboardUtil#getValidPanelHeight(Context)
     */
    void setIgnoreRecommendHeight(boolean isIgnoreRecommendHeight);
}