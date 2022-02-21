package priv.austin.common.domain.base;

import lombok.Getter;

/**
 * @author Austin
 * @description 常用API返回对象
 * @date 2021/8/16 2:27 下午
 */
@Getter
public enum ErrorCode implements IErrorCode{
    SUCCESS("0000","成功"),
    SYS_FAILED("XXXX", "系统内部异常"),

    /**
     * 参数校验异常 - 1
     */
    PARAM_VALIDATE_FAILED("1001","参数校验异常"),
    PARAM_UNPACKAGE_FAILED("1002","解包异常"),
    PARAM_REQ_FORMAT_FAILED("1003","请求参数格式化异常"),
    /**
     * 业务校验异常 - 2
     */
    BUSINESS_FAILED("2001","业务校验异常"),
    /**
     * 数据库异常 - 3
     */
    DB_SQL_FAILED("3001","数据库操作异常"),
    DB_DUPLICATE_KEY_FAILED("3002","数据库主键重复异常"),

    /**
     * 对外连接异常 - 5
     */
    CONNECT_FAILED("5001","连接异常"),
    CONNECT_TIME_OUT_FAILED("3002","连接超时异常"),
    ;

    /**
     * 应答码
     */
    private final String code;
    /**
     * 应答说明
     */
    private final String message;

    /**
     * 枚举构造
     * @param code 应答码
     * @param message 应答说明
     */
    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
