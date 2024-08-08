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

package cn.tycoding.langchat.app.store;

import cn.tycoding.langchat.app.entity.AigcApp;
import cn.tycoding.langchat.app.service.AigcAppService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tycoding
 * @since 2024/8/8
 */
@Slf4j
@Component
@AllArgsConstructor
public class AppStore {

    private static final Map<String, AigcApp> appMap = new HashMap<>();
    private final AigcAppService aigcAppService;

    @PostConstruct
    public void init() {
        log.info("initialize app config list...");
        List<AigcApp> list = aigcAppService.list();
        list.forEach(i -> appMap.put(i.getId(), i));
    }

    public AigcApp get(String appId) {
        return appMap.get(appId);
    }
}
