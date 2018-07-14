package com.flickrassignment.presenter;

import com.flickrassignment.model.Photo;
import com.flickrassignment.model.PhotoItem;
import com.flickrassignment.view.BaseView;

import java.util.ArrayList;

public class BasePresenterImpl implements BaseView.presenter, BaseView.GetPhotoIntractor.OnFinishedListener {

    private BaseView.MainView mainView;
    private BaseView.GetPhotoIntractor getNoticeIntractor;

    public BasePresenterImpl(BaseView.MainView mainView, BaseView.GetPhotoIntractor getNoticeIntractor) {
        this.mainView = mainView;
        this.getNoticeIntractor = getNoticeIntractor;
    }

    @Override
    public void onDestroy() {

        mainView = null;

    }


    @Override
    public void requestDataFromServer() {
        getNoticeIntractor.getPhotoList(this);
    }


    @Override
    public void onFinished(ArrayList<PhotoItem> noticeArrayList) {
        if(mainView != null){
            mainView.setDataToRecyclerView(noticeArrayList);
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if(mainView != null){
            mainView.onResponseFailure(t);
        }
    }
}
