<%@ page import="model.entity.CartBean, java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カート内商品</title>
<link rel="stylesheet" href="css/cart.css">
<%
List<CartBean> cartItemList = (List<CartBean>) request.getAttribute("cartItemList");
if (cartItemList != null) {
  for (CartBean cartItem : cartItemList) {
%>
</head>
<body>
<%@ include file="header.jsp"%>
  <main>
    <div class="main__ttl">
      <h1>カート内商品</h1>
    </div>

  <div class="main__wrapper">

    <div class="main__container">

		<img class="product__img" src="<%=cartItem.getProductImage()%>">

		<div class="sub__container">
		  <table>
		      <tr class="table__item">
		        <td><%=cartItem.getProductName()%></td>
		        <td><%=cartItem.getSumPrice()%></td>
		      </tr>
	      </table>
	
	      <div class="button__container">
		      <from class="upd__button" action="editCart" method="post">
		        <input class="input__quantity" type="text" name="quantity" value="<%=cartItem.getQuantity()%>">
		        <input type="hidden" name="productCode" value="<%=cartItem.getProductCode()%>">
		        <input type="submit" value="変更">
		      </from>
		
		      <form class="del__button" action="deleteCart" method="post">
		        <input type="hidden" name="productCode" value="<%=cartItem.getProductCode()%>">
		        <input type="submit" value="削除">
		      </form>
	      </div>
	    </div>
	</div>
  </div>
  <%
    }
}
  %>
  <form class="purchase__button" action="confirmPurchase" method="get">
    <input type="submit" value="購入画面へ">
  </form>

</body>
</html>