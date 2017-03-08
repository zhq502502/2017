package com.packages.controller;

import com.jfinal.core.Controller;
import com.packages.core.ResultBean;
import com.packages.util.Error;
import com.packages.util.TokenUtil;

public class UserController extends Controller {
	public void login(){
		ResultBean result = new ResultBean();
		String account = getPara("account");
		String password = getPara("password");
		//登录验证逻辑
		result.setCode(Error.SUCCESS);
		//--
		String token = TokenUtil.getNewToken(account);
		result.setData(token);
		renderJson(result);
	}
	
	public void logout(){
		String token = getPara("t");
		ResultBean result = new ResultBean();
		result.setCode(TokenUtil.deleteToken(token)?Error.SUCCESS:Error.FAIL);
		renderJson(result);
	}
	
	public void validate(){
		String token = getPara("t");
		ResultBean result = new ResultBean();
		result.setCode(TokenUtil.validateToken(token)?Error.SUCCESS:Error.TOKEN_ERROR);
		renderJson(result);
	}
}
