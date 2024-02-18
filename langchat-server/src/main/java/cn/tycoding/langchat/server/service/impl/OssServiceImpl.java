package cn.tycoding.langchat.server.service.impl;

import cn.tycoding.langchat.server.entity.LcOss;
import cn.tycoding.langchat.server.mapper.OssMapper;
import cn.tycoding.langchat.server.service.OssService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author tycoding
 * @since 2023/8/4
 */
@Service
@RequiredArgsConstructor
public class OssServiceImpl extends ServiceImpl<OssMapper, LcOss> implements OssService {

}

