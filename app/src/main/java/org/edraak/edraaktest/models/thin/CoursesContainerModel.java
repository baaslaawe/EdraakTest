package org.edraak.edraaktest.models.thin;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * The object that contains the list
 */
public class CoursesContainerModel implements Serializable {

    private static final long DEFAULT_OFFSET = 0;

    @SerializedName("next_offset")
    private long nextOffset;

    @SerializedName("offset")
    private long offset;

    @SerializedName("results")
    private List<CourseModel> results;

    public long getNextOffset() {
        return nextOffset;
    }

    public boolean isValidNextOffset() {
        return nextOffset != DEFAULT_OFFSET;
    }

    public long getOffset() {
        return offset;
    }

    public List<CourseModel> getResults() {
        return results;
    }
}
