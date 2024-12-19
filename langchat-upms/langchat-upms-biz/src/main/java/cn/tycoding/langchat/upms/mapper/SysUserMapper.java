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

package cn.tycoding.langchat.upms.mapper;

import cn.hutool.core.lang.Dict;
import cn.tycoding.langchat.upms.dto.UserInfo;
import cn.tycoding.langchat.upms.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 用户表(User)表数据库访问层
 *
 * @author tycoding
 * @since 2024/4/15
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    @Select("""
        SELECT
            COALESCE(COUNT(*), 0) AS totalUser,
            COALESCE(SUM( CASE WHEN YEAR ( create_time ) = YEAR ( CURDATE()) AND MONTH ( create_time ) = MONTH ( CURDATE()) THEN 1 ELSE 0 END ), 0) AS curUser
        FROM
            sys_user;
    """)
    Dict getCount();

    IPage<UserInfo> page(IPage<SysUser> page, UserInfo user, String ignoreId, String ignoreName);
}
