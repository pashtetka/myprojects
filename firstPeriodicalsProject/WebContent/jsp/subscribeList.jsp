<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib prefix="logic" uri="http://struts.apache.org/tags-logic"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<logic:equal name="auth" property="userRole" value="admin">
	<div class="title">
		<h1>${user.login}</h1>
		<h2>
			<bean:message key="label.mail" />
			: ${user.mail}
		</h2>
		<br />
	</div>
	<html:errors property="itemsSubscribe" />
	<c:if test="${!userSubscribes.isEmpty()}">
		<hr />
		<div class="list">
			<div class="elem2">
				<b>ID</b>
			</div>
			<div class="elem1">
				<b><bean:message key="label.periodicalname" /></b>
			</div>
			<div class="elem2">
				<b><bean:message key="label.cost" /></b>
			</div>
		</div>
		<hr />
		<logic:iterate collection="${userSubscribes}" id="sub">
			<div class="list">
				<div class="elem2">${sub.id}</div>
				<div class="elem1">${sub.periodicalName}</div>
				<div class="elem2">${sub.cost}</div>
			</div>
			<br />
		</logic:iterate>
		<hr />
		<b><bean:message key="label.summa" /></b> ${summ}
		</c:if>
</logic:equal>
<br />
<br />
<html:link href="userList.do">
	<img src="style/back.png" width="50px" height="40px" class="back">
</html:link>