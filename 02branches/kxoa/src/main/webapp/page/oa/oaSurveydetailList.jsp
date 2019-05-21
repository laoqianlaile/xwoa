<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
		<title>
			<s:text name="oaSurveydetail.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset
			style="border: hidden 1px #000000; ">
		<%-- 	<legend>
				 <s:text name="label.list.filter" />
			</legend> --%>
			
		   
			<s:form action="oaSurveydetail" namespace="/oa" style="margin-top:0;margin-bottom:5">
		
	   <table cellpadding="0" cellspacing="0" align="center">
       <s:hidden id="s_type" name="s_type" value="%{s_type}"></s:hidden>
	   <s:hidden id="s_djid" name="s_djid" value="%{s_djid}"></s:hidden>
	   
	    
      <div>
			
				<c:if test="${not empty oaSurvey}">${oaSurvey.title }</c:if><input type="button" name="back" Class="btn" onclick="history.back(-1);" value="返回" />
		</div>
					<%-- <tr >
						<td><s:text name="oaSurveydetail.itemid" />:</td>
						<td><s:textfield name="s_itemid" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaSurveydetail.creater" />:</td>
						<td><s:textfield name="s_creater" /> </td>
					</tr>	


					<tr >
						<td><s:text name="oaSurveydetail.no" />:</td>
						<td><s:textfield name="s_no" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaSurveydetail.title" />:</td>
						<td><s:textfield name="s_title" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaSurveydetail.djid" />:</td>
						<td><s:textfield name="s_djid" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaSurveydetail.createtime" />:</td>
						<td><s:textfield name="s_createtime" /> </td>
					</tr>	

					<tr>
						<td>
							<s:submit method="list"  key="opt.btn.query" cssClass="btn"/>
						</td>
						<td>
							<s:submit method="built"  key="opt.btn.new" cssClass="btn"/>
						</td>
					</tr> --%>
				</table>
			</s:form>
		</fieldset>

		<ec:table action="oa/oaSurveydetail!list.do" items="infoList" var="userinfo"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>

				<%-- <c:set var="titemid"><s:text name='oaSurveydetail.itemid' /></c:set>	
				<ec:column property="itemid" title="${titemid}" style="text-align:center" /> --%>

				<c:set var="tcreater"><s:text name='oaSurveydetail.creater' /></c:set>	
				<ec:column property="usercode" title="${tcreater}" style="text-align:center" >
				<c:if test="${'Y' eq oaSurvey.isbookn}">
				${cp:MAPVALUE("usercode",userinfo.usercode) }
				</c:if>
				<c:if test="${'N' eq oaSurvey.isbookn}">
			          匿名[${ROWCOUNT}]
				</c:if>
				</ec:column>


			<%-- 	<c:set var="tno"><s:text name='oaSurveydetail.no' /></c:set>	
				<ec:column property="no" title="${tno}" style="text-align:center" />

				<c:set var="ttitle"><s:text name='oaSurveydetail.title' /></c:set>	
				<ec:column property="title" title="${ttitle}" style="text-align:center" />

				<c:set var="tdjid"><s:text name='oaSurveydetail.djid' /></c:set>	
				<ec:column property="djid" title="${tdjid}" style="text-align:center" />

				<c:set var="tcreatetime"><s:text name='oaSurveydetail.createtime' /></c:set>	
				<ec:column property="createtime" title="${tcreatetime}" style="text-align:center" /> --%>
		
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
 				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<a href='oa/oaSurveydetail!surveyDetailViewList.do?s_djid=${s_djid}&s_creater=${userinfo.usercode}'><s:text name="opt.btn.view" /></a>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
