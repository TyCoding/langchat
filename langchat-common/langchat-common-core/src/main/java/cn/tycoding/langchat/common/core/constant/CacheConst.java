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

package cn.tycoding.langchat.common.core.constant;

/**
 * @author tycoding
 * @since 2024/1/15
 */
public interface CacheConst {

    /**
     * 系统所有Redis缓存Key前缀 prefix
     */
    String REDIS_KEY_PREFIX = "langchat:";

    /**
     * Auth缓存前缀
     */
    String AUTH_PREFIX = REDIS_KEY_PREFIX + "auth:";

    /**
     * Auth Session缓存前缀
     */
    String AUTH_SESSION_PREFIX = AUTH_PREFIX + "session:";

    /**
     * Auth Session缓存变量前缀
     */
    String AUTH_USER_INFO_KEY = "USER_INFO";

    /**
     * Auth Token缓存变量前缀
     */
    String AUTH_TOKEN_INFO_KEY = "TOKEN_INFO";

    /**
     * 用户信息缓存
     */
    String USER_DETAIL_KEY = REDIS_KEY_PREFIX + "user_details";

    /**
     * 验证码缓存前缀
     */
    String CAPTCHA_PREFIX = REDIS_KEY_PREFIX + "auth:captcha:";

}
