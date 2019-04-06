package com.ziv.juhezhan.recyclerview.classify;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ziv.juhezhan.R;

class ClassifyViewHolder extends RecyclerView.ViewHolder {

    TextView     tvTitle;
    RecyclerView listClassifySub;


    public ClassifyViewHolder(View itemView) {
        super(itemView);
        tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        listClassifySub = (RecyclerView) itemView.findViewById(R.id.listClassifySub);
    }
}