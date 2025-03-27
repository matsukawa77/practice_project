<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- CSSファイルはこの辺に入れる
<link rel="stylesheet" href="ファイルの名前.css">
 -->
</head>
<body>

<%@ include file="header.jsp"%>

<h1>購入者情報確認</h1>

<!-- カートからこのページに遷移
	情報はユーザーIDを元に入れる -->

	<form action="PurchaseServlet" method="POST">
	
		<!-- こそこそ情報もらうやつ -->
		
		<input type="hidden" name="userId" value="<%=user.getUserId()%>">
		
		
		<label for="userName">お名前：</label>
		<input type="text" size="15" id="userName" name="userName" value="<%=user.getUserName()%>"required>
		
		<br>
		
		<label for="userNameKana">お名前(カナ)：</label>
		<input type="text" size="15" id="userNameKana" name="userNameKana" value="<%=user.getUserNameKana()%>"required>
		
		<br> 
		
		<label for="postalCode">郵便番号：</label>
		<input type="text" size="15" id="postalCode" name="postalCode" value="<%=user.getPostalCode()%>" maxlength="8" placeholder="ハイフン有り"required>
		
		<br>
		
		<label for="address">住所：</label>
		<input type="text" id="address" name="address" value="<%=user.getAddress()%>" required>
	


<h2>お支払方法</h2>

<br>

	<input type="radio" id="daibiki" name="payment" value="daibiki" checked />
	<label for="daibiki">代引き(現金)</label>

<h2>到着予定</h2>

<br>

	<p>3営業日以内に出荷</p>

	<!-- 更新ボタン -->
		<input type="submit" class="btn-border" value="更新">
					
</form>
</body>
</html>