package cn.tycoding.langchat.common.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @author tycoding
 * @since 2024/1/5
 */
@Slf4j
@Component
public class AsyncFuture {

    private final ThreadPoolExecutor customThreadPool;
    private final Map<String, Map<String, CompletableFuture<Void>>> userTaskMap;

    public AsyncFuture() {
        customThreadPool = new ThreadPoolExecutor(
                10, // 核心线程数
                100, // 最大线程数
                60, // 线程空闲超时时间
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());

        userTaskMap = new ConcurrentHashMap<>();
    }

    public void async(Runnable task, String userId, String taskId) {
        log.info("开启新线程执行 userId={}, taskId={}", userId, task);
        userTaskMap.computeIfAbsent(userId, k -> new ConcurrentHashMap<>());
        CompletableFuture<Void> future = CompletableFuture.runAsync(task, customThreadPool)
                .whenComplete((result, throwable) -> {
                    log.info("新线程执行结束 userId={}, taskId={}", userId, task);
                    // 异步任务完成后移除对应的userId和taskId
                    Map<String, CompletableFuture<Void>> taskMap = userTaskMap.get(userId);
                    if (taskMap != null) {
                        taskMap.remove(taskId);
                        if (taskMap.isEmpty()) {
                            userTaskMap.remove(userId); // 若该userId下没有任务，则移除userId
                        }
                    }
                });
        userTaskMap.get(userId).put(taskId, future);
    }

    public int getPoolSize() {
        return customThreadPool.getPoolSize();
    }

    public int getCount(String userId) {
        return userTaskMap.containsKey(userId) ? userTaskMap.get(userId).size() : 0;
    }

    public void shutdown() {
        customThreadPool.shutdown();
        clearTaskMap();
    }

    public boolean isShutdown() {
        return customThreadPool.isShutdown();
    }

    private void clearTaskMap() {
        userTaskMap.clear();
    }
}
