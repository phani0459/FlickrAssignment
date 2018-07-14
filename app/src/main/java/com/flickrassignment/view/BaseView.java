package com.flickrassignment.view;

import com.flickrassignment.model.Photo;
import com.flickrassignment.model.PhotoItem;

import java.util.ArrayList;

public interface BaseView {

    /**
     * Call when user interact with the view and other when view OnDestroy()
     * */
    interface presenter{

        void onDestroy();

        void requestDataFromServer();

    }

    /**
     * showProgress() and hideProgress() would be used for displaying and hiding the progressBar
     * while the setDataToRecyclerView and onResponseFailure is fetched from the GetNoticeInteractorImpl class
     **/
    interface MainView {

        void setDataToRecyclerView(ArrayList<PhotoItem> photosArrayList);

        void onResponseFailure(Throwable throwable);

    }

    /**
     * Intractors are classes built for fetching data from your database, web services, or any other data source.
     **/
    interface GetPhotoIntractor {

        interface OnFinishedListener {
            void onFinished(ArrayList<PhotoItem> photosArrayList);
            void onFailure(Throwable t);
        }

        void getPhotoList(OnFinishedListener onFinishedListener);
    }
}
