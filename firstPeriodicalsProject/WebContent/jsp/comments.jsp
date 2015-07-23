<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib prefix="logic" uri="http://struts.apache.org/tags-logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="popup">
<img id="close" src="style/delete.png" width="15px" height="15px" onclick="close()">
<img src="ImportImage?${per.id}" width="300px" height="350px">
</div>
<div class="title">
	<h1>${per.periodicalName}</h1>
	<div class="list2">
		<div class="image" id="picture">
			<img src="ImportImage?${per.id}" width="120px" height="150px" onclick="handle()">
			<logic:present name="auth" scope="session">
				<logic:equal name="auth" property="userRole" value="admin">
					<html:form action="/changeImage" method="POST"
						enctype="multipart/form-data">
						<html:errors property="nullImage" />
						<html:hidden property="id" value="${per.id}" />
						<html:file property="image"></html:file>
						<html:submit value="Change image" />
					</html:form>
				</logic:equal>
			</logic:present>
		</div>
		<div class="perCont">
			<b><bean:message key="label.cost" />:</b> ${per.cost}<br /> <b><bean:message
					key="label.outputs" />:</b> ${per.outputsInMonth}<br /> <b><bean:message
					key="label.topic" />:</b> ${per.topic}
			<logic:present name="auth" scope="session">
				<br />
				<html:link href="newPeriodicalToCart2.do?periodicalId=${per.id}">
					<img src="style/cart.png" width="25px" height="25px" class="back">
				</html:link>
			</logic:present>
		</div>
	</div>
	<html:errors property="largeSizePictures" />
	<html:errors property="statusbasket" />
	<html:errors property="statussubscribe" />
	<html:messages id="message" message="true" property="performed">
		<div class="message">
			<bean:write name="message" />
		</div>
	</html:messages>
	<br />
</div>
<c:if test="${!comments.isEmpty()}">
	<h3>
		<bean:message key="label.comments" />
	</h3>
	<hr />
	<c:forEach items="${comments}" var="comm">
		<div class="list2">
			<div class="loginComent">
				<b>${comm.userLogin}</b>
			</div>
			<logic:present name="auth" scope="session">
				<bean:define id="userRole" name="auth" property="userRole"
					scope="session" />
				<logic:equal name="auth" property="userRole" value="admin">
					<div class="delete">
						<html:link href="commentDelete.do?id=${comm.id}">
							<img src="style/delete.png" width="15px" height="15px">
						</html:link>
					</div>
				</logic:equal>
			</logic:present>
			<br />
			<div class="comment">${comm.comm}</div>
		</div>
		<hr />
	</c:forEach>
</c:if>
<br />
<html:errors property="guestComment" />
<logic:present name="auth" scope="session">

	<html:errors property="commentNull" />
	<br />
	<h3>
		<bean:message key="label.newComm" />
	</h3>

	<html:form action="/commentNew" method="POST">
		<bean:message key="label.comment" />
		<br />
		<html:textarea property="comm"></html:textarea>
		<br />
		<html:hidden property="periodicalId" value="${per.id}" />
		<html:submit value="Add" />
	</html:form>

</logic:present>
<br />
<html:link href="periodicalsList.do">
	<img src="style/back.png" width="50px" height="40px" class="back">
</html:link>