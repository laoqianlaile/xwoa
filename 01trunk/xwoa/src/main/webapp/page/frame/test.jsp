<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>

<title></title>
<style type="text/css">
ul li { list-style:none; }
ul ul { display:block; }
</style>
</head>

<body>
<%-- <sj:head locale="zh_CN" /> --%>
	<div id="lists" class="getTree"></div>
	<script type="text/javascript">
	var getID = function( id ){
		return document.getElementById(id);	
	};
	
	var menuList = {
			"menuList":[
						  	{"MID":"aa","MText":"一级子菜单1","ParentID":"a"},
						  	{"MID":"ab","MText":"一级子菜单2","ParentID":"a"},
							{"MID":"ac","MText":"一级子菜单3","ParentID":"a"} ,
							{"MID":"ad","MText":"二级子菜单1","ParentID":"aa"},
							{"MID":"af","MText":"二级子菜单1","ParentID":"aa"},
							{"MID":"ae","MText":"二级子菜单2","ParentID":"ab"},
							{"MID":"ah","MText":"三级子菜单2","ParentID":"ae"},
							{"MID":"ag","MText":"四级子菜单2","ParentID":"ae"}
						  ]
		};
	var Menu = {};
	Menu.menuDate = {
		//判断对象是否为空
		isEmptyObject:function(jsonList){
			for( var name in jsonList ){
				return false;
			}
			return true;
		},
		
		//动态判断nodeName 是否存在
		haveNode:function(json,nodeName){
			if( json[nodeName] === undefined ){
				return false;
			}
			return true;
		},
		
		//固定的json格式，不管命名的变化
		getFirstNode:function( json ){
			var firstNode = new Array;
			for( var name in json ){
				firstNode.push(name);
			}
			return firstNode;
		},
		
		//获取菜单列表
		getList:function(json,nodeName){
			return json[nodeName];
		},
		
		getNode:function(list,flag){
			var v = new Array;
			for( var i=0,len=list.length;i<len;i++ ){
				if( parseInt(list[i][flag])===1 ){
					v.push(list[i]);	
				}
			}
			return v;
		}
	};
	
	Menu.menuView = {
		//生成菜单类
		headList:function(){
			var menuView = new Array;
			menuView.push("<div class='accordionContent' id='menu_a'><ul id='ul_a' class='tree treeFolder'></ul></div>");
			return menuView;
		},
		//子菜单 二级/三级
		menuList:function(list,node){
			var menuView = new Array;
			for( var i=0,len=list.length;i<len;i++ ){
				menuView.push("<li id='menu_"+list[i]["MID"]+"' parentId='"+list[i]["ParentID"]+"'><div><span rel='"+list[i]["MID"]+"' >"+list[i]["MText"]+"</span></div></li>");
			}
			return menuView;
		},
		
		addUL:function( id,liView ){
			var ulDom = getID("ul_"+id),menuDom = getID("menu_"+id);
			if( ulDom === null ){
				menuDom.innerHTML += "<ul id='ul_"+id+"' class='tree'>"+liView+"</ul>";
			}else{
				ulDom.innerHTML += liView;
			}
		},
		
		addLevel:function(){
			var $tree = $("ul.tree"),
				len = $tree.length;
			
			$tree.each(function(index){
				var $li = $('>li:not(:last)',$(this)),
					$last = $('>li:last',$(this));
				$('span',$li).before("<div class='line'></div>");
				$('span',$last).before("<div class='indent'></div>");
				
			});
			$tree.each(function(index){//last_expandable
				$('li:last:not(ul)',$(this)).addClass('last');
				$('>li',$(this)).find('div:first div:last').removeClass().addClass('node');
				$('>li:has(ul)',$(this)).find('div:first div:last').removeClass().addClass('collapsable');
				$('>li:last:has(ul)',$(this)).find('div:first div:last').removeClass().addClass('last_collapsable');
			});
		}
	};
	
	function menuDisplay(){
		var mv = Menu.menuView,md = Menu.menuDate;
		if( md.haveNode(menuList,"menuList") && !md.isEmptyObject(menuList) ){
			var c = mv.headList().join("");
			
			getID("lists").innerHTML = c;
			var d = mv.menuList( md.getList(menuList,"menuList") );
			
			for( var i=0,len=d.length;i<len;i++ ){
				var id = d[i].match(/(.*)parentId='(.*)'>(.*)/)[2];
				mv.addUL( id,d[i] );
			};
		}
		mv.addLevel();
	};
	
	menuDisplay();
	
	</script>
</body>
</html>