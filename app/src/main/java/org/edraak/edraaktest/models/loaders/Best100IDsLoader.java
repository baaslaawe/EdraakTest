package org.edraak.edraaktest.models.loaders;

import android.content.Context;

import org.edraak.edraaktest.adapters.CoursesAdapter;
import org.edraak.edraaktest.models.managers.BestItemsManagers;
import org.edraak.edraaktest.models.thin.CoursesContainerModel;

public class Best100IDsLoader {

    private CoursesCachingManager coursesCachingManager;
    private CoursesAdapter best100IdsAdapter;

    /**
     * Create an instance
     *
     * @param context a context of the application package implementing
     *                this class.
     */
    public Best100IDsLoader(Context context) {
        init(context);
    }

    private void init(Context context) {
        coursesCachingManager = new CoursesCachingManager(context);

        initAdapter(context);
    }

    private void initAdapter(Context context) {
        best100IdsAdapter = new CoursesAdapter(context);
    }

    /**
     * Read cached, if there is no cached data, retrieve data
     */
    public void retrieve() {
        best100IdsAdapter.resetItems();

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

        best100IdsAdapter.addItems(bestItemsManagers.getBest100CoursesBasedOnId());
    }

    public CoursesAdapter getBest100IdsAdapter() {
        return best100IdsAdapter;
    }
}
