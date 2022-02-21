package priv.austin.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import priv.austin.annotation.NotConflictUser;
import priv.austin.annotation.UniqueUser;
import priv.austin.domain.User;
import priv.austin.service.UserService;

import javax.validation.Valid;

/**
 * @author Austin
 * @description
 * @date 2022/2/21 4:09 下午
 */
@RestController
@RequestMapping("/senior/user")
@Slf4j
@Validated
public class UserController {
    @PostMapping
    public void createUser(@UniqueUser @Valid User user){
    }

    @SneakyThrows
    @PutMapping
    public void updateUser(@NotConflictUser @Valid @RequestBody User user){
    }
}
