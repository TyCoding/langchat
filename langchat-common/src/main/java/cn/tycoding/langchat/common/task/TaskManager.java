package cn.tycoding.langchat.common.task;

import cn.tycoding.langchat.common.threadpool.AnalysisThreadPool;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;


/**
 * @author GB
 * @desc
 * @since 2024-08-22
 */
public class TaskManager {
    private static final ConcurrentHashMap<String, List<Future<?>>> TASK_MAP = new ConcurrentHashMap<>();

    /**
     * 提交任务
     *
     * @param id
     * @param function
     */
    public static void submitTask(String id, Callable<?> function) {
        Future<?> future = AnalysisThreadPool.getThreadPool().submit(function);
        List<Future<?>> orDefault = TASK_MAP.getOrDefault(id, new ArrayList<>());
        orDefault.add(future);
        TASK_MAP.put(id, orDefault);
    }

    /**
     * 弹出任务
     *
     * @param id
     * @return
     */
    public void popTaskResult(String id) {
        TASK_MAP.remove(id);
    }

    public int getCount(String id) {
        if (TASK_MAP.containsKey(id)) {
            Collection<?> collection = TASK_MAP.get(id);
            return collection != null ? collection.size() : 0;
        }
        return 0;
    }
}
