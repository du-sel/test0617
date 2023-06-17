<%@page import="dao.ScoreDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.ReportDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	ReportDTO rep = (ReportDTO)request.getAttribute("REPORT");
	ArrayList<ScoreDTO> scores = rep.getScores();
	
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
	
	td {
		background: lightsteelblue;
		text-align: center;
		padding: 5px 10px;
		border: 2px solid white;
	}

	#head td {
		background: darkslateblue;
		color: white;
	}
	
	#mid td {
		background: darkslateblue;
		color: white;
	}
	
	#fin {
		color: firebrick;
		font-weight: bolder;
	}
	#acc_name {
		display: inline-block;
		margin-bottom: 30px;
	}
	#toList {
		display: inline-block;
		margin-top: 30px;
	}

</style>
</head>
<body>

	<h4 id="acc_name">${USERNAME } 님 로그인 상태입니다</h4>
	[<a href="logout.do">로그아웃</a>]

	

	<h1>개인 성적표</h1>
	
	<table>
	
		<tr id="head">
			<td>학번</td>
			<td colspan="3">${REPORT.id }</td>
		</tr>
		<tr>
			<td>이름</td>
			<td colspan="3"><%=rep.getName() %></td>
		</tr>
		<tr>
			<td>소속학과</td>
			<td colspan="3"><%=rep.getDept() %></td>
		</tr>

	
		<tr id="mid">
			<td>과목명</td>
			<td>담당교수</td>
			<td>이수구분</td>
			<td>점수</td>
		</tr>
		
		<% for(int i = 0; i<scores.size(); i++) { %>
			<tr>
				<td><%=scores.get(i).getLecTitle() %></td>
				<td><%=scores.get(i).getProf_name() %></td>
				<td><%=scores.get(i).getCategory() %></td>
				<td><%=scores.get(i).getScore() %></td>
			</tr>
		<% } %>
		
		<tr>
			<td>총점</td>
			<td colspan="3"><%=rep.getSum() %></td>
		</tr>
		<tr>
			<td>평균</td>
			<td colspan="3"><%=rep.getAvg() %></td>
		</tr>
		<tr>
			<td>최종학점</td>
			<td colspan="3" id="fin"><%=rep.getFin() %></td>
		</tr>
	
	</table>
	
	
	
	<c:if test="${ACCOUNT == 'admin' }">
		[<a href="studList.do" id="toList">목록으로 돌아가기</a>]
	</c:if>
	
	
	
	
	
</body>
</html>