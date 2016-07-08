package com.example.leo.corpus;

//https://www.youtube.com/watch?v=a4NT5iBFuZs
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class Columpios extends YouTubeBaseActivity {
    private Button b;
    private YouTubePlayerView youtuberPlayerView;
    private YouTubePlayer.OnInitializedListener onInitializedListerner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_columpios);

        youtuberPlayerView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        onInitializedListerner = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("Rjgitp674mg");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        b = (Button)findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youtuberPlayerView.initialize("AIzaSyBlrziCAzKyvE_s6qkZqd3UK7wmxWGxBpU",onInitializedListerner);

            }
        });

    }
}
