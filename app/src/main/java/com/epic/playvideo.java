package com.epic;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.potyvideo.library.AndExoPlayerView;

public class playvideo extends AppCompatActivity {
    private AndExoPlayerView andExoPlayerView;
    String video_url ;
    ImageView black;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playvideo);
        andExoPlayerView = findViewById(R.id.andExoPlayerView);
        video_url = getIntent().getStringExtra("url");
        loadMP4ServerSide();
        black = findViewById(R.id.ic_black);
        black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loadMP4ServerSide() {
        andExoPlayerView.setSource(video_url);
    }
}
