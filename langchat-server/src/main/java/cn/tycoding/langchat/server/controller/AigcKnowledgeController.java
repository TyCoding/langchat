/*
 * Copyright (c) 2024 LangChat. TyCoding All Rights Reserved.
 *
 * Licensed under the GNU Affero General Public License, Version 3 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.gnu.org/licenses/agpl-3.0.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.tycoding.langchat.server.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.biz.entity.AigcDocs;
import cn.tycoding.langchat.biz.entity.AigcKnowledge;
import cn.tycoding.langchat.biz.mapper.AigcDocsMapper;
import cn.tycoding.langchat.biz.service.AigcKnowledgeService;
import cn.tycoding.langchat.common.annotation.ApiLog;
import cn.tycoding.langchat.common.utils.MybatisUtil;
import cn.tycoding.langchat.common.utils.QueryPage;
import cn.tycoding.langchat.common.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author tycoding
 * @since 2024/4/15
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/aigc/knowledge")
public class AigcKnowledgeController {

    private final AigcKnowledgeService kbService;
    private final AigcDocsMapper docsMapper;

    @GetMapping("/list")
    public R<List<AigcKnowledge>> list(AigcKnowledge data) {
        List<AigcKnowledge> list = kbService.list(Wrappers.<AigcKnowledge>lambdaQuery().orderByDesc(AigcKnowledge::getCreateTime));
        List<String> ids = list.stream().map(AigcKnowledge::getId).toList();
        List<AigcDocs> docs = new ArrayList<>();
        if (!ids.isEmpty()){
            docs = docsMapper.selectList(Wrappers.<AigcDocs>lambdaQuery().in(AigcDocs::getKnowledgeId, ids));
        }
        Map<String, List<AigcDocs>> docsMap = docs.stream().collect(Collectors.groupingBy(AigcDocs::getKnowledgeId));
        list.forEach(i -> {
            List<AigcDocs> val = docsMap.get(i.getId());
            if (val != null) {
                i.setDocs(val);
                i.setDocsNum(val.size());
            }

        });
        return R.ok(list);
    }

    @GetMapping("/page")
    public R list(AigcKnowledge data, QueryPage queryPage) {
        Page<AigcKnowledge> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        LambdaQueryWrapper<AigcKnowledge> queryWrapper = Wrappers.<AigcKnowledge>lambdaQuery()
                .like(!StrUtil.isBlank(data.getName()), AigcKnowledge::getName, data.getName())
                .orderByDesc(AigcKnowledge::getCreateTime);
        Page<AigcKnowledge> iPage = kbService.page(page, queryWrapper);

        Map<String, List<AigcDocs>> docsMap = docsMapper.selectList(Wrappers.lambdaQuery()).stream().collect(Collectors.groupingBy(AigcDocs::getKnowledgeId));
        iPage.getRecords().forEach(i -> {
            List<AigcDocs> docs = docsMap.get(i.getId());
            if (docs != null) {
                i.setDocsNum(docs.size());
                i.setTotalSize(docs.stream().filter(d -> d.getSize() != null).mapToLong(AigcDocs::getSize).sum());
            }
        });

        return R.ok(MybatisUtil.getData(iPage));
    }

    @GetMapping("/{id}")
    public R<AigcKnowledge> findById(@PathVariable String id) {
        return R.ok(kbService.getById(id));
    }

    @PostMapping
    @ApiLog("新增知识库")
    @SaCheckPermission("aigc:knowledge:add")
    public R add(@RequestBody AigcKnowledge data) {
        data.setCreateTime(String.valueOf(System.currentTimeMillis()));
        kbService.save(data);
        return R.ok();
    }

    @PutMapping
    @ApiLog("更新知识库")
    @SaCheckPermission("aigc:knowledge:update")
    public R update(@RequestBody AigcKnowledge data) {
        kbService.updateById(data);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    @ApiLog("删除知识库")
    @SaCheckPermission("aigc:knowledge:delete")
    public R delete(@PathVariable String id) {
        kbService.removeKnowledge(id);
        return R.ok();
    }
}

