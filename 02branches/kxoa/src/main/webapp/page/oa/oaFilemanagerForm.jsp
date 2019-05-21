<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaFilemanager.edit.title" />
</title>
	<style type="text/css">
	  
	   a.btnPlus{display:inline-block;width:20px;height:20px;background:url('${ctxStatic}/image/ico-plus.png') no-repeat center;}
	   a.btnSubtract{display:inline-block;width:20px;height:20px;background:url('${ctxStatic}/image/ico-subtract.png') no-repeat center;}
	    .fileList{overflow:hidden;}
	   .fileList li{float:left;margin-right:10px;margin-bottom:2px;}
	</style>
</head>

<body class="sub-flow" scroll=no>
	<%@ include file="/page/common/messages.jsp"%>

	<s:form action="oaFilemanager" method="post" namespace="/oa" id="oaFilemanagerForm" enctype="multipart/form-data">
		<fieldset>
		<legend>	
		文件管理
		</legend>
		<div class="innermsg_form">
		<input type="button" class="btn" id="save" value="保存" onclick="submitItemFrame('SAVE');"/>
		</div>
		<input type="hidden" id="no" name="no" value="${object.no}" />
		<input type="hidden" id="s_infoType" name="s_infoType" value="${s_infoType}" />
		
		
		<table border="0" cellpadding="0" cellspacing="0" class="viewTable" >
			<tr>
				<td class="addTd">文件类型<font color='red'>*</font></td>
				<td align="left">	
				<c:if  test="${empty  object.infoType and  empty  s_infoType}">
				<select  id="infoType" style="width:200px;height:25px;"
						name="infoType">
								<option value="SW">
									<c:out value='${cp:MAPVALUE("optTypeName","SW") }'/>
								</option>
								<option value="FW" >
									<c:out value='${cp:MAPVALUE("optTypeName","FW") }'/>
								</option>
				</select> 
				</c:if>
				<c:if  test="${empty  object.infoType and  not empty  s_infoType}">
				${cp:MAPVALUE("optTypeName",s_infoType) }
				<input type="hidden" id="infoType" name="infoType" value="${s_infoType}">	
				</c:if>	
				<c:if  test="${not empty  object.infoType }">
				${cp:MAPVALUE("optTypeName",object.infoType) }
				<input type="hidden" id="infoType" name="infoType" value="${infoType}">	
				</c:if>
				</td> 
				<td class="addTd">上传者</td>
				<td align="left" colspan="3">${cp:MAPVALUE("usercode",object.creater) }</td>
			</tr>
			
			<tr>
				<td class="addTd">标题<font color='red'>*</font></td>
				<td align="left" colspan="3"><s:textfield name="title" id="title"  style="width:90%;height:25px;" /></td>
			</tr>
            <tr>
				<td class="addTd">关键字 
				</td>
				<td align="left"  colspan="3"><s:textfield name="publicKey" id="publicKey"  style="width:90%;height:25px;"/></td>
			</tr>
			<tr>
				<td class="addTd">发布日期*<font color='red'>*</font>
				</td>
				<td align="left">
			<input type="text" class="Wdate" readonly="readonly" style="width:200px;height:25px;" id="releaseDate" name="releaseDate" 
			value='<fmt:formatDate value="${object.releaseDate}"  pattern="yyyy-MM-dd"/>'
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})"></td>
				<td class="addTd">文件有效期</td>
				<td align="left">
				<input type="text" class="Wdate" readonly="readonly" id="validDate" name="validDate"   style="width:200px;height:25px;"
			value='<fmt:formatDate value="${object.validDate}"  pattern="yyyy-MM-dd"/>'  
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" >
				</td>
			</tr>

			
			<tr>
				<td class="addTd">文档上传</td>
				<td align="left" colspan="3" id="docTD">
				  <div class="fileList">
				     <ul>
				      <c:forEach var="fileItem" items="${docAttachments}">
				       <li><a style='text-decoration: none;' href="${ctx}/oa/oaFilemanager!downLocalStuffInfo.do?no=${fileItem.id};return false;">${fileItem.fileName}</a>&nbsp;<a style="text-decoration: none;font-size: 12px" href="javascript:void(0);" onclick="removeAttachment(${fileItem.id},this)">删除</a></li>
				       </c:forEach>
				     </ul>
				  </div>
				  <div class="inputFileArea">
				     <s:file name="document" size="40" style="width: 94%;"/>
				     <a class="btnPlus" href="javascript:void(0);" onclick="appendInputFile(this);"></a>
				   </div>
				</td>
			</tr>
			<tr >
				<td class="addTd">备注</td>
				<td align="left" colspan="3" ><s:textfield name="remark" id="remark"  style="width:90%;height:50px;"/></td>
				</td>
			</tr>

		</table>
		
	</s:form>
	</fieldset>
	</body>	
	<script type="text/javascript">
	function submitItemFrame(subType){
		if(docheck()==false){
			return;
		}else{
		var srForm  = document.getElementById("oaFilemanagerForm");
		if(subType == 'SAVE'){
			srForm.action = 'oaFilemanager!save.do';
		}
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
// 			$('#releaseDate').focus();
			return false;
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
		init = setInterval("FrameUtils.initialize(window, init)",
				MyConstant.initTimeForAdjustHeight);
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
				url:"${ctx}/oa/oaFilemanager!removeAttachment.do",
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
    </script>
	</html>
	