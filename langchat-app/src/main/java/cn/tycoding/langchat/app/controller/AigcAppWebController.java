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
import cn.tycoding.langchat.app.consts.AppConst;
import cn.tycoding.langchat.app.entity.AigcAppWeb;
import cn.tycoding.langchat.app.service.AigcAppWebService;
import cn.tycoding.langchat.app.store.AppChannelStore;
import cn.tycoding.langchat.common.annotation.ApiLog;
import cn.tycoding.langchat.common.utils.MybatisUtil;
import cn.tycoding.langchat.common.utils.QueryPage;
import cn.tycoding.langchat.common.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/aigc/app/web")
public class AigcAppWebController {

    private final AigcAppWebService aigcAppService;
    private final AppChannelStore appChannelStore;

    @GetMapping("/generate/key")
    public R generateKey() {
        String uuid = IdUtil.simpleUUID();
        return R.ok(Dict.create().set("apiKey", AppConst.PREFIX + uuid));
    }

    @GetMapping("/list")
    public R<List<AigcAppWeb>> list(AigcAppWeb data) {
        return R.ok(aigcAppService.list(new LambdaQueryWrapper<AigcAppWeb>()));
    }

    @GetMapping("/page")
    public R<Dict> page(AigcAppWeb data, QueryPage queryPage) {
        return R.ok(MybatisUtil.getData(aigcAppService.page(MybatisUtil.wrap(data, queryPage),
                Wrappers.<AigcAppWeb>lambdaQuery()
                        .like(StringUtils.isNotEmpty(data.getName()), AigcAppWeb::getName, data.getName()))));
    }

    @GetMapping("/{id}")
    public R<AigcAppWeb> findById(@PathVariable String id) {
        return R.ok(aigcAppService.getById(id));
    }

    @PostMapping
    @ApiLog("新增WEB渠道")
//    @SaCheckPermission("aigc:app:iframe:add")
    public R add(@RequestBody AigcAppWeb data) {
        if (data.getApiKey().contains("*")) {
            data.setApiKey(null);
        }
        aigcAppService.save(data);
        appChannelStore.init();
        return R.ok();
    }

    @PutMapping
    @ApiLog("修改WEB渠道")
//    @SaCheckPermission("aigc:app:iframe:update")
    public R update(@RequestBody AigcAppWeb data) {
        if (data.getApiKey().contains("*")) {
            data.setApiKey(null);
        }
        aigcAppService.updateById(data);
        appChannelStore.init();
        return R.ok();
    }

    @DeleteMapping("/{id}")
    @ApiLog("删除WEB渠道")
//    @SaCheckPermission("aigc:app:iframe:delete")
    public R delete(@PathVariable String id) {
        aigcAppService.removeById(id);
        appChannelStore.init();
        return R.ok();
    }
}