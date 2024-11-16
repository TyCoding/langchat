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

package cn.tycoding.langchat.ai.biz.service.impl;

import cn.tycoding.langchat.ai.biz.entity.AigcUser;
import cn.tycoding.langchat.ai.biz.mapper.AigcUserMapper;
import cn.tycoding.langchat.ai.biz.service.AigcUserService;
import cn.tycoding.langchat.common.core.utils.QueryPage;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tycoding
 * @since 2024/1/15
 */
@Service
@RequiredArgsConstructor
public class AigcUserServiceImpl extends ServiceImpl<AigcUserMapper, AigcUser> implements AigcUserService {

    @Override
    public AigcUser info(String username) {
        List<AigcUser> list = baseMapper.selectList(Wrappers.<AigcUser>lambdaQuery().eq(AigcUser::getUsername, username));
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Boolean checkName(AigcUser data) {
        List<AigcUser> list = baseMapper.selectList(Wrappers.<AigcUser>lambdaQuery().eq(AigcUser::getUsername, data.getUsername()));
        return list.isEmpty();
    }

    @Override
    public IPage<AigcUser> page(AigcUser data, QueryPage queryPage) {
        return baseMapper.selectPage(new Page<>(queryPage.getPage(), queryPage.getLimit()), Wrappers.lambdaQuery());
    }
}
