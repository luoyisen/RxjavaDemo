package com.example.i.rxjavademo;

/**
 * Created by I on 2017/8/11.
 */

public class Movie {
    public String title;
    public String original_title;
    public String year;
    public MovieImage images;

    public static class MovieImage{
        public String small;
        public String large;
        public String medium;
    }
}
