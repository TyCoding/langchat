package cn.tycoding.langchat.biz.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.tycoding.langchat.biz.entity.AigcOss;
import cn.tycoding.langchat.biz.mapper.AigcOssMapper;
import cn.tycoding.langchat.biz.service.AigcOssService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.dromara.x.file.storage.core.FileInfo;
import org.dromara.x.file.storage.core.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @author tycoding
 * @since 2024/1/4
 */
@Service
@RequiredArgsConstructor
public class AigcOssServiceImpl extends ServiceImpl<AigcOssMapper, AigcOss> implements AigcOssService {

    private final FileStorageService fileStorageService;

    @Override
    public AigcOss upload(MultipartFile file, String userId) {
        FileInfo info = fileStorageService.of(file)
                .setPath(DateUtil.format(new Date(), DatePattern.PURE_DATE_PATTERN))
                .upload();
        AigcOss oss = BeanUtil.copyProperties(info, AigcOss.class);
        oss.setOssId(info.getId());
        oss.setUserId(userId);
        this.save(oss);
        return oss;
    }
}

