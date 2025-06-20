<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.javaex.vo.GuestbookVO" %>

<%
	System.out.println("◎deleteForm.jsp");

	String gId = request.getParameter("gId");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>삭제 폼</title>
</head>
<body>
	<h2>삭제하려면 비밀번호를 입력하세요</h2>
    <form action="http://192.168.0.99:8080/guestbook3/pbc" method="get">
        <input type="hidden" name="action" value="delete">
        <input type="hidden" name="gId" value="<%=gId%>">
        <p>비밀번호: <input type="password" name="password" required></p>
        <p><button type="submit">삭제하기</button></p>
    </form>
	
	<br><br>
	<a href="http://192.168.0.99:8080/guestbook3/pbc?action=list">메인으로 돌아가기</a>
</body>
</html>
