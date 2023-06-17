<%@page import="dao.GradeStudentDTO"%>
<%@page import="dao.StudentDTO"%>
<%@page import="java.util.HashMap"%>
<%@page import="dao.ActiveLectureDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	
	ArrayList<ActiveLectureDTO> lecList = (ArrayList)request.getAttribute("LECLIST");
	HashMap<String, ArrayList<GradeStudentDTO>> map = (HashMap)request.getAttribute("STUDMAP");
	
	String username = (String)session.getAttribute("USERNAME");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>

	h3:not(:first-of-type) {
		margin-top: 100px;
	}

	form {
		display: inline-block;
		width: auto;
	}

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

	input {
		border: none;
		text-align: center;
	}
	input:focus {
		outline: none;
	}
	
	input[type="submit"] {
		background: lightgrey;
		padding: 5px 10px;
		margin-top: 10px;
	}
	#btnBox {
		display: flex;
		justify-content: flex-end;
	}
	
	#acc_name {
		display: inline-block;
		margin-bottom: 30px;
	}
	

</style>


</head>
<body>

	<h4 id="acc_name">${USERNAME } 님 로그인 상태입니다</h4>
	[<a href="logout.do">로그아웃</a>]
	



	<h1>학점 입력</h1>
	
	[<a href="studList.do">학생 목록 보기</a>]
	
	<hr>
	
	<% for(int i = 0; i<lecList.size(); i++)  { %>
			
		<h3><%=lecList.get(i).getId() %> /
		<%=lecList.get(i).getTitle() %> /
		<%=lecList.get(i).getProf_name() %> /
		<%=lecList.get(i).getClassroom() %> /
		<%=lecList.get(i).getCategory() %></h3>
		
		<form name="frm" action="update.do" method="post">
			<input type="hidden" name="lecId" value="<%=lecList.get(i).getId() %>">
			<table>
			
				<tr>
					<th>학생번호</th>
					<th>학생이름</th>
					<th>점수</th>
				</tr>
				
				<% 
					ArrayList<GradeStudentDTO> studList = map.get(lecList.get(i).getId());
				
					for(int j = 0; j<studList.size(); j++) {
						String s = "";
						if(studList.get(j).getScore() != 0) {
							s = ""+studList.get(j).getScore();
						}
	 			%>
	 			
	 				<tr>
						<td><%=studList.get(j).getStudId() %></td>
						<td><%=studList.get(j).getName() %></td>
						<td>
							<input type="text" name='score' value="<%=s %>">
							<%-- <input type="hidden" name='studId' value="<%=studList.get(j).getStudId() %>"> --%>
						</td>
					</tr>
				
	 			<% } %>
			
			</table>
			
			<div id="btnBox">
				<input type="submit" value="점수 등록">
			</div>
		</form>
			
	<% } %>
	
	
	
	
	
</body>
</html>