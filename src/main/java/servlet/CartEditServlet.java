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
import model.entity.CartBean;

/**
 * Servlet implementation class CartEditServlet
 */
@WebServlet("/editCart")
public class CartEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartEditServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		String quantityStr = request.getParameter("quantity");

		// 遷移先のパス
		String path = "";
		// cart情報をセットし、DBの情報を更新
		try {
			int productCode = Integer.parseInt(productCodeStr);
			int quantity = Integer.parseInt(quantityStr);

			CartBean updateItem = new CartBean();
			updateItem.setUserId(userId);
			updateItem.setProductCode(productCode);
			updateItem.setQuantity(quantity);

			CartDAO cartDAO = new CartDAO();
			int result = cartDAO.updateCartItem(updateItem);
			System.out.println("カート情報が" + result + "件アップデートされました。");
			path = "cart";
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "カートの商品の削除に失敗しました。");
			path = "error.jsp";
		}
		response.sendRedirect(path);
	}

}
