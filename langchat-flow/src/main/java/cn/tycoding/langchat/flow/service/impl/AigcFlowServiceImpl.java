package cn.tycoding.langchat.flow.service.impl;

import cn.tycoding.langchat.flow.entity.AigcFlow;
import cn.tycoding.langchat.flow.mapper.AigcFlowMapper;
import cn.tycoding.langchat.flow.service.AigcFlowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author tycoding
 * @since 2024/6/17
 */
@Service
@RequiredArgsConstructor
public class AigcFlowServiceImpl extends ServiceImpl<AigcFlowMapper, AigcFlow> implements AigcFlowService {

}

