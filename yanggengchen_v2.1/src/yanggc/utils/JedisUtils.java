package yanggc.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtils {
	
	private static int maxTotal = 0;
	private static int maxIdle = 0;
	private static String host = null;
	private static int port = 0;
	
	private static JedisPoolConfig config = new JedisPoolConfig();
	static{
		//读取配置文件
		InputStream in = JedisUtils.class.getClassLoader().getResourceAsStream("jedis.properties");
		Properties pro = new Properties();
		try {
			pro.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		maxTotal = Integer.parseInt(pro.getProperty("maxTotal"));
		maxIdle = Integer.parseInt(pro.getProperty("maxIdle"));
		port = Integer.parseInt(pro.getProperty("port"));
		host = pro.getProperty("host");
		config.setMaxTotal(maxTotal);
		config.setMaxIdle(maxIdle);
	}
	
	private static JedisPool pool = new JedisPool(config,host,port );
	
	//提供一个返回池子的方法
	public static JedisPool getPool(){
		return pool;
	}
	
	//获得一个jedis资源方法
	public static Jedis getJedis(){
		return pool.getResource();
	}
	
	//关闭的方法
	public static void close(Jedis jedis){
		if(jedis!=null){
			jedis.close();
		}
	}
	
}
