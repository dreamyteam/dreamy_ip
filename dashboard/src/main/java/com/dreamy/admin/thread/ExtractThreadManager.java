package com.dreamy.admin.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wangyongxing on 16/5/17.
 */
public class ExtractThreadManager {

    protected static ExecutorService service = Executors.newFixedThreadPool(4);

    public static void run(Runnable runnable) {
        service.execute(runnable);
    }
}
