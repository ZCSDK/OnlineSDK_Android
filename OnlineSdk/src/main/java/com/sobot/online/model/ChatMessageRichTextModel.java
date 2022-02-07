package com.sobot.online.model;


import java.io.Serializable;
import java.util.List;

/**
 * 富文本消息实体类
 */
public class ChatMessageRichTextModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String content;
    private String desc;
    private List<ChatMessageRichListModel> richList;
    private String richMoreUrl;
    private String snapshot;

    /**
     * 富文本消息文本、图片列表实体类
     */
    public class ChatMessageRichListModel implements Serializable {
        //0：文本，1：图片，2：音频，3：视频，4：文件
        private int type;
        //对应类型的内容
        private String msg;
        //名字
        private String name;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<ChatMessageRichListModel> getRichList() {
        return richList;
    }

    public void setRichList(List<ChatMessageRichListModel> richList) {
        this.richList = richList;
    }

    public String getRichMoreUrl() {
        return richMoreUrl;
    }

    public void setRichMoreUrl(String richMoreUrl) {
        this.richMoreUrl = richMoreUrl;
    }

    public String getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(String snapshot) {
        this.snapshot = snapshot;
    }

    @Override
    public String toString() {
        return "ChatMessageRichTextModel{" +
                "content='" + content + '\'' +
                ", desc='" + desc + '\'' +
                ", richList=" + richList +
                ", richMoreUrl='" + richMoreUrl + '\'' +
                ", snapshot='" + snapshot + '\'' +
                '}';
    }
}