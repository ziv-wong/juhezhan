package com.ziv.juhezhan.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import java.util.HashMap;


public class DiscoverFragment extends BaseFragment implements ClassifyListAdapter.ClassifyEventListener {


    private static final String ARG_POSITION      = "position";
    private static final String ARG_MAIN_SETTINGS = "main_settings";
    private static final String ARG_TAG           = "tag";
    private String mTitle;
    private DiscoverFragmentListener mDiscoverFragmentListener;
    private MyRecyclerView                                               listDiscover;
    private ClassifyListAdapter                                          adapter;
    private int                                                          mPosition;
    private ArrayList<com.ziv.juhezhan.setting.CatalogMainSetting> mCatalogMainSettings;

    public static DiscoverFragment newInstance(int position) {
        DiscoverFragment f = new DiscoverFragment();
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

        View root = inflater.inflate(R.layout.fragment_discover, container, false);

        listDiscover = (MyRecyclerView) root.findViewById(R.id.listClassify);
        listDiscover.setEmptyView(inflater.inflate(R.layout.item_empty, container, false));
        listDiscover.setLayoutManager(new LinearLayoutManager(
                inflater.getContext(), LinearLayoutManager.VERTICAL, false));
        listDiscover.setItemAnimator(new DefaultItemAnimator());
        listDiscover.setHasFixedSize(true);
        listDiscover.addItemDecoration(new Divider(
                inflater.getContext(),
                LinearLayoutManager.HORIZONTAL,
                DPUtil.dip2px(getContext(), 2),
                getResources().getColor(R.color.colorDividerBlue)));

        adapter = new ClassifyListAdapter(
                getContext(), "大分类", mCatalogMainSettings, ClassifyListAdapter.DISCOVER);
        adapter.setClassifyEventListener(this);
        listDiscover.setAdapter(adapter);

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

        // DiscoverFragment's CatalogMainSettings should differ with user data
        Classify allClassify = getAllClassify();
        Classify differClassify = ClassifyParser.parseToDifferClassify(allClassify, userClassify);
        mCatalogMainSettings = ClassifyParser.parseToCatalogMainSettings(differClassify);

    }

    private Classify getAllClassify() {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        ArrayList<String> mainNames = new ArrayList<>();
        mainNames.add("虎扑");
        mainNames.add("豆瓣");
        mainNames.add("斗鱼");
        mainNames.add("微博");
        mainNames.add("知乎");
        ArrayList<String> subNames;
        subNames = new ArrayList<>();
        subNames.add("话题");
        subNames.add("步行街");
        map.put(mainNames.get(0), subNames);
        subNames = new ArrayList<>();
        subNames.add("新电影");
        subNames.add("新书");
        subNames.add("热门书籍");
        map.put(mainNames.get(1), subNames);
        subNames = new ArrayList<>();
        subNames.add("所有游戏");
        subNames.add("王者荣耀");
        map.put(mainNames.get(2), subNames);
        subNames = new ArrayList<>();
        subNames.add("实时热搜");
        subNames.add("新鲜事");
        map.put(mainNames.get(3), subNames);
        subNames = new ArrayList<>();
        subNames.add("日报");
        map.put(mainNames.get(4), subNames);

        return new Classify(mainNames, map);
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public void setTitle(String title) {
        mTitle = title;
    }

    public DiscoverFragmentListener getDiscoverFragmentListener() {
        return mDiscoverFragmentListener;
    }

    public void setDiscoverFragmentListener(DiscoverFragmentListener discoverFragmentListener) {
        mDiscoverFragmentListener = discoverFragmentListener;
    }

    public void updateView() {
        getData();
        adapter.setClassifyList(mCatalogMainSettings);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClassifyClickEvent(View view, int position, int type) {
        if (mDiscoverFragmentListener != null) {
            mDiscoverFragmentListener.onDiscoverFragmentEvent(type);
        }
    }

    public interface DiscoverFragmentListener {
        void onDiscoverFragmentEvent(int type);
    }
}
