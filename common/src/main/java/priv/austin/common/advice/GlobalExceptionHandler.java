package priv.austin.common.advice;

import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;
import priv.austin.common.domain.base.CommonResult;
import priv.austin.common.domain.base.ErrorCode;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
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
    public CommonResult<?> jsonParseException(HttpMessageNotReadableException e) {
        return CommonResult.failed(ErrorCode.PARAM_UNPACKAGE_FAILED);
    }

    /**
     * 参数校验异常
     */
    @ResponseBody
    @ExceptionHandler(value = {ConstraintViolationException.class})
    public CommonResult<?> violationHandler(ConstraintViolationException e) {
        String message = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(","));

        return CommonResult.failed( ErrorCode.PARAM_VALIDATE_FAILED, message);
    }

    /**
     * 参数校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public CommonResult<?> paramsException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ":" + fieldError.getDefaultMessage())
                .collect(Collectors.joining(","));

        return CommonResult.failed( ErrorCode.PARAM_VALIDATE_FAILED, message);
    }

    @ExceptionHandler(JsonMappingException.class)
    @ResponseBody
    public CommonResult<?> jsonParseError(JsonMappingException e) {
        return CommonResult.failed( ErrorCode.PARAM_REQ_FORMAT_FAILED);
    }

    /**
     * 数据库异常
     */
    @ExceptionHandler({SQLException.class, DuplicateKeyException.class})
    @ResponseBody
    public CommonResult<?> sqlException(Exception e) {
        log.error("SQL error: " + e.getMessage(), e);
        if (e instanceof DuplicateKeyException) {
            return CommonResult.failed( ErrorCode.DB_DUPLICATE_KEY_FAILED);
        }
        return CommonResult.failed( ErrorCode.DB_SQL_FAILED);
    }

    /**
     * http 请求
     */
    @ExceptionHandler({SocketTimeoutException.class, InterruptedIOException.class})
    @ResponseBody
    public CommonResult<?> socketTimeoutException(IOException e) {
        log.error("IOException error: " + e.getMessage(), e);
        /*
            connect 超时：请求失败
            其他超时（read等）：请求未知
         */
        if ("connect timed out".equals(e.getMessage())) {
            return CommonResult.failed( ErrorCode.CONNECT_TIME_OUT_FAILED);
        }
        return CommonResult.failed( ErrorCode.CONNECT_FAILED);
    }

    /**
     * 运行时异常
     *
     * @param e Exception
     * @return vo
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public CommonResult<?> otherException(RuntimeException e) {
        log.error("运行时异常:", e.getCause());
        return CommonResult.failed( ErrorCode.SYS_FAILED);
    }

    /**
     * 其他未捕获异常
     *
     * @param e Exception
     * @return Result
     */
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public CommonResult<?> otherException(Throwable e) {
        e.printStackTrace();
        log.error("未捕获的全局异常:{}, {} ", e.getClass().getSimpleName(), e.getMessage());
        log.error("错误堆栈:", e);
        return CommonResult.failed( ErrorCode.SYS_FAILED);
    }
}
