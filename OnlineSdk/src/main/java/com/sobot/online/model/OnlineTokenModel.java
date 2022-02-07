package com.sobot.online.model;


import java.io.Serializable;

/**
 * 客服信息实例
 */
public class OnlineTokenModel implements Serializable {

    private static final long serialVersionUID = 1L;
    private OnlineToken item;
    private String ret_code;
    private String ret_msg;

    public OnlineToken getItem() {
        return item;
    }

    public void setItem(OnlineToken item) {
        this.item = item;
    }

    public String getRet_code() {
        return ret_code;
    }

    public void setRet_code(String ret_code) {
        this.ret_code = ret_code;
    }

    public String getRet_msg() {
        return ret_msg;
    }

    public void setRet_msg(String ret_msg) {
        this.ret_msg = ret_msg;
    }

    public class OnlineToken implements Serializable {
        private static final long serialVersionUID = 1L;
        //token编码
        private String token;
        //凭证有效时间 单位：秒
        private String expires_in;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getExpires_in() {
            return expires_in;
        }

        public void setExpires_in(String expires_in) {
            this.expires_in = expires_in;
        }
    }

    @Override
    public String toString() {
        return "OnlineTokenModel{" +
                "item=" + item +
                ", ret_code='" + ret_code + '\'' +
                ", ret_msg='" + ret_msg + '\'' +
                '}';
    }
}