package priv.austin.component;

import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.stereotype.Component;
import priv.austin.common.entity.UserDTO;

import java.util.List;


/**
 * @author Austin
 * @description 自定义权限验证接口扩展
 * @date 2021/8/26 10:21 上午
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 返回此 loginId 拥有的权限码列表
        UserDTO userDTO = (UserDTO) StpUtil.getSession().get("userInfo");
        return userDTO.getPermissionList();
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 返回此 loginId 拥有的角色码列表
        return null;
    }

}
