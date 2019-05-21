<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/css/icon-css.css" rel="stylesheet" type="text/css" />	
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />

<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />		
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset   class="search">
			<legend class="headTitle">
	附件管理
			</legend>
			<div class="searchDiv">

		<s:form action="optStuffInfo" namespace="/powerruntime"
			style="margin-top:0;margin-bottom:5">
			<table style="width: auto;">

				<tr>
					<td class="searchTitleArea"><s:text name="optStuffInfo.filename" />:</td>
					<td><s:textfield name="s_filename" /></td>
					<td class="searchTitleArea"></td>
					<td><s:submit method="list" key="opt.btn.query" cssClass="btn"/>
					<input id="gaoji" type="button" class="grayBtnWide" onclick="showgaoji()">
						<input id="shouqi" type="button" class="grayBtnWide grayBtnWide_sq" style="display: none;" onclick="toshouqi()">
					</td>
				</tr>
				
				<tr id="gaoji_more" style="display: none;">
					<td class="searchTitleArea"><s:text name="optStuffInfo.djId" />:</td>
					<td><s:textfield name="s_djId" /></td>
					<td class="searchTitleArea"><s:text name="optStuffInfo.uploadtime" />:</td>
					<td><input type="text" class="Wdate searchCondContent"  id="s_begTime" <c:if test="${not empty s_begTime }"> value="${s_begTime}" </c:if>
	                       <c:if test="${empty s_begTime  }">value="${param['s_begTime'] }"</c:if> name="s_begTime"  
	                       onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})"placeholder="选择开始日期">
						                     <label class="searchCondTitle">至</label>
						        <input type="text" class="Wdate searchCondContent"  id="s_endTime" 
						        <c:if test="${not empty s_endTime }"> value="${s_endTime }" </c:if>
	                            <c:if test="${empty s_endTime  }">value="${param['s_endTime'] }"</c:if> 
	                            name="s_endTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})"placeholder="选择结束日期">
					</td>
					
					
				</tr>
			</table>
		</s:form>
		</div>
	</fieldset>

	<ec:table action="powerruntime/optStuffInfo!list.do" items="objList"
		var="optStuffInfo" imagePath="${STYLE_PATH}/images/table/*.gif"
		retrieveRowsCallback="limit">
		<ec:row>
			<%-- 
				<c:set var="tstuffid"><s:text name='optStuffInfo.stuffid' /></c:set>	
				<ec:column property="stuffid" title="${tstuffid}" style="text-align:center" />

 --%>
 			 <ec:column property="none"
				  title='<input type="button" name="quanxuan"  id="quanxuan" target="false"  value="全选">
				  <input type="button" name="czpw" target="false" value="删除" >'
				  sortable="false"  style="width:8%">		
					<input type="checkbox" id="${optStuffInfo.djId}" class="ecbox" 
						value="${optStuffInfo.djId}">
			  </ec:column>  
			<c:set var="tdjId">
				<s:text name='optStuffInfo.djId' />
			</c:set>
			<ec:column property="djId" title="${tdjId}" style="text-align:center" />

			<c:set var="tfilename">
				<s:text name='optStuffInfo.filename' />
			</c:set>
			<ec:column property="filename" title="${tfilename}"
				style="text-align:center">
					<input type="hidden" value="${optStuffInfo.filename}"/> 
					<c:choose>
						<c:when test="${fn:length(optStuffInfo.filename) > 20}">
							<c:out
								value="${fn:substring(optStuffInfo.filename, 0, 20)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${optStuffInfo.filename}" />
						</c:otherwise>
					</c:choose>	
			</ec:column>


			<c:set var="tuploadtime">
				<s:text name='optStuffInfo.uploadtime' />
			</c:set>
			<ec:column property="uploadtime" title="${tuploadtime}"
				style="text-align:center" format="yyyy-MM-dd" cell="date"/>

			<c:set var="tuploadusercode">
				<s:text name='optStuffInfo.uploadusercode' />
			</c:set>
			<ec:column property="uploadusercode" title="${tuploadusercode}"
				style="text-align:center">
				${cp:MAPVALUE("usercode",optStuffInfo.uploadusercode)}
				</ec:column>

			<c:set var="tnodename">
				<s:text name='optStuffInfo.nodename' />
			</c:set>
			<ec:column property="nodename" title="${tnodename}"
				style="text-align:center" />
			<c:set var="optlabel">
				<s:text name="opt.btn.collection" />
			</c:set>
			<ec:column property="opt" title="${optlabel}" sortable="false"
				style="text-align:center">
				<c:set var="deletecofirm">
					<s:text name="label.delete.confirm" />
				</c:set>
				<a
					href='powerruntime/optStuffInfo!delete.do?stuffid=${optStuffInfo.stuffid}'
					onclick='return confirm("${deletecofirm}?");'><s:text
						name="opt.btn.delete" /></a>
			</ec:column>

		</ec:row>
	</ec:table>

</body>

<script type="text/javascript">
	
	$("[name='quanxuan']").bind('click',function(){
		if($("[name='quanxuan']").attr("target")=="false"){
		$("[name='quanxuan']").attr("target","true");
		$("input[class='ecbox']").attr("checked","true"); 
		$("#quanxuan").val('取消');
		}
		else{
			$("[name='quanxuan']").attr("target","false");
			$("input[class='ecbox']").removeAttr("checked");
			$("#quanxuan").val('全选');
		}	
	});
	
	
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

	$("[name='czpw']").bind('click',function(){
		var vfstring = "";
		var items = $('[class = "ecbox"]:checkbox:checked');
		for (var i = 0; i < items.length; i++) {
		     vfstring = (vfstring + items.get(i).value + ','); 
		}
		if(vfstring!=""){
			if(confirm("是否确定删除?")){
				$.ajax({
					   type : 'post',
					   url: "<%=request.getContextPath()%>/powerruntime/optStuffInfo!deleteCase.do",
					   dataType:'text',
					   async: false,
					   data : {
						   resetUsers:  vfstring
					   },
					   success: function(data){
					           location.href="<%=request.getContextPath()%>/powerruntime/optStuffInfo!list.do";
					   }
			});
					}
				}
			else{
				alert("请选中办件再删除 !");
				
			}
	}); 
	
</script>

</html>
