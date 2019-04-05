package com.petrlr14.labo4.network;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    private static String MOVIE_API_BASEURL = "http://www.omdbapi.com/";
    private static String TOKEN_API = "58280234";

    public static URL buildtSearchURL(String name) {
        Uri builtUri = Uri.parse(MOVIE_API_BASEURL)
                .buildUpon()
                .appendQueryParameter("apiKey", TOKEN_API)
                .appendQueryParameter("t", name)
                .build();
        try {
            return new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

}
