package priv.austin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Austin
 * @description 用户对象
 * @date 2021/8/16 4:46 下午
 */
@Data
@AllArgsConstructor
public class User implements Serializable {

    private Long id;

    private String username;

    private String password;
}
