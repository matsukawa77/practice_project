<%@ page language="java" contentType="text/html; charset=UTF-8"
	session="true" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ヘッダー</title>
<link rel="stylesheet" type="text/css" href="css/header.css">
</head>
<style>
form {
	position: relative; /* 矢印の親要素 */
}

.header_background {
	width: auto;
	height: 80px;
	display: flex;
	align-items: center;
	padding: 20px 50px;
	background-color: #021F3B;
	position: sticky;
	top: 0;
	z-index: 999;
}

.img_box {
	width: 150px;
	height: 100%;
}

.header_img {
	width: 100%;
	height: 100%;
	object-fit: contain;
}

.header_register_before {
	margin: 0px 0px 0px 30px;
	position: relative;
	display: inline-block;
}

.header_button_before {
	display: flex;
	margin: auto;
	margin-right: 0px;
	align-items: center;
}

.header_login {
	display: inline-block;
}

.login_register_button {
	width: 120px;
	padding: 0.5rem 3.5rem 0.5rem 2.5rem;
	font-weight: bold;
	background: #ffffff;
	color: #021F3B;
	border-radius: 100vh;
	border: none;
	cursor: pointer;
	transition: 0.5s;
}

.arrow {
	content: '';
	position: absolute !important;
	left: 10px;
	width: 7px;
	height: 7px;
	border-top: 2px solid #021F3B;
	border-right: 2px solid #021F3B;
	top: 50% !important;
	transform: translateY(-50%) rotate(45deg) !important;
}

.login_register_button:hover {
	background: #DBE4EE;
	color: #fff;
}

@media screen and (min-width:751px) {
	.hamburger_menu {
		display: none;
	}
	.header_register {
		margin: 0px 0px 0px 30px;
	}
	.header_button_before {
		display: flex;
	}
}

@media screen and (max-width:750px) {
	.nav_list {
		margin-top: 150px;
	}
	.login_register_button {
		position: relative;
		margin: 20px 0px;
	}
	/*　画面サイズが480px以下の場合ここの記述が適用される　*/
	.menu_content_pc {
		display: none;
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
	.drawer_open span::before {
		top: -8px; /* 上のバーの位置 */
	}
	.drawer_open span::after {
		top: 8px; /* 下のバーの位置 */
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
		background: #021F3B;
		transition: .5s;
	}

	/* アイコンがクリックされたらメニューを表示 */
	#drawer_input:checked ~ .menu_content {
		right: 0; /* マイページ・ログアウトメニューを画面に入れる */
	}

	/* アイコンがクリックされたら真ん中の線を透明にする */
	#drawer_input:checked ~ .drawer_open span {
		background: rgba(255, 255, 255, 0);
	}

	/* アイコンがクリックされたらアイコンが×印になように上下の線を回転 */
	#drawer_input:checked ~ .drawer_open span::before {
		bottom: 0;
		transform: rotate(45deg);
	}
	#drawer_input:checked ~ .drawer_open span::after {
		top: 0;
		transform: rotate(-45deg);
	}

	/* ハンバーガーメニューのアイコン */
	.drawer_open span {
		position: relative; /* ここを relative に */
		display: block;
		width: 25px;
		height: 3px;
		background: #ffffff;
		border-radius: 3px;
		transition: 0.5s;
	}
	.drawer_open span::before, .drawer_open span::after {
		content: '';
		position: absolute;
		width: 25px;
		height: 3px;
		background: #ffffff;
		border-radius: 3px;
		transition: 0.5s;
	}
}
</style>
<body>
	<% Object userId = session.getAttribute("userId");
	boolean Login = (userId != null); /* userがnullじゃない場合trueにする */
	%>
	<header class="header_background">
		<div class="img_box">
			<a href="searchCategory"> <img src="image/rogo.PNG" alt="ロゴ"
				class="header_img">
			</a>
		</div>
		<div class="header_button_before">
			<!-- ログイン前の表示 -->
			<% if (!Login) { %>
			<form action="login" method="get" class="header_login">
				<input type="submit" value="ログイン" class="login_register_button">
				<span class="arrow"></span>
			</form>
			<form action="registerUser" method="get"
				class="header_register_before">
				<input type="submit" value="会員登録" class="login_register_button">
				<span class="arrow"></span>
			</form>
			<% } %>

			<!-- ログイン後 -->
			<% if (Login) { %>
			<nav class="menu_content_pc">
				<ul class="nav_list">
					<form action="myPage" method="get" class="header_login">
						<input type="submit" value="マイページ" class="login_register_button">
						<span class="arrow"></span>
					</form>
					<form action="cart" method="get" class="header_register_before">
						<input type="submit" value=" カート" class="login_register_button">
						<span class="arrow"></span>
					</form>
					<form action="logout" method="get" class="header_register_before">
						<input type="submit" value="ログアウト" class="login_register_button">
						<span class="arrow"></span>
					</form>
				</ul>
			</nav>
			<!-- ハンバーガーメニュー -->
			<div class="hamburger_menu">
				<input type="checkbox" id="drawer_input" class="drawer_hidden">
				<label for="drawer_input" class="drawer_open"><span></span></label>
				<nav class="menu_content">
					<ul class="nav_list">
						<form action="myPage" method="get" class="header_login">
							<input type="submit" value="マイページ" class="login_register_button">
							<span class="arrow"></span>
						</form>
						<form action="cart" method="get" class="header_register">
							<input type="submit" value="カート" class="login_register_button">
							<span class="arrow"></span>
						</form>
						<form action="logout" method="get" class="header_register">
							<input type="submit" value="ログアウト" class="login_register_button">
							<span class="arrow"></span>
						</form>
					</ul>
				</nav>

			</div>
			<% } %>
		</div>

	</header>



</body>
</html>