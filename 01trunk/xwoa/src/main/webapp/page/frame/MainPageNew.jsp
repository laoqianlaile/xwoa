<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.centit.com/el/coderepo" prefix="cp"%>
<html lang="zh-cmn-Hans">
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    	<meta name="renderer" content="webkit">
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    	
		<title>${cp:MAPVALUE('SYSPARAM','SysName')}</title>
		
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/scripts/easyui/jquery-easyui-1.4.2/themes/${theme }/easyui.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/scripts/easyui/jquery-easyui-1.4.2/themes/icon.css">
		
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/scripts/easyui/themes/centit/qui/style.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/scripts/easyui/themes/centit/qui/blue/color.css">
	</head>
	
	<body class="easyui-layout">
	    <div id="main_header" data-options="region:'north'">
	    	<div class="easyui-panel header-tool" data-options="width: 350, height: 28">
	    		<a href="#" class="easyui-linkbutton" id="btn_exit_fullscreen" data-options="plain:true,iconCls:'icon-print'" onclick="fullScreen(false);" style="display: none;">退出全屏</a>
				<a href="#" class="easyui-linkbutton" id="btn_fullscreen" data-options="plain:true,iconCls:'icon-print'" onclick="fullScreen(true);">全屏显示</a>
				<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-save'">修改密码</a>
				<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-tip'">换肤</a>
				<a href="${pageContext.request.contextPath}/oa/oaHelpinfo!list.do" target="_blank" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-help'">帮助</a>
				<a href="${pageContext.request.contextPath}/j_spring_security_logout" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-back'">注销</a>
			</div>
	    </div>
	    
	    <div class="menu-container" data-options="region:'west', collapsedSize: 10">
	    	<div class="menu-left" style="width: 180px;">
		    	<div class="easyui-panel menu-search-panel" data-options="border: false">
			        <input id="menu_search" type="text" name="menuText" class="textinput" placeHolder="搜索菜单名称" />
			        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-search'" onclick="searchMenu()"></a>
				</div>
		    	<div id="main_menu" class="easyui-accordion" data-options="border: false">
		    	</div>
	    	</div>
	    	<div class="menu-split">
	    		<div class="menu-split-center" onclick="collapseMenu()" title="收缩面板"></div>
	    	</div>
	    </div>
	    
	    <div data-options="region:'center'">
	    	<div id="menu_tab" class="easyui-tabs" data-options="tabWidth: 112, fit: true, border: false, tools:'#tab_tools'">
			</div>
			<div id="tab_tools">
				<a href="javascript:void(0)" class="easyui-menubutton" data-options="menu:'#tab_tools_menu',iconCls:'icon-redo', hasDownArrow: false"></a>
			</div>
			<div id="tab_tools_menu" class="easyui-menu" style="width:150px;">
				<div data-options="name:'closeAll', iconCls:'icon-cancel'" class="close" name="closeAll">关闭所有标签页</div>
				<div class="menu-sep"></div>
			</div>
			<div id="menu_tab_menu" class="easyui-menu" style="width:150px;">
				<div data-options="name:'reload',iconCls:'icon-reload'" class="menu-reload">重新加载</div>
				<div class="menu-sep"></div>
				<div data-options="name:'close',iconCls:'icon-cancel'" class="close">关闭标签页</div>
				<div data-options="name:'closeAll'" class="close">关闭所有标签页</div>
				<div data-options="name:'closeOthers'" class="close">关闭其他标签页</div>
			</div>
	    </div>
	    
	    <div id="main_footer" data-options="region:'south'" style="height:30px">
	    	技术支持电话：025-84207500 @南大先腾有限公司 
	    </div>
	</body>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/easyui/jquery-1.11.2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/easyui/mustache.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/easyui/jquery.nicescroll.min.js"></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/easyui/jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/easyui/jquery-easyui-1.4.2/plugins/jquery.layout.js"></script>
	
	<jsp:include page="/page/frame/template.jsp"></jsp:include>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/easyui/centit.template.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/easyui/centit.iframe.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/easyui/centit.menu.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/easyui/centit.tab.js"></script>
	
	
	
	<script type="text/javascript">
	function launchFullScreen(element) {
		  if(element.requestFullscreen) {
		    element.requestFullscreen();
		  } else if(element.mozRequestFullScreen) {
		    element.mozRequestFullScreen();
		  } else if(element.webkitRequestFullscreen) {
		    element.webkitRequestFullscreen();
		  } else if(element.msRequestFullscreen) {
		    element.msRequestFullscreen();
		  }
		  else {
			  $.messager.show({
				title:'提示信息',
				msg:'请手动按F11进入全屏模式',
				showType:'show',
				style:{
					left:'',
					right:0,
					top:document.body.scrollTop+document.documentElement.scrollTop,
					bottom:''
				}
			});
		  }
		}
	
		function fullScreen(full) {
			
			if (full) {
				$('body').layout('collapse', 'west');
				launchFullScreen(document.documentElement); 
				
				$("#btn_exit_fullscreen").show();
				$("#btn_fullscreen").hide();
			}
			else {
				$('body').layout('expand', 'west');
				
				if(document.exitFullscreen) {
				    document.exitFullscreen();
				  } else if(document.mozCancelFullScreen) {
				    document.mozCancelFullScreen();
				  } else if(document.webkitExitFullscreen) {
				    document.webkitExitFullscreen();
				  } else {
					  $.messager.show({
						title:'提示信息',
						msg:'请手动按ESC退出全屏模式',
						showType:'show',
						style:{
							left:'',
							right:0,
							top:document.body.scrollTop+document.documentElement.scrollTop,
							bottom:''
						}
					});
				  }
				
				$("#btn_exit_fullscreen").hide();
				$("#btn_fullscreen").show();
			}
		}
	
		function collapseMenu() {
			$('body').layout('collapse', 'west');
		}
		
		$("#main_menu").centitMenu({
			base: '${pageContext.request.contextPath}',
			url: '/sys/mainFrame!getJSONMenus.do',
			
			callback: function() {
				$('#main_menu .panel-body').niceScroll({cursorcolor:"#BCBCBC"});
				
				var panelMenu = $('#tab_tools_menu').menu({
					onShow: function() {
						var self = $(this);
						var selectedIndex = menuTab.tabs('getTabIndex', menuTab.tabs('getSelected'));
						
						self.find('div.menu-item').not('.close').each(function(index) {
							$(this).data('index', index);
							
							if (index == selectedIndex) {
								self.menu('setIcon', {
									target: this,
									iconCls: 'icon-ok'
								});
							}
							else {
								self.menu('setIcon', {
									target: this,
									iconCls: 'icon-null'
								});
							}
						});
					},
					onClick: function(item) {
						
						if (item.name) {
							return menuTab.menuTab(item.name);
						}
						
						menuTab.tabs('select', $(item.target).data('index'));
					}
				});
				
				var menuTab = $("#menu_tab").menuTab({
					onClose: function(title, index) {
						panelMenu.menu('removeItem', panelMenu.find('div.menu-item').not('.close')[index]);
					},
					onAdd: function(title, index) {
						panelMenu.menu('appendItem', {
							text: title
						});
					}
				});
				
				menuTab.menuTab('open', {
					id: 'DASHBOARD',
					text: '我的首页',
					url: '/dispatchdoc/vuserTaskListOA!list.do?s_flowCode=000392&s_itemtype=GW',
					closable: false
				});
				
				menuTab.on('contextmenu', function(e){
					e.preventDefault();
					
					var closable = menuTab.tabs('getSelected').panel('options').closable;
					
					// 判断关闭按钮是否可用
					menu.find('div.close').each(function() {
						// 不可用
						if (!closable) {
							menu.menu('disableItem', this);
						}
						// 可用
						else {
							menu.menu('enableItem', this);
						}
					});
					
					menu.menu('show', {
						left: e.pageX,
						top: e.pageY
					});
				});
				
				var menu = $('#menu_tab_menu').menu({
					onClick: function(item) {
						menuTab.menuTab(item.name);
					}
				});
			}
		});
		
		function searchMenu() {
			var search = $('#menu_search').val();
			
			if (!search) {
				$('#main_menu').centitMenu('clean');
				return;
			}
			
			$('#main_menu').centitMenu('search', search);
		}
	</script>
</html>

