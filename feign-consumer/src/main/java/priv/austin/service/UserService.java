package priv.austin.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import priv.austin.common.domain.base.CommonResult;
import priv.austin.entity.User;
import priv.austin.service.impl.UserServiceFallbackImpl;

@FeignClient(value = "feign-provider",fallback = UserServiceFallbackImpl.class)
public interface UserService {
    @PostMapping("/user/create")
    CommonResult<?> create(@RequestBody User user);

    @GetMapping("/user/{id}")
    CommonResult<?> getUser(@PathVariable(value = "id") Long id);

    @GetMapping("/user/getByUsername")
    CommonResult<?> getByUsername(@RequestParam(value = "username") String username);

    @PostMapping("/user/update")
    CommonResult<?> update(@RequestBody User user);

    @PostMapping("/user/delete/{id}")
    CommonResult<?> delete(@PathVariable(value = "id") Long id);
}
