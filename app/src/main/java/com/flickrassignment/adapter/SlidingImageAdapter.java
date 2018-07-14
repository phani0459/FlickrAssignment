package com.flickrassignment.adapter;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flickrassignment.R;
import com.flickrassignment.model.PhotoItem;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class SlidingImageAdapter extends PagerAdapter {


    private ArrayList<PhotoItem> photoItems;
    private LayoutInflater inflater;
    private Context context;


    public SlidingImageAdapter(Context context, ArrayList<PhotoItem> photoItems) {
        this.context = context;
        this.photoItems = photoItems;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return photoItems.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.single_image, view, false);

        assert imageLayout != null;
        final PhotoView imageView = imageLayout.findViewById(R.id.photo_view);

        Picasso.get()
                .load(photoItems.get(position).getMedia().getThumbnailImage())
                .placeholder(R.mipmap.ic_launcher)
                .into(imageView);

        view.addView(imageLayout, 0);

        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }


}