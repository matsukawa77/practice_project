package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.CategoryBean;

public class CategoryDAO {

	public List<CategoryBean> getAllCategories() throws SQLException, ClassNotFoundException {
		List<CategoryBean> categoryList = new ArrayList<>();
		String sql = "SELECT category_id, category_name FROM m_category";
		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				CategoryBean category = new CategoryBean();
				category.setCategoryId(resultSet.getInt("category_id"));
				category.setCategoryName(resultSet.getString("category_name"));
				categoryList.add(category);
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return categoryList;
	}
}
