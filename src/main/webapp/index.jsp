<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List,model.entity.ProductBean, model.entity.CategoryBean"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品ページ</title>
<link rel="stylesheet" href="css/index.css">
</head>
<body>
	<%
		List<ProductBean> productList = (List<ProductBean>) request.getAttribute("product");
		List<CategoryBean> categoryList = (List<CategoryBean>) request.getAttribute("category");
		String selectedCategoryName = (String) request.getAttribute("selectedCategoryName");
	%>
	<%@ include file="header.jsp"%>
	<div class="banner__box">
		<img src="image/banner.jpg" alt="バナー" class="banner__image">
	</div>
	<div class="index__background">
		<div class="search__box__main">
			<div class="search__text">カテゴリーから探す</div>
			<div class="search__box">
				<form action="searchCategory" method="post">
					<select name="search" class="select__box">
						<%
						for (CategoryBean category : categoryList) {
						%>
						<option value="<%=category.getCategoryName()%>" <%=(category.getCategoryName().equals(selectedCategoryName)) ? "selected" : ""%>><%=category.getCategoryName()%></option>
						<%
						}
						%>
					</select><input type="submit" value="" class="search__button">
				</form>
			</div>
			<a href="searchCategory" class="clear__button"><div class="clear__button-input">クリア</div></a>
		</div>
		<%
		if (selectedCategoryName == null) {
		%>
		<h1>新生活 注目のタイムセール 開催中！</h1>
		<%
		} else {
		%>
		<h1>カテゴリー：<%=selectedCategoryName%></h1>
		<%
		}
		%>
		<%
		for (ProductBean product : productList) {
			int stock = product.getStock();
		%>
		<div class="product__box">
			<form action="cart" method="post">
				<input type="hidden" name="productCode" value="<%= product.getProductCode() %>">
				<div class="image__box">
					<img src=<%= product.getProductImage() %> alt="商品画像" class="product__image">
				</div>
				<p class="product__name"><%= product.getProductName() %></p>
				<p class="price"><%= product.getPrice() %>円</p>
				<div class="description__box">
					<p class="product__description"><%= product.getProductDesc() %></p>
				</div>
				<div class="cart__button_box">
					<select name="quantity" class="cart__quantity">
						<%
						for (int i = 1; i <= stock; i++) {
						%>
						<option value="<%= i %>"><%= i %></option>
						<%
						}
						%>

					</select><input type="submit" value="" class="cart__button">
				</div>
			</form>
		</div>
		<%
		}
		%>
	</div>
</body>
</html>