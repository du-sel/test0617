<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
	
		String[] sames = request.getParameterValues("same");
	
		for(int i = 0; i<sames.length; i++) {
	%>
	
		<h3><%=sames[i] %></h3>
		
	<%	} %>

</body>
</html>