package cn.tycoding.langchat.server.mapper;

import cn.tycoding.langchat.server.entity.LcMessage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author tycoding
 * @since 2023/8/4
 */
@Mapper
public interface MessageMapper extends BaseMapper<LcMessage> {

}

