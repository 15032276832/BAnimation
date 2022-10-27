package com.bianbian.frameanimation;

import android.app.Activity;
import android.os.Bundle;

public class FrameActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_activity);
        findViewById(R.id.compensation_animation_back_text).setOnClickListener(view -> {
            finish();
        });
    }
}