package cn.tycoding.langchat.aigc.service.impl;

import cn.tycoding.langchat.aigc.entity.AigcUser;
import cn.tycoding.langchat.aigc.mapper.AigcUserMapper;
import cn.tycoding.langchat.aigc.service.AigcUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author tycoding
 * @since 2024/1/15
 */
@Service
@RequiredArgsConstructor
public class AigcUserServiceImpl extends ServiceImpl<AigcUserMapper, AigcUser> implements AigcUserService {

}
