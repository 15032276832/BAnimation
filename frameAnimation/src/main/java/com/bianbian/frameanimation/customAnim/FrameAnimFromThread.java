package com.bianbian.frameanimation.customAnim;

import android.util.Log;

import com.bianbian.frameanimation.threadPool.ThreadPool;

import java.util.concurrent.Future;

/**
 * 使用线程池，和集合逐帧显示
 */
public class FrameAnimFromThread extends BaseFrameAnim {
    private static final String TAG = "ThreadPoolCustomAnim";

    private Future future;

    private boolean isFutureCancle = true;
    public boolean isAnimStart = false;

    /* 动画开始，开始伴随着重置 */
    public void start() {
        startVerify();
        release();
        isAnimStart = true;
        future = ThreadPool.submit("frameAnim", () -> {
            while (isAnimStart) {
                /* 设置图片 */
                runOnUIThread(() -> {
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

    /* 动画结束 */
    public void stop() {
        isAnimStart = false;
        release();
    }

    protected void release() {
        isAnimStart = false;
        isFutureCancle = true;
        if (future != null) {
            future.cancel(true);
            future = null;
        }
        isFutureCancle = false;
    }
}
