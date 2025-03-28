package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.CartDAO;
import model.entity.CartBean;

/**
 * Servlet implementation class Purchase
 */
@WebServlet("/Purchase")
public class PurchaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userId = (String) request.getSession().getAttribute("userId");

        if (userId == null || userId.isEmpty()) {
            request.setAttribute("errorMessage", "ログイン状態を確認してください。");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        try {
            CartDAO cartDAO = new CartDAO();
            List<CartBean> cartList = cartDAO.getAllCartItem();

            if (cartList == null || cartList.isEmpty()) {
                request.setAttribute("errorMessage", "カートが空です。");
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
            }

            // マジで自信ない
            // 合計金額を計算
            int totalAmount = 0;
            for (CartBean cartItem : cartList) {
                totalAmount += cartItem.getSumPrice();
            }

            // 購入処理
            System.out.println("ユーザーID: " + userId);
            System.out.println("合計金額: " + totalAmount + "円");

            // 購入完了ページにリダイレクト
            response.sendRedirect("complete.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "購入処理中にエラーが発生しました。");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
