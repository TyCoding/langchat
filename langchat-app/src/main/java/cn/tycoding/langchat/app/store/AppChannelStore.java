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

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.tycoding.langchat.app.consts.AppConst;
import cn.tycoding.langchat.app.entity.AigcAppApi;
import cn.tycoding.langchat.app.entity.AigcAppWeb;
import cn.tycoding.langchat.app.service.AigcAppApiService;
import cn.tycoding.langchat.app.service.AigcAppWebService;
import cn.tycoding.langchat.common.exception.ServiceException;
import cn.tycoding.langchat.common.utils.ServletUtil;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author tycoding
 * @since 2024/7/30
 */
@Slf4j
@Component
@AllArgsConstructor
public class AppChannelStore {

    private static final Map<String, AigcAppApi> API_MAP = new HashMap<>();
    private static final Map<String, AigcAppWeb> WEB_MAP = new HashMap<>();

    private final AigcAppApiService appApiService;
    private final AigcAppWebService appWebService;
    private final RedisTemplate stringRedisTemplate;

    public static AigcAppApi getApiChannel() {
        String token = ServletUtil.getAuthorizationToken();
        return API_MAP.get(token);
    }

    public static AigcAppWeb getWebChannel() {
        String token = ServletUtil.getAuthorizationToken();
        return WEB_MAP.get(token);
    }

    @PostConstruct
    public void init() {
        log.info("initialize app channel config list...");
        List<AigcAppApi> apis = appApiService.list();
        apis.forEach(api -> API_MAP.put(api.getApiKey(), api));

        List<AigcAppWeb> webs = appWebService.list();
        webs.forEach(web -> WEB_MAP.put(web.getApiKey(), web));
    }


    /**
     * 限流校验
     *
     * @param channel
     */
    public void currentLimiting(String channel) {
        try {
            String token = ServletUtil.getAuthorizationToken();
            Integer reqLimit = 0;
            if (AppConst.CHANNEL_API.equals(channel)) {
                AigcAppApi data = API_MAP.get(token);
                if (data == null) {
                    throw new RuntimeException("The ApiKey is empty");
                }
                reqLimit = data.getReqLimit();
            }

            if (AppConst.CHANNEL_WEB.equals(channel)) {
                AigcAppWeb data = WEB_MAP.get(token);
                if (data == null) {
                    throw new RuntimeException("The ApiKey is empty");
                }
                reqLimit = data.getReqLimit();
            }
            if (ObjectUtil.isEmpty(reqLimit)) {
                return;
            }

            long currentTime = System.currentTimeMillis();
            String lua = "local window_start = ARGV[1] - 60000\n" +
                    "redis.call('ZREMRANGEBYSCORE', KEYS[1], '-inf', window_start)\n" +
                    "local current_requests = redis.call('ZCARD', KEYS[1])\n" +
                    "if current_requests < tonumber(ARGV[2]) then\n" +
                    "  redis.call('ZADD', KEYS[1], ARGV[1], ARGV[1])\n" +
                    "  return 1\n" +
                    "else\n" +
                    "  return 0\n" +
                    "end";
            RedisScript<Long> integerRedisScript = new DefaultRedisScript<>(lua, Long.class);
            Long result = (Long) stringRedisTemplate.execute(integerRedisScript,
                    Collections.singletonList(token),
                    String.valueOf(currentTime),
                    String.valueOf(reqLimit)
            );
            if (result != 1) {
                throw new ServiceException("The api is restricted");
            }
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            log.error("校验限流出错", e);
        }

    }

    public void isExpired(String channel) {
        String token = ServletUtil.getAuthorizationToken();

        Date expired = null;
        if (AppConst.CHANNEL_API.equals(channel)) {
            AigcAppApi data = API_MAP.get(token);
            if (data == null) {
                throw new RuntimeException("The ApiKey is empty");
            }
            expired = data.getExpired();
        }

        if (AppConst.CHANNEL_WEB.equals(channel)) {
            AigcAppWeb data = WEB_MAP.get(token);
            if (data == null) {
                throw new RuntimeException("The ApiKey is empty");
            }
            expired = data.getExpired();
        }


        if (expired != null) {
            int is = DateUtil.compare(new Date(), expired);
            if (is > 0) {
                // expired
                throw new ServiceException("The ApiKey is expired");
            }
        }
    }
}
