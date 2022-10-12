package com.bianbian.tweenanimation;

import android.app.Activity;
import android.os.Bundle;

import com.bianbian.tweenanimation.R;

public class CompensationAnimationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compensation_animation_activity);

        findViewById(R.id.compensation_animation_back_text).setOnClickListener(view -> {
            finish();
        });
    }
}