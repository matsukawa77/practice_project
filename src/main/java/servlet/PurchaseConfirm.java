package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.CartDAO;
import model.dao.UserDAO;
import model.entity.CartBean;
import model.entity.UserBean;

/**
 * Servlet implementation class PurchaseConfirm
 */
@WebServlet("/PurchaseConfirm")
public class PurchaseConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PurchaseConfirm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

    	// セッションスコープからユーザーIDを取得する
    	String userId = (String) request.getSession().getAttribute("userId");

    	if(userId == null || userId.isEmpty()){
    	    request.setAttribute("errorMessage", "ユーザーIDが取得できませんでした。ログイン状態を確認してください。");
    	    RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
    	    dispatcher.forward(request, response);
    	    return;
    	}	

		System.out.println("編集するユーザーID(GET 確認用)"+userId);
		
		try {
			// タスク情報をデータベースから取得
			UserDAO userDAO = new UserDAO();
			UserBean user = userDAO.getUser(userId);
			
	        if (user == null) {
	            request.setAttribute("errorMessage", "ユーザーが存在しません。");
	            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
	            dispatcher.forward(request, response);
	            return;
	        }

			request.setAttribute("user", user);

			//  購入商品の取得
			CartDAO cartDAO = new CartDAO();
			List<CartBean> cartList = cartDAO.selectAllCartItem();
			request.setAttribute("cartList", cartList);

			// 編集ページへフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("confirmPurchase.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			request.setAttribute("errorMessage", "購入エラー(GET2)");
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		
		// セッションスコープからユーザーIDを取得する
    	String userId = (String) request.getSession().getAttribute("userId");

    	if(userId == null || userId.isEmpty()){
    	    request.setAttribute("errorMessage", "ユーザーIDが取得できませんでした。ログイン状態を確認してください。");
    	    RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
    	    dispatcher.forward(request, response);
    	    return;
    	}
		
		System.out.println("更新するユーザーID(POST)1 " + request.getParameter("userId"));
		
		// フォームからのパラメータ取得
		String userName = request.getParameter("userName");
		String userNameKana = request.getParameter("userNameKana");
		String postalCode = request.getParameter("postalCode");
		String address = request.getParameter("address");
		
		System.out.println("更新するユーザーID(POST)2 " + userId);
		
		
		String errorMessage = null;
		if (userName == null || userName.isEmpty()) {
			errorMessage = "名前を入力してください。";
		} else if (userNameKana == null || userNameKana.isEmpty()) {
			errorMessage = "ふりがなを入力してください。";
		} else if (postalCode == null || postalCode.isEmpty()) {
			errorMessage = "郵便番号を入力してください。";
		} else if (address == null || address.isEmpty()) {
			errorMessage = "住所を入力してください。";
		}
		
		if (errorMessage != null) {
			request.setAttribute("errorMessage", errorMessage);
			// 再取得
			try {
				UserDAO userDAO = new UserDAO();
				List<UserBean> userList = userDAO.getUserBean;
				request.setAttribute("userList", userList);

				CartDAO cartDAO = new CartDAO();
				List<CartBean> cartList = cartDAO.selectAllCart();
				request.setAttribute("cartList", cartList);
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "更新に失敗しました(POST1)");
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("confirmPurchase.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		try {
			// UserBeanの作成
			UserBean user = new UserBean();
			
			String userIdStr = request.getParameter("userId");

			user.setUserName(userName);
			user.setNameKana(userNameKana);
			user.setPostalCode(postalCode);
			user.setAdress(address);
			
			// 購入情報の登録
			UserDAO userDAO = new UserDAO();
			userDAO.updateUser(user);

			System.out.println("更新するユーザーID" + userId);// 登録完了後、完了ページに遷移
			response.sendRedirect("complete.jsp");
		
		} catch (SQLException | ClassNotFoundException e) {
			request.setAttribute("errorMessage", "更新に失敗しました(POST2)");
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);		}
	}
}
