package org.edraak.edraaktest.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.edraak.edraaktest.R;
import org.edraak.edraaktest.models.loaders.Best5CategoriesLoader;
import org.edraak.edraaktest.utils.RecyclerViewManager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Best5CategoriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Best5CategoriesFragment extends BaseFragment {

    private Best5CategoriesLoader best5CategoriesLoader;

    public Best5CategoriesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Best5CategoriesFragment.
     */
    public static Best5CategoriesFragment newInstance() {
        return new Best5CategoriesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        best5CategoriesLoader = new Best5CategoriesLoader(getContext());
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
        RecyclerView coursesList = (RecyclerView)
                rootView.findViewById(R.id.coursesList);

        RecyclerViewManager.setVerticalRecyclerView(coursesList,
                best5CategoriesLoader.getAdapter(),
                true,
                false);
    }

    @Override
    public void onStart() {
        super.onStart();

        best5CategoriesLoader.retrieve();
    }
}
