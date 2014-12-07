<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="com.stanford.bookstore.dao.*, 
			com.stanford.bookstore.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%
		Integer id = Integer.valueOf(request.getParameter("id"));
		Book book = BookDAO.getBook(id);
	%>
	
	<div style="text-align: center;">
		<h1>BOOK DETAIL</h1>
	</div>
	<div>
		<form action="">
			<table style="border: 1px">
				<thead style="border: 4px">
					<tr>
						<th>BOOK ID</th>
						<th>BOOK NAME</th>
						<th>AUTHOR</th>
						<th>PRICE</th>
					</tr>
				</thead>	
				<tr>
					<td><%=book.getId()%></td>
					<td><%=book.getName()%></td>
					<td><%=book.getAuthor()%></td>
					<td><%=book.getPrice()%>$</td>					
				</tr>
			</table>			
			<input type="button" value="Back" onclick="window.history.back()">	
		</form>
	</div>

</body>
</html>