package cn.tycoding.langchat.biz.service.impl;

import cn.tycoding.langchat.biz.entity.AigcOss;
import cn.tycoding.langchat.biz.mapper.AigcOssMapper;
import cn.tycoding.langchat.biz.service.AigcOssService;
import cn.tycoding.langchat.common.dto.OssR;
import cn.tycoding.langchat.common.properties.OssProps;
import cn.tycoding.langchat.common.utils.OssUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author tycoding
 * @since 2024/1/4
 */
@Service
@RequiredArgsConstructor
public class AigcOssServiceImpl extends ServiceImpl<AigcOssMapper, AigcOss> implements AigcOssService {

    private final OssProps ossProps;

    @Override
    public AigcOss upload(MultipartFile file, String userId) {
        OssR ossR = OssUtil.transfer(ossProps, file);
        AigcOss oss = new AigcOss();
        BeanUtils.copyProperties(ossR, oss);
        oss.setUserId(userId);
        this.save(oss);
        return oss;
    }
}

