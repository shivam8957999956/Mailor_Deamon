package com.example.mailordeamon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.audiofx.DynamicsProcessing;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.security.Provider;

import static com.example.mailordeamon.Config.*;

public class LiveNewsChannels extends AppCompatActivity {

    private static final int RECOVERY_REQUEST = 1;

    ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_news_channels);
        final YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);

        YouTubePlayerView youTubePlayerViewNewsNation = findViewById(R.id.youtube_player_view_news_nation);
        getLifecycle().addObserver(youTubePlayerViewNewsNation);

        YouTubePlayerView youTubePlayerViewAbp = findViewById(R.id.youtube_player_view_abp_live);
        getLifecycle().addObserver(youTubePlayerViewAbp);

        YouTubePlayerView youTubePlayerViewAajTak = findViewById(R.id.youtube_player_view_aaj_tak_news);
        getLifecycle().addObserver(youTubePlayerViewAajTak);

        YouTubePlayerView youTubePlayerViewRepublic = findViewById(R.id.youtube_player_view_republic_news);
        getLifecycle().addObserver(youTubePlayerViewRepublic);

        backBtn = findViewById(R.id.back_btn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainDashboard.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                finish();
            }
        });


       }



}
