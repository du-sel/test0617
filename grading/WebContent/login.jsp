<%@page import="dao.StudentDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%

	ArrayList<StudentDTO> list = (ArrayList)application.getAttribute("IDLIST");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

	#idlist {
		border-collapse: collapse;
	}
	#idlist td {
		border: 1px solid black;
		padding: 3px 10px;
	}


</style>

</head>
<body>

	
	<c:if test="${WRONG != null }">
		<h5>비밀번호를 ${WRONG } 회 틀렸습니다.</h5>
	</c:if>
	
	<c:if test="${NOTFOUND != null }">
		<h5>존재하지 않는 회원입니다.</h5>
	</c:if>
	
	

	<form name="login" action="login.do" method="post">
	
		<table>
		
			<tr>
				<td>ID</td>
				<td>
					<input type="text" name="id">
				</td>
			</tr>
			<tr>
				<td>PW</td>
				<td>
					<input type="password" name="pw">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="로그인">
				</td>
			</tr>
		
		</table>

	</form>


	<h5>* 관리자 ID는 admin 입니다</h5>
	<h5>* 모든 사람의 비밀번호는 1234 입니다</h5>
	<br><br>
	
	<h5>로그인 가능한 학생번호</h5>
	
	
	<table id="idlist">

 	<% for(int i = 0; i<list.size(); i++) { %>
		<tr>
			<td><%= list.get(i).getId() %></td>
		</tr>	
	<% } %>

	</table> 
</body>
</html>











