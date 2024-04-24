package cn.tycoding.langchat.auth.service;

import cn.dev33.satoken.stp.StpInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tycoding
 * @since 2024/1/5
 */
@Slf4j
@Component
public class PermissionService implements StpInterface {

    @Override
    public List<String> getPermissionList(Object o, String s) {
        log.info("获取权限列表：{}, {}", o, s);
        List<String> list = new ArrayList<>();
        list.add("user:add");
        return list;
    }

    @Override
    public List<String> getRoleList(Object o, String s) {
        log.info("获取角色列表：{}, {}", o, s);
        List<String> list = new ArrayList<>();
        list.add("admin");
        return list;
    }
}
