<%@page language="java" contentType="text/html;charset=UTF-8"%>
<html>
  <head>  
    <title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
  </head>
  <OBJECT classid=clsid:5EEEA87D-160E-4A2D-8427-B6C333FEDA4D id=RTXAX></OBJECT>
   <body onload="loginRtx();">
   </body>
</html>

<script type="text/javascript">

function loginRtx(){ 
    try{
       var key='<%=session.getAttribute("strSessionKey")%>';
     if(key!=""){
           var rtxSer='<%=session.getAttribute("rtxSer")%>';
           var objProp = RTXAX.GetObject("Property");
           objProp.value("RTXUsername") = '<%=session.getAttribute("strUser") %>'; //  用户账号登录名
           objProp.value("LoginSessionKey") =key;//该账号对应的key
           objProp.value("ServerAddress") = rtxSer; //RTX Server IP地址
           objProp.value("ServerPort") = 8000;
           RTXAX.Call(2,objProp);  //2表示通过SessionKey登录
        } 
   }catch(e){
   } 
}

</script>
