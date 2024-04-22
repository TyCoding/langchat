package cn.tycoding.langchat.aigc.service.impl;

import cn.tycoding.langchat.aigc.entity.AigcPrompt;
import cn.tycoding.langchat.aigc.mapper.AigcPromptMapper;
import cn.tycoding.langchat.aigc.service.AigcPromptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author tycoding
 * @since 2024/1/19
 */
@Service
@RequiredArgsConstructor
public class AigcPromptServiceImpl extends ServiceImpl<AigcPromptMapper, AigcPrompt> implements AigcPromptService {

}

