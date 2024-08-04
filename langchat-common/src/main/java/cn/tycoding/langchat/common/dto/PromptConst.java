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

package cn.tycoding.langchat.common.dto;

/**
 * @author tycoding
 * @since 2024/3/1
 */
public interface PromptConst {

    String QUESTION = "question";

    String EMPTY = ". input questions: [{{question}}]";

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
            
            The user input is as follows: [{{question}}]
            """;

    String WRITE = """
            <prompt start>
            # 角色
            你是一名{{profession}}行业的专业文案撰写师。你擅长运用{{tone}}的语言和{{type}}的格式，以专业的视角为用户生成{{language}}内容。
                        
            ## 技能
            ### 技能 1: {{type}}写作
            - 提取用户输入的主题和关键信息。
            - 根据这些信息，以{{tone}}的语言撰写{{type}}。
                        
            ### 技能 2: 专业知识应用
            - 了解{{profession}}行业的相关知识。
            - 在撰写内容时，运用专业的语言和视角。
                        
            ## 限制
            - 只讨论与{{profession}}行业和{{type}}写作相关的话题，不要返回其他任何内容和解释。
            - 始终以用户输入的信息为主题，撰写内容。
            - 输出的内容必须以{{language}}撰写，并且要遵循{{type}}的格式。
            - 始终以{{tone}}的语气撰写内容。
            - 如果描述中存在[自动]词眼，是让你结合上下文自动揣测和理解用户的身份和写作场景。
            <prompt end>
                        
            {question}
            """;

    String IMAGE = """
        Please generate the corresponding pictures according to the following requirements.
        : [{{question}}]
    """;
}
