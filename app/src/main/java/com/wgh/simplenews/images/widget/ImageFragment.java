package com.wgh.simplenews.images.widget;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wgh.simplenews.R;
import com.wgh.simplenews.base.MvpFragment;
import com.wgh.simplenews.beans.ImageBean;
import com.wgh.simplenews.images.ImageAdapter;
import com.wgh.simplenews.images.presenter.ImagePresenter;
import com.wgh.simplenews.images.view.ImagesView;

import java.util.ArrayList;
import java.util.List;

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
    private ImageAdapter mAdapter;
    private List<ImageBean> mImageBeanList;

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
        mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE,Color.YELLOW,Color.RED);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new ImageAdapter(mContext);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void registerListener() {
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        mAdapter.setOnItemCLickListener(new ImageAdapter.OnItemCLickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
    }

    @Override
    protected void initData() {
        onRefresh();
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
    public void addImages(List<ImageBean> images) {
        if(mImageBeanList == null){
            mImageBeanList = new ArrayList<>();
        }

        mImageBeanList.addAll(images);
        mAdapter.setData(mImageBeanList);
    }

    @Override
    public void onRefresh() {
        if(mImageBeanList != null){
            mImageBeanList.clear();
        }
        mvpPresenter.loadImageData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRecyclerView.clearOnScrollListeners();
    }
}
