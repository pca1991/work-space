package priv.austin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import priv.austin.common.entity.base.ErrorCode;
import priv.austin.common.entity.base.Result;
import priv.austin.common.entity.base.RetCode;
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
    public Result create(@RequestBody User user) {
        userService.create(user);
        return Result.ofSuccess(null, RetCode.SUCCESS);
    }

    @GetMapping("/{id}")
    public Result getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        log.info("根据id获取用户信息，用户名称为：{}",user.getUsername());
        return Result.ofSuccess(user, RetCode.SUCCESS);
    }

    @GetMapping("/getUserByIds")
    public Result getUserByIds(@RequestParam List<Long> ids) {
        List<User> userList= userService.getUserByIds(ids);
        log.info("根据ids获取用户信息，用户列表为：{}",userList);
        return Result.ofSuccess(userList, RetCode.SUCCESS);
    }

    @GetMapping("/getByUsername")
    public Result getByUsername(@RequestParam String username) {
        User user = userService.getByUsername(username);
        return Result.ofSuccess(user, RetCode.SUCCESS);
    }

    @PostMapping("/update")
    public Result update(@RequestBody User user) {
        userService.update(user);
        return Result.ofSuccess(null, RetCode.SUCCESS);
    }

    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable Long id) {
        userService.delete(id);
        return Result.ofSuccess(null, RetCode.SUCCESS);
    }
}
