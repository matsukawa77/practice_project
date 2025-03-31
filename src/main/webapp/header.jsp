<%@ page language="java" contentType="text/html; charset=UTF-8"
	session="true" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ヘッダー</title>
<link rel="stylesheet" href="css/header.css">
</head>

<body>

	<% 
		Object userId = session.getAttribute("userId");
		boolean Login = (userId != null); /* userがnullじゃない場合trueにする */
	%>
	<header class="header__background">
		<div class="imageBox">
			<a href="searchCategory"><img src="image/rogo.PNG" alt="ロゴ" class="header__image"></a>
		</div>
		<div class="header__button-before">
		
			<!-- ログイン前の表示 -->
			<% if (!Login) { %>
			<form action="login" method="get" class="header__form-login">
				<input type="submit" value="ログイン" class="header__form__button">
				<span class="arrow"></span>
			</form>
			<form action="registerUser" method="get" class="header__form-before">
				<input type="submit" value="会員登録" class="header__form__button">
				<span class="arrow"></span>
			</form>
			<% } %>

			<!-- ログイン後 -->
			<% if (Login) { %>
			<nav class="menu__content__pc">
				<ul class="list__login-after">
					<form action="myPage" method="get" class="header__form-login">
						<input type="submit" value="マイページ" class="header__form__button">
						<span class="arrow"></span>
					</form>
					<form action="cart" method="get" class="header__form-before">
						<input type="submit" value=" カート" class="header__form__button">
						<span class="arrow"></span>
					</form>
					<form action="logout" method="get" class="header__form-before">
						<input type="submit" value="ログアウト" class="header__form__button">
						<span class="arrow"></span>
					</form>
				</ul>
			</nav>
			<!-- ハンバーガーメニュー -->
			<div class="hamburgerMenu">
				<input type="checkbox" id="drawer_input" class="drawer__hidden">
				<label for="drawer_input" class="drawer__open"><span></span></label>
				<nav class="menu__content__sp">
					<ul class="list__login-after">
						<form action="myPage" method="get" class="header__form-login">
							<input type="submit" value="マイページ" class="header__form__button">
							<span class="arrow"></span>
						</form>
						<form action="cart" method="get" class="header__form-after">
							<input type="submit" value="カート" class="header__form__button">
							<span class="arrow"></span>
						</form>
						<form action="logout" method="get" class="header__form-after">
							<input type="submit" value="ログアウト" class="header__form__button">
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