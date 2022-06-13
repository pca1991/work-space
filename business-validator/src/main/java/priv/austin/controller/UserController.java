package priv.austin.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.austin.domain.User;

import javax.validation.Valid;

/**
 * @author Austin
 * @description
 * @date 2022/2/21 4:09 下午
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @PostMapping("/create")
    public String createUser(@RequestBody @Valid User user){
        return "OK";
    }

    @SneakyThrows
    @PostMapping("/update")
    public void updateUser(@Valid @RequestBody User user){
    }
}
