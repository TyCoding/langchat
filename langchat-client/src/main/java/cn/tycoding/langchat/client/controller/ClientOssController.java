package cn.tycoding.langchat.client.controller;

import cn.tycoding.langchat.biz.entity.AigcOss;
import cn.tycoding.langchat.biz.service.AigcOssService;
import cn.tycoding.langchat.biz.utils.ClientAuthUtil;
import cn.tycoding.langchat.common.annotation.ClientPerm;
import cn.tycoding.langchat.common.utils.R;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tycoding
 * @since 2024/1/19
 */
@RequestMapping("/client/oss")
@RestController
@AllArgsConstructor
public class ClientOssController {

    private final AigcOssService aigcOssService;

    @GetMapping("/list")
    public R list() {
        List<AigcOss> list = aigcOssService.list(Wrappers.<AigcOss>lambdaQuery()
                .eq(AigcOss::getUserId, ClientAuthUtil.getUserId())
                .orderByDesc(AigcOss::getCreateTime)
        );
        return R.ok(list);
    }

    @PutMapping
    @ClientPerm
    public R update(@RequestBody AigcOss data) {
        aigcOssService.updateById(data);
        return R.ok();
    }

    @ClientPerm
    @DeleteMapping("/{id}")
    public R delete(@PathVariable String id) {
        aigcOssService.removeById(id);
        return R.ok();
    }
}
