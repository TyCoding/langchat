package cn.tycoding.langchat.server.service.impl;

import cn.tycoding.langchat.core.properties.OssProps;
import cn.tycoding.langchat.core.service.LangDocService;
import cn.tycoding.langchat.core.utils.OssR;
import cn.tycoding.langchat.core.utils.OssUtil;
import cn.tycoding.langchat.common.constant.PromptConst;
import cn.tycoding.langchat.server.utils.TextR;
import cn.tycoding.langchat.server.entity.LcOss;
import cn.tycoding.langchat.server.service.ClientFileService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author tycoding
 * @since 2024/1/4
 */
@Slf4j
@Service
@AllArgsConstructor
public class ClientFileServiceImpl implements ClientFileService {

    private final OssProps ossProps;
    private final LangDocService langDocService;

    @Override
    public void chat(TextR req, PromptConst promptConst) {
//        langDocService.stream(new ChatReq(req.getContent(), PromptStore.get(promptConst), req.getEmitter()));
    }

    @Override
    @SneakyThrows
    public LcOss upload(MultipartFile file) {
        OssR ossR = OssUtil.transfer(ossProps, file);
        LcOss oss = new LcOss();
        BeanUtils.copyProperties(ossR, oss);
//        oss.setUserId(ClientUtil.getClientId());
//        oss.setChannel(FileEnum.INPUT.getChannel());
//        ossMapper.insert(oss);

//        asyncFuture.async(() -> {
//            langDocService.embedding(oss.getPath(), oss.getId());
//        }, ClientUtil.getClientId(), oss.getId());
        return oss;
    }
}
