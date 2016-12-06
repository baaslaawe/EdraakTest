package org.edraak.edraaktest.models.loaders;

import android.content.Context;

import org.edraak.edraaktest.models.managers.BestItemsManagers;
import org.edraak.edraaktest.models.thin.CourseModel;

import java.util.List;

public class Best5CategoriesLoader extends BestItemsLoader {

    /**
     * Create an instance
     *
     * @param context a context of the application package implementing
     *                this class.
     */
    public Best5CategoriesLoader(Context context) {
        super(context);
    }

    @Override
    protected List<CourseModel> getBestItemsList(BestItemsManagers bestItemsManagers) {
        return bestItemsManagers.getBest5CoursesBasedOnCategory();
    }
}
