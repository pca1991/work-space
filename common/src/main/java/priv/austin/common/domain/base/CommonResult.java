package priv.austin.common.domain.base;

import lombok.Data;

/**
 * @author Austin
 * @description 通用返回对象
 * @date 2022/2/17 4:11 下午
 */
@Data
public class CommonResult<T> {
    /**
     * 应答码
     */
    private String code;
    /**
     * 应答说明
     */
    private String message;
    /**
     * 数据封装
     */
    private T data;

    /**
     * 构造
     * @param errorCode {@link ErrorCode}
     * @param data 数据封装
     */
    protected CommonResult(IErrorCode errorCode, T data) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.data = data;
    }
    /**
     * 构造
     * @param errorCode {@link ErrorCode}
     * @param message 应答说明
     * @param data 数据封装
     */
    protected CommonResult(IErrorCode errorCode, String message, T data) {
        this.code = errorCode.getCode();
        this.message = message;
        this.data = data;
    }
    /**
     * 成功返回
     * @param data 数据封装
     * @return 成功返回
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>(ErrorCode.SUCCESS, data);
    }
    /**
     * 成功返回
     * @return 成功返回
     */
    public static <T> CommonResult<T> success() {
        return new CommonResult<>(ErrorCode.SUCCESS, null);
    }
    /**
     * 失败返回
     * @param errorCode {@link ErrorCode}
     * @param message 应答说明
     * @return 失败返回
     */
    public static <T> CommonResult<T> failed(IErrorCode errorCode, String message) {
        return new CommonResult<>(errorCode, message, null);
    }
    /**
     * 失败返回
     * @param errorCode {@link ErrorCode}
     * @return 失败返回
     */
    public static <T> CommonResult<T> failed(IErrorCode errorCode) {
        return new CommonResult<>(errorCode, null);
    }

    /**
     * 失败返回结果
     */
    public static <T> CommonResult<T> failed() {
        return failed(ErrorCode.SYS_FAILED);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> CommonResult<T> validateFailed(String message) {
        return failed(ErrorCode.PARAM_VALIDATE_FAILED, message);
    }
}
