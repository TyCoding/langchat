package cn.tycoding.langchat.auth.aspect;

import cn.tycoding.langchat.auth.event.LogEvent;
import cn.tycoding.langchat.auth.utils.SysLogUtil;
import cn.tycoding.langchat.common.annotation.ApiLog;
import cn.tycoding.langchat.common.component.SpringContextHolder;
import cn.tycoding.langchat.upms.entity.SysLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义日志记录切面
 *
 * @author tycoding
 * @since 2024/1/5
 */
@Slf4j
@Aspect
@Configuration
public class ApiLogAspect {

    @Around("@annotation(apiLog)")
    public Object around(ProceedingJoinPoint point, ApiLog apiLog) throws Throwable {
        String className = point.getTarget().getClass().getName();
        String methodName = point.getSignature().getName();

        long beginTime = System.currentTimeMillis();
        long time = System.currentTimeMillis() - beginTime;

        String method = className + "." + methodName + "()";
        SysLog sysLog = SysLogUtil.build(SysLogUtil.TYPE_OK, apiLog.value(), method, time);

        SpringContextHolder.publishEvent(new LogEvent(sysLog));
        return point.proceed();
    }

}
