package com.newpermission.core.result;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CommonCode implements Code {
	/** 服务器正常接收和处理请求 */
    SUCCESS(200, "操作成功")

    /** 服务器内部错误 */
    , SERVER_INERNAL_ERROR(500, "服务器内部错误，请联系管理员")

    /** Http请求错误 */
    , HTTP_METHOD_ERROR(415, "HTTP方法错误，请选择正确的方法")

    /** 参数错误 */
    , PARAMETER_ERROR(422, "参数错误")

    /** 服务器遇到了一个未曾预料的状况，导致了它无法完成对请求的处理。一般来说，这个问题都会在服务器的程序代码出错时出现 */
    , SYSTEM_ERROR(1000, "系统错误")

    /**上传文件失败 */
    , UPLOAD_FILE_ERROR(10099, "上传文件失败：上传的文件过大")

    /** 由于临时的服务器维护或者过载，服务器当前无法处理请求。这个状况是临时的，并且将在一段时间以后恢复 */
    , SERVICE_UNAVAILABLE(10001, "服务暂停")

    /** 业务异常 */
    , SERVICE_ERROR(10002, "")

    /** 需要授权认证的api接口要求请求方提供授权认证的参数值，而请求方没有提供该参数 */
    , MISSING_PERMISSION_PARAMTER(10006, "缺少访问令牌参数")

    /** 但请求方向服务器获取服务器不支持的媒体类型时会出现该错误，比如系统只支持mp4格式，而请求却获取rm格式的数据 */
    , TOKEN_INVALID(10007, "访问令牌失效")

    /** 请求的api接口不存在 */
    , REQUEST_API_NOT_FOUND(10017, "接口不存在")
    
    /** 订单已经续租过，无法再次续租 */
    , ORDER_CANNOT_RENEWAL(110001, "无法续租")
    
    /** 订单已经退板过，无法再次退板 */
    , ORDER_CANNOT_RETURN(110002, "无法退板")

    /** 数据不存在或已删除 */
    , DATA_DELETED(110003, "数据不存在或已删除！")
    
    /** 密码强度不足 */
    ,PASSWORD_NOTSTRONG(110004, "密码强度不符合要求！")
    ;
	
	private int code;
	
	private String message;

	@Override
	public int getCode() {
		return this.code;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

	@Override
	public void setMessage(String message) {
		this.message = message;
	}

}
