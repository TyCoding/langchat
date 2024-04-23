package cn.tycoding.langchat.aigc.service.impl;

import cn.tycoding.langchat.aigc.entity.AigcKb;
import cn.tycoding.langchat.aigc.mapper.AigcKbMapper;
import cn.tycoding.langchat.aigc.service.AigcKbService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author tycoding
 * @since 2024/4/15
 */
@Service
@RequiredArgsConstructor
public class AigcKbServiceImpl extends ServiceImpl<AigcKbMapper, AigcKb> implements AigcKbService {

}

