<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="logic" uri="http://struts.apache.org/tags-logic"%>

<logic:notPresent name="auth" scope="session">
	<logic:redirect href="periodicalsList.do?page=1"></logic:redirect>
</logic:notPresent>
<logic:equal name="auth" property="userRole" value="user">
	<logic:redirect href="periodicalsList.do?page=1"></logic:redirect>
</logic:equal>
<tiles:insert definition="periodicalNew.layout">
</tiles:insert>