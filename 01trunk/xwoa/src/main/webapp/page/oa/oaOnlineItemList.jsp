<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaOnlineItem.list.title" /></title>
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset style="border: hidden 1px #000000;">
		<%-- <legend>
			<s:text name="label.list.filter" />
		</legend> --%>

		<s:form action="oaOnlineItem" namespace="/oa"
			style="margin-top:0;margin-bottom:5">
<!-- 			<table cellpadding="0" cellspacing="0" align="center"> -->
				<input type="hidden" id="s_djid" value="${s_djid }" name="s_djid">
				<input type="hidden" id="s_type" value="${s_type }" name="s_type">
				<input type="hidden" id="s_itemType" value="${s_itemType }" name="s_itemType">
				<%-- 			<tr >
						<td><s:text name="oaOnlineItem.no" />:</td>
						<td><s:textfield name="s_no" /> </td>
					</tr>	


					<tr >
						<td><s:text name="oaOnlineItem.djid" />:</td>
						<td><s:textfield name="s_djid" /> </td>
					</tr>	 --%>

			<%-- 	<tr>
					<td><s:text name="oaOnlineItem.chosetype" />:</td>
					<td>
					<select
						name="s_chosetype" class="combox" id="s_chosetype" style="width: 150px;">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('OAChoseType')}">
								<option value="${row.datacode}"
									<c:if test="${s_chosetype eq row.datacode }">selected="selected"</c:if>>
									<c:out value="${row.datavalue}" />
								</option>
							</c:forEach>
					</select>
					</td>
				</tr>

				<tr>
					<td><s:text name="oaOnlineItem.title" />:</td>
					<td><s:textfield name="s_title" /></td>
				</tr> --%>

				<%-- <tr>
					<td><s:text name="oaOnlineItem.itemnames" />:</td>
					<td><s:textfield name="s_itemnames" /></td>
				</tr>

				<tr>
					<td><s:text name="oaOnlineItem.chosenum" />:</td>
					<td><s:textfield name="s_chosenum" /></td>
				</tr>

				<tr>
					<td><s:text name="oaOnlineItem.limitnum" />:</td>
					<td><s:textfield name="s_limitnum" /></td>
				</tr>
 --%>
				<%-- <tr>
					<td><s:text name="oaOnlineItem.thesign" />:</td>
					<td>
					<select
						name="s_thesign" class="combox" id="s_thesign" style="width: 150px;">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('OAIsOrNo')}">
								<option value="${row.datacode}"
									<c:if test="${s_thesign eq row.datacode }">selected="selected"</c:if>>
									<c:out value="${row.datavalue}" />
								</option>
							</c:forEach>
					</select>
					
					</td>
				</tr>
 --%>
<!-- 				<tr> -->
<!-- 					<td> -->
					<c:if test="${not empty oaSurvey}">${oaSurvey.title }</c:if> 
					<input type="button" name="back" Class="btn" 
				    onclick="javascript:window.location.href='${contextPath }/oa/oaOnlineItem!built.do?s_djid=${oaSurvey.djid}&s_type=${s_type}&s_itemType=${s_itemType}';" value="新增题目" />
					<input type="button" name="back" Class="btn"
				    onclick="javascript:window.location.href='${contextPath }/oa/oaOnlineItem!previewList.do?s_djid=${oaSurvey.djid}&s_type=${s_type}&s_itemType=${s_itemType}';" value="预览" />
<!-- 					</td> -->
<!-- 				</tr> -->
<!-- 			</table> -->
		</s:form>
	</fieldset>

	<ec:table action="oa/oaOnlineItem!list.do" items="objList"
		var="oaOnlineItem" imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
		
		<ec:row>
        <ec:column property="none"
				title='序号'
				sortable="false" style="width:70px;text-align:right;">
                  ${ROWCOUNT}.
		</ec:column>



			<%-- <c:set var="tno">
				<s:text name='oaOnlineItem.no' />
			</c:set>
			<ec:column property="no" title="${tno}" style="text-align:center" />


			<c:set var="tdjid">
				<s:text name='oaOnlineItem.djid' />
			</c:set>
			<ec:column property="djid" title="${tdjid}" style="text-align:center" /> --%>

			<c:set var="tchosetype">
				<s:text name='oaOnlineItem.chosetype' />
			</c:set>
			<ec:column property="chosetype" title="${tchosetype}"
				style="text-align:center" >
				${cp:MAPVALUE('OAChoseType', oaOnlineItem.chosetype) }
				</ec:column>

			<c:set var="ttitle">
				<s:text name='oaOnlineItem.title' />
			</c:set>
			<ec:column property="title" title="${ttitle}"
				style="text-align:center" />

			<c:set var="titemnames">
				<s:text name='oaOnlineItem.itemnames' />
			</c:set>
			<ec:column property="itemnames" title="${titemnames}"
				style="text-align:center" />

			<%-- <c:set var="tchosenum">
				<s:text name='oaOnlineItem.chosenum' />
			</c:set>
			<ec:column property="chosenum" title="${tchosenum}"
				style="text-align:center" /> --%>

			<c:set var="tlimitnum">
				<s:text name='oaOnlineItem.limitnum' />
			</c:set>
			<ec:column property="limitnum" title="${tlimitnum}"
				style="text-align:center" />

			<c:set var="tthesign">
				<s:text name='oaOnlineItem.thesign' />
			</c:set>
			<ec:column property="thesign" title="${tthesign}"
				style="text-align:center" >
				${cp:MAPVALUE('OAIsOrNo', oaOnlineItem.thesign) }
				</ec:column>

			<c:set var="optlabel">
				<s:text name="opt.btn.collection" />
			</c:set>
			<ec:column property="opt" title="${optlabel}" sortable="false"
				style="text-align:center">
				<c:set var="deletecofirm">
					<s:text name="label.delete.confirm" />
				</c:set>
				<a
					href='${contextPath }/oa/oaOnlineItem!previewList.do?no=${oaOnlineItem.no}&s_djid=${s_djid}&s_no=${oaOnlineItem.no}&ec_p=${ec_p}&ec_crd=${ec_crd}'>预览</a>
				<a href='${contextPath }/oa/oaOnlineItem!edit.do?no=${oaOnlineItem.no}&s_djid=${s_djid}'><s:text
						name="opt.btn.edit" /></a>
				<a href='${contextPath }/oa/oaOnlineItem!delete.do?no=${oaOnlineItem.no}&s_djid=${s_djid}'
					onclick='return confirm("${deletecofirm}该记录?");'><s:text
						name="opt.btn.delete" /></a>
				<%-- <a
					href='oa/oaOnlineItem!view.do?no=${oaOnlineItem.no}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text
						name="opt.btn.view" /></a> --%>
			</ec:column>

		</ec:row>
	</ec:table>

</body>
</html>
