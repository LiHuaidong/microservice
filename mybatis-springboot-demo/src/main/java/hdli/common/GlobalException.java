package hdli.common;

/**
 * @Description 全局异常
 * @Author: Lihuaidong
 * @Date: Created at 15:37 2019/2/19
 */
public class GlobalException extends RuntimeException {

	public GlobalException(String message) {
		super(message);
	}

	public GlobalException(String message, Throwable cause) {
		super(message, cause);
	}

}
