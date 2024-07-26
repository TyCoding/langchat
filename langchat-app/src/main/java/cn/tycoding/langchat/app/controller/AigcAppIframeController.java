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

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.lang.Dict;
import cn.tycoding.langchat.app.entity.AigcAppIframe;
import cn.tycoding.langchat.app.service.AigcAppIframeService;
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
@RequestMapping("/aigc/bot/iframe")
public class AigcAppIframeController {

    private final AigcAppIframeService aigcAppService;

    @GetMapping("/list")
    public R<List<AigcAppIframe>> list(AigcAppIframe data) {
        return R.ok(aigcAppService.list(new LambdaQueryWrapper<AigcAppIframe>()));
    }

    @GetMapping("/page")
    public R<Dict> page(AigcAppIframe data, QueryPage queryPage) {
        return R.ok(MybatisUtil.getData(aigcAppService.page(MybatisUtil.wrap(data, queryPage),
                Wrappers.<AigcAppIframe>lambdaQuery()
                        .like(StringUtils.isNotEmpty(data.getName()), AigcAppIframe::getName, data.getName()))));
    }

    @GetMapping("/{id}")
    public R<AigcAppIframe> findById(@PathVariable Long id) {
        return R.ok(aigcAppService.getById(id));
    }

    @PostMapping
    @ApiLog("新增应用")
    @SaCheckPermission("aigc:app:iframe:add")
    public R add(@RequestBody AigcAppIframe data) {
        aigcAppService.save(data);
        return R.ok();
    }

    @PutMapping
    @ApiLog("修改应用")
    @SaCheckPermission("aigc:app:iframe:update")
    public R update(@RequestBody AigcAppIframe data) {
        aigcAppService.updateById(data);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    @ApiLog("删除应用")
    @SaCheckPermission("aigc:app:iframe:delete")
    public R delete(@PathVariable Long id) {
        aigcAppService.removeById(id);
        return R.ok();
    }
}