package org.edraak.edraaktest.models.loaders;

import android.content.Context;

import org.edraak.edraaktest.adapters.CoursesAdapter;
import org.edraak.edraaktest.models.services.CoursesService;
import org.edraak.edraaktest.models.thin.CoursesContainerModel;

public class CoursesLoader extends LoaderRequestManager
        <CoursesService, CoursesContainerModel> {

    private static final int LIMIT = 20;

    private CoursesContainerModel coursesContainerModel;
    private CoursesAdapter coursesAdapter;
    private boolean hasToRenew = true;

    /**
     * Create an instance
     *
     * @param context a context of the application package implementing
     *                this class.
     */
    public CoursesLoader(Context context) {
        super(context, CoursesService.class);

        init(context);
    }

    private void init(Context context) {
        coursesAdapter = new CoursesAdapter(context);
    }

    @Override
    protected void onResponse(CoursesContainerModel response) {
        super.onResponse(response);

        this.coursesContainerModel = response;

        if (hasToRenew)
            coursesAdapter.resetItems();

        coursesAdapter.addItems(response.getResults());
    }

    /**
     * Retrieve data and clear the old
     */
    public void retrieve() {
        this.hasToRenew = true;
        this.coursesContainerModel = null;

        retrieveFromSource();
    }

    /**
     * Retrieve the next page based on the next offset
     */
    public void retrieveNext() {
        this.hasToRenew = false;

        retrieveFromSource();
    }

    private void retrieveFromSource() {
        this.hasToRenew = true;

        defineMethod();

        enqueue();
    }

    private void defineMethod() {
        if (thereIsNext())
            setCaller(getService().getCourses(LIMIT,
                    coursesContainerModel.getNextOffset()));

        else
            setCaller(getService().getCourses(LIMIT));
    }

    private boolean thereIsNext() {
        return coursesContainerModel != null
                && coursesContainerModel.isValidNextOffset();
    }

    public CoursesAdapter getCoursesAdapter() {
        return coursesAdapter;
    }
}
