<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<tiles:insert definition="base.layout">
	<tiles:put name="content" value="/periodicalsList2.do?page=1"></tiles:put>
</tiles:insert>
