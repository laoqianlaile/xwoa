<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%> 
<html>
<head>
<title><s:text name="pcpunishStandard.edit.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="pcpunishStandard.edit.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<s:form action="pcpunishStandard"  method="post" namespace="/powerbase" id="pcpunishStandardForm" onsubmit="return validatepcpunishStandardForm(this);" >
		<input type="hidden"  name="itemId"  id="itemId"  value="${object.itemId}" />
		<input type="hidden"  name="version"  id="version"  value="${object.version}" />

  <div>
 	<iframe id="itemFrame_pcTYpe" src="powerbase/pcpunishStandard!listPcType.do?itemId=${object.itemId}&version=${object.version}"   frameborder="0" width="100%" scrolling="no" 
		 onload="this.height=window.frames['itemFrame_pcTYpe'].document.body.scrollHeight"></iframe>
	
</div>
<div>
 	<iframe id="itemFrame_pcFreeumpireDegree" src="powerbase/pcfreeumpiredegree!list.do?itemId=${object.itemId}&version=${object.version}"   frameborder="0" width="100%" scrolling="no" 
		 onload="this.height=window.frames['itemFrame_pcFreeumpireDegree'].document.body.scrollHeight"></iframe>

</div>

</s:form>




	<script type="text/javascript">
	    
     
    	function doAddType(itemId,version) {
			var url = "powerbase/pcpunishStandard!edit.do?itemId="+itemId+"&version="+version ;
			document.location.href = url;
		}
    	function doFreeUmpire(itemId,version){
    		var url="powerbase/pcfreeumpiredegree!list.do?itemId="+itemId+"&version="+version;
     	   document.location.href = url;
    		
    	}   
    	function IsInUse(itemId,version,punishtypeid,isinuse){
    		   var id;
    		   var url;
    		   if( isinuse=="1"){
    		     id="禁用";
    		   }else{
    		     id="启用";
    		   }
    		 if(window.confirm("是否要将相应的自由裁量"+id)){
    	         url="powerbase/pcpunishStandard!editFreeumpireTypeIsInUse.do?itemId="+itemId+"&version="+version+"&punishtypeid="+punishtypeid;
    	         document.location.href = url;
    		   }else{
    		      url="powerbase/pcpunishStandard!editIsInUse.do?itemId="+itemId+"&version="+version+"&punishtypeid="+punishtypeid;
    	     	  document.location.href = url;
    		   }
    	}
    </script>	


</body>
</html>
