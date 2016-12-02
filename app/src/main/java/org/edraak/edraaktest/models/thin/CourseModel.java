package org.edraak.edraaktest.models.thin;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * The course model from the API
 */
public class CourseModel implements Serializable {

    @SerializedName("id")
    private long id;

    @SerializedName("course_id")
    private long courseId;

    @SerializedName("course_category_id")
    private long courseCategoryId;

    public long getId() {
        return id;
    }

    public long getCourseId() {
        return courseId;
    }

    public long getCourseCategoryId() {
        return courseCategoryId;
    }
}
