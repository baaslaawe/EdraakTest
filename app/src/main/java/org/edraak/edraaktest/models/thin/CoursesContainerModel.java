package org.edraak.edraaktest.models.thin;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * The object that contains the list
 */
public class CoursesContainerModel implements Serializable {

    @SerializedName("meta")
    private MetaModel meta;

    @SerializedName("results")
    private List<CourseModel> results;

    public MetaModel getMeta() {
        return meta;
    }

    public List<CourseModel> getResults() {
        return results;
    }

    public void setResults(List<CourseModel> results) {
        this.results = results;
    }
}
