package com.kivophong.countrystatus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button mStartBtn, mStopBtn, mTestBtn;
    private TextView mTextLatestRequest, mTextTimeAvailable;
    private MediaPlayer mediaPlayer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStartBtn = findViewById(R.id.btn_start);
        mStopBtn = findViewById(R.id.btn_stop);
        mTestBtn = findViewById(R.id.btn_test);
        mTextLatestRequest = findViewById(R.id.request_time);
        mTextTimeAvailable = findViewById(R.id.time_available);

        Intent intent = new Intent(this, ParseService.class);

        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(intent);
            }
        });

        mStopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(intent);
            }
        });

        mTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playRingtone();
            }
        });
//        WebView webview = new WebView(this);
//        setContentView(webview);
//        webview.getSettings().setJavaScriptEnabled(true);
//
//        webview.setWebViewClient(new WebViewClient() {
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                // Check here if url is equal to your site URL.
//                webview.loadUrl("javascript:(function() { document.getElementsByName('username')[0].value='minatookio'; document.getElementsByName('password')[0].value='@1v2i3n4hABdkhot009@'; document.getElementsByTagName('form')[0].submit();})()");
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        webview.loadUrl("https://onlineservices.immigration.govt.nz/?WHS");
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("CountryStatus", MODE_PRIVATE);
        mTextLatestRequest.setText(sharedPreferences.getString("latest_request", "not request"));
        mTextTimeAvailable.setText(sharedPreferences.getString("time_available", "not availabe"));
    }

    private void playRingtone(){
        AudioManager audioManager =  (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);

        if(mediaPlayer == null){
            mediaPlayer=MediaPlayer.create(this,R.raw.rington);
            mediaPlayer.start();
        } else {
            mediaPlayer.stop();
        }

//        MediaPlayer mediaPlayer2= MediaPlayer.create(this,R.raw.rington);
//        mediaPlayer2.start();
    }
}