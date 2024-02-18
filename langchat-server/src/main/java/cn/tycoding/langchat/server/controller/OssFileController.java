package cn.tycoding.langchat.server.controller;

import cn.tycoding.langchat.core.utils.StreamEmitter;
import cn.tycoding.langchat.server.common.constant.PromptConst;
import cn.tycoding.langchat.server.common.utils.ChatR;
import cn.tycoding.langchat.server.common.utils.R;
import cn.tycoding.langchat.server.entity.LcOss;
import cn.tycoding.langchat.server.service.ClientFileService;
import cn.tycoding.langchat.server.service.OssService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * @author tycoding
 * @since 2023/12/19
 */
@RequestMapping("/langchat/file")
@RestController
@AllArgsConstructor
public class OssFileController {

    private final ClientFileService clientFileService;
    private final OssService ossService;

    @GetMapping("/list")
    public R list() {
        List<LcOss> list = ossService.list(Wrappers.<LcOss>lambdaQuery()
//                .eq(LcOss::getUserId, ClientUtil.getClientId())
//                .eq(LcOss::getChannel, FileEnum.INPUT.getChannel())
                .orderByDesc(LcOss::getCreateTime)
        );
        return R.ok(list);
    }

    @PostMapping("/upload")
    public R upload(MultipartFile file) {
        LcOss oss = clientFileService.upload(file);
        return R.ok(oss);
    }

    @PostMapping("/chat")
    public SseEmitter chat(@RequestBody ChatR req) {
        StreamEmitter emitter = new StreamEmitter();
        req.setEmitter(emitter);
        clientFileService.chat(req, PromptConst.DOCUMENT);
        return emitter.get();
    }

    @GetMapping("/task")
    public R task() {
//        int count = asyncFuture.getCount(ClientUtil.getClientId());
//        return R.ok(count);
        return R.ok();
    }

    @PutMapping
    public R update(@RequestBody LcOss data) {
        ossService.updateById(data);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable String id) {
        ossService.removeById(id);
        return R.ok();
    }
}
