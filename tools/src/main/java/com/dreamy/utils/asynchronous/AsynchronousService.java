package com.dreamy.utils.asynchronous;


import java.util.concurrent.*;

/**
 * 异步任务总控制器
 * Created by wangyongxing on 16/4/11.
 */
public class AsynchronousService {
    private static ExecutorCompletionService<Callable<?>> ecs = null;
    private static ExecutorService pool = null;

    static {
        pool = Executors.newFixedThreadPool(70);
        ecs = new ExecutorCompletionService<Callable<?>>(pool);
    }

    /**
     * 提交一个异步任务
     *
     * @param thread
     * @return
     */
    public static Object submit(ObjectCallable thread) {
        //默认超时为5分钟
        return submit(thread, 5 * 60 * 1000);
    }

    public static Object submit(ObjectCallable thread, long timeout) {
        Future<?> futureTask = ecs.submit(thread);
//        try {
//            return futureTask.get(timeout, TimeUnit.MILLISECONDS);
//        } catch (Exception e) {
//            futureTask.cancel(true);
//        }
        return futureTask;
    }

    public static Object take() throws InterruptedException {
        return ecs.take();
    }

    /**
     * 获取线程池
     *
     * @return
     */
    public static ThreadPoolExecutor getPoolExecutor() {
        return (ThreadPoolExecutor) pool;
    }

    public static void shutdown() {
        pool.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
//	    for (int i = 0; i < 85; i++) {
        AsynchronousService.submit(new ObjectCallable() {
            @Override
            public Object run() throws Exception {
//	                System.out.println("start");
                Thread.sleep(2 * 1000);
//	                System.out.println("end");
                return null;
            }
        }, 1 * 1000);
//        }
//
//        System.out.println("getPoolSize:" + getPoolExecutor().getPoolSize());
//        Thread.sleep(1 * 1000);
//        System.out.println("getActiveCount:" + getPoolExecutor().getActiveCount());
//        Thread.sleep(3 * 1000);
//        System.out.println("getActiveCount:" + getPoolExecutor().getActiveCount());
//        System.out.println("getPoolSize:" + getPoolExecutor().getPoolSize());
    }
}
