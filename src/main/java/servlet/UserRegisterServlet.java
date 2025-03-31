package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.UserDAO;
import model.entity.UserBean;

/**
 * Servlet implementation class UserRegisterServlet
 */
@WebServlet("/registerUser")
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserRegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ユーザー登録画面を表示する処理
		request.getRequestDispatcher("registerUser.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String nameKana = request.getParameter("nameKana");
		String postalCode = request.getParameter("postalCode");
		String address = request.getParameter("address");
		Date birthDay = Date.valueOf(request.getParameter("birthDay"));
		int gender =  Integer.parseInt(request.getParameter("gender"));
		String phoneNumber = request.getParameter("phoneNumber");
		String role = request.getParameter("role");

		UserBean user = new UserBean();

		user.setUserId(userId);
		user.setPassword(password);
		user.setName(name);
		user.setNameKana(nameKana);
		user.setPostalCode(postalCode);
		user.setAddress(address);
		user.setBirthDay(birthDay);
		user.setGender(gender);
		user.setPhoneNumber(phoneNumber);
		user.setRole(role);

		UserDAO userDao = new UserDAO();

		int result = 0;
		try {
			result = userDao.registerUser(user);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		if (result > 0) {

			response.sendRedirect("login.jsp");

		} else {

			response.sendRedirect("error.jsp");

		}

	}

}