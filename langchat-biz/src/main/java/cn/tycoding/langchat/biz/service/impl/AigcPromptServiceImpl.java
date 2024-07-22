package cn.tycoding.langchat.biz.service.impl;

import cn.tycoding.langchat.biz.entity.AigcPrompt;
import cn.tycoding.langchat.biz.mapper.AigcPromptMapper;
import cn.tycoding.langchat.biz.service.AigcPromptService;
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

