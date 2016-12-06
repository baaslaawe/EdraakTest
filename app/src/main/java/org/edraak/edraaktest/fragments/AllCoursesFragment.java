package org.edraak.edraaktest.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import org.edraak.edraaktest.R;
import org.edraak.edraaktest.models.loaders.CoursesLoader;
import org.edraak.edraaktest.utils.RecyclerViewManager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AllCoursesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllCoursesFragment extends BaseFragment {

    private CoursesLoader coursesLoader;

    public AllCoursesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AllCoursesFragment.
     */
    public static AllCoursesFragment newInstance() {
        return new AllCoursesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        coursesLoader = new CoursesLoader(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list, container, false);
        initViews(rootView);

        return rootView;
    }

    @Override
    protected void initViews(View rootView) {
        RecyclerView coursesList = (RecyclerView) rootView.findViewById(R.id.coursesList);

        RecyclerViewManager.setVerticalRecyclerView(coursesList,
                coursesLoader.getAdapter(),
                true,
                false);
    }

    @Override
    public void onStart() {
        super.onStart();

        coursesLoader.readCachedOrRetrieve();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.all_courses, menu);
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
