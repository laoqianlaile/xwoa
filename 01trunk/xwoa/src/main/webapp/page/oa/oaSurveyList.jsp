<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaSurvey.list.title" /></title>
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset  class="search">
		<legend>
			<c:choose>
			<c:when test="${s_type eq 'mgr' }">调查管理</c:when>
			<c:when test="${s_type eq 'vote' }">调查投票</c:when>
			<c:when test="${s_type eq 'new' }">新建调查</c:when>
			</c:choose>
		</legend>

		<s:form action="oaSurvey" namespace="/oa"
			style="margin-top:0;margin-bottom:5">
			<table cellpadding="0" cellspacing="0" align="center">
				<s:hidden id="s_type" name="s_type" value="%{s_type}"></s:hidden>
				<tr>
					<td class="addTd"><s:text name="oaSurvey.title" />:</td>
					<td><s:textfield name="s_title" value="%{#parameters['s_title']}" /></td>
					<td class="addTd">调查开始时间:</td>
					<td><input type="text" class="Wdate"
						value="${s_begtimeBegin}" id="s_begtimeBegin"
						name="s_begtimeBegin"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期" />
						至 <input type="text" class="Wdate"
						value="${s_begtimeEnd}" id="s_begtimeEnd" name="s_begtimeEnd"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期" /></td>
				</tr>
				<tr>
					<td class="addTd"><s:text name="oaSurvey.reType" />:</td>
					<td><select name="s_reType" id="s_reType">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${oaSurveyTypeList}">
								<option value="${row.no}"
									<c:if test="${s_reType eq row.no }">selected="selected"</c:if>>
									<c:out value="${row.reType}" />
								</option>
							</c:forEach>
					</select></td>
					
				
				<td class="addTd">调查结束时间:</td>
					<td><input type="text" class="Wdate"
						value="${s_endtimeBegin}" id="s_endtimeBegin"
						name="s_endtimeBegin"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期" />
						至 <input type="text" class="Wdate"
						class="addTd"
						value="${s_endtimeEnd}" id="s_endtimeEnd" name="s_endtimeEnd"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期" />
					</td>
				</tr>
				<tr  class="searchButton" >
					<td colspan="4"><s:submit method="list" key="opt.btn.query" cssClass="btn" onclick="return checkFrom();"/>
					
					<c:if test="${'new' eq s_type }">
						<s:submit method="built" key="opt.btn.new" cssClass="btn" />
						
					</c:if>
					</td>
				</tr>
			</table>
		</s:form>
	</fieldset>

	<ec:table action="oa/oaSurvey!list.do" items="objList" var="oaSurvey"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
		<ec:row>

			<%-- 				<c:set var="tdjid"><s:text name='oaSurvey.djid' /></c:set>	 --%>
			<%-- 				<ec:column property="djid" title="${tdjid}" style="text-align:center" /> --%>


			<c:set var="ttitle">
				<s:text name='oaSurvey.title' />
			</c:set>
			<ec:column property="title" title="${ttitle}"
				style="text-align:center">
				<c:choose>
					<c:when test="${fn:length(title) > 20}">
						<c:out value="${fn:substring(oaSurvey.title, 0, 20)}..." />
					</c:when>
					<c:otherwise>
						<c:out value="${oaSurvey.title}" />
					</c:otherwise>
				</c:choose>
			</ec:column>

			<c:set var="treType">
				<s:text name='oaSurvey.reType' />
			</c:set>
			<ec:column property="none" title="${treType}"
				style="text-align:center">
				${oaSurvey.oaSurveyType.reType }
			
				</ec:column>

			<%-- 				<c:set var="tremark"><s:text name='oaSurvey.remark' /></c:set>	 --%>
			<%-- 				<ec:column property="remark" title="${tremark}" style="text-align:center" /> --%>

			<%-- 				<c:set var="tbegtime"><s:text name='oaSurvey.begtime' /></c:set>	 --%>
			<ec:column property="begtime" title="调查时间" style="text-align:center">
				<fmt:formatDate value='${oaSurvey.begtime}'
					pattern='yyyy-MM-dd  HH:mm' />-
				<fmt:formatDate value='${oaSurvey.endtime}'
					pattern='yyyy-MM-dd  HH:mm' />
			</ec:column>

			<%-- 				<c:set var="tendtime"><s:text name='oaSurvey.endtime' /></c:set>	 --%>
			<%-- 				<ec:column property="endtime" title="${tendtime}" style="text-align:center" /> --%>

			<%-- 				<c:set var="tcreater"><s:text name='oaSurvey.creater' /></c:set>	 --%>
			<%-- 				<ec:column property="creater" title="${tcreater}" style="text-align:center" /> --%>

			<c:set var="tcreatetime">
				<s:text name='oaSurvey.createtime' />
			</c:set>
			<ec:column property="createtime" title="${tcreatetime}"
				style="text-align:center">
				<fmt:formatDate value='${oaSurvey.createtime}'
					pattern='yyyy-MM-dd  HH:mm' />
			</ec:column>

			<%-- 				<c:set var="tcreateRemark"><s:text name='oaSurvey.createRemark' /></c:set>	 --%>
			<%-- 				<ec:column property="createRemark" title="${tcreateRemark}" style="text-align:center" /> --%>

			<c:set var="tcreateDepno">
				<s:text name='oaSurvey.createDepno' />
			</c:set>
			<ec:column property="createDepno" title="${tcreateDepno}"
				style="text-align:center">
				${cp:MAPVALUE("unitcode",oaSurvey.createDepno) }
				</ec:column>



			<c:set var="tthesign">
				<s:text name='oaSurvey.thesign' />
			</c:set>
			<ec:column property="thesign" title="${tthesign}"
				style="text-align:center">
				${cp:MAPVALUE("OATheSign",oaSurvey.thesign) }
				</ec:column>

			<%-- 				<c:set var="tsendusers"><s:text name='oaSurvey.sendusers' /></c:set>	 --%>
			<%-- 				<ec:column property="sendusers" title="${tsendusers}" style="text-align:center" /> --%>

			<%-- 				<c:set var="tisautoend"><s:text name='oaSurvey.isautoend' /></c:set>	 --%>
			<%-- 				<ec:column property="isautoend" title="${tisautoend}" style="text-align:center" /> --%>

			<%-- 				<c:set var="tisviewresult"><s:text name='oaSurvey.isviewresult' /></c:set>	 --%>
			<%-- 				<ec:column property="isviewresult" title="${tisviewresult}" style="text-align:center" /> --%>

			<c:set var="tisbookn">
				<s:text name='oaSurvey.isbookn' />
			</c:set>
			<ec:column property="isbookn" title="${tisbookn}"
				style="text-align:center">
				${cp:MAPVALUE("OAIsbookn",oaSurvey.isbookn) }
				</ec:column>


			<c:set var="optlabel">
				<s:text name="opt.btn.collection" />
			</c:set>
			<ec:column property="opt" title="${optlabel}" sortable="false"
				style="text-align:center">
				<c:set var="deletecofirm">
					<s:text name="label.delete.confirm" />
				</c:set>


				<c:if test="${'new' eq s_type }">
					<%-- 					<a href='${contextPath }/oa/oaSurvey!view.do?djid=${oaSurvey.djid}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text name="opt.btn.view" /></a> --%>

					<a
						href='${contextPath }/oa/oaOnlineItem!list.do?s_djid=${oaSurvey.djid}&s_type=${s_type}&s_itemType=preList'>预览</a>
					<a
						href='${contextPath }/oa/oaOnlineItem!list.do?s_djid=${oaSurvey.djid}&s_type=${s_type}&s_itemType=list'>设置题目</a>
					<a
						href='${contextPath }/oa/oaSurvey!edit.do?djid=${oaSurvey.djid}&s_type=${s_type}'><s:text
							name="opt.btn.edit" /></a>
					<c:if test="${'1' eq oaSurvey.thesign }">
						<a
							href='${contextPath }/oa/oaSurvey!updateSign.do?djid=${oaSurvey.djid}&s_type=${s_type}&thesign=2'
							onclick='return confirm("确认发布该调查?");'>发布</a>
					</c:if>
					<a
						href='${contextPath }/oa/oaSurvey!updateSign.do?djid=${oaSurvey.djid}&s_type=${s_type}&thesign=4'
						onclick='return confirm("${deletecofirm}该调查?");'><s:text
							name="opt.btn.delete" /></a>
				</c:if>

				<c:if test="${'mgr' eq s_type }">
					<a
						href='${contextPath }/oa/oaOnlineItem!list.do?s_djid=${oaSurvey.djid}&s_type=${s_type}&s_itemType=tjList'>查看投票统计</a>
					<a
						href='${contextPath }/oa/oaSurveydetail!detailList.do?s_djid=${oaSurvey.djid}'>投票详情</a>
					<c:if test="${'3' ne oaSurvey.thesign }">
						<a
							href='${contextPath }/oa/oaOnlineItem!list.do?s_djid=${oaSurvey.djid}&s_type=${s_type}&s_itemType=preList'>预览</a>
						
						<a
							href='${contextPath }/oa/oaSurvey!updateSign.do?djid=${oaSurvey.djid}&s_type=${s_type}&thesign=3'
							onclick='return confirm("确认结束该调查?");'>结束调查</a>
					</c:if>
					<a
							href='${contextPath }/oa/oaSurvey!edit.do?djid=${oaSurvey.djid}&s_type=${s_type}&s_rEdit=T'
							onclick='return confirm("确认重新发布该调查?");'>重新发布</a>
					<a
						href='${contextPath }/oa/oaSurvey!updateSign.do?djid=${oaSurvey.djid}&s_type=${s_type}&thesign=4'
						onclick='return confirm("${deletecofirm}该调查?");'><s:text
							name="opt.btn.delete" /></a>
					
				<%-- 	----暂时打开
					<a
						href='${contextPath }/oa/oaOnlineItem!list.do?s_djid=${oaSurvey.djid}&s_type=${s_type}&s_itemType=list'>设置题目</a>
					<a
						href='${contextPath }/oa/oaSurvey!edit.do?djid=${oaSurvey.djid}&s_type=${s_type}'><s:text
							name="opt.btn.edit" /></a> --%>
				</c:if>

				<c:if test="${'vote' eq s_type}">
					<c:if test="${'Y' eq  oaSurvey.isviewresult}">
						<a
							href='${contextPath }/oa/oaOnlineItem!list.do?s_djid=${oaSurvey.djid}&s_type=${s_type}&s_itemType=tjList'>查看投票统计</a>
					</c:if>

					<a
						href='${contextPath }/oa/oaOnlineItem!list.do?s_djid=${oaSurvey.djid}&s_type=${s_type}&s_itemType=surList'>
						<c:if
							test="${'T' eq oaSurvey.canVote}">投票</c:if>
							<c:if test="${'F' eq oaSurvey.canVote}">已投票</c:if></a>

					<c:if test="${'Y'eq isviewresult}">
						<a
							href='${contextPath }/oa/oaSurvey!view.do?s_djid=${oaSurvey.djid}'>查看投票结果</a>
					</c:if>
				</c:if>
			</ec:column>

		</ec:row>
	</ec:table>

</body>
<script type="text/javascript">
	function checkFrom(){
		var begD = $("#s_endtimeBegin").val();
		var endD = $("#s_endtimeEnd").val();
		if(endD!=""&&begD>endD){
			alert("结束时间不能早于开始时间。");
// 			$("#s_endTime").focus();
			return false;
		}
		var begB = $("#s_begtimeBegin").val();
		var endB = $("#s_begtimeEnd").val();
		if(endB!=""&&begB>endB){
			alert("结束时间不能早于开始时间。");
// 			$("#s_endTime").focus();
			return false;
		}
		return true;
	}
	</script>
</html>
