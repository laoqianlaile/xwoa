<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
	<head>
		<title>
			<s:text name="oaKqreception.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset
			style="border: hidden 1px #000000; ">
			<legend>
				 <s:text name="label.list.filter" />
			</legend>
			
			<s:form action="oaKqreception" namespace="/oa" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">

					<tr >
						<td><s:text name="oaKqreception.djId" />:</td>
						<td><s:textfield name="s_djId" /> </td>
					</tr>	


					<tr >
						<td><s:text name="oaKqreception.transaffairname" />:</td>
						<td><s:textfield name="s_transaffairname" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaKqreception.layoutno" />:</td>
						<td><s:textfield name="s_layoutno" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaKqreception.kqdepname" />:</td>
						<td><s:textfield name="s_kqdepname" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaKqreception.approtime" />:</td>
						<td><s:textfield name="s_approtime" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaKqreception.approvalremark" />:</td>
						<td><s:textfield name="s_approvalremark" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaKqreception.approval" />:</td>
						<td><s:textfield name="s_approval" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaKqreception.approphone" />:</td>
						<td><s:textfield name="s_approphone" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaKqreception.leavetime" />:</td>
						<td><s:textfield name="s_leavetime" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaKqreception.bodycontent" />:</td>
						<td><s:textfield name="s_bodycontent" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaKqreception.lodgingplace" />:</td>
						<td><s:textfield name="s_lodgingplace" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaKqreception.lodgingcase" />:</td>
						<td><s:textfield name="s_lodgingcase" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaKqreception.mealplace" />:</td>
						<td><s:textfield name="s_mealplace" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaKqreception.mealcase" />:</td>
						<td><s:textfield name="s_mealcase" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaKqreception.meetplase" />:</td>
						<td><s:textfield name="s_meetplase" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaKqreception.meetcase" />:</td>
						<td><s:textfield name="s_meetcase" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaKqreception.meetcontent" />:</td>
						<td><s:textfield name="s_meetcontent" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaKqreception.meetnum" />:</td>
						<td><s:textfield name="s_meetnum" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaKqreception.otheritems" />:</td>
						<td><s:textfield name="s_otheritems" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaKqreception.costtotalcapital" />:</td>
						<td><s:textfield name="s_costtotalcapital" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaKqreception.costtotallowcase" />:</td>
						<td><s:textfield name="s_costtotallowcase" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaKqreception.remark" />:</td>
						<td><s:textfield name="s_remark" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaKqreception.creater" />:</td>
						<td><s:textfield name="s_creater" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaKqreception.createtime" />:</td>
						<td><s:textfield name="s_createtime" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaKqreception.flowcode" />:</td>
						<td><s:textfield name="s_flowcode" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaKqreception.flowInstId" />:</td>
						<td><s:textfield name="s_flowInstId" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaKqreception.bizstate" />:</td>
						<td><s:textfield name="s_bizstate" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaKqreception.biztype" />:</td>
						<td><s:textfield name="s_biztype" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaKqreception.solvestatus" />:</td>
						<td><s:textfield name="s_solvestatus" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaKqreception.solvetime" />:</td>
						<td><s:textfield name="s_solvetime" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaKqreception.solvenote" />:</td>
						<td><s:textfield name="s_solvenote" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaKqreception.optId" />:</td>
						<td><s:textfield name="s_optId" /> </td>
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

		<ec:table action="oa/oaKqreception!list.do" items="objList" var="oaKqreception"
			imagePath="${STYLE_PATH}/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:exportXls fileName="oaKqreceptions.xls" ></ec:exportXls>
			<ec:exportPdf fileName="oaKqreceptions.pdf" headerColor="blue" headerBackgroundColor="white" ></ec:exportPdf>
			<ec:row>

				<c:set var="tdjId"><s:text name='oaKqreception.djId' /></c:set>	
				<ec:column property="djId" title="${tdjId}" style="text-align:center" />


				<c:set var="ttransaffairname"><s:text name='oaKqreception.transaffairname' /></c:set>	
				<ec:column property="transaffairname" title="${ttransaffairname}" style="text-align:center" />

				<c:set var="tlayoutno"><s:text name='oaKqreception.layoutno' /></c:set>	
				<ec:column property="layoutno" title="${tlayoutno}" style="text-align:center" />

				<c:set var="tkqdepname"><s:text name='oaKqreception.kqdepname' /></c:set>	
				<ec:column property="kqdepname" title="${tkqdepname}" style="text-align:center" />

				<c:set var="tapprotime"><s:text name='oaKqreception.approtime' /></c:set>	
				<ec:column property="approtime" title="${tapprotime}" style="text-align:center" />

				<c:set var="tapprovalremark"><s:text name='oaKqreception.approvalremark' /></c:set>	
				<ec:column property="approvalremark" title="${tapprovalremark}" style="text-align:center" />

				<c:set var="tapproval"><s:text name='oaKqreception.approval' /></c:set>	
				<ec:column property="approval" title="${tapproval}" style="text-align:center" />

				<c:set var="tapprophone"><s:text name='oaKqreception.approphone' /></c:set>	
				<ec:column property="approphone" title="${tapprophone}" style="text-align:center" />

				<c:set var="tleavetime"><s:text name='oaKqreception.leavetime' /></c:set>	
				<ec:column property="leavetime" title="${tleavetime}" style="text-align:center" />

				<c:set var="tbodycontent"><s:text name='oaKqreception.bodycontent' /></c:set>	
				<ec:column property="bodycontent" title="${tbodycontent}" style="text-align:center" />

				<c:set var="tlodgingplace"><s:text name='oaKqreception.lodgingplace' /></c:set>	
				<ec:column property="lodgingplace" title="${tlodgingplace}" style="text-align:center" />

				<c:set var="tlodgingcase"><s:text name='oaKqreception.lodgingcase' /></c:set>	
				<ec:column property="lodgingcase" title="${tlodgingcase}" style="text-align:center" />

				<c:set var="tmealplace"><s:text name='oaKqreception.mealplace' /></c:set>	
				<ec:column property="mealplace" title="${tmealplace}" style="text-align:center" />

				<c:set var="tmealcase"><s:text name='oaKqreception.mealcase' /></c:set>	
				<ec:column property="mealcase" title="${tmealcase}" style="text-align:center" />

				<c:set var="tmeetplase"><s:text name='oaKqreception.meetplase' /></c:set>	
				<ec:column property="meetplase" title="${tmeetplase}" style="text-align:center" />

				<c:set var="tmeetcase"><s:text name='oaKqreception.meetcase' /></c:set>	
				<ec:column property="meetcase" title="${tmeetcase}" style="text-align:center" />

				<c:set var="tmeetcontent"><s:text name='oaKqreception.meetcontent' /></c:set>	
				<ec:column property="meetcontent" title="${tmeetcontent}" style="text-align:center" />

				<c:set var="tmeetnum"><s:text name='oaKqreception.meetnum' /></c:set>	
				<ec:column property="meetnum" title="${tmeetnum}" style="text-align:center" />

				<c:set var="totheritems"><s:text name='oaKqreception.otheritems' /></c:set>	
				<ec:column property="otheritems" title="${totheritems}" style="text-align:center" />

				<c:set var="tcosttotalcapital"><s:text name='oaKqreception.costtotalcapital' /></c:set>	
				<ec:column property="costtotalcapital" title="${tcosttotalcapital}" style="text-align:center" />

				<c:set var="tcosttotallowcase"><s:text name='oaKqreception.costtotallowcase' /></c:set>	
				<ec:column property="costtotallowcase" title="${tcosttotallowcase}" style="text-align:center" />

				<c:set var="tremark"><s:text name='oaKqreception.remark' /></c:set>	
				<ec:column property="remark" title="${tremark}" style="text-align:center" />

				<c:set var="tcreater"><s:text name='oaKqreception.creater' /></c:set>	
				<ec:column property="creater" title="${tcreater}" style="text-align:center" />

				<c:set var="tcreatetime"><s:text name='oaKqreception.createtime' /></c:set>	
				<ec:column property="createtime" title="${tcreatetime}" style="text-align:center" />

				<c:set var="tflowcode"><s:text name='oaKqreception.flowcode' /></c:set>	
				<ec:column property="flowcode" title="${tflowcode}" style="text-align:center" />

				<c:set var="tflowInstId"><s:text name='oaKqreception.flowInstId' /></c:set>	
				<ec:column property="flowInstId" title="${tflowInstId}" style="text-align:center" />

				<c:set var="tbizstate"><s:text name='oaKqreception.bizstate' /></c:set>	
				<ec:column property="bizstate" title="${tbizstate}" style="text-align:center" />

				<c:set var="tbiztype"><s:text name='oaKqreception.biztype' /></c:set>	
				<ec:column property="biztype" title="${tbiztype}" style="text-align:center" />

				<c:set var="tsolvestatus"><s:text name='oaKqreception.solvestatus' /></c:set>	
				<ec:column property="solvestatus" title="${tsolvestatus}" style="text-align:center" />

				<c:set var="tsolvetime"><s:text name='oaKqreception.solvetime' /></c:set>	
				<ec:column property="solvetime" title="${tsolvetime}" style="text-align:center" />

				<c:set var="tsolvenote"><s:text name='oaKqreception.solvenote' /></c:set>	
				<ec:column property="solvenote" title="${tsolvenote}" style="text-align:center" />

				<c:set var="toptId"><s:text name='oaKqreception.optId' /></c:set>	
				<ec:column property="optId" title="${toptId}" style="text-align:center" />
		
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='oa/oaKqreception!view.do?djId=${oaKqreception.djId}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text name="opt.btn.view" /></a>
					<a href='oa/oaKqreception!edit.do?djId=${oaKqreception.djId}'><s:text name="opt.btn.edit" /></a>
					<a href='oa/oaKqreception!delete.do?djId=${oaKqreception.djId}' 
							onclick='return confirm("${deletecofirm}oaKqreception?");'><s:text name="opt.btn.delete" /></a>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
