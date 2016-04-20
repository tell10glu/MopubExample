package com.tll.mopub;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.mopub.common.MoPub;
import com.mopub.common.MoPubReward;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubRewardedVideoListener;

import java.util.Set;

public class MainActivity extends Activity {

    View view;
    private static final String AD_UNIT = "AD_UNIT";
    private MoPubRewardedVideoListener rewardedVideoListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = findViewById(R.id.hello_world);
        MoPub.initializeRewardedVideo(this);
        MoPub.onCreate(this);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadRewardedVideo();
                showRewardedVideo();
            }
        });
        rewardedVideoListener = new MoPubRewardedVideoListener() {
            @Override
            public void onRewardedVideoLoadSuccess(String adUnitId) {
                Log.e("test","success");
                // Called when the adUnitId has loaded. At this point you should be able to call MoPub.showRewardedVideo(String) to show the video
            }

            @Override
            public void onRewardedVideoLoadFailure(String adUnitId, MoPubErrorCode errorCode) {
                Log.e("test", errorCode.toString());

                // Called when a video fails to load for the given ad unit id. The provided error code will provide more insight into the reason for the failure to load.
            }

            @Override
            public void onRewardedVideoStarted(String adUnitId) {
                Log.e("test","started");
                // Called when a rewarded video starts playing.
            }

            @Override
            public void onRewardedVideoPlaybackError(String adUnitId, MoPubErrorCode errorCode) {
                Log.e("test","playback error");
                //  Called when there is an error during video playback.
            }

            @Override
            public void onRewardedVideoClosed(String adUnitId) {
                Log.e("test","closed");
                // Called when a rewarded video is closed. At this point your application should resume.
            }

            @Override
            public void onRewardedVideoCompleted(Set adUnitIds, MoPubReward reward) {
                Log.e("test","completed");
                // Called when a rewarded video is completed and the user should be rewarded.
                // You can query the reward object with boolean isSuccessful(), String getLabel(), and int getAmount().
            }
        };

        MoPub.setRewardedVideoListener(rewardedVideoListener);
    }

    private void loadRewardedVideo(){
        MoPub.loadRewardedVideo(AD_UNIT);
    }
    private void showRewardedVideo(){
        MoPub.showRewardedVideo(AD_UNIT);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MoPub.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MoPub.onResume(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        MoPub.onStart(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MoPub.onStop(this);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        MoPub.onDestroy(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MoPub.onBackPressed(this);
    }
}
