package cn.tycoding.langchat.auth.service;

import cn.dev33.satoken.stp.StpInterface;
import cn.tycoding.langchat.upms.utils.AuthUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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
        return AuthUtil.getPermissionNames();
    }

    @Override
    public List<String> getRoleList(Object o, String s) {
        return AuthUtil.getRoleNames();
    }
}
