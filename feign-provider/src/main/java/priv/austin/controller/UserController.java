package priv.austin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import priv.austin.common.domain.base.CommonResult;
import priv.austin.entity.User;
import priv.austin.service.UserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Austin
 * @description 用户管理
 * @date 2021/8/16 4:41 下午
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/create")
    public CommonResult<?> create(@RequestBody User user) {
        userService.create(user);
        return CommonResult.success();
    }

    @GetMapping("/{id}")
    public CommonResult<?> getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        log.info("根据id获取用户信息，用户名称为：{}",user.getUsername());
        return CommonResult.success(user);

    }

    @GetMapping("/getUserByIds")
    public CommonResult<?> getUserByIds(@RequestParam List<Long> ids) {
        List<User> userList= userService.getUserByIds(ids);
        log.info("根据ids获取用户信息，用户列表为：{}",userList);
        return CommonResult.success(userList);

    }

    @GetMapping("/getByUsername")
    public CommonResult<?> getByUsername(@RequestParam String username) {
        User user = userService.getByUsername(username);
        return CommonResult.success(user);
    }

    @PostMapping("/update")
    public CommonResult<?> update(@RequestBody User user) {
        userService.update(user);
        return CommonResult.success();
    }

    @PostMapping("/delete/{id}")
    public CommonResult<?> delete(@PathVariable Long id) {
        userService.delete(id);
        return CommonResult.success();
    }
}
