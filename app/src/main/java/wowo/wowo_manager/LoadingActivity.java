package wowo.wowo_manager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.util.Random;

import wowo.wowo_manager.R;
import wowo.wowo_manager.User.LoginActivity;

public class LoadingActivity extends Activity{

    private final int SPLASH_DISPLAY_LENGTH = 1000;

    Random r = new Random();
    int x = r.nextInt(10) + 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_page);
        startLoading();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void startLoading() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
