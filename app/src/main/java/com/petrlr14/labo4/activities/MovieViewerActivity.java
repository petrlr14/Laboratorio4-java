package com.petrlr14.labo4.activities;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.petrlr14.labo4.R;
import com.petrlr14.labo4.pojos.Movie;

public class MovieViewerActivity extends AppCompatActivity {

    private CollapsingToolbarLayout collapsibleActionView;
    private ImageView moviewImage;
    private TextView rating, plot, director, actors;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_viewer);
        bindViews();
        init(getIntent().getParcelableExtra("Movie"));
    }

    private void bindViews() {
        collapsibleActionView = findViewById(R.id.collapsingtoolbarviewer);
        moviewImage = findViewById(R.id.app_bar_image_viewer);
        rating = findViewById(R.id.app_bar_rating_viewer);
        plot = findViewById(R.id.plot_viewer);
        director = findViewById(R.id.director_viewer);
        actors = findViewById(R.id.actors_viewer);
        toolbar=findViewById(R.id.toolbarviewer);
    }

    private void init(Movie movie) {
        Glide.with(this)
                .load(movie.getPoster())
                .placeholder(R.drawable.ic_launcher_background)
                .into(moviewImage);
        collapsibleActionView.setTitle(movie.getTitle());
        rating.setText(movie.getImdbRating());
        plot.setText(movie.getPlot());
        director.setText(movie.getDirector());
        actors.setText(movie.getActors());
        /*setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
    }

    /*@Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }*/
}
