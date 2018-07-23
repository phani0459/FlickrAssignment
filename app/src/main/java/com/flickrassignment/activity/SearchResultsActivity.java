package com.flickrassignment.activity;

import android.app.SearchManager;
import android.content.Context;
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
import android.view.MenuInflater;
import android.widget.Toast;

import com.flickrassignment.R;
import com.flickrassignment.adapter.PhotosAdapter;
import com.flickrassignment.interfaces.GetPhotosService;
import com.flickrassignment.interfaces.RecyclerItemClickListener;
import com.flickrassignment.model.Photo;
import com.flickrassignment.model.PhotoItem;
import com.flickrassignment.network.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResultsActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private RecyclerView recyclerView;
    public ArrayList<PhotoItem> photoItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeToolbarAndRecyclerView();
        handleIntent(getIntent());
    }

    public void getPhotoList(String searchTag) {


        /** Create handle for the RetrofitInstance interface*/
        GetPhotosService service = RetrofitInstance.getRetrofitInstance().create(GetPhotosService.class);

        /** Call the method with parameter in the interface to get the notice data*/
        Call<Photo> call = service.getSearchPhotos(searchTag);

        /**Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<Photo>() {

            @Override
            public void onResponse(Call<Photo> call, Response<Photo> response) {
                if (response != null && response.body() != null && response.body().getItems() != null && response.body().getItems().size() > 0) {
                    photoItems = response.body().getItems();
                    PhotosAdapter adapter = new PhotosAdapter(SearchResultsActivity.this, response.body().getItems(), recyclerItemClickListener);
                    recyclerView.setAdapter(adapter);
                } else {
                    PhotosAdapter adapter = new PhotosAdapter(SearchResultsActivity.this, new ArrayList<PhotoItem>(), recyclerItemClickListener);
                    recyclerView.setAdapter(adapter);
                    Toast.makeText(SearchResultsActivity.this, "There are no photos avalable for this tag", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Photo> call, Throwable t) {
                PhotosAdapter adapter = new PhotosAdapter(SearchResultsActivity.this, new ArrayList<PhotoItem>(), recyclerItemClickListener);
                recyclerView.setAdapter(adapter);
                Toast.makeText(SearchResultsActivity.this, "There are no photos avalable for this tag", Toast.LENGTH_LONG).show();
            }
        });

    }

    /**
     * RecyclerItem click event listener
     */
    private RecyclerItemClickListener recyclerItemClickListener = new RecyclerItemClickListener() {
        @Override
        public void onItemClick(PhotoItem notice, int position) {
            Intent intent = new Intent(SearchResultsActivity.this, SlideImageActivity.class);
            intent.putExtra("PHOTOS", photoItems);
            intent.putExtra("POSITION", position);
            startActivity(intent);

        }
    };

    /**
     * Initializing Toolbar and RecyclerView
     */
    private void initializeToolbarAndRecyclerView() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycler_view_employee_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if (photoItems != null && photoItems.size() > 0) {
            if (!TextUtils.isEmpty(query)) {
                getPhotoList(query);
            }
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search

            getPhotoList(query);
        }
    }
}
