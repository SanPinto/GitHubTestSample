package common.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.sample.git.R;

public class SplashActivity extends AppCompatActivity {
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

    }
}
