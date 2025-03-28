package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.entity.CartBean;

public class CartDAO {
	public List<CartBean> getAllCartItem() throws SQLException, ClassNotFoundException {
		// return用変数
		List<CartBean> cartItemList = new ArrayList<CartBean>();
		// SQL
		String sql = """
				SELECT
					t1.user_id,
					t1.product_code,
					t2.product_name,
					t1.quantity,
					t2.price
				FROM m_cart as t1
					JOIN m_product as t2
					ON t1.product_code = t2.product_code
				""";
		// データベースへの接続を取得、Statementの取得
		try (Connection con = ConnectionManager.getConnection();
				Statement stmt = con.createStatement();
				ResultSet res = stmt.executeQuery(sql)) {
			// responseをsetterでcartItemListへ格納
			while (res.next()) {
				CartBean cartItem = new CartBean();
				cartItem.setUserId(res.getString("user_id"));
				cartItem.setProductCode(res.getInt("product_code"));
				cartItem.setProductName(res.getString("product_name"));
				cartItem.setQuantity(res.getInt("quantity"));
				cartItem.setPrice(res.getInt("price"));
				cartItem.setSumPrice(res.getInt("quantity") * res.getInt("price"));
				cartItemList.add(cartItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cartItemList;
	}

	public int addCartItem(String userId, int productCode) throws SQLException, ClassNotFoundException {
		// return用変数
		int result = 0;
		// SQL
		String selectSql = "SELECT * FROM m_cart WHERE user_id = ? AND product_code = ?";
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(selectSql)) {
			pstmt.setString(1, userId);
			pstmt.setInt(2, productCode);
			try (ResultSet res = pstmt.executeQuery()) {
				if (res.next()) {
					// 商品が既にカートに存在する場合、quantityを1増加
					int currentQuantity = res.getInt("quantity");
					String updateSql = "UPDATE m_cart SET quantity = ? WHERE user_id = ? AND product_code = ?";
					try (PreparedStatement updateRes = con.prepareStatement(updateSql)) {
						updateRes.setInt(1, currentQuantity + 1);
						updateRes.setString(2, userId);
						updateRes.setInt(3, productCode);
						result = updateRes.executeUpdate();
					}
				} else {
					// 商品がカートに入っていない場合、レコードを新規追加
					String insertSql = "INSERT INTO m_cart (user_id, product_code, quantity) VALUES (?, ?, 1)";
					try (PreparedStatement insertRes = con.prepareStatement(insertSql)) {
						insertRes.setString(1, userId);
						insertRes.setInt(2, productCode);
						result = insertRes.executeUpdate();
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateCartItem(CartBean cart) throws SQLException, ClassNotFoundException {
		int result = 0;
		// SQL
		String sql = "UPDATE m_cart SET quantity = ? WHERE user_id = ? AND product_code = ?";
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			// プレースホルダへの値の設定
			pstmt.setInt(1, cart.getQuantity());
			pstmt.setString(2, cart.getUserId());
			pstmt.setInt(3, cart.getProductCode());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteCartItem(String userId, int productCode) throws ClassNotFoundException {
		int result = 0;
		// SQL
		String sql = "DELETE FROM m_cart WHERE user_id = ? AND product_code = ?";
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			// プレースホルダへの値の設定
			pstmt.setString(1, userId);
			pstmt.setInt(2, productCode);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteAllCartItem(String userId) throws SQLException, ClassNotFoundException {
		int result = 0;
		// SQL
		String sql = "DELETE FROM m_cart WHERE user_id = ?";
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			// プレースホルダへの値の設定
			pstmt.setString(1, userId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
