<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/themes/css/icon-css.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css"
	rel="stylesheet" type="text/css" />
<!-- 新样式文件 -->
<link
	href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<fieldset class="search">
		<legend class="headTitle"> 党费收缴管理 </legend>
		<div class="searchDiv">
			<s:form id="oaDuescollectionForm" action="oaDuescollection"
				namespace="/oa" style="margin-top:0;margin-bottom:5" method="post"
				data-changeSubmit="true">
				<input type="hidden" id="showTag" name="showTag" value="${showTag}" />
				<s:hidden id="hid_ids" name="ids" />
				<div class="searchArea">
					<table style="width: auto;">
						<tr>
							<td class="searchTitleArea"><label class="searchCondTitle">标题:</label>
							</td>
							<td class="searchCountArea"><input type="text"
								class="searchCondContent" id="s_transaffairname"
								name="s_transaffairname" value="${s_transaffairname }" /></td>
							<td class="fenggexian" style="padding-left: 0; padding-right: 0">
							</td>
							<td><label class="searchCondTitle">截止日期:</label></td>
							<td class="searchCountArea" colspan="4"><input type="text"
								class="Wdate searchCondContent" id="s_endTime"
								<c:if test="${not empty s_begTime }"> value="${s_begTime}" </c:if>
								<c:if test="${empty s_begTime  }">value="${param['s_begTime'] }"</c:if>
								name="s_begTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})"
								placeholder="选择日期"> 至 <input type="text" class="Wdate"
								id="s_endTime"
								<c:if test="${not empty s_endTime }"> value="${s_endTime }" </c:if>
								<c:if test="${empty s_endTime  }"> value="${param['s_endTime'] }" </c:if>
								name="s_endTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})"
								placeholder="选择日期"> <input id="gaoji" type="button"
								class="grayBtnWide" onclick="showgaoji()"> <input
								id="shouqi" type="button" class="grayBtnWide grayBtnWide_sq"
								style="display: none;" onclick="toshouqi()"></td>
							<td class="searchCondArea"><s:submit method="list"
									key="opt.btn.query" cssClass="btn" /> <s:submit method="edit"
									key="opt.btn.new" cssClass="btn" />
									</td>
						</tr>
						<tr id="gaoji_more" style="display: none;">
							<td class="searchTitleArea"><label class="searchCondTitle">状态:</label>
							</td>
							<td class="searchCountArea"><select id="s_isfinish"
								name="s_isfinish" class="searchCondContent"
								style="width: 158px;">
									<option value=""
										<c:if test="${empty s_isfinish }"> selected="selected"</c:if>>全部</option>
									<option value="T"
										<c:if test="${'T' eq s_isfinish }"> selected="selected"</c:if>>已完成</option>
									<option value="F"
										<c:if test="${'F' eq s_isfinish }"> selected="selected"</c:if>>进行中</option>
							</select></td>
						</tr>

					</table>
				</div>
			</s:form>
		</div>
	</fieldset>


	<ec:table action="oa/oaDuescollection!list.do" items="objList"
		var="oaDuescollection" imagePath="${STYLE_PATH}/images/table/*.gif"
		retrieveRowsCallback="limit">
		<ec:row>
			<ec:column property="transaffairname" title="标题"
				style="text-align:center">
			<c:choose>
						<c:when test="${fn:length(oaDuescollection.transaffairname) >28}">
							<c:out
								value="${fn:substring(oaDuescollection.transaffairname, 0, 28)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${oaDuescollection.transaffairname}" />
						</c:otherwise>
					</c:choose>	
				</ec:column>
			<ec:column property="endtime" title="截止日期" style="text-align:center"
				sortable="false">
				<fmt:formatDate value='${ oaDuescollection.endtime }'
					pattern='yyyy-MM-dd' />
			</ec:column>
			<ec:column property="finflag" title="截止状态" style="text-align:center" />

			<ec:column property="isfinish" title="收缴状态" style="text-align:center">
				<c:if test="${'T' eq oaDuescollection.isfinish }">
					<span class="icon-gren" title="已完成"></span>已完成</c:if>
				<c:if
					test="${'F' eq oaDuescollection.isfinish or empty oaDuescollection.isfinish}">
					<span class="icon-redd" title="进行中"></span>进行中</c:if>
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
					href='oa/oaDuescollection!manage.do?djId=${oaDuescollection.djId}'>收缴记录</a>
				<a href='oa/oaDuescollection!edit.do?djId=${oaDuescollection.djId}'><s:text
						name="opt.btn.edit" /></a>
				<a
					href='oa/oaDuescollection!delete.do?djId=${oaDuescollection.djId}'
					onclick='return confirm("${deletecofirm}?");'><s:text
						name="opt.btn.delete" /></a>
			</ec:column>
		</ec:row>
	</ec:table>
</body>
<script type="text/javascript">
	function showgaoji() {
		$("#shouqi").show();
		$("#gaoji_more").show();
		$("#gaoji").hide();
	}
	function toshouqi() {
		$("#shouqi").hide();
		$("#gaoji_more").hide();
		$("#gaoji").show();
	}
	$(function() {
		if (gj()) {
			showgaoji();
		}
		//让滚动条至于顶部
		$(top.window.document).find("#firstPage").scrollTop(0);
	});
	function gj() {
		var t = false;
		if ($("#s_isfinish").val().trim() != ""
				&& $("#s_isfinish").val() != null) {
			t = true;
		}
		return t;
	}
</script>
</html>

