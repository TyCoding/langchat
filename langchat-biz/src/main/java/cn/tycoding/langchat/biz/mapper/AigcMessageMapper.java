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
import cn.tycoding.langchat.biz.entity.AigcMessage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author tycoding
 * @since 2024/1/4
 */
@Mapper
public interface AigcMessageMapper extends BaseMapper<AigcMessage> {

    @Select("""
        WITH RECURSIVE DateRange AS (
            SELECT CURDATE() AS date
            UNION ALL
            SELECT date - INTERVAL 1 DAY
            FROM DateRange
            WHERE date > DATE_SUB(CURDATE(), INTERVAL 31 DAY)
        )
        SELECT
            d.date,
            COALESCE(COUNT(*), 0) AS tokens
        FROM
            DateRange d
        LEFT JOIN
            aigc_message m
        ON
            DATE(m.create_time) = d.date
            AND m.role = 'agent'
        GROUP BY
            d.date
        ORDER BY
            d.date DESC;
    """)
    List<Dict> getReqChartBy30();

    @Select("""
        SELECT
            COALESCE(DATE_FORMAT(create_time, '%Y-%m'), 0) as month,
            COALESCE(COUNT(*), 0) as count
        FROM
            aigc_message
        WHERE
            create_time >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR)
            AND role = 'agent'
        GROUP BY
            month
        ORDER BY
            month ASC;
    """)
    List<Dict> getReqChart();

    @Select("""
        WITH RECURSIVE DateRange AS (
            SELECT CURDATE() AS date
            UNION ALL
            SELECT date - INTERVAL 1 DAY
            FROM DateRange
            WHERE date > DATE_SUB(CURDATE(), INTERVAL 31 DAY)
        )
        SELECT
            d.date,
            COALESCE(SUM(m.tokens), 0) AS tokens
        FROM
            DateRange d
        LEFT JOIN
            aigc_message m
        ON
            DATE(m.create_time) = d.date
            AND m.role = 'agent'
        GROUP BY
            d.date
        ORDER BY
            d.date DESC;
    """)
    List<Dict> getTokenChartBy30();

    @Select("""
        SELECT
            COALESCE(DATE_FORMAT(create_time, '%Y-%m'), 0) as month,
            COALESCE(SUM(tokens), 0) as count
        FROM
            aigc_message
        WHERE
            create_time >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR)
            AND role = 'agent'
        GROUP BY
            month
        ORDER BY
            month ASC;
    """)
    List<Dict> getTokenChart();

    @Select("""
        SELECT
            COALESCE(COUNT(*), 0) AS totalReq,
            COALESCE(SUM( CASE WHEN DATE ( create_time ) = CURDATE() THEN 1 ELSE 0 END ), 0) AS curReq
        FROM
            aigc_message
        WHERE
            role = 'agent'
    """)
    Dict getCount();

    @Select("""
        SELECT
            COALESCE(SUM(tokens), 0) AS totalToken,
            COALESCE(SUM(CASE WHEN DATE(create_time) = CURDATE() THEN tokens ELSE 0 END), 0) AS curToken
        FROM
            aigc_message;
    """)
    Dict getTotalSum();
}

