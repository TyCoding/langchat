package cn.tycoding.langchat.aigc.mapper;

import cn.tycoding.langchat.aigc.dto.MsgChartDto;
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
        GROUP BY
            date
        ORDER BY
            date DESC;
    """)
    List<MsgChartDto> charts();
}

