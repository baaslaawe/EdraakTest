package org.edraak.edraaktest.models.thin;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * The object that contains the list
 */
public class CoursesContainerModel implements Serializable {

    @SerializedName("next_offset")
    private long nextOffset;

    @SerializedName("offset")
    private long offset;

    @SerializedName("results")
    private List<CourseModel> results;

    public long getNextOffset() {
        return nextOffset;
    }

    public long getOffset() {
        return offset;
    }

    public List<CourseModel> getResults() {
        return results;
    }
}
