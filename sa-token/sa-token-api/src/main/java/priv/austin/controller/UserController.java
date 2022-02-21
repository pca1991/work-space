package priv.austin.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.austin.common.api.CommonResult;
import priv.austin.common.domain.UserDTO;

/**
 * @author Austin
 * @description 获取登录用户信息接口
 * @date 2021/8/26 10:44 上午
 */
@RestController
@RequestMapping("/user")
public class UserController{

    @SaCheckPermission("api:user:info")
    @GetMapping("/info")
    public CommonResult<UserDTO> userInfo() {
        UserDTO userDTO = (UserDTO) StpUtil.getSession().get("userInfo");
        return CommonResult.success(userDTO);
    }

}
