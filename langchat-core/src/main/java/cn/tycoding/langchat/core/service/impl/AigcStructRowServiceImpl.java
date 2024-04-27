package cn.tycoding.langchat.core.service.impl;

import cn.tycoding.langchat.core.entity.AigcStructRow;
import cn.tycoding.langchat.core.mapper.AigcStructRowMapper;
import cn.tycoding.langchat.core.service.AigcStructRowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author tycoding
 * @since 2024/1/19
 */
@Service
@RequiredArgsConstructor
public class AigcStructRowServiceImpl extends ServiceImpl<AigcStructRowMapper, AigcStructRow> implements
        AigcStructRowService {

}

