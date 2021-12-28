package cn.keovi.utils;


/**
 *
* @version
 */
public class JsonResult implements java.io.Serializable {

    private boolean status = true;

	private Object code=200;

	private long totalSize;

	private long total;

    private String message;	//成功/错误消息提示

    private Object data;	//数据包内容

	private boolean encode;	//是否进行Base64编码

	public Object getCode() {
		return code;
	}

	public void setCode(Object code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}

	public boolean isEncode() {
		return encode;
	}

	public void setEncode(boolean encode) {
		this.encode = encode;
	}

	//构建一个成功的数据区域
	public static JsonResult buildOk(Object datas){
		JsonResult result=new JsonResult();
		result.setStatus(true);
		result.setData(datas);
		return result;
	}
	//构建一个失败的结果
	public static JsonResult buildError(String msg){
		JsonResult result=new JsonResult();
		result.setStatus(false);
		result.setMessage(msg);
		return result;
	}
	//构建一个失败的结果
	public static JsonResult buildError(String msg,Object code){
		JsonResult result=new JsonResult();
		result.setStatus(false);
		result.setMessage(msg);
		result.setCode(code);
		return result;
	}

	@Override
	public String toString() {
		return "JsonResult{" +
				"status=" + status +
				", code=" + code +
				", totalSize=" + totalSize +
				", total=" + total +
				", message='" + message + '\'' +
				", data=" + data +
				'}';
	}
}

