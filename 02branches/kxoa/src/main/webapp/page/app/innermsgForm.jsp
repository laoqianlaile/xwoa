<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%> 
<html>
<head>
<title><s:text name="innermsg.edit.title" /></title>
	<%-- <sj:head locale="zh_CN" /> --%> 
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/ckeditor/ckeditor.js"></script>
	<script src="${pageContext.request.contextPath}/scripts/ajaxfileupload.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/scripts/centitUntil.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/scripts/suggest.js" type="text/javascript"></script>


<script type="text/javascript">
	function checkForm(){
		if(trim(_get('msgtitle').value).length==0){
			alert("主题不能为空");
			_get('msgtitle').focus();
			return false;
		}
		if(trim(_get('selectuser').value).length==0 && trim(_get('selectunit').value).length==0){
			alert("收件人不能为空");
			return false;
		}
		
		
		return true;
	}	

	//字符空格处理
	var trim = function (str) {
		return str.replace(/^\s+|\s+$/g, "");
	};
	
	//删除 
	function deleteRow(node) { 
	    if (!window.confirm("确认删除文件?")) { 
	        return; 
	    } 
	    var tabObj = _get("filetab");//获取表格对象 
	    var rowObj = node.parentNode.parentNode; 
	    tabObj.deleteRow(rowObj.rowIndex); 
	} 


	function addFile(data)
	{
		var newFile = "<input type='hidden' name='fileCodes' value='"+data.id+"' >" + data.name;
		var oper = "&nbsp;&nbsp;<a href='###' onclick=\"deleteRow(this)\">[删除]</a>"; 
		var tblObj = _get("filetab");
		var len =  tblObj.rows.length; 
	    var newRow = tblObj.insertRow(len);//插入行对象 
	    var fileCell = newRow.insertCell(0); 
	    fileCell.innerHTML = newFile + oper;
	};

	var _get = function (id) {
		return document.getElementById(id);
	};

	function checkReceive(v){
		if(v=='O'){
			_get("userDiv").style.display = "none";
			_get("unitDiv").style.display = "";
			_get("selectunit").disabled=false;
			_get("selectuser").disabled='disabled';
		}
		
		if(v=='P'){
			_get("userDiv").style.display = "";
			_get("unitDiv").style.display = "none";
			_get("selectuser").disabled=false;
			_get("selectunit").disabled='disabled';
		}
	}

</script>
</head>

<body>
<p class="ctitle">新建消息/公告</p>

<%@ include file="/page/common/messages.jsp"%>

<s:form action="innermsg" onsubmit="return checkForm();"  method="post" namespace="/app" id="innermsgForm" >
<table width="200" border="0" cellpadding="1" cellspacing="1">		

				<tr>
					<td class="TDTITLE">
						<s:text name="innermsg.msgtitle" />:
					</td>
					<td align="left">
						<s:textfield name="msgtitle" size="40"/><span id="msgtitleTip" style="line-height: "></span>
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="innermsg.receivetype" />:
					</td>
					<td align="left">
						<s:radio name="receivetype" onclick="checkReceive(this.value);" list="#{'P':'个人消息','O':'机构公告' }" listKey="key" listValue="value"  ></s:radio>
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="innermsg.receive" />:
					</td>
					<td align="left">
						<div id="userDiv" style="display:none;">
						<c:if test="${not empty receive}">
							${cp:MAPVALUE('usercode',object.receive)}
						</c:if>
						<c:if test="${empty receive}">
							<s:textfield  id="selectuser" name="userName" disabled="disabled" size="40"/>
						</c:if>
							<input type="hidden" name="receive" value="${receive}" />
							<ul id="list"></ul>
						<script type="text/javascript">
							$(function(){
								initValue($("input[name=userName]"),$("#list"),"${pageContext.request.contextPath}/sys/userDef!getUsers.do",$("input[name=receive]"));
							})
						</script>
						
						</div>
						<div id="unitDiv"  style="display:none;">
						<select id="selectunit" name="receive" disabled="disabled">   
							<option value="">   
									<c:out value="-- 请选择 --"/>   
							</option>    
							<c:forEach var="row" items="${cp:LVB('unitcode')}">     
								
								<c:if test="${object.receive eq row.value}">
									<option value="${row.value}" selected = "selected" >   
										<c:out value="${row.label}"/>   
									</option>
								</c:if>
								<c:if test="${object.receive ne row.value}">
									<option value="${row.value}">   
										<c:out value="${row.label}"/>   
									</option>
								</c:if>       
							</c:forEach> 
						</select>
						</div>
					</td>
				</tr>
				<script>
						if('${receivetype}' == 'O'){
							_get("unitDiv").style.display = "";
							_get("selectunit").disabled=false;
						}else{
							_get("userDiv").style.display = "";
							_get("selectuser").disabled=false;
						}
				</script>
				<tr>
					<td class="TDTITLE">
						附件:
					</td>
					<td align="left">
						<table id="filetab">
						</table>
						<input type="file" name="upload" id="upload">
						<input type="button" action="<c:url value='/app/fileinfo!uploadfile.do' ></c:url>"
 							onclick="uploadFile(this,'upload',addFile);" value="上传">
					</td>
				
				</tr>
				<tr>
					<td class="TDTITLE">
						<s:text name="innermsg.msgcontent" />:
					</td>
					<td align="left">
						<s:textarea  name="msgcontent" id="msgcontent" cols="60" rows="10"/>
					</td>
				</tr>
				<tr>
					<td height="40"></td>
					<td><s:submit name="save"  method="save" cssClass="btn" key="opt.btn.save" />
	&emsp;
	<!-- <input type="button" class="btn" value="返回" onClick="window.history.go(-1);"/> -->
	<input type="hidden" name="msgstate" value="U" ></td>
				</tr>

</table>
</s:form>
  	<script type="text/javascript">
	    
	    CKEDITOR.replace('msgcontent',
	    		{
	    	skin : 'office2003',
	    	language : 'zh-cn',
	    	width:500,
	    	toolbar:[
	    	['Bold','Italic','Underline','StrikeThrough','Style','FontFormat','FontName','FontSize','-','JustifyLeft','JustifyCenter','JustifyRight','JustifyFull','-','TextColor']
	    	,['OrderedList','UnorderedList','-','Outdent','Indent']
	    	,['-','Smiley','SpecialChar','Unlink','Replace','Preview']
	    	]
	    	}
	    );
   </script>
</body>
</html>
