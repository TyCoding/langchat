package cn.tycoding.langchat.server.service.impl;

import cn.tycoding.langchat.server.entity.LcPrompt;
import cn.tycoding.langchat.server.mapper.PromptMapper;
import cn.tycoding.langchat.server.service.PromptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author tycoding
 * @since 2023/12/19
 */
@Service
@RequiredArgsConstructor
public class PromptServiceImpl extends ServiceImpl<PromptMapper, LcPrompt> implements PromptService {

}

