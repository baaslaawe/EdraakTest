package org.edraak.edraaktest.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.edraak.edraaktest.R;
import org.edraak.edraaktest.models.loaders.CoursesLoader;

public class MainActivity extends BaseActivity {

    private CoursesLoader coursesLoader;

    /**
     * Use this factory method to create a new instance of {@link Intent}
     * related to this activity
     *
     * @param context a Context of the application package implementing this class.
     * @return a new Intent instance
     */
    public static Intent getIntentInstance(Context context) {
        return new Intent(context.getApplicationContext(), MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        coursesLoader = new CoursesLoader(this);

        initViews();
    }

    @Override
    protected void initViews() {
        initCoursesList();
    }

    private void initCoursesList() {
        RecyclerView coursesList = (RecyclerView) findViewById(R.id.coursesList);

        coursesList.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        coursesList.setLayoutManager(layoutManager);

        coursesList.setAdapter(coursesLoader.getCoursesAdapter());
    }

    @Override
    protected void onStart() {
        super.onStart();

        coursesLoader.readCachedOrRetrieve();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_refresh) {
            refreshCourses();

            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void refreshCourses() {
        coursesLoader.retrieve();
    }
}
