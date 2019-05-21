<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaAssetinformation.view.title" /></title>
<script type="text/javascript">
function reEditno(no){
	var ev=$("#assetleftalert").val();
   $.post(
         "<%=request.getContextPath()%>/oa/oaAssetinformation!oaAssetinformationAdd.do?no="+no+"&assetleftalert="+ev,
         {"no":no},
         function(){
          window.location.href="oa/oaAssetinformation!list.do";
         }
   );

}
</script>
<style type="text/css">
#infoTab { position:relative; height:26px; line-height:23px; border-bottom:1px solid #bbb; overflow:auto; padding-top:1px;}
#infoTab li { cursor:pointer; position:relative; float:left; padding:0 15px; border:1px solid #bbb; margin-right:6px; border-bottom:none; background:#afe7ff; }
#infoView { border:1px solid #bbb; border-top:none; padding:0px 10px 10px 10px; }
#infoTab .select { top:1px; font-weight:bold; cursor:default; }
#infoTab .current { border:1px solid #ff0000; border-bottom:none; color:#ff0000; }
#infoTab .disable { cursor:default; border:1px solid #ddd; border-bottom:none; color:#ddd; font-weight:bold;}
#infoView fieldset { display:none; }
</style>
</head>

<body>
<fieldset class="form">
  <c:if test="${callback ne 'T' }">
		<!-- <legend>
			<p class="ctitle">查看资产信息</p>
		</legend> -->
		<input type="button"
				name="back" Class="btn" onclick="history.back(-1);" value="返回" />
	</c:if>
	<c:if test="${callback eq 'F' }">
	<input type="button" onclick="reEditno('${no }');" value="保存" class="btn"> 
	</c:if>
<%@ include file="/page/common/messages.jsp"%>

<p>	
<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
  
				<tr>
					<td class="addTd">
						资产名称
					</td>
					<td align="left" >
						${cp:MAPVALUE('equipment', datacode) }
					</td>
					<td class="addTd">
						<s:text name="oaAssetinformation.assetnum" />
					</td>
					<td align="left">
						<s:property value="%{assetnum}" />&nbsp;(<s:property value="%{assetunit}" />)
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						<s:text name="oaAssetinformation.creater" />
					</td>
					<td align="left">
						${cp:MAPVALUE('usercode', creater) }
					</td>
					<td class="addTd">
						<s:text name="oaAssetinformation.createtime" />
					</td>
					<td align="left">
					<fmt:formatDate value='${createtime}' pattern='yyyy-MM-dd HH:mm:ss' />
					</td>
				</tr>	

				<tr>
			    	<td class="addTd">
						<s:text name="oaAssetinformation.senddeptype" />
					</td>
					<td align="left">
				    	${cp:MAPVALUE('UnitType', senddeptype) }
					</td>
					<td class="addTd">
						<s:text name="oaAssetinformation.createDepno" />
					</td>
					<td align="left">
					${cp:MAPVALUE('unitcode', createDepno) }
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						<s:text name="oaAssetinformation.assetleftalert" />
					</td>
					<td align="left" colspan="4">
					<c:if test="${callback ne 'F' }">
						<s:property value="%{assetleftalert}" />
					</c:if>
					<c:if test="${callback eq 'F' }">
					<s:textfield name="assetleftalert" value="%{assetleftalert}" id="assetleftalert" />
					</c:if>
					</td>
					<%-- <td class="addTd">
						<s:text name="oaAssetinformation.state" />
					</td>
					<td align="left">
						<s:property value="%{state}" />
					</td> --%>
				</tr>	

				<tr>
				   <td class="addTd">
						<s:text name="oaAssetinformation.createRemark" />
					</td>
					<td align="left" colspan="5">
						<s:property value="%{createRemark}" />
					</td>
					
				</tr>	

</table>
<ul id="infoTab">

	<li url="<%=request.getContextPath()%>/oa/oaAssetinformationInlog!list.do?no=${no}">入库记录</li>
	<li url="<%=request.getContextPath()%>/oa/oaAssetinformationOutlog!list.do?no=${no}&datacode=${object.no}">出库记录</li>

</ul>
 <div id="infoView">
	<iframe id="tabFrames1" name="tabFrames1" src="<%=request.getContextPath()%>/oa/oaAssetinformationInlog!list.do?no=${no}" onload="this.height=this.contentWindow.document.documentElement.scrollHeight+40;setParentHeight(this);" width="100%"
			frameborder="0" scrolling="auto" border="0" marginwidth="0" ></iframe>
    <iframe id="tabFrames2" name="tabFrames2" src="<%=request.getContextPath()%>/oa/oaAssetinformationOutlog!list.do?no=${no}&datacode=${object.no}"  width="100%"
			frameborder="0" scrolling="auto" border="0" marginwidth="0" style="display:none;"></iframe>			
</div> 
</fieldset>

<script type="text/javascript">
var inlogHeight,outlogHeight;
function sNav(){
	$("#infoTab li.current").addClass("select");
	$("#infoTab li").each(function(index){
		$(this).click(function(e){
			if($(this).hasClass("select")){
				return false;
			}
			$("#infoTab li").removeClass("select");
			$(this).addClass("select");
			$("#tabFrames1").attr("index",index);
			if(index==0){
				$("#tabFrames1").show();
				$("#tabFrames2").hide();
			}
			else{
				$("#tabFrames2").show();
				if(!outlogHeight){
				    document.getElementById("tabFrames2").height=window.frames["tabFrames2"].document.body.scrollHeight+40;
				    outlogHeight = document.getElementById("tabFrames2").height;					
				}
				$("#tabFrames1").hide();
			}
			if(outlogHeight){
				var m_height = outlogHeight-inlogHeight;
				if(index==1){
			        parent.document.getElementById("info").height = parseInt(parent.document.getElementById("info").height) + m_height;			
				}
				else{
					parent.document.getElementById("info").height = parseInt(parent.document.getElementById("info").height) - m_height;
				}
			}
		});
	});
}
sNav();	
function setParentHeight(t){
		inlogHeight = document.getElementById("tabFrames1").height;
}
</script>
</body>
</html>
