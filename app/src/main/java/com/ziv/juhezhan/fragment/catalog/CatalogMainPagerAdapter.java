package com.ziv.juhezhan.fragment.catalog;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.ziv.juhezhan.setting.CatalogMainSetting;

import java.util.ArrayList;

public class CatalogMainPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<CatalogMainSetting> mCatalogMainSettings;

    public CatalogMainPagerAdapter(FragmentManager fm, ArrayList<CatalogMainSetting> catalogMainSettings) {
        super(fm);
        this.mCatalogMainSettings = catalogMainSettings;

    }

    @Override
    public int getCount() {
        return mCatalogMainSettings.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mCatalogMainSettings.get(position).getName();
    }

    @Override
    public CatalogMainFragment getItem(int position) {
        CatalogMainSetting setting = mCatalogMainSettings.get(position);
        return CatalogMainFragment.newInstance(position, setting);
    }

    // This function rewrite is important
    // FragmentPagerAdapter will cache fragments so if you want to change fragment data
    // You must rewrite this function to make newest setting adapt to the fragment
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        CatalogMainFragment fragment =
                (CatalogMainFragment) super.instantiateItem(container, position);
        fragment.updateView(mCatalogMainSettings.get(position));
        return fragment;
    }

    public ArrayList<CatalogMainSetting> getCatalogMainSettings() {
        return mCatalogMainSettings;
    }

    public void setCatalogMainSettings(ArrayList<CatalogMainSetting> catalogMainSettings) {
        mCatalogMainSettings = catalogMainSettings;
    }
}