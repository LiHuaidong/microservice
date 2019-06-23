package hdli.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description 全局异常处理
 * @Author: Lihuaidong
 * @Date: Created at 15:54 2019/2/19
 */
@ControllerAdvice("hdli.controller")
public class GlobalExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public GlobalResponse handle(Exception e) {
		if (e instanceof GlobalException) {
			return new GlobalResponse(0, e.getMessage());
		} else {
			return new GlobalResponse(0, "服务器内部错误");
		}
	}

}
