<%@ page language="java" contentType="text/html; charset=UTF-8"
	session="true" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ヘッダー</title>
<link rel="stylesheet" type="text/css" href="css/header.css">
</head>
<body>
	<%-- <% Object user = session.getAttribute("user");
	boolean Login = (user != null); /* userがnullじゃない場合trueにする */
	%> --%>
	<header class="background">
		<img src="" alt="ロゴ" class="header_img">
		<div class="header_button">
			<!-- ログイン前の表示 -->
			<%-- <% if (!Login) { %>  --%>
			<form action="login" method="get" class="header_login">
				<input type="submit" value="ログイン">
			</form>
			<form action="registerUser" method="get" class="header_register">
				<input type="submit" value="会員登録">
			</form>
			<%-- <% } %> --%>
			
			<!-- ログイン後 -->
			<!-- ハンバーガーメニュー -->
			<%-- <% if (Login) { %> --%>
			<div class="hamburger_menu">
				<input type="checkbox" id="drawer_input" class="drawer_hidden">
				<label for="drawer_input" class="drawer_open"><span></span></label>
				<nav class="menu_content">
					<ul class="nav_list">
						<form action="myPage" method="get">
							<input type="submit" value="マイページ">
						</form>
						<form action="cart" method="get" class="header_cart">
							<input type="submit" value="カート">
						</form>
						<form action="logout" method="get">
							<input type="submit" value="ログアウト">
						</form>
					</ul>
				</nav>
			</div>
			<%-- <% } %> --%>
		</div>

	</header>



</body>
</html>