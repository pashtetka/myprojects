<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib prefix="logic" uri="http://struts.apache.org/tags-logic"%>

<div class="title">
	<h1>
		<bean:message key="label.newPer" />
		:
	</h1>
</div>
<logic:present name="auth" scope="session">
	<bean:define id="userRole" name="auth" property="userRole"
		scope="session" />
	<logic:equal name="auth" property="userRole" value="admin">
		<html:form action="/periodicalNew" method="POST" enctype="multipart/form-data">

			<div class="cont">
				<div class="label">
					<bean:message key="label.periodicalname" />*
				</div>
				<div class="text">
					<html:text property="periodicalName"></html:text>
				</div>
			</div>
			<html:errors property="periodicalName" />
			<br />
			<div class="cont">
				<div class="label">
					<bean:message key="label.cost" />*
				</div>
				<div class="text">
					<html:text property="cost"></html:text>
				</div>
			</div>
			<html:errors property="cost" />
			<br />
			<div class="cont">
				<div class="label">
					<bean:message key="label.outputs" />*
				</div>
				<div class="text">
					<html:text property="outputsInMonth"></html:text>
				</div>
			</div>
			<html:errors property="outputsInMonth" />
			<br />
			<div class="cont">
				<div class="label">
					<bean:message key="label.topic" />*
				</div>
				<div class="text">
					<html:text property="topic"></html:text>
				</div>
			</div>
			<html:errors property="topic" />
			<br />
			<div class="cont">
				<div class="label">
					<bean:message key="label.image" />
				</div>
				<div class="text">
					<html:file property="image"></html:file> ( < 1MB )
				</div>
			</div>
			<html:errors property="largeSizePictures" />
			<br />
			<html:submit />
			<html:reset />
		</html:form>
	</logic:equal>
</logic:present>