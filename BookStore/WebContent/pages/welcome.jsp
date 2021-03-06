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
<style>
.center {
    margin-left: auto;
    margin-right: auto;
    width: 70%;
    background-color: #b0e0e6;
}
</style>
</head>
<body>

	<%
		List<Book> bookList = BookDAO.getBookList();
	%>
	<div style="float: left">
		Your items number : <%=((Cart)session.getAttribute("cart")).getItems().size() %> <br/>
		<a href="cart">Get in your cart</a>
	</div>
	<div style="text-align: center;">
		<h1>BOOK LIST</h1>
	</div>
	<div>
		<form action="cart" method="post">
			<table style="border: 1px">
				<thead style="border: 4px">
					<tr>
						<th>BOOK NAME</th>
						<th>AUTHOR</th>
						<th>PRICE</th>
					</tr>
				</thead>

				<%
					for (Book book : bookList) {
				%>	
							
				<tr>
					<td><a href=<%="bookDetail?id=" + book.getId()%>><%=book.getName()%></a></td>
					<td><%=book.getAuthor()%></td>
					<td><%=book.getPrice()%>$</td>
					<td><input type="checkbox" name="id" value="<%=book.getId()%>"/> </td>
				</tr>				
				<%} %>
			</table>			
			<input type="submit" value="Buy" >		
		</form>
	</div>
	
	<div>
		<%=request.getAttribute("error") == null ? "" : request.getAttribute("error") %>
	</div>
</body>
</html>