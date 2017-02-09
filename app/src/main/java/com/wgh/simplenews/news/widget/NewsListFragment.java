package com.wgh.simplenews.news.widget;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wgh.simplenews.R;
import com.wgh.simplenews.base.MvpFragment;
import com.wgh.simplenews.beans.NewsBean;
import com.wgh.simplenews.network.Urls;
import com.wgh.simplenews.news.NewsAdapter;
import com.wgh.simplenews.news.presenter.NewsPresenter;
import com.wgh.simplenews.news.view.NewsView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsListFragment extends MvpFragment<NewsPresenter> implements NewsView, SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "NewsListFragment";

    @Bind(R.id.swipe_refresh_layout) SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.recycler_view) RecyclerView mRecyclerView;

    private LinearLayoutManager mLayoutManager;
    private NewsAdapter mAdapter;

    private List<NewsBean> mData;

    private int mType = NewsFragment.NEWS_TYPE_TOP;
    private int pageIndex = 0;

    public static NewsListFragment getInstance(int type) {
        Bundle args = new Bundle();
        NewsListFragment fragment = new NewsListFragment();
        args.putInt("type",type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = getArguments().getInt("type");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    protected void registerListener() {

        mSwipeRefreshLayout.setOnRefreshListener(this);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            private int lastVisibleItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == RecyclerView.SCROLL_STATE_IDLE
                        &&lastVisibleItem + 1 == mAdapter.getItemCount()
                        &&mAdapter.isShowFooter()){

                    mvpPresenter.loadNews(pageIndex);

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            }
        });


    }

    @Override
    protected NewsPresenter createPresenter() {
        return new NewsPresenter(this,mType);
    }

    @Override
    protected void initViews() {
        mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE,Color.RED,
                Color.YELLOW);
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new NewsAdapter(mContext);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(mOnItemClickListener);
    }

    NewsAdapter.OnItemClickListener mOnItemClickListener = new NewsAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            NewsBean bean = mAdapter.getItem(position);
            Intent intent = new Intent(mContext,NewsDetailActivity.class);
            intent.putExtra("news",bean);

            View transitionView = view.findViewById(R.id.ivNews);
            ActivityOptionsCompat options = ActivityOptionsCompat
                    .makeSceneTransitionAnimation(mContext,transitionView,getString(R.string.transition_news_img));
            ActivityCompat.startActivity(mContext,intent,options.toBundle());
        }
    };

    @Override
    protected void initData() {
//        onRefresh();
        mvpPresenter.loadNews(pageIndex);
    }

    @Override
    public void addNews(List<NewsBean> newsList) {

        mAdapter.isShowFooter(true);
        if(mData == null){
            mData = new ArrayList<>();
        }

        mData.addAll(newsList);

        if(pageIndex == 0){
            mAdapter.setmDate(mData);
        }else {
            if(newsList ==null&& newsList.size() == 0){
                mAdapter.isShowFooter(false);
            }
            mAdapter.notifyDataSetChanged();
        }
        pageIndex += Urls.PAZE_SIZE;
    }

    @Override
    public void showProgress() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showLoadFailMsg() {

    }

    @Override
    public void onRefresh() {

        pageIndex = 0;
        if(mData !=null){
            mData.clear();
        }
        mvpPresenter.loadNews(pageIndex);
    }

}
