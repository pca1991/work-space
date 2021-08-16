package priv.austin.common.advice;

import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;
import priv.austin.common.entity.base.ErrorCode;
import priv.austin.common.entity.base.Result;
import priv.austin.common.entity.base.RetCode;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.security.InvalidKeyException;
import java.sql.SQLException;
import java.util.stream.Collectors;

/**
 * @author Austin
 * @description 全局异常捕获
 * @date 2021/8/16 2:17 下午
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * mvc body 转换异常
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public Result jsonParseException(HttpMessageNotReadableException e) {
        Result<Object> result = Result.ofFail(ErrorCode.UNPACKAGE_ERROR, e.getMessage());
        result.setFinRetcode(RetCode.FAIL.getCode());
        return result;
    }

    /**
     * 参数校验异常
     */
    @ResponseBody
    @ExceptionHandler(value = {ConstraintViolationException.class})
    public Result violationHandler(ConstraintViolationException e) {
        String collect = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(","));
        Result<Object> result = Result.ofUnknow(ErrorCode.PARAM_ERROR.getCode(), collect);
        result.setFinRetcode(RetCode.FAIL.getCode());
        return result;
    }

    /**
     * 参数校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result paramsException(MethodArgumentNotValidException e) {
        String collect = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ":" + fieldError.getDefaultMessage())
                .collect(Collectors.joining(","));
        Result<Object> result = Result.ofUnknow(ErrorCode.PARAM_ERROR.getCode(), collect);
        result.setFinRetcode(RetCode.FAIL.getCode());
        return result;
    }

    @ExceptionHandler(JsonMappingException.class)
    @ResponseBody
    public Result jsonParseError(JsonMappingException e) {
        Result<Object> result = Result.ofFail(ErrorCode.REQ_PARAM_FORMAT_FAIL);
        result.setFinRetcode(RetCode.FAIL.getCode());
        return result;
    }

    /**
     * 渠道使用：签名异常
     *
     * @param e Exception
     * @return Result
     */
    @ExceptionHandler(InvalidKeyException.class)
    @ResponseBody
    public Result signException(InvalidKeyException e) {
        log.error("签名相关异常: " + e.getMessage(), e);
        return Result.ofUnknow(ErrorCode.PACK_ERROR.getCode(), e.getMessage());
    }

    /**
     * 数据库异常
     */
    @ExceptionHandler({SQLException.class, DuplicateKeyException.class})
    @ResponseBody
    public Result sqlException(Exception e) {
        log.error("SQL error: " + e.getMessage(), e);
        if (e instanceof DuplicateKeyException) {
            return Result.ofFail(ErrorCode.ORG_TRACE_ERROR, ", 重复");
        }
        return Result.ofUnknow(ErrorCode.ADD_JNLS_ERROR.getCode(), e.getMessage());
    }

    /**
     * http 请求
     */
    @ExceptionHandler({SocketTimeoutException.class, InterruptedIOException.class})
    @ResponseBody
    public Result socketTimeoutException(IOException e) {
        log.error("IOException error: " + e.getMessage(), e);
        /*
            connect 超时：请求失败
            其他超时（read等）：请求未知
         */
        if ("connect timed out".equals(e.getMessage())) {
            return Result.ofFail(ErrorCode.HTTP_READ_EXC, RetCode.TIME_OUT);
        }
        return Result.ofUnknow(ErrorCode.HTTP_READ_EXC);
    }

    /**
     * 运行时异常
     *
     * @param e Exception
     * @return vo
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Result otherException(RuntimeException e) {
        Throwable cause = e.getCause();
        String appendMsg = "";
        log.error("运行时异常:", e);
        return Result.ofFail(ErrorCode.SYS_INNER_ERROR);
    }

    /**
     * 其他未捕获异常
     *
     * @param e Exception
     * @return Result
     */
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public Result otherException(Throwable e) {
        e.printStackTrace();
        log.error("未捕获的全局异常:{}, {} ", e.getClass().getSimpleName(), e.getMessage());
        log.error("错误堆栈:", e);
        return Result.ofFail(ErrorCode.SYS_INNER_ERROR);

    }
}
