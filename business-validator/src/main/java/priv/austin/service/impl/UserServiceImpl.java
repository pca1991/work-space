package priv.austin.service.impl;

import org.springframework.stereotype.Service;
import priv.austin.domain.User;
import priv.austin.service.UserService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Austin
 * @description 用户管理实现
 * @date 2022/2/21 3:50 下午
 */
@Service
public class UserServiceImpl implements UserService {
    private List<User> userList;

    /**
     * @param userName
     * @return
     */
    @Override
    public boolean existsByUserName(String userName) {
        return userList.stream().anyMatch(c -> c.getUserName().equals(userName));
    }

    /**
     * @param userName
     * @param email
     * @param telphone
     * @return
     */
    @Override
    public List<User> findByUserNameOrEmailOrTelphone(String userName, String email, String telphone) {
        return null;
    }

    @PostConstruct
    private void initialize(){
        userList = new ArrayList<User>(){{
            add(new User("1", "qxu", "18721300176", "123@qq.com"));
            add(new User("2", "jdong", "13329940807", "jjj@qq.com"));
            add(new User("3", "rwu", "13907171321", "rw@qq.com"));
        }};
    }
}
