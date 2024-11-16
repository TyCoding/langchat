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

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Service;

/**
 * @author tycoding
 * @since 2024/1/19
 */
@Service
public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    public static void publishEvent(ApplicationEvent event) {
        if (applicationContext == null) {
            return;
        }
        applicationContext.publishEvent(event);
    }

    public <T> T getBean(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.applicationContext = applicationContext;
    }

    public void registerBean(String beanName, Object beanInstance) {
        BeanDefinitionRegistry beanDefinitionRegistry =
                (BeanDefinitionRegistry) applicationContext.getAutowireCapableBeanFactory();

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder
                .genericBeanDefinition((Class<Object>) beanInstance.getClass(), () -> beanInstance);

        BeanDefinition beanDefinition = beanDefinitionBuilder.getRawBeanDefinition();

        beanDefinitionRegistry.registerBeanDefinition(beanName, beanDefinition);
    }

    public void unregisterBean(String beanName) {
        BeanDefinitionRegistry beanDefinitionRegistry =
                (BeanDefinitionRegistry) applicationContext.getAutowireCapableBeanFactory();

        if (beanDefinitionRegistry.containsBeanDefinition(beanName)) {
            beanDefinitionRegistry.removeBeanDefinition(beanName);
        }
    }
}
