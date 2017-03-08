package com.packages.util;

public class Error {
	/**成功*/
	public static final int SUCCESS = 0;
	/**失败*/
	public static final int FAIL = 1;
	/**参数错误*/
	public static final int ERROR_PARAM = 3;
	/**订单号已存在*/
	public static final int ERROR_DDH_EXIST = 4;
	/**支付异常*/
	public static final int PAY_SYSTEMERROR = 5;
	/**token失效*/
	public static final int TOKEN_ERROR = 6;
	
	public static String getErrorMsg(int code){
		String msg = "未知的错误信息";
		switch (code) {
		case SUCCESS:
			msg = "成功";  
			break;
		case FAIL:
			msg = "失败";  
			break;
		case ERROR_PARAM:
			msg = "参数异常";  
			break;
		case ERROR_DDH_EXIST:
			msg = "订单号已存在";  
			break;
		case PAY_SYSTEMERROR:
			msg = "支付异常";  
			break;
		case TOKEN_ERROR:
			msg = "TOKEN无效";  
			break;

		default:
			break;
		}
		return msg;
	}
}
