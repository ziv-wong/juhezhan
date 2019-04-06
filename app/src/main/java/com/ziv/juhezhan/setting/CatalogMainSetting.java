package com.ziv.juhezhan.setting;

import java.io.Serializable;
import java.util.ArrayList;

public class CatalogMainSetting implements Serializable {

    private String                       name;
    private String                       queryUrl;
    private ArrayList<CatalogSubSetting> mCatalogSubSettings;

    public CatalogMainSetting() {

    }

    public CatalogMainSetting(String name, String queryUrl, ArrayList<CatalogSubSetting> catalogSubSettings) {
        this.name = name;
        this.queryUrl = queryUrl;
        this.mCatalogSubSettings = catalogSubSettings;
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

    public ArrayList<CatalogSubSetting> getCatalogSubSettings() {
        return mCatalogSubSettings;
    }

    public void setCatalogSubSettings(ArrayList<CatalogSubSetting> catalogSubSettings) {
        mCatalogSubSettings = catalogSubSettings;
    }

}
