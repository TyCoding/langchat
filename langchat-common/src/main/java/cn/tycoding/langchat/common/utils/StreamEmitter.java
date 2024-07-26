/*
 * Project: LangChat
 * Author: TyCoding
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

/**
 * @author tycoding
 * @since 2024/1/30
 */
public class StreamEmitter {

    private final SseEmitter emitter;

    public StreamEmitter() {
        emitter = new SseEmitter(600 * 1000L);
    }

    public StreamEmitter(Long timeout) {
        emitter = new SseEmitter(timeout);
    }

    public SseEmitter get() {
        return emitter;
    }

    public void send(Object obj) {
        try {
            emitter.send(obj);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void complete() {
        try {
            emitter.complete();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void error(String message) {
        try {
            emitter.send("Error: " + message);
            emitter.complete();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
