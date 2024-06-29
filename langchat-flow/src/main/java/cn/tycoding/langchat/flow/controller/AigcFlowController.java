package cn.tycoding.langchat.flow.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.tycoding.langchat.common.exception.ServiceException;
import cn.tycoding.langchat.common.utils.MybatisUtil;
import cn.tycoding.langchat.common.utils.QueryPage;
import cn.tycoding.langchat.common.utils.R;
import cn.tycoding.langchat.flow.entity.AigcFlow;
import cn.tycoding.langchat.flow.service.AigcFlowService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author tycoding
 * @since 2024/6/17
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/langchat/flow")
public class AigcFlowController {

    private final AigcFlowService flowService;
//    private final FlowExecutor flowExecutor;
//    private final LcAppService appService;

    @GetMapping("/list")
    public R<List<AigcFlow>> list(AigcFlow data) {
        return R.ok(flowService.list(Wrappers.<AigcFlow>lambdaQuery()
                .eq(AigcFlow::getIsPublish, true)
                .orderByDesc(AigcFlow::getCreateTime)
        ));
    }

    @GetMapping("/page")
    public R list(AigcFlow data, QueryPage queryPage) {
        Page<AigcFlow> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        return R.ok(MybatisUtil.getData(flowService.page(page, Wrappers.<AigcFlow>lambdaQuery()
                .orderByDesc(AigcFlow::getCreateTime)
        )));
    }

    @GetMapping("/{id}")
    public R<AigcFlow> findById(@PathVariable String id) {
        return R.ok(flowService.getById(id));
    }

    @PostMapping
    @SaCheckPermission("aigc:flow:add")
    public R add(@RequestBody AigcFlow data) {
        data.setCreateTime(new Date());
        flowService.save(data);
        return R.ok(data);
    }

    @PutMapping
    @SaCheckPermission("aigc:flow:update")
    public R update(@RequestBody AigcFlow data) {
        data.setUpdateTime(new Date());
        flowService.updateById(data);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    @SaCheckPermission("aigc:flow:delete")
    public R delete(@PathVariable String id) {
        flowService.removeById(id);
        return R.ok();
    }

    @PutMapping("/publish")
    @SaCheckPermission("aigc:flow:publish")
    public R publish(@RequestBody AigcFlow data) {
        if (data.getId() == null) {
            throw new ServiceException("Flow数据异常");
        }
        if (data.getFlow() == null) {
            data = flowService.getById(data.getId());
        }

//        ElParser parser = new ElParser(data.getFlow());
//        String script = parser.genEl();
//        log.info("EL解析脚本：\n{}", script);
//
//        data.setPublishTime(new Date());
//        flowService.updateById(new LcFlow().setId(data.getId()).setScript(script));
//        flowExecutor.reloadRule();
//
//        // 更新应用数据
//        appService.update(Wrappers.<LcApp>lambdaUpdate().eq(LcApp::getFlowId, data.getId()).eq(LcApp::getFlowScript, script));
        return R.ok();
    }
}

