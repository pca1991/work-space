package priv.austin.advice;

import cn.dev33.satoken.exception.DisableLoginException;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.austin.common.api.CommonResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Austin
 * @description 全局异常捕获
 * @date 2021/8/26 4:45 下午
 */
@ControllerAdvice // 可指定包前缀，比如：(basePackages = "com.pj.admin")
public class GlobalExceptionHandler {

    // 在当前类每个方法进入之前触发的操作
    //@ModelAttribute
    //public void get(HttpServletRequest request) throws IOException {
    //
    //}


    // 全局异常拦截（拦截项目中的所有异常）
    @ResponseBody
    @ExceptionHandler
    public CommonResult handlerException(Exception e, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // 不同异常返回不同状态码
        //AjaxJson aj = null;
        //if (e instanceof NotLoginException) {	// 如果是未登录异常
        //    NotLoginException ee = (NotLoginException) e;
        //    aj = AjaxJson.getNotLogin().setMsg(ee.getMessage());
        //} else if(e instanceof NotRoleException) {		// 如果是角色异常
        //    NotRoleException ee = (NotRoleException) e;
        //    aj = AjaxJson.getNotJur("无此角色：" + ee.getRole());
        //} else if(e instanceof NotPermissionException) {	// 如果是权限异常
        //    NotPermissionException ee = (NotPermissionException) e;
        //    aj = AjaxJson.getNotJur("无此权限：" + ee.getCode());
        //} else if(e instanceof DisableLoginException) {	// 如果是被封禁异常
        //    DisableLoginException ee = (DisableLoginException) e;
        //    aj = AjaxJson.getNotJur("账号被封禁：" + ee.getDisableTime() + "秒后解封");
        //} else {	// 普通异常, 输出：500 + 异常信息
        //    aj = AjaxJson.getError(e.getMessage());
        //}

        // 返回给前端
        return CommonResult.failed(e.getMessage());

        // 输出到客户端
//		response.setContentType("application/json; charset=utf-8"); // http说明，我要返回JSON对象
//		response.getWriter().print(new ObjectMapper().writeValueAsString(aj));
    }



    // 全局异常拦截（拦截项目中的NotLoginException异常）
//	@ExceptionHandler(NotLoginException.class)
//	public AjaxJson handlerNotLoginException(NotLoginException nle, HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//
//		// 打印堆栈，以供调试
//		nle.printStackTrace();
//
//		// 判断场景值，定制化异常信息
//		String message = "";
//		if(nle.getType().equals(NotLoginException.NOT_TOKEN)) {
//			message = "未提供token";
//		}
//		else if(nle.getType().equals(NotLoginException.INVALID_TOKEN)) {
//			message = "token无效";
//		}
//		else if(nle.getType().equals(NotLoginException.TOKEN_TIMEOUT)) {
//			message = "token已过期";
//		}
//		else if(nle.getType().equals(NotLoginException.BE_REPLACED)) {
//			message = "token已被顶下线";
//		}
//		else if(nle.getType().equals(NotLoginException.KICK_OUT)) {
//			message = "token已被踢下线";
//		}
//		else {
//			message = "当前会话未登录";
//		}
//
//		// 返回给前端
//		return AjaxJson.getError(message);
//	}


}
