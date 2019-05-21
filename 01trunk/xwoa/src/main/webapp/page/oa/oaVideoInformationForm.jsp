<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<style>
 table td{white-space:nowrap;}
</style>
<script type="text/javascript">
$(function(){
	  $("#saveVideo").bind("click",function(){
		 window.location.href="oaVideoInformation!save.do";
	  });
	 // $("#header").width($("#video_content form").width());
}); 
</script>
<%-- <sj:head locale="zh_CN" /> --%>
</head>
<body>

<%@ include file="/page/common/messages.jsp"%>
<div  style="height:30xp;background-color:#000;display:block;height:30px;margin-left:9%;margin-right: 9%;">
			<p style="color:#FFFFFF ;padding-top:8px;margin-left: 18px;">上传视频</p>
</div>
 <div id="video_content" style="background-color:#F6F6F6;height: 100%;margin-top:30px;1px ;margin-left:9%;margin-right: 9%;">
  <s:form action="oaVideoInformation"  method="post" namespace="/oa" id="oaVideoInformationForm" enctype="multipart/form-data">
     <table border="0" cellpadding="1" cellspacing="1"  class="viewTable">		
				<tr>
					<td class="addTd">
						视频标题
					</td>
					<td align="left">
						<s:textfield name="title" size="40" />
					</td>
						<td class="addTd">
						视频分类
					</td>
					<td align="left">
						<select name="infoType"  style="width: 100%;cursor:pointer;">
			              <option value="">---请选择---</option>
			                <c:forEach var="row" items="${cp:DICTIONARY('videoType')}">
			                <option value="${row.datacode}"
			                 <c:if test="${row.datacode==infoType}"> selected="selected"</c:if>>
			                 <c:out value="${row.datavalue}" />
			             </option>
						</c:forEach></select>

					</td>
				</tr>
				<tr>
					<td class="TDTITLE">
						发布日期
					</td>
					<td align="left">
				<input type="text" class="Wdate"  id="releaseDate" name="releaseDate" 
				style="height: 20px; width: 150px; border-radius: 3px; border: 1px solid #cccccc;"
				value='<fmt:formatDate value="${object.releaseDate}" pattern="yyyy-MM-dd"/>'
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期">					
<%-- 					<sj:datepicker id="releaseDate" name="releaseDate"  --%>
<%-- 					value="%{object.releaseDate}"  --%>
<%-- 					yearRange="2000:2020" displayFormat="yy-mm-dd" changeYear="true" readonly="true"  /> --%>
					</td>
					<td class="TDTITLE">
						信息有效期
					</td>
					<td align="left">
				<input type="text" class="Wdate"  id="validDate" name="validDate" 
				style="height: 20px; width: 150px; border-radius: 3px; border: 1px solid #cccccc;"
				value='<fmt:formatDate value="${object.validDate}" pattern="yyyy-MM-dd"/>'
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期">						
<%-- 						<sj:datepicker id="validDate" name="validDate" value="%{object.validDate}"  --%>
<%-- 							 yearRange="2000:2020" displayFormat="yy-mm-dd"  --%>
<%-- 							 changeYear="true" readonly="true"/> --%>
					</td>
				</tr>
				<tr>
					<td class="TDTITLE">
						视频剧情
					</td>
					<td align="left" colspan="3">
						<s:textarea name="videoName" style= "width:100%;height:100px;" />
					</td>
				</tr>
				<tr>
					<td class="TDTITLE">
						封面图片
					</td>
					<td align="left">
						<s:file name="file"/>
					</td>
					<td class="TDTITLE">
						视频格式
					</td>
					<td align="left">
						<s:textarea name="videoStyle" cols="40" rows="2"/>
					</td>
				</tr>
				<tr>
				    <td class="TDTITLE">
					上传视频</td>
					<td align="left" colspan="3">
					<s:file name="video"/>
					</td>
				</tr>
				<tr>
					<td class="TDTITLE">
						关键字
					</td>
					<td align="left">
	                   <s:textfield name="publicKey"  size="40"/>
					</td>
					<td class="TDTITLE">
						导演
					</td>
					<td align="left">
	                   <s:textfield name="derector"  size="40"/>
					</td>
				</tr>

          </table>
        
        <div>
			<span ><s:submit key="保存" method="save" style= "margin-top:20px;margin-left:408px;margin-right: auto; height:30px;width:80px;text-align:center;background-color:#1c98e7 ;border:1px solid #007dcc;"/></span>
		</div>
		</s:form>
</div>
</body>

</html>
