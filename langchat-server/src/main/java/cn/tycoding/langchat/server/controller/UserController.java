package cn.tycoding.langchat.server.controller;

import cn.hutool.core.lang.Dict;
import cn.tycoding.langchat.server.common.dto.LcUserInfo;
import cn.tycoding.langchat.server.common.utils.AuthUtil;
import cn.tycoding.langchat.server.common.utils.MybatisUtil;
import cn.tycoding.langchat.server.common.utils.QueryPage;
import cn.tycoding.langchat.server.common.utils.R;
import cn.tycoding.langchat.server.entity.LcUser;
import cn.tycoding.langchat.server.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tycoding
 * @since 2023/11/15
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/langchat/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/info")
    public R<LcUserInfo> info() {
        LcUserInfo userInfo = userService.info(AuthUtil.getUsername());
        userInfo.setPassword(null);
        return R.ok(userInfo);
    }

    @GetMapping("/checkName")
    public R<Boolean> checkName(LcUser lcUser) {
        return R.ok(userService.checkName(lcUser));
    }

    @GetMapping("/list")
    public R<List<LcUser>> list(LcUser lcUser) {
        return R.ok(userService.list(lcUser));
    }

    @GetMapping("/page")
    public R<Dict> page(LcUser lcUser, QueryPage queryPage) {
        return R.ok(MybatisUtil.getData(userService.page(lcUser, queryPage)));
    }

    @GetMapping("/{id}")
    public R<LcUser> findById(@PathVariable Long id) {
        return R.ok(userService.findById(id));
    }

    @PostMapping
//    @PreAuthorize("@auth.hasAuth('upms:user:add')")
    public R<LcUser> add(@RequestBody LcUser lcUser) {
        userService.add(lcUser);
        return R.ok();
    }

    @PutMapping
//    @PreAuthorize("@auth.hasAuth('upms:user:update')")
    public R update(@RequestBody LcUser lcUser) {
        userService.update(lcUser);
        return R.ok();
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("@auth.hasAuth('upms:user:delete')")
    public R delete(@PathVariable Long id) {
        LcUser lcUser = userService.getById(id);
        if (lcUser != null) {
            userService.delete(id, lcUser.getUsername());
        }
        return R.ok();
    }

    @GetMapping("/reset")
//    @PreAuthorize("@auth.hasAuth('upms:user:reset')")
    public R reset(@RequestParam Long id, String password) {
        LcUser lcUser = userService.getById(id);
        if (lcUser != null) {
            userService.reset(id, password, lcUser.getUsername());
        }
        return R.ok();
    }
}
