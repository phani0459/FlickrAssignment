package com.flickrassignment.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.flickrassignment.R;
import com.flickrassignment.adapter.SlidingImageAdapter;
import com.flickrassignment.model.PhotoItem;

import java.util.ArrayList;

public class SlideImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayList<PhotoItem> photos = (ArrayList<PhotoItem>) getIntent().getExtras().get("PHOTOS");
        int position = getIntent().getIntExtra("POSITION", 0);

        ViewPager mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new SlidingImageAdapter(this, photos));
        mPager.setCurrentItem(position);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
