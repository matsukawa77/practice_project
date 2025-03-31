

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List,model.entity.ProductBean, model.entity.CategoryBean"%>
<%-- <%
/* session.invalidate(); */
/* session = request.getSession(true);
String access = (String) session.getAttribute("Session");
if (access == null) {
	RequestDispatcher dispatcher = request.getRequestDispatcher("/searchCategory");
	dispatcher.forward(request, response);// 商品一覧ページにリダイレクト
} */
%> --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品ページ</title>
<link rel="stylesheet" type="text/css" href="css/index.css">
</head>
<style>
.banner_box {
	width: auto;
	height: 250px;
}

.banner_img {
	width: 100%;
	height: 100%;
	object-fit: cover;
}

.index_background {
	background-image: url("image/sakura.png");
	background-repeat: no-repeat;
	background-size: contain;
	width: auto;
	height: 100%;
	background-color: #DBE4EE;
	padding: 20px 20px;
	text-align: center;
}

.search_text {
	font-size: 1.3rem;
	font-weight: bold;
	margin: auto;
	margin-left: 0px;
	margin-right: 20px;
}

.search_box {
	margin-bottom: 10px;
	text-align: left;
	display: flex;
}

.search_box2 {
	margin: auto 0px;
	height: 40px;
	border: solid 1px #333;
	border-radius: 8px;
	-webkit-box-shadow: 0 1px 3px rgba(0, 0, 0, .3);
}

.select_box {
	width: 250px;
	height: 40px;
	border-top-left-radius: 8px;
	border-bottom-left-radius: 8px;
	border: none;
}

.search_button {
	width: 41px;
	height: 41px;
	background-image: url("image/search_icon.png");
	background-repeat: no-repeat;
	background-size: contain;
	background-color: #f17300;
	padding: auto;
	margin: auto;
	border: none;
	border-top-right-radius: 7px;
	border-bottom-right-radius: 7px;
	cursor: pointer;
}

.clear_button {
	margin: auto;
	margin-left: 10px;
	text-decoration: none;
}

.clear_button_input {
	width: 60px;
	height: 40px;
	line-height: 40px;
	text-align: center;
	color: #333333;
	background-color: #f2f2f2;
	border-radius: 8px;
	border: solid 1px #333333;
	font-size: 0.8rem;
	-webkit-box-shadow: 0 1px 3px rgba(0, 0, 0, .3);
}

.product_box {
	background-color: #ffffff;
	width: 220px;
	height: 360px;
	padding: 10px;
	margin: 10px;
	display: inline-block;
	border-radius: 8px;
	-webkit-box-shadow: 0 1px 3px rgba(0, 0, 0, .3);
}

.image_box {
	background-color: #F1F5F9;
	margin: auto;
	width: 190px;
	height: 150px;
	border-radius: 8px;
}

.product_description {
	margin: 0px;
	text-align: left;
	font-size: 0.9rem;
}

.product_img {
	margin: auto;
	width: 100%;
	height: 100%;
	object-fit: contain;
}

.product_name {
	font-weight: bold;
	margin: 5px 0px;
	text-align: left;
}

.price {
	margin: 0px 0px 5px 0px;
	text-align: left;
	color: #ff0000;
}

.description_box {
	width: auto;
	height: 90px;
}

.cart_button {
	width: 41px;
	height: 41px;
	background-image: url("image/cart_icon.png");
	background-repeat: no-repeat;
	background-size: contain;
	background-color: #f17300;
	padding: auto;
	margin: auto;
	border: none;
	border-top-right-radius: 7px;
	border-bottom-right-radius: 7px;
	cursor: pointer;
}

.button_box {
	margin: 0px 0px 0px auto;
	border: solid 1px #333;
	width: 91px;
	border-radius: 8px;
	-webkit-box-shadow: 0 1px 3px rgba(0, 0, 0, .3);
}

.cart_box {
	width: 50px;
	height: 40px;
	border-top-left-radius: 8px;
	border-bottom-left-radius: 8px;
	border: none;
}
</style>
<body>
	<%
	List<ProductBean> productList = (List<ProductBean>) request.getAttribute("product");
	List<CategoryBean> categoryList = (List<CategoryBean>) request.getAttribute("category");
	String selectedCategoryName = (String) request.getAttribute("selectedCategoryName");
	%>
	<jsp:include page="header.jsp" flush="true" />

	<div class="banner_box">
		<img src="image/banner.jpg" alt="バナー" class="banner_img">
	</div>
	<div class="index_background">
		<div class="search_box">
			<div class="search_text">カテゴリーから探す</div>
			<div class="search_box2">
				<form action="searchCategory" method="post">
					<select name="search" class="select_box">
						<%
						for (CategoryBean category : categoryList) {
				    %>
						<option value="<%=category.getCategoryName()%>"
							<%= (category.getCategoryName().equals(selectedCategoryName)) ? "selected" : "" %>><%=category.getCategoryName()%></option>
						<%
						}
					%>
					</select><input type="submit" value="" class="search_button">
				</form>
			</div>
			<a href="searchCategory" class="clear_button">
				<div class="clear_button_input">クリア</div>
			</a>
		</div>
		<% if (selectedCategoryName == null) { %>
		<h1>新生活 注目のタイムセール 開催中！</h1>
		<% } else { %>
		<h1>
			カテゴリー：<%= selectedCategoryName %></h1>
		<% } %>
		<%
		for (ProductBean product : productList) {
			int stock = product.getStock();
		%>
		<div class="product_box">
			<form action="cart" method="post" class="product_form">
				<input type="hidden" name="productCode"
					value="<%=product.getProductCode()%>">
				<div class="image_box">
					<img src=<%=product.getProductImage()%> alt="商品画像"
						class="product_img">
				</div>
				<p class="product_name"><%=product.getProductName()%></p>
				<p class="price"><%=product.getPrice()%>円
				</p>
				<div class="description_box">
					<p class="product_description"><%=product.getProductDesc()%></p>
				</div>
				<div class="button_box">
					<select name="quantity" class="cart_box">
						<% for(int i = 1; i <= stock; i++) { %>
						<option value="<%= i %>"><%= i %></option>
						<% } %>

					</select><input type="submit" value="" class="cart_button">
				</div>
			</form>
		</div>
		

<% } %>

	</div>
</body>
</html>