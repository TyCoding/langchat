package cn.tycoding.langchat.aigc.service.impl;

import cn.tycoding.langchat.aigc.entity.AigcExcelRow;
import cn.tycoding.langchat.aigc.mapper.AigcExcelRowMapper;
import cn.tycoding.langchat.aigc.service.AigcExcelRowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author tycoding
 * @since 2024/1/19
 */
@Service
@RequiredArgsConstructor
public class AigcExcelRowServiceImpl extends ServiceImpl<AigcExcelRowMapper, AigcExcelRow> implements
        AigcExcelRowService {

}

