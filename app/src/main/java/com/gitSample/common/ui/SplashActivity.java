package com.gitSample.common.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;

import com.android.volley.VolleyError;
import com.sample.git.R;

import com.gitSample.common.dto.Configuration;
import com.gitSample.common.io.ConfigManager;

public class SplashActivity extends AppCompatActivity implements ConfigManager.OnCongifDownloadListner {
    private ProgressBar mLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initViews();
        downloadConfig();
    }

    private void initViews(){
        mLoader = (ProgressBar) findViewById(R.id.loader);
    }

    private void downloadConfig(){
       String configUrl = "http://drop.ndtv.com/ndtv/apps/config/test/ndtvapps/lollypop-english.json";

        ConfigManager configManager = ConfigManager.getInstance();
        if(configManager != null) {
            configManager.downLoadConfig(this, configUrl, this);
        }

    }

    @Override
    public void onConfigDownloaded(Configuration configuration) {
        Log.d("Config", configuration.toString());
    }

    @Override
    public void onConfigFailed(VolleyError error) {
      Log.d("Config Failed", error.toString());
    }
}
