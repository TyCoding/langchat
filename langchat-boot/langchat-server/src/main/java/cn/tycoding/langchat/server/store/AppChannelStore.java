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

package cn.tycoding.langchat.server.store;

import cn.tycoding.langchat.ai.biz.entity.AigcAppApi;
import cn.tycoding.langchat.ai.biz.service.AigcAppApiService;
import cn.tycoding.langchat.common.core.utils.ServletUtil;
import cn.tycoding.langchat.server.consts.AppConst;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tycoding
 * @since 2024/7/30
 */
@Slf4j
@Component
@AllArgsConstructor
public class AppChannelStore {

    private static final Map<String, AigcAppApi> API_MAP = new HashMap<>();

    private final AigcAppApiService appApiService;

    public static AigcAppApi getApiChannel() {
        String token = ServletUtil.getAuthorizationToken();
        return API_MAP.get(token);
    }

    @PostConstruct
    public void init() {
        log.info("initialize app channel config list...");
        List<AigcAppApi> apis = appApiService.list();
        apis.forEach(api -> API_MAP.put(api.getApiKey(), api));
    }

    public void isExpired(String channel) {
        String token = ServletUtil.getAuthorizationToken();

        if (AppConst.CHANNEL_API.equals(channel)) {
            AigcAppApi data = API_MAP.get(token);
            if (data == null) {
                throw new RuntimeException("The ApiKey is empty");
            }
        }
    }
}
