package com.bianbian.tweenanimation.alphaanimation;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bianbian.tweenanimation.R;

public class CompensationAnimationActivity extends Activity {

    /* 透明度动画 */
    LinearLayout tweenJavaAlphaLl;
    LinearLayout tweenXMLAlphaLl;
    LinearLayout tweenAlphaGroupLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compensation_animation_activity);

        findViewById(R.id.compensation_animation_back_text).setOnClickListener(view -> {
            finish();
        });

        /* 透明度动画演示 */
        tweenAlphaAnim();
    }

    /* 透明度动画演示 */
    private void tweenAlphaAnim() {
        /* 透明度动画 */
        tweenJavaAlphaLl = findViewById(R.id.tween_alpha_anim_java_ll);
        tweenXMLAlphaLl = findViewById(R.id.tween_alpha_anim_xml_ll);
        tweenAlphaGroupLl = findViewById(R.id.twee_alpha_anim_group_li);
        /* 代码方式实现 */
        tweenJavaAlphaLl.setOnClickListener(view -> {
            toast("代码方式实现：透明度_补间动画\n(间隔3s,重复两次,恢复)");
            tweenAlphaAnimJava();
        });
        /* xml方式实现 */
        tweenXMLAlphaLl.setOnClickListener(view -> {
            toast("XML方式实现：透明度_补间动画\n(间隔3s,重复两次,恢复)");
            tweenAlphaAnimXML();
        });
    }

    /* java代码方式实现透明度动画 */
    private void tweenAlphaAnimJava() {
        Animation alphaAnim = new AlphaAnimation(1, (float) 0.1);
        alphaAnim.setDuration(3000);
        /* 动画结束后是否保持当前位置 */
        alphaAnim.setFillAfter(false);
        tweenAlphaGroupLl.startAnimation(alphaAnim);
    }

    /* xml方式实现透明度动画 */
    private void tweenAlphaAnimXML() {
        Animation alphAnim = AnimationUtils.loadAnimation(this, R.anim.tween_alpha);
        tweenAlphaGroupLl.startAnimation(alphAnim);
    }

    /* 吐司 */
    private void toast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}