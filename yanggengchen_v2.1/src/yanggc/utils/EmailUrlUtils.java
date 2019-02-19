package yanggc.utils;

public class EmailUrlUtils {

	public static String getEmailLoginUrl(String email){
		//认为判断是哪个邮箱登录地址
		String emailLoginUrl = "";
		String com = email.split("@")[1];
		if(com.contains("126")){
			emailLoginUrl = "http://mail.126.com";
		}else if(com.contains("qq")){
			emailLoginUrl = "http://mail.qq.com";
		}
		return emailLoginUrl;
		
	}
	
}
