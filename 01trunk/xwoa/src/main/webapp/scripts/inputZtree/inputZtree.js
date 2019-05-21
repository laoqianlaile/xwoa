var setting = {
				data: {
					key: {
						title:"t"
					},
					simpleData: {
						enable: true
					}
				},
				callback: {
					onMouseDown: onMouseDown
				}
			};
     var targetId;
			function onMouseDown(event, treeId, treeNode) {
				if(treeNode){
					targetId = treeId.replace("treeDemo_","");
					$("#"+targetId).val(treeNode.name);
					if($("#"+targetId+"_submit")){
						$("#"+targetId+"_submit").val(treeNode.id);						
					}
					$("#treeDiv_"+targetId).hide();	
					 document.getElementById("treeDiv_"+targetId).ondblclick = function(){
					    	$("#"+targetId).val("");
					    	$("#"+targetId+"_submit").val("");
					    };
				}
			}
     
		//数据字典树形下拉框方法
			
		   function inputTree(id,code){
			   if(targetId&&targetId!=id){
				   $("#treeDiv_"+targetId).hide(); 
			   }
			   targetId = id;
			   if(document.getElementById("treeDiv_"+id)){
				   if($("#treeDiv_"+id).is(":hidden")){
					   $("#treeDiv_"+id).show();
				   }
				   else{
					   $("#treeDiv_"+id).hide();
				   }				
			     }
			  else{	      				    	
				    $.getJSON("${pageContext.request.contextPath}/app/dashboard!treeMenu.do", {key: code}, function(data) {
						   if(data){
							   $("#"+id).after("<br/><div id='treeDiv_"+id+"'  style='width:220px;height:370px;position:fixed;_position:absolute;overflow:auto;z-index:100;'><ul id='treeDemo_"+id+"' class='ztree'></ul></div>");
							   oZTree.init($("#treeDemo_"+id), setting, data);
						   }
				    });
			     }
					   			
		     }
		   
		   //通用树形下拉框方法
		   
		   function inputCommonTree(id,url){
			   if(targetId&&targetId!=id){
				   $("#treeDiv_"+targetId).hide(); 
			   }
			   targetId = id;
			   if(document.getElementById("treeDiv_"+id)){
				   if($("#treeDiv_"+id).is(":hidden")){
					   $("#treeDiv_"+id).show();
				   }
				   else{
					   $("#treeDiv_"+id).hide();
				   }				
			     }
			  else{	      				    	
				    $.getJSON(url, function(data) {
						   if(data){
							   $("#"+id).after("<br/><div id='treeDiv_"+id+"'  style='width:220px;height:370px;position:fixed;_position:absolute;overflow:auto;z-index:100;'><ul id='treeDemo_"+id+"' class='ztree'></ul></div>");
							   $.fn.zTree.init($("#treeDemo_"+id), setting, data);
						   }
				    });
			     }
					   			
		     }
		   
		   function clickHide(e){
			   var e = window.event || e;
			   var target = e.srcElement || e.target;
			   if(targetId){
				   if(target.id.indexOf("treeDemo_")<0&&target.id!=targetId){
					   $("#treeDiv_"+targetId).hide();
				   }				   
			   }
		   }
		   document.onclick = clickHide;