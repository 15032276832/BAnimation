package com.bianbian.tweenanimation;

import android.app.Activity;
import android.content.Intent;

import com.bianbian.tweenanimation.alphaanimation.CompensationAnimationActivity;

public class TweenAnimModuleRouter {
    /* 跳转补间动画activity */
    public static void startTweenAnimationActivity(Activity activity) {
        activity.startActivity(new Intent(activity, CompensationAnimationActivity.class));
    }
}
