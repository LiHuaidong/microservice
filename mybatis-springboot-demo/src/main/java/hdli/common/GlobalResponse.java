package hdli.common;

/**
 * @Description 公共返回值
 * @Author: Lihuaidong
 * @Date: Created at 15:44 2019/2/19
 */
public class GlobalResponse {

	private int code;

	private String tipMessage;

	private Object data;

	public GlobalResponse(int code, String tipMessage) {
		this.code = code;
		this.tipMessage = tipMessage;
	}

	public GlobalResponse(int code, String tipMessage, Object data) {
		this.code = code;
		this.tipMessage = tipMessage;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getTipMessage() {
		return tipMessage;
	}

	public void setTipMessage(String tipMessage) {
		this.tipMessage = tipMessage;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
