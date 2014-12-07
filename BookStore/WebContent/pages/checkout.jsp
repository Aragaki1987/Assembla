<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.util.*,
			com.stanford.bookstore.dao.*, 
			com.stanford.bookstore.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		List<Book> items = ((Cart)session.getAttribute("cart")).getItems();
		Integer price = 0;
		
		for(Book book : items) {
			price += book.getPrice();
		}
		
		items.clear();
	%>
	
	<div style="text-align: center;">
		<h1>CHECKOUT</h1>
	</div>
	<div style="text-align: center;">
		Total : <%=price %> $
	</div>
	
	<a href="catalog">Back to book list</a>
</body>
</html>