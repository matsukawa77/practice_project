<%@ page import="model.entity.CartBean, java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カート内商品</title>
<link rel="stylesheet" href="css/cart.css">
<%@ include file="header.jsp"%>
</head>
<body>

  <main>
    <div class="main__ttl">
      <h1>カート内商品</h1>
    </div>
<%
int totalPrice = 0;
List<CartBean> cartItemList = (List<CartBean>) request.getAttribute("cartItemList");
if (cartItemList != null) {
  for (CartBean cartItem : cartItemList) {
	  totalPrice += cartItem.getSumPrice();
%>

  <div class="main__wrapper">

    <div class="main__container">
		
		<div class=product__img__box>
			<img class="product__img" src="<%=cartItem.getProductImage()%>">
		</div>
		
		<div class="sub__container">
		  <table>
		      <tr class="table__item">
		        <td class="productName__txt"><%=cartItem.getProductName()%></td>
		        <td class="price__txt">￥<%=cartItem.getSumPrice()%></td>
		      </tr>
	      </table>
	
	      <div class="button__container">
		      <form class="upd__button" action="editCart" method="post">
		        <input class="input__quantity" type="text" name="quantity" value="<%=cartItem.getQuantity()%>">
		        <input type="hidden" name="productCode" value="<%=cartItem.getProductCode()%>">
		        <input type="submit" value="変更">
		      </form>
		
		      <form class="del__button" action="deleteCart" method="post">
		        <input type="hidden" name="productCode" value="<%=cartItem.getProductCode()%>">
		        <input type="submit" value="削除">
		      </form>
	      </div>
	    </div>
	</div>
  <%
    }
}
  %>
  <p class="sumPrice__txt">合計（税込）：￥<%=totalPrice %></p>
  </div>
  
  <form class="purchase__button" action="PurchaseConfirm" method="get">
    <input type="submit" value="購入画面へ">
  </form>

</body>
</html>