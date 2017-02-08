package com.wgh.simplenews.news;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wgh.simplenews.R;
import com.wgh.simplenews.base.BaseFragment;
import com.wgh.simplenews.news.widget.NewsListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @version V1.0.0
 * @author: guanghui_wan
 * @date: 2017/1/18
 */
public class NewsFragment extends BaseFragment{

    public static final int NEWS_TYPE_TOP = 0;
    public static final int NEWS_TYPE_NBA = 1;
    public static final int NEWS_TYPE_CARS = 2;
    public static final int NEWS_TYPE_JOKES = 3;

    @Bind(R.id.tablayout) TabLayout mTabLayout;
    @Bind(R.id.viewpager) ViewPager mViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news,null);
        return view;
    }

    @Override
    protected void registerListener() {

    }

    @Override
    protected void initViews() {
        mViewPager.setOffscreenPageLimit(3);
        setupViewPager();
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.top));
//        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.nba));
//        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.cars));
//        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.jokes));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void initData() {

    }


    private void setupViewPager() {
        MyPagerAdapter adapter = new MyPagerAdapter(getChildFragmentManager());
        adapter.addFragment(NewsListFragment.getInstance(NEWS_TYPE_TOP),getString(R.string.top));
//        adapter.addFragment(NewsListFragment.getInstance(NEWS_TYPE_NBA),getString(R.string.nba));
//        adapter.addFragment(NewsListFragment.getInstance(NEWS_TYPE_CARS),getString(R.string.cars));
//        adapter.addFragment(NewsListFragment.getInstance(NEWS_TYPE_JOKES),getString(R.string.jokes));
        mViewPager.setAdapter(adapter);

    }

    public class MyPagerAdapter extends FragmentPagerAdapter{

        private List<Fragment> mFragments = new ArrayList<>();
        private List<String> mFragmentTitles = new ArrayList<>();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(@NonNull Fragment fragment,@NonNull String title){
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
