<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
		<title>
			<s:text name="oaAssetinformationOutlog.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset  class="search">
			<legend>
				出库记录
			</legend>
			
			<s:form action="oaAssetinformationOutlog" namespace="/oa" style="margin-top:0;margin-bottom:5">
				<input type="hidden" name="no" value="${no }"/>
				<input type="hidden" name="isDept" value="${isDept }"/>
				<table cellpadding="0" cellspacing="0" align="center">
				<%-- 	<tr >
						<td><s:text name="oaAssetinformationOutlog.djid" />:</td>
						<td><s:textfield name="s_djid" /> </td>
					</tr>	


					<tr >
						<td><s:text name="oaAssetinformationOutlog.no" />:</td>
						<td><s:textfield name="s_no" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaAssetinformationOutlog.applyuser" />:</td>
						<td><s:textfield name="s_applyuser" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaAssetinformationOutlog.applyUnitcode" />:</td>
						<td><s:textfield name="s_applyUnitcode" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaAssetinformationOutlog.applydate" />:</td>
						<td><s:textfield name="s_applydate" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaAssetinformationOutlog.assetnum" />:</td>
						<td><s:textfield name="s_assetnum" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaAssetinformationOutlog.assetunit" />:</td>
						<td><s:textfield name="s_assetunit" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaAssetinformationOutlog.creater" />:</td>
						<td><s:textfield name="s_creater" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaAssetinformationOutlog.createtime" />:</td>
						<td><s:textfield name="s_createtime" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaAssetinformationOutlog.createRemark" />:</td>
						<td><s:textfield name="s_createRemark" /> </td>
					</tr>	 --%>
					<tr>
					<td class="addTd">领用时间:</td>
					<td>
					<input type="text" class="Wdate"  id="s_begcreatertime" <c:if test="${not empty s_begcreatertime }"> value="${s_begcreatertime}" </c:if>
	                    <c:if test="${empty s_begcreatertime  }">value="${param['s_begcreatertime'] }"</c:if> name="s_begcreatertime"  
	                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})"placeholder="选择日期">
						      至
						<input type="text" class="Wdate"  id="s_endcreatertime" <c:if test="${not empty s_endcreatertime }"> value="${s_endcreatertime }" </c:if>
	                    <c:if test="${empty s_endcreatertime  }">value="${param['s_endcreatertime'] }"</c:if> name="s_endcreatertime" 
	                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})"placeholder="选择日期">
					</td>
					<td class="addTd">出库部门:</td>
					<td><select name="s_applyUnitcode">
							<option value="">---请选择---</option>
							<c:forEach items="${unitlist}" var="u">
								<option value="${u.unitcode }"
									<c:if test="${s_applyUnitcode eq u.unitcode }">selected="selected"</c:if>>${u.unitname
									}</option>
							</c:forEach>
					</select></td>
				</tr>

					<tr class="searchButton">
					<td colspan="5 "><s:submit method="list" key="opt.btn.query" cssClass="btn" />
					                 <s:hidden name="datacodeType" />
					                 <s:submit method="exportExcelByPo" value="导出资产信息" cssClass="btn btnWide" /> 
				</tr>
				</table>
			</s:form>
		</fieldset> 
         
		<ec:table action="oa/oaAssetinformationOutlog!list.do" items="assetinList" var="oaAssetinformationOutlog"
			imagePath="${STYLE_PATH}/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>
<%-- 
				<c:set var="tdjid"><s:text name='oaAssetinformationOutlog.djid' /></c:set>	
				<ec:column property="djid" title="${tdjid}" style="text-align:center" />
 --%>

				<%-- <ec:column property="no" title="是否关联" style="text-align:center;width:8%;" >
				<c:if test="${oaAssetinformationOutlog.assetunit eq 'T'}">
				<img align="middle" alt="已关联" src="${pageContext.request.contextPath}/images/green.gif" />
				</c:if>
				<c:if test="${ oaAssetinformationOutlog.assetunit  ne 'T'}">
				<img align="middle" alt="未关联" src="${pageContext.request.contextPath}/images/red.gif" />
				</c:if>
				</ec:column> --%>

				<c:set var="tapplyUnitcode">出库部门</c:set>	
				<ec:column property="applyUnitcode" title="${tapplyUnitcode}" style="text-align:center" >
				${cp:MAPVALUE('unitcode', oaAssetinformationOutlog.applyUnitcode) }
				</ec:column>
				
				<c:set var="tapplyuser">领用者</c:set>	
				<ec:column property="applyuser" title="${tapplyuser}" style="text-align:center" >
				${cp:MAPVALUE('usercode', oaAssetinformationOutlog.applyuser) }
				</ec:column>

				<%-- <c:set var="tapplydate"><s:text name='oaAssetinformationOutlog.applydate' /></c:set>	
				<ec:column property="applydate" title="${tapplydate}" style="text-align:center" >
				<fmt:formatDate value='${oaAssetinformationOutlog.applydate}' pattern='yyyy-MM-dd HH:mm:ss' />
				</ec:column> --%>

				<c:set var="tassetnum">领用数量</c:set>	
				<ec:column property="assetnum" title="${tassetnum}" style="text-align:center" />

				<%-- <c:set var="tassetunit"><s:text name='oaAssetinformationOutlog.assetunit' /></c:set>	
				<ec:column property="assetunit" title="${tassetunit}" style="text-align:center" /> --%>

                <c:set var="tcreatetime">领用时间</c:set>	
				<ec:column property="createtime" title="${tcreatetime}" style="text-align:center" >
				<fmt:formatDate value='${oaAssetinformationOutlog.createtime}' pattern='yyyy-MM-dd' />
				</ec:column> 

				<c:set var="tcreateRemark">领用备注</c:set>	
				<ec:column property="createRemark" title="${tcreateRemark}" style="text-align:center" >
				</ec:column>
					
				<%-- <c:set var="tassetunit">关联事项</c:set>	
				<ec:column property="assetunit" title="${tassetunit}" style="text-align:center" >
				<a href='#' onClick="openNewWindow('<%=request.getContextPath()%>/powerruntime/optApply!generalOptView.do?djId=${oaAssetinformationOutlog.assetunit}&nodeInstId=0&s_itemtype=SQ','powerWindow',null)">${oaAssetinformationOutlog.assetunit }</a>
				</ec:column> --%>
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:if test="${empty oaAssetinformationOutlog.assetunit }">
					<a href='#' onClick="openNewWindow('<%=request.getContextPath()%>/powerruntime/optApply!listSQ.do?s_itemtype=SQ&djid=${oaAssetinformationOutlog.djid}','powerWindow',null)">关联</a>
					</c:if>
					<c:if test="${not empty oaAssetinformationOutlog.assetunit }">
					<a href='#' onClick="openNewWindow('<%=request.getContextPath()%>/powerruntime/optApply!listSQ.do?s_itemtype=SQ&djid=${oaAssetinformationOutlog.djid}','powerWindow',null)">修改关联</a>
					<a onclick="openNewWin('${oaAssetinformationOutlog.djid}', '${oaAssetinformationOutlog.assetunit }', '${no }')" href='javascript:void(0);' 
							>取消关联</a>
					<a href='#' onClick="openNewWindow('<%=request.getContextPath()%>/powerruntime/optApply!generalOptView.do?djId=${oaAssetinformationOutlog.assetunit}&nodeInstId=0&s_itemtype=SQ&isDept=${isDept}','powerWindow',null)">查看关联</a>
					</c:if>
				</ec:column> 

			</ec:row>
		</ec:table>

	</body>
	<script type="text/javascript">
	   function openNewWin(djid,assetunit,no){
		   if(confirm("确认取消关联?")){
			   var currentUrl ="<%=request.getContextPath()%>/oa/oaAssetinformationOutlog!delete.do?djid="+djid+"&assetunit="+assetunit+"&no="+no+"";
			   $.post(
					   currentUrl,
				         function(){
							  window.location.reload();
				         }
				   );
			   return false;
		   }else{
			   return false;
		   }
		   
		  
		   
	   }
	</script>
</html>
