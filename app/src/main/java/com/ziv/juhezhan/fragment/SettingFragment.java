package com.ziv.juhezhan.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ziv.juhezhan.R;
import com.ziv.juhezhan.data.Classify;
import com.ziv.juhezhan.data.ClassifyParser;
import com.ziv.juhezhan.data.DataBaseUtil;
import com.ziv.juhezhan.recyclerview.Divider;
import com.ziv.juhezhan.recyclerview.MyRecyclerView;
import com.ziv.juhezhan.recyclerview.classify.ClassifyListAdapter;
import com.ziv.juhezhan.tool.DPUtil;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;


public class SettingFragment extends BaseFragment implements ClassifyListAdapter.ClassifyEventListener {

    private static final String ARG_POSITION      = "position";
    private static final String ARG_MAIN_SETTINGS = "main_settings";
    private static final String ARG_TAG           = "tag";
    private String mTitle;
    private SettingFragmentListener mSettingFragmentListener;
    private MyRecyclerView listUserClassify;
    private TextView       tvUserName;
    private ImageView      imgUserHead;
    private ClassifyListAdapter                                          adapter;
    private int                                                          mPosition;
    private ArrayList<com.ziv.juhezhan.setting.CatalogMainSetting> mCatalogMainSettings;

    public static SettingFragment newInstance(int position) {
        SettingFragment f = new SettingFragment();
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

        View root = inflater.inflate(R.layout.fragment_setting, container, false);

        listUserClassify = (MyRecyclerView) root.findViewById(R.id.listUserClassify);
        listUserClassify.setEmptyView(inflater.inflate(R.layout.item_empty, container, false));
        listUserClassify.setLayoutManager(new LinearLayoutManager(
                inflater.getContext(), LinearLayoutManager.VERTICAL, false));
        listUserClassify.setItemAnimator(new DefaultItemAnimator());
        listUserClassify.setHasFixedSize(true);
        listUserClassify.addItemDecoration(new Divider(
                inflater.getContext(),
                LinearLayoutManager.HORIZONTAL,
                DPUtil.dip2px(getContext(), 2),
                getResources().getColor(R.color.colorDividerBlue)));

        adapter = new ClassifyListAdapter(
                getContext(), "大分类", mCatalogMainSettings, ClassifyListAdapter.SETTING);
        adapter.setClassifyEventListener(this);
        listUserClassify.setAdapter(adapter);

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

    public SettingFragmentListener getSettingFragmentListener() {
        return mSettingFragmentListener;
    }

    public void setSettingFragmentListener(SettingFragmentListener settingFragmentListener) {
        mSettingFragmentListener = settingFragmentListener;
    }

    public void updateView() {
        getData();
        adapter.setClassifyList(mCatalogMainSettings);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClassifyClickEvent(View view, int position, int type) {
        if (mSettingFragmentListener != null) {
            mSettingFragmentListener.onSettingFragmentEvent(type);
        }
    }


    public interface SettingFragmentListener {
        void onSettingFragmentEvent(int type);
    }
}
