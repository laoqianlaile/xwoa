String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g, "");
};

function person(obj, box, hiddenbox, oOrgUser) {
	getObj = function(){ return obj; };
	getBox = function(){ return box; };
	getHiddenbox = function(){ return hiddenbox; };
	getOrgUser = function() {return oOrgUser;};
	
	createList = function(obj, box, hiddenbox) {
		var pJson = obj;
		var len = pJson.length, sHtml = "", list = new Array;
		var nameVal = box.val(), nodeVal = hiddenbox.val(), name = new Array, node = new Array,
			pidVal = hiddenbox.attr("rel"),pidA = new Array;
		
		
		// create head
		for ( var i = 0; i < len; i++) {
			if (pJson[i].levle == 1) {
				sHtml += "<h2 id='" + pJson[i].nodeID + "'>" + pJson[i].name + "</h2>";
			}
		}
		$("#leftSide").html(sHtml);
		// create list
		
		// create search
		var hLen = $("#leftSide h2").length;
		if(!hLen){
			if(len>15){
				sHtml += "<div style='width:154px;overflow:hidden;'><input type='text' id='ui_sKey' style='width:110px;height:22px;line-height:22px;float:left;padding-left:2px;' value='' />" +
						//"<input type='button' id='ui_sBtn' style='float:left;padding-left:0;padding-right:0;' class='btn' value='搜索' />" +
						"</div>";
			}
		}
		
		if (hLen) {
			for ( var j = 0; j < hLen; j++) {
				var belongID = $("#leftSide h2").eq(j).attr("id");
				list[j] = "<dl class='hide " + belongID + "'>";
				for ( var i = 0; i < len; i++) {
					if (pJson[i].belongID == belongID) {
						list[j] += "<dd><a pid='"+i+"' href='#' nodeID='" + pJson[i].nodeID
//						if(pJson[i].loginName){
								+"'  loginname= '" + pJson[i].loginName
//						}
								+ "'>" + pJson[i].name+   "</a></dd>";
					}
				}
				list[j] += "</dl>";
				$("#" + belongID).after(list[j]);
			}
		} else {
			sHtml += "<dl>";
			for ( var i = 0; i < len; i++) {
				sHtml += "<dd><a pid='"+i+"' href='#' nodeID='" + pJson[i].nodeID 
//				if(pJson[i].loginName){
				+"'  loginname= '" + pJson[i].loginName
//				}
				+ "'>" + pJson[i].name + "</a>"
//				+'<img class="people" align="absbottom" width =16 height=16 src="../scripts/rtx/images/blank.gif" name="liuj3" onload="RAP(\'liuj3\');" >';
						"</dd>";
			}
			sHtml += "</dl>";
			$("#leftSide").html(sHtml);
		};
		if(nameVal.trim() != ""){ 
			 this.clearPerson(); 
			 name = nameVal.split(","); 
			 node = nodeVal.split(",");
			 //if(pidVal!=undefined)pidA = pidVal.split(",");
			 var lens =name.length; 
			 for(var i=0;i<lens;i++){
				 var oldHtml = $("#rightSide ul").html();
				 //if(pidA[i]==undefined)pidA[i] = 0;
				 //alert(pidA[i]);
				 var $this = $("#leftSide dd");
				 for(var k=0,dd = $this.length;k<dd;k++){
					 if($this.eq(k).find("a").attr("nodeID")==node[i]) pidA[i]=$this.eq(k).find("a").attr("pid");
				 }
				 $("#rightSide ul").append("<li pid='"+pidA[i]+"' nodeID='"+node[i]+"'>"+name[i]+"</li>"); 
			}
		 }
	};
	
	search=function(){
		var hLen = $("#leftSide h2").length,sHtml = "",len = getObj().length;
		var key = $("#ui_sKey").val().trim();
		/*if(key===""){
			alert("请输入关键字！");
			return false;
		}*/
		for ( var i = 0; i < len; i++) {
			if(getObj()[i].name.indexOf(key)>=0)
				sHtml += "<dd><a href='#' nodeID='" + getObj()[i].nodeID + "'>" + getObj()[i].name + "</a></dd>";
		}
		if(sHtml.trim()===""){
			$("#leftSide dl").html("<dt>没有搜索到你要的结果！</dt>");
			return;
		}
		$("#leftSide dl").html(sHtml);
	};
	
	addAllPerson=function(obj){
		var hLen = $("#leftSide h2").length;
		var sHtml = "";
		var pJson = obj;
		var len = pJson.length;
		var li = $("#leftSide dl dd");
		if(!hLen){
			this.clearPerson();
			for ( var i = 0; i < li.length; i++) {
				sHtml += "<li pid='"+$(li[i]).find("a").attr("pid")+"' nodeID='"+$(li[i]).find("a").attr("nodeID")+"'>"+$(li[i]).find("a").html()+"</li>";
			}
			$("#rightSide ul").html(sHtml);
		}else{
			return false;
		}
	};

	addPerson = function(o) {
		var nodeID = o.attr("nodeID"), obj = $("#rightSide ul li"), len = obj.length, flag = 0;
		var pxid = o.attr("pid");
		for ( var i = 0; i <= len; i++) {
			if (nodeID === obj.eq(i).attr("nodeID")) {
				return false;
			};
		};
		$("#rightSide ul").append("<li pid='"+pxid+"' nodeID='" + o.attr("nodeID") + "'>" + o.html() + "</li>");
		var s = $("#rightSide ul li"),slen = s.length,sA = new Array,sB,sC="";
		for(var j=0;j<slen;j++){
			sA[j] = {"pid":parseInt($(s[j]).attr("pid")),"nodeID":$(s[j]).attr("nodeID"),"name":$(s[j]).html()};
		};
		for(var k=0;k<slen-1;k++){
			for(var i=k;i<slen;i++){
				if(sA[k].pid>sA[i].pid){
					sB = sA[k];
					sA[k] = sA[i];
					sA[i]=sB;
				};
			};
		};
		for(var h=0;h<slen;h++){
			sC += "<li pid='"+sA[h].pid+"' nodeID='" + sA[h].nodeID + "'>" + sA[h].name + "</li>";
		};
		$("#rightSide ul").html(sC);
	};

	selectPerson = function(o) {
		var i = $("#rightSide ul li").index(o), len = $("#rightSide ul li").length;
		$("#rightSide ul li").removeClass("select");
		o.addClass("select");
	};

	deletePerson = function(o) {
		o.remove();
	};

	savePerson = function(obj, box, oOrgUser) {
		var o = $("#rightSide li"), name = new Array, len = o.length, nodeId = new Array,pid=new Array;
		for ( var i = 0; i < len; i++) {
			name.push(o.eq(i).html());
			nodeId.push(o.eq(i).attr("nodeID"));
			pid.push(o.eq(i).attr("pid"));
		}
		obj.val(name.join(","));
		box.val(nodeId.join(","));
		box.attr("rel",pid.join(","));
		
		// 拼装办理意见
		if (oOrgUser && oOrgUser["transcontent"]) {
			genHandleComments(oOrgUser);
		}
		
		//selnr();
		if(obj.val().trim()!=''){
			$("#zchgr").attr("value","");
			$("#zchgr").attr("disabled",true);
		}else if(obj.val().trim()==''){
			$("#zchgr").attr("disabled",false);
		}
		clearPerson();
		closeAlert("attAlert");
	};
	
	displayPerson = function(o,n) {
		n.toggle(0,function(){});
	};
	
	clearPerson = function() {
		$("#rightSide ul").html("");
		
	};
	
	this.init = function() {
		//var $t = this;
		var tip = "<div class='tip'><p></p><b class='out'></b><b class='in'></b></div>";
		$("#leftSide").html("");
		$("#rightSide ul").html("");
		$("#l-r-arrow .rb").eq(0).addClass("r");
		$("#leftSide dl").addClass("hide");
		createList(getObj(), getBox(), getHiddenbox());
		if(!$("#leftSide h2").length){
			$("#l-r-arrow .lb").eq(0).addClass("l");
		}else{
			$("#l-r-arrow .lb").eq(0).removeClass("l");
		}
		$("#leftSide dd a").live('click', function(e) {
			$this = $(this);
			addPerson($this);
			e.preventDefault();
		});
		$("#rightSide li").live("click", function() {
			$this = $(this);
			deletePerson($this);
		});
		$("#clear").bind('click', function() {
			clearPerson();
			closeAlert("attAlert");
		});
		$("#save").bind('click', function() {
			savePerson(getBox(), getHiddenbox(), getOrgUser());
			createList(getObj(), getBox(), getHiddenbox());
			$("#save").unbind("click");
		});
		$("#leftSide h2").bind('click', function(e) {
			$this = $(this);
			displayPerson($this,$this.next());
			e.preventDefault();
		});
		$("#l-r-arrow .lb").eq(0).bind('click',function(){
			addAllPerson(getObj());
		});
		$("#l-r-arrow .rb").eq(0).bind('click',function(){
			clearPerson();
		});
		$("#l-r-arrow .lb").eq(0).hover(function(){
			if(!$("#leftSide h2").length){
				$(this).html(tip);
				$(this).find("p").html("点击全选所有列项。");
			}
		},function(){
			$(this).html("");
		});
		$("#l-r-arrow .rb").eq(0).hover(function(){
			$(this).html(tip);
			$(this).find("p").html("点击删除已选择列项。");
		},function(){
			$(this).html("");
		});
		
		$("#ui_sKey").bind('keyup',function(){
			search();
		});
	}
}
	
	function person2(obj, box, hiddenbox) {
		getObj = function(){ return obj; };
		getBox = function(){ return box; };
		getHiddenbox = function(){ return hiddenbox; };
		
		createList = function(obj, box, hiddenbox) {
			var pJson = obj;
			var len = pJson.length, sHtml = "", list = new Array;
			var nameVal = box.val(), nodeVal = hiddenbox.val(), name = new Array, node = new Array,
				pidVal = hiddenbox.attr("rel"),pidA = new Array;
			
			
			// create head
			for ( var i = 0; i < len; i++) {
				if (pJson[i].levle == 1) {
					sHtml += "<h2 id='" + pJson[i].nodeID + "'>" + pJson[i].name + "</h2>";
				}
			}
			$("#leftSide").html(sHtml);
			// create list
			
			// create search
			var hLen = $("#leftSide h2").length;
			if(!hLen){
				if(len>15){
					sHtml += "<div style='width:154px;overflow:hidden;'><input type='text' id='ui_sKey' style='width:110px;height:22px;line-height:22px;float:left;padding-left:2px;' value='' />" +
							//"<input type='button' id='ui_sBtn' style='float:left;padding-left:0;padding-right:0;' class='btn' value='搜索' />" +
							"</div>";
				}
			}
			
			if (hLen) {
				for ( var j = 0; j < hLen; j++) {
					var belongID = $("#leftSide h2").eq(j).attr("id");
					list[j] = "<dl class='hide " + belongID + "'>";
					for ( var i = 0; i < len; i++) {
						if (pJson[i].belongID == belongID) {
							list[j] += "<dd><a pid='"+i+"' href='#' nodeID='" + pJson[i].nodeID
									+ "'>" + pJson[i].name + "</a></dd>";
						}
					}
					list[j] += "</dl>";
					$("#" + belongID).after(list[j]);
				}
			} else {
				sHtml += "<dl>";
				for ( var i = 0; i < len; i++) {
					sHtml += "<dd><a pid='"+i+"' href='#' nodeID='" + pJson[i].nodeID + "'>" + pJson[i].name + "</a></dd>";
				}
				sHtml += "</dl>";
				$("#leftSide").html(sHtml);
			};
			if(nameVal.trim() != ""){ 
				 this.clearPerson(); 
				 name = nameVal.split("、"); 
				 node = nodeVal.split(",");
				 //if(pidVal!=undefined)pidA = pidVal.split(",");
				 var lens =name.length; 
				 for(var i=0;i<lens;i++){
					 var oldHtml = $("#rightSide ul").html();
					 //if(pidA[i]==undefined)pidA[i] = 0;
					 //alert(pidA[i]);
					 var $this = $("#leftSide dd");
					 for(var k=0,dd = $this.length;k<dd;k++){
						 if($this.eq(k).find("a").attr("nodeID")==node[i]) pidA[i]=$this.eq(k).find("a").attr("pid");
					 }
					 $("#rightSide ul").append("<li pid='"+pidA[i]+"' nodeID='"+node[i]+"'>"+name[i]+"</li>"); 
				}
			 }
		};
		
		search=function(){
			var hLen = $("#leftSide h2").length,sHtml = "",len = getObj().length;
			var key = $("#ui_sKey").val().trim();
			if(key===""){
				alert("请输入关键字！");
				return false;
			}
			for ( var i = 0; i < len; i++) {
				if(getObj()[i].name.indexOf(key)>=0)
					sHtml += "<dd><a href='#' nodeID='" + getObj()[i].nodeID + "'>" + getObj()[i].name + "</a></dd>";
			}
			if(sHtml.trim()===""){
				$("#leftSide dl").html("<dt>没有搜索到你要的结果！</dt>");
				return;
			}
			$("#leftSide dl").html(sHtml);
		};
		
		addAllPerson=function(obj){
			var hLen = $("#leftSide h2").length;
			var sHtml = "";
			var pJson = obj;
			var len = pJson.length;
			var li = $("#leftSide dl dd");
			if(!hLen){
				this.clearPerson();
				for ( var i = 0; i < li.length; i++) {
					sHtml += "<li pid='"+$(li[i]).find("a").attr("pid")+"' nodeID='"+$(li[i]).find("a").attr("nodeID")+"'>"+$(li[i]).find("a").html()+"</li>";
				}
				$("#rightSide ul").html(sHtml);
			}else{
				return false;
			}
		};

		addPerson = function(o) {
			var nodeID = o.attr("nodeID"), obj = $("#rightSide ul li"), len = obj.length, flag = 0;
			var pxid = o.attr("pid");
			for ( var i = 0; i <= len; i++) {
				if (nodeID === obj.eq(i).attr("nodeID")) {
					return false;
				};
			};
			$("#rightSide ul").append("<li pid='"+pxid+"' nodeID='" + o.attr("nodeID") + "'>" + o.html() + "</li>");
			var s = $("#rightSide ul li"),slen = s.length,sA = new Array,sB,sC="";
			for(var j=0;j<slen;j++){
				sA[j] = {"pid":parseInt($(s[j]).attr("pid")),"nodeID":$(s[j]).attr("nodeID"),"name":$(s[j]).html()};
			};
			for(var k=0;k<slen-1;k++){
				for(var i=k;i<slen;i++){
					if(sA[k].pid>sA[i].pid){
						sB = sA[k];
						sA[k] = sA[i];
						sA[i]=sB;
					};
				};
			};
			for(var h=0;h<slen;h++){
				sC += "<li pid='"+sA[h].pid+"' nodeID='" + sA[h].nodeID + "'>" + sA[h].name + "</li>";
			};
			$("#rightSide ul").html(sC);
		};

		selectPerson = function(o) {
			var i = $("#rightSide ul li").index(o), len = $("#rightSide ul li").length;
			$("#rightSide ul li").removeClass("select");
			o.addClass("select");
		};

		deletePerson = function(o) {
			o.remove();
		};

		savePerson = function(obj, box) {
			var o = $("#rightSide li"), name = new Array, len = o.length, nodeId = new Array,pid=new Array;
			for ( var i = 0; i < len; i++) {
				name.push(o.eq(i).html());
				nodeId.push(o.eq(i).attr("nodeID"));
				pid.push(o.eq(i).attr("pid"));
			}
			obj.val(name.join("、"));
			box.val(nodeId.join(","));
			box.attr("rel",pid.join(","));
			selnr();
			resetBlyj();
			if(obj.val().trim()!=''){
				$("#zchgr").attr("value","");
				$("#zchgr").attr("disabled",true);
			}else if(obj.val().trim()==''){
				$("#zchgr").attr("disabled",false);
			}
			clearPerson();
			closeAlert("zrAlert");
		};
		
		displayPerson = function(o,n) {
			//$("#leftSide dl").addClass("hide");
			//n.removeClass("hide");
			if(n.hasClass("flag")){
				//n.removeClass("hide");
				$("#leftSide dl").addClass("hide");
				n.addClass("hide");
				n.removeClass("flag");
			}else{
				$("#leftSide dl").addClass("hide");
				n.removeClass("hide");
				n.addClass("flag");
			}
		};
		
		clearPerson = function() {
			$("#rightSide ul").html("");
			
		};
		selnr=function(){
			if(!$('#issjzq').length){
				closeAlert("zrAlert");
				this.clearPerson();
				return;
			}

			if($('#issjzq').val()=="")
	        	return ;
	        	
			var text=$('#issjzq').html();
			if(text=='退回'||text=='回退'){// 如果是退回的话，只写退回。 
				$("textArea").eq(0).html(text);
			}else if(text=='暂缓接收'){
				$("#idea").html("纸质文书未到,暂缓接收");
			}else{// 如果不是退回，则全部处理
	     		var fgwzr=$("#fgwzr").val();
				var index=$('#zbcsdep')[0].selectedIndex ;
				 var zbcsdep;
				if(index==0)
					zbcsdep='';
				else
			        zbcsdep=document.getElementById("zbcsdep").options[index].text;
			    var xbcsbm=$("#xbcsbm").val();
			    var xbcsbmArray=xbcsbm.split(",");
			    var xbcsbm2="";
			    for(var i=0;i<xbcsbmArray.length;i++){
			    	if(i<xbcsbmArray.length-1)
			    		xbcsbm2+=xbcsbmArray[i]+"、";
			    	else
			    		xbcsbm2+=xbcsbmArray[i];
			    }
				var s1='';
				var s='';
				if(fgwzr==''|| fgwzr==undefined)
					s1='';
				else{
					if(fgwzr.indexOf('陈 勇')>0){
						s1='请'+fgwzr.substring(0,fgwzr.length-4)+'同志阅示,'+fgwzr.substring(fgwzr.length-3,fgwzr.length)+'同志阅。';
					}else
						s1='请'+fgwzr+'同志阅示。';
				}
				if(zbcsdep==''&&xbcsbm2=='')
					s=s1;
				else if(zbcsdep==''&&xbcsbm2!='')
					s=s1+'请'+xbcsbm2+'协办。';
				else if(zbcsdep!=''&&xbcsbm2=='')
					s=s1+'请'+zbcsdep+'主办。';
				else if(zbcsdep!=''&&xbcsbm2!='') 
					s=s1+'请'+zbcsdep+'会'+xbcsbm2+'阅办。';
				$("textArea").eq(0).html(s);
		     }
		
		};
		
		resetBlyj=function(){
			if($('#zbcs').val()==undefined)
	        	return ;
			var dex = $('#zbcs')[0].selectedIndex;
			var zbcsdep='';
			var s='';
			if (dex != 0){
				zbcsdep = document.getElementById("zbcs").options[dex].text;
			}
			var xbcsbm = $("#xbcsnames").val();
			if (zbcsdep == '' && xbcsbm == '')
				s += '';
			else if (zbcsdep == '' && xbcsbm != '')
				s += '请' + xbcsbm + '协办。';
			else if (zbcsdep != '' && xbcsbm == '')
				s += '请' + zbcsdep + '主办。';
			else if (zbcsdep != '' && xbcsbm != '')
				s +=  '请' + zbcsdep + '会' + xbcsbm + '阅办。';
				$("#blyj").html(s);
		};
		this.init = function() {
			//var $t = this;
			var tip = "<div class='tip'><p></p><b class='out'></b><b class='in'></b></div>";
			$("#leftSide").html("");
			$("#rightSide ul").html("");
			$("#l-r-arrow .rb").eq(0).addClass("r");
			createList(getObj(), getBox(), getHiddenbox());
			if(!$("#leftSide h2").length){
				$("#l-r-arrow .lb").eq(0).addClass("l");
			}else{
				$("#l-r-arrow .lb").eq(0).removeClass("l");
			}
			$("#leftSide dd a").live('click', function(e) {
				$this = $(this);
				addPerson($this);
				e.preventDefault();
			});
			$("#rightSide li").live("click", function() {
				$this = $(this);
				deletePerson($this);
			});
			$("#clear").bind('click', function() {
				clearPerson();
				closeAlert("zrAlert");
			});
			$("#save").bind('click', function() {
				savePerson(getBox(), getHiddenbox());
				createList(getObj(), getBox(), getHiddenbox());
				$("#save").unbind("click");
			});
			$("#leftSide h2").live('click', function(e) {
				$this = $(this);
				displayPerson($this,$this.next());
				e.preventDefault();
			});
			$("#l-r-arrow .lb").eq(0).bind('click',function(){
				addAllPerson(getObj());
			});
			$("#l-r-arrow .rb").eq(0).bind('click',function(){
				clearPerson();
			});
			$("#l-r-arrow .lb").eq(0).hover(function(){
				if(!$("#leftSide h2").length){
					$(this).html(tip);
					$(this).find("p").html("点击全选所有列项。");
				}
			},function(){
				$(this).html("");
			});
			$("#l-r-arrow .rb").eq(0).hover(function(){
				$(this).html(tip);
				$(this).find("p").html("点击删除已选择列项。");
			},function(){
				$(this).html("");
			});
			
			$("#ui_sKey").bind('keyup',function(){
				search();
			});
		}
}
