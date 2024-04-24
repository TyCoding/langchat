package cn.tycoding.langchat.common.utils;

import cn.hutool.json.JSONUtil;
import cn.tycoding.langchat.common.constant.CommonConst;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

/**
 * @author tycoding
 * @since 2024/1/2
 */
public class ServletUtil {

    @SneakyThrows
    public static void write(HttpServletResponse response, R data) {
        response.setStatus(data.getCode());
        response.setHeader("Content-type", "application/json;charset=" + CommonConst.UTF_8);
        response.setCharacterEncoding(CommonConst.UTF_8);
        response.getWriter().write(JSONUtil.toJsonStr(data));
    }

    @SneakyThrows
    public static void write(HttpServletResponse response, int status, R data) {
        response.setStatus(status);
        response.setHeader("Content-type", "application/json;charset=" + CommonConst.UTF_8);
        response.setCharacterEncoding(CommonConst.UTF_8);
        response.getWriter().write(JSONUtil.toJsonStr(data));
    }
}
