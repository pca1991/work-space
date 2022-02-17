package priv.austin.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Austin
 * @description 信息变更通知对象
 * @date 2021/11/16 1:50 下午
 */
@Data
public class NotifyDto {
    /**
     * 内部商户号
     */
    private String merCode;
    /**
     * 终端号
     */
    private String termCode;
    /**
     * 触发通知的类别，M：商户，T：终端，其他：功能id
     */
    private String notifyType;
    /**
     * 触发通知的行为，1：开通，2：修改，3：关闭
     */
    private Integer behavior;
    /**
     * 更新时间
     */
    private String updateDate;
}
