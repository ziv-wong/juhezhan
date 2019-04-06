package com.ziv.juhezhan.recyclerview.classify;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ziv.juhezhan.R;
import com.ziv.juhezhan.data.DataBaseUtil;
import com.ziv.juhezhan.setting.CatalogSubSetting;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;


public class SubClassifyListAdapter extends RecyclerView.Adapter<SubClassifyViewHolder> {

    private Context                 mContext;
    private String                  mClassifyName;
    private List<CatalogSubSetting> mSubClassifies;
    private int                     mType;
    private OnBtnClickListener      mOnBtnClickListener;
    public SubClassifyListAdapter(Context context, String classifyName, List<CatalogSubSetting> subClassifies, int type) {
        mContext = context;
        mClassifyName = classifyName;
        mSubClassifies = subClassifies;
        mType = type;

    }

    @Override
    public SubClassifyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.item_classify_sub, parent, false);
        SubClassifyViewHolder viewHolder = new SubClassifyViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final SubClassifyViewHolder holder, final int position) {
        CatalogSubSetting bean = mSubClassifies.get(position);
        holder.tvSubTitle.setText(bean.getName());
        final Button button = holder.btnSubTitle;
        switch (mType) {
            case ClassifyListAdapter.DISCOVER:
                button.setText(mContext.getString(R.string.subscribe));
                break;
            case ClassifyListAdapter.SETTING:
                button.setText(mContext.getString(R.string.unsubsribe));
                break;
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setClickable(false);
                int position = holder.getAdapterPosition();
                try {
                    // Modify User Data
                    DataBaseUtil.getInstance(mContext).modifyDataBase(
                            mClassifyName, mSubClassifies.get(position).getName(), mType);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
                // Update View
                if (mOnBtnClickListener != null) {
                    mOnBtnClickListener.onBtnClick(mSubClassifies.get(position).getName(), mType);
                }
                mSubClassifies.remove(position);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return mSubClassifies.size();
    }

    public List<CatalogSubSetting> getSubClassifies() {
        return mSubClassifies;
    }

    public void setSubClassifies(List<CatalogSubSetting> subClassifies) {
        mSubClassifies = subClassifies;
        notifyDataSetChanged();
    }

    public OnBtnClickListener getOnBtnClickListener() {
        return mOnBtnClickListener;
    }

    public void setOnBtnClickListener(OnBtnClickListener onBtnClickListener) {
        mOnBtnClickListener = onBtnClickListener;
    }

    public interface OnBtnClickListener {
        boolean onBtnClick(String subName, int type);
    }

}

