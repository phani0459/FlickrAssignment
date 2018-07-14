package com.flickrassignment.utils;

import android.util.Log;

import com.flickrassignment.interfaces.GetPhotosService;
import com.flickrassignment.model.Photo;
import com.flickrassignment.network.RetrofitInstance;
import com.flickrassignment.view.BaseView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetPhotosImpl implements BaseView.GetPhotoIntractor{

    @Override
    public void getPhotoList(final BaseView.GetPhotoIntractor.OnFinishedListener onFinishedListener) {


        /** Create handle for the RetrofitInstance interface*/
        GetPhotosService service = RetrofitInstance.getRetrofitInstance().create(GetPhotosService.class);

        /** Call the method with parameter in the interface to get the notice data*/
        Call<Photo> call = service.getPublicPhotos();

        /**Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<Photo>() {
            @Override
            public void onResponse(Call<Photo> call, Response<Photo> response) {
                onFinishedListener.onFinished(response.body().getItems());

            }

            @Override
            public void onFailure(Call<Photo> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });

    }

}
