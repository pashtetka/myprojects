<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib prefix="logic" uri="http://struts.apache.org/tags-logic"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<h3>
	<logic:equal name="auth" property="userRole" value="admin">
		<html:link href="periodicalNewTile.jsp">
			<bean:message key="label.newPer" />
		</html:link>
		<br />
		<html:link href="periodicalDelete.do?page=1">
			<bean:message key="label.delPer" />
		</html:link>
		<br />
		<html:link href="userList.do">
			<bean:message key="label.subList" />
		</html:link>
		<br />
		<br />
	</logic:equal>
	<html:link href="periodicalsList.do">
		<bean:message key="label.perList" />
	</html:link>
	<br />
	<html:link href="basket.do?status=basket">
		<bean:message key="label.cart" />
	</html:link>
	<br />
	<html:link href="subscribe.do?status=subscribe">
		<bean:message key="label.subscribes" />
	</html:link>
	<br /> <br />
	<html:link href="logout.do">
		<bean:message key="label.logout" />
	</html:link>
</h3>