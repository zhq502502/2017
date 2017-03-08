package com.packages.util;

public class NumberUtil {
	/**
	 * 获取随机数
	 * @param length 长度
	 * @param letter 是否包含字母
	 * @return
	 */
	public static String getRandom(int length,boolean letter){
		String base = "";
		if(letter){
			base = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		}else{
			base = "0123456789";
		}
		String [] bases = base.split("");
		String result = "";
		for(int i=0;i<length;i++){
			int index = (int)(Math.random()*base.length());
			result+=bases[index];
		}		
		return result;
	}
}
