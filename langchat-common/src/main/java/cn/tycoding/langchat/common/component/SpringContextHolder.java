package cn.tycoding.langchat.common.component;

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

    public static <T> T getBean(Class<T> requiredType) {
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
