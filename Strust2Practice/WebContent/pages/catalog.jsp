<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div style="text-align: center;">
		<h1>CATALOG</h1>
	</div>
	<div>Your cart contain : <s:property value="#session.cart.getItems().size()" /> items</div>
	<div id="booklist" >
		<s:form theme="simple" method="post" action="buy">
			<table>
				<thead>
					<tr>
						<th>NAME</th>
						<th>PRICE</th>
						<th>AUTHOR</th>	
						<th></th>					
					</tr>
				</thead>
				<s:iterator value="catalogs" var="cat">
					<tr>
						<td><s:property value="#cat.name" /></td>
						<td><s:property value="#cat.price" /></td>
						<td><s:property value="#cat.author" /></td>	
						<td><s:checkbox name="ids" label="Check to buy" value="false" fieldValue="%{#cat.id}"></s:checkbox><td>					
					</tr>
				</s:iterator>
			</table>
			<s:submit value="Buy"/>
		</s:form>
	</div>
</body>
</html>