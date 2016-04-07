package com.dreamy.utils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by wangyongxing on 16/4/1.
 */
public class TimerUtil {
    /**
     * 安排指定的任务在指定的延迟后开始进行重复的固定速率执行
     * @param task
     * @param delay 启动时间（毫秒）
     * @param period 重复频率（毫秒）
     */
    public static void schedule(TimerTask task, long delay, long period, String name){
        Timer timer = new Timer(name, true);
        timer.schedule(task, delay, period);
    }
}
