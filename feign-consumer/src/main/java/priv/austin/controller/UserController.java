package priv.austin.controller;


import org.springframework.web.bind.annotation.*;
import priv.austin.common.domain.base.CommonResult;
import priv.austin.entity.User;
import priv.austin.service.UserService;

import javax.annotation.Resource;

/**
 * @author qxu
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/{id}")
    public CommonResult<?> getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping("/getByUsername")
    public CommonResult<?> getByUsername(@RequestParam String username) {
        return userService.getByUsername(username);
    }

    @PostMapping("/create")
    public CommonResult<?> create(@RequestBody User user) {
        return userService.create(user);
    }

    @PostMapping("/update")
    public CommonResult<?> update(@RequestBody User user) {
        return userService.update(user);
    }

    @PostMapping("/delete/{id}")
    public CommonResult<?> delete(@PathVariable Long id) {
        return userService.delete(id);
    }
}
