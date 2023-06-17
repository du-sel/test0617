<%@page import="dao.StudentDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	
	ArrayList<StudentDTO> list = (ArrayList)request.getAttribute("STUDLIST");
	
	String username = (String)session.getAttribute("USERNAME");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>


	table {
		border-collapse: collapse;
	}

	th, td {
		text-align: center;
		padding: 5px 10px;
		border: 2px solid white;
	}
	
	th {
		background: darkslateblue;
		color: white;
	}
	
	td {
		background: lightsteelblue;
	}
	td[onclick]:hover {
		cursor: pointer;
		opacity: 0.6;
	}
	
	#acc_name {
		display: inline-block;
		margin-bottom: 30px;
	}

</style>

<script>

	const toInfo = function(id) {
		
		location.href="info.do?id="+id;
		
	}


</script>


</head>
<body>

	<h4 id="acc_name"><%=username %> 님 로그인 상태입니다</h4>
	[<a href="logout.do">로그아웃</a>]
	

	
	
	<h1>전체 학생 목록</h1>
	
	[<a href="regi.do">학점 입력하기</a>]
	
	<hr>
	
	<table>
	
		<tr>
			<th>학생번호</th>
			<th>소속학과</th>
			<th>학생이름</th>
			<th>학년</th>
			<th>성별</th>
			<th>나이</th>
			<th>전화번호</th>
			<th>주소</th>
		</tr>
	
		<% for(int i = 0; i<list.size(); i++)  { %>
			<tr>
				<td onclick="toInfo(<%=list.get(i).getId() %>)"><%=list.get(i).getId() %></td>
				<td><%=list.get(i).getDept() %></td>
				<td><%=list.get(i).getName() %></td>
				<td><%=list.get(i).getGrade() %></td>
				<td><%=list.get(i).getGender() %></td>
				<td><%=list.get(i).getAge() %></td>
				<td><%=list.get(i).getPhone() %></td>
				<td><%=list.get(i).getAddress() %></td>
			</tr>
		<% } %>
	
	</table>
	
</body>
</html>