package org.edraak.edraaktest.models.loaders;

import android.content.Context;

import org.edraak.edraaktest.adapters.CoursesAdapter;
import org.edraak.edraaktest.models.managers.BestItemsManagers;
import org.edraak.edraaktest.models.thin.CourseModel;
import org.edraak.edraaktest.models.thin.CoursesContainerModel;

import java.util.List;

public abstract class BestItemsLoader {

    private CoursesCachingManager coursesCachingManager;
    private CoursesAdapter adapter;

    /**
     * Create an instance
     *
     * @param context a context of the application package implementing
     *                this class.
     */
    BestItemsLoader(Context context) {
        init(context);
    }

    private void init(Context context) {
        coursesCachingManager = new CoursesCachingManager(context);

        initAdapter(context);
    }

    private void initAdapter(Context context) {
        adapter = new CoursesAdapter(context);
    }

    /**
     * Read cached, if there is no cached data, retrieve data
     */
    public void retrieve() {
        adapter.resetItems();

        CoursesContainerModel coursesContainerModel = readCached();

        if (coursesContainerModel != null)
            useThisData(coursesContainerModel);
    }

    private CoursesContainerModel readCached() {
        return coursesCachingManager.readCourses();
    }

    private void useThisData(CoursesContainerModel response) {
        BestItemsManagers bestItemsManagers =
                new BestItemsManagers(response.getResults());

        List<CourseModel> bestItems = getBestItemsList(bestItemsManagers);

        adapter.addItems(bestItems);
    }

    protected abstract List<CourseModel>
    getBestItemsList(BestItemsManagers bestItemsManagers);

    public CoursesAdapter getAdapter() {
        return adapter;
    }
}

