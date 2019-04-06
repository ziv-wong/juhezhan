package com.ziv.juhezhan;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ziv.juhezhan.fragment.DiscoverFragment;
import com.ziv.juhezhan.fragment.SettingFragment;
import com.ziv.juhezhan.fragment.SubscribeFragment;

import java.util.ArrayList;
import java.util.HashMap;

public class MainPagerAdapter extends FragmentPagerAdapter implements SettingFragment.SettingFragmentListener, DiscoverFragment.DiscoverFragmentListener {

    private HashMap<String, Fragment> mMap;

    private ArrayList<Integer> mList;


    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
        mMap = new HashMap<>();
        mList = new ArrayList<>();
        mList.add(0);
        mList.add(1);
        mList.add(2);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "新的发现！";
            case 1:
                return "你的订阅！";
            case 2:
                return "你的设置！";
        }
        return "";
    }

    @Override
    public Fragment getItem(int position) {
        int pos = mList.get(position);
        switch (pos) {
            case 0:
                DiscoverFragment discoverFragment = DiscoverFragment.newInstance(position);
                mMap.put("discover", discoverFragment);
                discoverFragment.setDiscoverFragmentListener(this);
                return discoverFragment;
            case 1:
                SubscribeFragment subscribeFragment = SubscribeFragment.newInstance(position);
                mMap.put("subscribe", subscribeFragment);
                return subscribeFragment;
            case 2:
                SettingFragment settingFragment = SettingFragment.newInstance(position);
                mMap.put("setting", settingFragment);
                settingFragment.setSettingFragmentListener(this);
                return settingFragment;
        }
        return null;
    }

    @Override
    public void onSettingFragmentEvent(int type) {
        DiscoverFragment dis = (DiscoverFragment) mMap.get("discover");
        dis.updateView();
        SubscribeFragment subscribeFragment = (SubscribeFragment) mMap.get("subscribe");
        subscribeFragment.updateView();
    }

    @Override
    public void onDiscoverFragmentEvent(int type) {
        SettingFragment settingFragment = (SettingFragment) mMap.get("setting");
        settingFragment.updateView();
        SubscribeFragment subscribeFragment = (SubscribeFragment) mMap.get("subscribe");
        subscribeFragment.updateView();
    }
}