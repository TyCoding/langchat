package cn.tycoding.langchat.server.mapper;

import cn.tycoding.langchat.server.entity.LcPrompt;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author tycoding
 * @since 2023/12/19
 */
@Mapper
public interface PromptMapper extends BaseMapper<LcPrompt> {

}

