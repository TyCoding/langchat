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

package cn.tycoding.langchat.upms.service.impl;

import cn.tycoding.langchat.upms.entity.SysRole;
import cn.tycoding.langchat.upms.entity.SysUser;
import cn.tycoding.langchat.upms.entity.SysUserRole;
import cn.tycoding.langchat.upms.mapper.SysUserRoleMapper;
import cn.tycoding.langchat.upms.service.SysUserRoleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户角色关联表(UserRole)表服务实现类
 *
 * @author tycoding
 * @since 2024/4/15
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    @Override
    public List<SysUser> getUserListByRoleId(String roleId) {
        return baseMapper.getUserListByRoleId(roleId);
    }

    @Override
    public List<SysRole> getRoleListByUserId(String userId) {
        return baseMapper.getRoleListByUserId(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUserRolesByUserId(String userId) {
        baseMapper.delete(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, userId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUserRolesByRoleId(String roleId) {
        baseMapper.delete(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getRoleId, roleId));
    }
}
