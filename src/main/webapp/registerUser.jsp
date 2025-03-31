<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規会員登録</title>

<link rel="styleshet" herf="css/mypage_style.css">

<script src="js/script.js"></script>
</head>
<body>
	<!-- ヘッダーをインクルード -->
	<%@ include file="header.jsp"%>


	<div class="container">
		<div style="text-align: center;">
			<h1>新規会員登録</h1>
		</div>

		<form action="updateUser" method="post">
			<div class="form-row">
				<label for="name">お名前</label> 
				 <input type="text" name="name" required>
			</div>

			<div class="form-row">
				<label for="nameKana">お名前（カナ）</label>
				 <input type="text" name="nameKana" required>
			</div>

			<div class="form-group">
				<label>性別</label>
				<div class="gender-group">
				 <input type="radio" name="gender" value= 1 required>
				<label for="male">男性</label> 
				  
				 <input type="radio" name="gender" value= 2 >
				<label for="female">女性</label> 
				
				 <input type="radio" name="gender" value= 0 >
				<label for="unknown">不明</label>
			    </div>
			</div>
	
	        <div class="form-row birth-date-group">
		        <label for="birthDay">生年月日</label>
		         <input type="date" name="birthDay" required>
	        </div>
	        
	        <div class="form-row postal-code-group">
		        <label for="postalCode">郵便番号：</label>
		         <input type="text" id="postalCode" name="postalCode" pattern="\\d{3}-?\\d{4}">
		          <button type="button" class="search-button" onclick="searchPostalCode()">検索</button>
	        </div>
	
	        <div class="form-row address-group">
		        <label for="address">住所：</label> 
		         <input type="text" id="address" name="address" required>
	        </div>
	        
	        <div class="form-row">
		        <label for="phoneNumber">電話番号：</label>
		         <input type="text" id="phoneNumber" name="phoneNumber" required pattern="\\d{2,4}-?\\d{2,4}-?\\d{3,4}">
	        </div>
	
	        <div class="form-row">
		         <label for="email">メールアドレス：</label>
		          <input type="email" id="email" name="email" required placeholder="example@example.com">
	        </div>
	        
	        <div class="form-row">
		         <label for="password">パスワード：</label> 
		          <input type="password" name="password" required>
	        </div>
	        
	        <div class="form-row update-button-container">
		         <button type="submit" class="update-button">登録</button>
	        </div>
	    </form>
	</div>

</body>
</html>