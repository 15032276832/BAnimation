package com.bianbian.frameanimation.customAnim;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseFrameAnim {
    private static final String TAG = "BaseFrameAnim";
    private Handler handler = new Handler(Looper.getMainLooper());

    /* 每一帧显示时间间隔 ms */
    public long duration = 200l;
    /* 是否重复 */
    public boolean isRepeat = false;

    /* 执行动画的image */
    public ImageView animImgView;

    /* 动画资源列表 */
    public List<Integer> imgResList = new ArrayList<>();

    /* 当前播放帧 下标 */
    public int index = 0;

    /* 运行在主线程 */
    public void runOnUIThread(Runnable runnable) {
        handler.post(runnable);
    }

    /* 设置动画背景资源 */
    public BaseFrameAnim setAnimBitmapRes(List<Integer> imgListRes) {
        this.imgResList = imgListRes;
        return this;
    }

    /* 绑定ImageView */
    public BaseFrameAnim bindImageView(ImageView imageView) {
        this.animImgView = imageView;
        return this;
    }

    /* 是否重复，默认否 */
    public BaseFrameAnim setRepeat(boolean repeat) {
        isRepeat = repeat;
        return this;
    }

    /* 设置每一帧的时间间隔 */
    public BaseFrameAnim setDuration(long duration) {
        this.duration = duration;
        return this;
    }

    /* 动画开始，开始伴随着重置 */
    protected void startVerify() {
        Log.e(TAG, "start");
        /* 设置view */
        if (animImgView == null) {
            Log.e(TAG, "animImg null");
            return;
        }
        if (imgResList == null || imgResList.size() == 0) {
            Log.e(TAG, "imgResList null ");
            return;
        }
    }

    protected void addIndex() {
        index++;
        if (index < imgResList.size()) {
            return;
        }
        if (isRepeat) {
            index = 0;
            return;
        }
        index = 0;
        release();
    }

    protected abstract void release();
}
