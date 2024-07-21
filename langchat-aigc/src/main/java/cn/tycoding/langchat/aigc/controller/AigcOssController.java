package cn.tycoding.langchat.aigc.controller;

import cn.tycoding.langchat.aigc.entity.AigcOss;
import cn.tycoding.langchat.aigc.service.AigcOssService;
import cn.tycoding.langchat.aigc.utils.AigcAuthUtil;
import cn.tycoding.langchat.common.annotation.AigcPerm;
import cn.tycoding.langchat.common.utils.R;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author tycoding
 * @since 2024/1/19
 */
@RequestMapping("/aigc/file")
@RestController
@AllArgsConstructor
public class AigcOssController {

    private final AigcOssService aigcOssService;

    @GetMapping("/list")
    public R list() {
        List<AigcOss> list = aigcOssService.list(Wrappers.<AigcOss>lambdaQuery()
                .eq(AigcOss::getUserId, AigcAuthUtil.getUserId())
                .orderByDesc(AigcOss::getCreateTime)
        );
        return R.ok(list);
    }

    @PostMapping("/upload")
    @AigcPerm
    public R upload(MultipartFile file) {
        return R.ok(aigcOssService.upload(file));
    }

    @PutMapping
//    @SaCheckPermission("aigc:oss:update")
    public R update(@RequestBody AigcOss data) {
        aigcOssService.updateById(data);
        return R.ok();
    }

    @DeleteMapping("/{id}")
//    @SaCheckPermission("aigc:oss:delete")
    public R delete(@PathVariable String id) {
        aigcOssService.removeById(id);
        return R.ok();
    }
}
