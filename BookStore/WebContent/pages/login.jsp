<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<br />
	<br />
	<br />

	<form action="login" method="post">
		UserName : <input type="text" name="username" /> <br /> Password : <input
			type="password" name="password" /> <br /> <input type="submit"
			value="Submit" />
	</form>

	<br />
	<br />
	<br />
	<%
		String[] errors = (String[]) request.getAttribute("error");
		if (errors != null) {
			for (String error : errors) {
				out.println(error);
			}
		}
	%>

	<br />
	<br />
	<br />
	<%@ include file="footer.jsp"%>

</body>
</html>