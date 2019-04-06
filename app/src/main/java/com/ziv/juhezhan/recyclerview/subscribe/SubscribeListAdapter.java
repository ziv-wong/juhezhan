package com.ziv.juhezhan.recyclerview.subscribe;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;

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
import com.squareup.picasso.Picasso;
import com.thefinestartist.finestwebview.FinestWebView;

import java.util.List;


public class SubscribeListAdapter<T extends BaseBean> extends RecyclerView.Adapter<SubscribeViewHolder> {

    private Context mContext;
    private List<T> mList;
    private int     mContentType;



    public SubscribeListAdapter(Context context, List<T> list, int contentType) {
        mContext = context;
        mList = list;
        mContentType = contentType;
    }

    @Override
    public SubscribeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        switch (viewType) {
            case BaseBean.HUPU_VOTE:
                itemView = LayoutInflater.from(mContext).inflate(R.layout.item_hupu_vote, parent, false);
                break;
            case BaseBean.HUPU_BXJ:
                itemView = LayoutInflater.from(mContext).inflate(R.layout.item_hupu_bxj, parent, false);
                break;
            case BaseBean.DOUBAN_MOVIE:
                itemView = LayoutInflater.from(mContext).inflate(R.layout.item_douban_movie, parent, false);
                break;
            case BaseBean.DOUBAN_NEWBOOK:
                itemView = LayoutInflater.from(mContext).inflate(R.layout.item_douban_newbook, parent, false);
                break;
            case BaseBean.DOUBAN_POPULARBOOK:
                itemView = LayoutInflater.from(mContext).inflate(R.layout.item_douban_popularbook, parent, false);
                break;
            case BaseBean.DOUYU_GAME:
                itemView = LayoutInflater.from(mContext).inflate(R.layout.item_douyu_game, parent, false);
                break;
            case BaseBean.DOUYU_WZRY:
                itemView = LayoutInflater.from(mContext).inflate(R.layout.item_douyu_game, parent, false);
                break;
            case BaseBean.WEIBO_REALTIMEHOT:
                itemView = LayoutInflater.from(mContext).inflate(R.layout.item_weibo_realtimehot, parent, false);
                break;
            case BaseBean.WEIBO_NOVELTY:
                itemView = LayoutInflater.from(mContext).inflate(R.layout.item_weibo_novelty, parent, false);
                break;
            case BaseBean.ZHIHU_DAILY:
                itemView = LayoutInflater.from(mContext).inflate(R.layout.item_zhihu_daily, parent, false);
                break;
        }
        SubscribeViewHolder viewHolder = new SubscribeViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final SubscribeViewHolder holder, int position) {
        final FinestWebView.Builder builder = new FinestWebView.Builder(mContext)
                .stringResCopiedToClipboard(R.string.c_copied_to_clipboard)
                .stringResRefresh(R.string.c_refresh)
                .stringResShareVia(R.string.c_share_via)
                .stringResCopyLink(R.string.c_copy_link)
                .stringResOpenWith(R.string.c_open_with)
                .progressBarColor(mContext.getResources().getColor(R.color.colorGrey50))
                .swipeRefreshColor(mContext.getResources().getColor(R.color.colorPrimary))
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder.webViewMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        switch (holder.getItemViewType()) {
            case BaseBean.HUPU_VOTE:
                final HupuVoteBean hupuVoteBean = (HupuVoteBean) mList.get(position);
                holder.tvTitle.setText(hupuVoteBean.getTitle());
                break;
            case BaseBean.HUPU_BXJ:
                final HupuBxjBean hupuBxjBean = (HupuBxjBean) mList.get(position);
                holder.tvTitle.setText(hupuBxjBean.getTitle());
                holder.tvComment.setText(hupuBxjBean.getComment());
                break;
            case BaseBean.DOUBAN_MOVIE:
                final DoubanMovieBean doubanMovieBean = (DoubanMovieBean) mList.get(position);
                holder.tvTitle.setText(doubanMovieBean.getTitle());
                holder.tvActors.setText(doubanMovieBean.getActors());
                holder.tvDirector.setText(doubanMovieBean.getDirector());
                String score = "" + doubanMovieBean.getScore();
                holder.tvScore.setText(score);
                Picasso.with(mContext).load(doubanMovieBean.getImg()).into(holder.imgPreview);
                break;
            case BaseBean.DOUBAN_NEWBOOK:
                final DoubanNewBookBean doubanNewBookBean = (DoubanNewBookBean) mList.get(position);
                holder.tvTitle.setText(doubanNewBookBean.getTitle());
                holder.tvAuthor.setText(doubanNewBookBean.getAuthor());
                String bookScore = "" + doubanNewBookBean.getScore();
                holder.tvScore.setText(bookScore);
                Picasso.with(mContext).load(doubanNewBookBean.getImg()).into(holder.imgPreview);
                break;
            case BaseBean.DOUBAN_POPULARBOOK:
                final DoubanPopularBookBean doubanPopularBookBean = (DoubanPopularBookBean) mList.get(position);
                holder.tvTitle.setText(doubanPopularBookBean.getTitle());
                holder.tvAuthor.setText(doubanPopularBookBean.getAuthor());
                String popularScore = "" + doubanPopularBookBean.getScore();
                holder.tvScore.setText(popularScore);
                Picasso.with(mContext).load(doubanPopularBookBean.getImg()).into(holder.imgPreview);
                break;
            case BaseBean.DOUYU_GAME:
                final DouyuGameBean douyuGameBean = (DouyuGameBean) mList.get(position);
                holder.tvTitle.setText(douyuGameBean.getTitle());
                holder.tvAuthor.setText(douyuGameBean.getAuthor());
                holder.tvNum.setText(douyuGameBean.getNum());
                Picasso.with(mContext).load(douyuGameBean.getImg()).into(holder.imgPreview);
                break;
            case BaseBean.DOUYU_WZRY:
                final DouyuWzryBean douyuWzryBean = (DouyuWzryBean) mList.get(position);
                holder.tvTitle.setText(douyuWzryBean.getTitle());
                holder.tvAuthor.setText(douyuWzryBean.getAuthor());
                holder.tvNum.setText(douyuWzryBean.getNum());
                Picasso.with(mContext).load(douyuWzryBean.getImg()).into(holder.imgPreview);
                break;
            case BaseBean.WEIBO_REALTIMEHOT:
                final WeiboRealTimeHotBean weiboRealTimeHotBean = (WeiboRealTimeHotBean) mList.get(position);
                holder.tvTitle.setText(weiboRealTimeHotBean.getTitle());
                holder.tvSearchIndex.setText(weiboRealTimeHotBean.getSearchIndex());
                break;
            case BaseBean.WEIBO_NOVELTY:
                final WeiboNoveltyBean weiboNoveltyBean = (WeiboNoveltyBean) mList.get(position);
                holder.tvTitle.setText(weiboNoveltyBean.getTitle());
                Picasso.with(mContext).load(weiboNoveltyBean.getImg()).into(holder.imgPreview);
                break;
            case BaseBean.ZHIHU_DAILY:
                final ZhihuDailyBean zhihuDailyBean = (ZhihuDailyBean) mList.get(position);
                holder.tvTitle.setText(zhihuDailyBean.getTitle());
                Picasso.with(mContext).load(zhihuDailyBean.getImg()).into(holder.imgPreview);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mContentType;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public List<T> getList() {
        return mList;
    }

    public void setList(List<T> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public int getContentType() {
        return mContentType;
    }

    public void setContentType(int contentType) {
        mContentType = contentType;
    }
}

