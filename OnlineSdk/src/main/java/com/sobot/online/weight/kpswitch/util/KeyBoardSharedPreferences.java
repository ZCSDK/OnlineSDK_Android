package com.sobot.online.weight.kpswitch.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * <p/>
 * For save the keyboard height.
 */
class KeyBoardSharedPreferences {

    private static final String FILE_NAME = "keyboard.common";

    private static final String KEY_KEYBOARD_HEIGHT = "sp.key.keyboard.height";

    private static volatile SharedPreferences SP;

    public static boolean save(final Context context, final int keyboardHeight) {
        return with(context).edit()
                .putInt(KEY_KEYBOARD_HEIGHT, keyboardHeight)
                .commit();
    }

    private static SharedPreferences with(final Context context) {
        if (SP == null) {
            synchronized (KeyBoardSharedPreferences.class) {
                if (SP == null) {
                    SP = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
                }
            }
        }

        return SP;
    }

    public static int get(final Context context, final int defaultHeight) {
        return with(context).getInt(KEY_KEYBOARD_HEIGHT, defaultHeight);
    }

}
