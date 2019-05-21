<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
	<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
		<title>
			<s:text name="oaMeetingmaterialinfos.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset
			style="border: hidden 1px #000000; ">
			<legend>
				 <s:text name="label.list.filter" />
			</legend>
			
			<s:form action="oaMeetingmaterialinfos" namespace="/oa" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">

					<tr >
						<td><s:text name="oaMeetingmaterialinfos.djId" />:</td>
						<td><s:textfield name="s_djId" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaMeetingmaterialinfos.meetingAttendee" />:</td>
						<td><s:textfield name="s_meetingAttendee" /> </td>
					</tr>	


					<tr >
						<td><s:text name="oaMeetingmaterialinfos.createtime" />:</td>
						<td><s:textfield name="s_createtime" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaMeetingmaterialinfos.isback" />:</td>
						<td><s:textfield name="s_isback" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaMeetingmaterialinfos.backtime" />:</td>
						<td><s:textfield name="s_backtime" /> </td>
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

		<ec:table action="oa/oaMeetingmaterialinfos!list.do" items="objList" var="oaMeetingmaterialinfos"
			imagePath="${STYLE_PATH}/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:exportXls fileName="oaMeetingmaterialinfoss.xls" ></ec:exportXls>
			<ec:exportPdf fileName="oaMeetingmaterialinfoss.pdf" headerColor="blue" headerBackgroundColor="white" ></ec:exportPdf>
			<ec:row>

				<c:set var="tdjId"><s:text name='oaMeetingmaterialinfos.djId' /></c:set>	
				<ec:column property="djId" title="${tdjId}" style="text-align:center" />

				<c:set var="tmeetingAttendee"><s:text name='oaMeetingmaterialinfos.meetingAttendee' /></c:set>	
				<ec:column property="meetingAttendee" title="${tmeetingAttendee}" style="text-align:center" />


				<c:set var="tcreatetime"><s:text name='oaMeetingmaterialinfos.createtime' /></c:set>	
				<ec:column property="createtime" title="${tcreatetime}" style="text-align:center" />

				<c:set var="tisback"><s:text name='oaMeetingmaterialinfos.isback' /></c:set>	
				<ec:column property="isback" title="${tisback}" style="text-align:center" />

				<c:set var="tbacktime"><s:text name='oaMeetingmaterialinfos.backtime' /></c:set>	
				<ec:column property="backtime" title="${tbacktime}" style="text-align:center" />
		
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='oa/oaMeetingmaterialinfos!view.do?djId=${oaMeetingmaterialinfos.djId}&meetingAttendee=${oaMeetingmaterialinfos.meetingAttendee}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text name="opt.btn.view" /></a>
					<a href='oa/oaMeetingmaterialinfos!edit.do?djId=${oaMeetingmaterialinfos.djId}&meetingAttendee=${oaMeetingmaterialinfos.meetingAttendee}'><s:text name="opt.btn.edit" /></a>
					<a href='oa/oaMeetingmaterialinfos!delete.do?djId=${oaMeetingmaterialinfos.djId}&meetingAttendee=${oaMeetingmaterialinfos.meetingAttendee}' 
							onclick='return confirm("${deletecofirm}oaMeetingmaterialinfos?");'><s:text name="opt.btn.delete" /></a>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
