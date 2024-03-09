package cn.tycoding.langchat.biz.mapper;

import cn.tycoding.langchat.biz.entity.LcUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author tycoding
 * @since 2024/1/15
 */
@Mapper
public interface UserMapper extends BaseMapper<LcUser> {

}
