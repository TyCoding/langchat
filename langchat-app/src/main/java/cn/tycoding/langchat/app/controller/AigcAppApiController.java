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

package cn.tycoding.langchat.app.controller;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.app.consts.AppConst;
import cn.tycoding.langchat.app.entity.AigcAppApi;
import cn.tycoding.langchat.app.service.AigcAppApiService;
import cn.tycoding.langchat.app.store.AppChannelStore;
import cn.tycoding.langchat.common.utils.MybatisUtil;
import cn.tycoding.langchat.common.utils.QueryPage;
import cn.tycoding.langchat.common.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/aigc/app/api")
public class AigcAppApiController {

    private final AigcAppApiService appApiService;
    private final AppChannelStore appChannelStore;

    @GetMapping("/create/{id}")
    public R create(@PathVariable String id) {
        String uuid = AppConst.PREFIX + IdUtil.simpleUUID();
        appApiService.save(new AigcAppApi().setAppId(id).setApiKey(uuid).setCreateTime(new Date()));
        appChannelStore.init();
        return R.ok();
    }

    @GetMapping("/list")
    public R<List<AigcAppApi>> list(AigcAppApi data) {
        List<AigcAppApi> list = appApiService.list(new LambdaQueryWrapper<AigcAppApi>()
                .eq(StrUtil.isNotBlank(data.getAppId()), AigcAppApi::getAppId, data.getAppId())
                .orderByDesc(AigcAppApi::getCreateTime));
        return R.ok(list);
    }

    @GetMapping("/page")
    public R<Dict> page(AigcAppApi data, QueryPage queryPage) {
        IPage<AigcAppApi> iPage = appApiService.page(MybatisUtil.wrap(data, queryPage),
                Wrappers.<AigcAppApi>lambdaQuery().orderByDesc(AigcAppApi::getCreateTime));
        return R.ok(MybatisUtil.getData(iPage));
    }

    @GetMapping("/{id}")
    public R<AigcAppApi> findById(@PathVariable String id) {
        AigcAppApi api = appApiService.getById(id);
        return R.ok(api);
    }

    @PostMapping
    public R add(@RequestBody AigcAppApi data) {
        data.setCreateTime(new Date());
        appApiService.save(data);
        appChannelStore.init();
        return R.ok();
    }

    @PutMapping
    public R update(@RequestBody AigcAppApi data) {
        appApiService.updateById(data);
        appChannelStore.init();
        return R.ok();
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable String id) {
        appApiService.removeById(id);
        appChannelStore.init();
        return R.ok();
    }
}