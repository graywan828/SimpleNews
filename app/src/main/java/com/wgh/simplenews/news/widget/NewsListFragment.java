package com.wgh.simplenews.news.widget;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wgh.simplenews.R;
import com.wgh.simplenews.base.BaseFragment;
import com.wgh.simplenews.beans.NewsBean;
import com.wgh.simplenews.network.OkHttpUtils;
import com.wgh.simplenews.network.Urls;
import com.wgh.simplenews.news.NewsAdapter;
import com.wgh.simplenews.news.NewsFragment;
import com.wgh.simplenews.news.view.NewsView;
import com.wgh.simplenews.utils.LogUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsListFragment extends BaseFragment implements NewsView, SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "NewsListFragment";

    @Bind(R.id.swipe_refresh_layout) SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.recycler_view) RecyclerView mRecyclerView;

    private LinearLayoutManager mLayoutManager;
    private NewsAdapter mAdapter;

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

                    requestNews();

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            }
        });
    }

    private void requestNews() {
        subscription = OkHttpUtils.getInstance().getNewsApi()
                .getNews(Urls.TOP_PATH,Urls.TOP_ID,pageIndex)
                .map(new Func1<String, List<NewsBean>>() {
                    @Override
                    public List<NewsBean> call(String s) {

                        return null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObserver);
    }

    Observer<List<NewsBean>> mObserver = new Observer<List<NewsBean>>() {
        @Override
        public void onCompleted() {
            hideProgress();
            LogUtils.e("onCompleted");
        }

        @Override
        public void onError(Throwable e) {
            hideProgress();
            LogUtils.e("onError  "  + e.getMessage());
        }

        @Override
        public void onNext(List<NewsBean> newsBeanList) {
            hideProgress();
        }

    };

    @Override
    protected void initViews() {
        mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE,Color.RED,
                Color.YELLOW);
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new NewsAdapter(mContext);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
//        onRefresh();
            requestNews();
    }

    @Override
    public void addNews(List<NewsBean> newsList) {

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

    }
}
