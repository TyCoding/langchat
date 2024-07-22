package cn.tycoding.langchat.auth.aspect;

import cn.dev33.satoken.exception.NotPermissionException;
import cn.tycoding.langchat.biz.entity.AigcUser;
import cn.tycoding.langchat.biz.utils.ClientAuthUtil;
import cn.tycoding.langchat.common.annotation.ClientPerm;
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
public class ClientPermAspect {

    @Around("@annotation(clientPerm)")
    public Object around(ProceedingJoinPoint point, ClientPerm clientPerm) throws Throwable {
        AigcUser userInfo = ClientAuthUtil.getUserInfo();
        if (userInfo.getIsPerms() == null || !userInfo.getIsPerms()) {
            throw new NotPermissionException("当前账号没有操作权限，请联系管理员");
        }
        return point.proceed();
    }

}
