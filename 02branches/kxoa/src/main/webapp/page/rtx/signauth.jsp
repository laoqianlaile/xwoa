<%@page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
  <head>  
    <title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
  </head>
  <OBJECT classid=clsid:5EEEA87D-160E-4A2D-8427-B6C333FEDA4D id=RTXAX></OBJECT>
   <body onload="signauth();">
   </body>
</html>

<script type="text/javascript">

function signauth(){ 
    try{
    	/* debugger; */
    	var objKernalRoot = RTXAX.GetObject("KernalRoot");
        var objRtcData = objKernalRoot.Sign;
        var strAccount = objKernalRoot.Account;
        var strSgin = objRtcData.GetString("Sign");
   }catch(e){
	   
   } 
   window.location.href ="<%=request.getContextPath()%>/sys/mainFrame!showMainByRTX.do?url=${param.url}&user="+strAccount+"&sign="+strSgin;
}

</script>
