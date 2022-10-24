package com.bianbian.frameanimation;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class FrameAnimGroup1 extends LinearLayout {

    Context context;

    TextView runText;
    TextView cleanText;
    ImageView runImg;

    AnimationDrawable animationDrawable;

    public FrameAnimGroup1(Context context) {
        this(context, null);
    }

    public FrameAnimGroup1(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public FrameAnimGroup1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.frame_anim_group_1_rl, this);
        init();
    }

    private void init() {
        runText = findViewById(R.id.frame_anim_group_1_run_text);
        runImg = findViewById(R.id.frame_anim_group_1_img);
        cleanText = findViewById(R.id.frame_anim_group_1_clean_text);

        runImg.setBackgroundResource(R.drawable.frame_list);
        animationDrawable = (AnimationDrawable) runImg.getBackground();

        runText.setOnClickListener(view -> {
            animationDrawable.start();
        });
        cleanText.setOnClickListener(view -> {
            /* 停止动画 */
            if (animationDrawable != null) {
                animationDrawable.stop();
            }
            runImg.clearAnimation();
            /* 重新设置 */
            runImg.setBackgroundResource(R.drawable.frame_list);
            animationDrawable = (AnimationDrawable) runImg.getBackground();
        });
    }
}
