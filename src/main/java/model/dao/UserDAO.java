package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import model.entity.UserBean;

public class UserDAO {
	public UserBean getUser(String userId) throws SQLException, ClassNotFoundException {
		// return用変数
		UserBean user = new UserBean();
		// SQL文
		String sql = """
				SELECT
					mumber_num, user_id, password, name, name_kana, postal_code,
					address, birth_day, gender, phone_number, role
				FROM
					m_user
				WHERE
					user_id = ?
				""";
		// DBへの接続、PreparedStatementの取得
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			// プレースホルダへの値の設定、SQL実行
			pstmt.setString(1, userId);
			ResultSet res = pstmt.executeQuery();
			// userIdが一致した場合
			if (res.next()) {
				// return用変数にDBから取得した値をセット
				user.setUserId(res.getString("user_id"));
				user.setPassword(res.getString("password"));
				user.setName(res.getString("name"));
				user.setNameKana(res.getString("name_kana"));
				user.setPostalCode(res.getString("postal_code"));
				user.setAddress(res.getString("address"));
				user.setBirthDay(res.getDate("birth_day"));
				user.setGender(res.getInt("gender"));
				user.setPhoneNumber(res.getString("phone_number"));
				user.setRole(res.getString("role"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return user;
	}

	public boolean loginAuth(UserBean user, String password) {
		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
		// 入力されたパスワードとDBのパスワード(ハッシュ化済み)を比較
		if (bcpe.matches(password, user.getPassword())) {
			return true;
		}
		return false;
	}

	public int registerUser(UserBean user) throws SQLException, ClassNotFoundException {
		// return用変数
		int result = 0;
		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
		// ハッシュ化
		String encodedPassword = bcpe.encode(user.getPassword());
		user.setPassword(encodedPassword);
		String sql = """
				INSERT INTO m_user
					(user_id, password, name, name_kana, postal_code,
					address, birth_day, gender, phone_number)
				VALUES
					(?, ?, ?, ?, ?, ?, ?, ?, ?);
				""";
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			// プレースホルダへの値の設定
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getNameKana());
			pstmt.setString(5, user.getPostalCode());
			pstmt.setString(6, user.getAddress());
			pstmt.setDate(7, (Date) user.getBirthDay());
			pstmt.setInt(8, user.getGender());
			pstmt.setString(9, user.getPhoneNumber());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateUser(UserBean user) throws SQLException, ClassNotFoundException {
		// return用変数
		int result = 0;
		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
		// ハッシュ化
		String encodedPassword = bcpe.encode(user.getPassword());
		user.setPassword(encodedPassword);
		// SQL
		String sql = """
				UPDATE
					m_user
				SET
					user_id = ?, password = ?, name = ?, name_kana = ?, postal_code = ?,
					adress = ?, birth_day = ?, gender = ?, phone_number = ?)
				WHERE
					member_num = ?;
				""";
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			// プレースホルダへの値の設定
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getNameKana());
			pstmt.setString(5, user.getPostalCode());
			pstmt.setString(6, user.getAddress());
			pstmt.setDate(7, (Date) user.getBirthDay());
			pstmt.setInt(8, user.getGender());
			pstmt.setString(9, user.getPhoneNumber());
			pstmt.setInt(10, user.getMemberNum());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}