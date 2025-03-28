<%@ page language="java" contentType="text/html; charset=UTF-8"
	session="true" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ヘッダー</title>
<link rel="stylesheet" type="text/css" href="css/header.css">
</head>
<style>
.header_background {
	width: auto;
	height: 120px;
	display: flex;
	align-items: center;
	padding: 20px 50px;
	background-color: none;
	position: sticky;
	top: 0;
	z-index: 999;
}

.header_img {
	width: auto;
}

.header_button {
	display: flex;
	margin: auto;
	margin-right: 0px;
	align-items: center;
}

.header_login {}

.header_register {
	margin: 0px 30px;
}

.drawer_hidden {
	display: none;
}

/* ハンバーガーアイコンの設置スペース */
.drawer_open {
	display: flex;
	height: 60px;
	width: 60px;
	justify-content: center;
	align-items: center;
	position: relative;
	z-index: 100;
	/* 重なり順を一番上にする */
	cursor: pointer;
}

/* ハンバーガーメニューのアイコン */
.drawer_open span,
.drawer_open span:before,
.drawer_open span:after {
	content: '';
	display: block;
	height: 3px;
	width: 25px;
	border-radius: 3px;
	background: #333;
	transition: 0.5s;
	position: absolute;
}

/* 三本線の一番上の棒の位置調整 */
.drawer_open span:before {
	bottom: 8px;
}

/* 三本線の一番下の棒の位置調整 */
.drawer_open span:after {
	top: 8px;
}

/* アイコンがクリックされたら真ん中の線を透明にする */
#drawer_input:checked~.drawer_open span {
	background: rgba(255, 255, 255, 0);
}

/* アイコンがクリックされたらアイコンが×印になように上下の線を回転 */
#drawer_input:checked~.drawer_open span::before {
	bottom: 0;
	transform: rotate(45deg);
}

#drawer_input:checked~.drawer_open span::after {
	top: 0;
	transform: rotate(-45deg);
}

/* マイページ・ログアウトメニューのデザイン*/
.menu_content {
	width: 300px;
	height: 100%;
	position: fixed;
	top: 0;
	right: -300px;
	/* マイページ・ログアウトメニューを画面の外に飛ばす */
	z-index: 99;
	background: #888888;
	transition: .5s;
}

/* アイコンがクリックされたらメニューを表示 */
#drawer_input:checked~.menu_content {
	right: 0;  /* マイページ・ログアウトメニューを画面に入れる */
}
</style>
<body>
	<% Object user = session.getAttribute("user");
	boolean Login = (user != null); /* userがnullじゃない場合trueにする */
	%>
	<header class="header_background">
		<img src="" alt="ロゴ" class="header_img">
		<div class="header_button">
			<!-- ログイン前の表示 -->
			<% if (!Login) { %> 
			<form action="login" method="get" class="header_login">
				<input type="submit" value="ログイン">
			</form>
			<form action="registerUser" method="get" class="header_register">
				<input type="submit" value="会員登録">
			</form>
			<% } %>
			
			<!-- ログイン後 -->
			<!-- ハンバーガーメニュー -->
			 <% if (Login) { %>
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
			<% } %>
		</div>

	</header>



</body>
</html>