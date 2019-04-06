package com.ziv.juhezhan.fragment.catalog;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.astuetz.PagerSlidingTabStrip;
import com.ziv.juhezhan.R;
import com.ziv.juhezhan.setting.CatalogMainSetting;
import com.ziv.juhezhan.setting.CatalogSubSetting;

import java.util.ArrayList;


public class CatalogMainFragment extends Fragment {
    private static final String ARG_POSITION     = "position";
    private static final String ARG_MAIN_SETTING = "main_setting";
    private static final String ARG_TAG          = "tag";

    private PagerSlidingTabStrip   tabs;
    private ViewPager              pager;
    private CatalogSubPagerAdapter adapter;

    private int                mPosition;
    private CatalogMainSetting mCatalogMainSetting;

    public static CatalogMainFragment newInstance(int position, CatalogMainSetting catalogMainSetting) {
        CatalogMainFragment f = new CatalogMainFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        b.putSerializable(ARG_MAIN_SETTING, catalogMainSetting);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPosition = getArguments().getInt(ARG_POSITION);
        mCatalogMainSetting = (CatalogMainSetting) getArguments().getSerializable(ARG_MAIN_SETTING);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_tab, container, false);

        tabs = (PagerSlidingTabStrip) root.findViewById(R.id.tab_tabs);
        pager = (ViewPager) root.findViewById(R.id.tab_pager);

        tabs.setId(View.generateViewId());
        int tabId = tabs.getId();

        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) pager.getLayoutParams();
        lp.addRule(RelativeLayout.BELOW, tabId);
        pager.setId(View.generateViewId());

        pager.setOffscreenPageLimit(5);

        ArrayList<CatalogSubSetting> catalogSubSettings = mCatalogMainSetting.getCatalogSubSettings();

        adapter = new CatalogSubPagerAdapter(getActivity().getSupportFragmentManager(),
                catalogSubSettings);
        pager.setAdapter(adapter);
        tabs.setViewPager(pager);

        return root;
    }

    public void updateView(CatalogMainSetting catalogMainSetting) {
        mCatalogMainSetting = catalogMainSetting;
    }

}
