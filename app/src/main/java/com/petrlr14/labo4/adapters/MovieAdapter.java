package com.petrlr14.labo4.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.petrlr14.labo4.R;
import com.petrlr14.labo4.pojos.Movie;

import java.util.List;

public abstract class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<Movie> movies;
    private Context ctx;

    public MovieAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ctx=viewGroup.getContext();
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_movie, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        setInfo(viewHolder, movies.get(i));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void updateList(List<Movie> movies){
        this.movies=movies;
        notifyDataSetChanged();
    }

    private void setInfo(ViewHolder viewHolder, Movie movie) {
        viewHolder.movieTitle.setText(movie.getTitle());
        viewHolder.moviewPlot.setText(movie.getPlot());
        viewHolder.moviewRate.setText(movie.getImdbRating());
        viewHolder.moviewRunTime.setText(movie.getRuntime());
        viewHolder.itemView.setOnClickListener(v->onClick(movie));
        Glide.with(ctx)
                .load(movie.getPoster())
                .into(viewHolder.poster);
    }

    public abstract void onClick(Movie movie);

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView movieTitle, moviewPlot, moviewRate, moviewRunTime;
        ImageView poster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.movie_image_cv);
            movieTitle = itemView.findViewById(R.id.movie_title_cv);
            moviewPlot = itemView.findViewById(R.id.movie_plot_cv);
            moviewRate = itemView.findViewById(R.id.movie_rate_cv);
            moviewRunTime = itemView.findViewById(R.id.movie_runtime_cv);
        }
    }
}
