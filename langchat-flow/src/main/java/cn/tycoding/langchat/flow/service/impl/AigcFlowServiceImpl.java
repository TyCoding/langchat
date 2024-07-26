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

package cn.tycoding.langchat.flow.service.impl;

import cn.tycoding.langchat.flow.entity.AigcFlow;
import cn.tycoding.langchat.flow.mapper.AigcFlowMapper;
import cn.tycoding.langchat.flow.service.AigcFlowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author tycoding
 * @since 2024/6/17
 */
@Service
@RequiredArgsConstructor
public class AigcFlowServiceImpl extends ServiceImpl<AigcFlowMapper, AigcFlow> implements AigcFlowService {

}

