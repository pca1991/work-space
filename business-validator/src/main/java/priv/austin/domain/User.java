package priv.austin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Austin
 * @description 用户类
 * @date 2022/2/21 11:35 上午
 */
@Data
@AllArgsConstructor
public class User {
    private String id;

    private String userName;

    private String telphone;

    private String email;
}
