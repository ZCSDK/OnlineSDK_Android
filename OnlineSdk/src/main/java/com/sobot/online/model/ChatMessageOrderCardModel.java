package com.sobot.online.model;


import java.io.Serializable;
import java.util.List;

/**
 * 订单卡片消息实体类
 */
public class ChatMessageOrderCardModel implements Serializable {

//    {orderStatus:订单状态
//        orderCode:订单编号
//        orderUrl:订单链接
//        createTime:创建时间
//        totalFee:总金额
//        goodsCount:商品个数
//        goods：商品信息
//        createTimeFormat：格式化后的时间
//    }

    private static final long serialVersionUID = 1L;

    private int orderStatus;
    private String orderCode;
    private String orderUrl;
    private String createTime;
    private int totalFee;
    private String goodsCount;
    private List<ChatMessageGoodsModel> goods;
    private String createTimeFormat;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderUrl() {
        return orderUrl;
    }

    public void setOrderUrl(String orderUrl) {
        this.orderUrl = orderUrl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }

    public String getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(String goodsCount) {
        this.goodsCount = goodsCount;
    }

    public List<ChatMessageGoodsModel> getGoods() {
        return goods;
    }

    public void setGoods(List<ChatMessageGoodsModel> goods) {
        this.goods = goods;
    }

    public String getCreateTimeFormat() {
        return createTimeFormat;
    }

    public void setCreateTimeFormat(String createTimeFormat) {
        this.createTimeFormat = createTimeFormat;
    }

    public class ChatMessageGoodsModel {
        // {"pictureUrl":"https://img.sobot.com/chatres/66a522ea3ef944a98af45bac09220861/msg/20190930/7d938872592345caa77eb261b4581509.png","name":"苹果"}
        private String pictureUrl;
        private String name;

        public String getPictureUrl() {
            return pictureUrl;
        }

        public void setPictureUrl(String pictureUrl) {
            this.pictureUrl = pictureUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "ChatMessageGoodsModel{" +
                    "pictureUrl='" + pictureUrl + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }


    @Override
    public String toString() {
        return "ChatMessageOrderCardModel{" +
                "orderStatus='" + orderStatus + '\'' +
                ", orderCode='" + orderCode + '\'' +
                ", orderUrl='" + orderUrl + '\'' +
                ", createTime='" + createTime + '\'' +
                ", totalFee='" + totalFee + '\'' +
                ", goodsCount='" + goodsCount + '\'' +
                ", goods=" + goods +
                ", createTimeFormat='" + createTimeFormat + '\'' +
                '}';
    }
}