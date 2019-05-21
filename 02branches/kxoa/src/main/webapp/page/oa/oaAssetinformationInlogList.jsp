<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaAssetinformationInlog.list.title" /></title>
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
			<fieldset
			 class="search">
			<legend>
				 入库记录
			</legend>
			
			<s:form action="oaAssetinformationInlog" namespace="/oa" style="margin-top:0;margin-bottom:5">
				<input type="hidden" name="no" value="${no }"/>
				<table cellpadding="0" cellspacing="0" align="center">
					<tr>
					<td class="addTd">记录时间:</td>
					<td colspan="4">
					<input type="text" class="Wdate"  id="s_begcreatertime" <c:if test="${not empty s_begcreatertime }"> value="${s_begcreatertime}" </c:if>
	                    <c:if test="${empty s_begcreatertime  }">value="${param['s_begcreatertime'] }"</c:if> name="s_begcreatertime"  
	                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})"placeholder="选择日期">
						      至
						<input type="text" class="Wdate"  id="s_endcreatertime" <c:if test="${not empty s_endcreatertime }"> value="${s_endcreatertime }" </c:if>
	                    <c:if test="${empty s_endcreatertime  }">value="${param['s_endcreatertime'] }"</c:if> name="s_endcreatertime" 
	                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})"placeholder="选择日期">
					</td>
				</tr>

					<tr class="searchButton">
					<td colspan="5 "><s:submit method="list" key="opt.btn.query" cssClass="btn" />
					                 <s:hidden name="datacodeType" />
					                 <s:submit method="exportExcelByPo" value="导出资产信息" cssClass="btn btnWide" /> 
				</tr>
				</table>
			</s:form>
		</fieldset> 
		<ec:table action="oa/oaAssetinformationInlog!list.do" items="assetinList"
			var="oaAssetinformationInlog"
			imagePath="${STYLE_PATH}/images/table/*.gif"
			retrieveRowsCallback="limit">
			<input type="hidden" name="no" value="${no }"/>
			<ec:row>
				<%-- 
				<c:set var="tdjid"><s:text name='oaAssetinformationInlog.djid' /></c:set>	
				<ec:column property="djid" title="${tdjid}" style="text-align:center" /> --%>


				<%-- <ec:column property="no" title="是否关联" style="text-align:center;width:8%;" >
				<c:if test="${oaAssetinformationOutlog.assetunit eq 'T'}">
				<img align="middle" alt="已关联" src="${pageContext.request.contextPath}/images/green.gif" />
				</c:if>
				<c:if test="${ oaAssetinformationOutlog.assetunit  ne 'T'}">
				<img align="middle" alt="未关联" src="${pageContext.request.contextPath}/images/red.gif" />
				</c:if>
				</ec:column> --%>
				
				<c:set var="tassetnum">
					进库数量
				</c:set>
				<ec:column property="assetnum" title="${tassetnum}"
					style="text-align:center" />

				<%-- <c:set var="tassetunit">
					<s:text name='oaAssetinformationInlog.assetunit' />
				</c:set>
				<ec:column property="assetunit" title="${tassetunit}"
					style="text-align:center" /> --%>

				<c:set var="tcreater">
					记录者
				</c:set>
				<ec:column property="creater" title="${tcreater}"
					style="text-align:center" >
					${cp:MAPVALUE('usercode', oaAssetinformationInlog.creater) }
                </ec:column>
				<c:set var="tcreatetime">
					记录时间
				</c:set>
				<ec:column property="createtime" title="${tcreatetime}"
					style="text-align:center" >
					<fmt:formatDate value='${oaAssetinformationInlog.createtime}' pattern='yyyy-MM-dd HH:mm:ss' />
                </ec:column>
				 <c:set var="tcreateRemark"><s:text name='oaAssetinformationInlog.createRemark' /></c:set>	
				<ec:column property="createRemark" title="${tcreateRemark}" style="text-align:center" />

				<c:set var="optlabel">
					<s:text name="opt.btn.collection" />
				</c:set>
				<%-- <ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm">
						<s:text name="label.delete.confirm" />
					</c:set>
					<a href='#' onClick="openNewWindow('<%=request.getContextPath()%>/powerruntime/optApply!listSQ.do?s_itemtype=SQ&djid=${oaAssetinformationInlog.djid}','powerWindow',null)">关联事项</a>
					<a
						href='oa/oaAssetinformationInlog!edit.do?djid=${oaAssetinformationInlog.djid}'><s:text
							name="opt.btn.edit" /></a>
					<a
						href='oa/oaAssetinformationInlog!delete.do?djid=${oaAssetinformationInlog.djid}'
						onclick='return confirm("${deletecofirm}oaAssetinformationInlog?");'><s:text
							name="opt.btn.delete" /></a>
				</ec:column>
 --%>
			</ec:row>
		</ec:table>
</body>
</html>
