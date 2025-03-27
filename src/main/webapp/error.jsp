<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>エラー画面</title>
<!-- CSSファイルはこの辺に入れる
<link rel="stylesheet" href="ファイルの名前.css">
 -->
</head>
<body>

<%@ include file="header.jsp"%>

<!-- 以下文はServletでerrorMessageの設定が必要
もしいらなかったら消すだけ、作ってるうちに追々
request.setAttribute("errorMessage", "エラーメッセージ内容");のやつ
 -->
 
 <p>エラーが発生しました。</p>
 
	<%
	// エラーメッセージの表示
	String errorMessage = (String) request.getAttribute("errorMessage");
	if (errorMessage != null) {
	%>
	<p><%=errorMessage%></p>
	<%
	}
	%>

<a href="index.jsp">商品一覧画面へ</a>

</body>
</html>