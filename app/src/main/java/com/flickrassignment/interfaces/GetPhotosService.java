package com.flickrassignment.interfaces;

import com.flickrassignment.model.Photo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetPhotosService {

    @GET("services/feeds/photos_public.gne?format=json&nojsoncallback=1")
    Call<Photo> getPublicPhotos();

}