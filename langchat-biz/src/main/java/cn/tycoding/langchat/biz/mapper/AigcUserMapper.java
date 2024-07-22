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
            COUNT(*) AS totalUser,
            SUM( CASE WHEN YEAR ( create_time ) = YEAR ( CURDATE()) AND MONTH ( create_time ) = MONTH ( CURDATE()) THEN 1 ELSE 0 END ) AS curUser
        FROM
            aigc_user;
    """)
    Dict getCount();
}
