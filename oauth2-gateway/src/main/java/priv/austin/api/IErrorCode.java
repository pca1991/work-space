package priv.austin.api;

/**
 * @author Austin
 * @description 封装API错误码
 * @date 2021/8/24 2:26 下午
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}
