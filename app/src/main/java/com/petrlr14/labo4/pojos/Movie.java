package com.petrlr14.labo4.pojos;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator<Movie>() {

        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    private String Title, Year, Relased, Runtime, Genre, Director, Actors, Plot, Language, imdbRating, Poster;

    public Movie(String title, String year, String relased, String runtime, String genre, String director, String actors, String plot, String language, String imdbRating, String poster) {
        Title = title;
        Year = year;
        Relased = relased;
        Runtime = runtime;
        Genre = genre;
        Director = director;
        Actors = actors;
        Plot = plot;
        Language = language;
        this.imdbRating = imdbRating;
        Poster = poster;
    }

    public Movie(Parcel source) {
        this.Actors = source.readString();
        this.Director = source.readString();
        this.Genre = source.readString();
        this.imdbRating = source.readString();
        this.Language = source.readString();
        this.Plot = source.readString();
        this.Poster = source.readString();
        this.Runtime = source.readString();
        this.Relased = source.readString();
        this.Year = source.readString();
        this.Title = source.readString();
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getRelased() {
        return Relased;
    }

    public void setRelased(String relased) {
        Relased = relased;
    }

    public String getRuntime() {
        return Runtime;
    }

    public void setRuntime(String runtime) {
        Runtime = runtime;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public String getActors() {
        return Actors;
    }

    public void setActors(String actors) {
        Actors = actors;
    }

    public String getPlot() {
        return Plot;
    }

    public void setPlot(String plot) {
        Plot = plot;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImbdRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Actors);
        dest.writeString(this.Director);
        dest.writeString(this.Genre);
        dest.writeString(this.imdbRating);
        dest.writeString(this.Language);
        dest.writeString(this.Plot);
        dest.writeString(this.Poster);
        dest.writeString(this.Runtime);
        dest.writeString(this.Relased);
        dest.writeString(this.Title);
        dest.writeString(this.Year);
    }
}
