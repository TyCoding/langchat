package cn.tycoding.langchat.aigc.service.impl;

import cn.tycoding.langchat.aigc.entity.AigcKbDoc;
import cn.tycoding.langchat.aigc.mapper.AigcKbDocMapper;
import cn.tycoding.langchat.aigc.service.AigcKbDocService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author tycoding
 * @since 2024/4/15
 */
@Service
@RequiredArgsConstructor
public class AigcKbDocServiceImpl extends ServiceImpl<AigcKbDocMapper, AigcKbDoc> implements AigcKbDocService {

}

