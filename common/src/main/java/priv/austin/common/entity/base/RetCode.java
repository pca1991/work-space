package priv.austin.common.entity.base;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Austin
 * @description 返回枚举类
 * @date 2021/8/16 2:47 下午
 */
@Getter
public enum RetCode {

    SUCCESS("00", "成功"),

    FAIL("XX", "失败"),

    TIME_OUT("98", "超时"),

    UNKNOWN("99", "未知"),

    CLOSED("X0", "已关闭"),

    CANCELED("X1", "已撤销"),

    REFUNDED("X2", "已转入退款"),

    NOT_FOUND("25", "查无此交易"),

    ;

    private String code;

    private String desc;

    RetCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private static Map<String, RetCode> map = new HashMap<>();

    static {
        for (RetCode value : RetCode.values()) {
            map.put(value.getCode(), value);
        }
    }

    public static RetCode parseCode(String code) {
        if (map.containsKey(code)) {
            return map.get(code);
        }
        return null;
    }

    /**
     * 是否终态
     */
    public static boolean isFinalState(RetCode retCode) {
        return !(TIME_OUT.equals(retCode) || UNKNOWN.equals(retCode) || NOT_FOUND.equals(retCode));
    }

    /**
     * 是否是失败的
     * @param retCode
     * @return
     */
    public static boolean isFailState(RetCode retCode) {
        return (FAIL.equals(retCode) || CLOSED.equals(retCode) || CANCELED.equals(retCode) || REFUNDED.equals(retCode));
    }

}
