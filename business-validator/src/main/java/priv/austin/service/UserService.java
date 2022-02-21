package priv.austin.service;

import priv.austin.domain.User;

import java.util.List;

/**
 * @author Austin
 * @description 用户管理
 * @date 2022/2/21 3:36 下午
 */
public interface UserService {
    /**
     *
     * @param userName
     * @return
     */
    boolean existsByUserName(String userName);

    /**
     *
     * @param userName
     * @param email
     * @param telphone
     * @return
     */
    List<User> findByUserNameOrEmailOrTelphone(String userName, String email, String telphone);
}
