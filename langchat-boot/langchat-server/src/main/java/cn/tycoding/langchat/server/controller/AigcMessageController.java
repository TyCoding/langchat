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
import cn.tycoding.langchat.ai.biz.entity.AigcMessage;
import cn.tycoding.langchat.ai.biz.service.AigcMessageService;
import cn.tycoding.langchat.common.core.annotation.ApiLog;
import cn.tycoding.langchat.common.core.utils.MybatisUtil;
import cn.tycoding.langchat.common.core.utils.QueryPage;
import cn.tycoding.langchat.common.core.utils.R;
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
    @ApiLog("删除会话消息")
    @SaCheckPermission("aigc:message:delete")
    public R del(@PathVariable String id) {
        return R.ok(aigcMessageService.removeById(id));
    }

}
