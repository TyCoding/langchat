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

package cn.tycoding.langchat.common.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author tycoding
 * @since 2024/1/2
 */
@Getter
public class AuthException extends RuntimeException {

    private static final long serialVersionUID = -1068765335343416833L;

    private final int code;

    public AuthException() {
        super("没有操作权限");
        this.code = HttpStatus.UNAUTHORIZED.value();
    }

    public AuthException(String message) {
        super(message);
        this.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

    public AuthException(int code, String message) {
        super(message);
        this.code = code;
    }
}
