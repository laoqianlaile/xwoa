<%@ page language="java"%>
<%@ taglib uri="http://www.centit.com/el/coderepo" prefix="cp"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="authz"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec" %>
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix="fns" uri="http://www.centit.com/el/sysfuncs"%> 
<% 
//response.setHeader("Pragma","No-cache"); 
//response.setHeader("Cache-Control","no-cache"); //HTTP 1.1 
//response.setDateHeader ("Expires", 0); //prevents caching at the proxy server 
pageContext.setAttribute("contextPath", request.getContextPath());
pageContext.setAttribute("ctx", request.getContextPath());
pageContext.setAttribute("ctxStatic", request.getContextPath()+"/newStatic");
pageContext.setAttribute("ctx3rdJS", request.getContextPath()+"/newStatic/js/3rd");
pageContext.setAttribute("ctxCustomJS", request.getContextPath()+"/newStatic/js/custom");
%> 