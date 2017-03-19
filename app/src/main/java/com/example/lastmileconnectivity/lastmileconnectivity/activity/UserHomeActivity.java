package com.example.lastmileconnectivity.lastmileconnectivity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.lastmileconnectivity.lastmileconnectivity.R;
import com.example.lastmileconnectivity.lastmileconnectivity.adpater.DrawerItemCustomAdapter;
import com.example.lastmileconnectivity.lastmileconnectivity.constant.SharePref;
import com.example.lastmileconnectivity.lastmileconnectivity.fragment.UserAccountInfoFragment;
import com.example.lastmileconnectivity.lastmileconnectivity.fragment.UserHistoryFragment;
import com.example.lastmileconnectivity.lastmileconnectivity.fragment.UserHomeFragment;
import com.example.lastmileconnectivity.lastmileconnectivity.fragment.UserWalletFragment;
import com.example.lastmileconnectivity.lastmileconnectivity.fragment.WebViewFragment;
import com.example.lastmileconnectivity.lastmileconnectivity.model.DataModel;
import com.kelltontech.ui.activity.BaseActivity;

import java.util.ArrayList;

public class UserHomeActivity extends BaseActivity implements UserHomeFragment.IUserHomeFragmentCallBack {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private Toolbar toolbar;
    private CharSequence mTitle;
    private ActionBarDrawerToggle mDrawerToggle;
    private ArrayList<DataModel> drawerItem=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        setupToolbar();

        mTitle= getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        drawerItem = new ArrayList<>();
        drawerItem.add(new DataModel(R.drawable.user, "Home"));
        drawerItem.add(new DataModel(R.drawable.user, "Account Info"));
        drawerItem.add(new DataModel(R.drawable.user, "History"));
        drawerItem.add(new DataModel(R.drawable.user, "Wallet Status"));
        drawerItem.add(new DataModel(R.drawable.user, "Data Statistics"));
        drawerItem.add(new DataModel(R.drawable.user, "Sign Out"));
        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.list_view_item_row, drawerItem);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setupDrawerToggle();
        selectItem(1);

    }

    @Override
    public void sendData(String source, String destination, String paymentOption) {
        startActivity(new Intent(UserHomeActivity.this,UserRideActivity.class));
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }

    }

    private void selectItem(int position) {
        if(position==0){
            return;
        }

        Fragment fragment = null;

        switch (position) {
            case 1:
                fragment = new UserHomeFragment();
                break;
            case 2:
                fragment = new UserAccountInfoFragment();
                break;
            case 3:
                fragment = new UserHistoryFragment();
                break;
            case 4:
                fragment = new UserWalletFragment();
                break;
            case 5:
                fragment = new WebViewFragment();
                break;
            case 6:
                SharePref.setUserLoggedIn(this,false);
                Intent intent = new Intent(UserHomeActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(drawerItem.get(position-1).name);
            mDrawerLayout.closeDrawer(mDrawerList);

        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    void setupToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.back_arrow);
            actionBar.setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
    }

    void setupDrawerToggle(){
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name, R.string.app_name);
        mDrawerToggle.syncState();
    }

    @Override
    public void updateUi(boolean status, int action, Object serviceResponse) {

    }

    @Override
    public void getData(int actionID) {

    }
}
