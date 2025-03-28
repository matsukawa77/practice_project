<%@ page import="model.entity.CartBean, java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カート内商品</title>
<%
List<CartBean> cartItemList = (List<CartBean>) request.getAttribute("cartItemList");
if (cartItemList != null) {
  for (CartBean cartItem : cartItemList) {
%>
</head>
<body>
  <main>
    <div class="main__ttl">
      <h1>カート内商品</h1>
    </div>

  <div class="main__wrapper">

    <div class="main__container">

      <img src="<%=cartItem.getProductImage()%>">

      <tr class="table__item">
        <td><%=cartItem.getProductName()%></td>
        <td><%=cartItem.getSumPrice()%></td>
      </tr>

      <from class="detail" action="editCart" method="post">
        <input type="text" name="quantity" value="<%=cartItem.getQuantity()%>">
        <input type="hidden" name="productCode" value="<%=cartItem.getProductCode()%>">
        <input type="submit" value="変更">
      </from>

      <form class="" action="deleteCart" method="post">
        <input type="hidden" name="productCode" value="<%=cartItem.getProductCode()%>">
        <input type="submit" value="削除">
      </form>
    </div>

  </div>
  <%
    }
}
  %>
  <form action="confirmPurchase" method="get">
    <input type="submit" value="購入画面へ">
  </form>

</body>
</html>