package yanggc.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;
import yanggc.base.BaseServlet;
import yanggc.domain.Category;
import yanggc.serviceImp.CategoryServiceImp;
import yanggc.utils.JedisUtils;

public class CategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	public String findAllCats(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json;charset=utf-8"); 			
		Jedis jedis = JedisUtils.getJedis();
		String jsonStr = jedis.get("allCats");
		if(null == jsonStr) {
			CategoryServiceImp csi = new CategoryServiceImp();
			 List<Category> list = csi.findAllCats();
			 jsonStr = JSONArray.fromObject(list).toString();
			jedis.set("allCats", jsonStr);
			System.out.println("redis is null......");
		}else {
			System.out.println("redis is not null......");
		}
		response.getWriter().write(jsonStr);
		jedis.close();
		return null;
	}
}