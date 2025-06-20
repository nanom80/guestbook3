<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.javaex.vo.GuestbookVO" %>

<%
	System.out.println("◎addList.jsp");
	
	List<GuestbookVO> guestbookList= (List<GuestbookVO>)request.getAttribute("gList");
	
	for (GuestbookVO vo : guestbookList) {
		System.out.println(vo.toString());
	}
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="http://192.168.0.99:8080/guestbook3/pbc" method="get">
	    <input type="hidden" name="action" value="write">
		<table border="1" width="540px">
			<tr>
				<td>이름</td><td><input type="text" name="name"></td>
				<td>비밀번호</td><td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
			</tr>
			<tr>
				<td colspan="4"><button type="submit">등록</button></td>
			</tr>
		</table>
	</form>
	<br>
	<%
	for(int i=0; i<guestbookList.size(); i++) {
	%>
		<table border="1" width="540px">
			<tr>
				<td width="50"><%= guestbookList.get(i).getGuestbookId() %></td>
				<td width="280"><%= guestbookList.get(i).getName() %></td>
				<td width="160"><%= guestbookList.get(i).getRegDate() %></td>
				<td width="50"><a href="deleteForm.jsp?gId=<%=guestbookList.get(i).getGuestbookId()%>">삭제</a></td>
			</tr>
			<tr>
				<td colspan="4"><%= guestbookList.get(i).getContent() %></td>
			</tr>
		</table>
		<br>
	<%
	}
	%>
	<%-- 메시지 출력 --%>
	<%
    String message = (String) request.getAttribute("message");
    if (message != null) {
	%>
	    <p style="color: blue;"><%= message %></strong></p>
	<%
	}
	%>
</body>
</html>