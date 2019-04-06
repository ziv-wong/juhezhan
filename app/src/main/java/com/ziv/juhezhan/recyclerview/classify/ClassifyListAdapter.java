package com.ziv.juhezhan.recyclerview.classify;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ziv.juhezhan.R;
import com.ziv.juhezhan.recyclerview.Divider;
import com.ziv.juhezhan.setting.CatalogMainSetting;
import com.ziv.juhezhan.setting.CatalogSubSetting;
import com.ziv.juhezhan.tool.DPUtil;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


public class ClassifyListAdapter extends RecyclerView.Adapter<ClassifyViewHolder> implements SubClassifyListAdapter.OnBtnClickListener {

    public static final int DISCOVER = 1;
    public static final int SETTING  = 2;
    private ClassifyEventListener mClassifyEventListener;
    private Context                  mContext;
    private String                   mTitle;
    private String                   mRequestUrl;
    private List<CatalogMainSetting> mClassifyList;
    private int                      mType;
    private MyHandler mMyHandler;

    public ClassifyListAdapter(Context context, String title, List<CatalogMainSetting> list, int type) {
        mContext = context;
        mTitle = title;
        mClassifyList = list;
        mType = type;
        init(context);
    }

    private void init(Context context) {
        mMyHandler = new MyHandler(context);
    }

    public ClassifyListAdapter(Context context, String title, String requestUrl, int type) {
        mContext = context;
        mTitle = title;
        mRequestUrl = requestUrl;
        mType = type;
        init(context);
    }

    @Override
    public ClassifyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.item_classify, parent, false);
        ClassifyViewHolder viewHolder = new ClassifyViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ClassifyViewHolder holder, int position) {

        // TODO: 这里应该网络请求到各个小分类的名字和对应的请求链接(当子分类较多时)
        CatalogMainSetting bean = mClassifyList.get(position);
        holder.tvTitle.setText(bean.getName());
        RecyclerView listClassifySub = holder.listClassifySub;
        listClassifySub.setLayoutManager(new LinearLayoutManager(
                mContext, LinearLayoutManager.VERTICAL, false));
        listClassifySub.setItemAnimator(new DefaultItemAnimator());
        listClassifySub.setHasFixedSize(true);
        listClassifySub.addItemDecoration(new Divider(
                mContext,
                LinearLayoutManager.HORIZONTAL,
                DPUtil.dip2px(mContext, 1),
                mContext.getResources().getColor(R.color.colorDividerBlue)));
        ArrayList<CatalogSubSetting> list = bean.getCatalogSubSettings();
        SubClassifyListAdapter adapter = new SubClassifyListAdapter(
                mContext, bean.getName(), list, mType);
        adapter.setOnBtnClickListener(this);
        listClassifySub.setAdapter(adapter);

    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return mClassifyList.size();
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public List<CatalogMainSetting> getClassifyList() {
        return mClassifyList;
    }

    public void setClassifyList(List<CatalogMainSetting> classifyList) {
        mClassifyList = classifyList;
        notifyDataSetChanged();
    }

    public ClassifyEventListener getClassifyEventListener() {
        return mClassifyEventListener;
    }

    public void setClassifyEventListener(ClassifyEventListener classifyEventListener) {
        mClassifyEventListener = classifyEventListener;
    }

    @Override
    public boolean onBtnClick(String subName, int type) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mMyHandler.sendEmptyMessage(1);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

        if (mClassifyEventListener != null) {
            mClassifyEventListener.onClassifyClickEvent(null, 0, type);
        }

        return true;
    }

    public interface ClassifyEventListener {
        void onClassifyClickEvent(View view, int position, int type);
    }

    class MyHandler extends Handler {

        private WeakReference<Context> mReference;

        public MyHandler(Context context) {
            mReference = new WeakReference<>(context);
        }

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case 1:
                    notifyDataSetChanged();
                    for (int i = 0; i < mClassifyList.size(); ) {
                        CatalogMainSetting bean = mClassifyList.get(i);
                        if (bean.getCatalogSubSettings().size() == 0) {
                            mClassifyList.remove(i);
                            notifyItemRemoved(i);
                            continue;
                        }
                        i++;
                    }
                    break;
                case 2:

                    break;
            }

        }
    }

}

