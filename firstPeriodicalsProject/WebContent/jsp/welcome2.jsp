<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="wel">
	<h1>
		<bean:message key="label.welcome" />
		, ${auth.userName}!
	</h1>

	<div class="wel_lang">
		<html:link action="/language.do?lang=en">
			<img src="style/en.jpg" width="20px" height="10px">English
</html:link>
		<html:link action="/language.do?lang=ru">
			<img src="style/ru.jpg" width="20px" height="10px">Русский
</html:link>
	</div>
</div>