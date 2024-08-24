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

package cn.tycoding.langchat.common.utils;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

/**
 * @author tycoding
 * @since 2024/1/30
 */
public class StreamEmitter {

    private final SseEmitter emitter;

    public StreamEmitter() {
        emitter = new SseEmitter(5 * 60 * 1000L);
    }

    public SseEmitter get() {
        return emitter;
    }

    public SseEmitter streaming(final ExecutorService executor, Runnable func) {
//        ExecutorService executor = Executors.newSingleThreadExecutor();

        emitter.onCompletion(() -> {
            System.out.println("SseEmitter 完成");
            executor.shutdownNow();
        });

        emitter.onError((e) -> {
            System.out.println("SseEmitter 出现错误: " + e.getMessage());
            executor.shutdownNow();
        });

        emitter.onTimeout(() -> {
            System.out.println("SseEmitter 超时");
            emitter.complete();
            executor.shutdownNow();
        });
        executor.execute(() -> {
            try {
                func.run();
            } catch (Exception e) {
                System.out.println("捕获到异常: " + e.getMessage());
                emitter.completeWithError(e);
                Thread.currentThread().interrupt();
            } finally {
                if (!executor.isShutdown()) {
                    executor.shutdownNow();
                }
            }
        });
        return emitter;
    }

    public void send(Object obj) {
        try {
            emitter.send(obj);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void complete() {
        emitter.complete();
    }

    public void error(String message) {
        try {
            emitter.send("Error: " + message);
            emitter.complete();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
