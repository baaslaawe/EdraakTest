package org.edraak.edraaktest.models.services;

import org.edraak.edraaktest.models.thin.CoursesContainerModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CoursesService {

    @GET("/studentstats")
    Call<CoursesContainerModel> getCourses(
            @Query("offset") long offset, @Query("limit") int limit);
}
