/*
 * Copyright (c) 2024 LangChat. TyCoding All Rights Reserved.
 *
 * Licensed under the GNU Affero General Public License, Version 3 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.gnu.org/licenses/agpl-3.0.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.tycoding.langchat.common.core.task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;


/**
 * @author GB
 * @since 2024-08-22
 */
public class TaskManager {
    private static final ConcurrentHashMap<String, List<Future<?>>> TASK_MAP = new ConcurrentHashMap<>();

    /**
     * 提交任务
     */
    public static void submitTask(String id, Callable<?> function) {
        Future<?> future = AnalysisThreadPool.getThreadPool().submit(function);
        List<Future<?>> orDefault = TASK_MAP.getOrDefault(id, new ArrayList<>());
        orDefault.add(future);
        TASK_MAP.put(id, orDefault);
    }

    /**
     * 弹出任务
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
