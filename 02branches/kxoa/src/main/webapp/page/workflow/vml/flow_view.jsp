<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html xmlns:v="urn:schemas-microsoft-com:vml">
<HEAD>

<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
<meta name="decorator" content="noneWorkFlow"/>

<% String path = request.getContextPath(); %>

<TITLE>${ wfname }-在线工作流设计</TITLE>
<style type="text/css">
#nodetable{border:1px solid #000;}
#nodetable td { padding:5px 10px;height:20px;line-height:20px;border:none; }
#nodetable th { font-size:12px; }
#shadow { width:100%; position:absolute; left:0; top:0; background:#000; filter:alpha(opacity=30); opacity:0.3; }
</style>
<link href="<%=path%>/page/workflow/css/style.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="<%=path%>/scripts/jquery-1.6.min.js" ></script>
<script type="text/javascript" src="<%=path%>/page/workflow/vml/js/flow.js"></script>
<script type="text/javascript" src="<%=path%>/page/workflow/vml/js/common.js"></script>
<script type="text/javascript" src="<%=path%>/page/workflow/vml/js/dtree.js"></script>
<script type="text/javascript" src="<%=path%>/page/workflow/vml/js/shape.js"></script>
<script type="text/javascript" src="<%=path%>/page/workflow/vml/js/toppanel.js"></script>
<script type="text/javascript" src="<%=path%>/page/workflow/vml/js/topflow.js"></script>
<script type="text/javascript" src="<%=path%>/page/workflow/vml/js/viewflowevent.js"></script>
<script type="text/javascript" src="<%=path %>/page/workflow/svgworkflow/drag.js"></script>
<script type="text/javascript" src="<%=path %>/page/workflow/svgworkflow/moveTip.js"></script>

<SCRIPT type="text/javascript">
var flowid='${param.flowInstId}';
var path='<%=path%>';

function DrawVML(){
  Canvas.innerHTML = _FLOW.ProcString();
  Canvas.innerHTML += _FLOW.StepString();
  _FLOW.getInnerObject();
  _FOCUSTEDOBJ = null;
}

function DrawAll(){
  //DrawTree();
  DrawVML();
  //DrawDataView();
}

function LoadFlow(xml){
  if(xml == "")
  {
    _FLOW.createNew("");
  }
  else
  {
   if ( _FLOW.loadFromXML(xml))
   {
	 DrawAll();
	
	 changeVML(viewxml);
	 //alert(viewxml);
	   
   }
  }
}
function closeNodeView(){
	$("#shadow").hide();
	$("#NodeView").hide();
	$("#nodetable").empty();
	//$("body").css({"height":"auto","overflow":"auto"});
}



</script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style='background-image: url(<%=path%>/page/workflow/image/canvasbg.gif);' >
<input id="xml" type="hidden" value='${xml}'></input>
<input id="viewxml" type="hidden" value='${viewXml}'></input>
<div style='left:0px;top:0px;width:100%;height:100%;position:absolute;z-index:-1000' id="Canvas"></div>
<div style='width:100%;' id="canvas" onmousedown="mouseDown(event)"></div>
<!-- <v:rect class="toolui" style="display:none" id="_rectui">
<v:Stroke dashstyle="dashdot"/>
</v:rect>
<v:roundrect class="toolui" style="display:none;left:0px;top:0px;width:60px;height:50px;" id="_roundrectui">
<v:Stroke dashstyle="dashdot"/>
</v:roundrect>
<v:shape type="#diamond" class="toolui" style="display:none;left:0px;top:0px;width:60px;height:50px;" strokeweight="1px" id="_diamondui">
<v:Stroke dashstyle="dashdot"/>
</v:shape>
<v:oval class="toolui" style="display:none;left:0px;top:0px;width:60px;height:50px;" id="_ovalui">
<v:Stroke dashstyle="dashdot"/>
</v:oval>
<v:line class="toolui" style="display:none" from="0,0" to="100,0" id="_lineui">
<v:Stroke dashstyle="dashdot" StartArrow="" EndArrow="Classic"/>
</v:line> -->
<div id="shadow" style="display: none"></div>
<div id="NodeView" style="width:460px; border:1px solid #666; background:#fff; position:absolute; left:200px; top:200px;display: none;">
<p id="nodebt" style="padding:6px 0 0 10px;height:24px;line-height:24px;text-align: center;color: red;cursor:move;margin:6px 0px;" >主办人办理</p>
<a style="width:16px;height:16px;line-height:16px;text-align:center;border:1px solid #aaa;position:absolute; right:10px;top:6px;color:#000;cursor:pointer;" onclick="closeNodeView()">×</a>
<table id="nodetable" border="0" cellpadding="0" cellspacing="0" style="width:97%; border-collapse:collapse; margin:0 10px 10px 10px;">
</table>
</div>
<script type="text/javascript">

var xml=$("#xml").attr("value");
var viewxml=$("#viewxml").attr("value");
//alert(viewxml);

if($.browser.msie && $.browser.version<9){
	$("#canvas").hide();
	LoadFlow(xml);
}else{
	$("#Canvas").hide();
	readXML(xml,viewxml);
	if(navigator.userAgent.indexOf("Firefox")>-1){
		document.getElementsByTagName("svg")[0].style.height = '100%';
		document.getElementsByTagName("svg")[0].style.width = '100%';
	}
}

/* $(function(){
	$(window).bind('scroll',function(){
		setTimeout(function(){
			var h = parseInt(document.body.scrollTop)+parseInt(document.body.clientHeight)/2-parseInt($("#NodeView").outerHeight())/2;
			$("#NodeView").css({"top":h});},1000);
	});
}); */

// 如果是页面中tab页显示的入口进入查看流程，则调整父页面以及祖父页面的高度（hll）
if (window.frameElement && "tabFrames" == window.frameElement.id) {
	window.parent.document.getElementById("tabFrames").style.height = window.document.body.scrollHeight + "px";
	window.parent.parent.document.getElementById("AllInfoFrame").style.height = (window.document.body.scrollHeight + 60) + "px";
}

</script>

</body>
</html>
