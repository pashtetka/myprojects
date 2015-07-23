<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<div class="title">
	<h1>
		<bean:message key="label.reg" />
		:
	</h1>
</div>
<html:form action="/registration" method="POST">

	<div class="cont">
		<div class="label">
			<bean:message key="label.username" />
		</div>
		<div class="text">
			<html:text property="userName"></html:text>
		</div>
	</div>
	<html:errors property="userName" />

	<br />

	<div class="cont">
		<div class="label">
			<bean:message key="label.usersurname" />
		</div>
		<div class="text">
			<html:text property="userSurname"></html:text>
		</div>
	</div>
	<html:errors property="userSurname" />

	<br />

	<div class="cont">
		<div class="label">
			<bean:message key="label.login" />
		</div>
		<div class="text">
			<html:text property="loginReg"></html:text>
		</div>
	</div>
	<html:errors property="login" />
	<html:errors property="equallogin" />

	<br />

	<div class="cont">
		<div class="label">
			<bean:message key="label.password" />
		</div>
		<div class="text">
			<html:password property="passwordReg"></html:password>
		</div>
	</div>
	<html:errors property="password" />

	<br />

	<div class="cont">
		<div class="label">
			<bean:message key="label.password2" />
		</div>
		<div class="text">
			<html:password property="password2"></html:password>
		</div>
	</div>
	<html:errors property="password2" />
	<html:errors property="passwordnull" />

	<br />

	<div class="cont">
		<div class="label">
			<bean:message key="label.mail" />
		</div>
		<div class="text">
			<html:text property="mail"></html:text>
		</div>
	</div>
	<html:errors property="mail" />

	<br />
	<html:submit />
	<html:reset />
</html:form>