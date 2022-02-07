package com.sobot.online.model;

/**
 * Created by Administrator on 2016/11/23.
 */

public class VisitMsgBean {
    /**
     * visitSourcePage : 访问来源页
     * searchEngine : 搜索引擎
     * utmCampaign : 广告名称
     * utmContent : 广告内容
     * utmMedium : 广告媒介
     * utmSource : 广告来源
     * utmTerm : 广告关键词
     * keyWord : 关键字
     */

    private String visitSourcePage;
    private String searchEngine;
    private String utmCampaign;
    private String utmContent;
    private String utmMedium;
    private String utmSource;
    private String utmTerm;
    private String keyWord;

    public String getVisitSourcePage() {
        return visitSourcePage;
    }

    public void setVisitSourcePage(String visitSourcePage) {
        this.visitSourcePage = visitSourcePage;
    }

    public String getSearchEngine() {
        return searchEngine;
    }

    public void setSearchEngine(String searchEngine) {
        this.searchEngine = searchEngine;
    }

    public String getUtmCampaign() {
        return utmCampaign;
    }

    public void setUtmCampaign(String utmCampaign) {
        this.utmCampaign = utmCampaign;
    }

    public String getUtmContent() {
        return utmContent;
    }

    public void setUtmContent(String utmContent) {
        this.utmContent = utmContent;
    }

    public String getUtmMedium() {
        return utmMedium;
    }

    public void setUtmMedium(String utmMedium) {
        this.utmMedium = utmMedium;
    }

    public String getUtmSource() {
        return utmSource;
    }

    public void setUtmSource(String utmSource) {
        this.utmSource = utmSource;
    }

    public String getUtmTerm() {
        return utmTerm;
    }

    public void setUtmTerm(String utmTerm) {
        this.utmTerm = utmTerm;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}