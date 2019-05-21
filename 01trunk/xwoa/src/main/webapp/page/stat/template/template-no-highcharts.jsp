<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>

<!DOCTYPE html>
<html>
<head>
<title>${formNameFormat}</title>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />

<%@include file="/page/stat/childs/stat-css.jsp" %>

</head>
<body>

	<div class="container">
		<%@include file="/page/stat/childs/search-new.jsp"%>

		<%@include file="/page/stat/childs/normalTable-new.jsp"%>
	</div>

	<%@include file="/page/stat/childs/stat-scripts2.jsp" %>
</body>
</html>