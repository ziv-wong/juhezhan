package com.ziv.juhezhan.recyclerview.subscribe;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ziv.juhezhan.R;

class SubscribeViewHolder extends RecyclerView.ViewHolder {

    RelativeLayout layoutItem;
    TextView       tvTitle;
    TextView       tvDirector;
    TextView       tvActors;
    TextView       tvScore;
    ImageView      imgPreview;
    TextView       tvAuthor;
    TextView       tvNum;
    TextView       tvSearchIndex;
    TextView       tvComment;


    public SubscribeViewHolder(View itemView) {
        super(itemView);
        layoutItem = (RelativeLayout) itemView.findViewById(R.id.layout_item);
        tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        tvDirector = (TextView) itemView.findViewById(R.id.tvDirector);
        tvActors = (TextView) itemView.findViewById(R.id.tvActors);
        tvScore = (TextView) itemView.findViewById(R.id.tvScore);
        imgPreview = (ImageView) itemView.findViewById(R.id.imgPreview);
        tvAuthor = (TextView) itemView.findViewById(R.id.tvAuthor);
        tvNum = (TextView) itemView.findViewById(R.id.tvNum);
        tvSearchIndex = (TextView) itemView.findViewById(R.id.tvSearchIndex);
        tvComment = (TextView) itemView.findViewById(R.id.tvComment);
    }
}