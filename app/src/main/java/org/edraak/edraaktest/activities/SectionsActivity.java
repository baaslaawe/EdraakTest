package org.edraak.edraaktest.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import org.edraak.edraaktest.R;
import org.edraak.edraaktest.fragments.AllCoursesFragment;

public class SectionsActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * Use this factory method to create a new instance of {@link Intent}
     * related to this activity
     *
     * @param context a Context of the application package implementing this class.
     * @return a new Intent instance
     */
    public static Intent getIntentInstance(Context context) {
        return new Intent(context.getApplicationContext(), SectionsActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sections);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        initViews();

        showAllCoursesSection();
    }

    @Override
    protected void initViews() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_all_courses) {
            showAllCoursesSection();

        } else if (id == R.id.nav_best_100_ids) {
            showBest100IDsSection();

        } else if (id == R.id.nav_best_5_categories) {
            showBest5Categories();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showAllCoursesSection() {
        changeFragment(AllCoursesFragment.newInstance());
    }

    private void showBest100IDsSection() {

    }

    private void showBest5Categories() {

    }

    private void changeFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_sections, fragment)
                .commit();
    }
}
