package yanggc.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import redis.clients.jedis.Jedis;
import yanggc.domain.Category;
import yanggc.impl.CategoryServiceImpl;
import yanggc.service.CategoryService;
import yanggc.utils.JedisUtils;

public class CategoryServlet extends BaseServlet {

	public String findAllByJson(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//先从缓存去数据 没有相应的缓存数据 在去数据库进行查询 放到redis缓存中
		//Jedis jedis = JedisUtils.getJedis();
		//String categoryJson = jedis.get("categoryJson");
		String categoryJson = null;
		if(categoryJson==null){
			CategoryService catgtgoryService = new CategoryServiceImpl();
			List<Category> categoryList = catgtgoryService.findAll();//dao层数据库查询
			Gson gson = new Gson();
			categoryJson = gson.toJson(categoryList);
			// 放到redis缓存中
			//jedis.set("categoryJson", categoryJson);
			System.out.println("从数据库磁盘查询数据.....");
			
		}
		
		//将资源归还给jedis的连接池
	//	jedis.close();
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(categoryJson);
		
		return null;
	}

}