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

package cn.tycoding.langchat.biz.service;

import cn.tycoding.langchat.biz.entity.AigcModel;
import cn.tycoding.langchat.common.utils.QueryPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author tycoding
 * @since 2024/1/19
 */
public interface AigcModelService extends IService<AigcModel> {

    List<AigcModel> getChatModels();

    List<AigcModel> getImageModels();

    List<AigcModel> getEmbeddingModels();

    List<AigcModel> list(AigcModel data);

    Page<AigcModel> page(AigcModel data, QueryPage queryPage);

    AigcModel selectById(String id);
}

