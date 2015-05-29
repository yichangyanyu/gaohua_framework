package com.bm.gaohua_framework.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * Copyright © 2014 蓝色互动. All rights reserved.
 * 
 * @Description 格式验证工具类
 * @author 高骅
 * @date 2014-11-4 上午10:01:36
 */
public class FormatCheckUtil {
	private static FormatCheckUtil checkUtil;

	public static FormatCheckUtil getInstance() {
		if (checkUtil==null) {
			checkUtil = new FormatCheckUtil();
		}
		return checkUtil;
	}

	private FormatCheckUtil() {

	}

	/**
	 * 
	 * @author 高骅
	 * @Description 验证车牌号
	 * @return boolean
	 * @date 2014-11-4 上午10:03:53
	 */
	public boolean isCarNO(String carNumber) {
		Pattern p = Pattern.compile("^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$");
		Matcher m = p.matcher(carNumber);
		return m.matches();
	}

	/**
	 * 
	 * @author 高骅
	 * @Description 验证手机号
	 * @return boolean
	 * @date 2014-11-4 上午10:04:51
	 */
	public boolean isMobileNO(String mobiles) {

		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	/**
	 * 
	 * @author 高骅
	 * @Description 验证邮箱
	 * @return boolean
	 * @date 2014-11-4 上午10:05:21
	 */
	public boolean is_Email(String email_str) {

		Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
		Matcher m = pattern.matcher(email_str);
		return m.matches();
	}

	/**
	 * 
	 * @author 高骅
	 * @Description 验证银行卡号
	 * @return boolean
	 * @date 2014-11-4 上午10:07:58
	 */
	public boolean validateBankCard(String bankCard) {
		if (bankCard == null)
			return false;
		String pattern = "^\\d{13,19}$";
		return bankCard.matches(pattern);
	}

	/**
	 * 
	 * @author 高骅
	 * @Description 验证身份证
	 * @return boolean
	 * @date 2014-11-4 上午10:09:13
	 */
	public boolean validateIdCard(String idCard) {
		if (idCard == null)
			return false;
		String pattern = "^[0-9]{17}[0-9|xX]{1}$";
		return idCard.matches(pattern);
	}
	
	/**
	 * 
	 * @author 高骅
	 * @Description 验证密码
	 * @return boolean
	 * @date 2014-11-4 上午10:09:13
	 */
	public boolean checkPassWord(String pwd){
		if (pwd == null)
			return false;
		String pattern = "^[a-zA-Z0-9]{8,16}+$";
		return pwd.matches(pattern);
	}
}
