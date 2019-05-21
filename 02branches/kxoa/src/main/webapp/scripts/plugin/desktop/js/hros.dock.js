/*
**  应用码头
*/
HROS.dock = (function(){
	return {
		/*
		**	初始化
		*/
		init : function(){
			$(window).resize(function(){
				HROS.dock.setPos();
			});
			HROS.dock.setPos();
			//绑定应用码头拖动事件
//			HROS.dock.move();
			//绑定应用码头2个按钮的点击事件
//			$('.dock-tool-setting').on('mousedown', function(){
//				return false;
//			}).on('click',function(){
//				if(HROS.base.checkLogin()){
//					HROS.window.createTemp({
//						appid : 'hoorayos-zmsz',
//						title : '桌面设置',
//						url : 'sysapp/desksetting/index.php',
//						width : 750,
//						height : 450,
//						isflash : false
//					});
//				}else{
//					HROS.base.login();
//				}
//			});
//			$('.dock-tool-style').on('mousedown', function(){
//				return false;
//			}).on('click', function(){
//				if(HROS.base.checkLogin()){
//					HROS.window.createTemp({
//						appid : 'hoorayos-ztsz',
//						title : '主题设置',
//						url : 'sysapp/wallpaper/index.php',
//						width : 580,
//						height : 520,
//						isflash : false
//					});
//				}else{
//					HROS.base.login();
//				}
//			});
			
			$('#dock-container a.exit').on('mousedown', function(){
				return false;
			}).on('click',function(){
				window.location.href = HROS.CONFIG.contextPath;
			});
			$('#dock-container a.closeall').on('mousedown', function(){
				return false;
			}).on('click', function(){
				HROS.window.closeAll();
			});
			$('#dock-container a.showdesk').on('mousedown', function(){
				return false;
			}).on('click', function(){
				HROS.window.hideAll();
			});
			$('#dock-container a.lock').on('mousedown', function(){
				return false;
			}).on('click', function(){
				
				if ($(this).hasClass('lock')) {
					$(this).removeClass('icon122')
					.removeClass('lock')
					.addClass('unlock')
					.addClass('icon123')
					.attr('title', '自动隐藏侧边栏');
					
					$('#dock-bar').off('hover');
				}
				else {
					$(this).removeClass('icon123')
					.removeClass('unlock')
					.addClass('lock')
					.addClass('icon122')
					.attr('title', '固定侧边栏');
					
					HROS.dock.hoverDock();
				}
				
			});
			
			$('#dock-bar').css({
				left:'-60px'
			});
			HROS.dock.hoverDock();
			
		},
		hoverDock : function() {
			$('#dock-bar').hover(function() {
				$(this).css({
					left:0
				});
			}, function() {
				$(this).css({
					left:'-60px'
				});
			});
		},
		setPos : function(){
			var desktop = $('#desk-' + HROS.CONFIG.desk), desktops = $('#desk .desktop-container');
			var desk_w = desktop.css('width', '100%').width(), desk_h = desktop.css('height', '100%').height();
			//清除dock位置样式
			$('#dock-container').removeClass('dock-top').removeClass('dock-left').removeClass('dock-right');
			$('#dock-bar').removeClass('top-bar').removeClass('left-bar').removeClass('right-bar').hide();
			if(HROS.CONFIG.dockPos == 'top'){
				$('#dock-bar').addClass('top-bar').children('#dock-container').addClass('dock-top');
				desktops.css({
					'width' : desk_w,
					'height' : desk_h - 143,
					'left' : desk_w,
					'top' : 73
				});
				desktop.css({
					'left' : 0
				});
			}else if(HROS.CONFIG.dockPos == 'left'){
				$('#dock-bar').addClass('left-bar').children('#dock-container').addClass('dock-left');
				desktops.css({
					'width' : desk_w - 73,
					'height' : desk_h - 70,
					'left' : desk_w + 73,
					'top' : 0
				});
				desktop.css({
					'left' : 73
				});
			}else if(HROS.CONFIG.dockPos == 'right'){
				$('#dock-bar').addClass('right-bar').children('#dock-container').addClass('dock-right');
				desktops.css({
					'width' : desk_w - 73,
					'height' : desk_h - 70,
					'left' : desk_w,
					'top' : 0
				});
				desktop.css({
					'left' : 0
				});
			}
			$('#dock-bar').show();
			HROS.taskbar.resize();
			HROS.folderView.resize();
		},
		updatePos : function(pos){
			if(pos != HROS.CONFIG.dockPos && typeof(pos) != 'undefined'){
				HROS.CONFIG.dockPos = pos;
				//更新码头位置
				HROS.dock.setPos();
				//更新应用位置
				HROS.deskTop.appresize();
				if(HROS.base.checkLogin()){
					$.ajax({
						type : 'POST',
						url : ajaxUrl,
						data : 'ac=setDockPos&dock=' + pos + '&desk=' + HROS.CONFIG.desk
					});
				}
			}
		},
		move : function(){
			$('#dock-container').on('mousedown',function(e){
				if(e.button == 0 || e.button == 1){
					var lay = HROS.maskBox.dock(), location;
					$(document).on('mousemove', function(e){
						lay.show();
						if(e.clientY < lay.height() * 0.2){
							location = 'top';		
						}else if(e.clientX < lay.width() * 0.5){
							location = 'left';
						}else{				
							location = 'right';
						}
						$('.dock_drap_effect').removeClass('hover');
						$('.dock_drap_effect_' + location).addClass('hover');
					}).on('mouseup', function(){
						$(document).off('mousemove').off('mouseup');
						lay.hide();
						HROS.dock.updatePos(location);
					});
				}
			});
		}
	};
})();