<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaAssetinformation.list.title" /></title>
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset class="search">
		<legend>
		    <c:if test="${isDept eq 'T' }">${cp:MAPVALUE('UnitType', isDept) }</c:if>
		     <c:if test="${isDept ne 'T' }">${cp:MAPVALUE('unitcode', unitcode) }</c:if>
		</legend>

		<s:form action="oaAssetinformation" namespace="/oa"
			style="margin-top:0;margin-bottom:5">
			<input type="hidden" name="s_datacode" value="${datacodeType }" />
			<input type="hidden" name="hasChildNode" value="${hasChildNode }" />
			
			<table cellpadding="0" cellspacing="0" align="center">
				<tr>
					<%-- <td><s:text name="oaAssetinformation.no" />:</td>
						<td><s:textfield name="s_no" /> </td> --%>
					<td class="addTd">资产名称:</td>
					<td ><select name="s_datacode">
							<option value="">---请选择---</option>
							<c:forEach items="${cp:LVB('equipment')}" var="v">
								<option value="${v.value }"
									<c:if test="${v.value eq s_datacode }">selected="selected" </c:if>>${v.label
									}</option>
							</c:forEach>
					</select></td>
					<td class="addTd">启用状态:</td>
					<td ><select name="s_state">
							<option value="">---请选择---</option>
							<c:forEach items="${cp:LVB('zcstate')}" var="v">
								<option value="${v.value }"
									<c:if test="${v.value eq s_state }">selected="selected" </c:if>>${v.label
									}</option>
							</c:forEach>
					</select></td>
				<tr>
					<td class="addTd">记录时间:</td>
					<td>
					<input type="text" class="Wdate"  id="s_begcreatertime" <c:if test="${not empty s_begcreatertime }"> value="${s_begcreatertime}" </c:if>
	                    <c:if test="${empty s_begcreatertime  }">value="${param['s_begcreatertime'] }"</c:if> name="s_begcreatertime"  
	                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})"placeholder="选择日期">
						      至
						<input type="text" class="Wdate"  id="s_endcreatertime" <c:if test="${not empty s_endcreatertime }"> value="${s_endcreatertime }" </c:if>
	                    <c:if test="${empty s_endcreatertime  }">value="${param['s_endcreatertime'] }"</c:if> name="s_endcreatertime" 
	                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})"placeholder="选择日期">
					</td>
					<c:if test="${isDept eq 'T' }">
					<td class="addTd">所属机构:</td>
					<td><select name="s_createDepno">
							<option value="">---请选择---</option>
							<c:forEach items="${unitlist}" var="u">
								<option value="${u.unitcode }"
									<c:if test="${s_createDepno eq u.unitcode }">selected="selected"</c:if>>${u.unitname
									}</option>
							</c:forEach>
					</select></td>
					</c:if>
				</tr>


				<tr class="searchButton">
					<td colspan="5 "><s:submit method="list" key="opt.btn.query" cssClass="btn" />
					              <c:if test="${hasChildNode  ne 'Y' }">  
					                 <s:submit method="built" key="opt.btn.new" cssClass="btn" />
					              </c:if>
					                 <s:hidden name="datacodeType" />
					                 <s:submit method="exportExcelByPo" value="导出资产信息" cssClass="btn btnWide" /> 
				</tr>
			</table>
		</s:form>
	</fieldset>

	<ec:table action="oa/oaAssetinformation!list.do" items="objList"
		var="oaAssetinformation" imagePath="${STYLE_PATH}/images/table/*.gif"
		retrieveRowsCallback="limit">
		<ec:row>
			<c:set var="tdatacode">资产名称</c:set>
			<ec:column property="datacode" title="${tdatacode}"
				style="text-align:center">
				<a href='oa/oaAssetinformation!viewInfo.do?no=${oaAssetinformation.no}'>${cp:MAPVALUE('equipment',
					oaAssetinformation.datacode) }</a>
			</ec:column>

			<c:set var="tassetnum">
				<s:text name='oaAssetinformation.assetnum' />
			</c:set>
			<ec:column property="assetnum" title="${tassetnum}"
				style="text-align:center" >
				<c:if test="${oaAssetinformation.assetnum >5&&oaAssetinformation.assetnum<=10}">
				<img align="middle" alt="提醒" src="${pageContext.request.contextPath}/images/green.gif" />${oaAssetinformation.assetnum}
				</c:if>
				<c:if test="${ oaAssetinformation.assetnum <=5}">
				<img align="middle" alt="预警" src="${pageContext.request.contextPath}/images/red.gif" />${oaAssetinformation.assetnum}
				</c:if>
		    </ec:column>
			<c:set var="tcreateDepno">
				<s:text name='oaAssetinformation.createDepno' />
			</c:set>
			<ec:column property="createDepno" title="${tcreateDepno}"
				style="text-align:center">
				${cp:MAPVALUE('unitcode', oaAssetinformation.createDepno) }
				</ec:column>
			<c:set var="tcreatetime">
				<s:text name='oaAssetinformation.createtime' />
			</c:set>
			<ec:column property="createtime" title="${tcreatetime}"
				style="text-align:center">
				<fmt:formatDate value='${oaAssetinformation.createtime}'
					pattern='yyyy-MM-dd HH:mm:ss' />
			</ec:column>
			<c:set var="tsenddeptype">
				<s:text name='oaAssetinformation.senddeptype' />
			</c:set>
			<ec:column property="senddeptype" title="${tsenddeptype}"
				style="text-align:center">
				${cp:MAPVALUE('UnitType', oaAssetinformation.senddeptype) }
				</ec:column>
			<c:set var="tassetleftalert">
				<s:text name='oaAssetinformation.assetleftalert' />
			</c:set>
			<ec:column property="assetleftalert" title="${tassetleftalert}"
				style="text-align:center" >
            </ec:column>
			<c:set var="optlabel">
				<s:text name="opt.btn.collection" />
			</c:set>
			<ec:column property="state" title="启用状态"
				style="text-align:center">
				${cp:MAPVALUE('zcstate', oaAssetinformation.state) }
				</ec:column>
			<ec:column property="opt" title="${optlabel}" sortable="false"
				style="text-align:center">
				<c:set var="deletecofirm">
					<s:text name="label.delete.confirm" />
				</c:set>
				<c:if test="${oaAssetinformation.state eq 'T' }">
				<a
					href='oa/oaAssetinformation!view.do?no=${oaAssetinformation.no}&callback=F'>编辑</a>
				<a
					href='oa/oaAssetinformationInlog!edit.do?datacode=${oaAssetinformation.datacode}&no=${oaAssetinformation.no}'>进库</a>
				<a
					href='oa/oaAssetinformationOutlog!edit.do?datacode=${oaAssetinformation.datacode}&no=${oaAssetinformation.no}&isDept=${isDept}'>出库</a>
					<a
					href='oa/oaAssetinformation!oaAssetinformationStart.do?no=${oaAssetinformation.no}&s_state=D'>停用</a>
				</c:if>
				<c:if test="${oaAssetinformation.state eq 'D' }">
				 <a
					href='oa/oaAssetinformation!view.do?no=${oaAssetinformation.no}&callback=F'>编辑</a>
				<a
					href='oa/oaAssetinformation!oaAssetinformationStart.do?datacode=${oaAssetinformation.datacode}&no=${oaAssetinformation.no}&s_state=T'>启用</a>
				</c:if>
				<c:if test="${oaAssetinformation.state eq 'F'  }">
				    <a
					href='oa/oaAssetinformation!view.do?no=${oaAssetinformation.no}&callback=F'>编辑</a>
					<a
					href='oa/oaAssetinformation!oaAssetinformationStart.do?no=${oaAssetinformation.no}&s_state=T'>启用</a>
					<c:if test="${oaAssetinformation.hasOutIn ne 'T'  }">
					<a
					href='oa/oaAssetinformation!delete.do?no=${oaAssetinformation.no}'>删除</a>
					</c:if>
				</c:if>
			</ec:column>

		</ec:row>
	</ec:table>

</body>
</html>
