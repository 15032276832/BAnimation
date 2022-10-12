package com.bianbian.tweenanimation.scale;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bianbian.tweenanimation.R;

import androidx.annotation.Nullable;

public class ScaleGroupLl extends LinearLayout {

    Context context;

    LinearLayout groupLl;
    LinearLayout javaLl;
    LinearLayout xmlLl;

    public ScaleGroupLl(Context context) {
        this(context, null);
    }

    public ScaleGroupLl(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScaleGroupLl(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.tween_anim_scale_info_group, this);
        init();
    }


    private void init() {
        groupLl = this.findViewById(R.id.twee_scale_anim_group_li);
        javaLl = this.findViewById(R.id.tween_scale_anim_java_ll);
        xmlLl = this.findViewById(R.id.tween_scale_anim_xml_ll);

        /* java方式实现缩放动画 */
        javaLl.setOnClickListener(view -> {
            toast("代码方式实现：缩放_补间动画\n(0.5到1,3s,恢复)");
            javaScaleAnim();
        });

        /* xml方式实现缩放动画 */
        xmlLl.setOnClickListener(view -> {
            toast("XML方式实现：缩放_补间动画\n(0.5到1,3s,重复两次，恢复)");
            xmlScaleAnim();
        });
    }

    private void javaScaleAnim() {
        Animation rotateAnim = new ScaleAnimation(0.5f, 1f, 0.5f, 1f);
        rotateAnim.setDuration(3000);
        /* 动画结束后时否保持动画结束的状态 */
        rotateAnim.setFillAfter(false);
        groupLl.startAnimation(rotateAnim);
    }

    private void xmlScaleAnim() {
        Animation rotateAnim = AnimationUtils.loadAnimation(context, R.anim.tween_scale);
        groupLl.startAnimation(rotateAnim);
    }

    /* 吐司 */
    private void toast(String str) {
        Toast.makeText(context, str, Toast.LENGTH_LONG).show();
    }

}
