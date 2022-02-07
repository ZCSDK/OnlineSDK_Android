package com.sobot.online.util.voice;

import android.widget.ImageView;

import com.sobot.online.model.ChatMessageModel;

public interface AudioPlayCallBack {

    void onPlayStart(ChatMessageModel mCurrentMsg, ImageView ivAudioPlay);

    void onPlayEnd(ChatMessageModel mCurrentMsg, ImageView ivAudioPlay);
}