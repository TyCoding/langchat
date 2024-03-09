package cn.tycoding.langchat.biz.service.impl;

import cn.tycoding.langchat.biz.entity.LcPrompt;
import cn.tycoding.langchat.biz.mapper.PromptMapper;
import cn.tycoding.langchat.biz.service.PromptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author tycoding
 * @since 2024/1/19
 */
@Service
@RequiredArgsConstructor
public class PromptServiceImpl extends ServiceImpl<PromptMapper, LcPrompt> implements PromptService {

}

