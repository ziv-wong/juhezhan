package com.ziv.juhezhan.fragment.catalog;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;

import com.google.gson.Gson;
import com.ziv.juhezhan.MyService;
import com.ziv.juhezhan.R;
import com.ziv.juhezhan.bean.BaseBean;
import com.ziv.juhezhan.bean.DoubanMovieBean;
import com.ziv.juhezhan.bean.DoubanNewBookBean;
import com.ziv.juhezhan.bean.DoubanPopularBookBean;
import com.ziv.juhezhan.bean.DouyuGameBean;
import com.ziv.juhezhan.bean.DouyuWzryBean;
import com.ziv.juhezhan.bean.HupuBxjBean;
import com.ziv.juhezhan.bean.HupuVoteBean;
import com.ziv.juhezhan.bean.WeiboNoveltyBean;
import com.ziv.juhezhan.bean.WeiboRealTimeHotBean;
import com.ziv.juhezhan.bean.ZhihuDailyBean;
import com.ziv.juhezhan.recyclerview.Divider;
import com.ziv.juhezhan.recyclerview.MyRecyclerView;
import com.ziv.juhezhan.recyclerview.RecyclerViewClickListener;
import com.ziv.juhezhan.recyclerview.subscribe.SubscribeListAdapter;
import com.ziv.juhezhan.setting.CatalogSubSetting;
import com.ziv.juhezhan.setting.GlobalConfig;
import com.ziv.juhezhan.tool.LogAndToastUtil;
import com.thefinestartist.finestwebview.FinestWebView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class CatalogSubFragment extends Fragment {
    private static final String ARG_POSITION    = "position";
    private static final String ARG_SUB_SETTING = "main_setting";

    private MyRecyclerView       listView;
    private SubscribeListAdapter adapter;

    private int               mPosition;
    private CatalogSubSetting mCatalogSubSetting;

    public static CatalogSubFragment newInstance(int position, CatalogSubSetting catalogSubSetting) {
        CatalogSubFragment f = new CatalogSubFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        b.putSerializable(ARG_SUB_SETTING, catalogSubSetting);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPosition = getArguments().getInt(ARG_POSITION);
        mCatalogSubSetting = (CatalogSubSetting) getArguments().getSerializable(ARG_SUB_SETTING);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list, container, false);

        listView = (MyRecyclerView) view.findViewById(R.id.listView);
        listView.setEmptyView(inflater.inflate(R.layout.item_empty, container, false));
        listView.setLayoutManager(new LinearLayoutManager(inflater.getContext(), LinearLayoutManager.VERTICAL, false));
        listView.setItemAnimator(new DefaultItemAnimator());
        listView.setHasFixedSize(true);
        listView.addItemDecoration(new Divider(inflater.getContext(), LinearLayoutManager.HORIZONTAL));

        listView.addOnItemTouchListener(new RecyclerViewClickListener(getContext(), listView, new RecyclerViewClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                BaseBean bean = (BaseBean) adapter.getList().get(position);
                String h = bean.getHref();
                FinestWebView.Builder builder = new FinestWebView.Builder(getContext())
                        .stringResCopiedToClipboard(R.string.c_copied_to_clipboard)
                        .stringResRefresh(R.string.c_refresh)
                        .stringResShareVia(R.string.c_share_via)
                        .stringResCopyLink(R.string.c_copy_link)
                        .stringResOpenWith(R.string.c_open_with)
                        .progressBarColor(getContext().getResources().getColor(R.color.colorGrey50))
                        .swipeRefreshColor(getContext().getResources().getColor(R.color.colorPrimary))
                        .webViewJavaScriptEnabled(true)
                        .webViewJavaScriptCanOpenWindowsAutomatically(true)
                        .webViewSupportZoom(true)
                        .webViewBuiltInZoomControls(true)
                        .webViewUseWideViewPort(true)
                        .webViewAllowFileAccess(true)
                        .webViewLoadWithOverviewMode(true)
                        .webViewAppCacheEnabled(false)
                        .webViewCacheMode(WebSettings.LOAD_NO_CACHE)
                        .webViewDomStorageEnabled(true);
                builder.show(h);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));

        final int contentType = mCatalogSubSetting.getContentType();
        adapter = new SubscribeListAdapter<>(
                inflater.getContext(), new ArrayList<BaseBean>(), contentType);
        listView.setAdapter(adapter);

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(GlobalConfig.mListRequest[0])
                .build();
        MyService myService = mRetrofit.create(MyService.class);
        String url = mCatalogSubSetting.getQueryUrl();
        Call<ResponseBody> call = myService.getData(url);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String body = response.body().string();
                    Gson gson = new Gson();
                    BaseBean[] bean = null;
                    switch (contentType) {
                        case BaseBean.HUPU_VOTE:
                            bean = gson.fromJson(body, HupuVoteBean[].class);
                            break;
                        case BaseBean.HUPU_BXJ:
                            bean = gson.fromJson(body, HupuBxjBean[].class);
                            break;
                        case BaseBean.DOUBAN_MOVIE:
                            bean = gson.fromJson(body, DoubanMovieBean[].class);
                            break;
                        case BaseBean.DOUBAN_NEWBOOK:
                            bean = gson.fromJson(body, DoubanNewBookBean[].class);
                            break;
                        case BaseBean.DOUBAN_POPULARBOOK:
                            bean = gson.fromJson(body, DoubanPopularBookBean[].class);
                            break;
                        case BaseBean.DOUYU_GAME:
                            bean = gson.fromJson(body, DouyuGameBean[].class);
                            break;
                        case BaseBean.DOUYU_WZRY:
                            bean = gson.fromJson(body, DouyuWzryBean[].class);
                            break;
                        case BaseBean.WEIBO_REALTIMEHOT:
                            bean = gson.fromJson(body, WeiboRealTimeHotBean[].class);
                            break;
                        case BaseBean.WEIBO_NOVELTY:
                            bean = gson.fromJson(body, WeiboNoveltyBean[].class);
                            break;
                        case BaseBean.ZHIHU_DAILY:
                            bean = gson.fromJson(body, ZhihuDailyBean[].class);
                            break;
                    }
                    adapter.setList(Arrays.asList(bean));
                } catch (IOException e) {
                    LogAndToastUtil.ToastOut(getActivity(), e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });

        return view;
    }

}
