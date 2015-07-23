<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib prefix="logic" uri="http://struts.apache.org/tags-logic"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="title">
	<h1>
		<bean:message key="label.users" />
		:
	</h1>
</div>
<logic:equal name="auth" property="userRole" value="admin">
	<html:form action="/userList">
		<div class="cont2">
			<img src="style/search.png" width="20px" height="20px">
			<html:text property="userLogin" styleId="search"></html:text>
			<html:submit value="search" />
		</div>
	</html:form>
	<html:errors property="nullUser" />
	<html:errors property="ownRole" />
	<html:errors property="adminRole" />
	<hr />
	<hr />
	<c:if test="${!userList.isEmpty()}">
		<div class="list">
			<div class="elem2">
				<b>ID</b>
			</div>
			<div class="elem1">
				<b><bean:message key="label.log" /></b>
			</div>
			<div class="elem2">
				<b><bean:message key="label.role" /></b>
			</div>
			<div class="elem1" align="center">
				<b><bean:message key="label.chRole" /></b>
			</div>
		</div>
		<hr />
		<logic:iterate collection="${userList}" id="user">
			<div class="list">
				<div class="elem2">${user.id}</div>
				<div class="elem1">
					<html:link href="userSubscribes.do?userId=${user.id}">${user.login}</html:link>
				</div>
				<div class="elem2">${user.userType}</div>
				<div class="elem1" align="center">
					(
					<html:link
						href="updateUserRole.do?userId=${user.id}&userRole=admin">admin</html:link>
					|
					<html:link href="updateUserRole.do?userId=${user.id}&userRole=user">user</html:link>
					)
				</div>
			</div>
			<br />
		</logic:iterate>
	</c:if>
	<hr />
</logic:equal>