<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head></head>
<body>
	<h1>Struts 2 Hello World Example</h1>

	<s:form action="welcome" validate="true">
		<s:textfield name="username" label="UserName" />
		<s:password name="password" label="Password" />
		<s:submit name="submit" value="Submit"/>
	</s:form>
	
</body>
</html>