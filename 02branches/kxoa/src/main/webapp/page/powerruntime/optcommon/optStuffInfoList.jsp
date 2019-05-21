<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
	<head>
		<title>
			<s:text name="optStuffInfo.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset>
			<legend>
				 <s:text name="label.list.filter" />
			</legend>
			
			<s:form action="optStuffInfo" namespace="/powerruntime" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">

					<tr >
						<td><s:text name="optStuffInfo.stuffid" />:</td>
						<td><s:textfield name="s_stuffid" /> </td>
					</tr>	


					<tr >
						<td><s:text name="optStuffInfo.djId" />:</td>
						<td><s:textfield name="s_djId" /> </td>
					</tr>	

					<tr >
						<td><s:text name="optStuffInfo.stuffname" />:</td>
						<td><s:textfield name="s_stuffname" /> </td>
					</tr>	

					<tr >
						<td><s:text name="optStuffInfo.stuffcontent" />:</td>
						<td><s:textfield name="s_stuffcontent" /> </td>
					</tr>	

					<tr >
						<td><s:text name="optStuffInfo.iszhi" />:</td>
						<td><s:textfield name="s_iszhi" /> </td>
					</tr>	

					<tr >
						<td><s:text name="optStuffInfo.filename" />:</td>
						<td><s:textfield name="s_filename" /> </td>
					</tr>	

					<tr >
						<td><s:text name="optStuffInfo.nodeinstid" />:</td>
						<td><s:textfield name="s_nodeinstid" /> </td>
					</tr>	

					<tr >
						<td><s:text name="optStuffInfo.uploadtime" />:</td>
						<td><s:textfield name="s_uploadtime" /> </td>
					</tr>	

					<tr >
						<td><s:text name="optStuffInfo.uploadusercode" />:</td>
						<td><s:textfield name="s_uploadusercode" /> </td>
					</tr>	

					<tr >
						<td><s:text name="optStuffInfo.nodename" />:</td>
						<td><s:textfield name="s_nodename" /> </td>
					</tr>	

					<tr >
						<td><s:text name="optStuffInfo.filetype" />:</td>
						<td><s:textfield name="s_filetype" /> </td>
					</tr>	

					<tr >
						<td><s:text name="optStuffInfo.archivetype" />:</td>
						<td><s:textfield name="s_archivetype" /> </td>
					</tr>	

					<tr>
						<td>
							<s:submit method="list"  key="opt.btn.query" cssClass="btn"/>
						</td>
						<td>
							<s:submit method="built"  key="opt.btn.new" cssClass="btn"/>
						</td>
					</tr>
				</table>
			</s:form>
		</fieldset>

		<ec:table action="powerruntime/optStuffInfo!list.do" items="objList" var="optStuffInfo"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:exportXls fileName="optStuffInfos.xls" ></ec:exportXls>
			<ec:exportPdf fileName="optStuffInfos.pdf" headerColor="blue" headerBackgroundColor="white" ></ec:exportPdf>
			<ec:row>

				<c:set var="tstuffid"><s:text name='optStuffInfo.stuffid' /></c:set>	
				<ec:column property="stuffid" title="${tstuffid}" style="text-align:center" />


				<c:set var="tdjId"><s:text name='optStuffInfo.djId' /></c:set>	
				<ec:column property="djId" title="${tdjId}" style="text-align:center" />

				<c:set var="tstuffname"><s:text name='optStuffInfo.stuffname' /></c:set>	
				<ec:column property="stuffname" title="${tstuffname}" style="text-align:center" />

				<c:set var="tstuffcontent"><s:text name='optStuffInfo.stuffcontent' /></c:set>	
				<ec:column property="stuffcontent" title="${tstuffcontent}" style="text-align:center" />

				<c:set var="tiszhi"><s:text name='optStuffInfo.iszhi' /></c:set>	
				<ec:column property="iszhi" title="${tiszhi}" style="text-align:center" />

				<c:set var="tfilename"><s:text name='optStuffInfo.filename' /></c:set>	
				<ec:column property="filename" title="${tfilename}" style="text-align:center" />

				<c:set var="tnodeinstid"><s:text name='optStuffInfo.nodeinstid' /></c:set>	
				<ec:column property="nodeinstid" title="${tnodeinstid}" style="text-align:center" />

				<c:set var="tuploadtime"><s:text name='optStuffInfo.uploadtime' /></c:set>	
				<ec:column property="uploadtime" title="${tuploadtime}" style="text-align:center" />

				<c:set var="tuploadusercode"><s:text name='optStuffInfo.uploadusercode' /></c:set>	
				<ec:column property="uploadusercode" title="${tuploadusercode}" style="text-align:center" />

				<c:set var="tnodename"><s:text name='optStuffInfo.nodename' /></c:set>	
				<ec:column property="nodename" title="${tnodename}" style="text-align:center" />

				<c:set var="tfiletype"><s:text name='optStuffInfo.filetype' /></c:set>	
				<ec:column property="filetype" title="${tfiletype}" style="text-align:center" />

				<c:set var="tarchivetype"><s:text name='optStuffInfo.archivetype' /></c:set>	
				<ec:column property="archivetype" title="${tarchivetype}" style="text-align:center" />
		
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='powerruntime/optStuffInfo!view.do?stuffid=${optStuffInfo.stuffid}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text name="opt.btn.view" /></a>
					<a href='powerruntime/optStuffInfo!edit.do?stuffid=${optStuffInfo.stuffid}'><s:text name="opt.btn.edit" /></a>
					<a href='powerruntime/optStuffInfo!delete.do?stuffid=${optStuffInfo.stuffid}' 
							onclick='return confirm("${deletecofirm}optStuffInfo?");'><s:text name="opt.btn.delete" /></a>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
