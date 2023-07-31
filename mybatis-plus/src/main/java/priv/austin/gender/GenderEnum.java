package priv.austin.gender;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Austin
 * @description 性别枚举
 * @date 2023/6/29 15:36
 */
@AllArgsConstructor
@Getter
public enum GenderEnum {
    /**
     * 男
     */
    BOY("1","男"),
    /**
     * 女
     */
    GIRL("2","女");

    /**
     * 代码
     */
    @EnumValue
    @JsonValue //标记响应json值
    private final String code;
    /**
     * 描述
     */
    private final String desc;

    @Override
    public String toString() {
        return this.code;
    }
}
