package cn.tycoding.langchat.biz.service.impl;

import cn.tycoding.langchat.biz.entity.SysOss;
import cn.tycoding.langchat.biz.mapper.OssMapper;
import cn.tycoding.langchat.biz.service.OssService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author tycoding
 * @since 2024/1/4
 */
@Service
@RequiredArgsConstructor
public class OssServiceImpl extends ServiceImpl<OssMapper, SysOss> implements OssService {

}

