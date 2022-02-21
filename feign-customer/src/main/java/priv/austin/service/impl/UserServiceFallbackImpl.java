package priv.austin.service.impl;

import org.springframework.stereotype.Component;
import priv.austin.common.domain.base.CommonResult;
import priv.austin.common.domain.base.ErrorCode;
import priv.austin.entity.User;
import priv.austin.service.UserService;

@Component
public class UserServiceFallbackImpl implements UserService {
    @Override
    public CommonResult<?> create(User user) {
        return CommonResult.success( new User(-1L, "defaultUser", "123456")) ;
    }

    @Override
    public CommonResult<?> getUser(Long id) {
        return CommonResult.success( new User(-1L, "defaultUser", "123456")) ;

    }

    @Override
    public CommonResult<?> getByUsername(String username) {
        return CommonResult.success( new User(-1L, "defaultUser", "123456")) ;

    }

    @Override
    public CommonResult<?> update(User user) {
        return CommonResult.failed(ErrorCode.SYS_FAILED,"调用失败，服务被降级");
    }

    @Override
    public CommonResult<?> delete(Long id) {
        return CommonResult.failed(ErrorCode.SYS_FAILED,"调用失败，服务被降级");
    }
}
