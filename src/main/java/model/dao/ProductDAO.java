package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.entity.ProductBean;

public class ProductDAO {

	//商品ページの一覧表示
	public List<ProductBean> getAllProducts() throws SQLException, ClassNotFoundException {
		List<ProductBean> productList = new ArrayList<ProductBean>();
		try (Connection con = ConnectionManager.getConnection();
				Statement stmt = con.createStatement();
				ResultSet res = stmt.executeQuery("""
						SELECT
						  product_code,
						  product_image,
						  product_name,
						  price,
						  product_desc,
						  stock
						FROM
						  m_product
						""")) {
			while (res.next()) {
				int code = res.getInt("product_code");
				String img = res.getString("product_image");
				String name = res.getString("product_name");
				int price = res.getInt("price");
				String desc = res.getString("product_desc");
				int stock = res.getInt("stock");

				ProductBean product = new ProductBean();
				product.setProductCode(code);
				product.setProductImage(img);
				product.setProductName(name);
				product.setPrice(price);
				product.setProductDesc(desc);
				product.setStock(stock);
				productList.add(product);
				

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return productList;
	}

	//検索
	public List<ProductBean> getSortProduct(String categoryName) throws SQLException, ClassNotFoundException {
		List<ProductBean> productList = new ArrayList<ProductBean>();
		String sql = """
				SELECT
					P.product_code,
					P.product_image,
				    P.product_name,
					P.price,
					P.product_desc,
					P.stock
				FROM
				  	m_product P
				JOIN
				  	m_category C ON P.category_id = C.category_id
				WHERE
					C.category_name = ?
				""";
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setString(1, categoryName);

			ResultSet res = pstmt.executeQuery();
			while (res.next()) {
				ProductBean product = new ProductBean();
				product.setProductCode(res.getInt("product_code"));
				product.setProductImage(res.getString("product_image"));
				product.setProductName(res.getString("product_name"));
				product.setPrice(res.getInt("price"));
				product.setProductDesc(res.getString("product_desc"));
				product.setStock(res.getInt("stock"));
				productList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return productList;

	}

	//在庫を減らす アップデート
	public int decrementStock(String productName, String userId) throws SQLException, ClassNotFoundException {
		int count = 0;
		String sql = """
				UPDATE
					m_product P
				JOIN
					m_cart C ON P.product_code = C.product_code
				SET
					P.stock = P.stock - C.quantity
				WHERE
					P.product_name = ?
				AND
					C.user_id = ?
				""";
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, productName);
			pstmt.setString(2, userId);
			count = pstmt.executeUpdate();
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
			return count;
		}

	}

}