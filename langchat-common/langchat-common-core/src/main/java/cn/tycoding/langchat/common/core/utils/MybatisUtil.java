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

package cn.tycoding.langchat.common.core.utils;

import cn.hutool.core.lang.Dict;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author tycoding
 * @since 2024/1/2
 */
public class MybatisUtil {

    /**
     * 分页查询：格式化响应数据结构
     *
     * @param page 分页数据
     * @return 格式化后的Map对象
     */
    public static Dict getData(IPage<?> page) {
        return Dict.create().set("rows", page.getRecords()).set("total", (int) page.getTotal());
    }

    /**
     * QueryPage对象转换为Page对象
     */
    public static <T> IPage<T> wrap(T t, QueryPage query) {
        return new Page<T>(query.getPage(), query.getLimit());
    }
}
