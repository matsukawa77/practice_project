<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.List,model.entity.UserBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購入確認</title>
<link rel="stylesheet" href="css/confirmPurchase.css">
</head>
<body>

<%@ include file="header.jsp"%>

<h1>購入者情報確認</h1>

	<%
	UserBean user= (UserBean)request.getAttribute("user");
	%>

<!-- カートからこのページに遷移
	情報はユーザーIDを元に入れる -->

	<form action="PurchaseServlet" method="POST">
	
		<!-- こそこそ情報もらうやつ -->
		
		<input type="hidden" name="userId" value="<%=user.getUserId()%>">
		

		<p>お名前： <%=user.getName()%>"</p>
		
		
		<p>お名前(カナ)：<%=user.getNameKana()%>"</p>
		
		
		<p>郵便番号：<%=user.getPostalCode()%>"</p>
		
		
		<p>住所：<%=user.getAddress()%>"</p>
		

<h2>お支払方法</h2>

	<input type="radio" id="daibiki" name="payment" value="daibiki" checked />
	<label for="daibiki">代引き(現金)</label>

<h2>到着予定</h2>

	<p>3営業日以内に出荷</p>

	<!-- 更新ボタン -->
		<input type="submit" class="btn-border" value="購入確定">
					
</form>
</body>
</html>