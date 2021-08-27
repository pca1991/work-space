package priv.austin.common.entity.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Austin
 * @description 返回对象
 * @date 2021/8/16 2:31 下午
 */
@Data
@ApiModel("Result")
public class Result<R> {
    @ApiModelProperty("是否成功")
    private boolean success;
    @ApiModelProperty("系统应答码")
    private String sysRetcode;
    @ApiModelProperty("系统应答码解释")
    private String sysRetmsg;
    @ApiModelProperty("终态应答码")
    private String finRetcode;
    @ApiModelProperty("返回数据")
    private R data;


    public static <R> Result<R> ofSuccess(R data, RetCode finRetcode) {
        Result<R> result = new Result<R>();
        result.setSuccess(true);
        result.setSysRetcode("000000");
        result.setSysRetmsg("success");
        result.setFinRetcode(finRetcode.getCode());
        result.setData(data);
        return result;
    }

    public static <R> Result<R> ofSuccess(R data) {
        Result<R> result = new Result<R>();
        result.setSuccess(true);
        result.setSysRetcode("000000");
        result.setSysRetmsg("success");
        result.setFinRetcode(RetCode.SUCCESS.getCode());
        result.setData(data);
        return result;
    }


    public static <R> Result<R> ofUnknow(String code, String msg) {
        Result<R> result = new Result<>();
        result.setSuccess(false);
        result.setSysRetcode(code);
        result.setSysRetmsg(msg);
        result.setFinRetcode(RetCode.UNKNOWN.getCode());
        return result;
    }

    public static <R> Result<R> ofUnknow(ErrorCode code) {
        Result<R> result = new Result<>();
        result.setSuccess(false);
        result.setSysRetcode(code.getCode());
        result.setSysRetmsg(code.getMessage());
        result.setFinRetcode(RetCode.UNKNOWN.getCode());
        return result;
    }

    public static <R> Result<R> ofFail(String code, String finRetcode, String msg) {
        Result<R> result = new Result<>();
        result.setSuccess(false);
        result.setSysRetcode(code);
        result.setSysRetmsg(msg);
        result.setFinRetcode(finRetcode);
        return result;
    }

    public static <R> Result<R> ofFail(ErrorCode code, RetCode finRetcode) {
        Result<R> result = new Result<>();
        result.setSuccess(false);
        result.setSysRetcode(code.getCode());
        result.setSysRetmsg(code.getMessage());
        result.setFinRetcode(finRetcode.getCode());
        return result;
    }

    public static <R> Result<R> ofFail(ErrorCode code) {
        Result<R> result = new Result<>();
        result.setSuccess(false);
        result.setSysRetcode(code.getCode());
        result.setSysRetmsg(code.getMessage());
        result.setFinRetcode(RetCode.UNKNOWN.getCode());
        return result;
    }

    public static <R> Result<R> ofFail(ErrorCode code, String appendMsg) {
        Result<R> result = new Result<>();
        result.setSuccess(false);
        result.setSysRetcode(code.getCode());
        result.setSysRetmsg(code.getMessage() + appendMsg);
        result.setFinRetcode(RetCode.UNKNOWN.getCode());
        return result;
    }


    public static <R> Result<R> ofThrowable(String code, Throwable throwable) {
        Result<R> result = new Result<>();
        result.setSuccess(false);
        result.setSysRetcode(code);
        result.setFinRetcode(RetCode.UNKNOWN.getCode());
        result.setSysRetmsg(throwable.getClass().getName() + ", " + throwable.getMessage());
        return result;
    }
}
