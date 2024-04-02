package cn.tycoding.langchat.biz.mapper;

import cn.tycoding.langchat.biz.entity.SysLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author tycoding
 * @since 2024/1/19
 */
@Mapper
public interface LogMapper extends BaseMapper<SysLog> {

}
