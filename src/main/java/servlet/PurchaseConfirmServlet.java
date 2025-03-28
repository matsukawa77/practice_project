package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
public class PurchaseConfirmServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PurchaseConfirmServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String userId = (String) request.getSession().getAttribute("userId");

        if (userId == null || userId.isEmpty()) {
            request.setAttribute("errorMessage", "ユーザーIDが取得できませんでした。ログイン状態を確認してください。");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        try {
            UserDAO userDAO = new UserDAO();
            UserBean user = userDAO.getUser(userId);

            if (user == null) {
                request.setAttribute("errorMessage", "ユーザーが存在しません。");
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
            }

            request.setAttribute("user", user);

            CartDAO cartDAO = new CartDAO();
            List<CartBean> cartList = cartDAO.getAllCartItem();
            request.setAttribute("cartList", cartList);
            request.getRequestDispatcher("confirmPurchase.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "購入エラー(GET)");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String userId = (String) request.getSession().getAttribute("userId");

        if (userId == null || userId.isEmpty()) {
            request.setAttribute("errorMessage", "ユーザーIDが取得できませんでした。ログイン状態を確認してください。");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        // フォームからのパラメータ取得
        String userName = request.getParameter("userName");
        String userNameKana = request.getParameter("userNameKana");
        String postalCode = request.getParameter("postalCode");
        String address = request.getParameter("address");

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
            try {
                UserDAO userDAO = new UserDAO();
                UserBean user = userDAO.getUser(userId);
                request.setAttribute("user", user);

                CartDAO cartDAO = new CartDAO();
                List<CartBean> cartList = cartDAO.getAllCartItem();
                request.setAttribute("cartList", cartList);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "更新に失敗しました(POST1)");
            }
            request.getRequestDispatcher("confirmPurchase.jsp").forward(request, response);
            return;
        }

        try {
            UserBean user = new UserBean();
            // セッションから取得した userId を設定
            user.setUserId(userId);
            user.setName(userName);
            user.setNameKana(userNameKana);
            user.setPostalCode(postalCode);
            user.setAddress(address);

            UserDAO userDAO = new UserDAO();
            userDAO.updateUser(user);

            response.sendRedirect("complete.jsp");
        } catch (SQLException | ClassNotFoundException e) {
            request.setAttribute("errorMessage", "更新に失敗しました(POST2)");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
