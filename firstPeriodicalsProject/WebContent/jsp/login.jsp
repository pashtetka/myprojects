<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<div class="title">
	<h1>
		<bean:message key="label.log" />
	</h1>
</div>
<html:form action="/login" method="POST">
	<div class="cont">
		<div class="labelLogin">
			<bean:message key="label.login" />
		</div>
		<div class="text">
<!-- 			<div class="popup" id="popup"> -->
<!-- 				<p> -->
<%-- 					<bean:message key="label.symbols" /> --%>
<!-- 				</p> -->
<!-- 			</div> -->
			<html:text property="userName" styleId="username"></html:text>
		</div>
	</div>
	<html:errors property="userName" />
	<br />
	<div class="cont">
		<div class="labelLogin">
			<bean:message key="label.password" />
		</div>
		<div class="text">
			<html:password property="password"></html:password>
		</div>
		<br />
	</div>
	<html:errors property="password" />
	<html:errors property="invalidUserName" />
	<html:errors property="invalidPassword" />
	<html:submit value="Enter"/>
	<html:reset />
</html:form>