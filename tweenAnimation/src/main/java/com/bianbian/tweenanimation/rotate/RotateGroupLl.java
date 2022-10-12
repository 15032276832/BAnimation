package com.bianbian.tweenanimation.rotate;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bianbian.tweenanimation.R;

import androidx.annotation.Nullable;

public class RotateGroupLl extends LinearLayout {

    Context context;

    LinearLayout groupLl;
    LinearLayout javaLl;
    LinearLayout xmlLl;

    public RotateGroupLl(Context context) {
        this(context, null);
    }

    public RotateGroupLl(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RotateGroupLl(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.tween_anim_rotate_info_group, this);
        init();
    }

    private void init() {
        groupLl = this.findViewById(R.id.twee_rotate_anim_group_li);
        javaLl = this.findViewById(R.id.tween_rotate_anim_java_ll);
        xmlLl = this.findViewById(R.id.tween_rotate_anim_xml_ll);

        /* java方式实现旋转动画 */
        javaLl.setOnClickListener(view -> {
            toast("代码方式实现：旋转_补间动画\n(0到45度,3s,恢复)");
            javaRotateAnim();
        });

        /* xml方式实现旋转动画 */
        xmlLl.setOnClickListener(view -> {
            toast("XML方式实现：旋转_补间动画\n(0到45度,3s,重复两次，恢复)");
            xmlRotateAnim();
        });
    }

    private void javaRotateAnim() {
        Animation rotateAnim = new RotateAnimation(0, 45);
        rotateAnim.setDuration(3000);
        /* 动画结束后时否保持动画结束的状态 */
        rotateAnim.setFillAfter(false);
        groupLl.startAnimation(rotateAnim);
    }

    private void xmlRotateAnim() {
        Animation rotateAnim = AnimationUtils.loadAnimation(context, R.anim.tween_rotate);
        groupLl.startAnimation(rotateAnim);
    }

    /* 吐司 */
    private void toast(String str) {
        Toast.makeText(context, str, Toast.LENGTH_LONG).show();
    }

}
