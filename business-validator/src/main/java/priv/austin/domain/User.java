package priv.austin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author Austin
 * @description 用户类
 * @date 2022/2/21 11:35 上午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @NotNull(message = "用户id不能为空")
    private String id;
    @NotNull(message = "用户名不能为空")
    private String userName;

    private String telphone;

    private String email;
}
