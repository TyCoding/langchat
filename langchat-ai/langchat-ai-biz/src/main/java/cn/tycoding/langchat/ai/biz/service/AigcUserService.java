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

package cn.tycoding.langchat.ai.biz.service;

import cn.tycoding.langchat.ai.biz.entity.AigcUser;
import cn.tycoding.langchat.common.core.utils.QueryPage;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author tycoding
 * @since 2024/1/15
 */
public interface AigcUserService extends IService<AigcUser> {

    /**
     * 获取账号登录信息
     */
    AigcUser info(String username);

    /**
     * 校验用户登录信息
     */
    Boolean checkName(AigcUser data);

    /**
     * 账户列表
     */
    IPage<AigcUser> page(AigcUser data, QueryPage queryPage);
}
