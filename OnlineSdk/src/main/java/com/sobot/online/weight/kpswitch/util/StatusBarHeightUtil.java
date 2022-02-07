package com.sobot.online.weight.kpswitch.util;

import android.content.Context;
import android.util.Log;

/**
 * <p/>
 * In order to avoid the layout of the Status bar.
 */
public class StatusBarHeightUtil {

    private static boolean INIT = false;
    private static int STATUS_BAR_HEIGHT = 50;

    private static final String STATUS_BAR_DEF_PACKAGE = "android";
    private static final String STATUS_BAR_DEF_TYPE = "dimen";
    private static final String STATUS_BAR_NAME = "status_bar_height";

    public static synchronized int getStatusBarHeight(final Context context) {
        if (!INIT) {
            int resourceId = context.getResources().
                    getIdentifier(STATUS_BAR_NAME, STATUS_BAR_DEF_TYPE, STATUS_BAR_DEF_PACKAGE);
            if (resourceId > 0) {
                STATUS_BAR_HEIGHT = context.getResources().getDimensionPixelSize(resourceId);
                INIT = true;
                Log.d("StatusBarHeightUtil",
                        String.format("Get status bar height %d", STATUS_BAR_HEIGHT));
            }
        }

        return STATUS_BAR_HEIGHT;
    }
}
