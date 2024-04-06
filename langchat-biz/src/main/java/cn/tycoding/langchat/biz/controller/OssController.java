package cn.tycoding.langchat.biz.controller;

import cn.tycoding.langchat.biz.entity.SysOss;
import cn.tycoding.langchat.biz.service.OssService;
import cn.tycoding.langchat.common.utils.R;
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

/**
 * @author tycoding
 * @since 2024/1/19
 */
@RequestMapping("/langchat/file")
@RestController
@AllArgsConstructor
public class OssController {

    private final OssService ossService;

    @GetMapping("/list")
    public R list() {
        List<SysOss> list = ossService.list(Wrappers.<SysOss>lambdaQuery()
                .orderByDesc(SysOss::getCreateTime)
        );
        return R.ok(list);
    }

    @PostMapping("/upload")
    public R upload(MultipartFile file) {
        return R.ok(ossService.upload(file));
    }

    @PutMapping
    public R update(@RequestBody SysOss data) {
        ossService.updateById(data);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable String id) {
        ossService.removeById(id);
        return R.ok();
    }
}
