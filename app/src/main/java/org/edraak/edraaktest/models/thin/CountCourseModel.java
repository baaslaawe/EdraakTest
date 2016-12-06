package org.edraak.edraaktest.models.thin;

public class CountCourseModel {

    private static final int STARTING_AT = 1;

    private CourseModel course;
    private int count = STARTING_AT;

    public CountCourseModel(CourseModel course) {
        this.course = course;
    }

    public CourseModel getCourse() {
        return course;
    }

    public int getCount() {
        return count;
    }

    public void incrementCount() {
        count++;
    }
}
