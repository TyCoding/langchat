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
public interface AuthConst {

    /**
     * 默认密码（重置密码）
     */
    String DEFAULT_PASS = "123456";

    /* 登录表单验证码Key标识 */
    String CAPTCHA_FORM_KEY = "captcha";

    /* 登录验证码Header Key标识 */
    String CAPTCHA_HEADER_KEY = "Captcha-Key";

    /* 验证码错误信息 */
    String CAPTCHA_ERROR_INFO = "验证码不正确";
}
