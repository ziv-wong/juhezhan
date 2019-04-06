package com.ziv.juhezhan.recyclerview.classify;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.ziv.juhezhan.R;

class SubClassifyViewHolder extends RecyclerView.ViewHolder {

    TextView tvSubTitle;
    Button   btnSubTitle;


    public SubClassifyViewHolder(View itemView) {
        super(itemView);
        tvSubTitle = (TextView) itemView.findViewById(R.id.tvSubTitle);
        btnSubTitle = (Button) itemView.findViewById(R.id.btnSubTitle);
    }
}