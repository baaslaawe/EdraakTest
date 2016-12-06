package org.edraak.edraaktest.models.managers;

import android.annotation.SuppressLint;

import org.edraak.edraaktest.models.thin.CountCourseModel;
import org.edraak.edraaktest.models.thin.CourseModel;
import org.edraak.edraaktest.utils.NumbersComparatorMaager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The manager that gets the best items based on their IDs and categories
 */
public class BestItemsManagers {

    private List<CourseModel> courses;

    /**
     * Create an instance
     *
     * @param courses all courses so far
     */
    public BestItemsManagers(List<CourseModel> courses) {
        this.courses = courses;
    }

    public List<CourseModel> getBest100CoursesBasedOnId() {
        List<CountCourseModel> bestCourses = getSortedBestCountCoursesList
                (new CourseIdComparator());

        return getBestCourses(bestCourses, new CourseIdComparator(), 100);
    }

    public List<CourseModel> getBest5CoursesBasedOnCategory() {
        List<CountCourseModel> bestCourses = getSortedBestCountCoursesList
                (new CourseIdComparator());

        return getBestCourses(bestCourses, new CourseCategoryComparator(), 5);
    }

    private List<CourseModel> getBestCourses
            (List<CountCourseModel> countCourseModelList,
             Comparator<CountCourseModel> comparator, int limit) {

        List<CountCourseModel> bestCountedCoursesList =
                getSortedBestCountCoursesList(comparator);
        List<CourseModel> bestCoursesList = new ArrayList<>();

        int listSize = bestCountedCoursesList.size();
        int count = Math.min(listSize, limit);
        for (int i = 0; i < count; i++) {
            CountCourseModel countCourseModel = countCourseModelList.get(i);
            bestCoursesList.add(countCourseModel.getCourse());
        }

        return bestCoursesList;
    }

    private List<CountCourseModel> getSortedBestCountCoursesList
            (Comparator<CountCourseModel> comparator) {
        List<CountCourseModel> bestCountedCoursesList = getBestCountCoursesList();

        Collections.sort(bestCountedCoursesList, comparator);

        return bestCountedCoursesList;
    }

    private List<CountCourseModel> getBestCountCoursesList() {
        Map<Long, CountCourseModel> bestCountedCoursesMap = getBestCoursesMap();

        return new ArrayList<>(bestCountedCoursesMap.values());
    }

    private Map<Long, CountCourseModel> getBestCoursesMap() {
        @SuppressLint("UseSparseArrays")
        Map<Long, CountCourseModel> bestCourses = new HashMap<>();

        for (CourseModel courseModel : courses) {
            countKey(courseModel, courseModel.getId(), bestCourses);
        }

        return bestCourses;
    }

    private void countKey(CourseModel courseModel, long key,
                          Map<Long, CountCourseModel> countList) {
        CountCourseModel foundItem = countList.get(key);
        CountCourseModel countCourseModel = null;

        if (foundItem == null) {
            countCourseModel = new CountCourseModel(courseModel);

        } else {
            foundItem.incrementCount();
        }

        countList.put(key, countCourseModel);
    }

    private class CourseIdComparator implements Comparator<CountCourseModel> {

        @Override
        public int compare(CountCourseModel value1, CountCourseModel value2) {
            long id1 = value1.getCourse().getCourseId();
            long id2 = value2.getCourse().getCourseId();

            return NumbersComparatorMaager.compare(id1, id2);
        }
    }

    private class CourseCategoryComparator implements Comparator<CountCourseModel> {

        @Override
        public int compare(CountCourseModel value1, CountCourseModel value2) {
            long id1 = value1.getCourse().getCourseCategoryId();
            long id2 = value2.getCourse().getCourseCategoryId();

            return NumbersComparatorMaager.compare(id1, id2);
        }
    }
}
