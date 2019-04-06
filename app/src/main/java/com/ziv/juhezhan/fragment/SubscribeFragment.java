package com.ziv.juhezhan.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.ziv.juhezhan.R;
import com.ziv.juhezhan.data.Classify;
import com.ziv.juhezhan.data.ClassifyParser;
import com.ziv.juhezhan.data.DataBaseUtil;
import com.ziv.juhezhan.fragment.catalog.CatalogMainPagerAdapter;
import com.ziv.juhezhan.setting.CatalogMainSetting;
import com.ziv.juhezhan.tool.DPUtil;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;


public class SubscribeFragment extends BaseFragment {
    private static final String ARG_POSITION      = "position";
    private static final String ARG_MAIN_SETTINGS = "main_settings";
    private static final String ARG_TAG           = "tag";

    private String mTitle;

    private PagerSlidingTabStrip    tabs;
    private ViewPager               pager;
    private CatalogMainPagerAdapter adapter;
    private FragmentManager         mFragmentManager;
    private TextView tvEmpty;

    private int                           mPosition;
    private ArrayList<CatalogMainSetting> mCatalogMainSettings;

    public static SubscribeFragment newInstance(int position) {
        SubscribeFragment f = new SubscribeFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPosition = getArguments().getInt(ARG_POSITION);
        mCatalogMainSettings = null;
        getData();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_subscribe, container, false);

        tvEmpty = (TextView) root.findViewById(R.id.tvEmpty);

        tabs = (PagerSlidingTabStrip) root.findViewById(R.id.subscribe_tabs);
        tabs.setTextColor(getResources().getColor(R.color.colorMainTab));
        tabs.setTextSize(DPUtil.dip2px(getContext(), 15));
        pager = (ViewPager) root.findViewById(R.id.subscribe_pager);
        pager.setOffscreenPageLimit(10);

        tvEmpty.setVisibility(mCatalogMainSettings.size() != 0 ? View.INVISIBLE : View.VISIBLE);

        mFragmentManager = getActivity().getSupportFragmentManager();
        adapter = new CatalogMainPagerAdapter(
                getActivity().getSupportFragmentManager(), mCatalogMainSettings);
        pager.setAdapter(adapter);
        tabs.setViewPager(pager);

        return root;
    }

    private void getData() {

        // Read from local json
        DataBaseUtil dataBaseUtil = DataBaseUtil.getInstance(getContext());
        Classify userClassify = null;
        try {
            userClassify = dataBaseUtil.getUserData();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        if (userClassify == null) {
            return;
        }

        // Parse Classify to List of CatalogMainSetting
        mCatalogMainSettings = ClassifyParser.parseToCatalogMainSettings(userClassify);


    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public void setTitle(String title) {
        mTitle = title;
    }

    public void updateView() {
        getData();
        tvEmpty.setVisibility(mCatalogMainSettings.size() != 0 ? View.INVISIBLE : View.VISIBLE);
        adapter = new CatalogMainPagerAdapter(mFragmentManager, mCatalogMainSettings);
        pager.setAdapter(adapter);
        tabs.setViewPager(pager);
    }


}
