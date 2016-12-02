package org.edraak.edraaktest.models.services;

import org.edraak.edraaktest.models.thin.CoursesContainerModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CoursesService {

    @GET("/v1/studentipedia/studentstats")
    Call<CoursesContainerModel> getCourses(@Query("limit") int limit);

    @GET("/v1/studentipedia/studentstats")
    Call<CoursesContainerModel> getCourses(
            @Query("limit") int limit, @Query("offset") long offset);
}
