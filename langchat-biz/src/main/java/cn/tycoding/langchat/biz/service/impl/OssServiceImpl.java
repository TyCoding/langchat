package cn.tycoding.langchat.biz.service.impl;

import cn.tycoding.langchat.biz.entity.SysOss;
import cn.tycoding.langchat.biz.mapper.OssMapper;
import cn.tycoding.langchat.biz.service.OssService;
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
public class OssServiceImpl extends ServiceImpl<OssMapper, SysOss> implements OssService {

    private final OssProps ossProps;

    @Override
    public SysOss upload(MultipartFile file) {
        OssR ossR = OssUtil.transfer(ossProps, file);
        SysOss oss = new SysOss();
        BeanUtils.copyProperties(ossR, oss);
        //TODO 增加userId
        this.save(oss);
        return oss;
    }
}

