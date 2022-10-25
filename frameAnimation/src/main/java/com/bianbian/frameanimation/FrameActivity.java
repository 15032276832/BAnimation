package com.bianbian.frameanimation;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bianbian.frameanimation.threadPool.CustomFrameAnim;

import java.util.ArrayList;
import java.util.List;

public class FrameActivity extends Activity {

    CustomFrameAnim anim = new CustomFrameAnim();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_activity);
        findViewById(R.id.compensation_animation_back_text).setOnClickListener(view -> {
            finish();
        });

        ImageView imageView = findViewById(R.id.frame_img2);
        TextView startText = findViewById(R.id.start_text);
        TextView endText = findViewById(R.id.end_text);

        List<Integer> resList = new ArrayList<>();
        resList.add(R.drawable.frame_1);
        resList.add(R.drawable.frame_2);
        resList.add(R.drawable.frame_3);
        resList.add(R.drawable.frame_4);
        resList.add(R.drawable.frame_5);
        resList.add(R.drawable.frame_6);
        resList.add(R.drawable.frame_7);
        resList.add(R.drawable.frame_8);

        anim = new CustomFrameAnim();
        CustomFrameAnim anim = new CustomFrameAnim();
        anim.setAnimBitmapRes(resList).bindImageView(imageView).setRepeat(true);
        startText.setOnClickListener(v -> {
            anim.start();
        });
        endText.setOnClickListener(v -> {
            anim.stop();
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        anim.stop();
    }
}