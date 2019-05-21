<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>

<html>

<head>
<meta name="decorator" content='${LAYOUT}' />
<title><c:out value="数据交换" /></title>
<script type="text/javascript"
	src="<c:url value='/scripts/datepicker/WdatePicker.js'/>"></script>

</head>
<body>
	<%@ include file="/page/common/messages.jsp"%>
	
	<div id="mydiv">
	
	<fieldset>
		<legend> 数据导出 </legend>
		<s:form action="dataChange" namespace="/dispatchdoc" id="exportDataForm" 
			style="margin-top:0;margin-bottom:5">
			<table cellpadding="0" cellspacing="0" align="center">
				<tr height="22">
					<td>
						<%-- 需要导出的申报数据一共有 ${needUploadArchives}  条;
						需要导出的用户一共有 ${needUploadUsers}  位。 --%>
						<%@ include file="/page/dispatchdoc/datachange1.jsp"%>
					</td>
					<td>
						<s:submit method="doexport" cssClass="btn" value="导出数据"/>
					</td>
				</tr>
			</table>
			
		</s:form>
		</fieldset>
		<%-- <div class="eXtremeTable">
			<table id="ec_t_expdata" border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
				<thead>
					<tr>
					<td width="50%" class="tableHeader">导出时间</td>   
					<td width="20%" class="tableHeader">导出人</td>  
					<td width="30%" class="tableHeader">下载</td>  
					</tr>  
				</thead>
				
				<tbody class="tableBody" >
				<c:set value="odd" var="rownum" />
				
				<c:forEach var="expfile" items="${expfiles}">    
		
					<tr class="${rownum}"  onmouseover="this.className='highlight'"  onmouseout="this.className='${rownum}'" >
						<td align="center"><fmt:formatDate value ='${expfile.recordDate}' type="both" dateStyle="long" timeStyle="long" /></td>   
						<td align="center"><c:out value="${cp:MAPVALUE('userCode',expfile.recorder)}"/></td>   
						<td align="center">
							<c:if test="${expfile.inDb eq '0'}" >
								正在导出......
							</c:if>
							<c:if test="${expfile.inDb ne '0'}" >
								<a href='dataChange!downloadexp.do?fileCode=${expfile.fileCode}'>数据</a>
								<a href='dataChange!downloadexplog.do?fileCode=${expfile.fileCode}'>日志</a>
							</c:if>
						</td>  
					</tr>  
		
		          <c:set value="${rownum eq 'odd'? 'even': 'odd'}" var="rownum" />
		          
				</c:forEach> 
				</tbody>        
			</table>
		</div>	 --%>	
		
	<div class="eXtremeTable">	
	<%@ include file="/page/dispatchdoc/datachange2.jsp"%>
	</div>
	
	
	
	<fieldset style=" display:block; margin-top:20px;">
		<legend>数据导入</legend>
		<s:form action="dataChange" namespace="/dispatchdoc" method="POST"
			style="margin-top:0;margin-bottom:5" id="importDataForm"  enctype="multipart/form-data">
			<table cellpadding="0" cellspacing="0" align="center">
				<tr height="22">
					<td class="addTd">选择文件</td>
					<td><s:file name="importFile" /></td>	
					<td><s:submit method="doimport" cssClass="btn" value="上传结果" /></td>
				</tr>
			</table>
		</s:form>
		</fieldset>
		<%-- <div class="eXtremeTable" >
			<table id="ec_t_impdata" border="0" cellspacing="0" cellpadding="0" class="tableRegion" width="100%" >
		
				<thead>
					<tr>
					<td width="50%" class="tableHeader">上传时间</td>   
					<td width="20%" class="tableHeader">上传人员</td>  
					<td width="30%" class="tableHeader">操作</td>  
					</tr>  
				</thead>
				
				<tbody class="tableBody" >
				<c:set value="odd" var="rownum" />
				
				<c:forEach var="impfile" items="${impfiles}">    
					<tr class="${rownum}"  onmouseover="this.className='highlight'"  onmouseout="this.className='${rownum}'" >
						<td align="center"><fmt:formatDate value ='${impfile.recordDate}' type="both" dateStyle="long" timeStyle="long"/></td>   
						<td align="center"><c:out value="${cp:MAPVALUE('userCode',impfile.recorder)}"/></td>   
						<td align="center">
							<c:if test="${expfile.inDb eq '0'}" >
								正在导入......
							</c:if>
							<c:if test="${expfile.inDb ne '0'}" >
								<a href='dataChange!downloadexp.do?fileCode=${impfile.fileCode}'>数据</a>
								<a href='dataChange!downloadexplog.do?fileCode=${impfile.fileCode}'>日志</a>
							</c:if>
						</td>  
					</tr>  
		
		          <c:set value="${rownum eq 'odd'? 'even': 'odd'}" var="rownum" />
		          
				</c:forEach> 
				</tbody>        
			</table>
		</div>	 --%>
		
		<div class="eXtremeTable">	
		<%@ include file="/page/dispatchdoc/datachange3.jsp"%>
		</div>
		
	</div>
</body>



</html>