package yanggc.utils;

import java.util.UUID;

public class CommonUtils {

	public static String getUUID(){
		String uuid = UUID.randomUUID().toString().replace("-", "");
		return uuid;
	}
	
}
