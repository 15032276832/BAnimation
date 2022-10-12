package com.bianbian.tweenanimation.translate;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bianbian.tweenanimation.R;

import androidx.annotation.Nullable;

public class TranslateGroupLl extends LinearLayout {
    Context context;

    LinearLayout groupLl;
    LinearLayout javaLl;
    LinearLayout xmlLl;

    public TranslateGroupLl(Context context) {
        this(context, null);
    }

    public TranslateGroupLl(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TranslateGroupLl(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.tween_anim_translate_info_group, this);
        init();
    }

    private void init() {
        groupLl = this.findViewById(R.id.twee_translate_anim_group_li);
        javaLl = this.findViewById(R.id.tween_translate_anim_java_ll);
        xmlLl = this.findViewById(R.id.tween_translate_anim_xml_ll);

        /* java方式实现旋转动画 */
        javaLl.setOnClickListener(view -> {
            toast("代码方式实现：平移_补间动画\n(xy均0到250度,3s,恢复,cycle插入器)");
            javaTranslateAnim();
        });

        /* xml方式实现旋转动画 */
        xmlLl.setOnClickListener(view -> {
            toast("XML方式实现：平移_补间动画\n(xy均0到250度,3s,恢复,重复两次,添加先加速后减速插值器)");
            xmlTranslateAnim();
        });
    }

    private void javaTranslateAnim() {
        Animation translateAnimation = new TranslateAnimation(0, 250, 0, 250);
        translateAnimation.setDuration(3000);
        /* 动画结束后时否保持动画结束的状态 */
        translateAnimation.setFillAfter(false);
        /* 添加插入器 */
        translateAnimation.setInterpolator(context, android.R.anim.cycle_interpolator);
        groupLl.startAnimation(translateAnimation);
    }

    private void xmlTranslateAnim() {
        /* 添加了先加速后减速插值器 */
        Animation rotateAnim = AnimationUtils.loadAnimation(context, R.anim.tween_translate);
        groupLl.startAnimation(rotateAnim);
    }

    /* 吐司 */
    private void toast(String str) {
        Toast.makeText(context, str, Toast.LENGTH_LONG).show();
    }
}
