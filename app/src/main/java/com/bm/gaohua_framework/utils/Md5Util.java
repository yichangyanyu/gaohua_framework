package com.bm.gaohua_framework.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 对密码进行加密和验证的程序
 * 
 * @author ray
 * 
 */
public class Md5Util {

	 /**
    *
    * @param plainText
    *            明文
    * @return 32位密文
    */
   public static String encryption(String plainText) {
       String re_md5 = new String();
       try {
           MessageDigest md = MessageDigest.getInstance("MD5");
           md.update(plainText.getBytes());
           byte b[] = md.digest();

           int i;

           StringBuffer buf = new StringBuffer("");
           for (int offset = 0; offset < b.length; offset++) {
               i = b[offset];
               if (i < 0)
                   i += 256;
               if (i < 16)
                   buf.append("0");
               buf.append(Integer.toHexString(i));
           }

           re_md5 = buf.toString();

       } catch (NoSuchAlgorithmException e) {
           e.printStackTrace();
       }
       return re_md5;
   }
//

}
