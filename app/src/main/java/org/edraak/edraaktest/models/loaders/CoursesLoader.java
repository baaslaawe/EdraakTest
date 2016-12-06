package org.edraak.edraaktest.models.loaders;

import android.content.Context;

import org.edraak.edraaktest.adapters.BaseListAdapter;
import org.edraak.edraaktest.adapters.CoursesAdapter;
import org.edraak.edraaktest.models.services.CoursesService;
import org.edraak.edraaktest.models.thin.CourseModel;
import org.edraak.edraaktest.models.thin.CoursesContainerModel;

import java.util.List;

/**
 * The courses loader that gets data then change it to be ready
 * to connecting with the view
 */
public class CoursesLoader extends LoaderRequestManager
        <CoursesService, CoursesContainerModel> {

    private static final long DEFAULT_OFFSET = 0;
    private static final int LIMIT = 10;

    private long nextOffset = DEFAULT_OFFSET;
    private CoursesCachingManager coursesCachingManager;

    private CoursesAdapter adapter;

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
        coursesCachingManager = new CoursesCachingManager(context);

        initAdapter(context);
    }

    private void initAdapter(Context context) {
        adapter = new CoursesAdapter(context);
        adapter.setEndless(true);

        adapter.setOnLastItemViewedListener(new BaseListAdapter.OnLastItemViewedListener() {
            @Override
            public void onLastItemViewed(int position) {
                checkToRetrieveNext();
            }
        });
    }

    @Override
    protected void onResponse(CoursesContainerModel response) {
        super.onResponse(response);

        useThisData(response);

        cacheToFile(response, adapter.getList());
    }

    private void useThisData(CoursesContainerModel response) {
        if (hasToRenew)
            adapter.resetItems();

        adapter.addItems(response.getResults());

        this.nextOffset = response.getMeta().getNextOffset();

        adapter.setIsLoadingMore(false);
    }

    private void cacheToFile(CoursesContainerModel response,
                             List<CourseModel> newList) {
        coursesCachingManager.combineAndSave(response, newList);
    }

    private void checkToRetrieveNext() {
        if (!adapter.isLoadingMore()) {
            retrieveNext();
            adapter.setIsLoadingMore(true);
        }
    }

    /**
     * Read cached, if there is no cached data, retrieve data
     */
    public void readCachedOrRetrieve() {
        CoursesContainerModel coursesContainerModel = readCached();

        if (coursesContainerModel == null)
            retrieve();
        else
            useThisData(coursesContainerModel);
    }

    private CoursesContainerModel readCached() {
        return coursesCachingManager.readCourses();
    }

    /**
     * Retrieve data and clear the old
     */
    public void retrieve() {
        adapter.resetItems();

        this.hasToRenew = true;
        this.nextOffset = DEFAULT_OFFSET;

        retrieveFromSource();
    }

    /**
     * Retrieve the next page based on the next offset
     */
    private void retrieveNext() {
        this.hasToRenew = false;

        retrieveFromSource();
    }

    private void retrieveFromSource() {
        defineMethod();

        enqueue();
    }

    private void defineMethod() {
        if (thereIsNext())
            setCaller(getService().getCourses(LIMIT, nextOffset));

        else
            setCaller(getService().getCourses(LIMIT));
    }

    private boolean thereIsNext() {
        return nextOffset != DEFAULT_OFFSET;
    }

    public CoursesAdapter getAdapter() {
        return adapter;
    }
}
