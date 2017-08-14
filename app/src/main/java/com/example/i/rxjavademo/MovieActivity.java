package com.example.i.rxjavademo;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import rx.functions.Action1;

/**
 * Created by I on 2017/8/11.
 */

public class MovieActivity extends Activity implements View.OnClickListener {
    private MovieLoader mMovieLoader;
    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        mMovieLoader = new MovieLoader();

        initView();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.setTitle(R.string.movie_activity_name);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.addItemDecoration(new MovieDecoration());
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mMovieAdapter = new MovieAdapter();
        mRecyclerView.setAdapter(mMovieAdapter);
        getMovieList();
    }

    private void getMovieList() {
        //observable.subscribe(observer)

        mMovieLoader.getMovie(0, 50).subscribe(new Action1<List<Movie>>() {// TODO: 2017/8/11 question1
            @Override
            public void call(List<Movie> movies) {
                //Call其实在Retrofit中就是行使网络请求并处理返回值的类，调用的时候会把需要拼接的参数传递进去，
                mMovieAdapter.setMovies(movies);
                mMovieAdapter.notifyDataSetChanged();
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                if (throwable instanceof Fault) {

                    Fault fault = (Fault) throwable;
                    if (fault.getErrorCode() == 404) {
                    } else if (fault.getErrorCode() == 500) {
                    } else if (fault.getErrorCode() == 501) {
                    }
                }
            }
        });

    }


    @Override
    public void onClick(View v) {
    }

    public static class MovieDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.set(0, 0, 10, 10);//item距离边框的距离，参数依次为：1.左 2.上 3.右 4.下
        }
    }
}
