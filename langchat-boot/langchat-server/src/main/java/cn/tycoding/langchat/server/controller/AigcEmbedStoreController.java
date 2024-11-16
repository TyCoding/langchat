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
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.ai.biz.component.EmbeddingRefreshEvent;
import cn.tycoding.langchat.ai.biz.entity.AigcEmbedStore;
import cn.tycoding.langchat.ai.biz.service.AigcEmbedStoreService;
import cn.tycoding.langchat.common.core.annotation.ApiLog;
import cn.tycoding.langchat.common.core.component.SpringContextHolder;
import cn.tycoding.langchat.common.core.utils.MybatisUtil;
import cn.tycoding.langchat.common.core.utils.QueryPage;
import cn.tycoding.langchat.common.core.utils.R;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tycoding
 * @since 2024/4/15
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/aigc/embed-store")
public class AigcEmbedStoreController {

    private final AigcEmbedStoreService embedStoreService;
    private final SpringContextHolder contextHolder;

    @GetMapping("/list")
    public R<List<AigcEmbedStore>> list(AigcEmbedStore data) {
        List<AigcEmbedStore> list = embedStoreService.list(Wrappers.lambdaQuery());
        list.forEach(this::hide);
        return R.ok(list);
    }

    @GetMapping("/page")
    public R<Dict> page(AigcEmbedStore embedStore, QueryPage queryPage) {
        IPage<AigcEmbedStore> page = embedStoreService.page(MybatisUtil.wrap(embedStore, queryPage),
                Wrappers.lambdaQuery());
        page.getRecords().forEach(this::hide);
        return R.ok(MybatisUtil.getData(page));
    }

    @GetMapping("/{id}")
    public R<AigcEmbedStore> findById(@PathVariable String id) {
        AigcEmbedStore store = embedStoreService.getById(id);
        hide(store);
        return R.ok(store);
    }

    @PostMapping
    @ApiLog("新增向量库")
    @SaCheckPermission("aigc:embed-store:add")
    public R<AigcEmbedStore> add(@RequestBody AigcEmbedStore data) {
        if (StrUtil.isNotBlank(data.getPassword()) && data.getPassword().contains("*")) {
            data.setPassword(null);
        }
        embedStoreService.save(data);
        SpringContextHolder.publishEvent(new EmbeddingRefreshEvent(data));
        return R.ok();
    }

    @PutMapping
    @ApiLog("修改向量库")
    @SaCheckPermission("aigc:embed-store:update")
    public R update(@RequestBody AigcEmbedStore data) {
        if (StrUtil.isNotBlank(data.getPassword()) && data.getPassword().contains("*")) {
            data.setPassword(null);
        }
        embedStoreService.updateById(data);
        SpringContextHolder.publishEvent(new EmbeddingRefreshEvent(data));
        return R.ok();
    }

    @DeleteMapping("/{id}")
    @ApiLog("删除向量库")
    @SaCheckPermission("aigc:embed-store:delete")
    public R delete(@PathVariable String id) {
        AigcEmbedStore store = embedStoreService.getById(id);
        if (store != null) {
            embedStoreService.removeById(id);
            contextHolder.unregisterBean(id);
        }
        return R.ok();
    }

    private void hide(AigcEmbedStore data) {
        if (data == null || StrUtil.isBlank(data.getPassword())) {
            return;
        }
        String key = StrUtil.hide(data.getPassword(), 0, data.getPassword().length());
        data.setPassword(key);
    }
}
