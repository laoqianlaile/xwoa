var _FLOW=new TTopFlow("");
var _ZOOM=1;
var _TOOLTYPE="point";


function objFocusedOn(objId)
{
}
function changeVML(viewxml)
{
	var xmlDoc = new ActiveXObject('MSXML2.DOMDocument');
	xmlDoc.async = false;
	xmlDoc.loadXML(viewxml);
	
	var xmlRoot = xmlDoc.documentElement;
	var Procs = xmlRoot.getElementsByTagName("Procs").item(0);      
	for (var i = 0;i < Procs.childNodes.length;i++) {
		var Proc = Procs.childNodes.item(i);
		var id = Proc.getAttribute("id");
		var state = Proc.getAttribute("inststate");
		if (document.getElementById(id))
		{
			if(state == 'ready'){
				document.getElementById(id).StrokeColor = '#000000';
				document.getElementById(id+"Text").style.color='#000000';
				if(document.getElementById("h-"+id)){
					var _OFFHR = document.getElementById("h-"+id);
					var obj=_FLOW.getProcByID(id);
					_OFFHR.style.color = '#000000';
					_OFFHR.style.height = "1px";
					if(obj.ShapeType=="multi"){
						_OFFHR.style.border = "1px dashed #000000";
					}
				}
				
			}
			if (state == 'waiting')
			{
				document.getElementById(id).StrokeColor = '#FF0000';
				if(document.getElementById("h-"+id)){
					var _OFFHR = document.getElementById("h-"+id);
					var obj=_FLOW.getProcByID(id);
					_OFFHR.style.color = '#FF0000';
					_OFFHR.style.height = "1px";
					if(obj.ShapeType=="multi"){
						_OFFHR.style.border = "1px dashed #FF0000";
					}
				}
			}
			if (state == 'complete')
			{
				document.getElementById(id).StrokeColor = '#0000FF';
			}
		}
	}

	var Steps = xmlRoot.getElementsByTagName("Steps").item(0); 
	for (i = 0;i < Steps.childNodes.length;i++) {
		var Step = Steps.childNodes.item(i);
		var id = Step.getAttribute("id");
		var state = Step.getAttribute("inststate");
		if (document.getElementById(id))
		{
			if (state == '-1')
			{
				document.getElementById(id).StrokeColor = '#000000';
			}
			if (state == '1')
			{
				document.getElementById(id).StrokeColor = '#FF0000';
			}
		}
	}
}
function doDocMouseDown() {
	if(event.button==2) {
		return false;
	}
	var x=(event.x+document.body.scrollLeft)/_ZOOM;
	var y=(event.y+document.body.scrollTop)/_ZOOM;
	var oEvt=event.srcElement;
	if(oEvt.id=="tableContainer"||oEvt.id=="") {
		return ;
	}
	// 过滤数据视图/对象视图上的事件
	if(oEvt.typ=="Step")
	{
		document.all.Canvas.style.cursor="default";
		return ;
	}
	if(fireProcMouseDown(x,y)) {
		return ;
	}
	// 过滤图元上的事件
	switch(_TOOLTYPE) {
		case "rect":
		case "roundrect":
		case "diamond":
		case "oval":
		case "fillrect":
		if(oEvt.tagName!="DIV") {
			return ;
		}
		if(oEvt.id!="Canvas") {
			return ;
		}
		var obj=document.all("_"+_TOOLTYPE+"ui");
		_CURRENTX=x;
		_CURRENTY=y;
		obj.style.left=_CURRENTX;
		obj.style.top=_CURRENTY;
		obj.style.width=0;
		obj.style.height=0;
		obj.style.display="block";
		_MOVETYPE=_TOOLTYPE;
		break;
	}
}
function fireProcMouseDown(x,y) {
	var curProc=null;
	//遍历节点
	for(var i=0;i<_FLOW.Procs.length;i++) {
		Proc=_FLOW.Procs[i];
		if(x>=parseInt(Proc.X)&&x<=(parseInt(Proc.X)+parseInt(Proc.Width))
		&&y>=parseInt(Proc.Y)&&y<=(parseInt(Proc.Y)+parseInt(Proc.Height))) {
			//if(curProc==null||Proc.zIndex>=curProc.zIndex)// 重叠的情况下取上面那个
			curProc=Proc;
		}
	}
	//遍历标签
	for(var i=0;i<_FLOW.Steps.length;i++) {
		Proc=_FLOW.Steps[i].Label;
		if(x>=parseInt(Proc.X)&&x<=(parseInt(Proc.X)+parseInt(Proc.Width))
		&&y>=parseInt(Proc.Y)&&y<=(parseInt(Proc.Y)+parseInt(Proc.Height))) {
			//if(curProc==null||Proc.zIndex>=curProc.zIndex)// 重叠的情况下取上面那个
			curProc=Proc;
		}
	}
	if(curProc!=null) {
		obj=document.getElementById(curProc.ID);
		if(obj.tagName!='DIV') {
			objFocusedOn(obj.id);
		}
		doProcMouseDown(obj,x,y);
		return true;
	}
	return false;
}
function doProcMouseDown(obj,x,y) {
	//判断是否是画线
	if(_TOOLTYPE=="line"||_TOOLTYPE=="polyline") {
		_CURRENTX=x;
		_CURRENTY=y;
		_MOVEOBJ=document.all("_lineui");
		//_MOVEOBJ.from = _CURRENTX + "," + _CURRENTY;
		_MOVEOBJ.from=_CURRENTX+","+(_CURRENTY-0);
		//原代码
		_MOVEOBJ.to=_MOVEOBJ.from;
		_MOVEOBJ.style.display="block";
		_MOVETYPE=_TOOLTYPE;
	}
	else {
		var rightSide=(parseInt(obj.style.left)+parseInt(obj.style.width)-x<=2);
		var bottomSide=(parseInt(obj.style.top)+parseInt(obj.style.height)-y<=2);
		if(rightSide&&bottomSide) {
			_MOVETYPE="proc_nw";
		}
		else if(rightSide) {
			_MOVETYPE="proc_e";
		}
		else if(bottomSide) {
			_MOVETYPE="proc_n";
		}
		else {
			_MOVETYPE="proc_m";
			_CURRENTX=x-obj.offsetLeft;
			_CURRENTY=y-obj.offsetTop;
		}
		_MOVEOBJ=obj;
	}
	window.event.cancelBubble=true;
}

function objFocusedOn(objId) {
	//清除原来选中的对象
	objFocusedOff();
	_FOCUSTEDOBJ=document.getElementById(objId);         //注意这里 根据id取对象
	if(_FOCUSTEDOBJ!=null)
	{
		//_FOCUSTEDOBJ.StrokeColor=_FOCUSTEDOBJ.fsc;
		_FOCUSTEDOBJ.StrokeWeight=1.2;
	}
	var x=(event.x+document.body.scrollLeft)/_ZOOM;
	var y=(event.y+document.body.scrollTop)/_ZOOM;
	_clkPx=x/4*3+"pt";
	_clkPy=y/4*3+"pt";
	//如果是折线则修改折线形状
	if(_FOCUSTEDOBJ.tagName=="PolyLine")
	{
		modifyStepShape(_FOCUSTEDOBJ,x,y);
	}
	//stuffProp();
	//alert(objId)	
	showNodeView(objId);
}

function showNodeView(objId){
	/*alert(flowid);
	alert(objId);*/
	/*alert(path);*/
	//$("body,html").unbind();
	$.ajax({
		type:"POST",
		url:path+"/sampleflow/sampleFlowManager!viewFlowNodeInfo.do?flowInstId="+flowid+"&nodeId="+objId,
		dataType:"json",
		async: false,
		success:function(data){	
			debugger;
			if(data.instance!=null){	
			$("#nodetable").empty();
			var h = parseInt(document.body.scrollTop)+200;
			//$("#shadow").height(document.body.scrollHeight).show();
			$("#NodeView").css({"top":h+"px"}).show();
			//$("body").css({"height":document.body.clientHeight,"overflow":"hidden"});
			$("#nodebt").html(data.nodename);
			var instances=eval(data.instance);
		/*	var tasks=eval(instances[0].task);
			alert(tasks[0].username);*/
			
			for(var i=0;i<instances.length;i++){
				if (instances[i].isTimer=='T') {
					$("#nodetable").append('<tr><td>当前状态为<span style="color:red;">'+instances[i].state+'</span>，使用时间'+instances[i].usetime+'，剩余时间'+instances[i].timelimit+'</td></tr>');
				}
				else{
					$("#nodetable").append('<tr><td>当前状态为<span style="color:red;">'+instances[i].state+'</span></td></tr>');
				}
				if(instances[i].unitname==null){
					$("#nodetable").append('<tr><td>创建于<span style="color:0000ff;">'+instances[i].createtime+'</span></td></tr>');
				}
				else
					$("#nodetable").append('<tr><td>创建于<span style="color:0000ff;">'+instances[i].createtime+'</span>('+instances[i].unitname+')</td></tr>');	
				if(instances[i].action!=null){
					var actions=eval(instances[i].action);
					var j=actions.length;
				/*	alert(actions[0].username);*/
					//$("#nodetable").append('<tr><th rowspan="'+j+'">详细信息</th><td>'+actions[0].username+'</td><th>操作时间</th><td>'+actions[0].actiontime +'</td><th>操作类型</th><td>'+actions[0].actiontype+'</td></tr>');
					if(j>=1){
						for(var m=0;m<j;m++){
							if(m==j-1){
								$("#nodetable").append('<tr><td style="border-bottom:1px solid #aaa;">'+actions[m].username+'于<span style="color:0000ff;">'+actions[m].actiontime+'</span>'+actions[m].actiontype+'</td></tr>');
							}else{
								$("#nodetable").append('<tr><td>'+actions[m].username+'于<span style="color:0000ff;">'+actions[m].actiontime+'</span>'+actions[m].actiontype+'</td></tr>');
							}
						}
					}
				}
				else if (instances[i].task!=null){
					var tasks=eval(instances[i].task);
					/*alert(tasks[0].username);*/
					var j=tasks.length;
					var arry = new Array();
					  for(var m=0;m<j;m++){
						  arry.push(tasks[m].username);
					  }
					  $("#nodetable").append('<tr><td style="border-bottom:1px solid #aaa;">可操作人员：'+arry.join("，")+'</td></tr>');				
				}
			}
			moveTip( g("NodeView"),g("nodebt") );
		}
			
		},
		error:function(){
			$("#shadow").hide();
			$("#NodeView").hide();
			$("#nodetable").empty();
			alert("失败");
		}
	});

}


//清除原来选中的对象
function objFocusedOff() {
	if(_FOCUSTEDOBJ!=null)
	{
		//_FOCUSTEDOBJ.StrokeColor=_FOCUSTEDOBJ.sc;
		//恢复原来的颜色
		_FOCUSTEDOBJ.StrokeWeight=1;
		var oLabel=document.getElementById('lab'+_FOCUSTEDOBJ.id);
		if(oLabel)
		{
			oLabel.style.backgroundColor='';
		}
	}
	_FOCUSTEDOBJ=null;
	isSelectPoint=0;
	isSelectLine=0;
	ptMoveType="";
	oOval=null;
	return ;
}
if($.browser.msie && $.browser.version<=9){
  document.onmousedown=doDocMouseDown;
}
