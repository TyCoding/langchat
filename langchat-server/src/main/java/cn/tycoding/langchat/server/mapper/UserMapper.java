package cn.tycoding.langchat.server.mapper;

import cn.tycoding.langchat.server.entity.LcUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author tycoding
 * @since 2023/11/15
 */
@Mapper
public interface UserMapper extends BaseMapper<LcUser> {

}
