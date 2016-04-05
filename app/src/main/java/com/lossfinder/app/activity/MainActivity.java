package com.lossfinder.app.activity;

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
import com.lossfinder.app.R;
import com.lossfinder.app.fragment.*;
import com.lossfinder.app.interfaces.OnCategorySelectedListener;

public class MainActivity extends AppCompatActivity implements OnCategorySelectedListener {

    private static final int LAYOUT = R.layout.activity_main;
    private static final int THEME = R.style.AppTheme;
    private static final int CONTENT_CONTAINER = R.id.MainContent;
    private static long backPressed;

    private FragmentManager manager;
    private CategoryFragment categoryFragment;
    private FragmentTransaction transaction;
    private TypeFragment typeFragment;

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private CharSequence toolbarTitle;
    private NavigationView nvDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(THEME);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        manager = getSupportFragmentManager();
        typeFragment = new TypeFragment();
        categoryFragment = new CategoryFragment();

        initToolbar();

        initNavigation();

        //First start load CategoryFragment
        if (savedInstanceState == null) {
            initCategoryFragment();
        }
    }


    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setTitle(R.string.app_name);
            toolbar.setNavigationIcon(R.mipmap.ic_drawer);
        }
    }

    private void initCategoryFragment() {
        transaction = manager.beginTransaction();
        transaction.add(CONTENT_CONTAINER, categoryFragment);
        transaction.commit();
    }

    private void initNavigation() {
        toolbarTitle = getTitle();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

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

        nvDrawer = (NavigationView) findViewById(R.id.navigationDrawer);
        nvDrawer.setNavigationItemSelectedListener(new DrawerMenuItemClickListener());
    }

    /**
     * Replacing fragment when choosing a category
     */
    @Override
    public void OnCategorySelected() {
        transaction = manager.beginTransaction();
        transaction.replace(CONTENT_CONTAINER, typeFragment);
        transaction.addToBackStack(null);
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
                case R.id.navMenuPost:
                    fragment = new PostFragment();
                    break;
                case R.id.navMenuSearch:
                    fragment = new SearchFragment();
                    break;
                case R.id.navMenuMyAds:
                    fragment = new MyAdsFragment();
                    break;
            }

            // Insert the fragment by replacing any existing fragment
            if (fragment != null) {
                transaction = manager.beginTransaction();
                transaction.replace(CONTENT_CONTAINER, fragment);
                transaction.commit();

                // Highlight the selected item, update the title, and close the drawer
                menuItem.setChecked(true);
                setTitle(menuItem.getTitle());
                drawerLayout.closeDrawer(GravityCompat.START);
            }
            return true;
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        toolbarTitle = title;
        getSupportActionBar().setTitle(toolbarTitle);
    }
}




