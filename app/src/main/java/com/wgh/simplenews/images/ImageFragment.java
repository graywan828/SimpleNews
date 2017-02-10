package com.wgh.simplenews.images;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wgh.simplenews.R;
import com.wgh.simplenews.base.MvpFragment;
import com.wgh.simplenews.images.presenter.ImagePresenter;
import com.wgh.simplenews.images.view.ImagesView;

import butterknife.Bind;

/**
 * @version V1.0.0
 * @author: guanghui_wan
 * @date: 2017/2/10
 * @description
 */

public class ImageFragment extends MvpFragment<ImagePresenter> implements ImagesView, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private LinearLayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, null);
        return view;
    }

    @Override
    protected ImagePresenter createPresenter() {
        return new ImagePresenter(this);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void registerListener() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void initData() {
        mvpPresenter.loadImageData();
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
    public void onRefresh() {

    }
}
