package cn.tycoding.langchat.server.mapper;

import cn.tycoding.langchat.server.entity.LcLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author tycoding
 * @since 2023/10/19
 */
@Mapper
public interface LogMapper extends BaseMapper<LcLog> {

}
