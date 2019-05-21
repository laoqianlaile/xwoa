<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaInformation.edit.title" /></title>
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
						document.getElementById("oaInformationForm").submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.getElementById("oaInformationForm").submit();
					});
				}
			});			
			prettyPrint();
		});
	</script>
<style type="text/css">
a.btnPlus {
	display: inline-block;
	width: 20px;
	height: 20px;
	background: url('${ctxStatic}/image/ico-plus.png') no-repeat center;
}

a.btnSubtract {
	display: inline-block;
	width: 20px;
	height: 20px;
	background: url('${ctxStatic}/image/ico-subtract.png') no-repeat center;
}

.fileList {
	overflow: hidden;
}

.fileList li {
	float: left;
	margin-right: 10px;
	margin-bottom: 2px;
}
</style>
</head>

<body class="sub-flow">
	<%@ include file="/page/common/messages.jsp"%>

	<s:form action="oaInformation" method="post" namespace="/oa"
		id="oaInformationForm" enctype="multipart/form-data">
		<fieldset>
			<legend>
				<s:text name="oaInformation.edit.title" />
			</legend>
			<div class="innermsg_form">

				<input type="button" class="btn" id="save" value="保存"
					onclick="submitItemFrame('SAVE');" />
				<c:if test="${empty dashboard }">
					<input type="button" name="back" Class="btn"
						onclick="javascript:window.history.go(-1);" value="返回" />
				</c:if>
			</div>
			<input type="hidden" id="no" name="no" value="${object.no}" />
			<%-- 		<input type="hidden" id="infoType" name="infoType" value="${infoType}" /> --%>


			<table border="0" cellpadding="0" cellspacing="0" class="viewTable"
				style="height: 1124px">
				<tr>
					<td class="addTd"><s:text name="oaInformation.infoType" /><font
						color='red'>*</font></td>
					<td align="left">${cp:MAPVALUE('infoType',infoType)}<input
						type="hidden" id="infoType" name="infoType" value="${infoType}">
						<%-- <select  id="infoType" style="width:200px;height:25px;"
						name="infoType">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('infoType')}">
									<c:if test="${row.datatag eq 'T'}">
								<option value="${row.datacode}"
									<c:if test="${row.datacode==infoType}"> selected="selected"</c:if>>
									<c:out value="${row.datavalue}" />
								</option>
									</c:if>
							</c:forEach>
					</select> --%>
					</td>
					<td class="addTd"><s:text name="oaInformation.majorDegree" />
					</td>
					<td align="left"><select id="majorDegree"
						style="width: 200px; height: 25px;" name="majorDegree">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('IMP')}">
								<option value="${row.datacode}"
									<c:if test="${row.datacode==majorDegree}"> selected="selected"</c:if>>
									<c:out value="${row.datavalue}" />
								</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td class="addTd">是否启用</td>
					<td td align="left"><select id="state"
						style="width: 200px; height: 25px;" name="state">
							<c:if test="${empty state }">
								<option value="">---请选择---</option>
							</c:if>
							<c:forEach var="row" items="${cp:DICTIONARY('isStart')}">
								<option value="${row.datacode}"
									<c:if test="${row.datacode==state}"> selected="selected"</c:if>>
									<c:out value="${row.datavalue}" />
								</option>
							</c:forEach>
					</select></td>
					<td class="addTd">是否可回复</td>
					<td align="left"><select name="isAllowComment"
						style="width: 200px; height: 25px;">
							<c:forEach var="row" items="${cp:DICTIONARY('OAISAllowComment')}">
								<option value="${row.datacode}"
									<c:if test="${row.datacode==isAllowComment}"> selected="selected"</c:if>>
									<c:out value="${row.datavalue}" />
								</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td class="addTd">是否置顶</td>
					<td align="left" colspan="3"><select name="isTop"
						style="width: 200px; height: 25px;">
							<%-- <option value="" 
					<c:if test='${isTop eq '' or empty isTop }'> selected="selected"</c:if>>
					否</option>
					<option value="T" <c:if test='${isTop eq 'T'}'> selected="selected"</c:if>>
					是</option> --%>
							<option value=""
								<c:if test="${empty isTop or isTop eq ''}"> selected="selected"</c:if>>
								<c:out value="否" />
							</option>
							<option value="T"
								<c:if test="${isTop eq 'T'}"> selected="selected"</c:if>>
								<c:out value="是" />
							</option>
					</select></td>
				</tr>
				<tr>
					<td class="addTd"><s:text name="oaInformation.title" /><font
						color='red'>*</font></td>
					<td align="left" colspan="3"><s:textfield name="title"
							id="title" style="width:90%;height:25px;" /></td>
				</tr>

				<tr>
					<td class="addTd"><s:text name="oaInformation.releaseDate" /><font
						color='red'>*</font></td>
					<!-- activity -->
					<td align="left"><input type="text" class="Wdate"
						readonly="readonly" style="width: 200px; height: 25px;"
						id="releaseDate" name="releaseDate"
						value='<fmt:formatDate value="${object.releaseDate}"  pattern="yyyy-MM-dd"/>'
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})"></td>
					<td class="addTd"><s:text name="oaInformation.validDate" /> <c:if
							test="${'activity' eq infoType}">
							<font color='red'>*</font>
						</c:if></td>
					<td align="left"><input type="text" class="Wdate"
						readonly="readonly" id="validDate" name="validDate"
						style="width: 200px; height: 25px;"
						value='<fmt:formatDate value="${object.validDate}"  pattern="yyyy-MM-dd"/>'
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
				</tr>

				<tr>
					<td class="addTd"><s:text name="oaInformation.publicKey" /></td>
					<td align="left"><s:textfield name="publicKey" id="publicKey"
							style="width:200px;height:25px;" /></td>
					<td class="addTd"><s:text name="oaInformation.docNo" /></td>
					<td align="left"><s:textfield name="docNo" size="40"
							id="docNo" style="width:200px;height:25px;" /></td>
				</tr>

				<tr>
					<td class="addTd"><s:text name="oaInformation.author" /></td>
					<td align="left"><s:textfield name="author" id="author"
							size="40" style="width:200px;height:25px;" /></td>
					<td class="addTd"><s:text name="oaInformation.referenceLinks" />
					</td>
					<td align="left"><s:textfield name="referenceLinks"
							id="referenceLinks" style="width:200px;height:25px;" /></td>
				</tr>
				<tr>
					<td class="addTd"><s:text name="oaInformation.uploadFile" /></td>
					<td align="left" colspan="3" id="docTD">
						<div class="fileList">
							<ul>
								<c:forEach var="fileItem" items="${docAttachments}">
									<a href="#" class="docFile" onclick="downFile('${fileItem.id}')" style="text-decoration: underline"> ${fileItem.fileName} </a>
									<a style="text-decoration: none; font-size: 12px" href="javascript:void(0);" onclick="removeAttachment(${fileItem.id},this)">删除</a></li>
								</c:forEach>
							</ul>
						</div>
						<div class="inputFileArea">
							<s:file name="document" size="40" style="width: 94%;height:25px;" />
							<a class="btnPlus" href="javascript:void(0);"
								onclick="appendInputFile(this);"></a>
						</div>
					</td>
				</tr>
				<tr>
					<td class="addTd">上传视频</td>
					<td align="left" colspan="3" id="videoTD">
						<div class="fileList">
							<ul>
								<c:forEach var="fileItem" items="${videoAttachments}">
									<li><a
										href="${ctx}/oa/oaInformation!downLocalStuffInfo.do?no=${fileItem.id}">${fileItem.fileName}</a>&nbsp;<a
										style="text-decoration: none; font-size: 12px"
										href="javascript:void(0);"
										onclick="removeAttachment(${fileItem.id},this)">删除</a></li>
								</c:forEach>
							</ul>
						</div>
						<div class="inputFileArea">
							<s:file name="video" size="40" style="width: 94%;height:25px;"
								onchange="limitVideoFile(this)" />
							<a class="btnPlus" href="javascript:void(0);"
								onclick="appendInputFile(this);"></a>
						</div>
					</td>
				</tr>
				<tr style="height: 800px">
					<td class="addTd"><s:text name="oaInformation.bodyContent" /></td>
					<td align="left" colspan="3"><s:textarea name="bodyContent"
							id="newcontent" cols="40" rows="20"
							style="width: 100%;height:800px" /></td>
				</tr>

			</table>
	</s:form>
</body>
<script type="text/javascript">
	function submitItemFrame(subType){
		if(docheck()==false){
			return;
		}else{
		var srForm  = document.getElementById("oaInformationForm");
		if(subType == 'SAVE'){
			srForm.action = 'oaInformation!save.do?flag=GGZY';
		}
		editor.sync();
		srForm.submit();
		}
}
	
	
	function docheck() {
		if($("#infoType").val()==''){
			alert("信息类型不可为空！");
			$('#infoType').focus();
			return false;
		}	
		if($("#title").val()==''){
			alert("标题不可为空！");
			$('#title').focus();
			return false;
		}else{
			if($("#title").val().length>66){
				alert("标题长度不能超过66！");
				$('#title').focus();	
				return false;
			}
		}	
		if($("#releaseDate").val()==''){
			alert("发布时间不能为空！");
			return false;
		}
		if("activity"==$("#infoType").val()){
			if(""==$("#validDate").val()) {
				alert("信息有效期不能为空！");
				return false;
			}
			var releaseDate = new Date($("#releaseDate").val());
			var validDate = new Date($("#validDate").val());
			if(releaseDate.getTime()>=validDate.getTime()){
				alert("信息有效期必须大于发布日期");
				return false;
			}
		}
		
		if(null!=$("#publicKey").val()&&$("#publicKey").val().length>33){
			alert("关键字长度不能超过33！");
			$("#publicKey").focus();
			return false;	
		}
		if(null!=$("#docNo").val()&&$("#docNo").val().length>13){
			alert("文号长度不能超过13！");
			$("#docNo").focus();	
			return false;
		}
		if(null!=$("#author").val()&&$("#author").val().length>13){
			alert("作者名不能超过13！");
			$("#author").focus();	
			return false;
		}
		if(null!=$("#referenceLinks").val()&&$("#referenceLinks").val().length>66){
			alert("引用链接不能超过66！");
			$("#referenceLinks").focus();
			return false;
		}
		if(null!=$('#validDate').val()&&$('#validDate').val()!=""&&$('#validDate').val()<$("#releaseDate").val()){
			alert("信息有效期不能晚于发布时间！");
// 			$("#referenceLinks").focus();
			return false;
		}
    }
	$(function() {
	setTimeout(function(){
		init = setInterval("FrameUtils.initialize(window, init)",
				MyConstant.initTimeForAdjustHeight);
	},200); 
	});
	function appendInputFile(obj){
		var divContainer = $(obj).parent("div");
		var btnSubtract = $("<a>",{"class":"btnSubtract","href":"javascript:void(0);"}).click(function(){
			removeInputFile(this);
		});
		if(divContainer.find(".btnSubtract").length>0){
			divContainer.find(".btnSubtract").remove();
		}
		divContainer.append(btnSubtract);
		var newDivContainer = divContainer.clone();
		newDivContainer.insertAfter(divContainer);
		//当前加号删除
		$(obj).remove();
		//重新绑定事件
		newDivContainer.find(".btnSubtract").click(function(){removeInputFile(this);});
		newDivContainer.find(".btnPlus").click(function(){appendInputFile(this);});
		divContainer.find(".btnSubtract").click(function(){removeInputFile(this);});
	}
	function removeInputFile(obj){
	 	var divContainer = $(obj).parent("div");
		var divContainerParent = divContainer.parent();
		//如果删除行中有加号按钮，需要将加号按钮添加到上一行去
        if($(obj).prev().hasClass("btnPlus")){
        	var btnPlus = $("<a>",{"class":"btnPlus","href":"javascript:void(0);"}).click(function(){
        		appendInputFile(this);
        	});
        	btnPlus.insertBefore(divContainer.prev().find(".btnSubtract"));
		} 
		divContainer.remove();
		//判断是否剩下最后一个了，如果是那么减号操作不允许
		if(divContainerParent.find("div.inputFileArea").length==1){
			divContainerParent.find("div.inputFileArea").find(".btnSubtract").remove();
		}
	}
	function removeAttachment(id,obj){
		if(confirm("确定将该附件从磁盘上永久删除吗?")){
			$.ajax({
				type:"post",
				url:"${ctx}/oa/oaInformation!removeAttachment.do",
				dataType:"json",
				data:{"attachmentId":id},
				success:function(resp){
					if(resp){
						$(obj).parent().remove();
					}
				}
			});
				
		}
	}
	function limitVideoFile(ele){
		if(!isVideo(ele.value)){
			alert("非法文件格式，只能上传后缀为swf,flv,mp3,wav,wma,wmv,avi,mpg,rmvb,mov的文件");
			ele.outerHTML=ele.outerHTML; 
		}
	}
	function getFileName(path){
		var pos=path.lastIndexOf("\\");
		return path.substring(pos+1);  
	}
	
	//检测视频格式
	function isVideo(path){
		var fileName = getFileName(path);
		var allowFormat = "swf,flv,mp3,wav,wma,wmv,avi,mpg,rmvb,mov";
        if(fileName==undefined || fileName=='')
        	return false;
        var fileExt=path.replace(/.+\./,"");   //正则表达式获取后缀
        if((","+allowFormat+",").indexOf((","+fileExt+",")) > -1){
        	return true;
        }
        return false;
	}
	
	//下载已经上传的文件
	function downFile(id) {
			var url = "oaInformation!downLocalStuffInfo.do?no=" + id;
			document.location.href = url;
	};
    </script>
</html>
