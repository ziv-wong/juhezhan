package com.ziv.juhezhan.setting;


public class CatalogSubSetting extends CatalogMainSetting {

    private String name;
    private String queryUrl;
    private int    contentType;

    public CatalogSubSetting(String name, String queryUrl, int contentType) {
        this.name = name;
        this.queryUrl = queryUrl;
        this.contentType = contentType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQueryUrl() {
        return queryUrl;
    }

    public void setQueryUrl(String queryUrl) {
        this.queryUrl = queryUrl;
    }

    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }

}
