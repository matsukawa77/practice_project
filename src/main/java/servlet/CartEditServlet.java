package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.CartDAO;

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
		// requestのエンコーディング方式を指定
		request.setCharacterEncoding("UTF-8");
		// パラメータ取得
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String quantityStr = request.getParameter("quantity");

		try {
			int quantity = Integer.parseInt(quantityStr);
			// インスタンス化
			CartDAO cartDAO = new CartDAO();

			// 顧客情報の取得、全てのareaとuserを取得
			CartBean cartItem = (CartBean) cartDAO.getCustomer(customerId);
			List<AreaBean> areaList = areaDAO.getAllAreas();
			List<UserBean> userList = userDAO.getAllUser();

			// nullチェックし、リクエストスコープに値をセット
			if (areaList != null || userList != null || customer != null) {
				request.setAttribute("areaList", areaList);
				request.setAttribute("userList", userList);
				request.setAttribute("customer", customer);

				request.getRequestDispatcher("customerEdit.jsp").forward(request, response);
			} else {
				System.out.println("areList or userList or customerList is null");
				request.getRequestDispatcher("menu.jsp");
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			request.getRequestDispatcher("menu.jsp");
		}
	}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
