<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<title></title>
   <script src="${ctx3rdJS}/codemirror/lib/codemirror.js"></script>
<link rel="stylesheet" href="${ctx3rdJS}/codemirror/lib/codemirror.css">
<script src="${ctx3rdJS}/codemirror/mode/htmlmixed/htmlmixed.js"></script>
<script src="${ctx3rdJS}/codemirror/mode/css/css.js"></script>
<script src="${ctx3rdJS}/codemirror/mode/javascript/javascript.js"></script>
<script src="${ctx3rdJS}/codemirror/mode/xml/xml.js"></script>
<script src="${ctx3rdJS}/codemirror/addon/edit/closetag.js"></script>
<script src="${ctx3rdJS}/codemirror/addon/edit/closebrackets.js"></script>
</head>

<body>
<fieldset class="_new">
		<legend>
			<p>布局方式编辑</p>
		</legend>

	<s:form action="optLayoutMethod" method="post" namespace="/oa"
		id="optLayoutMethodForm" >
     <div align="left">
		<input type="button" class="btn" value="保存"
			onclick="save();" />
		<input class="btn" id="back" type="button"  value="返回"/>
		<input type="button" class="btn" value="提交运行"
			onclick="submitTryit();" />
	 </div>
		<input type="hidden" id="id" name="id" value="${object.id}" />
		<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
			<tr>
			    <td class="addTd">布局方式<span style="color: red">*</span></td> 
				<td align="left"><input type="text" name="methodName" id="methodName" value="${object.methodName}" style="width: 220px; height: 25px;"/> </td>
			</tr>
		</table>
		<h5 style="text-align:center">布局内容编辑器</h5>
		<div style="overflow:hidden;">
				      <div style="float:left;width:50%;overflow-x:hidden;">
				         <div style="background:#dbf2ff;height:30px;line-height:30px;padding-left:10px;color:#0c6da0;font-weight: bold;">源代码 </div>
				         <textarea id="content" name="content">${object.content}</textarea>
				      </div>
				      <div style="float:right;width:50%;">
				        <div  style="background:#dbf2ff;height:30px;line-height:30px;padding-left:10px;color:#0c6da0;font-weight: bold;">运行结果</div>
				        <div id="iframewrapper"></div>
				      </div>
			      </div>
	 </s:form>
</fieldset>
</body>
<script type="text/javascript">
var editor;
$(function(){
	$('#back').click(function(){
		var srForm = document.getElementById("optLayoutMethodForm");
		srForm.action = 'optLayoutMethod!list.do';
		srForm.submit();
	});
	var mixedMode = {
			name: "htmlmixed",
			scriptTypes: [{matches: /\/x-handlebars-template|\/x-mustache/i,
			               mode: null},
			              {matches: /(text|application)\/(x-)?vb(a|script)/i,
			               mode: "vbscript"}]
			};
    editor = CodeMirror.fromTextArea(document.getElementById("content"), {
		mode: mixedMode,
		selectionPointer: true,
		lineNumbers: false,
		matchBrackets: true,
		indentUnit: 4,
		indentWithTabs: true
		});
    submitTryit();
});
function submitTryit() {
	  var text = editor.getValue();
	  var patternHtml = /<html[^>]*>((.|[\n\r])*)<\/html>/im
	  var patternHead = /<head[^>]*>((.|[\n\r])*)<\/head>/im
	  var array_matches_head = patternHead.exec(text);
	  var patternBody = /<body[^>]*>((.|[\n\r])*)<\/body>/im;
	  
	  var array_matches_body = patternBody.exec(text);
	  var basepath_flag = 1;
	  var basepath = '';
	  if(basepath_flag) {
	    basepath = '<base href="${ctx}/" target="_blank">';
	  }
	  var style = "<head><title>运行结果</title><style type='text/css'>table td{border:dashed 1px #ccc;}</style></head>";
	  text = style + text;
	  if(array_matches_head) {
	    text = text.replace('<head>', '<head>' + basepath );
	  } else if (patternHtml) {
	    text = text.replace('<html>', '<head>' + basepath + '</head>');
	  } else if (array_matches_body) {
	    text = text.replace('<body>', '<body>' + basepath );
	  } else {
	    text = basepath + text;
	  }
	  var ifr = document.createElement("iframe");
	  ifr.setAttribute("frameBorder", "no");
	  ifr.setAttribute("border", "0");
	  ifr.setAttribute("id", "iframeResult");  
	  document.getElementById("iframewrapper").innerHTML = "";
	  document.getElementById("iframewrapper").appendChild(ifr);
	 
	  var ifrw = (ifr.contentWindow) ? ifr.contentWindow : (ifr.contentDocument.document) ? ifr.contentDocument.document : ifr.contentDocument;
	  ifrw.document.open();
	  ifrw.document.write(text);  
	  ifrw.document.close();
}
function save(){
	var srForm = document.getElementById("optLayoutMethodForm");
	srForm.action = 'optLayoutMethod!save.do';
	srForm.submit();
}
</script>
</html>