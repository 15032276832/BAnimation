package com.bianbian.frameanimation.animGroupView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bianbian.frameanimation.R;
import com.bianbian.frameanimation.customAnim.FrameAnimFromThread;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class FrameAnimFromThreadInfoGroup extends LinearLayout {

    Context context;
    ImageView imageView;
    TextView startText;
    TextView endText;
    FrameAnimFromThread anim = new FrameAnimFromThread();

    public FrameAnimFromThreadInfoGroup(Context context) {
        this(context, null);
    }

    public FrameAnimFromThreadInfoGroup(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public FrameAnimFromThreadInfoGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.frame_anim_thread_group, this);
        bindView();
        setListener();
        initData();
    }

    private void bindView() {
        imageView = findViewById(R.id.frame_img2);
        startText = findViewById(R.id.start_text);
        endText = findViewById(R.id.end_text);
    }

    private void setListener() {
        startText.setOnClickListener(v -> {
            anim.start();
        });
        endText.setOnClickListener(v -> {
            anim.stop();
        });
    }

    private void initData() {
        List<Integer> resList = new ArrayList<>();
        resList.add(R.drawable.frame_1);
        resList.add(R.drawable.frame_2);
        resList.add(R.drawable.frame_3);
        resList.add(R.drawable.frame_4);
        resList.add(R.drawable.frame_5);
        resList.add(R.drawable.frame_6);
        resList.add(R.drawable.frame_7);
        resList.add(R.drawable.frame_8);

        anim = new FrameAnimFromThread();
        anim
                .setAnimBitmapRes(resList)
                .bindImageView(imageView)
                .setRepeat(true)
                .setDuration(200l);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        anim.stop();
    }
}
