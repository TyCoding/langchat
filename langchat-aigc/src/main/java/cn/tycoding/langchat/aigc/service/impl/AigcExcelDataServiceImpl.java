package cn.tycoding.langchat.aigc.service.impl;

import cn.tycoding.langchat.aigc.entity.AigcExcelData;
import cn.tycoding.langchat.aigc.mapper.AigcExcelDataMapper;
import cn.tycoding.langchat.aigc.service.AigcExcelDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author tycoding
 * @since 2024/1/19
 */
@Service
@RequiredArgsConstructor
public class AigcExcelDataServiceImpl extends ServiceImpl<AigcExcelDataMapper, AigcExcelData> implements AigcExcelDataService {

}

