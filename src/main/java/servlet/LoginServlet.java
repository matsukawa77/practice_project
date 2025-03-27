package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.UserDAO;
import model.entity.UserBean;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
		// requestパラメータ取得
		String id = request.getParameter("userId");
		String pass = request.getParameter("password");
		// インスタンス化
		UserDAO dao = new UserDAO();
		// 遷移先パス初期化
		String path = "";

		// user情報の取得、ログイン認証
		try {
			UserBean userInfo = dao.getUser(id);
			if (dao.loginAuth(userInfo, pass)) {
				// userId, role, auth情報をsessionに格納
				String role = userInfo.getRole();
				HttpSession session = request.getSession();
				session.setAttribute("userId", id);
				session.setAttribute("role", role);
				path = "index.jsp";
			} else {
				request.setAttribute("msg", "ユーザーIDまたはパスワードが違います");
				path = "login.jsp";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("msg", "ログイン時にエラーが発生しました");
			path = "login.jsp";
		}
		request.getRequestDispatcher(path).forward(request, response);
	}
}
