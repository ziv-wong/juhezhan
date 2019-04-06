package com.ziv.juhezhan;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.ziv.juhezhan.data.AppSaveDataSPUtil;

import java.util.Random;

public class StartActivity extends AppCompatActivity {

    ObjectAnimator mAlphaAnim;
    private boolean ifFirstIn;
    private ImageView imgBackground;
    private ImageView imgBanner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        initData();

        initView();

        initAnim();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAlphaAnim.isRunning()) {
            mAlphaAnim.cancel();
        }
    }

    private void initData() {
        AppSaveDataSPUtil.init(this);
        ifFirstIn = AppSaveDataSPUtil.getIfFirstIn();
    }

    private void initView() {
        imgBackground = (ImageView) findViewById(R.id.imgBackground);
        imgBanner = (ImageView) findViewById(R.id.imgBanner);

        Random random = new Random();
        int index = random.nextInt(5) + 1;
        int drawableId = getResources().getIdentifier("start_bg_" + index, "drawable", getPackageName());
        imgBackground.setImageResource(drawableId);
    }

    private void initAnim() {
        mAlphaAnim = ObjectAnimator
                .ofFloat(imgBanner, "alpha", 0.0f, 1.0f);
        mAlphaAnim.setDuration(1500);
        mAlphaAnim.setStartDelay(1500);
        mAlphaAnim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                goActivity(2500);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                goActivity(0);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        mAlphaAnim.start();
    }

    private void goActivity(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent intent;
        if (ifFirstIn) {
            intent = new Intent(StartActivity.this, FirstActivity.class);
        } else {
            intent = new Intent(StartActivity.this, MainActivity.class);
        }
        startActivity(intent);
        finish();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        // Immersive Mode
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}
