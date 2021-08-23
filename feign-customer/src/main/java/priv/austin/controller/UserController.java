package priv.austin.controller;


import org.springframework.web.bind.annotation.*;
import priv.austin.common.entity.base.Result;
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
    public Result getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping("/getByUsername")
    public Result getByUsername(@RequestParam String username) {
        return userService.getByUsername(username);
    }

    @PostMapping("/create")
    public Result create(@RequestBody User user) {
        return userService.create(user);
    }

    @PostMapping("/update")
    public Result update(@RequestBody User user) {
        return userService.update(user);
    }

    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable Long id) {
        return userService.delete(id);
    }
}
