<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib prefix="logic" uri="http://struts.apache.org/tags-logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<div class="title">
	<h1>
		<bean:message key="label.cart" />
	</h1>
</div>
<html:errors property="itemsBasket" />
<c:if test="${!periodicalsBasket.isEmpty()}">
	<html:form action="/newSubscribe" method="POST">
		<hr />
		<div class="list">
			<div class="elem1">
				<b><bean:message key="label.periodicalname" /></b>
			</div>
			<div class="elem2">
				<b><bean:message key="label.cost" /></b>
			</div>
			<div class="elem2">
				<b><bean:message key="label.outputs" /></b>
			</div>
			<div class="elem2">
				<b><bean:message key="label.topic" /></b>
			</div>
		</div>
		<hr />
		<c:forEach items="${periodicalsBasket}" var="per">
			<html:hidden property="periodicalId" value="${per.id}" />
			<div class="list">
				<div class="elem1">${per.periodicalName}</div>
				<div class="elem2">${per.cost}</div>
				<div class="elem2" align="center">${per.outputsInMonth}</div>
				<div class="elem2">${per.topic}</div>
				<div class="elem2">
					<div class="delete">
						<html:link href="deleteBasket.do?periodicalId=${per.id}">
							<img src="style/delete.png" width="15px" height="15px">
						</html:link>
					</div>
				</div>
			</div>
			<br />
		</c:forEach>
		<hr />
		<b><bean:message key="label.summa" /></b> ${summ}
	<html:submit value="Subscribe" />
	</html:form>
</c:if>