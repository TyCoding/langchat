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

package cn.tycoding.langchat.core.consts;

/**
 * @author tycoding
 * @since 2024/1/6
 */
public interface ModelConst {

    String IMAGE_SUFFIX = "_image";
    String TEXT_SUFFIX = "_text";
    String EMBED_SUFFIX = "_embed";

    String OPENAI_GPT_3T = "gpt-3.5-turbo";
    String OPENAI_GPT_4 = "gpt-4";
    String OPENAI_GPT_4T = "gpt-4-turbo";
    String OPENAI_GPT_4O = "gpt-4o";
    String OPENAI_EMBED_3S = "text-embedding-3-small";
    String OPENAI_EMBED_3L = "text-embedding-3-large";
    String OPENAI_IMAGE_2 = "dall-e-2";
    String OPENAI_IMAGE_3 = "dall-e-3";

    String GEMINI_F = "gemini-1.5-flash";
    String GEMINI_P = "gemini-1.5-pro";

    String AZUREOPENAI_GPT_3T = "gpt-3.5-turbo";
    String AZUREOPENAI_GPT_4 = "gpt-4";
    String AZUREOPENAI_GPT_4T = "gpt-4-turbo";
    String AZUREOPENAI_GPT_4O = "gpt-4o";
    String AZUREOPENAI_EMBED_3S = "text-embedding-3-small";
    String AZUREOPENAI_EMBED_3L = "text-embedding-3-large";
    String AZUREOPENAI_IMAGE_2 = "dall-e-2";
    String AZUREOPENAI_IMAGE_3 = "dall-e-3";

    String OLLAMA = "ollama";
    String OLLAMA_TEXT = OLLAMA + TEXT_SUFFIX;
    String OLLAMA_EMBED = OLLAMA + EMBED_SUFFIX;

    String GEMINI = "gemini";
    String GEMINI_IMAGE = GEMINI + IMAGE_SUFFIX;


}
