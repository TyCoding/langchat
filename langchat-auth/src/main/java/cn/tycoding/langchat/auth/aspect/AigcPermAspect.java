package cn.tycoding.langchat.auth.aspect;

import cn.dev33.satoken.exception.NotPermissionException;
import cn.tycoding.langchat.aigc.entity.AigcUser;
import cn.tycoding.langchat.aigc.utils.AigcAuthUtil;
import cn.tycoding.langchat.common.annotation.AigcPerm;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义AIGC权限切面
 *
 * @author tycoding
 * @since 2024/7/5
 */
@Slf4j
@Aspect
@Configuration
public class AigcPermAspect {

    @Around("@annotation(aigcPerm)")
    public Object around(ProceedingJoinPoint point, AigcPerm aigcPerm) throws Throwable {
        AigcUser userInfo = AigcAuthUtil.getUserInfo();
        if (userInfo.getIsPerms() == null || !userInfo.getIsPerms()) {
            throw new NotPermissionException("当前账号没有操作权限，请联系管理员");
        }
        return point.proceed();
    }

}
