package cn.tycoding.langchat.aigc.mapper;

import cn.hutool.core.lang.Dict;
import cn.tycoding.langchat.aigc.entity.AigcMessage;
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
        SELECT
            DATE(create_time) as date,
            SUM(tokens) as tokens
        FROM
            aigc_message
        WHERE
            create_time >= DATE_SUB(CURDATE(), INTERVAL 30 DAY)
            AND role = 'assistant'
        GROUP BY
            date
        ORDER BY
            date DESC;
    """)
    List<Dict> getReqChartBy30();

    @Select("""
        SELECT
            DATE_FORMAT(create_time, '%Y-%m') as month,
            COUNT(*) as count
        FROM
            aigc_message
        WHERE
            create_time >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR)
            AND role = 'assistant'
        GROUP BY
            month
        ORDER BY
            month ASC;
    """)
    List<Dict> getReqChart();

    @Select("""
        SELECT
            DATE_FORMAT(create_time, '%Y-%m') as month,
            SUM(tokens) as count
        FROM
            aigc_message
        WHERE
            create_time >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR)
            AND role = 'assistant'
        GROUP BY
            month
        ORDER BY
            month ASC;
    """)
    List<Dict> getTokenChart();

    @Select("""
        SELECT
            COUNT(*) AS totalReq,
            SUM( CASE WHEN DATE ( create_time ) = CURDATE() THEN 1 ELSE 0 END ) AS curReq
        FROM
            aigc_message
        WHERE
            role = 'assistant'
    """)
    Dict getCount();

    @Select("""
        SELECT
            SUM( tokens ) AS totalToken,
            SUM( CASE WHEN DATE ( create_time ) = CURDATE() THEN tokens ELSE 0 END ) AS curToken
        FROM
            aigc_message
    """)
    Dict getTotalSum();
}

