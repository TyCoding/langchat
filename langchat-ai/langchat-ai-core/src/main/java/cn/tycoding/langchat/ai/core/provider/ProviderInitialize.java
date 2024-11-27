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

package cn.tycoding.langchat.ai.core.provider;

import cn.hutool.core.util.ObjectUtil;
import cn.tycoding.langchat.ai.biz.component.ModelTypeEnum;
import cn.tycoding.langchat.ai.biz.entity.AigcModel;
import cn.tycoding.langchat.ai.biz.service.AigcModelService;
import cn.tycoding.langchat.ai.core.consts.ModelConst;
import cn.tycoding.langchat.ai.core.provider.build.ModelBuildHandler;
import cn.tycoding.langchat.common.core.component.SpringContextHolder;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.image.ImageModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author tycoding
 * @since 2024/6/16
 */
@Configuration
@AllArgsConstructor
@Slf4j
public class ProviderInitialize implements ApplicationContextAware {
    private final AigcModelService aigcModelService;
    private final SpringContextHolder contextHolder;
    private List<ModelBuildHandler> modelBuildHandlers;
    private List<AigcModel> modelStore = new ArrayList<>();

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        init();
    }

    @Async
    public void init() {
        modelStore = new ArrayList<>();

        List<AigcModel> list = aigcModelService.list();
        list.forEach(model -> {
            if (Objects.equals(model.getBaseUrl(), "")) {
                model.setBaseUrl(null);
            }
            // Uninstall previously registered beans before registering them
            contextHolder.unregisterBean(model.getId());

            chatHandler(model);
            embeddingHandler(model);
            imageHandler(model);
        });

        modelStore.forEach(i -> log.info("已成功注册模型：{} -- {}， 模型配置：{}", i.getProvider(), i.getType(),  i));
    }

    private void chatHandler(AigcModel model) {
        try {
            String type = model.getType();
            if (!ModelTypeEnum.CHAT.name().equals(type)) {
                return;
            }
            modelBuildHandlers.forEach(x -> {
                StreamingChatLanguageModel streamingChatLanguageModel = x.buildStreamingChat(model);
                if (ObjectUtil.isNotEmpty(streamingChatLanguageModel)) {
                    contextHolder.registerBean(model.getId(), streamingChatLanguageModel);
                    modelStore.add(model);
                }

                ChatLanguageModel languageModel = x.buildChatLanguageModel(model);
                if (ObjectUtil.isNotEmpty(languageModel)) {
                    contextHolder.registerBean(model.getId() + ModelConst.TEXT_SUFFIX, languageModel);
                }
            });
        } catch (Exception e) {
            log.error("model 【 id: {} name: {}】streaming chat 配置报错", model.getId(), model.getName());
        }
    }

    private void embeddingHandler(AigcModel model) {
        try {
            String type = model.getType();
            if (!ModelTypeEnum.EMBEDDING.name().equals(type)) {
                return;
            }
            modelBuildHandlers.forEach(x -> {
                EmbeddingModel embeddingModel = x.buildEmbedding(model);
                if (ObjectUtil.isNotEmpty(embeddingModel)) {
                    contextHolder.registerBean(model.getId(), embeddingModel);
                    modelStore.add(model);
                }
            });

        } catch (Exception e) {
            log.error("model 【id{} name{}】 embedding 配置报错", model.getId(), model.getName());
        }
    }

    private void imageHandler(AigcModel model) {
        try {
            String type = model.getType();
            if (!ModelTypeEnum.TEXT_IMAGE.name().equals(type)) {
                return;
            }
            modelBuildHandlers.forEach(x -> {
                ImageModel imageModel = x.buildImage(model);
                if (ObjectUtil.isNotEmpty(imageModel)) {
                    contextHolder.registerBean(model.getId(), imageModel);
                    modelStore.add(model);
                }
            });
        } catch (Exception e) {
            log.error("model 【id{} name{}】 image 配置报错", model.getId(), model.getName());
        }
    }
}
