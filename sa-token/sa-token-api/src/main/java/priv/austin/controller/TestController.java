package priv.austin.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.austin.common.api.CommonResult;

/**
 * @author Austin
 * @description 测试接口
 * @date 2021/8/26 10:45 上午
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @SaCheckPermission("api:test:hello")
    @GetMapping("/hello")
    public CommonResult hello() {
        return CommonResult.success("Hello World.");
    }

}
