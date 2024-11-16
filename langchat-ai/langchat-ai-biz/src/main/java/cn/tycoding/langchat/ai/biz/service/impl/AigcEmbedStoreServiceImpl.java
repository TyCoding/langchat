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

import cn.tycoding.langchat.ai.biz.entity.AigcEmbedStore;
import cn.tycoding.langchat.ai.biz.mapper.AigcEmbedStoreMapper;
import cn.tycoding.langchat.ai.biz.service.AigcEmbedStoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author tycoding
 * @since 2024/10/28
 */
@Service
@RequiredArgsConstructor
public class AigcEmbedStoreServiceImpl extends ServiceImpl<AigcEmbedStoreMapper, AigcEmbedStore> implements AigcEmbedStoreService {

}
