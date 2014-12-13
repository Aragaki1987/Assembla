<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head></head>
<body>
	<h1>Struts 2 Hello World Example</h1>

	<s:form action="welcome">
		<s:textfield name="username" key="global.username" />
		<s:password name="password" key="global.password" />
		<s:submit key="global.submit" name="submit" />
	</s:form>

	<s:url id="localeEN" namespace="/localization" action="locale">
		<s:param name="request_locale">en</s:param>
	</s:url>
	<s:url id="localeDE" namespace="/localization" action="locale">
		<s:param name="request_locale">de</s:param>
	</s:url>
	<s:url id="localeFR" namespace="/localization" action="locale">
		<s:param name="request_locale">fr</s:param>
	</s:url>

	<s:a href="%{localeEN}">English</s:a>
	<s:a href="%{localeDE}">German</s:a>
	<s:a href="%{localeFR}">France</s:a>
</body>
</html>