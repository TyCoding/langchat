package cn.tycoding.langchat.common.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author GB
 * @desc
 * @since 2024-08-22
 */
public class AnalysisThreadPool {
    volatile private static ThreadPoolExecutor EXECUTOR = null;

    /**
     * 根据cpu 数量动态配置核心线程数和最大线程数
     */
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    /**
     * 核心线程数
     */
    private static final int CORE_PO0L_SIZE = CPU_COUNT + 1;
    /**
     * 最大线程数
     */
    private static final int MAX_POOL_SIZE = 2 * CPU_COUNT + 1;
    /**
     * 线程队列
     */
    private static final int MAX_LIMIT_JOB_SIZE = 1000;
    /**
     * 非核心线程存活时间1s
     */
    private static final int KEEP_ALIVE = 1;

    public static ThreadPoolExecutor getThreadPool() {
        if (null == EXECUTOR) {
            synchronized (AnalysisThreadPool.class) {
                if (null == EXECUTOR) {
                    EXECUTOR = new ThreadPoolExecutor(
                            CORE_PO0L_SIZE,
                            MAX_POOL_SIZE,
                            KEEP_ALIVE,
                            TimeUnit.MICROSECONDS,
                            new LinkedBlockingDeque<>(MAX_LIMIT_JOB_SIZE),
                            Executors.defaultThreadFactory(),
                            new ThreadPoolExecutor.AbortPolicy() {
                                @Override
                                public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
                                    super.rejectedExecution(r, e);
                                }
                            }
                    );
                }
            }
        }
        return EXECUTOR;
    }

    public static void execute(Runnable runable) {
        getThreadPool().execute(runable);

    }

}
