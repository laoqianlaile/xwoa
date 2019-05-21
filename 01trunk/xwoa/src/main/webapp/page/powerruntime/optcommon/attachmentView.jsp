<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>历史附件查看</title>
<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />
<script src="${ctx3rdJS}/jquery-1.9.1.min.js"></script>
<script src="${ctx3rdJS}/layer-2.1/layer.js"></script>
<script type="text/javascript">
   $(function(){
	   $("li").each(function(){
		   $(this).find("span").mouseover(function(){
			   var archivetype = $(this).data("archivetype")?$(this).data("archivetype"):'办理附件';
			   var iszhi = $(this).data("iszhi")=='true'?'是':'否';
			   var filetype = $(this).data("filetype");
			   var uploader = $(this).data("uploader");
			   var id = $(this).data("id");
			   var text = '<div>'
			            //+ '  <div>类别：'+filetype+'</div>'
			            + '  <div>分类：'+archivetype+'</div>'
			          //  + '  <div>是否纸质：'+iszhi+'</div>'
			            + '  <div>上传人：'+uploader+'</div>'
			           // + '  {0}'
			            + '</div>';
			   
			   
			   /* if($(this).data("iszhi")=='true'){
				   text = text.replace('{0}','');
			   }else{
				   var s = '<div><a href="${ctx}/powerruntime/generalOperator!downStuffInfo.do?stuffInfo.stuffid='+id+'">下载</a></div>';
				   text = text.replace('{0}',s);
			   } */
			   layer.tips(text, this, {
				   time: 4000,
				   tips: [4, '#0FA6D8'] //还可配置颜色
				 });
		   }).mouseout(function(){
			   layer.closeAll('tips');
		   }).click(function(){
			   if($(this).data("iszhi")==true){
				   return;
			   }else{
				   var id = $(this).data("id");
				   window.location.href="${ctx}/powerruntime/generalOperator!downStuffInfo.do?stuffInfo.stuffid="+id;
			   }
		   });
		  
	   });
	  
   });
</script>
</head>
<body style="font-family:Microsoft YaHei !important; font-size:14px;color:black;">
 <div class="group-title">
	     <div class="title-ico"></div>
	     <div class="title-name">附件</div>
	     <div class="title-split-line"><span></span></div>
</div>
<div class="div_wrapper" >
	<div class="fj" id="fj">
	   <div>
	    <c:if test="${empty vStuffnames1 and empty optStuffs}"> <div style="text-align:center;color:gray;font-family:Microsoft YaHei !important;">还没有添加任何附件</div></c:if>
	      <ul>
	       <c:if test="${empty vStuffnames1 }">
	         <c:forEach var="stuff" items="${optStuffs}">
	            <c:set var="filenameSuffix" value="${fns:getFileSuffix(stuff.filename)}"/>
	           <li>
	              <span style="background:url(${ctxStatic}/image/${filenameSuffix}.png)no-repeat;cursor:pointer"
	                 <c:if test="${not empty stuff.archivetype and 'fj' ne stuff.archivetype and 'nwfj' ne stuff.archivetype}">
	                   data-archivetype="${cp:MAPEXPRESSION('TEMPLATE_TYPE',stuff.archivetype)}"
	                 </c:if>
	                 data-iszhi="${stuff.iszhi eq '1'}" data-nodename="${stuff.nodename}" 
	                 data-uploader="${cp:MAPVALUE('usercode',stuff.uploadusercode)}"
	                 data-filetype="${cp:MAPVALUE('FILETYPE',stuff.filetype)}"
	                 data-id="${stuff.stuffid}">
	             ${stuff.filename} 
	              </span>
	           </li>
	         </c:forEach>
	       </c:if>
	       <c:forEach var="stuff" items="${vStuffnames1}">
	        <c:set var="filenameSuffix" value="${fns:getFileSuffix(stuff.filename)}"/>
	           <li>
	             <span style="background:url('${ctxStatic}/image/${filenameSuffix}.png') no-repeat 0 3px;cursor:pointer" 
	                 <c:if test="${not empty stuff.archivetype and 'fj' ne stuff.archivetype and 'nwfj' ne stuff.archivetype}">
	                   data-archivetype="${cp:MAPEXPRESSION('TEMPLATE_TYPE',stuff.archivetype)}"
	                 </c:if>
	                 data-iszhi="${stuff.iszhi eq '1'}" data-nodename="${stuff.nodename}" 
	                 data-uploader="${cp:MAPVALUE('usercode',stuff.uploadusercode)}"
	                 data-filetype="${cp:MAPVALUE('FILETYPE',stuff.filetype)}"
	                 data-id="${stuff.stuffid}">
	               ${stuff.filename}
	           </span></li>
	       </c:forEach>
	      </ul>
	   </div>	
	</div>
	</div>
</body>
</html>