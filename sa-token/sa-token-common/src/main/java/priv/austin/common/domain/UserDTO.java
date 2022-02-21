package priv.austin.common.domain;

import lombok.*;

import java.util.List;

/**
 * @author Austin
 * @description 用户信息类
 * @date 2021/8/24 2:10 下午
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private List<String> permissionList;
}
