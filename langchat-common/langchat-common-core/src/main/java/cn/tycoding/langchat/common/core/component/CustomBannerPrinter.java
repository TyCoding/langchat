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

package cn.tycoding.langchat.common.core.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author tycoding
 * @since 2024/1/2
 */
@Slf4j
@Component
public class CustomBannerPrinter implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        System.out.println("""
                    
                    __                         ________          __
                   / /   ____ _____  ____ _   / ____/ /_  ____ _/ /_
                  / /   / __ `/ __ \\/ __ `/  / /   / __ \\/ __ `/ __/
                 / /___/ /_/ / / / / /_/ /  / /___/ / / / /_/ / /_
                /_____/\\__,_/_/ /_/\\__, /   \\____/_/ /_/\\__,_/\\__/
                                  /____/
                    Copyright (c) 2024 LangChat. TyCoding All Rights Reserved.         
                     LangChat采用AGPL协议，允许个人学习使用，商业化项目请联系作者授权
                
                - WebSite: http://langchat.cn
                - Email: langchat@outlook.com
                - Author: TyCoding
                - WeiXin: LangchainChat
                """);

        log.info("LangChat 启动完成...... 当前环境：{}", event.getApplicationContext().getEnvironment().getActiveProfiles());
    }
}
