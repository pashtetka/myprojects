<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link type="text/css" rel="stylesheet" href="style/style.css">
<script type="text/javascript" src="style/popup.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:getAsString name="title" ignore="true" /></title>
</head>
<body>
	<div class="header_div">
		<div class="header">
			<tiles:insert attribute="header" />
		</div>
	</div>
	<div class="central">
		<div class="menu_div">
			<div class="menu">
				<tiles:insert attribute="menu" />
			</div>
		</div>
		<div class="content_div">
			<div class="content">
				<tiles:insert attribute="content" />
			</div>
		</div>
	</div>
	<div class=footer>
		<h3>
			<tiles:getAsString name="footer" />
		</h3>
	</div>
</body>
</html>
