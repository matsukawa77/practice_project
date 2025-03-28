<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
<link rel="stylesheet" href="css/login.css">
</head>
<body>
	<%@ include file="header.jsp"%>
	
	<div>
		<h1>ログイン画面</h1>
	</div>
	
	<div class="form__container">
	
	<form action="login" method="post">
		<label>ユーザーID：</label>
		<input id="login__id" type="text" name="userId" required><br>

		<label>パスワード：</label>
		<input id="login__pass" type="password" name="password" required><br><br>

		<input type="submit" value="ログイン">
		<input type="reset" value="クリア">
    </form>
    
    <form action="registerUser" method="post">
        <input type="submit" value="新規会員登録">
    </form>
    
		<% String msg = (String) request.getAttribute("msg");
			if( msg != null) {
		%>
			<p class="msg"><%=msg %></p>
		<%
			}
		%>
</body>
</html>