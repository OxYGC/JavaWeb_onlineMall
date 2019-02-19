package yanggc.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yanggc.base.BaseServlet;
import yanggc.domain.Product;
import yanggc.serviceImp.ProductServiceImp;
import yanggc.utils.PageModel;

public class ProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	public String findProductByPid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pid = request.getParameter("pid");
		ProductServiceImp psi = new ProductServiceImp();
		Product product = psi.findProductByPid(pid);
		request.setAttribute("pro", product);
		return "/jsp/product_info.jsp";
	}
	public String findProductsWithPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*String cid, int startIndex, int pageSize*/
		//取出分页的参数
		String cid = request.getParameter("cid");
		int currnetPage = Integer.parseInt( request.getParameter("num"));
		ProductServiceImp psi = new ProductServiceImp();
		PageModel pagePros = psi.findProductsWithPage(cid, currnetPage);
		request.setAttribute("page", pagePros);
		//开始转发...
		return "/jsp/product_list.jsp";
	}
}