package cn.tycoding.langchat.upms.controller;

import cn.hutool.core.lang.Dict;
import cn.tycoding.langchat.common.annotation.ApiLog;
import cn.tycoding.langchat.common.exception.ServiceException;
import cn.tycoding.langchat.common.properties.AuthProps;
import cn.tycoding.langchat.common.utils.MybatisUtil;
import cn.tycoding.langchat.common.utils.QueryPage;
import cn.tycoding.langchat.common.utils.R;
import cn.tycoding.langchat.upms.dto.UserInfo;
import cn.tycoding.langchat.upms.entity.SysUser;
import cn.tycoding.langchat.upms.service.SysUserService;
import cn.tycoding.langchat.upms.utils.AuthUtil;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户表(User)表控制层
 *
 * @author tycoding
 * @since 2024/4/15
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/upms/user")
public class SysUserController {

    private final SysUserService sysUserService;
    private final AuthProps authProps;

    @GetMapping("/info")
    public R<UserInfo> info() {
        UserInfo userInfo = sysUserService.info(AuthUtil.getUsername());
        userInfo.setPassword(null);
        return R.ok(userInfo);
    }

    @GetMapping("/checkName")
    public R<Boolean> checkName(UserInfo sysUser) {
        return R.ok(sysUserService.checkName(sysUser));
    }

    @GetMapping("/list")
    public R<List<SysUser>> list(SysUser sysUser) {
        return R.ok(sysUserService.list(sysUser));
    }

    @GetMapping("/page")
    public R<Dict> page(UserInfo user, QueryPage queryPage) {
        return R.ok(MybatisUtil.getData(sysUserService.page(user, queryPage)));
    }

    @GetMapping("/{id}")
    public R<UserInfo> findById(@PathVariable Long id) {
        return R.ok(sysUserService.findById(id));
    }

    @PostMapping
    @ApiLog("新增用户")
//    @PreAuthorize("@auth.hasAuth('upms:user:add')")
    public R<SysUser> add(@RequestBody UserInfo user) {
        sysUserService.add(user);
        return R.ok();
    }

    @PutMapping
    @ApiLog("修改用户")
//    @PreAuthorize("@auth.hasAuth('upms:user:update')")
    public R update(@RequestBody UserInfo user) {
        sysUserService.update(user);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    @ApiLog("删除用户")
//    @PreAuthorize("@auth.hasAuth('upms:user:delete')")
    public R delete(@PathVariable Long id) {
        SysUser user = sysUserService.getById(id);
        if (user != null) {
            sysUserService.delete(id, user.getUsername());
        }
        return R.ok();
    }

    @PutMapping("/resetPass")
    @ApiLog("重置密码")
    public R resetPass(@RequestBody UserInfo data) {
        SysUser user = sysUserService.getById(data.getId());
        if (user != null) {
            sysUserService.reset(data.getId(), data.getPassword(), user.getUsername());
        }
        return R.ok();
    }

    @PutMapping("/updatePass")
    @ApiLog("修改密码")
    public R updatePass(@RequestBody UserInfo data) {
        SysUser user = sysUserService.getById(data.getId());
        if (user == null || !AuthUtil.decrypt(authProps.getSaltKey(), user.getPassword()).equals(data.getPassword())) {
            throw new ServiceException("Old password entered incorrectly, please re-enter");
        }
        user.setPassword(AuthUtil.encode(authProps.getSaltKey(), data.getPassword()));
        return R.ok();
    }
}
