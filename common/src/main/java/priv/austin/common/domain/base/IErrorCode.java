package priv.austin.common.domain.base;

/**
 * @author Austin
 * @description 常用API返回对象接口
 * @date 2021/8/16 2:23 下午
 */
public interface IErrorCode {
    /**
     * 获取应答码
     * @return 应答码
     */
    String getCode();

    /**
     * 获取应答说明
     * @return 应答说明
     */
    String getMessage();
}
