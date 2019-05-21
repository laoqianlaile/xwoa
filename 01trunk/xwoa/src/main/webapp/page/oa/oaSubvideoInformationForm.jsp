<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<style type="text/css">
.update_success_panel {
	display: none;
	transition: opacity 0.5s;
	opacity: 0;
	position: absolute;
	z-index: 999;
	top: 40%;
	left: 30%;
	width: 550px;
	height: 100px;
	margin-left: -125px;
	margin-top: -75px;
	border: 1px solid #189AD7;
	border-radius: 4px;
	background-color: #fff;
	padding: 10px 5px;
	box-shadow: 2px 2px 2px #0081CD;
}
</style>
<title><s:text name="oaSubvideoInformation.edit.title" /></title>
</head>

<body>
	<fieldset class="form">
		<legend>
			<p class="ctitle">
				<s:text name="oaVideoInformation.edit.title" />
			</p>
		</legend>
<%@ include file="/page/common/messages.jsp"%>
	<s:form action="oaSubvideoInformation" method="post" namespace="/oa"
			id="oaSubvideoInformationForm" enctype="multipart/form-data">

			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<input type="hidden" name="id" value="${id }">
					<input type="hidden" name="no" id="no"   value="${no }">
				<tr>
					<td class="addTd">
						节目视频编号
					</td>
					<td align="left" colspan="3">
	                   ${no }
	
					</td>
				</tr>

				<tr>
					<td class="addTd">
						<s:text name="oaSubvideoInformation.title" /><font color='red'>*</font>
					</td>
		
					<td align="left" colspan="3"><s:textfield name="title" id="title"
							size="40" style="width: 100%;cursor:pointer;" /></td>
				</tr>

				<tr>
					<td class="addTd">
						<s:text name="oaSubvideoInformation.releaseDate" />
					</td>
						<td align="left"><input type="text" class="Wdate"
						id="releaseDate" name="releaseDate"
						value='<fmt:formatDate value="${object.releaseDate}" pattern="yyyy-MM-dd"/>'
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期">
					</td>
				
					<td class="addTd">
						<s:text name="oaSubvideoInformation.isuseprarent" />
					</td>
		
	
			     <td align="left" ><s:radio name="isuseprarent" value="#{object.isuseprarent}" onclick="dataMap(this.value)"
									
							list="#{'1':'否','0':'是' }" listKey="key" listValue="value" ></s:radio></td>
	
				</tr>
               
				<tr id="img" style="display:${(object.isuseprarent==1 || empty object.isuseprarent)?'':'none'}">
					<td class="addTd">
						<s:text name="oaSubvideoInformation.smallimage" />
					</td>
				
  	            <td colspan="3"><s:file name="upload" id="upload" size="40"   style="width:200px;height:25px" />
	
				</tr>

				<tr>
					<td class="addTd">
						上传视频
					</td>
					<td align="left" colspan="3">
          
              <s:file name="video_" id="video_" size="40" accept=".avi,.mp4,mpeg,.flv,.3gp,.wmv"   style="width:200px;height:25px" />
	
					</td>
				</tr>

				<tr>
					<td class="addTd">
						<s:text name="oaSubvideoInformation.remark" />
					</td>
				<td align="left" colspan="3"><s:textarea name="remark"
							id="remark"  style="width: 100%;" /></td>
				</tr>
</table>
		   <div class="formButton">
			<input type="button" name="back" Class="btn" onclick="history.back(-1);" value="返回" />
			<input type="button" class="btn" id="save" value="保存" onclick="submitItemFrame('SAVE');" />	
		</div>
		<div class="update_success_panel">
		   <h2>正在为您转码,过程大约需要30分钟,请耐心等待.....</h2>
		   <h2 class="time"></h2>
		</div>

				

</s:form></fieldset>

</body></html>
<script type="text/javascript">
$(function() {
	
	//初始化会议类型类型为P:视频会议室O:一般会议室(默认选中)
	var val = $("input[name='isuseprarent']:radio:checked").val();
	if(val==undefined||val==null){
		$("input[name='isuseprarent']:radio").eq(0).attr("checked","checked");
	
	}else{checkMeetType(val);}

});
function downFile(id,v) {
	var url = "oaVideoInformation!downloadPhoto.do?no=" + id+"&type="+v ;
	document.location.href = url;
}
	function submitItemFrame(subType){
		if(docheck()==false){
			return;
		}else{
		var srForm  = document.getElementById("oaSubvideoInformationForm");
		if(subType == 'SAVE'){
			srForm.action = 'oaSubvideoInformation!save.do';
		}
        srForm.submit();

		}
      }
	
	function docheck() {
		if($("#title").val() == '') {
			alert("视频标题不可为空！");
			$('#title').focus();
			return false;
		}	
	

	}
	function dataMap(t){
		
		var no = $.trim($("#no").val());

		if(t!=null||t!=''){
	
			if('0'==t){
				$.ajax({
					type : "POST",
					url :  "${contextPath}/oa/oaVideoInformation!checksmalimg.do?no="+no,
					dataType : "json",
					success : function(data) {
						if ("exist" == data.status) {
							alert("首页图片不可使用！");
						} else if ("none" == data.status) {
							alert("首页图片可使用！");
						} 
					},
					error : function() {
						alert("失败！");
					}
				});
				$("#img").attr("style","display:none");	

			}else{
				$("#img").attr("style","display:block");	

			}
			
		
		}
		
	}
</script> 
