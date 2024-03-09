package cn.tycoding.langchat.biz.mapper;

import cn.tycoding.langchat.biz.entity.LcConversation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author tycoding
 * @since 2024/1/4
 */
@Mapper
public interface ConversationMapper extends BaseMapper<LcConversation> {

}

