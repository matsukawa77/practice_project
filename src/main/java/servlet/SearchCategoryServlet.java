package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.CategoryDAO;
import model.dao.ProductDAO;
import model.entity.CategoryBean;
import model.entity.ProductBean;

/**
 * Servlet implementation class SearchCategoryServlet
 */
@WebServlet("/searchCategory")
public class SearchCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchCategoryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		ProductDAO productdao = new ProductDAO();
		CategoryDAO categorydao = new CategoryDAO();

		List<ProductBean> product = null;
		List<CategoryBean> category = null;

		try {
			product = productdao.getAllProducts();
			category = categorydao.getAllCategories();
			request.setAttribute("product", product);
			request.setAttribute("category", category);
			request.getRequestDispatcher("index.jsp").forward(request, response);

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		CategoryDAO categorydao = new CategoryDAO();
		ProductDAO productdao = new ProductDAO();
		List<CategoryBean> category = null;

		try {
			String search = request.getParameter("search");

			// 検索する前に古いリストデータを削除し、HITしたリストのみを表示する
			List<ProductBean> productList = new ArrayList<ProductBean>();
			productList.clear();
			productList = productdao.getSortProduct(search);
			category = categorydao.getAllCategories();
			request.setAttribute("category", category);

			request.setAttribute("product", productList);
			request.setAttribute("selectedCategoryName", search);

			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}