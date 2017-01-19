package com.wgh.simplenews.news.widget;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wgh.simplenews.R;
import com.wgh.simplenews.base.BaseFragment;
import com.wgh.simplenews.news.NewsFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsListFragment extends BaseFragment {

    private static final String TAG = "NewsListFragment";

    @Bind(R.id.swipe_refresh_layout) SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.recycler_view) RecyclerView mRecyclerView;


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

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {

    }
}
