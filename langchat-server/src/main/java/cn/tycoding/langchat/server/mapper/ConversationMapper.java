package cn.tycoding.langchat.server.mapper;

import cn.tycoding.langchat.server.entity.LcConversation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author tycoding
 * @since 2023/8/4
 */
@Mapper
public interface ConversationMapper extends BaseMapper<LcConversation> {

}

