<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="logic" uri="http://struts.apache.org/tags-logic"%>

<div class="title">
	<h1>
		<bean:message key="label.per" />
	</h1>
</div>
<html:form action="/periodicalsList">
	<div class="cont2">
		<img src="style/search.png" width="20px" height="20px">
		<html:text property="name" styleId="search"></html:text>
		<html:submit value="search" />
	</div>
</html:form>
<hr />
<html:errors property="statusbasket" />
<html:errors property="statussubscribe" />
<html:errors property="performed" />
<html:errors property="nullPer" />
<c:if test="${!periodicals.isEmpty()}">
	<div class="list2">
		<c:forEach items="${periodicals}" var="per">
			<div class="periodical">
				<html:link href="comments.do?periodicalId=${per.id}">
					<img src="ImportImage?${per.id}" width="120px" height="150px">
					<br />
			${per.periodicalName}</html:link>
				<br /> <b><bean:message key="label.cost" />:</b> ${per.cost}
			</div>
		</c:forEach>
		<div class="pages">
			<hr />
			<c:forEach items="${pages}" var="pag">
				<c:choose>
					<c:when test="${pag == currentPage}">
						<b>${pag}</b>
					</c:when>
					<c:otherwise>
						<html:link href="periodicalsList.do?page=${pag}">${pag}</html:link>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</div>
	</div>
</c:if>
