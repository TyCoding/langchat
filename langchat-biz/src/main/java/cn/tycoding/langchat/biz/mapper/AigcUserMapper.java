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

package cn.tycoding.langchat.biz.mapper;

import cn.hutool.core.lang.Dict;
import cn.tycoding.langchat.biz.entity.AigcUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author tycoding
 * @since 2024/1/15
 */
@Mapper
public interface AigcUserMapper extends BaseMapper<AigcUser> {

    @Select("""
        SELECT
            COALESCE(COUNT(*), 0) AS totalUser,
            COALESCE(SUM( CASE WHEN YEAR ( create_time ) = YEAR ( CURDATE()) AND MONTH ( create_time ) = MONTH ( CURDATE()) THEN 1 ELSE 0 END ), 0) AS curUser
        FROM
            aigc_user;
    """)
    Dict getCount();
}
