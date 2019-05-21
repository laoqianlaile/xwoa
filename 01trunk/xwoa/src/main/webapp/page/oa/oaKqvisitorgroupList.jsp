<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
	<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />
	<!-- 新样式文件 -->
	<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />		
</head>
<body>
<fieldset class="search">
			<legend class="headTitle">
				来访客团管理
			</legend>
			<div class="searchDiv">
			<s:form id="oaKqvisitorgroupForm" action="oaKqvisitorgroup" namespace="/oa" style="margin-top:0;margin-bottom:5" method="post"
			data-changeSubmit="true">
			<input type="hidden" id="showTag" name="showTag" value="${showTag}" />
			<s:hidden id="hid_ids" name="ids" />
			 <div class="searchArea">
				<table style="width: auto;">
				<tr>				
					<td class="searchTitleArea" >
					<label class="searchCondTitle">接待部门:</label>
					</td>
					<td class="searchCountArea">
					<input type="text" class="searchCondContent" name="s_kqdepname" value="${s_kqdepname }" /> 
					</td>
					<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
					<td>
					<label class="searchCondTitle">接待日期:</label>
					</td>
					<td class="searchCountArea" colspan="4">
					<input type="text" class="Wdate searchCondContent"  id="s_endTime" <c:if test="${not empty s_begTime }"> value="${s_begTime}" </c:if>
	                            <c:if test="${empty s_begTime  }">value="${param['s_begTime'] }"</c:if> name="s_begTime"  
	                            onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})"placeholder="选择日期">
	                                                     至 <input type="text" class="Wdate" id="s_endTime"
						<c:if test="${not empty s_endTime }"> value="${s_endTime }" </c:if>
						<c:if test="${empty s_endTime  }"> value="${param['s_endTime'] }" </c:if>
						name="s_endTime"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">
	                            
	                            <input id="gaoji" type="button" class="grayBtnWide" onclick="showgaoji()">
								<input id="shouqi" type="button" class="grayBtnWide grayBtnWide_sq" style="display: none;" onclick="toshouqi()">	
	                        </td>
						<td class="searchCondArea">
							<s:submit method="list" key="opt.btn.query" cssClass="btn" />
							<s:submit method="edit" key="opt.btn.new" cssClass="btn" />
							<s:submit method="exportExcelKqv" value="导出Excel"
							cssClass="btn btnWide" /> 
						</td>
					</tr>
				<tr id="gaoji_more" style="display: none;">
				<td class="searchTitleArea" >
					<label class="searchCondTitle">接待类型:</label>
					</td>
					<td class="searchCountArea">
					<select
						id="s_jdtype" name="s_jdtype" class="searchCondContent"
						style="width: 158px;">
							<option value="">全部</option>
							<c:forEach var="row" items="${cp:DICTIONARY('jdType')}">
								<option value="${row.datacode}"
									<c:if test="${row.datacode == s_jdtype}"> selected="selected"</c:if>
									>
									<c:out value="${row.datavalue}" />
								</option>
							</c:forEach>
					</select> 
					</td>
				</tr>
				
			</table>
			</div>	
			</s:form>
			</div>
		</fieldset>

	<ec:table
		action="${pageContext.request.contextPath}/oa/oaKqvisitorgroup!list.do"
		items="objList" var="foaKqvisitorgroup"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
		<ec:row>
	<%-- 		<c:if test="${'F' ne showTag}">
				<ec:column property="none"
					title='<input id="chk_all" type="checkbox"  value="全选" name="quanxuan"  />'
					sortable="false" style="2%">
						<input class="chk_one" type="checkbox"
							id="${foaKqvisitorgroup.djId}" class="ecbox"
							value="${foaKqvisitorgroup.djId}">
				</ec:column>
			</c:if> --%>
			<ec:column property="approvalremark" title="接待对象"
				style="text-align:center;" width="30%">
					<c:choose>
						<c:when test="${fn:length(foaKqvisitorgroup.approvalremark) >18}">
							<c:out
								value="${fn:substring(foaKqvisitorgroup.approvalremark, 0, 18)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${foaKqvisitorgroup.approvalremark}" />
						</c:otherwise>
					</c:choose>
				</ec:column>	
			<%-- <ec:column property="approvalremark" title="接待对象"
				style="text-align:center" />	
			<ec:column property="approval" title="人数"
				style="text-align:center" /> --%>
			<ec:column property="jdtype" title="接待类型"
				style="text-align:center">
				${cp:MAPVALUE("jdType",foaKqvisitorgroup.jdtype)}
				</ec:column>	
			<ec:column property="kqdepname" title="接待部门"
				style="text-align:center" />	
			<ec:column property="meetplase" title="接待人"
				style="text-align:center" />						
			<%-- <ec:column property="approtime" title="日期"
				format="yyyy-MM-dd HH:mm" style="text-align:center" /> --%>
			<ec:column property="approtime" title="日期" style="text-align:center" sortable="false" >
				<fmt:formatDate value='${foaKqvisitorgroup.approtime}' pattern='yyyy-MM-dd' />
			 </ec:column>
			<c:set var="optlabel">
				<s:text name="opt.btn.collection" />
			</c:set>
			<ec:column property="opt" title="${optlabel}" sortable="false"
				style="text-align:center">
				<c:if test="${'F' ne showTag}">

					<a
						href="${pageContext.request.contextPath}/oa/oaKqvisitorgroup!view.do?djId=${foaKqvisitorgroup.djId}">
						查看</a>
					<a
						href="${pageContext.request.contextPath}/oa/oaKqvisitorgroup!edit.do?djId=${foaKqvisitorgroup.djId}">
						编辑</a>
					<a class="delete" onclick='return confirm("确定要删除吗?");'
						href="${pageContext.request.contextPath}/oa/oaKqvisitorgroup!delete.do?djId=${foaKqvisitorgroup.djId}">
						删除</a>
				</c:if>
			</ec:column>
		</ec:row>
	</ec:table>
			
</body>

<script type="text/javascript">
	function recipient(){
		
	}
	function sub(){
			$("#oaKqvisitorgroupForm").attr("action","oaKqvisitorgroup!list.do");
			$("#oaKqvisitorgroupForm").submit();
	}
	function showgaoji(){
		$("#shouqi").show();
		$("#gaoji_more").show();
		$("#gaoji").hide();
	}
	function toshouqi(){
		$("#shouqi").hide();
		$("#gaoji_more").hide();
		$("#gaoji").show();
	}
	function gj(){
		var t=false;
		if($("#s_jdtype").val().trim()!=""&&$("#s_jdtype").val()!=null){
			t=true;
		}
		return t;
	}
	 $(function(){
			if(gj()){
				showgaoji();
			}
			//让滚动条至于顶部
			$(top.window.document).find("#firstPage").scrollTop(0);
		});
	</script>
	</html>