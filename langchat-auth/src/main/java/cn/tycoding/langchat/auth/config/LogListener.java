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

package cn.tycoding.langchat.auth.config;

import cn.tycoding.langchat.common.auth.event.LogEvent;
import cn.tycoding.langchat.upms.entity.SysLog;
import cn.tycoding.langchat.upms.service.SysLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 监听自定义Log 事件
 *
 * @author tycoding
 * @since 2024/1/5
 */
@Component
@RequiredArgsConstructor
public class LogListener {

    private final SysLogService sysLogService;

    @Async
    @Order
    @EventListener(LogEvent.class)
    public void handler(LogEvent event) {
        SysLog sysLog = (SysLog) event.getSource();
        sysLogService.add(sysLog);
    }
}
