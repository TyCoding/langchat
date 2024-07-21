package cn.tycoding.langchat.aigc.controller;

import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.aigc.entity.AigcMessage;
import cn.tycoding.langchat.aigc.service.AigcMessageService;
import cn.tycoding.langchat.common.annotation.AigcPerm;
import cn.tycoding.langchat.common.utils.MybatisUtil;
import cn.tycoding.langchat.common.utils.QueryPage;
import cn.tycoding.langchat.common.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author tycoding
 * @since 2024/1/19
 */
@RequestMapping("/aigc/message")
@RestController
@AllArgsConstructor
public class AigcMessageController {

    private final AigcMessageService aigcMessageService;

    @GetMapping("/page")
    public R list(AigcMessage data, QueryPage queryPage) {
        LambdaQueryWrapper<AigcMessage> queryWrapper = Wrappers.<AigcMessage>lambdaQuery()
                .like(!StrUtil.isBlank(data.getMessage()), AigcMessage::getMessage, data.getMessage())
                .like(!StrUtil.isBlank(data.getUsername()), AigcMessage::getUsername, data.getUsername())
                .eq(!StrUtil.isBlank(data.getRole()), AigcMessage::getRole, data.getRole())
                .orderByDesc(AigcMessage::getCreateTime);
        IPage<AigcMessage> iPage = aigcMessageService.page(MybatisUtil.wrap(data, queryPage), queryWrapper);
        return R.ok(MybatisUtil.getData(iPage));
    }

    @GetMapping("/{id}")
    public R getById(@PathVariable String id) {
        return R.ok(aigcMessageService.getById(id));
    }

    @DeleteMapping("/{id}")
    @AigcPerm
    public R del(@PathVariable String id) {
        return R.ok(aigcMessageService.removeById(id));
    }

}
