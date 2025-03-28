package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.CartDAO;

/**
 * Servlet implementation class CartDeleteServlet
 */
@WebServlet("/deleteCart")
public class CartDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartDeleteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// requestのエンコーディング方式を指定
		request.setCharacterEncoding("UTF-8");

		// パラメータ取得
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String productCodeStr = request.getParameter("productCode");
		// 遷移先のパス
		String path = "";

		try {
			int productCode = Integer.parseInt(productCodeStr);

			CartDAO dao = new CartDAO();
			// カート商品削除
			int result = dao.deleteCartItem(userId, productCode);
			System.out.println("カートの商品が" + result + "件削除されました。");

			request.setAttribute("msg", "削除");
			path = "/cart";
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "カートの商品の削除に失敗しました。");
			path = "error.jsp";
		}
		request.getRequestDispatcher(path).forward(request, response);
	}

}
