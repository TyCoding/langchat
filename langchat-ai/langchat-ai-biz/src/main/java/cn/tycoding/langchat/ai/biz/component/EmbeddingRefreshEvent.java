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

package cn.tycoding.langchat.ai.biz.component;

import org.springframework.context.ApplicationEvent;

/**
 * @author tycoding
 * @since 2024/6/16
 */
public class EmbeddingRefreshEvent extends ApplicationEvent {
    private static final long serialVersionUID = 4109980679877560773L;

    public EmbeddingRefreshEvent(Object source) {
        super(source);
    }
}
