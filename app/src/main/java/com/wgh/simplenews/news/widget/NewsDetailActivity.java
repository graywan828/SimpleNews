package com.wgh.simplenews.news.widget;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wgh.simplenews.R;
import com.wgh.simplenews.base.MvpActivity;
import com.wgh.simplenews.beans.NewsBean;
import com.wgh.simplenews.news.presenter.NewsDetailPresenter;
import com.wgh.simplenews.news.view.NewsDetailView;
import com.wgh.simplenews.utils.GlideUtils;
import com.wgh.simplenews.utils.LogUtils;

import butterknife.Bind;

public class NewsDetailActivity extends MvpActivity<NewsDetailPresenter> implements NewsDetailView {

    @Bind(R.id.progress)
    ProgressBar mProgressBar;
    @Bind(R.id.tv_content)
    TextView mTextView;
    @Bind(R.id.toolbar) Toolbar mToolbar;
    private NewsBean mNewsBean;
    @Bind(R.id.collapsing_toolbar) CollapsingToolbarLayout mCollapsingToolbarLayout;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_news_detail);
    }

    @Override
    protected void initView() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void registerListener() {

    }

    @Override
    protected void initData() {
        mNewsBean = (NewsBean) getIntent().getSerializableExtra("news");

        mCollapsingToolbarLayout.setTitle(mNewsBean.getTitle());

        GlideUtils.display(mActivity, (ImageView) findViewById(R.id.ivNews),mNewsBean.getImgsrc());

        mvpPresenter.loadNewsDetail(mNewsBean.getDocid());
    }

    @Override
    protected NewsDetailPresenter createPresenter() {
        LogUtils.e("createPresenter");

        return new NewsDetailPresenter(this);
    }

    @Override
    public void showNewsDetailContent(String newsDetailContent) {
        mTextView.setText(Html.fromHtml(newsDetailContent));
    }

    @Override
    public void showProgress() {
        mProgressBar.setProgress(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }
}
