package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	/**
	* データベースのURL
	*/
	private final static String URL = "jdbc:mysql://localhost:3306/ysky_db";
	/**
	* データベースのユーザー名
	*/
	private final static String USER = "ysky_user";
	/**
	* データベースのパスワード
	*/
	private final static String PASSWORD = "ysky_pass";

	/**
	* データベースへの接続を取得して返します。
	*
	* @return データベース接続
	* @throws SQLException
	* * @throws ClassNotFoundException
	*/
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		// JDBCドライバのロード
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}
