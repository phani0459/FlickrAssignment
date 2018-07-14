package com.flickrassignment.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.flickrassignment.R;
import com.flickrassignment.interfaces.RecyclerItemClickListener;
import com.flickrassignment.model.PhotoItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.EmployeeViewHolder> {

    private ArrayList<PhotoItem> dataList;
    private RecyclerItemClickListener recyclerItemClickListener;
    private Context mContext;

    public PhotosAdapter(Context mContext, ArrayList<PhotoItem> dataList, RecyclerItemClickListener recyclerItemClickListener) {
        this.dataList = dataList;
        this.mContext = mContext;
        this.recyclerItemClickListener = recyclerItemClickListener;
    }


    @Override
    public EmployeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_view_row, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmployeeViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.txtPhotoTitle.setText(dataList.get(position).getTitle());

        Picasso.get()
                .load(dataList.get(position).getMedia().getThumbnailImage())
                .centerCrop()
                .resize(96, 96)
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.flickrImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerItemClickListener.onItemClick(dataList.get(position), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder {

        TextView txtPhotoTitle;
        ImageView flickrImage;

        EmployeeViewHolder(View itemView) {
            super(itemView);
            txtPhotoTitle = itemView.findViewById(R.id.txt_image_title);
            flickrImage = itemView.findViewById(R.id.img_flickr_photo);

        }
    }
}