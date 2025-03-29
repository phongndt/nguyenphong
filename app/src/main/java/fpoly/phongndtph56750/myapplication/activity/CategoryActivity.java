package fpoly.phongndtph56750.myapplication.activity;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fpoly.phongndtph56750.myapplication.MyApplication;
import fpoly.phongndtph56750.myapplication.adapter.MovieAdapter;
import fpoly.phongndtph56750.myapplication.constant.ConstantKey;
import fpoly.phongndtph56750.myapplication.constant.GlobalFunction;
import fpoly.phongndtph56750.myapplication.databinding.ActivityCategoryBinding;
import fpoly.phongndtph56750.myapplication.model.Category;
import fpoly.phongndtph56750.myapplication.model.Movie;

public class CategoryActivity extends AppCompatActivity {

    private ActivityCategoryBinding mActivityCategoryBinding;
    private List<Movie> mListMovies;
    private Category mCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityCategoryBinding = ActivityCategoryBinding.inflate(getLayoutInflater());
        setContentView(mActivityCategoryBinding.getRoot());

        getDataIntent();
        initListener();
        getListMovies();
    }

    private void getDataIntent() {
        Bundle bundleReceived = getIntent().getExtras();
        if (bundleReceived != null) {
            mCategory = (Category) bundleReceived.get(ConstantKey.KEY_INTENT_CATEGORY_OBJECT);
            mActivityCategoryBinding.tvTitle.setText(mCategory.getName());
        }
    }

    private void initListener() {
        mActivityCategoryBinding.imgBack.setOnClickListener(v -> onBackPressed());
    }

    private void getListMovies() {
        MyApplication.get(this).getMovieDatabaseReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (mListMovies != null) {
                    mListMovies.clear();
                } else {
                    mListMovies = new ArrayList<>();
                }
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Movie movie = dataSnapshot.getValue(Movie.class);
                    if (movie != null && mCategory.getId() == movie.getCategoryId()) {
                        mListMovies.add(0, movie);
                    }
                }
                displayListMovies();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }

    private void displayListMovies() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        mActivityCategoryBinding.rcvData.setLayoutManager(gridLayoutManager);
        MovieAdapter movieAdapter = new MovieAdapter(mListMovies,
                movie -> GlobalFunction.goToMovieDetail( this,  movie));
        mActivityCategoryBinding.rcvData.setAdapter(movieAdapter);
    }
}