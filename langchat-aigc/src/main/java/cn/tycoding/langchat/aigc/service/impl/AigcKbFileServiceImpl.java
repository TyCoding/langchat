package cn.tycoding.langchat.aigc.service.impl;

import cn.tycoding.langchat.aigc.entity.AigcKbFile;
import cn.tycoding.langchat.aigc.mapper.AigcKbFileMapper;
import cn.tycoding.langchat.aigc.service.AigcKbFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author tycoding
 * @since 2024/4/15
 */
@Service
@RequiredArgsConstructor
public class AigcKbFileServiceImpl extends ServiceImpl<AigcKbFileMapper, AigcKbFile> implements AigcKbFileService {

}

