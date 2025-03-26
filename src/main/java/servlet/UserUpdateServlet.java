package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.UserDAO;
import model.entity.UserBean;

/**
 * Servlet implementation class UserUpdateServlet
 */
@WebServlet("/updateUser")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String nameKana = request.getParameter("nameKana");
        String postalCode = request.getParameter("postalCode");
        String address = request.getParameter("address");
        String birthDay = request.getParameter("birthDay");
        String gender = request.getParameter("gender");
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
        userDao.updateUser(user);

        response.sendRedirect("mypage.jsp");
    }
}
