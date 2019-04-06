package com.ziv.juhezhan.fragment.catalog;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ziv.juhezhan.setting.CatalogSubSetting;

import java.util.ArrayList;

public class CatalogSubPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<CatalogSubSetting> mCatalogSubSettings;

    public CatalogSubPagerAdapter(FragmentManager fm, ArrayList<CatalogSubSetting> catalogSubSettings) {
        super(fm);
        this.mCatalogSubSettings = catalogSubSettings;
    }

    @Override
    public int getCount() {
        return mCatalogSubSettings.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mCatalogSubSettings.get(position).getName();
    }

    @Override
    public CatalogSubFragment getItem(int position) {
        CatalogSubSetting setting = mCatalogSubSettings.get(position);
        return CatalogSubFragment.newInstance(position, setting);
    }

    public ArrayList<CatalogSubSetting> getCatalogSubSettings() {
        return mCatalogSubSettings;
    }

    public void setCatalogSubSettings(ArrayList<CatalogSubSetting> catalogSubSettings) {
        mCatalogSubSettings = catalogSubSettings;
    }

}