package com.lossfinder.app.activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import com.lossfinder.app.R;
import com.lossfinder.app.constant.Constant;
import com.lossfinder.app.fragment.CategoryFragment;
import com.lossfinder.app.fragment.MyAdsFragment;
import com.lossfinder.app.fragment.PostFragment;
import com.lossfinder.app.fragment.TypeFragment;
import com.lossfinder.app.listener.NavigationHeaderOnClickListener;
import com.lossfinder.app.listener.OnCategorySelectedListener;

public class MainActivity extends AppCompatActivity implements OnCategorySelectedListener {

    private static long backPressed;

    private FragmentManager manager;
    private CategoryFragment categoryFragment;

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private CharSequence toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(Constant.MAIN_LAYOUT);

        manager = getSupportFragmentManager();
        categoryFragment = new CategoryFragment();

        initToolbar();

        //First start load CategoryFragment
        if (savedInstanceState == null) {
            initCategoryFragment();
        }

        initNavigation();
    }

    /**
     * Replacing fragment when choosing a category
     */
    @Override
    public void onCategorySelected() {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(Constant.MAIN_CONTENT_CONTAINER, new TypeFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(Constant.MAIN_TOOLBAR);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setTitle(R.string.app_name);
            toolbar.setNavigationIcon(Constant.MAIN_ICON_DRAWER);
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        toolbarTitle = title;
        getSupportActionBar().setTitle(toolbarTitle);
    }

    private void initNavigation() {
        toolbarTitle = getTitle();
        drawerLayout = (DrawerLayout) findViewById(Constant.MAIN_DRAWER_LAYOUT);

        toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        ) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                supportInvalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                supportInvalidateOptionsMenu();
            }
        };

        toggle.setDrawerIndicatorEnabled(true);
        drawerLayout.setDrawerListener(toggle);

        NavigationView nvDrawer = (NavigationView) findViewById(Constant.MAIN_NAVIGATION_VIEW);
        nvDrawer.setNavigationItemSelectedListener(new DrawerMenuItemClickListener());

        //The click listener for Header in the navigation drawer
        View navHeader = nvDrawer.getHeaderView(0);
        LinearLayout linearLayout = (LinearLayout) navHeader.findViewById(Constant.MAIN_NAVIGATION_HEADER_LINEAR_LAYOUT);
        linearLayout.setOnClickListener(new NavigationHeaderOnClickListener(this));
    }

    private void initCategoryFragment() {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(Constant.MAIN_CONTENT_CONTAINER, categoryFragment);
        transaction.commit();
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred
        toggle.syncState();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        toggle.onConfigurationChanged(newConfig);

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

//            if (backPressed + 2000 > System.currentTimeMillis())
//         else {
//            Toast.makeText(getBaseContext(), "Нажмите еще раз для выхода", Toast.LENGTH_SHORT).show();
//            backPressed = System.currentTimeMillis();
//        }
    }


    /**
     * The click listener for Menu in the navigation drawer
     */
    private class DrawerMenuItemClickListener implements NavigationView.OnNavigationItemSelectedListener {

        @Override
        public boolean onNavigationItemSelected(MenuItem menuItem) {
            // Update the main content by replacing fragments
            Fragment fragment = null;
            switch (menuItem.getItemId()) {
                case R.id.nav_menu_post:
                    fragment = new PostFragment();
                    break;
                case R.id.nav_menu_search:
                    fragment = categoryFragment;
                    break;
                case R.id.nav_menu_my_ads:
                    fragment = new MyAdsFragment();
                    break;
            }

            // Insert the fragment by replacing any existing fragment
            if (fragment != null) {
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(Constant.MAIN_CONTENT_CONTAINER, fragment);
                transaction.commit();

                // Highlight the selected item, update the title, and close the drawer
                menuItem.setChecked(true);
                setTitle(menuItem.getTitle());

                drawerLayout.closeDrawer(GravityCompat.START);
            }
            return true;
        }
    }


}




