<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<tiles:insert definition="base.layout">
	<tiles:put name="title" value="Registration" />
	<tiles:put name="content" value="/jsp/registration.jsp"></tiles:put>
</tiles:insert>
