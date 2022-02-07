package com.sobot.online.model;

import java.io.Serializable;

//创建客户时，选择公司实体类
public class OnlineEnterPriseModel implements Serializable {

    /**
     * items : [{"companyId":"acb290d666814d65834c70073aed65f2","createServiceId":"efcfda336b5d4caeb4fd71392c54b5c9","createTime":1516774127,"enterpriseId":"4c9049f20ad24a07a400b2de772563ed","enterpriseName":"c014252","enterpriseStatus":1,"id":"4c9049f20ad24a07a400b2de772563ed","rowId":"4c9049f20ad24a07a400b2de772563ed","rowkey":"4c9049f20ad24a07a400b2de772563ed","updateTime":1516774127},{"companyId":"acb290d666814d65834c70073aed65f2","createServiceId":"efcfda336b5d4caeb4fd71392c54b5c9","createTime":1516774127,"enterpriseId":"fd11962566544b7c858f520b8c518849","enterpriseName":"c014260","enterpriseStatus":1,"id":"fd11962566544b7c858f520b8c518849","rowId":"fd11962566544b7c858f520b8c518849","rowkey":"fd11962566544b7c858f520b8c518849","updateTime":1516774127},{"companyId":"acb290d666814d65834c70073aed65f2","createServiceId":"efcfda336b5d4caeb4fd71392c54b5c9","createTime":1516774127,"enterpriseId":"3e8db5279b81463b8c0e159be2576d20","enterpriseName":"c014285","enterpriseStatus":1,"id":"3e8db5279b81463b8c0e159be2576d20","rowId":"3e8db5279b81463b8c0e159be2576d20","rowkey":"3e8db5279b81463b8c0e159be2576d20","updateTime":1516774127},{"companyId":"acb290d666814d65834c70073aed65f2","createServiceId":"efcfda336b5d4caeb4fd71392c54b5c9","createTime":1516774127,"enterpriseId":"b12fb509e33f406cbc51dc8d65423619","enterpriseName":"c014292","enterpriseStatus":1,"id":"b12fb509e33f406cbc51dc8d65423619","rowId":"b12fb509e33f406cbc51dc8d65423619","rowkey":"b12fb509e33f406cbc51dc8d65423619","updateTime":1516774127},{"companyId":"acb290d666814d65834c70073aed65f2","createServiceId":"efcfda336b5d4caeb4fd71392c54b5c9","createTime":1516774127,"enterpriseId":"6c7534b18cb449f2a5fcd0a6b39257b0","enterpriseName":"c014295","enterpriseStatus":1,"id":"6c7534b18cb449f2a5fcd0a6b39257b0","rowId":"6c7534b18cb449f2a5fcd0a6b39257b0","rowkey":"6c7534b18cb449f2a5fcd0a6b39257b0","updateTime":1516774127},{"companyId":"acb290d666814d65834c70073aed65f2","createServiceId":"efcfda336b5d4caeb4fd71392c54b5c9","createTime":1516774127,"enterpriseId":"cb3c042dce454d2292ac56c7f786c57a","enterpriseName":"c014326","enterpriseStatus":1,"id":"cb3c042dce454d2292ac56c7f786c57a","rowId":"cb3c042dce454d2292ac56c7f786c57a","rowkey":"cb3c042dce454d2292ac56c7f786c57a","updateTime":1516774127},{"companyId":"acb290d666814d65834c70073aed65f2","createServiceId":"efcfda336b5d4caeb4fd71392c54b5c9","createTime":1516774127,"enterpriseId":"714197ae40f0493998e091744b968d4c","enterpriseName":"c014333","enterpriseStatus":1,"id":"714197ae40f0493998e091744b968d4c","rowId":"714197ae40f0493998e091744b968d4c","rowkey":"714197ae40f0493998e091744b968d4c","updateTime":1516774127},{"companyId":"acb290d666814d65834c70073aed65f2","createServiceId":"efcfda336b5d4caeb4fd71392c54b5c9","createTime":1516774127,"enterpriseId":"999b0d66adea486eb239bb0824517b1f","enterpriseName":"c014339","enterpriseStatus":1,"id":"999b0d66adea486eb239bb0824517b1f","rowId":"999b0d66adea486eb239bb0824517b1f","rowkey":"999b0d66adea486eb239bb0824517b1f","updateTime":1516774127},{"companyId":"acb290d666814d65834c70073aed65f2","createServiceId":"efcfda336b5d4caeb4fd71392c54b5c9","createTime":1516774127,"enterpriseId":"dfa4edcdfa0d4a7f93821ace0926b9ea","enterpriseName":"c014361","enterpriseStatus":1,"id":"dfa4edcdfa0d4a7f93821ace0926b9ea","rowId":"dfa4edcdfa0d4a7f93821ace0926b9ea","rowkey":"dfa4edcdfa0d4a7f93821ace0926b9ea","updateTime":1516774127},{"companyId":"acb290d666814d65834c70073aed65f2","createServiceId":"efcfda336b5d4caeb4fd71392c54b5c9","createTime":1516774127,"enterpriseId":"a8ba2f6cfb364fef90cc6d5207016a41","enterpriseName":"c014367","enterpriseStatus":1,"id":"a8ba2f6cfb364fef90cc6d5207016a41","rowId":"a8ba2f6cfb364fef90cc6d5207016a41","rowkey":"a8ba2f6cfb364fef90cc6d5207016a41","updateTime":1516774127},{"companyId":"acb290d666814d65834c70073aed65f2","createServiceId":"efcfda336b5d4caeb4fd71392c54b5c9","createTime":1516774127,"enterpriseId":"73f9ffee392a4822b442a6d70b742b3b","enterpriseName":"c014368","enterpriseStatus":1,"id":"73f9ffee392a4822b442a6d70b742b3b","rowId":"73f9ffee392a4822b442a6d70b742b3b","rowkey":"73f9ffee392a4822b442a6d70b742b3b","updateTime":1516774127},{"companyId":"acb290d666814d65834c70073aed65f2","createServiceId":"efcfda336b5d4caeb4fd71392c54b5c9","createTime":1516774127,"enterpriseId":"45bd5057d96f440eb6ffdf1621020a7b","enterpriseName":"c014392","enterpriseStatus":1,"id":"45bd5057d96f440eb6ffdf1621020a7b","rowId":"45bd5057d96f440eb6ffdf1621020a7b","rowkey":"45bd5057d96f440eb6ffdf1621020a7b","updateTime":1516774127},{"companyId":"acb290d666814d65834c70073aed65f2","createServiceId":"efcfda336b5d4caeb4fd71392c54b5c9","createTime":1516774127,"enterpriseId":"7ce9dcadc14741ce880ce9ac8281ab38","enterpriseName":"c014820","enterpriseStatus":1,"id":"7ce9dcadc14741ce880ce9ac8281ab38","rowId":"7ce9dcadc14741ce880ce9ac8281ab38","rowkey":"7ce9dcadc14741ce880ce9ac8281ab38","updateTime":1516774127},{"companyId":"acb290d666814d65834c70073aed65f2","createServiceId":"efcfda336b5d4caeb4fd71392c54b5c9","createTime":1516774127,"enterpriseId":"11597190f0bb4911a3e21449137608a2","enterpriseName":"c014851","enterpriseStatus":1,"id":"11597190f0bb4911a3e21449137608a2","rowId":"11597190f0bb4911a3e21449137608a2","rowkey":"11597190f0bb4911a3e21449137608a2","updateTime":1516774127},{"companyId":"acb290d666814d65834c70073aed65f2","createServiceId":"efcfda336b5d4caeb4fd71392c54b5c9","createTime":1516774127,"enterpriseId":"e91521cce56249ac927cd5714ae48e1c","enterpriseName":"c014858","enterpriseStatus":1,"id":"e91521cce56249ac927cd5714ae48e1c","rowId":"e91521cce56249ac927cd5714ae48e1c","rowkey":"e91521cce56249ac927cd5714ae48e1c","updateTime":1516774127}]
     * pageCount : 1334
     * pageNo : 1
     * pageSize : 15
     * retCode : 000000
     * totalCount : 20005
     */

    private String companyId;
    private String createServiceId;
    private int createTime;
    private String enterpriseId;
    private String enterpriseName;
    private int enterpriseStatus;
    private String id;
    private String rowId;
    private String rowkey;
    private int updateTime;

    @Override
    public String toString() {
        return "EnterPriseModel{" +
                "companyId='" + companyId + '\'' +
                ", createServiceId='" + createServiceId + '\'' +
                ", createTime=" + createTime +
                ", enterpriseId='" + enterpriseId + '\'' +
                ", enterpriseName='" + enterpriseName + '\'' +
                ", enterpriseStatus=" + enterpriseStatus +
                ", id='" + id + '\'' +
                ", rowId='" + rowId + '\'' +
                ", rowkey='" + rowkey + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCreateServiceId() {
        return createServiceId;
    }

    public void setCreateServiceId(String createServiceId) {
        this.createServiceId = createServiceId;
    }

    public int getCreateTime() {
        return createTime;
    }

    public void setCreateTime(int createTime) {
        this.createTime = createTime;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public int getEnterpriseStatus() {
        return enterpriseStatus;
    }

    public void setEnterpriseStatus(int enterpriseStatus) {
        this.enterpriseStatus = enterpriseStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public String getRowkey() {
        return rowkey;
    }

    public void setRowkey(String rowkey) {
        this.rowkey = rowkey;
    }

    public int getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(int updateTime) {
        this.updateTime = updateTime;
    }
}