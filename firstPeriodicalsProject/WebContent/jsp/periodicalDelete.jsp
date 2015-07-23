<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="logic" uri="http://struts.apache.org/tags-logic"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<div class="title">
	<h1>
		<bean:message key="label.delPer" />
		:
	</h1>
</div>
<logic:present name="auth" scope="session">
	<logic:equal name="auth" property="userRole" value="admin">

		<html:form action="/periodicalDelete" method="POST">
			<div class="list2">
				<logic:iterate collection="${publications}" id="per">
					<div class="periodical">
						<html:multibox property="selectedDevices">${per.id}</html:multibox>
						<img src="ImportImage?${per.id}" width="120px" height="150px"><br />
						${per.periodicalName} (${per.cost}, ${per.outputsInMonth},
						${per.topic})
					</div>
				</logic:iterate>
			</div>
			<html:submit value="Delete"/>
		</html:form>
	</logic:equal>
</logic:present>
<div class="pages">
	<c:forEach items="${pages}" var="pag">
		<c:choose>
			<c:when test="${pag == currentPage}">
				<b>${pag}</b>
			</c:when>
			<c:otherwise>
				<html:link href="periodicalDelete.do?page=${pag}">${pag}</html:link>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</div>