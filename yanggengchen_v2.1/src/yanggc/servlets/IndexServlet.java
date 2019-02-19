package yanggc.servlets;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yanggc.base.BaseServlet;
import yanggc.domain.Product;
import yanggc.serviceImp.ProductServiceImp;

public class IndexServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductServiceImp psi = new ProductServiceImp();
		List<Product> find9News = psi.find9News();
		List<Product> find9Hots = psi.find9Hots();
		request.setAttribute("news", find9News);
		request.setAttribute("hots", find9Hots);
		return "/jsp/index.jsp";
	}

}