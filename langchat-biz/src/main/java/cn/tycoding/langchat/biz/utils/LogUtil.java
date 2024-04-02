package cn.tycoding.langchat.biz.utils;

import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HttpUtil;
import cn.tycoding.langchat.biz.entity.SysLog;
import cn.tycoding.langchat.common.component.SpringContextHolder;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;
import lombok.SneakyThrows;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author tycoding
 * @since 2024/1/19
 */
public class LogUtil {

    /**
     * 成功日志类型
     */
    public static final int TYPE_OK = 1;

    /**
     * 错误日志类型
     */
    public static final int TYPE_FAIL = 2;

    /**
     * 构建日志Log类信息
     *
     * @param type      日志类型
     * @param operation 日志描述
     * @param method    操作方法
     * @param time      耗时
     * @return Log类
     */
    @SneakyThrows
    public static SysLog build(Integer type, String operation, String method, Long time) {
        HttpServletRequest request =
                ((ServletRequestAttributes)
                        Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                        .getRequest();

        return new SysLog()
                .setType(type)
                .setUsername(AuthUtil.getUsername())
                .setOperation(operation)
                .setCreateTime(new Date())
//                .setIp(ServletUtil.getClientIP(request))
                .setUrl(URLUtil.getPath(request.getRequestURI()))
                .setMethod(method)
                .setParams(HttpUtil.toParams(request.getParameterMap()))
                .setUserAgent(request.getHeader("user-agent"))
                .setTime(time);
    }

    /**
     * Spring事件发布：发布日志，写入到数据库
     *
     * @param type      日志类型
     * @param operation 描述
     */
    public static void publish(int type, String operation) {
        SysLog sysLog = LogUtil.build(type, operation, null, null);
        SpringContextHolder.publishEvent(new LogEvent(sysLog));
    }
}
