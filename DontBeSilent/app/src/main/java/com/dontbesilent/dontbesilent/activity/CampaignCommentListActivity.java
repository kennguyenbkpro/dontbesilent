package com.dontbesilent.dontbesilent.activity;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.dontbesilent.dontbesilent.R;
import com.dontbesilent.dontbesilent.adapter.CampaignCommentAdapter;
import com.dontbesilent.dontbesilent.util.Utils;

public class CampaignCommentListActivity extends AppCompatActivity {

    private RecyclerView mRvComments;
    private LinearLayoutManager mRvCommentsLayoutManager;
    private CampaignCommentAdapter mCampaignCommentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign_comment_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRvComments = (RecyclerView) findViewById(R.id.rv_campaign_comment_list);
        mRvCommentsLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mCampaignCommentAdapter = new CampaignCommentAdapter();
        mRvComments.setLayoutManager(mRvCommentsLayoutManager);
        mRvComments.setAdapter(mCampaignCommentAdapter);
//        mRvComments.addItemDecoration(new RecyclerView.ItemDecoration() {
//            @Override
//            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//                if (parent.findContainingViewHolder(view).getAdapterPosition() > 0) {
//                    outRect.top = Utils.dpToPx(2);
//                }
//            }
//        });
    }
}
