<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.entity.UserBean" %>
<%
UserBean user = (UserBean) request.getAttribute("user");
%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>マイページ</title>

<link rel="stylesheet" href="css/mypage_style.css">

<script src="js/script.js"></script>
</head>
<body>
	<!-- ヘッダーをインクルード -->
	<%@ include file="header.jsp"%>


	<div class="container">
		<div style="text-align: center;">
			<h1>マイページ</h1>
		</div>

		<form action="updateUser" method="post">
			<div class="form-row">
				<label for="name">お名前</label> 
				 <input type="text" name="name" value="<%= user != null ? user.getName() : ""%>" required>
			</div>

			<div class="form-row">
				<label for="nameKana">お名前（カナ）</label>
				 <input type="text" name="nameKana" value="<%= user != null ? user.getNameKana() : "" %>" required>
			</div>

			<div class="form-group">
				<label>性別</label>
				<div class="gender-group">
				 <input type="radio" name="gender" value="1" <%= user != null && user.getGender() == '1' ? "checked" : "" %>>
				<label for="male">男性</label> 
				  
				 <input type="radio" name="gender" value="2" <%= user != null && user.getGender() == '2' ? "checked" : "" %>>
				<label for="female">女性</label> 
				
				 <input type="radio" name="gender" value="0" <%= user != null && user.getGender() == '0' ? "checked" : "" %>>
				<label for="unknown">不明</label>
			    </div>
			</div>
	
	        <div class="form-row birth-date-group">
		        <label for="birthDay">生年月日</label>
		         <input type="date" name="birthDay" value="<%= user != null ? user.getBirthDay() : "" %>"required>
	        </div>
	        
	        <div class="form-row postal-code-group">
		        <label for="postalCode">郵便番号：</label>
		         <input type="text" id="postalCode" name="postalCode" value="<%= user != null ? user.getPostalCode() : "" %>" pattern="\d{3}-?\d{4}">
		          <button type="button" class="search-button" onclick="searchPostalCode()">検索</button>
	        </div>
	
	        <div class="form-row address-group">
		        <label for="address">住所：</label> 
		         <input type="text" id="address" name="address" value="<%= user != null ? user.getAddress() : "" %>" required>
	        </div>
	        
	        <div class="form-row">
		        <label for="phoneNumber">電話番号：</label>
		         <input type="text" id="phoneNumber" name="phoneNumber" value="<%= user != null ? user.getPhoneNumber() : "" %>" required pattern="\d{2,4}-?\d{2,4}-?\d{3,4}">
	        </div>
	
	        <div class="form-row">
		         <label for="email">メールアドレス：</label>
		          <input type="email" id="email" name="userId" value="<%= user != null ? user.getUserId() : "" %>" required placeholder="example@example.com">
	        </div>
	        
	        <div class="form-row">
		         <label for="password">パスワード：</label> 
		          <input type="password" name="password" value="<%= user != null ? user.getPassword() : "" %>" required>
	        </div>
	        
	        <div class="form-row update-button-container">
		         <button type="submit" class="update-button">更新</button>
	        </div>
	    </form>
	</div>

</body>
</html>