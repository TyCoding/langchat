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

package cn.tycoding.langchat.upms.service;

import cn.tycoding.langchat.common.utils.QueryPage;
import cn.tycoding.langchat.upms.dto.UserInfo;
import cn.tycoding.langchat.upms.entity.SysUser;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 用户表(User)表服务接口
 *
 * @author tycoding
 * @since 2024/4/15
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 根据用户名查询
     */
    SysUser findByName(String username);

    /**
     * 根据ID查询
     */
    UserInfo findById(String userId);

    /**
     * 查询用户数据
     */
    UserInfo info(String username);

    /**
     * 条件查询
     */
    List<SysUser> list(SysUser sysUser);

    /**
     * 分页、条件查询
     */
    IPage<UserInfo> page(UserInfo user, QueryPage queryPage);

    /**
     * 校验用户名是否存在
     */
    boolean checkName(UserInfo sysUser);

    void add(UserInfo user);

    void update(UserInfo user);

    void delete(String id, String username);

    void reset(String id, String password, String username);
}
