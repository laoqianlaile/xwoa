String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g, "");
};

function treePerson(obj, box, hiddenbox, oOrgUser,personNum) {
	getObj = function(){ return obj; };
	getBox = function(){ return box; };
	getHiddenbox = function(){ return hiddenbox; };
	getOrgUser = function() {return oOrgUser;};
	
	createList = function(obj, box, hiddenbox) {
		var nameVal = box.val(), nodeVal = hiddenbox.val(), name = new Array, node = new Array;
		$('#leftSide').addClass('ztree');
		var setting = {
				data: {
					simpleData: {
						enable: true
					}
				},
				callback: {
					onClick: addPerson
				}
			};
		if(oZTree){
			oZTree.init($('#leftSide'), setting, obj,personNum);			
		}
		else{
			$.fn.zTree.init($('#leftSide'), setting, obj);
		}
		
		if(nameVal.trim() != ""){ 
			 this.clearPerson(); 
			 name = nameVal.split(","); 
			 node = nodeVal.split(",");
			 var lens =name.length; 
			 for(var i=0;i<lens;i++){
				 $("#rightSide ul").append("<li nodeID='"+node[i]+"'>"+name[i]+"</li>"); 
			}
		 }
	};
		
	addAllPerson=function(){
		var treeObj;
		if(oZTree){
			treeObj = oZTree.getZTreeObj("leftSide");
		}
		else{
			treeObj = $.fn.zTree.getZTreeObj("leftSide");
		}
		var sNodes = treeObj.getNodes();
		var selectNodes = treeObj.getSelectedNodes();
		var hLen = sNodes.length;
		var sHtml ='';
		var code = new Array();
		var EachChildNodes = function(node,obj){
			if(node.children ==undefined){
				if(code.length>0){
					for(var k = 0;k < code.length;k++){
						if(code[k]==node.id || node.type=="false"){
							return false;
						}
					}
				}
				if(node.type=="false"){
					return false;
				}
				var sortid = node.sortid==null?100000000:parseInt(node.sortid);
				if(personNum!=undefined){
					if($("#rightSide ul li").length>=personNum){
						return false;
					}
				}else{
					sHtml += "<li userorder='"+node.userorder+"' sortid='"+sortid+"' nodeID='"+node.id+"'>"+node.name+"</li>";
					code.push(node.id);		
				}
			}
			else{
				for(var j =0 ;j < node.children.length;j++){
					EachChildNodes(node.children[j],obj);
				}
			}
		};
		this.clearPerson();
		if(selectNodes.length>0){
			for ( var i = 0; i < selectNodes.length; i++) {
				EachChildNodes(selectNodes[i],treeObj);									
			}
		}
		else{
			for ( var i = 0; i < hLen; i++) {
				EachChildNodes(sNodes[i],treeObj);									
			}
		}
		$("#rightSide ul").html(sHtml);
		this.sortPerson();
	};

	addPerson = function(event, treeId, treeNode) {
		if(treeNode.isParent||treeNode.type=="false"){
			if(treeNode.isParent){
				var treeObj;
				if(oZTree){
					treeObj = oZTree.getZTreeObj("leftSide");
				}
				else{
					treeObj = $.fn.zTree.getZTreeObj("leftSide");
				}
				treeObj.expandNode(treeNode,!treeNode.open,true,false);
			}
			return false;
		}
		var nodeID = treeNode.id, obj = $("#rightSide ul li"), len = obj.length;
		var pxid = treeNode.userorder;
		var sortid = treeNode.sortid==null?100000000:parseInt(treeNode.sortid);
		for ( var i = 0; i <= len; i++) {
			if (nodeID === obj.eq(i).attr("nodeID")) {
				return false;
			};
		};
		if(personNum!=undefined){
			if($("#rightSide ul li").length>=personNum){
				return false;
			}
		}
		$("#rightSide ul").append("<li userorder='"+pxid+"' sortid='"+sortid+"' nodeID='" + nodeID + "'>" + treeNode.name + "</li>");
		sortPerson();
	};
	sortPerson = function(){
		var s = $("#rightSide ul li"),slen = s.length,sA = new Array,sB,sC="";
		for(var j=0;j<slen;j++){
			var sort = parseInt($(s[j]).attr("sortid"));
			sA[j] = {"pid":parseInt($(s[j]).attr("userorder")),"sortid":sort,"nodeID":$(s[j]).attr("nodeID"),"name":$(s[j]).html()};
		};
		for(var k=0;k<slen-1;k++){
			for(var i=k+1;i<slen;i++){
				if(sA[k].pid>sA[i].pid){
					sB = sA[k];
					sA[k] = sA[i];
					sA[i]=sB;
				}
				else if(sA[k].pid==sA[i].pid){
					if(sA[k].sortid>sA[i].sortid){
						sB = sA[k];
						sA[k] = sA[i];
						sA[i]=sB;
					}
				}
			};
		};
		for(var h=0;h<slen;h++){
			sC += "<li userorder='"+sA[h].pid+"' sortid='"+sA[h].sortid+"' nodeID='" + sA[h].nodeID + "'>" + sA[h].name + "</li>";
		};
		$("#rightSide ul").html(sC);		
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
		//box.attr("rel",pid.join(","));
		// 拼装办理意见
		if (oOrgUser && oOrgUser["transcontent"]) {
			genHandleComments(oOrgUser);
		}
		clearPerson();
		closeAlert("attAlert");
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
		createList(getObj(), getBox(), getHiddenbox());
		if(!$("#leftSide h2").length){
			$("#l-r-arrow .lb").eq(0).addClass("l");
		}else{
			$("#l-r-arrow .lb").eq(0).removeClass("l");
		}
		$("#rightSide li").live("click", function() {
			$this = $(this);
			deletePerson($this);
		});
		$("#t-b-arrow").delegate('#clear','click', function() {
			clearPerson();
			closeAlert("attAlert");
		});
		$("#t-b-arrow").undelegate('#save','click').delegate('#save','click', function() {
			savePerson(getBox(), getHiddenbox(), getOrgUser());
		});
		$("#l-r-arrow .lb").eq(0).bind('click',function(){
			addAllPerson(personNum);
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
	};
}
	
