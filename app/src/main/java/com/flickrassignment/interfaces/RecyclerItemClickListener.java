package com.flickrassignment.interfaces;

import com.flickrassignment.model.PhotoItem;

public interface RecyclerItemClickListener {
    void onItemClick(PhotoItem photoItem, int position);
}