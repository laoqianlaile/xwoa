<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<%@page import="com.centit.powerbase.po.Suppower"%>
<%@ page import="java.util.List"%>
<html>
	<head>
		<title>
			权力库查看
		</title>
		<%-- <sj:head locale="zh_CN" /> --%>
		
		
	</head>
	<body class="sub-flow">
<%@ include file="/page/common/messages.jsp"%>
	<input type="button" class="btn" value="返回" onclick="javascript:history.go(-1);" />
<fieldset> 
<legend><b>事项详细信息</b></legend>
	<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
			<tr>
				<td class="addTd"><s:text name="suppower.itemId" /></td>
				<td align="left"><s:property value="%{itemId}" /></td>
		
				<td class="addTd">版本号</td>
				<td align="left"><s:property value="%{version}" /></td>
			</tr>
			<tr>
				<td class="addTd">启用时间</td>
				<td align="left"><fmt:formatDate value='${sup.beginTime}' pattern='yyyy-MM-dd HH:mm' /></td>
		
				<td class="addTd">本版本停用时间</td>
				<td align="left"><fmt:formatDate value='${sup.endTime}' pattern='yyyy-MM-dd HH:mm' /></td>
			</tr>
			<tr>
				<td class="addTd"><s:text name="suppower.itemName" /></td>
				<td align="left" colspan="3">${sup.itemName}</td>
			</tr>

			<tr>
				<td class="addTd">权力类型</td>
				<td align="left">
					${cp:MAPVALUE("ITEM_TYPE",sup.itemType)}
				</td>
				<td class="addTd">行使部门</td>
				<td align="left">${cp:MAPVALUE("depno",sup.orgId)}</td>
			</tr>
			
			<tr>
				<td class="addTd">事项类型</td>
				<td align="left">
					${cp:MAPVALUE("optItemType",sup.optItemType)}
				</td>
				<td class="addTd">公开方式</td>
				<td align="left">${cp:MAPVALUE("optOpenStyle",sup.optOpenStyle)}</td>
			</tr>
			<tr>
				<td class="addTd"><s:text name="suppower.isNetwork" /></td>
				<td align="left" colspan="4">
				<c:if
						test='${ sup.isNetwork==\"1\"}'>否</c:if> <c:if
						test='${ sup.isNetwork==\"0\"}'>是</c:if>
				</td>
			</tr>
			<tr>
			   <td class="addTd">实施依据</td>
			    <td  colspan="3">${sup.actualDependent}  </td>
			</tr>			
			<tr>
				<td class="addTd"><s:text name="suppower.qlState" /></td>
				<td align="left">
					${cp:MAPVALUE("QL_State",sup.qlState)}
				</td>
			
				<td class="addTd"><s:text name="suppower.lastmodifytime" /></td>
				<td align="left"><fmt:formatDate value='${sup.lastmodifytime}' pattern='yyyy-MM-dd HH:mm' /></td>
			</tr>
		</table>
		</fieldset>
		<c:if test="${not empty vPowerUserInfo.wfcode}">
		<fieldset>
		<legend><b>事项流程图</b></legend>
		<iframe id="basicWF" name="wf" width="100%" height="" frameborder="no" scrolling="false" border="0" marginwidth="0" marginheight="0"
				src="" onload="this.height=window.frames['wf'].document.body.scrollHeight">
		</iframe>
		</fieldset>
		</c:if>
		
	</body>
	<script type="text/javascript">
	function selDegree(){
	     var form=document.getElementById("lkmform");
	     form.action="punishobjectbasic!seldegreefacilitydes.do";     
	     form.submit();
	}
	function showBlan(item_id){
		var url = "./itemComparisonFrame.jsp?systemType=ApplyJcFlow&item_id="+item_id+"&version="+version;
	    window.open(url);
	}
	function openFlow(){
		var url="${pageContext.request.contextPath}/page/powerbase/innerflow/svg/readXml.html";
		window.open(url);
	}
	
	$(document).ready(function() {
		var objfram=document.getElementById('basicWF');
		if(objfram){
		if($.browser.msie && $.browser.version<9 ){
			objfram.src= '${pageContext.request.contextPath}/sampleflow/sampleFlowDefine!viewflowxml.do?flowCode=${vPowerUserInfo.wfcode}&version=${vPowerUserInfo.wfVersion}&flag=ie';
		}else{		
		    objfram.src= '${pageContext.request.contextPath}/sampleflow/sampleFlowDefine!viewflowxml.do?flowCode=${vPowerUserInfo.wfcode}&version=${vPowerUserInfo.wfVersion}&flag=notie';
	    }
		}
	   // alert(objfram.src);
		});
	</script>
</html>
