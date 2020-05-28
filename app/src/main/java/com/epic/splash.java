package com.epic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class splash extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MoviesAdapter mAdapter;
    private List<Movie> movieList = new ArrayList<>();

    private String[] listvideo = new String[]{"part1.mp4","part2.mp4","part3.mp4","part4.mp4","part5.mp4","part6.mp4","part7.mp4"};
    String sever = "http://data.aib.babylover.me/guide/Nerfepic/";

    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        recyclerView = (RecyclerView) findViewById(R.id.rcw_1);
        mAdapter = new MoviesAdapter(movieList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        prepareMovieData();
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent myIntent = new Intent(getApplicationContext(),playvideo.class);
                        myIntent.putExtra("url", sever+listvideo[position]);
                        startActivity(myIntent);
                        Random r = new Random();
                        int ads = r.nextInt(100);

                        if (ads < MainActivity.PERCENT_SHOW_INTER_AD){
                            MainActivity.showInterstitial(getApplicationContext());
                        }
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
        View adContainer = findViewById(R.id.adMobView);

        AdView mAdView = new AdView(this);
        mAdView.setAdSize(AdSize.BANNER);
        mAdView.setAdUnitId(MainActivity.BANNER_ID);
        ((RelativeLayout)adContainer).addView(mAdView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Random r = new Random();
        int ads = r.nextInt(100);

        if (ads >= MainActivity.PERCENT_SHOW_BANNER_AD){
            mAdView.destroy();
            mAdView.setVisibility(View.GONE);
        }

    }
    private void prepareMovieData() {
        Movie movie = new Movie("Part 1",R.mipmap.part1);
        movieList.add(movie);
        movie = new Movie("Part 2",R.mipmap.part2);
        movieList.add(movie);
        movie = new Movie("Part 3 - 1",R.mipmap.part3);
        movieList.add(movie);
        movie = new Movie("Part 3 - 2",R.mipmap.part4);
        movieList.add(movie);
        movie = new Movie("Part 3 - 3",R.mipmap.part5);
        movieList.add(movie);
        movie = new Movie("Part 3 - full",R.mipmap.part6);
        movieList.add(movie);
        movie = new Movie("Part 3 - full",R.mipmap.part7);
        movieList.add(movie);


        mAdapter.notifyDataSetChanged();
    }
}
