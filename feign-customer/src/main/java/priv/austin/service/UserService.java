package priv.austin.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import priv.austin.common.entity.base.Result;
import priv.austin.entity.User;
import priv.austin.service.impl.UserServiceFallbackImpl;

@FeignClient(value = "feign-provider",fallback = UserServiceFallbackImpl.class)
public interface UserService {
    @PostMapping("/user/create")
    Result create(@RequestBody User user);

    @GetMapping("/user/{id}")
    Result getUser(@PathVariable(value = "id") Long id);

    @GetMapping("/user/getByUsername")
    Result getByUsername(@RequestParam(value = "username") String username);

    @PostMapping("/user/update")
    Result update(@RequestBody User user);

    @PostMapping("/user/delete/{id}")
    Result delete(@PathVariable(value = "id") Long id);
}
