package priv.austin.service.impl;

import org.springframework.stereotype.Component;
import priv.austin.common.entity.base.ErrorCode;
import priv.austin.common.entity.base.Result;
import priv.austin.common.entity.base.RetCode;
import priv.austin.entity.User;
import priv.austin.service.UserService;

@Component
public class UserServiceFallbackImpl implements UserService {
    @Override
    public Result create(User user) {
        User defaultUser = new User(-1L, "defaultUser", "123456");
        return Result.ofSuccess(defaultUser, RetCode.SUCCESS);
    }

    @Override
    public Result getUser(Long id) {
        User defaultUser = new User(-1L, "defaultUser", "123456");
        return Result.ofSuccess(defaultUser, RetCode.SUCCESS);
    }

    @Override
    public Result getByUsername(String username) {
        User defaultUser = new User(-1L, "defaultUser", "123456");
        return Result.ofSuccess(defaultUser, RetCode.SUCCESS);
    }

    @Override
    public Result update(User user) {
        return Result.ofFail(ErrorCode.SYS_INNER_ERROR,"调用失败，服务被降级");
    }

    @Override
    public Result delete(Long id) {
        return Result.ofFail(ErrorCode.SYS_INNER_ERROR,"调用失败，服务被降级");
    }
}
