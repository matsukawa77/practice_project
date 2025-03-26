<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.List,model.entity.ProductBean"%>
<%
    response.sendRedirect("searchCategory"); // 商品一覧ページにリダイレクト
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品ページ</title>
<link rel="stylesheet" type="text/css" href="css/index.css">
</head>
<body>
<% List<ProductBean> productList = (List<ProductBean>) request.getAttribute("product"); %>
			
<div class="background">
	<div class="search_box">
		<form action="searchCategory" method="post">
			<select name="" class="select_box">
				<option value=""></option>
			</select>
			<input type="submit" value="検索" class="search_button">
		</form>
	</div>
	<% for (ProductBean product : productList) { %>
	<div class="product_box">
		<form action="cart" method="post" class="product_form">
			<div class="image_box">
				<img src="image/aomusi.png" alt="商品画像" class="product_img">
			</div>
			<div class="title_box">
				<p class="product_name"><%= product.getProductName %></p>
				<p class="price">1,000円</p>
			</div>
			<div class="description_box">
				<p class="product_description">商品説明サンプル商品説明サンプル商品説明サンプル商品説明サンプル商品説明サンプル</p>
			</div>
			<div class="button_box">
				<input type="submit" value="カート" class="cart_button">
			</div>
		</form>
	</div>
	<% } %>
	
</div>
</body>
</html>