package yanggc.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yanggc.domain.Category;
import yanggc.domain.Product;
import yanggc.impl.CategoryServiceImpl;
import yanggc.impl.ProductServiceImpl;
import yanggc.service.CategoryService;
import yanggc.service.ProductService;
import yanggc.utils.BeanFactory;
import yanggc.vo.PageBean;

public class ProductServlet extends BaseServlet {
	
	//根据商品的id查询商品信息
	public String findById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pid = request.getParameter("pid");
		//ProductService productService = new ProductServiceImpl();
		ProductService productService = (ProductService) BeanFactory.getBean("productService");
		Product product = productService.findById(pid);
		
		request.setAttribute("product", product);
		
		return "dispatcher:product_info";
	}
	//根据商品的类别进行分页查询
	public String findByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//获得商品类别cid
		String cid = request.getParameter("cid");
		//当前页	
		String pageNumStr = request.getParameter("pageNum");
		if(pageNumStr==null) pageNumStr = "1";
		int pageNum = Integer.parseInt(pageNumStr);//模拟第一页
		//规定每页显示的条数
		int pageSize = 12;
		
		//访问service层 获得PageBean对象
		ProductService productService = new ProductServiceImpl();
		//select * from product where cid=? limit ?,?
		PageBean<Product> pageBean = productService.page(cid,pageNum,pageSize);
		//存储到域中进行转发
		request.setAttribute("cid", cid);
		request.setAttribute("pageBean", pageBean);
		
		return "dispatcher:product_list";
	}
	
	
	public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProductService productService = new ProductServiceImpl();
		
		//准备商品的类别列表数据
		/*CategoryService catgtgoryService = new CategoryServiceImpl();
		List<Category> categoryList = catgtgoryService.findAll();
		request.setAttribute("categoryList", categoryList);*/
		
		//准备首页需要的热门数据和最新商品数据数据
		//热门
		List<Product> hotProductList = productService.findHotProductList();
		//最新
		List<Product> newProductList = productService.findNewProductList();
		//放到数据中转站 request域
		request.setAttribute("hotProductList", hotProductList);
		request.setAttribute("newProductList", newProductList);
		
		//转发index.jsp
		return "dispatcher:index";
		
	}
	
}