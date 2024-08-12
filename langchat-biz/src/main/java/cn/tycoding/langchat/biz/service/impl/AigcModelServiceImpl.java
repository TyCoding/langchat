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

package cn.tycoding.langchat.biz.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.biz.component.ModelTypeEnum;
import cn.tycoding.langchat.biz.entity.AigcModel;
import cn.tycoding.langchat.biz.mapper.AigcModelMapper;
import cn.tycoding.langchat.biz.service.AigcModelService;
import cn.tycoding.langchat.common.utils.QueryPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tycoding
 * @since 2024/1/19
 */
@Service
@RequiredArgsConstructor
public class AigcModelServiceImpl extends ServiceImpl<AigcModelMapper, AigcModel> implements AigcModelService {

    @Override
    public List<AigcModel> getChatModels() {
        return baseMapper.selectList(Wrappers.<AigcModel>lambdaQuery()
                .eq(AigcModel::getType, ModelTypeEnum.CHAT.name()));
    }

    @Override
    public List<AigcModel> getImageModels() {
        return baseMapper.selectList(Wrappers.<AigcModel>lambdaQuery()
                .eq(AigcModel::getType, ModelTypeEnum.TEXT_IMAGE.name()));
    }

    @Override
    public List<AigcModel> getEmbeddingModels() {
        return baseMapper.selectList(Wrappers.<AigcModel>lambdaQuery()
                .eq(AigcModel::getType, ModelTypeEnum.EMBEDDING.name()));
    }

    @Override
    public List<AigcModel> list(AigcModel data) {
        List<AigcModel> list = this.list(Wrappers.<AigcModel>lambdaQuery()
                .eq(StrUtil.isNotBlank(data.getType()), AigcModel::getType, data.getType())
                .eq(StrUtil.isNotBlank(data.getProvider()), AigcModel::getProvider, data.getProvider()));
        list.forEach(this::hide);
        return list;
    }

    @Override
    public Page<AigcModel> page(AigcModel data, QueryPage queryPage) {
        Page<AigcModel> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        Page<AigcModel> iPage = this.page(page, Wrappers.<AigcModel>lambdaQuery().eq(AigcModel::getProvider, data.getProvider()));
        iPage.getRecords().forEach(this::hide);
        return iPage;
    }

    @Override
    public AigcModel selectById(String id) {
        AigcModel model = this.getById(id);
        hide(model);
        return model;
    }

    private void hide(AigcModel model) {
        if (model == null || StrUtil.isBlank(model.getApiKey())) {
            return;
        }
        String key = StrUtil.hide(model.getApiKey(), 3, model.getApiKey().length() - 4);
        model.setApiKey(key);

        if (StrUtil.isBlank(model.getSecretKey())) {
            return;
        }
        String sec = StrUtil.hide(model.getSecretKey(), 3, model.getSecretKey().length() - 4);
        model.setSecretKey(sec);
    }
}

