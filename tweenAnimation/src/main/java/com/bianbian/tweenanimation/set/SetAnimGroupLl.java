package com.bianbian.tweenanimation.set;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bianbian.tweenanimation.R;

import androidx.annotation.Nullable;

public class SetAnimGroupLl extends LinearLayout {

    Context context;

    LinearLayout groupLl;
    LinearLayout javaLl;
    LinearLayout xmlLl;

    public SetAnimGroupLl(Context context) {
        this(context, null);
    }

    public SetAnimGroupLl(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SetAnimGroupLl(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.tween_anim_set_info_group, this);
        init();
    }

    private void init() {
        groupLl = this.findViewById(R.id.tween_set_anim_group_li);
        javaLl = this.findViewById(R.id.tween_set_anim_java_ll);
        xmlLl = this.findViewById(R.id.tween_set_anim_xml_ll);

        /* java方式实现旋转动画 */
        javaLl.setOnClickListener(view -> {
            toast("代码方式实现");
            javaSetAnim();
        });

        /* xml方式实现旋转动画 */
        xmlLl.setOnClickListener(view -> {
            toast("XML方式实现");
            xmSetAnim();
        });
    }

    private void javaSetAnim() {
        /* 参数如果使用自己的插值器传false，否则true */
        AnimationSet animSet = new AnimationSet(true);
        animSet.addAnimation(AnimationUtils.loadAnimation(context, R.anim.tween_translate));
        animSet.addAnimation(AnimationUtils.loadAnimation(context, R.anim.tween_scale));
        animSet.addAnimation(AnimationUtils.loadAnimation(context, R.anim.tween_rotate));
        animSet.addAnimation(AnimationUtils.loadAnimation(context, R.anim.tween_alpha));
        animSet.setInterpolator(context, android.R.anim.anticipate_interpolator);
        groupLl.startAnimation(animSet);
    }

    private void xmSetAnim() {
        Animation animSet = AnimationUtils.loadAnimation(context, R.anim.tween_set_anim);
        groupLl.startAnimation(animSet);
    }

    /* 吐司 */
    private void toast(String str) {
        Toast.makeText(context, str, Toast.LENGTH_LONG).show();
    }
}
