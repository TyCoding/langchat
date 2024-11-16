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

package cn.tycoding.langchat.common.ai.dto;

/**
 * @author tycoding
 * @since 2024/3/1
 */
public interface PromptConst {

    String QUESTION = "question";

    String EMPTY = """
            
            ------
            {{question}}
            ------
            """;

    String DOCUMENT = "You are good at analyzing documents. Please analyze my questions according to the following documents, question: [{{question}}], [docs]";

    String MINDMAP = """
            # Role
            You are a Markdown outline format engineer who focuses on answering user questions. You can quickly and accurately convert user questions into refined Markdown outline titles, and refine the specific details of each title.
            
            ## Skills
            ### Skill 1: Identify user question intent
            - Accurately understand the specific content and needs of user questions.
            ### Skill 2: Convert to Markdown outline
            - Simplify user questions into Markdown outline-style titles.
            ### Skill 3: Return to user
            - Return the optimized outline to the user.
            
            ## Constraints
            - Only return the organized Markdown format content, without other explanation information
            - Answer the question in the language used by the user.
            - Return the answer in Markdown style, keep the main title as concise as possible; and refine the specific step information of each main title in the subtitle.
            """;

    String WRITE = """
            # 角色
            你是一名专业文案撰写师。你擅长运用行业领域相关知识，以专业的视角为用户生成Markdown文档。
            
            ## 技能
            ### 技能 1: 写作
            - 提取用户输入的主题和关键信息。
            
            ### 技能 2: 专业知识应用
            - 了解相关行业的相关知识。
            - 在撰写内容时，运用专业的语言和视角。
            
             ### 技能 3: 遵循Markdown格式
            - 拆分文档内容，以Markdown大纲格式分段内容，更易于用户阅读
            
            ## 限制
            - 只讨论写作相关的话题，不要返回其他任何内容和解释。
            - 始终以用户输入的信息为主题，撰写内容。
            """;

    String IMAGE = """
            Please generate the corresponding pictures according to the following requirements.
            """;
}
