package com.wgh.simplenews.main.widget;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.wgh.simplenews.R;
import com.wgh.simplenews.base.BaseActivity;
import com.wgh.simplenews.main.presenter.MainPresenter;
import com.wgh.simplenews.main.view.MainView;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements MainView{

    @Bind(R.id.drawer_layout)DrawerLayout mDrawerLayout;
    @Bind(R.id.appbar)Toolbar mToolbar;
    @Bind(R.id.navigation_view) private NavigationView mNavigationView;
    private ActionBarDrawerToggle mDrawerToggle;
    private MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {
        setSupportActionBar(mToolbar);

        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,R.string.drawer_open,R.string.drawer_close);
        setupDrawerContent(mNavigationView);

        mMainPresenter = new MainPresenter(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mMainPresenter.switchNavigation(item.getItemId());
                item.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public void switch2News() {

    }

    @Override
    public void switch2Images() {

    }

    @Override
    public void switch2Weather() {

    }

    @Override
    public void switch2About() {

    }
}
