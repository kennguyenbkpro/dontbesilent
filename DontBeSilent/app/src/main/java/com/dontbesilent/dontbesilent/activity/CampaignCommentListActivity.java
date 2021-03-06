package com.dontbesilent.dontbesilent.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.dontbesilent.dontbesilent.R;
import com.dontbesilent.dontbesilent.adapter.CampaignCommentAdapter;
import com.dontbesilent.dontbesilent.util.DummyDataUtils;

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
        getSupportActionBar().setTitle(getString(R.string.title_comment));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

        if (DummyDataUtils.mComments == null || DummyDataUtils.mComments.isEmpty()) {
            DummyDataUtils.getDummyComments();
        }
        mCampaignCommentAdapter.setData(DummyDataUtils.mComments);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
