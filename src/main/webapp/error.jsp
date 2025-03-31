<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>エラー画面</title>
<link rel="stylesheet" type="text/css" href="css/error_complete.css">
</head>
<body>

<%@ include file="header.jsp"%>

<!-- 以下文はServletでerrorMessageの設定が必要
request.setAttribute("errorMessage", "エラーメッセージ内容");のやつ
 -->
 
 <p>エラーが発生しました。</p>
 
	<%
	// エラーメッセージの表示
	String errorMessage = (String) request.getAttribute("errorMessage");
	if (errorMessage != null) {
	%>
	<div class="error"><p><%=errorMessage%></p></div>
	<%
	}
	%>

<br>

<!-- 一旦リンク先を商品一覧からサーチカテゴリに変更 -->

<a href="searchCategory" class="btn">商品一覧画面へ</a>

</body>
</html>