package com.petrlr14.labo4.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.petrlr14.labo4.R;
import com.petrlr14.labo4.adapters.MovieAdapter;
import com.petrlr14.labo4.network.NetworkUtils;
import com.petrlr14.labo4.pojos.Movie;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.LayoutManager viewManager;
    private MovieAdapter adapter;
    private List<Movie> movies;
    private RecyclerView recyclerView;
    private Button addMovie;
    private EditText movieName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
    }

    private void bindViews(){
        recyclerView=findViewById(R.id.movie_list_rv);
        addMovie=findViewById(R.id.add_movie_btn);
        movieName=findViewById(R.id.movie_name_et);
        initRecycler();
        setUpViews();
    }

    private void initRecycler(){
        movies=new ArrayList<>();
        viewManager=new LinearLayoutManager(this);
        adapter= new MovieAdapter(movies) {
            @Override
            public void onClick(Movie movie) {
                startActivity(new Intent(MainActivity.this, MovieViewerActivity.class).putExtra("Movie", movie));
            }
        };
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(viewManager);
        recyclerView.setAdapter(adapter);
    }

    private void setUpViews(){
        addMovie.setOnClickListener(v->{
            if(!movieName.getText().toString().isEmpty()){
                new FetchMoview().execute(movieName.getText().toString());
            }
        });
    }

    private void addMovieToList(Movie movie){
        movies.add(movie);
        adapter.updateList(movies);
    }

    private class FetchMoview extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {
            String movieName=params[0];
            URL moviewURL= NetworkUtils.buildtSearchURL(movieName);
            try {
                return NetworkUtils.getResponseFromHttpUrl(moviewURL);
            }catch (IOException e){
                e.printStackTrace();
                return "";
            }
        }

        @Override
        protected void onPostExecute(String movieInfo) {
            super.onPostExecute(movieInfo);
            if(movieInfo!=null){
                JSONObject movieJson;
                try {
                    movieJson = new JSONObject(movieInfo);
                    try {
                        if(movieJson.getString("Response").equals("True")){
                            Movie m=new Gson().fromJson(movieInfo, Movie.class);
                            Log.d("MOVIE", movieInfo);
                            addMovieToList(new Gson().fromJson(movieInfo, Movie.class));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Snackbar.make(findViewById(R.id.main_ll), "No existe la pelicula", Snackbar.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else{

            }
        }
    }

}
