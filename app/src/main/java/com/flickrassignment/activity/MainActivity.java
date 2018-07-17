package com.flickrassignment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.flickrassignment.R;
import com.flickrassignment.adapter.PhotosAdapter;
import com.flickrassignment.interfaces.RecyclerItemClickListener;
import com.flickrassignment.model.PhotoItem;
import com.flickrassignment.presenter.BasePresenterImpl;
import com.flickrassignment.utils.GetPhotosImpl;
import com.flickrassignment.view.BaseView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BaseView.MainView, SearchView.OnQueryTextListener {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    private BaseView.presenter presenter;
    private ArrayList<PhotoItem> photoItems;
    private PhotosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeToolbarAndRecyclerView();

        presenter = new BasePresenterImpl(this, new GetPhotosImpl());
        presenter.requestDataFromServer();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setFocusable(false);
        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                if (photoItems != null && photoItems.size() > 0) {
                    adapter = new PhotosAdapter(MainActivity.this, photoItems,recyclerItemClickListener);
                    recyclerView.setAdapter(adapter);
                }
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Initializing Toolbar and RecyclerView
     */
    private void initializeToolbarAndRecyclerView() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycler_view_employee_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);


    }

    /**
     * RecyclerItem click event listener
     * */
    private RecyclerItemClickListener recyclerItemClickListener = new RecyclerItemClickListener() {
        @Override
        public void onItemClick(PhotoItem notice, int position) {
            Intent intent = new Intent(MainActivity.this, SlideImageActivity.class);
            intent.putExtra("PHOTOS", photoItems);
            intent.putExtra("POSITION", position);
            startActivity(intent);

        }
    };

    @Override
    public void setDataToRecyclerView(ArrayList<PhotoItem> photoItems) {
        this.photoItems = photoItems;
        adapter = new PhotosAdapter(this, photoItems , recyclerItemClickListener);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(MainActivity.this,
                "Something went wrong...Error message: " + throwable.getMessage(),
                Toast.LENGTH_LONG).show();
        Log.e("TAGTAG", "onResponseFailure: " + throwable.getMessage() );
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if (photoItems != null && photoItems.size() > 0) {
            if (!TextUtils.isEmpty(query)) {
                ArrayList<PhotoItem> newPhotoItems = new ArrayList<>();
                for (int i = 0; i < photoItems.size(); i++) {
                    if (!TextUtils.isEmpty(photoItems.get(i).getTags()) && photoItems.get(i).getTags().toLowerCase().contains(query.toLowerCase())) {
                        newPhotoItems.add(photoItems.get(i));
                    }
                }
                adapter = new PhotosAdapter(this, newPhotoItems,recyclerItemClickListener);
                recyclerView.setAdapter(adapter);
            } else {
                adapter = new PhotosAdapter(this, photoItems,recyclerItemClickListener);
                recyclerView.setAdapter(adapter);
            }
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}

