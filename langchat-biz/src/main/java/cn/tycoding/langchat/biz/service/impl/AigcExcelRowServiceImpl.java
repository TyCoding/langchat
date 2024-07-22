package cn.tycoding.langchat.biz.service.impl;

import cn.tycoding.langchat.biz.entity.AigcExcelRow;
import cn.tycoding.langchat.biz.mapper.AigcExcelRowMapper;
import cn.tycoding.langchat.biz.service.AigcExcelRowService;
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

