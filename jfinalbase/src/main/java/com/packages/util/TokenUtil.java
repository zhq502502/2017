package com.packages.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.jfinal.plugin.activerecord.Db;
import com.packages.model.Token;

public class TokenUtil {
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 获取token
	 * @param useraccount
	 * @return
	 */
	public static String getNewToken(String useraccount){
		Db.update("delete from tbtoken where account=?",useraccount);
		String token = NumberUtil.getRandom(24, true);
		Calendar nowtime = Calendar.getInstance();
		Token bean = new Token();
		bean.set("account", useraccount);
		bean.set("token", token);
		bean.set("updatetime", format.format(nowtime.getTime()));
		bean.save();
		return token;
	}
	/**
	 * 验证token有效性
	 * @param token
	 * @return
	 */
	@SuppressWarnings({ "deprecation", "unused" })
	public static boolean validateToken(String token){
		Token bean = Token.dao.findFirst("select * from tbtoken where token=?",token);
		if(bean==null){
			return false;
		}else{
			Calendar nowtime = Calendar.getInstance();
			if(bean.get("updatetime")==null){
				return false;
			}
			Calendar beantime = Calendar.getInstance();
			try {
				beantime.setTime(format.parse(bean.get("updatetime").toString()));
			} catch (ParseException e) {
				e.printStackTrace();
				return false;
			}
			int timeMin = Integer.parseInt(PropUtil.getInstance().getValue("token.aging"));
			beantime.set(Calendar.MINUTE, beantime.get(Calendar.MINUTE)+timeMin);
			if(beantime.getTimeInMillis()<nowtime.getTimeInMillis()){
				return false;
			}
			bean.set("updatetime",format.format(nowtime.getTime()));
			bean.update();
			return true;
		}
	}
	/**
	 * 用于退出操作清楚系统中的token
	 * @param token
	 * @return
	 */
	public static boolean deleteToken(String token){
		return Db.update("delete from tbtoken where token=?",token)>0;
	}
}
