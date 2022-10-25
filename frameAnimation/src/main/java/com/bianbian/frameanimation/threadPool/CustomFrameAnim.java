package com.bianbian.frameanimation.threadPool;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * 使用线程池，和集合逐帧显示
 */
public class CustomFrameAnim {
    private static final String TAG = "ThreadPoolCustomAnim";
    /* 每一帧显示时间间隔 ms */
    private long duration = 200l;
    /* 是否重复 */
    private boolean isRepeat = false;

    /* 当前播放帧 下标 */
    private int index = 0;

    /* 动画资源列表 */
    private List<Integer> imgResList = new ArrayList<>();

    /* 执行动画的image */
    private ImageView animImgView;

    private Future future;

    private boolean isFutureCancle = true;

    private Handler handler = new Handler(Looper.getMainLooper());

    private boolean isAnimStart = false;

    /* 动画开始，开始伴随着重置 */
    public void start() {
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
        isAnimStart = true;
        cancleFuture();
        future = AnimThreadPool.submit("frameAnim", () -> {
            while (isAnimStart) {
                /* 设置图片 */
                handler.post(() -> {
                    animImgView.setImageResource(imgResList.get(index));
                    /* 配置下一帧 */
                    addIndex();
                });
                /* 暂停duration */
                if (isFutureCancle) {
                    return;
                }
                try {
                    Thread.sleep(duration);
                } catch (InterruptedException err) {
                    Log.e(TAG, err.getMessage());
                }
            }
        });
    }

    private void addIndex() {
        index++;
        if (index < imgResList.size()) {
            return;
        }
        if (isRepeat) {
            index = 0;
            return;
        }
        index = 0;
        isAnimStart = false;
        cancleFuture();
    }

    private void cancleFuture() {
        isFutureCancle = true;
        if (future != null) {
            future.cancel(true);
            future = null;
        }
        isFutureCancle = false;
    }

    /* 动画结束 */
    public void stop() {
        isAnimStart = false;
        cancleFuture();
    }

    /* 设置动画背景资源 */
    public CustomFrameAnim setAnimBitmapRes(List<Integer> imgListRes) {
        this.imgResList = imgListRes;
        return this;
    }

    /* 绑定ImageView */
    public CustomFrameAnim bindImageView(ImageView imageView) {
        this.animImgView = imageView;
        return this;
    }

    /* 是否重复，默认否 */
    public CustomFrameAnim setRepeat(boolean repeat) {
        isRepeat = repeat;
        return this;
    }
}
