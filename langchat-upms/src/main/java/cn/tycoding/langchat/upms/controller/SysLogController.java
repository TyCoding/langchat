package cn.tycoding.langchat.upms.controller;

import cn.hutool.core.lang.Dict;
import cn.tycoding.langchat.common.utils.MybatisUtil;
import cn.tycoding.langchat.common.utils.QueryPage;
import cn.tycoding.langchat.common.utils.R;
import cn.tycoding.langchat.upms.entity.SysLog;
import cn.tycoding.langchat.upms.service.SysLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 系统日志表(Log)表控制层
 *
 * @author tycoding
 * @since 2024/4/15
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/upms/log")
public class SysLogController {

    private final SysLogService sysLogService;

    @GetMapping("/page")
    public R<Dict> list(SysLog sysLog, QueryPage queryPage) {
        return R.ok(MybatisUtil.getData(sysLogService.list(sysLog, queryPage)));
    }

    @GetMapping("/{id}")
    public R<SysLog> findById(@PathVariable Long id) {
        return R.ok(sysLogService.getById(id));
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("@auth.hasAuth('system:log:delete')")
    public R delete(@PathVariable Long id) {
        sysLogService.delete(id);
        return R.ok();
    }
}
