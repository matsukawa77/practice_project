package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.CartDAO;
import model.entity.CartBean;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// requestのエンコーディング方式を指定
		request.setCharacterEncoding("UTF-8");
		// requestパラメータ取得

		try {
			HttpSession session = request.getSession();
			String userId = (String) session.getAttribute("userId");
			CartDAO cartDAO = new CartDAO();
			List<CartBean> cartItemList;

			cartItemList = cartDAO.getAllCartItem(userId);

			if (cartItemList != null) {
				request.setAttribute("cartItemList", cartItemList);
				request.getRequestDispatcher("cart.jsp").forward(request, response);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			response.sendRedirect("searchCategory");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// requestのエンコーディング方式を指定
		request.setCharacterEncoding("UTF-8");

		try {
			// sessionスコープ、requestパラメータ値の取得
			HttpSession session = request.getSession();
			String userId = (String) session.getAttribute("userId");
			int productCode = Integer.parseInt(request.getParameter("productCode"));

			// カートに商品を追加
			CartDAO cartDAO = new CartDAO();
			int result = cartDAO.addCartItem(userId, productCode);
			System.out.println("商品が" + result + "件追加されました。");
			response.sendRedirect("searchCategory");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			request.setAttribute("errorMassage", "商品の追加に失敗しました。");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}
