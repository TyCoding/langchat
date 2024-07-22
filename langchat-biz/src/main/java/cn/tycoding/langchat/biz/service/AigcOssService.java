package cn.tycoding.langchat.biz.service;

import cn.tycoding.langchat.biz.entity.AigcOss;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author tycoding
 * @since 2024/1/4
 */
public interface AigcOssService extends IService<AigcOss> {

    AigcOss upload(MultipartFile file, String userId);
}

