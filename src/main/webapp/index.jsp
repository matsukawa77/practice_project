

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List,model.entity.ProductBean, model.entity.CategoryBean"
%>
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
.index_background {
	width: 100%;
	height: 100%;
	background-color: #fcdccc;
	padding: 20px 50px;
	text-align: center;
}
.search_box {
	margin: 20px 0px;
	text-align: left;
	display: flex;
}
.select_box {
	width: 250px;
	height: 40px;
}
.search_button {
	width: 60px;
	height: 40px;
}
.clear_button {
	margin: auto;
	margin-left: 10px;
	
}
.clear_button_input {
	width: 60px;
	height: 40px;
	text-align: center;
	
	
}
.product_box {
	background-color: #a6fff6;
	width: 320px;
	height: 450px;
	padding: 30px;
	margin: 30px;
	display: inline-block;
}
.image_box {
	margin: auto;
	border: solid 1px;
	width: 300px;
	height: 200px;
	
}
.product_description {
	margin: 0px;
	text-align: left;
	
}
.product_img {
	margin: auto;
	width: 100%;
	height: 100%;
	object-fit: cover;
	
}
.product_name {
	font-size: 2rem;
	margin: 10px 0px;
	text-align: left;
}
.price {
	font-size: 1.5rem;
	margin: 0px 0px 10px 0px;
	text-align: left;
	color: #ff0000;
}
.description_box {
	width: auto;
	height: 100px;
}
.cart_button {
	width: 200px;
	height: 40px;
}
.button_box {
	text-align: right;
}
</style>
<body>
	<%
	List<ProductBean> productList = (List<ProductBean>) request.getAttribute("product");
	List<CategoryBean> categoryList = (List<CategoryBean>) request.getAttribute("category");
	String selectedCategoryName = (String) session.getAttribute("selectedCategoryName"); 
	%>
	<jsp:include page="header.jsp" flush="true" />
	<div class="index_background">
	
		<div class="search_box">
			<form action="searchCategory" method="post">
				<select name="search" class="select_box">
				<%
					for (CategoryBean category : categoryList) {
				%>
					<option  value="<%=category.getCategoryName()%>"<%= (category.getCategoryName().equals(selectedCategoryName)) ? "selected" : "" %>><%=category.getCategoryName()%></option>
				<%
					}
				%>
				</select> 
				<input type="submit" value="検索" class="search_button">
			</form>
			<form action="searchCategory" method="get" class="clear_button">
			  	<input type="submit" value="クリア" class="clear_button_input">
			</form>
		</div>
		<%
		for (ProductBean product : productList) {
		%>
		<div class="product_box">
			<form action="cart" method="post" class="product_form">
				<div class="image_box">
					<img src=<%=product.getProductImage()%> alt="商品画像" class="product_img">
				</div>
					<p class="product_name"><%=product.getProductName()%></p>
					<p class="price"><%=product.getPrice()%>円</p>
				<div class="description_box">
					<p class="product_description"><%=product.getProductDesc()%></p>
				</div>
				<div class="button_box">
					<input type="submit" value="カート" class="cart_button">
				</div>
			</form>
		</div>
		<%
		}
		%>

	</div>
</body>
</html>