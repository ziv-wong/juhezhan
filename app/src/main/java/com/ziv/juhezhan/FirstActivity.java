package com.ziv.juhezhan;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ziv.juhezhan.data.AppSaveDataSPUtil;
import com.ziv.juhezhan.data.Classify;
import com.ziv.juhezhan.data.ClassifyParser;
import com.ziv.juhezhan.data.DataBaseUtil;
import com.ziv.juhezhan.recyclerview.Divider;
import com.ziv.juhezhan.recyclerview.MyRecyclerView;
import com.ziv.juhezhan.recyclerview.classify.ClassifyListAdapter;
import com.ziv.juhezhan.setting.CatalogMainSetting;
import com.ziv.juhezhan.tool.DPUtil;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener, ClassifyListAdapter.ClassifyEventListener {

    private TextView       tvHead;
    private Button         btnConfirm;
    private MyRecyclerView listClassify;

    private ClassifyListAdapter           adapter;
    private ArrayList<CatalogMainSetting> mCatalogMainSettings;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        AppSaveDataSPUtil.init(this);
        AppSaveDataSPUtil.setFirstIn(false);

        getData();

        initView();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private void getData() {
        // Read from local json
        DataBaseUtil dataBaseUtil = DataBaseUtil.getInstance(this);
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

    private void initView() {
        tvHead = (TextView) findViewById(R.id.tvHead);
        btnConfirm = (Button) findViewById(R.id.btnConfirm);
        listClassify = (MyRecyclerView) findViewById(R.id.listClassify);

        btnConfirm.setOnClickListener(this);
        btnConfirm.setClickable(false);

        listClassify.setEmptyView(View.inflate(this, R.layout.item_empty, null));
        listClassify.setLayoutManager(new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false));
        listClassify.setItemAnimator(new DefaultItemAnimator());
        listClassify.setHasFixedSize(true);
        listClassify.addItemDecoration(new Divider(
                this,
                LinearLayoutManager.HORIZONTAL,
                DPUtil.dip2px(this, 2),
                getResources().getColor(R.color.colorDividerBlue)));

        adapter = new ClassifyListAdapter(
                this, "大分类", mCatalogMainSettings, ClassifyListAdapter.DISCOVER);
        adapter.setClassifyEventListener(this);
        listClassify.setAdapter(adapter);

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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnConfirm:
                btnConfirm.setClickable(false);
                Intent intent = new Intent(FirstActivity.this, MainActivity.class);

                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public void onClassifyClickEvent(View view, int position, int type) {

        btnConfirm.setClickable(true);
        btnConfirm.setTextColor(getResources().getColor(R.color.colorGrey50));
        btnConfirm.setBackground(getResources().getDrawable(R.drawable.bg_sub_classify_item));

        getData();
        adapter.setClassifyList(mCatalogMainSettings);
        adapter.notifyDataSetChanged();
    }
}
