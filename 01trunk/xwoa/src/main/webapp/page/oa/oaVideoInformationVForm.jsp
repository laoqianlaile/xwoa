<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<%-- <sj:head locale="zh_CN" /> --%>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title><s:text name="oaVideoInformation.edit.title" /></title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/themes/default/default.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/plugins/code/prettify.css" />
<script charset="utf-8"
	src="${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/kindeditor.js"></script>
<script charset="utf-8"
	src="${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/lang/zh_CN.js"></script>
<script charset="utf-8"
	src="${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/plugins/code/prettify.js"></script>
<script>
		var editor;
		KindEditor.ready(function(K) {
			editor = K.create('textarea[id="newcontent"]', {
				cssPath : '${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/plugins/code/prettify.css',
				uploadJson : '${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/jsp/upload_json.jsp',
				fileManagerJson : '${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.getElementById("oaVideoInformationForm").submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.getElementById("oaVideoInformationForm").submit();
					});
				}
			});			
			prettyPrint();
		});
	</script>
</head>

<body>
	<fieldset class="form">
		<legend>
			<p class="ctitle">
				<s:text name="oaVideoInformation.edit.title" />
			</p>
		</legend>


		<%@ include file="/page/common/messages.jsp"%>

		<s:form action="oaVideoInformation" method="post" namespace="/oa"
			id="oaVideoInformationForm" enctype="multipart/form-data">

			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<input type="hidden" name="no" value="${no }">
				<tr>
					<td class="addTd">视频节目标题<font color='red'>*</font></td>
					<td align="left" colspan="3"><s:textfield name="title" id="title"
							size="40" style="width: 100%;cursor:pointer;" /></td>

				</tr>
				<tr>
					<td class="addTd">视频节目分类</td>
					<td align="left"><select name="infoType" style="width:100px">
							<option value="">请选择</option>
							<c:forEach var="row" items="${cp:DICTIONARY('videoType')}">
								<option value="${row.datacode}"
									<c:if test="${row.datacode==infoType}"> selected="selected"</c:if>>
									<c:out value="${row.datavalue}" />
								</option>
							</c:forEach>
					</select></td>
						<td class="addTd">视频节目类别</td>
		           <td align="left" ><s:radio name="videoType" value="#{object.videoType}"
									
							list="#{'D':'单剧','L':'连载' }" listKey="key" listValue="value" ></s:radio></td>
					</tr>
				
					<tr>
						<td class="addTd">视频节目标签</td>
            
                   	<td align="left" colspan="3" id="bookmark" style="height:60px;">
				<s:checkboxlist  list="#request.dataMap" name="bookmark"  listKey="key" listValue="value" value="%{bookmarkList}"></s:checkboxlist>		
<%-- 				<input type="text" name="otherItem" id="otherItem" value="${object.otherItem }" style="display:none;width:340px;height:50px;"/>
 --%>				</td>
                 
				</tr>
				<tr>
					<td class="addTd">
						视频图片(大)
					</td>
						
					<td><s:file name="big_" size="40" id="big_"   style="width:200px;height:25px" />
						 <c:if
							test="${not empty bigImage}">&nbsp;
				<input type="button" name="built" value="查看" class="btn"
								onclick="downFile('${object.no}','big_')">&nbsp;</c:if></td>
					<td class="addTd">
						视频图片(小)
					</td>
						
					<td><s:file name="upload" id="upload" size="40"   style="width:200px;height:25px" />
					 <c:if
							test="${not empty smallImage}">&nbsp;
				<input type="button" name="built" value="查看" class="btn"
								onclick="downFile('${object.no}','upload')">&nbsp;</c:if>
					</td>
				</tr>
					<tr>
					<td class="addTd">是否可回复</td>
					<td align="left" colspan="3"><select name="isAllowComment" style="width:100px">
							<c:forEach var="row" items="${cp:DICTIONARY('OAISAllowComment')}">
								<option value="${row.datacode}"
									<c:if test="${row.datacode==isAllowComment}"> selected="selected"</c:if>>
									<c:out value="${row.datavalue}" />
								</option>
							</c:forEach>
					</select></td>
					</tr>
				<tr>
					<td class="addTd">发布日期<font color='red'>*</font></td>
					<td align="left"><input type="text" class="Wdate"
						id="releaseDate" name="releaseDate"
						value='<fmt:formatDate value="${object.releaseDate}" pattern="yyyy-MM-dd"/>'
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期">
					</td>
					<td class="addTd">信息有效期</td>
					<td align="left"><input type="text" class="Wdate"
						id="validDate" name="validDate"
						value='<fmt:formatDate value="${object.validDate}" pattern="yyyy-MM-dd"/>'
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期">
					</td>
				</tr>
                  <tr>
				
					<td class="addTd">关键字</td>
					<td align="left"><s:textfield name="publicKey"
							style="width: 100%" /></td>
				<td class="addTd">引用连接</td>
					<td align="left"><s:textfield name="referenceLinks"
							style="width: 100%" /></td>
				</tr>
				<tr>
					<td class="addTd">视频介绍</td>
					<td align="left" colspan="3"><s:textarea name="remark"
							id="remark" style="width: 100%;" /></td>
				</tr>
		<%-- 		<tr>
					<td class="addTd">视频剧情</td>
					<td align="left" colspan="3"><s:textarea name="videoName"
							id="newcontent" cols="400" rows="20" style="width: 100%;" /></td>
				</tr> --%>
			</table>

        <div class="formButton">
			<input type="button" name="back" Class="btn" onclick="history.back(-1);" value="返回" />
			<input type="button" class="btn" id="save" value="保存" onclick="submitItemFrame('SAVE');" />	
		</div>
		<div style="color:red;text-align:center;margin-top:10px">
		  <p>友情提示：1、视频节目分类设置：在数据字典管理功能中找到代码为“videoType”进行设置；</p>
		        <p style="padding-left:70px">2、视频节目标签设置：在数据字典管理功能中找到代码为“bookmark”进行设置；</p>
		</div>
		</s:form>
	</fieldset>
</body>
</html>

<script type="text/javascript">
function downFile(id,v) {
	var url = "oaVideoInformation!downloadPhoto.do?no=" + id+"&type="+v ;
	document.location.href = url;
}
	function submitItemFrame(subType){
		if(docheck()==false){
			return;
		}else{
		var srForm  = document.getElementById("oaVideoInformationForm");
		if(subType == 'SAVE'){
			srForm.action = 'oaVideoInformation!save.do';
		}

	//	editor.sync();
		srForm.submit();
		}
      }
	
	function docheck() {
		if($("#title").val() == '') {
			alert("视频标题不可为空！");
			$('#title').focus();
			return false;
		}	
		
		if($("#releaseDate").val() == '') {
			alert("发布日期不可为空！");
			$('#releaseDate').focus();
			return false;
		}
		var validDate=$('#validDate').val();
		var releaseDate=$('#releaseDate').val();
		if(null!=validDate&&validDate!=""&&validDate<releaseDate){
			alert("信息有效期不可晚于发布日期！");
// 			$('#validDate').focus();
			return false;
		}

	}
</script> 