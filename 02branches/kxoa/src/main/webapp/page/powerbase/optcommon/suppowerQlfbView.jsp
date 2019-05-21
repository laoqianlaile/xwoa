<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<%@page import="com.centit.powerbase.po.Suppower"%>
<%@ page import="java.util.List"%>

<html>
<%-- <sj:head locale="zh_CN" /> --%>
		<title>
			权力发布
		</title>
<script src="${pageContext.request.contextPath}/scripts/suggest.js" type="text/javascript"></script>
	</head>

	<body>
<%@ include file="/page/common/messages.jsp"%>
	<s:form action="supPower" method="SuppowerQlfbSave" namespace="/powerbase" id="suppowerForm" enctype="multipart/form-data">
		<input id="version" type="hidden" name="version" value="${object.version}" />
		<input id="itemId" type="hidden" name="itemId" value="${object.itemId}" />
		<input id="orgId" type="hidden" name="orgId" value="${object.orgId}" />
		<input id="itemType" type="hidden" name="itemType" value="${object.itemType}" />
<fieldset>
<legend><b>权力事项详细信息</b></legend>
		<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<tr>
				<td class="addTd" width="130">权力编码</td>
				<td colspan="3">
				<a
					href='powerbase/suppowerchglog!listVersion.do?itemId=${object.itemId}&version=${object.version}'><s:property value="%{object.itemId}" /></a>
				</td>
				</tr>
				<tr>
				<td class="addTd" width="130">权力事项名称</td>
				<td colspan="3"><s:property value="%{object.itemName}" /></td>
				</tr>
				<tr>
				<td class="addTd" width="130">行使部门</td>
				<td >${cp:MAPVALUE("depno",object.orgId)}</td>
				<td class="addTd" width="130">权力类型</td>
				<td >${cp:MAPVALUE("ITEM_TYPE",object.itemType)}</td>
				</tr>
			<%-- <tr>
				<td class="addTd"><s:text name="suppower.inFlowImg" /></td>
				<td align="left"><a
					href='supPower!downloadStuff.do?itemId=${object.itemId}&version=${object.version}&fileType=inFlowImg_'>
						${object.inFlowImgName}
				</a></td>
				<td class="addTd"><s:text name="suppower.inFlowInfo" /></td>
				<td align="left"><c:if test="${!empty object.inFlowInfo}">
	          <input type="button" name="btnblc" value="流程查看" onclick="openFlow();" class="btn" />
   				<input type="hidden" id="xmlDate" value="${object.inFlowXml}" />
               </c:if>&nbsp;</td>
			</tr>
			<tr>
				<td class="addTd"><s:text name="suppower.processDesc" /></td>
				<td align="left"><a
					href='supPower!downloadStuff.do?itemId=${object.itemId}&version=${object.version}&fileType=processDesc_'>
						${object.fileName}
				</a></td>
				<td class="addTd"><s:text name="suppower.transactDepname" /></td>
				<td align="left">${object.transactDepname}</td>
			</tr> --%>
			<tr>
				<td class="addTd"><s:text name="suppower.qlDepid" /></td>
				<td align="left">
					${cp:MAPVALUE("depno",object.qlDepid)}
				</td>
				<td class="addTd"><s:text name="suppower.qlDepstate" /></td>
				<td align="left">
					${cp:MAPVALUE("QL_DepState",object.qlDepstate)}
				</td>
			</tr>
			</table>
		</fieldset>
		<fieldset class="form">
			<legend><b>权力事项发布</b></legend>
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<tr>
					<td class="addTd" ><font color="red"><strong>*&nbsp;</strong></font>生效时间</td>	
				<td align="left">
<%-- 				<sj:datepicker id="beginTime" name="beginTime"  style="width:140px" --%>
<%-- 			      yearRange="2000:2020" timepickerFormat="hh:mm:ss" displayFormat="yy-mm-dd" 
					changeYear="true"  timepicker="true" --%>
<%-- 			    value="%{object.beginTime}"/> --%>
	  <input type="text" class="Wdate"  id="beginTime" name="beginTime" 
	  value="<fmt:formatDate value='${object.beginTime}' pattern='yyyy-MM-dd  HH:mm:ss' />" 
      onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'2000-01-01 00:00:00',maxDate:'2020-12-31 23:59:59'})"
	  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss '})" placeholder="选择日期">
		       </td>
				</tr>
				<tr align="center">
				<td align="center" colspan="3">
			<div class="formButton">	
			<s:submit name="save" method="SuppowerQlfbSave" cssClass="btn" key="发布" onclick="return checkForm();" />
			<input type="button" class="btn" value="返回" onclick="javascript:history.go(-1);" />
			</div>
			</td>
			</tr>
		</table>
	  </fieldset>
	</s:form>
	</body>
	<script type="text/javascript">
	function checkForm(){
		if($("#beginTime").val()==""){
			alert("生效时间不可为空");
			return false;
			
		}
		return true;
	}
	
	function selDegree(){
	     var form=document.getElementById("lkmform");
	     form.action="punishobjectbasic!seldegreefacilitydes.do";     
	     form.submit();
	}
	function openFlow(){
		var url="${pageContext.request.contextPath}/page/powerbase/innerflow/svg/readXml.html";
		window.open(url);
	}

	</script>
</html>
