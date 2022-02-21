package priv.austin.annotation.validator;

import lombok.extern.slf4j.Slf4j;
import priv.austin.annotation.NotConflictUser;
import priv.austin.annotation.UniqueUser;
import priv.austin.domain.User;
import priv.austin.service.UserService;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.function.Predicate;

/**
 * @author Austin
 * @description 用户相关业务校验器
 * @date 2022/2/21 2:59 下午
 */
@Slf4j
public class UserValidation<T extends Annotation> implements ConstraintValidator<T, User> {

    protected Predicate<User> predicate = c -> true;

    @Resource
    protected UserService userService;

    @Override
    public boolean isValid(User user, ConstraintValidatorContext context) {
        return userService == null || predicate.test(user);
    }

    /**
     * 校验用户是否唯一
     * 即判断数据库是否存在当前新用户的信息，如用户名，手机，邮箱
     */
    public static class UniqueUserValidator extends UserValidation<UniqueUser>{
        @Override
        public void initialize(UniqueUser uniqueUser) {
            predicate = c -> !userService.existsByUserName(c.getUserName());
        }
    }

    /**
     * 校验是否与其他用户冲突
     * 将用户名、邮件、电话改成与现有完全不重复的，或者只与自己重复的，就不算冲突
     */
    public static class NotConflictUserValidator extends UserValidation<NotConflictUser>{
        @Override
        public void initialize(NotConflictUser notConflictUser) {
            predicate = c -> {
                log.info("user detail is {}",c);
                Collection<User> collection = userService.findByUserNameOrEmailOrTelphone(c.getUserName(), c.getEmail(), c.getTelphone());
                // 将用户名、邮件、电话改成与现有完全不重复的，或者只与自己重复的，就不算冲突
                return collection.isEmpty() || (collection.size() == 1 && collection.iterator().next().getId().equals(c.getId()));
            };
        }
    }
}
