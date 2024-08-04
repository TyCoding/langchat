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

package cn.tycoding.langchat.upms.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.lang.tree.Tree;
import cn.tycoding.langchat.common.annotation.ApiLog;
import cn.tycoding.langchat.common.utils.R;
import cn.tycoding.langchat.upms.entity.SysDept;
import cn.tycoding.langchat.upms.service.SysDeptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门表(Dept)表控制层
 *
 * @author tycoding
 * @since 2024/4/15
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/upms/dept")
public class SysDeptController {

    private final SysDeptService sysDeptService;

    @GetMapping("/list")
    public R<List<SysDept>> list(SysDept sysDept) {
        return R.ok(sysDeptService.list(sysDept));
    }

    @GetMapping("/tree")
    public R<List<Tree<Object>>> tree(SysDept sysDept) {
        return R.ok(sysDeptService.tree(sysDept));
    }

    @GetMapping("/{id}")
    public R<SysDept> findById(@PathVariable String id) {
        return R.ok(sysDeptService.getById(id));
    }

    @PostMapping
    @ApiLog("新增部门")
    @SaCheckPermission("upms:dept:add")
    public R add(@RequestBody SysDept sysDept) {
        sysDept.setParentId(sysDept.getParentId());
        sysDeptService.save(sysDept);
        return R.ok();
    }

    @PutMapping
    @ApiLog("修改部门")
    @SaCheckPermission("upms:dept:update")
    public R update(@RequestBody SysDept sysDept) {
        sysDept.setParentId(sysDept.getParentId());
        sysDeptService.updateById(sysDept);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    @ApiLog("删除部门")
    @SaCheckPermission("upms:dept:delete")
    public R delete(@PathVariable String id) {
        sysDeptService.delete(id);
        return R.ok();
    }
}
