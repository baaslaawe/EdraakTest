package org.edraak.edraaktest.models.loaders;

import android.content.Context;

import org.edraak.edraaktest.managers.FilesCachingManager;
import org.edraak.edraaktest.models.thin.CourseModel;
import org.edraak.edraaktest.models.thin.CoursesContainerModel;
import org.edraak.edraaktest.utils.Parser;

import java.util.List;

/**
 * The courses manager for read / write courses to JSON file
 */
public class CoursesCachingManager {

    private static final String FILE_NAME = "courses.json";

    private FilesCachingManager filesCachingManager;

    /**
     * Create an instance
     *
     * @param context a context of the application package implementing
     *                this class.
     */
    public CoursesCachingManager(Context context) {
        this.filesCachingManager = new FilesCachingManager(context);
        this.filesCachingManager.setFileName(FILE_NAME);
    }

    /**
     * Combine the courses container object with the new list, the save the object to the JSON file
     *
     * @param coursesContainerModel the used courses container object
     * @param newList               the new courses list
     */
    public void combineAndSave(CoursesContainerModel coursesContainerModel,
                               List<CourseModel> newList) {
        coursesContainerModel.setResults(newList);

        saveCourses(coursesContainerModel);
    }

    /**
     * Save the object to the JSON file
     *
     * @param coursesContainerModel the used courses container object to be saved
     */
    public void saveCourses(CoursesContainerModel coursesContainerModel) {
        String json = Parser.parseToJson(coursesContainerModel,
                CoursesContainerModel.class);

        filesCachingManager.saveToFile(json);
    }

    /**
     * Read the courses container object from the JSON file
     *
     * @return the extracted courses container object
     */
    public CoursesContainerModel readCourses() {
        String json = filesCachingManager.readFile();

        return Parser.parseFromJson(json, CoursesContainerModel.class);
    }
}
